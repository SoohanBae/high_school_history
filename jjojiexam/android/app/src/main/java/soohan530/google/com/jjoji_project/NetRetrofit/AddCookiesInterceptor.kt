package soohan530.google.com.jjoji_project.NetRetrofit

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import soohan530.google.com.jjoji_project.Activity.StartActivity.Companion.prefs

public class AddCookiesInterceptor : Interceptor {


    //@Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        // Preference에서 cookies를 가져오는 작업을 수행
//            val preferences = SharedPreferenceBase.getSharedPreference(
//                APIPreferences.SHARED_PREFERENCE_NAME_COOKIE,
//                HashSet<String>()
//            )



        val preferences = prefs.getStringSet("cookie_value",HashSet<String>())

        Log.d("cookieData_get",preferences.toString())

        for (cookie in preferences) {
            builder.addHeader("Cookie", cookie)
        }



        // Web,Android,iOS 구분을 위해 User-Agent세팅
        builder.removeHeader("User-Agent").addHeader("User-Agent", "Android")






        return chain.proceed(builder.build())
    }
}