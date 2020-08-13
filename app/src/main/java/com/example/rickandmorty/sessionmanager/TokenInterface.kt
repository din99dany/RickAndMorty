package com.example.rickandmorty.sessionmanager

import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface TokenInterface {

    @FormUrlEncoded
    @POST("/oauth2/token")
    @Headers("Connection: close ",
            "Content-Length: 98 ",
            "Content-Type: application/x-www-form-urlencoded",
            "Host: api.fitbit-int.com")
    fun getToken( @Field("grant_type") grant_type:String,
                  @Field("username") username: String,
                  @Field("password") password:String,
                  @Field("client_id") client_id:String
                  ) : Single<TokenResponse>

}