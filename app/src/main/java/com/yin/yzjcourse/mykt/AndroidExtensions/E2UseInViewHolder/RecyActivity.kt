package com.yin.yzjcourse.mykt.AndroidExtensions.E2UseInViewHolder

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.yin.yzjcourse.BaseActivity
import com.yin.yzjcourse.R
import kotlinx.android.synthetic.main.activity_holder.*

class RecyActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_holder)
        initView()
    }

    private fun initView() {
        val  llManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerView.layoutManager = llManager
        val adapter = FruitAdapter(getFruitList())
        recyclerView.adapter = adapter
    }
    private fun getFruitList():List<Fruit>{
        val list = arrayListOf<Fruit>()
        for(i in 0..10){
            list.add(Fruit("名字$i", R.mipmap.ic_launcher))
        }
        return list
    }
}
