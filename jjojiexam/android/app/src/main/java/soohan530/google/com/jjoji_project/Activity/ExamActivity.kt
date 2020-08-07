package soohan530.google.com.jjoji_project.Activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import soohan530.google.com.jjoji_project.R
import android.os.Build
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_class.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import soohan530.google.com.jjoji_project.Fragment.ExamFragment
import soohan530.google.com.jjoji_project.NetRetrofit.NetRetrofit
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.view.KeyEvent
import android.widget.SeekBar
import android.widget.Toast
import android.widget.Toolbar
import kotlinx.android.synthetic.main.activity_exam.*
import okhttp3.ResponseBody
import soohan530.google.com.jjoji_project.DataBean.*
import java.text.SimpleDateFormat


class ExamActivity : AppCompatActivity() {

    var exam_fragment = ExamFragment()

    lateinit var baseAdapter : BaseAdapter

    lateinit var intentData : Get_exam_list
    var timeCount = 0
    var answerCount = 0
    var actionBar : ActionBar? = null
    lateinit var mContext : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(soohan530.google.com.jjoji_project.R.layout.activity_exam)

//        setSupportActionBar(toolbar)
//

        mContext = this

        intentData = intent.getParcelableExtra<Get_exam_list>("data")

        actionBar = supportActionBar
        actionBar?.title = intentData.examName
        actionBar?.subtitle = "로딩중 "
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.show()
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#00B0F0")))

        baseAdapter = BaseAdapter(supportFragmentManager)
        examPager.adapter = baseAdapter





        var notEndRes = NetRetrofit.ourInstance.service.get_answer_exam_list(intentData.examNo)

        notEndRes.enqueue(object : Callback<BaseUtil<ArrayList<Get_answer_exam_list>>> {
            override fun onResponse(call: Call<BaseUtil<ArrayList<Get_answer_exam_list>>>, response: Response<BaseUtil<ArrayList<Get_answer_exam_list>>>) {
                if(response.code() == 200){
                    Log.d("data",response.body().toString())


                        for (i in response.body()!!.data){
                            var answerFragment = ExamFragment()
                            answerFragment.data = i
                            baseAdapter.addItem(answerFragment)
                        }
                    seekBar.max = response.body()!!.data.size
                    seekBar.min = 1



                    seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

                        override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                            // Display the current progress of SeekBar
                            examPager.setCurrentItem(i-1,true)
                        }

                        override fun onStartTrackingTouch(seekBar: SeekBar) {
                            // Do something

                        }

                        override fun onStopTrackingTouch(seekBar: SeekBar) {

                        }
                    })



                    baseAdapter.notifyDataSetChanged()

                    }else if(response.code() == 204){
                    Log.e("Err","Err")
                }
            }

            override fun onFailure(call: Call<BaseUtil<ArrayList<Get_answer_exam_list>>>, t: Throwable) {
                Log.e("Err", t.message)
            }
        })

        examPager.setClipToPadding(false)
        val dpValue = 22
        val d = resources.displayMetrics.density
        val margin = (dpValue * d).toInt()
        examPager.setPadding(margin, 0, margin, 0)
        examPager.setPageMargin(margin / 2)


        examPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {


            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                seekBar.progress = p0+1

                var count = 0


                answerCount = count
            }
        })


        var t = Thread(CustomRunnable())
        t.start()
