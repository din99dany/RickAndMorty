package com.example.rickandmorty.roommodels

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmorty.retrofitMihaiModels.MihaiCharacterModel
import com.example.rickandmorty.retrofitMihaiModels.MihaiEpisodeModel
import com.example.rickandmorty.retrofitMihaiModels.MihaiLocationModel

@Database( entities = arrayOf(MihaiCharacterModel::class, MihaiEpisodeModel::class, MihaiLocationModel::class),
    exportSchema = false,
    version = 4 )
abstract class AppDatabase: RoomDatabase() {
    abstract fun characterDao() : RoomDAO

    companion object{
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context:Context): AppDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "roomdb")
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return INSTANCE as AppDatabase
        }
    }

}