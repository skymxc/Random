package com.skymxc.random.data

import androidx.lifecycle.LiveData
import com.skymxc.random.callback.DeleteRandomItemCallback
import com.skymxc.random.callback.SaveRandomItemCallback
import com.skymxc.random.data.task.RandomItemDeleteAsyncTask
import com.skymxc.random.data.task.RandomItemSaveAsyncTask
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
object RandomRepository {

    fun all():LiveData<List<RandomItem>>{
        return RandomDataBase.getDB().getRandomDao().all()
    }

    fun save(randomItem: RandomItem,callback: SaveRandomItemCallback){
        RandomItemSaveAsyncTask(callback).execute(randomItem)
    }
    fun delete(randomItem: RandomItem,callback: DeleteRandomItemCallback){
        RandomItemDeleteAsyncTask(callback).execute(randomItem)
    }
}