package com.example.rickandmorty.retrofitMihaiModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity( tableName = "character")
class MihaiCharacterModel {

    @PrimaryKey
    @ColumnInfo(name="id")
    @field:SerializedName("id")
    var id: Int = 0

    @ColumnInfo(name="name")
    @field:SerializedName("name")
    var name: String = ""

    @ColumnInfo(name="thumbnail")
    @field:SerializedName("image")
    var thumbnail: String = ""

    @ColumnInfo(name="species")
    @field:SerializedName("species")
    var species: String = ""

    @ColumnInfo(name="gender")
    @field:SerializedName("gender")
    var gender: String = ""

    @ColumnInfo(name="status")
    @field:SerializedName("status")
    var status: String = ""

    @ColumnInfo(name ="page")
    var page: Int = 0

}


class MihaiCharacterModelDetailed {

    @field:SerializedName("id")
    var id: Int = 0

    @field:SerializedName("image")
    var thumbnail: String = ""

    @field:SerializedName("name")
    var name: String = ""

    @field:SerializedName("species")
    var species: String = ""

    @field:SerializedName("gender")
    var gender: String = ""

    @field:SerializedName("status")
    var status: String = ""


    @field:SerializedName("episodes")
    var episodes: List<MihaiCharacterModelEpisodes> = listOf()

    @field:SerializedName("location")
    var location: MihaiCharacterModelLocation = MihaiCharacterModelLocation()


}

class MihaiCharacterModelLocation {

    @field:SerializedName("id")
    var id: Int = 0

    @field:SerializedName("name")
    var name: String = ""

    @field:SerializedName("dimension")
    var dimenstion: String = ""

}

class MihaiCharacterModelEpisodes {

    @field:SerializedName("id")
    var id: Int = 0

    @field:SerializedName("name")
    var name: String = ""

    @field:SerializedName("episode_number")
    var episodesNumber: String = ""

}