//        mHandler.postDelayed(mCustomRunnable,100)


    }

    fun sendData(){
        var examNo = intentData.examNo


        var minutes = timeCount / (60 )
        var seconds = (timeCount) % 60
        var useTime = String.format("00:%02d:%02d", minutes, seconds)

        var answerData = ArrayList<Post_answer_A>()
        for (i in baseAdapter.fragments){
            var a =""
            if(i.data.examASlot[0].examAName == null){
                a = i.answerTextData
            }else{
                a = (i.selectedPosition + 1).toString()
            }
            answerData.add(Post_answer_A(i.data.examQNumber,a))
        }

        var data = Post_answer(examNo,useTime,answerData)

        var res = NetRetrofit.ourInstance.service.post_answer(data)

        res.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.code() == 200){
                    var start_intent = Intent(mContext, StartActivity::class.java)
                    start_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)


                    startActivity(start_intent)
                }else{
                    Toast.makeText(MainActivity.mContext,"이미 추가되어 있는 수업",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("Err", t.message)
            }
        })


    }
    inner class CustomRunnable : Runnable{

        override fun run() {
            try{


                while(true){
                    timeCount += 1

                    Thread.sleep(998)
                    var sdf = SimpleDateFormat("")

                    var count = 0
                    for(i in baseAdapter.fragments){
                        if(i.selectedPosition != -1 || !i.answerTextData.equals("")){
                            count+=1
                        }
                    }
                    answerCount = count



                    var ab = intentData.examUseTime.split(":")
                    //var ab = "00:10:00".split(":")
                    var a = ab[0].toInt()*60*60 + ab[1].toInt()*60 + ab[2].toInt()
                    var time = a - timeCount

                    if(time == 0){
                        sendData()
                    }


                    var minutes = time / (60 )
                    var seconds = (time) % 60
                    var str = String.format("%02d분 %02d초", minutes, seconds);
                    runOnUiThread {

                        actionBar?.subtitle = "푼 문제 : ${count} / ${seekBar.max}, 남은시간 : ${str}"
                    }
                }

            }catch (e:InterruptedException){
                e.printStackTrace()
            }

        }
    }

     inner class BaseAdapter// 필수 생성자
        (fm: FragmentManager) : FragmentPagerAdapter(fm) {

        // ViewPager에 들어갈 Fragment들을 담을 리스트
        var fragments : ArrayList<ExamFragment> = ArrayList<ExamFragment>()

        override fun getItem(position: Int): Fragment {
            return fragments.get(position)
        }

        override fun getCount(): Int {
            return fragments.size
        }

        // List에 Fragment를 담을 함수
        fun addItem(fragment: ExamFragment) {
            fragments.add(fragment)
        }
    }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(soohan530.google.com.jjoji_project.R.menu.exam_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.





        when (item.itemId) {
            soohan530.google.com.jjoji_project.R.id.action_sub->{




                if(answerCount == seekBar.max) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("끝내겠습니까?")
                    builder.setMessage("시험 종료후 수정은 불가능합니다")
                    builder.setPositiveButton("완료",
                        DialogInterface.OnClickListener { dialog, which ->
                            sendData()
                        })
                    builder.setNegativeButton("취소",
                        DialogInterface.OnClickListener { dialog, which ->

                        })
                    builder.show()
                }else{
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("풀지않은 문제가 있습니다 완료하시겠습니까?")
                    builder.setMessage("풀지않은 문제는 오답처리 됩니다")
                    builder.setPositiveButton("완료",
                        DialogInterface.OnClickListener { dialog, which ->
                            sendData()
                        })
                    builder.setNeutralButton("안푼문제 이동",
                        DialogInterface.OnClickListener { dialog, which ->
                            for(i in 0..(seekBar.max - 1)){
                                if(baseAdapter.fragments[i].selectedPosition == -1 && baseAdapter.fragments[i].answerTextData.equals("")){
                                    examPager.setCurrentItem(i,true)
                                    break
                                }
                            }
                        })
                    builder.setNegativeButton("취소",
                        DialogInterface.OnClickListener { dialog, which ->

                        })
                    builder.show()
                }




                return true
            }

            android.R.id.home ->{


                val builder = AlertDialog.Builder(this)


                builder.setTitle("종료하시겠습니까")
                builder.setMessage("데이터는 저장되지 않습니다")
                builder.setPositiveButton("종료",
                    DialogInterface.OnClickListener { dialog, which ->
                        finish()
                    })
                builder.setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, which ->

                    })
                builder.show()


                //Toast.makeText(this,"백 이벤트",Toast.LENGTH_LONG).show()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }


    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("종료하시겠습니까")
        builder.setMessage("데이터는 저장되지 않습니다")
        builder.setPositiveButton("종료",
            DialogInterface.OnClickListener { dialog, which ->
                finish()
            })
        builder.setNegativeButton("취소",
            DialogInterface.OnClickListener { dialog, which ->

            })
        builder.show()

    }



}
