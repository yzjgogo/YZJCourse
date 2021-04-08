package com.yin.yzjcourse.mykt.AndroidExtensions.E2UseInViewHolder

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yin.yzjcourse.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fruit_item.*

class FruitAdapter(val fruitList: List<Fruit>) : androidx.recyclerview.widget.RecyclerView.Adapter<FruitAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
//        holder.tvName.text = fruit.name
//        holder.ivImage.setImageResource(fruit.imageId)
        holder.setName(fruit.name)
        holder.setImage(fruit.imageId)

    }

    /**
     * kotlin-android-extensions
     * 也可用于ViewHolder,不用通过findViewById()即可获取控件，只需让ViewHolder实现LayoutContainer接口即可，该接口只定义了一个抽象属性。
     * 因为kotlin-android-extensions用于ViewHolder还处于实验阶段，所以需要在配置文件声明一下，
     * 参考app的gradle的配置文件：[org.gradle.api.Project#androidExtensions]
     *
     * 注意顶部导入了：import kotlinx.android.synthetic.main.fruit_item.*
     *
     * 通过findViewById()获取控件的原始方式参考:[com.yin.yzjcourse.mykt.AndroidExtensions.E2UseInViewHolder.FruitOriginalAdapter.ViewHolder]
     */
    class ViewHolder(override val containerView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(containerView), LayoutContainer {
        //        val tvName = view.findViewById<TextView>(R.id.tvName)
//        val ivImage = view.findViewById<ImageView>(R.id.ivImage)
        fun setName(name:String) {
            tvName.text = name
        }
        fun setImage(iv:Int){
            ivImage.setImageResource(iv)
        }
    }
}