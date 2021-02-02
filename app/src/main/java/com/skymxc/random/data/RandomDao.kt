package com.skymxc.random.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
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
@Dao
interface RandomDao {

    @Query("select * from randomitem order by id ")
    fun all(): LiveData<List<RandomItem>>

    @Insert()
    fun insert(item: RandomItem): Long

    @Delete
    fun delete(item: RandomItem): Int

    @Query("select count(name) from randomitem where name like :name")
    fun getCount(name: String): Long

}