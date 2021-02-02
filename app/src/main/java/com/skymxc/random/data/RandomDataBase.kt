package com.skymxc.random.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.skymxc.random.APP
import com.skymxc.random.entity.RandomItem

/**
 * <p>
 *
 * </p>
 *
 * @author 孟祥超
 * <p>
 * date: 2021/2/1
 */
@Database(entities = [RandomItem::class], version = 1)
abstract class RandomDataBase : RoomDatabase() {
    companion object {
        private val _db:RandomDataBase by lazy {
            Room.databaseBuilder(
                APP.applicationContext,
                RandomDataBase::class.java,
                "random.db"
            ).build()
        }
        fun getDB():RandomDataBase = _db

    }

    abstract fun getRandomDao(): RandomDao
}

