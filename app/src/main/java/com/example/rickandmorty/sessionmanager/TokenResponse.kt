package com.example.rickandmorty.sessionmanager

import com.google.gson.annotations.SerializedName

class TokenResponse {

    @field:SerializedName("access_token")
    var token: String = ""

}