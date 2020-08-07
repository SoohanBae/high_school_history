package soohan530.google.com.jjoji_project

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

public class AddCookiesInterceptor : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        // Preference에서 cookies를 가져오는 작업을 수행
//            val preferences = SharedPreferenceBase.getSharedPreference(
//                APIPreferences.SHARED_PREFERENCE_NAME_COOKIE,
//                HashSet<String>()
//            )

        val context: Context = StartActivity.contextOfApplication
        val prefs = context.getSharedPreferences(RetrofitCreator.FILE_NAME, Context.MODE_PRIVATE)

        val preferences = prefs.getString(RetrofitCreator.SHARED_PREFERENCE_NAME_COOKIE, "")

        for (cookie in preferences) {
            builder.addHeader("Cookie", cookie as String)
        }

        // Web,Android,iOS 구분을 위해 User-Agent세팅
        builder.removeHeader("User-Agent").addHeader("User-Agent", "Android")






        return chain.proceed(builder.build())
    }
}