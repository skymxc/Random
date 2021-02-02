package com.skymxc.random.observer

import androidx.lifecycle.Observer
import com.skymxc.random.entity.AsyncResult
import com.skymxc.random.entity.Status
import com.skymxc.random.module.base.BaseActivity
import java.lang.Exception

/**
 * <p>
 *
 * </p>
 *
 * @author 孟祥超
 * <p>
 * date: 2021/2/2
 */
abstract class AbstractAsyncResultObserver<T>:Observer<AsyncResult<T>> {
    override fun onChanged(t: AsyncResult<T>?) {
        t?.let {
            when(it.status){
                Status.START ->{ onStart()}
                Status.ERROR ->{onError(it.exception!!)}
                Status.SUCCESS ->{onSuccess(it.value!!)}
            }
        }
    }
    abstract fun onSuccess(value:T)
    abstract fun onError(exception: Exception)
    abstract fun onStart()
}

abstract class SimpleAsyncResultObserver<T>(
    private val activity:BaseActivity
): AbstractAsyncResultObserver<T>() {
    override fun onStart() {
        activity.showProgress()
    }

    override fun onError(exception: Exception) {
        activity.showError(exception)
    }

}