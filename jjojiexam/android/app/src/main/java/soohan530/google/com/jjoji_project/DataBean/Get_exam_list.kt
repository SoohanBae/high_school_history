package soohan530.google.com.jjoji_project.DataBean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Get_exam_list(
    var examNo : Int,
    var examName : String,
    var examStartTime : Date,
    var examEndTime : Date,
    var examUseTime : String,
    var classCode : String,
    var examOuting : Int,
    var examTimeOut : Int,
    var examCount : Int,
    var score : Int,
    var className : String) : Parcelable {

}