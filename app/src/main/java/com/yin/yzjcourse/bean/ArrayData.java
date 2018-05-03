package com.yin.yzjcourse.bean;

import java.util.Random;

/**
 * Created by think on 2018/5/3.
 */

public class ArrayData {
    public static Integer[] coolestRandomNumbers = new Integer[3000];
    static {
        Random randomGenerator = new Random();
        for (int i=0; i<3000; i++) {
            coolestRandomNumbers[i] = randomGenerator.nextInt();
        }
    }
}
