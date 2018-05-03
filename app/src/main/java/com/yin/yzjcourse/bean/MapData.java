package com.yin.yzjcourse.bean;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by think on 2018/5/3.
 */

public class MapData {
    public static HashMap<Integer, Integer> coolestRandomNumbers = new HashMap<>();
    static {
        Random randomGenerator = new Random();
        for (int i=0; i<3000; i++) {
            coolestRandomNumbers.put(randomGenerator.nextInt(), i);
        }
    }
}
