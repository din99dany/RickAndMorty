package com.example.rickandmorty.retrofitMihaiModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "episodes")
class MihaiEpisodeModel {

    @PrimaryKey
    @ColumnInfo(name="id")
    @field:SerializedName("id")
    var id = 0

    @ColumnInfo(name="name")
    @field:SerializedName("name")
    var name = ""

    @ColumnInfo(name="episode")
    @field:SerializedName("episode_number")
    var episode = ""

    @ColumnInfo(name="date")
    @field:SerializedName("air_date")
    var date = ""

    @ColumnInfo(name ="page")
    var page: Int = 0

}


class MihaiEpisodeModelDetailed {

    @field:SerializedName("id")
    var id = 0

    @field:SerializedName("name")
    var name = ""

    @field:SerializedName("episode_number")
    var episode = ""

    @field:SerializedName("air_date")
    var date = ""

    @field:SerializedName("synopsis")
    var synopsis: String = ""

    @field:SerializedName("characters")
    var characters: List<MihaiUltraLowCharacter>  = listOf()
}