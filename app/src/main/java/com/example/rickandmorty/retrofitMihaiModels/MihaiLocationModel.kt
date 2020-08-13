package com.example.rickandmorty.retrofitMihaiModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "location")
class MihaiLocationModel {

    @PrimaryKey
    @ColumnInfo(name="id")
    @field:SerializedName("id")
    var id: Int = 0

    @ColumnInfo(name="name")
    @field:SerializedName("name")
    var name: String = ""

    @ColumnInfo(name="dimension")
    @field:SerializedName("dimension")
    var dimension: String = ""

    @ColumnInfo(name="type")
    @field:SerializedName("type")
    var type: String = ""

    @ColumnInfo(name ="page")
    var page: Int = 0

}

class MihaiLocationModelDetailed {

    @field:SerializedName("id")
    var id: Int = 0

    @field:SerializedName("name")
    var name: String = ""

    @field:SerializedName("dimension")
    var dimension: String = ""

    @field:SerializedName("type")
    var type: String = ""

    @field:SerializedName("residents")
    var residents: List<MihaiUltraLowCharacter> = listOf()

}