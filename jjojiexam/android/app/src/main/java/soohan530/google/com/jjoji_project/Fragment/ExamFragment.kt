package soohan530.google.com.jjoji_project.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.fragment_exam.view.*
import soohan530.google.com.jjoji_project.Activity.MainActivity
import soohan530.google.com.jjoji_project.DataBean.ExamASlot


import soohan530.google.com.jjoji_project.DataBean.Get_answer_exam_list
import soohan530.google.com.jjoji_project.R


class ExamFragment : Fragment() {



    lateinit var data : Get_answer_exam_list

    var selectedPosition = -1

    var selectedTextView : RadioButton? = null

    var answerTextData : String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view  = inflater.inflate(R.layout.fragment_exam, container, false)

        var answerListView = view.findViewById<ListView>(R.id.answerListView)
        var answerText = view.findViewById<EditText>(R.id.answerText)
        var answerImage = view.findViewById<ImageView>(R.id.answerImage)
        var answerName = view.findViewById<TextView>(R.id.answerName)


        answerName.text = data.examQName
        DownloadImageTask(answerImage)
            .execute(data.examQImage)

        if(data.examASlot[0].examAName == null){
            answerListView.visibility = View.GONE

            answerText.setText(answerTextData)

            answerText.addTextChangedListener(object : TextWatcher{
                override fun afterTextChanged(p0: Editable?) {
                    answerTextData = answerText.text.toString()
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }
            })
        }else{
            answerText.visibility = View.GONE

            var answerAdapter = radioListAdapter()
            answerListView.adapter = answerAdapter


            setListViewHeightBasedOnChildren(answerListView)
            answerAdapter.notifyDataSetChanged()
        }



        return view
    }


    inner class radioListAdapter : BaseAdapter(){

        override fun getCount(): Int {
            return data.examASlot.size
        }
        override fun getItem(position: Int): Any? {
            return null
        }
        override fun getItemId(position: Int): Long {
            return 0
        }
        override fun getView(position: Int, p1: View?, parent: ViewGroup?): View? {
            var convertView:View? = p1
            if(p1 == null){
                convertView = layoutInflater.inflate(soohan530.google.com.jjoji_project.R.layout.answer_row,null)
            }

            var r = convertView?.findViewById<RadioButton>(R.id.radioButton)


            r?.text = data.examASlot[position].examAName


            if(selectedPosition == position){
                r?.isChecked = true
            }else{
                r?.isChecked = false
            }

            r?.setOnClickListener { v->
                if(selectedPosition != position){
                    if(selectedTextView != null){
                        selectedTextView?.isChecked = false
                    }

                    selectedPosition = position
                    selectedTextView = r

                    notifyDataSetChanged()
                }



            }

            return convertView
        }

    }


    fun setListViewHeightBasedOnChildren(listView: ListView) {

        val listAdapter = listView.adapter



        var totalHeight = 0

        val desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.width, View.MeasureSpec.AT_MOST)



        for (i in (0..listAdapter.count - 1)) {

            val listItem = listAdapter.getView(i, null, listView)

            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)

            totalHeight += 120

        }


        val params = listView.layoutParams

        params.height = totalHeight + listView.dividerHeight * (listAdapter.count - 1)

        listView.layoutParams = params

        listView.requestLayout()

    }


}