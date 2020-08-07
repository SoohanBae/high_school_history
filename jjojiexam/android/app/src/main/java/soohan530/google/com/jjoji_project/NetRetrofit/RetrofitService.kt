package soohan530.google.com.jjoji_project.NetRetrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import soohan530.google.com.jjoji_project.DataBean.*


interface RetrofitService {
    @GET("user/loginCheck")
    fun loginCheck() : Call<BaseUtil<Post_user_login_result>>


    @POST("/user/login")
    fun post_user_login(@Body body: Post_user_login): Call<BaseUtil<Post_user_login_result>>

    @POST("user/signup")
    fun post_user_signup(@Body body: Post_user_signup): Call<ResponseBody>

    @GET("/class/list")
    fun get_class_list(): Call<BaseUtil<ArrayList<Get_user_list>>>

    @POST("/class")
    fun post_class(@Body body: Post_class): Call<ResponseBody>

    @GET("/exam/end/list")
    fun get_exam_end_list(@Query("classCode") classCode: String): Call<BaseUtil<ArrayList<Get_exam_list>>>

    @GET("/exam/notEnd/list")
    fun get_exam_notEnd_list(@Query("classCode") classCode: String): Call<BaseUtil<ArrayList<Get_exam_list>>>

    @GET("/home/event/list")
    fun get_home_event_list(): Call<BaseUtil<ArrayList<Get_home_event_par>>>

    @GET("/home/userCount")
    fun get_home_userCount(): Call<BaseUtil<Get_home_userCount>>

    @GET("/answer/exam/list")
    fun get_answer_exam_list(@Query("examNo") examNo : Int): Call<BaseUtil<ArrayList<Get_answer_exam_list>>>

    @POST("/answer")
    fun post_answer(@Body body: Post_answer): Call<ResponseBody>



}