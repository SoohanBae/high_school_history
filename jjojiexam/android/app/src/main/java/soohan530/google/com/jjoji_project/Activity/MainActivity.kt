package soohan530.google.com.jjoji_project.Activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import soohan530.google.com.jjoji_project.NetRetrofit.NetRetrofit

import kotlin.math.min
import android.os.Build

import android.view.View
import android.content.DialogInterface
import android.support.v4.app.FragmentManager
import android.text.InputType
import android.widget.EditText
import android.support.v7.app.AlertDialog
import android.view.KeyEvent
import android.widget.TextView

import okhttp3.ResponseBody
import soohan530.google.com.jjoji_project.DataBean.*
import soohan530.google.com.jjoji_project.Fragment.CalendarFragment
import soohan530.google.com.jjoji_project.Fragment.ClassFragment
import soohan530.google.com.jjoji_project.Fragment.HomeFragment
import soohan530.google.com.jjoji_project.R


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var subMenu: Menu

    var classData : ArrayList<Get_user_list>? = ArrayList<Get_user_list>()
    companion object{

        lateinit var mContext : Context
    }

    var itemData : ArrayList<MenuItem> = ArrayList<MenuItem>()
    var class_fragment : ClassFragment = ClassFragment()
    var home_fragment : HomeFragment = HomeFragment()
    var calenadar_fragment : CalendarFragment = CalendarFragment()
    var classInit = false
    var calInit = false

    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setTitle(R.string.app_name)



        mContext = this

        val view = window.decorView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (view != null) {
                // 23 버전 이상일 때 상태바 하얀 색상에 회색 아이콘 색상을 설정
                view.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = Color.parseColor("#4794B0")
            }
        } else if (Build.VERSION.SDK_INT >= 21) {
            // 21 버전 이상일 때
            window.statusBarColor = Color.BLACK
        }



        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )


        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        Log.d("navigation",nav_view.menu.toString())

        var userNameText = nav_view.getHeaderView(0).findViewById<TextView>(R.id.nav_name)
        userNameText.text = intent.getStringExtra("userName")

        var userEmailText = nav_view.getHeaderView(0).findViewById<TextView>(R.id.nav_email)
        userEmailText.text = intent.getStringExtra("userEmail")


        home_fragment.userText = intent.getStringExtra("userName")
        home_fragment.endCnt = intent.getIntExtra("endCount",-1)
        home_fragment.notEndCnt = intent.getIntExtra("notEndCount",-1)
        home_fragment.scoreAvg = intent.getDoubleExtra("scoreAvg",-1.0)
        home_fragment.data = intent.getParcelableArrayListExtra<Get_home_event_par>("data")

