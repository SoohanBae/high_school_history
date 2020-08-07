package soohan530.google.com.jjoji_project.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daimajia.slider.library.SliderLayout

import android.widget.Toast

import com.daimajia.slider.library.Animations.DescriptionAnimation
import com.daimajia.slider.library.SliderTypes.BaseSliderView

import com.daimajia.slider.library.SliderTypes.TextSliderView

import android.util.Log
import android.widget.TextView
import com.daimajia.slider.library.Tricks.ViewPagerEx
import kotlinx.android.synthetic.main.activity_sign_up.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import soohan530.google.com.jjoji_project.Activity.MainActivity
import soohan530.google.com.jjoji_project.DataBean.*
import soohan530.google.com.jjoji_project.NetRetrofit.NetRetrofit
import soohan530.google.com.jjoji_project.R


class HomeFragment : Fragment(), BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    lateinit var mDemoSlider : SliderLayout


    var notEndCnt : Int = -1
    var endCnt : Int = -1
    var scoreAvg : Double = -1.0
    var userText : String = ""


    var data : ArrayList<Get_home_event_par> = ArrayList<Get_home_event_par>()
    var class_fragment : ClassFragment = ClassFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)



        var notEndCntView = view.findViewById<TextView>(R.id.notEndCnt)
        var endCntView = view.findViewById<TextView>(R.id.endCnt)
        var scoreAvgView = view.findViewById<TextView>(R.id.scoreAvg)
        var userTextView = view.findViewById<TextView>(R.id.userText)

        mDemoSlider = view.findViewById<SliderLayout>(R.id.slider)

        notEndCntView.text = "${notEndCnt} 개"
        endCntView.text = "${endCnt} 개"

        if(scoreAvg == -1.0){
            scoreAvgView.text = "-"
        }else{
            scoreAvgView.text = String.format("%.1f 점",scoreAvg)
        }

        userTextView.text = userText




        if(data == null)
            return view


        for (i in data!!) {
            val textSliderView = TextSliderView(MainActivity.mContext)
            // initialize a SliderLayout
            textSliderView
                .description(i.eventName)
                .image(i.eventImage)
                .setScaleType(BaseSliderView.ScaleType.Fit)
                .setOnSliderClickListener(this)

            //add your extra information
            textSliderView.bundle(Bundle())
            textSliderView.bundle
                .putString("extra", i.eventName)

            mDemoSlider.addSlider(textSliderView)
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion)
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
        mDemoSlider.setCustomAnimation(DescriptionAnimation())
        mDemoSlider.setDuration(4500)
        mDemoSlider.addOnPageChangeListener(this)


        class_fragment.classCode = "allListCode"
        class_fragment.showmode = class_fragment.SHOW_CLASS


        fragmentManager!!.beginTransaction().add(R.id.homeContain,class_fragment).commit()

        return view
    }








    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

    }

    override fun onSliderClick(slider: BaseSliderView?) {

    }
}