package com.skymxc.random.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skymxc.random.R
import com.skymxc.random.callback.ItemDeleteClickCallback
import com.skymxc.random.entity.RandomItem
import com.skymxc.random.generated.callback.OnClickListener

/**
 * <p>
 *
 * </p>
 *
 * @author 孟祥超
 * <p>
 * date: 2021/2/2
 */
class RandomItemAdapter(
    val list:List<RandomItem>,
    val context:Context,
    val deleteClickCallback: ItemDeleteClickCallback?=null)
    :RecyclerView.Adapter<RandomItemViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomItemViewHolder {
        val inflate =
            LayoutInflater.from(context).inflate(R.layout.recycler_item_random, parent, false)
        return RandomItemViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: RandomItemViewHolder, position: Int) {
        val item =list[position]
        holder.tvDelete.tag = item
        holder.tvDelete.setOnClickListener(deleteClick)
        holder.tvName.text = item.name
    }

    override fun getItemCount(): Int {
        return list.size
    }

    val deleteClick = View.OnClickListener{
        val item = it.tag as? RandomItem ?:return@OnClickListener
        deleteClickCallback?.onDelete(item)
    }
}

class RandomItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvName:TextView = itemView.findViewById(R.id.tv_item_name)
    val tvDelete :TextView = itemView.findViewById(R.id.tv_item_delete)
}