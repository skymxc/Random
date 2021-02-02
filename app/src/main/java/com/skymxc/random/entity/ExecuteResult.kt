package com.skymxc.random.entity

/**
 * <p>
 *
 * </p>
 *
 * @author 孟祥超
 * <p>
 * date: 2021/2/2
 */
data class ExecuteResult(
    val code:Int,
    val msg:String
) {
    companion object {
        fun success():ExecuteResult = ExecuteResult(0,"success")
    }
    fun isSuccess():Boolean = code==0
}