package soohan530.google.com.jjoji_project.DataBean

//class BaseUtil <T> {
//    var success : String? = null
//    var message : String? = null
//    var errors : String? = null
//    var data : T? = null
//}

data class BaseUtil <T>(var success : String, var message : String, var errors : String,var data : T)