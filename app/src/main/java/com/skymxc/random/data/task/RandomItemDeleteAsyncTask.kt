package com.skymxc.random.data.task

import android.os.AsyncTask
import com.skymxc.random.callback.DeleteRandomItemCallback
import com.skymxc.random.callback.SaveRandomItemCallback
import com.skymxc.random.data.RandomDataBase
import com.skymxc.random.entity.ExecuteResult
import com.skymxc.random.entity.RandomItem

/**
 * <p>
 * 删除
 * </p>
 *
 * @author 孟祥超
 * <p>
 * date: 2021/2/2
 */
class RandomItemDeleteAsyncTask(
    private val callback: DeleteRandomItemCallback
) : AsyncTask<RandomItem, Unit, Int>() {
    override fun onPreExecute() {
        super.onPreExecute()
    }
    override fun doInBackground(vararg params: RandomItem?): Int {
        val item = params[0]!!
        val randomDao = RandomDataBase.getDB().getRandomDao()
        return randomDao.delete(item)

    }

    override fun onPostExecute(result: Int) {
        callback.onSuccess(result>0)
    }
}