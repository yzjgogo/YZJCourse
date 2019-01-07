package com.yin.yzjcourse.mykt.AndroidExtensions.E2UseInViewHolder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.yin.yzjcourse.R

class FruitOriginalAdapter(val fruitList: List<Fruit>) : RecyclerView.Adapter<FruitOriginalAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.tvName.text = fruit.name
        holder.ivImage.setImageResource(fruit.imageId)

    }

    /**
     * 这是没有使用kotlin-android-extensions的ViewHolder的实现方式，需要自己通过findViewById()获取控件。
     * 使用了kotlin-android-extensions的参考：[com.yin.yzjcourse.mykt.AndroidExtensions.E2UseInViewHolder.FruitAdapter.ViewHolder]
     */
    class ViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
        val tvName = containerView.findViewById<TextView>(R.id.tvName)
        val ivImage = containerView.findViewById<ImageView>(R.id.ivImage)
    }
}