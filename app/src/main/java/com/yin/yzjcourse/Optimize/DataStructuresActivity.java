package com.yin.yzjcourse.Optimize;

import android.os.Trace;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.bean.ArrayData;
import com.yin.yzjcourse.bean.MapData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DataStructuresActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_structures);

        Button dumpCountriesButton = (Button) findViewById(R.id.bt_array);

        dumpCountriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dumpPopularRandomNumbersByRankWithArray();
            }
        });
        findViewById(R.id.bt_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dumpPopularRandomNumbersByRankWithMap();
            }
        });

        // It's much easier to see how your decisions affect framerate when there's something
        // changing on screen.  For entirely serious, educational purposes, a dancing pirate
        // will be included with this exercise.
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadUrl("file:///android_asset/shiver_me_timbers.gif");
    }

    /**
     * 虽然也是官方提供的数据结构但是不适合用在这里，用在这里效率就很底
     * Using the pre-formed array of random numbers ordered by popularity, prints out an ordered
     * list of the random number + rank in the form "(RandomNumber): #(Rank)".
     */
    public void dumpPopularRandomNumbersByRankWithArray() {
//        Trace.beginSection("Data Structures");
        // First we need a sorted list of the numbers to iterate through.
        Integer[] sortedNumbers = ArrayData.coolestRandomNumbers.clone();
        Arrays.sort(sortedNumbers);

        // Great!  Now because we have no rank lookup in the population-sorted array,
        // take the random number in sorted order, and find its index in the array
        // that's sorted by popularity.  The index is the rank, so report that.  Easy and efficient!
        // Except that it's... you know... It's not.
        for (int i = 0; i < sortedNumbers.length; i++) {
            Integer currentNumber = sortedNumbers[i];
            for (int j = 0; j < ArrayData.coolestRandomNumbers.length; j++) {
                if (currentNumber.compareTo(ArrayData.coolestRandomNumbers[j]) == 0) {
                    Log.e("yin", currentNumber + ": #" + j);
                }
            }
        }
//        Trace.endSection();
    }

    /**
        通过Map存取效率更高
     */
    private void dumpPopularRandomNumbersByRankWithMap() {
        Trace.beginSection("myData1");
        // Make a copy so that we don't accidentally shatter our data structure.
        Map<Integer, Integer> rankedNumbers = new HashMap<>();
        rankedNumbers.putAll(MapData.coolestRandomNumbers);
        // Then, we need a sorted version of the numbers to iterate through.
        Integer[] sortedNumbers = {};
        sortedNumbers = rankedNumbers.keySet().toArray(sortedNumbers);
        Arrays.sort(sortedNumbers);

        Integer number;
        for (int i = 0; i < sortedNumbers.length; i++) {
            number = sortedNumbers[i];
            Log.i("Popularity Dump", number + ": #" + rankedNumbers.get(number));
        }
        Trace.endSection();
    }
}
