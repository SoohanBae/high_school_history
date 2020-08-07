package soohan530.google.com.jjoji_project

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.MainThread
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.ArrayCompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class StartActivity : AppCompatActivity() {


    lateinit var compositeDisposable: CompositeDisposable

    companion object {
        lateinit var contextOfApplication: Context

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)


//        contextOfApplication = applicationContext
//        compositeDisposable = CompositeDisposable()
//
//        compositeDisposable.add(GithubApi.getRepoList("test")
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.newThread())
//            .subscribe({ response: GithubResponseModel ->
//                for (item in response.items) {
//                    Log.d("MainActivity", item.userEmail)
//                }
//            }, { error: Throwable ->
//                Log.d("MainActivity", error.localizedMessage)
//                Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
//            }))

        var intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }



}



