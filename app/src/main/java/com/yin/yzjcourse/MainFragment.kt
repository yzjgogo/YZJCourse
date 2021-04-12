package com.yin.yzjcourse

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import butterknife.ButterKnife
import butterknife.OnClick
import com.yin.yzjcourse.Jetpack.MyLCService
import com.yin.yzjcourse.utils.DLog

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val mView = inflater.inflate(R.layout.fragment_main, container, false)

        //跳转方式1
        mView.findViewById<View>(R.id.bt_to_second_1).setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_secondFragment)
        }

        //跳转方式2
        //这里才是createNavigateOnClickListener的正确用法
        mView.findViewById<View>(R.id.bt_to_second_2).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_secondFragment))
        //下面对createNavigateOnClickListener的使用时错误的
        mView.findViewById<View>(R.id.bt_to_second_2).setOnClickListener{
            //这样做是无效的，因为createNavigateOnClickListener的作用是返回一个OnClickListener，并不是触发跳转，真正要触发跳转还需要这个OnClickListener得到执行才可以
            Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_secondFragment,null)
        }
        return mView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MainFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}