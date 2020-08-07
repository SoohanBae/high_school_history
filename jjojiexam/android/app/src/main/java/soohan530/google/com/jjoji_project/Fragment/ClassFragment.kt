package soohan530.google.com.jjoji_project.Fragment


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import soohan530.google.com.jjoji_project.DataBean.BaseUtil
import soohan530.google.com.jjoji_project.DataBean.Get_exam_list
import soohan530.google.com.jjoji_project.NetRetrofit.NetRetrofit
import soohan530.google.com.jjoji_project.R

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import android.widget.Toast
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AlertDialog
import soohan530.google.com.jjoji_project.Activity.ExamActivity
import soohan530.google.com.jjoji_project.Activity.MainActivity


class ClassFragment : Fragment() {

    var endClassData : ArrayList<Get_exam_list>  = ArrayList<Get_exam_list>()
    var notEndClassData : ArrayList<Get_exam_list>  = ArrayList<Get_exam_list>()
    lateinit var endView:ListView
    lateinit var endAdapter:endListAdapter
    lateinit var notEndView:ListView
    lateinit var notEndAdapter:notEndListAdapter
    lateinit var notEndText : TextView
    lateinit var endText : TextView



    var classCode = "AAAAAA"
    var showmode = 4

    val SHOW_END = 1
    val SHOW_NOT_END = 2
    val SHOW_CLASS = 4


    var notDataShow = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(soohan530.google.com.jjoji_project.R.layout.fragment_class, container, false)
        endView = view.findViewById<ListView>(soohan530.google.com.jjoji_project.R.id.endView)
        notEndView = view.findViewById<ListView>(soohan530.google.com.jjoji_project.R.id.notEndView)

        notEndText = view.findViewById<TextView>(soohan530.google.com.jjoji_project.R.id.notEndText)
        endText = view.findViewById<TextView>(soohan530.google.com.jjoji_project.R.id.endText)


        Log.d("lateinits",endText.text.toString())
        endAdapter = endListAdapter()
        endView.adapter = endAdapter
        endView.setOnItemClickListener { adapterView, view, i, l ->
            endShow(i)
        }

        notEndAdapter = notEndListAdapter()
        notEndView.adapter = notEndAdapter
        notEndView.setOnItemClickListener { adapterView, view, i, l ->
            notEndShow(i)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        dataUpdate()
        super.onActivityCreated(savedInstanceState)
    }

    fun dataUpdate(){



        if(showmode == SHOW_END){


            endUpdate()
            notEndClassData = ArrayList<Get_exam_list>()
            notEndAdapter.notifyDataSetChanged()
            notEndText.visibility = View.GONE


        }else if(showmode == SHOW_NOT_END){




            notEndUpdate()
            endClassData = ArrayList<Get_exam_list>()
            endAdapter.notifyDataSetChanged()
            endText.visibility = View.GONE

        }else if(showmode == SHOW_CLASS){
            endUpdate()
            notEndUpdate()
        }





    }

    fun notEndUpdate(){



        var notEndRes = NetRetrofit.ourInstance.service.get_exam_notEnd_list(classCode)

        notEndRes.enqueue(object : Callback<BaseUtil<ArrayList<Get_exam_list>>> {
            override fun onResponse(call: Call<BaseUtil<ArrayList<Get_exam_list>>>, response: Response<BaseUtil<ArrayList<Get_exam_list>>>) {
                if(response.code() == 200){
                    Log.d("data",response.body().toString())


                    notEndClassData = response.body()!!.data
                    notEndAdapter.notifyDataSetChanged()
                    notEndText.visibility = View.VISIBLE

                    setListViewHeightBasedOnChildren(notEndView)
                }else if(response.code() == 204){
                    notEndText.visibility = View.GONE
                    notEndClassData = ArrayList<Get_exam_list>()
                    notEndAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BaseUtil<ArrayList<Get_exam_list>>>, t: Throwable) {
                Log.e("Err", t.message)
            }
        })
    }

    fun endUpdate(){



        var endRes = NetRetrofit.ourInstance.service.get_exam_end_list(classCode)

        endRes.enqueue(object : Callback<BaseUtil<ArrayList<Get_exam_list>>> {
            override fun onResponse(call: Call<BaseUtil<ArrayList<Get_exam_list>>>, response: Response<BaseUtil<ArrayList<Get_exam_list>>>) {
                if(response.code() == 200){
                    Log.d("data",response.body().toString())


                    endClassData = response.body()!!.data
                    endAdapter.notifyDataSetChanged()
                    endText.visibility = View.VISIBLE
                    setListViewHeightBasedOnChildren(endView)

                }else if(response.code() == 204){
                    endText.visibility = View.GONE
                    endClassData = ArrayList<Get_exam_list>()
                    endAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BaseUtil<ArrayList<Get_exam_list>>>, t: Throwable) {
                Log.e("Err", t.message)
            }
        })
    }


    fun setListViewHeightBasedOnChildren(listView: ListView) {

        val listAdapter = listView.adapter



        var totalHeight = 0

        val desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.width, View.MeasureSpec.AT_MOST)



        for (i in (0..listAdapter.count - 1)) {

            val listItem = listAdapter.getView(i, null, listView)

            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)

            totalHeight += listItem.measuredHeight

        }


        val params = listView.layoutParams

        params.height = totalHeight + listView.dividerHeight * (listAdapter.count - 1)

        listView.layoutParams = params

        listView.requestLayout()

    }


