package com.yin.yzjcourse.OfficialWeight;

import android.widget.Filter;

import com.yin.yzjcourse.bean.Dog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2017/9/7.
 */

public class DogsFilter1 extends Filter {
    AutoCompleteDogsAdapter adapter;
    List<Dog> originalList;
    List<Dog> filteredList;
    public DogsFilter1(AutoCompleteDogsAdapter adapter, List<Dog> originalList) {
        super();
        this.adapter = adapter;
        this.originalList = originalList;
        this.filteredList = new ArrayList<>();
    }
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filteredList.clear();
        final FilterResults results = new FilterResults();

//        if (constraint == null || constraint.length() == 0) {
            filteredList.addAll(originalList);
//        } else {
//            final String filterPattern = constraint.toString().toLowerCase().trim();
//
//            // Your filtering logic goes in here
//            for (final Dog dog : originalList) {
//                if (dog.breed.toLowerCase().contains(filterPattern)) {
//                    filteredList.add(dog);
//                }
//            }
//        }
        results.values = filteredList;
        results.count = filteredList.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
//        adapter.filteredDogs.clear();
//        adapter.filteredDogs.addAll((List) results.values);
//        adapter.notifyDataSetChanged();
    }
}
