package com.skymxc.random.entity

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
data class AsyncResult<T>(
    val status: Status,
    val value: T?=null,
    val exception: Exception?=null
) {
    constructor(value: T) : this(Status.SUCCESS, value, null)
    constructor(exception: Exception) : this(Status.ERROR, null, exception)
}