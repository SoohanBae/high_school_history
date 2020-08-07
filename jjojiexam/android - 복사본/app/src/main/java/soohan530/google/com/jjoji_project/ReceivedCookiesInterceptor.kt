package soohan530.google.com.jjoji_project

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


public class ReceivedCookiesInterceptor : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())


        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            val cookies = HashSet<String>()

            for (header in originalResponse.headers("Set-Cookie")) {
                cookies.add(header)
            }

            // Preference에 cookies를 넣어주는 작업을 수행
//                SharedPreferenceBase.putSharedPreference(APIPreferences.SHARED_PREFERENCE_NAME_COOKIE, cookies)

            val context: Context = StartActivity.contextOfApplication
            val prefs = context.getSharedPreferences(RetrofitCreator.FILE_NAME, Context.MODE_PRIVATE)
            val editor = prefs!!.edit()
            editor.putStringSet(RetrofitCreator.SHARED_PREFERENCE_NAME_COOKIE,cookies)


        }

        return originalResponse
    }
}