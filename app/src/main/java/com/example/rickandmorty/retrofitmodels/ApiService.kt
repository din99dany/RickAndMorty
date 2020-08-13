package com.example.rickandmorty.retrofitmodels

import android.content.Context
import android.util.Log
import com.example.rickandmorty.retrofitMihaiModels.*
import com.example.rickandmorty.roommodels.AppDatabase
import com.example.rickandmorty.sessionmanager.SesionManager
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiService(context: Context) {

    private val apiClient : ApiClient
    private val dbLocal : AppDatabase
    private val TAG = "ApiService"

    init {

        val clientVal = OkHttpClient.Builder().addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response? {

                Log.d(TAG,"FirstBuild:"+SesionManager.token ?: "wtff")
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("AUTHORIZATION", "Bearer " + SesionManager.token ?: "")
                    .build()
                return chain.proceed(newRequest)
            }
        }).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.fitbit-int.com/v3/internship-ram/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(clientVal)
            .build()

        apiClient = retrofit.create(ApiClient::class.java)

        dbLocal = AppDatabase.getInstance(context)
    }

    fun getCharacterPage(page: Int) : Single<List<MihaiCharacterModel>> {

        Log.d(TAG,"getCharacterPage")

        return dbLocal.characterDao()
            .getLocalPage(page)
            .flatMap { items ->
                if ( items.isEmpty() ) {
                    Log.d(TAG,"network call")
                    apiClient.getCharacters(page)
                        .subscribeOn(Schedulers.io())
                        .map { chars ->
                        dbLocal.characterDao().insertList(chars.map {
                            val paginated = it
                            paginated.page = page
                            paginated
                        })
                        chars
                    }
                } else {
                    Log.d(TAG,"database call")
                    Single.just( items )
                }
            }
            .subscribeOn(Schedulers.io())
    }

    fun getCharacterDetails(id: Int ) : Single<MihaiCharacterModelDetailed> {

        Log.d(TAG,"getCharacterDetails")

        return apiClient.getCharacterDetails(id)
            .subscribeOn(Schedulers.io())
    }

    fun getEpisodePage( page: Int ): Single<List<MihaiEpisodeModel>> {

        Log.d(TAG,"getEpisodePage")

        return dbLocal.characterDao()
            .getLocalEpisodePage(page)
            .flatMap { items ->
                if ( items.isEmpty() ) {
                    Log.d(TAG,"network call")
                    apiClient.getEpisodePage(page)
                        .subscribeOn(Schedulers.io())
                        .map { chars ->
                            dbLocal.characterDao().insertEpisodes(chars.map {
                                val paginated = it
                                paginated.page = page
                                paginated
                            })
                            chars
                        }
                } else {
                    Log.d(TAG,"database call")
                    Single.just( items )
                }
            }
            .subscribeOn(Schedulers.io())
    }

    fun getEpisodeDetails( id: Int ): Single<MihaiEpisodeModelDetailed>  {

        Log.d(TAG,"getEpisodeDetails")

        return apiClient.getEpisodeDetails(id)
            .retry{
                    e ->
                Log.d(TAG,e.message.toString())
                false
            }
            .subscribeOn(Schedulers.io())
    }

    fun getLocationsPage( page: Int ) : Single<List<MihaiLocationModel>> {

        return dbLocal.characterDao()
            .getLocalLocationPage(page)
            .flatMap { items ->
                if ( items.isEmpty() ) {
                    Log.d(TAG,"network call")
                    apiClient.getLocationsPage(page)
                        .subscribeOn(Schedulers.io())
                        .map { chars ->
                            dbLocal.characterDao().insertLocations(chars.map {
                                val paginated = it
                                paginated.page = page
                                paginated
                            })
                            chars
                        }
                } else {
                    Log.d(TAG,"database call")
                    Single.just( items )
                }
            }
            .subscribeOn(Schedulers.io())
    }

    fun getLocationDetails( id: Int ): Single<MihaiLocationModelDetailed> {
        Log.d(TAG,"getLocationDetails")

        return apiClient.getLocationDetails(id)
            .subscribeOn(Schedulers.io())
    }

}