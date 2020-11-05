package com.yin.yzjcourse;

import android.content.Context;

import org.junit.Test;

import java.util.Arrays;

import static javax.microedition.khronos.egl.EGL10.EGL_ALPHA_SIZE;
import static javax.microedition.khronos.egl.EGL10.EGL_BLUE_SIZE;
import static javax.microedition.khronos.egl.EGL10.EGL_DEPTH_SIZE;
import static javax.microedition.khronos.egl.EGL10.EGL_GREEN_SIZE;
import static javax.microedition.khronos.egl.EGL10.EGL_NONE;
import static javax.microedition.khronos.egl.EGL10.EGL_RED_SIZE;
import static javax.microedition.khronos.egl.EGL10.EGL_SAMPLES;
import static javax.microedition.khronos.egl.EGL10.EGL_SAMPLE_BUFFERS;
import static javax.microedition.khronos.egl.EGL10.EGL_STENCIL_SIZE;
import static org.junit.Assert.assertEquals;

public class ExampleUnitTest {

    @Test
    public void myTest()  {
        String abc = "dddd";
        System.out.println(abc);

        String my = null;
        System.out.println(abc.equals(my));

        int[] configSpec = new int[]{10,11,12,13,14,15};
        final int len = configSpec.length;
        final int[] newConfigSpec = new int[len + 2];
        System.arraycopy(configSpec, 0, newConfigSpec, 0, len - 1);
        System.out.println("输出："+ Arrays.toString(configSpec)+","+Arrays.toString(newConfigSpec));
    }
}