//            main_intent.putExtra("userName",response.body()!!.data.userName)
//        main_intent.putExtra("userEmail",response.body()!!.data.userEmail)
//        main_intent.putExtra("endCount",response.body()!!.data.endCount)
//        main_intent.putExtra("notEndCount",response.body()!!.data.notEndCount)
//        main_intent.putExtra("scoreAvg",response.body()!!.data.scoreAvg)
//            main_intent.putExtra("arrayEvent",eventList)


        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.container_1,home_fragment).commit()



        subMenu = nav_view.menu.addSubMenu("과목별보기")
        classUpdate()

        var homeItem = nav_view.menu.getItem(0)
        homeItem.setChecked(true)




    }

    fun classUpdate(){

        var res = NetRetrofit.ourInstance.service.get_class_list()
        res.enqueue(object : Callback<BaseUtil<ArrayList<Get_user_list>>> {
            override fun onResponse(call: Call<BaseUtil<ArrayList<Get_user_list>>>, response: Response<BaseUtil<ArrayList<Get_user_list>>>) {

                subMenu.clear()

                var draw_number = arrayListOf(
                    R.drawable.zero,
                    R.drawable.one,
                    R.drawable.two,
                    R.drawable.three,
                    R.drawable.four, R.drawable.five,
                    R.drawable.six,
                    R.drawable.seven,
                    R.drawable.eight, R.drawable.nine)

                var body : BaseUtil<ArrayList<Get_user_list>>? = response.body()
                classData = body?.data


                if(classData == null) {
                    subMenu.add("수업이 없습니다! 추가해보세요")
                    return
                }

                itemData = ArrayList<MenuItem>()


                var dataLength : Int = classData!!.size - 1



                for (i in 0..dataLength){
                    subMenu.add(classData!![i].className)

                    var item = subMenu.getItem(i)

                    item.setIcon(draw_number[min(classData!![i].noExamCount,9)])
                    item.setCheckable(true)



                    itemData.add(item)

                }





            }

            override fun onFailure(call: Call<BaseUtil<ArrayList<Get_user_list>>>, t: Throwable) {
                Toast.makeText(StartActivity.contextOfApplication,"네트워크르 체크해 주세요", Toast.LENGTH_LONG)
            }
        })
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.





        when (item.itemId) {
            R.id.action_settings -> {
                setting_acttion()
                return true
            }
            R.id.action_refresh -> {
                refresh_acttion()
                return true
            }
            R.id.action_add -> {
                add_acttion()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }



    fun setting_acttion(){
        Log.d("acttion","setting")
    }

    fun refresh_acttion(){
        class_fragment.dataUpdate()
    }


    fun add_acttion(){
        Log.d("acttion","add")

        var m_Text : String = ""
        var builder : AlertDialog.Builder = AlertDialog.Builder(this)

        builder.setTitle("수업 추가")

        // Set up the input
        val input = EditText(this)
        input.hint = "수업코드 입력"
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input,60,0,60,0)

        // Set up the buttons
        builder.setPositiveButton("확인",
            DialogInterface.OnClickListener { dialog, which ->

                m_Text = input.text.toString()

                var data = Post_class(m_Text)
                var res = NetRetrofit.ourInstance.service.post_class(data)

                res.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if(response.code() == 200){
                            Toast.makeText(mContext,"수업 추가 완료",Toast.LENGTH_LONG).show()

                            runOnUiThread {
                                classUpdate()
                            }
                        }else{
                            Toast.makeText(mContext,"이미 추가되어 있는 수업",Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.e("Err", t.message)
                    }
                })

            })



        builder.setNegativeButton("닫기",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()

    }

    fun showClass(classCode:String, classMode : Int){

        class_fragment.classCode = classCode
        class_fragment.showmode = classMode

        if(!classInit){
            fragmentManager.beginTransaction().add(R.id.container_1,class_fragment).commit()
            classInit = true
        }else{

            class_fragment.dataUpdate()
        }
        fragmentManager.beginTransaction().hide(calenadar_fragment).commit()
        fragmentManager.beginTransaction().hide(home_fragment).commit()
        fragmentManager.beginTransaction().show(class_fragment).commit()



    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.




        if (itemData.size > 0){
            for(i in 0..(itemData.size - 1)){
                if (item.equals(itemData[i])) {

                    Log.d("itemId",i.toString())
                    showClass(classData!![i].classCode, class_fragment.SHOW_CLASS)

                }

            }
        }
        when (item.itemId) {
            R.id.nav_home -> {

                fragmentManager.beginTransaction().show(home_fragment).commit()
                fragmentManager.beginTransaction().hide(class_fragment).commit()
                fragmentManager.beginTransaction().hide(calenadar_fragment).commit()
            }
            R.id.nav_end -> {
                showClass("allListCode", class_fragment.SHOW_END)

            }
            R.id.nav_notEnd -> {
                showClass("allListCode", class_fragment.SHOW_NOT_END)
            }
            R.id.nav_calender->{
                if(!calInit){
                    fragmentManager.beginTransaction().add(R.id.container_1,calenadar_fragment).commit()
                    calInit = true
                }

                fragmentManager.beginTransaction().hide(class_fragment).commit()
                fragmentManager.beginTransaction().hide(home_fragment).commit()
                fragmentManager.beginTransaction().show(calenadar_fragment).commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onKeyDown(keyCode : Int, event : KeyEvent) : Boolean{
        if( event.getAction() == KeyEvent.ACTION_DOWN ){ //키 다운 액션 감지
            if( keyCode == KeyEvent.KEYCODE_BACK ){ //BackKey 다운일 경우만 처리
                val builder = AlertDialog.Builder(MainActivity.mContext)
                builder.setTitle("종료하시겠습니까")
                builder.setMessage("안녕")
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
        return super.onKeyDown( keyCode, event );
    }


}
