package soohan530.google.com.jjoji_project.NetRetrofit

import android.content.SharedPreferences
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import soohan530.google.com.jjoji_project.Activity.StartActivity.Companion.prefs


public class ReceivedCookiesInterceptor : Interceptor {


    //@Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())


        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            val cookies = HashSet<String>()

            for (header in originalResponse.headers("Set-Cookie")) {
                cookies.add(header)
            }

            // Preference에 cookies를 넣어주는 작업을 수행
//                SharedPreferenceBase.putSharedPreference(APIPreferences.SHARED_PREFERENCE_NAME_COOKIE, cookies)



            val editor : SharedPreferences.Editor= prefs.edit()

            Log.d("cookieData_put",cookies.toString())
            editor.putStringSet("cookie_value",cookies)
            editor.commit()

        }

        return originalResponse
    }
}