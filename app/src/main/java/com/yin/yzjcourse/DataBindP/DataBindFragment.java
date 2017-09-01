package com.yin.yzjcourse.DataBindP;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.databinding.ActivityDataBindBinding;
import com.yin.yzjcourse.databinding.FragmentDataBindBinding;

public class DataBindFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_data_bind, null);
        //注意：在fragment中用FragmentDataBindBinding
        FragmentDataBindBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_data_bind,container,false);
        User user = new User("第一", "第二");
        binding.setUser(user);
        return binding.getRoot();
    }
}
