package com.yin.yzjcourse.OfficialWeight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.bean.Dog;
import com.yin.yzjcourse.utils.DLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by think on 2017/9/7.
 */

public class AutoCompleteDogsAdapter extends ArrayAdapter<Dog> {
    private final List<Dog> dogs;
    private List<Dog> filteredDogs = new ArrayList<>();

    public AutoCompleteDogsAdapter(Context context, List<Dog> dogs) {
        super(context, 0, dogs);
        this.dogs = dogs;
    }
    @Override
    public int getCount() {
        return filteredDogs.size();
    }

    @Override
    public Filter getFilter() {
        return new DogsFilter(this, dogs);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.auto_complete_item, parent, false);
        Dog dog = filteredDogs.get(position);
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_item);
        tvName.setText(dog.getName()+","+dog.getSex()+","+dog.getAge());
        return convertView;
    }






    class DogsFilter extends Filter {
        AutoCompleteDogsAdapter adapter;
        List<Dog> originalList;
        List<Dog> filteredList;
        public DogsFilter(AutoCompleteDogsAdapter adapter, List<Dog> originalList) {
            super();
            this.adapter = adapter;
            this.originalList = originalList;
            this.filteredList = new ArrayList<>();
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            DLog.eLog("约束："+constraint.toString());
            filteredList.clear();
            final FilterResults results = new FilterResults();

            filteredList.addAll(originalList);
//            if (constraint == null || constraint.length() == 0) {
//                filteredList.addAll(originalList);
//            } else {
//                final String filterPattern = constraint.toString().toLowerCase().trim();
//
//                // Your filtering logic goes in here
//                for (final Dog dog : originalList) {
//                    if (dog.breed.toLowerCase().contains(filterPattern)) {
//                        filteredList.add(dog);
//                    }
//                }
//            }
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Dog dog = (Dog) resultValue;
            return dog.getName()+","+dog.getSex()+","+dog.getAge();
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.filteredDogs.clear();
//            List<Dog> resultList = (List<Dog>) results.values;

            adapter.filteredDogs.addAll((List) results.values);
            adapter.notifyDataSetChanged();
        }

    }



}
