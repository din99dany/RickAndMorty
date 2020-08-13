package com.example.rickandmorty.sessionmanager

import android.util.Log
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SesionManager {

    private  val TAG = "ApiResponse"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.fitbit-int.com")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val tokenInterface = retrofit.create(TokenInterface::class.java)

    var token: String? = null

    fun initializaToken() {

        Log.d(TAG,"something")

        tokenInterface
            .getToken("password","ricksanchez@spam4.me","TOTALLYSECUREPASSWORD!","228TQF")
            .retry{ e ->
                Log.d(TAG,e.message.toString())
                true
            }
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    token = it.token
                    Log.d(TAG, (token  == null).toString() )
                    if ( token != null ) {
                        Log.d(TAG, token.toString())
                    } else {
                        Log.d(TAG,"token null")
                    }
                },
                {
                    Log.d(TAG,"cant get token")
                })


    }

}