package com.example.rickandmorty.utils

import com.example.rickandmorty.R

object DecodingUtils {

    fun decodeGenderToImage( toDecode: String ): Int {
        return when( toDecode ) {
            "Male" -> R.drawable.male_icon
            "Female" -> R.drawable.female_icon
            else -> R.drawable.female_icon
        }
    }

    fun decodeStatusToImage( toDecode: String ): Int {
        return when( toDecode ) {
            "Dead" -> R.drawable.ic_deadicon
            "Alive" -> R.drawable.ic_alive
            else -> R.drawable.ic_deadicon
        }
    }

}