    inner class endListAdapter : BaseAdapter(){

        override fun getCount(): Int {
            return endClassData.size
        }
        override fun getItem(position: Int): Any? {
            return null
        }
        override fun getItemId(position: Int): Long {
            return 0
        }
        override fun getView(position: Int, p1: View?, parent: ViewGroup?): View? {
            return allView(position,p1,parent,endClassData)
        }

    }

    inner class notEndListAdapter : BaseAdapter(){

        override fun getCount(): Int {
            return notEndClassData.size
        }
        override fun getItem(position: Int): Any? {
            return null
        }
        override fun getItemId(position: Int): Long {
            return 0
        }
        override fun getView(position: Int, p1: View?, parent: ViewGroup?): View? {
            return allView(position,p1,parent,notEndClassData)
        }

    }

    fun allView(position: Int, p1: View?, parent: ViewGroup?, classData : ArrayList<Get_exam_list>): View?{
        var convertView:View? = p1
        if(p1 == null){
            convertView = layoutInflater.inflate(soohan530.google.com.jjoji_project.R.layout.exam_row,null)
        }
        var titleText : TextView? = convertView?.findViewById<TextView>(soohan530.google.com.jjoji_project.R.id.examTitleText)
        var endTimeText : TextView? = convertView?.findViewById<TextView>(soohan530.google.com.jjoji_project.R.id.examEndTimeText)
        var infoText : TextView? = convertView?.findViewById<TextView>(soohan530.google.com.jjoji_project.R.id.examInfoTExt)
        var scoreText : TextView? = convertView?.findViewById<TextView>(soohan530.google.com.jjoji_project.R.id.examScoreText)

        var data : Get_exam_list = classData[position]

        var dateFormat = SimpleDateFormat("MM월 dd일 hh:mm")
        var useTime = SimpleDateFormat("hh:mm:ss").parse(data.examUseTime)


        var nowTimeData = System.currentTimeMillis()
        var nowTime = Date(nowTimeData)



        val currDate = SimpleDateFormat("hh:mm:ss").parse("00:00:00")

        Log.d("userTime",useTime.toString())

        titleText?.text = data.examName
        endTimeText?.text = "${dateFormat.format(data.examStartTime)} ~ ${dateFormat.format(data.examEndTime)}"
        infoText?.text = "${data.className} / ${data.examCount}문항 / 시험시간 ${(useTime.time - currDate.time)  / (60 * 1000)}분"

        if(nowTime.compareTo(data.examStartTime) == -1){
            scoreText?.text = "시작전"

            titleText?.setTextColor(Color.parseColor("#E6E6E6"))
            endTimeText?.setTextColor(Color.parseColor("#E6E6E6"))
            infoText?.setTextColor(Color.parseColor("#E6E6E6"))


        }
        else if(data.score == -1){
            scoreText?.text = "-"
        }else{
            scoreText?.text = "${data.score}점"
        }

        return convertView
    }


    fun endShow(i : Int) {

        var useTime = SimpleDateFormat("hh:mm:ss").parse(endClassData[i].examUseTime)
        val currDate = SimpleDateFormat("hh:mm:ss").parse("00:00:00")

        val builder = AlertDialog.Builder(MainActivity.mContext)
        builder.setTitle(endClassData[i].examName)
        builder.setMessage("${endClassData[i].className} / ${endClassData[i].examCount}문항 / 시험시간 ${(useTime.time - currDate.time)  / (60 * 1000)}분")
        builder.setPositiveButton("재시험 시작",
            DialogInterface.OnClickListener { dialog, which ->
                var examIntent = Intent(MainActivity.mContext,ExamActivity::class.java)
                examIntent.putExtra("data",endClassData[i])
                startActivity(examIntent)
            })
        builder.setNegativeButton("결과보기",
            DialogInterface.OnClickListener { dialog, which ->

            })
        builder.setNeutralButton("취소",
            DialogInterface.OnClickListener { dialog, which ->

            })
        builder.show()
    }


    fun notEndShow(i : Int) {

        var useTime = SimpleDateFormat("hh:mm:ss").parse(notEndClassData[i].examUseTime)
        val currDate = SimpleDateFormat("hh:mm:ss").parse("00:00:00")

        val builder = AlertDialog.Builder(MainActivity.mContext)
        builder.setTitle(notEndClassData[i].examName)
        builder.setMessage("${notEndClassData[i].className} / ${notEndClassData[i].examCount}문항 / 시험시간 ${(useTime.time - currDate.time)  / (60 * 1000)}분")
        builder.setPositiveButton("시험 시작",
            DialogInterface.OnClickListener { dialog, which ->
                var examIntent = Intent(MainActivity.mContext,ExamActivity::class.java)
                examIntent.putExtra("data",notEndClassData[i])
                startActivity(examIntent)
            })
        builder.setNegativeButton("취소",
            DialogInterface.OnClickListener { dialog, which ->

            })
        builder.show()
    }

}
