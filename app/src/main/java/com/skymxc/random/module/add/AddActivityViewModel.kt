package com.skymxc.random.module.add

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skymxc.random.callback.SaveRandomItemCallback
import com.skymxc.random.data.RandomRepository
import com.skymxc.random.entity.AsyncResult
import com.skymxc.random.entity.RandomItem
import com.skymxc.random.entity.Status
import java.lang.Exception

/**
 * <p>
 * 增加
 * </p>
 *
 * @author 孟祥超
 * <p>
 * date: 2021/2/2
 */
class AddActivityViewModel(application: Application) : AndroidViewModel(application) {

    val name = ObservableField<String>()

    private val _saveLiveData = MutableLiveData<AsyncResult<Boolean>>()
    val saveLiveData: LiveData<AsyncResult<Boolean>> get() = _saveLiveData
    fun save() {
        Log.e(AddActivityViewModel::class.simpleName, "save: ${name.get()}" )
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
}