package com.skymxc.random.data.task

import android.os.AsyncTask
import com.skymxc.random.callback.SaveRandomItemCallback
import com.skymxc.random.data.RandomDataBase
import com.skymxc.random.entity.ExecuteResult
import com.skymxc.random.entity.RandomItem

/**
 * <p>
 * 保存
 * </p>
 *
 * @author 孟祥超
 * <p>
 * date: 2021/2/2
 */
class RandomItemSaveAsyncTask(
    private val callback: SaveRandomItemCallback
) : AsyncTask<RandomItem, Unit, ExecuteResult>() {
    override fun onPreExecute() {
        super.onPreExecute()
    }
    override fun doInBackground(vararg params: RandomItem?): ExecuteResult {
        val item = params[0]!!
        val randomDao = RandomDataBase.getDB().getRandomDao()
        val count =randomDao.getCount(item.name)
        if (count>0){
            return ExecuteResult(-1,"${item.name} 已存在")
        }

        val insert = RandomDataBase.getDB().getRandomDao().insert(item)
        return if (insert>0) ExecuteResult.success() else ExecuteResult(-1,"保存失败")
    }

    override fun onPostExecute(result: ExecuteResult) {
        if (result.isSuccess()){
            callback.onSuccess(true)
        }else{
            callback.onError(result.code,result.msg)
        }
    }
}