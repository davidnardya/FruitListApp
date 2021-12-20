package com.davidnardya.fruitlistapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.davidnardya.fruitlistapp.R
import com.davidnardya.fruitlistapp.dao.FruitDao
import com.davidnardya.fruitlistapp.model.FruitItem

@Database(entities = [FruitItem::class], version = 3, exportSchema = false)
abstract class FruitDataBase: RoomDatabase() {

    abstract fun fruitDao(): FruitDao

    companion object{
        @Volatile
        private var INSTANCE: FruitDataBase? = null

        fun getDataBase(context: Context): FruitDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FruitDataBase::class.java,
                    context.getString(R.string.fruit_database)
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}