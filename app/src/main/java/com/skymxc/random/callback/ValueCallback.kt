package com.skymxc.random.callback

/**
 * <p>
 *
 * </p>
 *
 * @author 孟祥超
 * <p>
 * date: 2021/2/2
 */
interface ValueCallback<T> {
    fun onSuccess(value:T)
    fun onError(code:Int,msg:String)
}