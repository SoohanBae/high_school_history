package soohan530.google.com.jjoji_project


import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import android.content.Context


class RetrofitCreator {

    companion object {
        val API_BASE_URL = "http://54.180.153.125:3000"
        val SHARED_PREFERENCE_NAME_COOKIE : String = "cookie_name"
        val FILE_NAME = "cookie_file"

        private fun defaultRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build()
        }

        fun <T> create(service: Class<T>): T {
            return defaultRetrofit().create(service)
        }

        private fun createOkHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                interceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                interceptor.level = HttpLoggingInterceptor.Level.NONE
            }


            var client : OkHttpClient = OkHttpClient()

            var addCookiesInterceptor : AddCookiesInterceptor = AddCookiesInterceptor()
            var receivedCookiesInterceptor : ReceivedCookiesInterceptor = ReceivedCookiesInterceptor()

            return OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addInterceptor(addCookiesInterceptor)
                .addInterceptor(receivedCookiesInterceptor)
                .build()

        }


    }



}