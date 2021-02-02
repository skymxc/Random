package com.skymxc.random.callback

import com.skymxc.random.entity.RandomItem

/**
 * <p>
 *
 * </p>
 *
 * @author 孟祥超
 * <p>
 * date: 2021/2/2
 */
fun interface ItemDeleteClickCallback {
    fun onDelete(item:RandomItem)
}