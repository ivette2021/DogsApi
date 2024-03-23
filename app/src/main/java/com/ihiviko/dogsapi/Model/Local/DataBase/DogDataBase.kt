package com.ihiviko.dogsapi.Model.Local.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ihiviko.dogsapi.Model.Local.DogDao
import com.ihiviko.dogsapi.Model.Local.Entities.DogDetailEntity
import com.ihiviko.dogsapi.Model.Local.Entities.DogEntity

@Database(entities = [DogEntity::class, DogDetailEntity::class], version = 1, exportSchema = false)  //colocamos las dos entidades que definimos anteriormente
abstract class DogDataBase : RoomDatabase() {
    abstract fun dogDao(): DogDao
    companion object {

        @Volatile
        private var INSTANCE: DogDataBase? = null

        fun getDatabase(context: Context): DogDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DogDataBase::class.java,
                    "Dogs_Api"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

