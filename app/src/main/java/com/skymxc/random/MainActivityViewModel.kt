package com.skymxc.random

import android.app.Application
import android.util.Log
import androidx.arch.core.util.Function
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.skymxc.random.callback.DeleteRandomItemCallback
import com.skymxc.random.callback.SaveRandomItemCallback
import com.skymxc.random.data.RandomRepository
import com.skymxc.random.data.task.RandomItemDeleteAsyncTask
import com.skymxc.random.entity.AsyncResult
import com.skymxc.random.entity.RandomItem
import com.skymxc.random.entity.Status
import com.skymxc.random.module.add.AddActivityViewModel
import java.lang.Exception

/**
 * <p>
 *
 * </p>
 *
 * @author 孟祥超
 * <p>
 * date: 2021/2/1
 */
class MainActivityViewModel(application: Application) : AndroidViewModel(application) {


    val listLiveData: LiveData<List<RandomItem>> = Transformations.map(
        RandomRepository.all()
    ) { input ->
        input?.let {
            list = it
            list
        } ?: emptyList()
    }


    var list: List<RandomItem> = emptyList()

    val name = ObservableField<String>()

    private val _saveLiveData = MutableLiveData<AsyncResult<Boolean>>()
    val addLiveData: LiveData<AsyncResult<Boolean>> get() = _saveLiveData
    fun add() {
        Log.e(MainActivityViewModel::class.simpleName, "save: ${name.get()}")
        val temp = name.get() ?: return
        val item = RandomItem(name = temp)
        _saveLiveData.value = AsyncResult(Status.START)
        RandomRepository.save(item,
            object : SaveRandomItemCallback {
                override fun onSuccess(value: Boolean) {
                    _saveLiveData.value = AsyncResult(value)
                }

                override fun onError(code: Int, msg: String) {
                    _saveLiveData.value = AsyncResult(Exception(msg))
                }
            })
    }

    private val _delLiveData = MutableLiveData<AsyncResult<Boolean>>()
    val delLiveData: LiveData<AsyncResult<Boolean>> get() = _delLiveData
    fun delete(item: RandomItem) {
        _delLiveData.value = AsyncResult(Status.START)
        RandomRepository.delete(item,
            object : DeleteRandomItemCallback {
                override fun onSuccess(value: Boolean) {
                    _delLiveData.value = AsyncResult(value)
                }

                override fun onError(code: Int, msg: String) {
                    _delLiveData.value = AsyncResult(Exception(msg))
                }
            })
    }

    val randomResult = ObservableField<String>()

    fun random() {
        if (list.isEmpty() || list.size == 1) return
        val index = (list.indices).random()
        randomResult.set(list[index].name)


    }
}