package com.example.rickandmorty.retrofitCharacter

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

class CharacterModel {

    @field:SerializedName("id")
    var id: Int = 0

    @field:SerializedName("image")
    var thumbnail: String = ""

    @field:SerializedName("name")
    var name: String = ""

    @field:SerializedName("gender")
    var gender: String = ""

    @field:SerializedName("status")
    var status: String = ""
}


class CharacterResponse {
    @field:SerializedName("results")
    var characters: List<CharacterModel>? = null
}



class Location {
    @field:SerializedName("name")
    var name: String = ""

    @field:SerializedName("url")
    var url: String = ""
}

class CharacterDetails{
    @field:SerializedName("episode")
    var episodes: List<String>? = null

    @field:SerializedName("location")
    var location: Location? = null

    @field:SerializedName("name")
    var name: String = ""

    @field:SerializedName("gender")
    var gender: String = ""

    @field:SerializedName("status")
    var status: String = ""

    @field:SerializedName("id")
    var id: Int = 0

    @field:SerializedName("image")
    var thumbnail: String = ""

}

class EpisodeModel {

    @field:SerializedName("id")
    var mId: Int = 0;

    @field:SerializedName("name")
    var mNmae: String = ""

    @field:SerializedName("air_date")
    var mData: String = ""

    @field:SerializedName("episode")
    var mSeason: String = ""

    @field:SerializedName("characters")
    var mCharacters: List<String> = listOf()

}

class EpisodeResponse {

    @field:SerializedName("results")
    var mEpisodes : List<EpisodeModel>? = null

}


class LocationModel {

    @field:SerializedName("id")
    var mId: Int = 0

    @field:SerializedName("name")
    var mName: String = ""

    @field:SerializedName("type")
    var mType: String = ""

    @field:SerializedName("dimension")
    var mDims: String = ""

    @field:SerializedName("residents")
    var mResidents: List<String> = listOf()

}


class LocationResponse {

    @field:SerializedName("results")
    var mLocations: List<LocationModel> = listOf()

}
