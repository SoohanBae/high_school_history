package soohan530.google.com.jjoji_project.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.daimajia.slider.library.Animations.DescriptionAnimation
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import soohan530.google.com.jjoji_project.DataBean.*
import soohan530.google.com.jjoji_project.NetRetrofit.NetRetrofit
import soohan530.google.com.jjoji_project.R


class StartActivity : AppCompatActivity() {


    lateinit var login_intent : Intent
    lateinit var main_intent : Intent
    companion object {
        lateinit var contextOfApplication: Context
        lateinit var prefs : SharedPreferences
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        contextOfApplication = this
        prefs = getSharedPreferences("cookie_file", Context.MODE_PRIVATE)

        login_intent = Intent(this, LoginActivity::class.java)
        main_intent = Intent(this, MainActivity::class.java)

        var res = NetRetrofit.ourInstance.service.get_home_userCount()
        res.enqueue(object : Callback<BaseUtil<Get_home_userCount>> {
            override fun onResponse(call: Call<BaseUtil<Get_home_userCount>>, response: Response<BaseUtil<Get_home_userCount>>) {


                if(response.code() == 200){
                    main_intent.putExtra("userName",response.body()!!.data.userName)
                    main_intent.putExtra("userEmail",response.body()!!.data.userEmail)
                    if(response.body()!!.data.endCount == null){
                        main_intent.putExtra("endCount",-1)
                    }else{

                        main_intent.putExtra("endCount",response.body()!!.data.endCount)
                    }
                    main_intent.putExtra("notEndCount",response.body()!!.data.notEndCount)
                    main_intent.putExtra("scoreAvg",response.body()!!.data.scoreAvg)
                    Log.d("avg1",response.body()!!.data.scoreAvg.toString())

                    var res2 = NetRetrofit.ourInstance.service.get_home_event_list()

                    res2.enqueue(object : Callback<BaseUtil<ArrayList<Get_home_event_par>>> {
                        override fun onResponse(call2: Call<BaseUtil<ArrayList<Get_home_event_par>>>, response2: Response<BaseUtil<ArrayList<Get_home_event_par>>>) {
                            if(response2.code() == 200){
                                var eventList = response2.body()!!.data


                                main_intent.putParcelableArrayListExtra("data",eventList)
                                main_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

                                startActivity(main_intent)
                                finish()
                                Log.d("chekc","로그인 체크")
                            }
                        }

                        override fun onFailure(call2: Call<BaseUtil<ArrayList<Get_home_event_par>>>, t2: Throwable) {
                            Toast.makeText(contextOfApplication,"서버와의 연결이 원할하지 않습니다.",Toast.LENGTH_LONG).show()
                        }
                    })



                }
                else{
                    startActivity(login_intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<BaseUtil<Get_home_userCount>>, t: Throwable) {
                Toast.makeText(contextOfApplication,"네트워크르 체크해 주세요",Toast.LENGTH_LONG)
            }
        })






    }



}



