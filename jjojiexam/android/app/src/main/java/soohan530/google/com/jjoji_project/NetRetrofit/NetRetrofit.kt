package soohan530.google.com.jjoji_project.NetRetrofit


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory;

class NetRetrofit {
    companion object {
        var ourInstance : NetRetrofit =
            NetRetrofit()

    }



    var retrofit : Retrofit = Retrofit.Builder()
    .baseUrl("http://10.80.161.248:3000")
    .addConverterFactory(GsonConverterFactory.create()) // 파싱등록
    .client(getClient())
    .build()

    var service : RetrofitService = retrofit.create(RetrofitService::class.java)

    fun getClient() : OkHttpClient {
        var client : OkHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(AddCookiesInterceptor())
            .addNetworkInterceptor(ReceivedCookiesInterceptor())
            .build()

        return client
    }
}





