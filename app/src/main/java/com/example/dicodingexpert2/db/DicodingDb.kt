package com.example.dicodingexpert2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dicodingexpert2.db.dao.FavoriteDao
import com.example.dicodingexpert2.db.dao.FavoriteTeamDao
import com.example.dicodingexpert2.db.entity.Favorite
import com.example.dicodingexpert2.db.entity.FavoriteTeam

@Database (entities = arrayOf(Favorite::class, FavoriteTeam::class), version = 1)
abstract class DicodingDb : RoomDatabase() {

    abstract fun favoriteDao() : FavoriteDao

    abstract fun favoriteTeamDao() : FavoriteTeamDao

    companion object {

        private var INSTANCE: DicodingDb?= null

        fun getInstance(context: Context): DicodingDb? {
            if (INSTANCE == null) {
                synchronized(DicodingDb::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        DicodingDb::class.java, "dicodingdb.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}