package com.example.roomviewmodel.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    version = 1,
    entities = [User::class]
)
abstract class Userdatabase: RoomDatabase() {
    abstract fun userDao(): Userdao

    companion object{
        @Volatile
        private var INSTANSE: Userdatabase? = null
        fun getDatabase(context: Context): Userdatabase {
            val tempInstance = INSTANSE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Userdatabase::class.java,
                    "user_database.db"
                ).build()
                INSTANSE = instance
                return instance
            }
        }
    }
}