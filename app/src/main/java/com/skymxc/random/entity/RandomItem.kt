package com.skymxc.random.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * <p>
 * 随机项
 * </p>
 *
 * @author 孟祥超
 * <p>
 * date: 2021/2/1
 */
@Entity()
data class RandomItem(
    //id
    @PrimaryKey(autoGenerate = true)
    var id: Long=0,
    @ColumnInfo(name="name") var name: String, //名字
)