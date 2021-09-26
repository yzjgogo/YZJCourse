package com.yin.yzjcourse.tools;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;
import com.yin.yzjcourse.tools.svg.SVG;
import com.yin.yzjcourse.tools.svg.SVGImageView;
import com.yin.yzjcourse.tools.svg.SVGParseException;
import com.yin.yzjcourse.utils.DLog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToolsActivity extends BaseActivity {
    private long mInitialTime;
    @BindView(R.id.iv_svg)
    SVGImageView iv_svg;
    @BindView(R.id.my_iv)
    ImageView my_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_heap_size, R.id.bt_format_ms, R.id.bt_count_down, R.id.bt_timing_schedule,
            R.id.bt_timing_rate, R.id.bt_time_calculate, R.id.bt_crash_catch, R.id.bt_screen
            , R.id.bt_utils, R.id.bt_gener_id, R.id.bt_str, R.id.bt_progress, R.id.bt_drawable
            , R.id.bt_status_nav, R.id.bt_svg_bitmap, R.id.bt_svg_local, R.id.bt_svg_net, R.id.bt_svg_drawable})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_utils:
                Utils.showToast(this, "看看这个类就行了");
                break;
            case R.id.bt_heap_size:
                ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                int heapSize = manager.getMemoryClass();//单位是Mb
                DLog.eLog("分配的内存大小:" + heapSize + "Mb");
                break;
            case R.id.bt_format_ms:
                SimpleDateFormat formatter = new SimpleDateFormat("HH小时mm分");
                formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));//必须设置时区，否则有8个小时的差距，如果不设置时区结果就是11小时17分钟
                String hms = formatter.format(3 * 60 * 60 * 1000 + 17 * 60 * 1000);//3小时17分钟
                DLog.eLog("格式化后：" + hms);
                break;
            case R.id.bt_count_down:
                //第一个参数：一共倒计时多少ms，注意是毫秒
                //第二个参数：每隔多少ms倒计时一次，注意是毫秒
                CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {//参数是剩余多少毫秒，CountDownTimer第一个参数的剩余
                        //每倒计时一次就调用一次，多用于更新UI
                        long s = millisUntilFinished / 1000;
                        DLog.eLog("当前：" + s + "秒");
                        if (s == 50l) {
                            DLog.eLog("去取消");
//                            cancel();
                        }
                    }

                    @Override
                    public void onFinish() {
                        //倒计时结束的回调
                        DLog.eLog("倒计时结束");
                    }
                }.start();//调用start()才能开始倒计时
//                timer.cancel();别忘了用完取消,如果倒计时没完成久取消，不会调用onFinish
                break;
            case R.id.bt_timing_schedule:
/*
1秒后，每隔1秒执行一次
                mInitialTime = SystemClock.elapsedRealtime();
                Timer myTimer = new Timer();
                // Update the elapsed time every second.
                myTimer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        final long newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000;
                        // setValue() cannot be called from a background thread so post to main thread.
//                        mElapsedTime.postValue(newValue);
                        DLog.eLog("时间："+newValue);
                    }
                }, 1000, 1000);
                */


/*
                final Timer myTimer = new Timer();
//                myTimer.scheduleAtFixedRate(new TimerTask() {
                myTimer.schedule(new TimerTask() {//分别注释这行和上面这行试一试效果
                    int count = 1;

                    @Override
                    public void run() {
                        count++;
                        if (count  == 10) {
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                DLog.eLog("延迟5s");
                                e.printStackTrace();
                            }
                        }
                        SimpleDateFormat sf = new SimpleDateFormat(
                                "yyyy MM dd hh:mm:ss");
                        DLog.eLog("当前时间："
                                + sf.format(System.currentTimeMillis()) + "计划时间："
                                + sf.format(scheduledExecutionTime()));
                    }
                }, 1000, 1000);
*/

                /**
                 * 执行的任务的时间小于周期间隔时
                 * schedule()方法
                 * scheduledExecutionTime()程序计划执行的各个人物的时间点和当前真实时间点一致，即System.currentTimeMillis()与scheduledExecutionTime()一样。
                 * 如果开始执行的时间startDate还没过去则效果和scheduleAtFixedRate()一样，等到startDate来临，根据周期间隔period不断执行；
                 * 如果开始执行的时间startDate已经过去，则程序执行的“此时此刻”代替startDate,根据周期间隔period不断执行，
                 * 注意，startDate变成了“此时此刻”，因此下次执行时间也是根据周期间隔period和“此时此刻”往后推算的；
                 * 例如原来startDate是"2019/07/23 14:31:00"，period是25*1000，但是程序执行时已经到了"2019/07/23 14:50:23"，则第一次任务的scheduledExecutionTime
                 * 是"2019/07/23 14:50:23"，第二次任务的scheduledExecutionTime是"2019/07/23 14:50:48"，第三次任务的scheduledExecutionTime是"2019/07/23 14:51:13"。。。依次类推
                 */
                try {
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date startDate = dateFormatter.parse("2019/07/23 14:31:00");
                    long period = 25 * 1000;
                    Timer myschedule = new Timer();
                    myschedule.schedule(new TimerTask() {
                        public void run() {
//                            System.out.println("execute task!" + this.scheduledExecutionTime());
                            DLog.eLog("schedule-当前时间："
                                    + dateFormatter.format(System.currentTimeMillis()) + "计划时间："
                                    + dateFormatter.format(scheduledExecutionTime()));
                        }
                    }, startDate, period);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_timing_rate:
                /**
                 * 执行的任务的时间小于周期间隔时
                 * scheduleAtFixedRate()方法
                 * scheduledExecutionTime()是各个人物计划执行的时间点。
                 * 如果开始执行的时间startDate还没过去则效果和schedule()一样，等到startDate来临，根据周期间隔25*1000不断执行；
                 * 如果开始执行的时间startDate已经过去，则在“此时此刻”scheduleAtFixedRate()会按照原计划各个任务的时间点，先把错过的时间点的任务一次性执行了，之后
                 * 会回到正常的时间点轨道一个任务一个任务的执行。
                 * 例如原来startDate是"2019/07/23 14:31:00"，period是25*1000，但是程序执行时已经到了"2019/07/23 14:32:11"，则在此时一次性执行3个错过的任务：
                 * "2019/07/23 14:32:11" "2019/07/23 14:31:00"
                 * "2019/07/23 14:32:11" "2019/07/23 14:31:25"
                 * "2019/07/23 14:32:11" "2019/07/23 14:31:50"
                 * 然后到了"2019/07/23 14:32:15"  "2019/07/23 14:31:50" 回到正常执行轨道。
                 * "2019/07/23 14:32:40"  "2019/07/23 14:31:40"
                 * "2019/07/23 14:33:05"  "2019/07/23 14:33:05"
                 *                      。
                 *                      。
                 *                      。
                 * 注意上面几组时间，左侧是System.currentTimeMillis()，右侧是scheduledExecutionTime()
                 */
                try {
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date startDate = dateFormatter.parse("2019/07/23 14:46:00");
                    long period = 25 * 1000;
                    Timer mytimer = new Timer();
                    mytimer.scheduleAtFixedRate(new TimerTask() {
                        public void run() {
                            DLog.eLog("FixedRate-当前时间："
                                    + dateFormatter.format(System.currentTimeMillis()) + "计划时间："
                                    + dateFormatter.format(scheduledExecutionTime()));
                        }
                    }, startDate, period);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_time_calculate:
//                Toast.makeText(ToolsActivity.this,"只有两个类，看一下就行了",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_crash_catch:
                Toast.makeText(ToolsActivity.this, "只有两个类，看一下就行了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_screen:
                //在Activity的onCreate()方法中调用下面一行即可
//                ScreenUtils.setCustomDensity(this, getApplication());
                break;
            case R.id.bt_gener_id:
                View myView = new View(this);
                myView.setId(IdiUtils.generateViewId());
                break;
            case R.id.bt_str:
                startActivity(new Intent(this, StrAboutActivity.class));
                break;
            case R.id.bt_progress:
                startActivity(new Intent(this, ProgressActivity.class));
                break;
            case R.id.bt_drawable:
                startActivity(new Intent(this, DrawableActivity.class));
                break;
            case R.id.bt_status_nav:
                startActivity(new Intent(this, StatusHeightActivity.class));
                break;
            case R.id.bt_svg_bitmap:
                svgToBitmap();
                break;
            case R.id.bt_svg_local:
                svgLocalRender();
                break;
            case R.id.bt_svg_net:
                svgNetRender();
                break;
            case R.id.bt_svg_drawable:
                svgPicture();
                break;
        }
    }

/**
 * com.yin.yzjcourse.tools.svg来自于  implementation 'com.caverock:androidsvg-aar:1.4'
 * https://bigbadaboom.github.io/androidsvg/index.html
 *
 * 但是它不支持楷体因此，我把代码copy到com.yin.yzjcourse.tools.svg里SVGAndroidRenderer的checkGenericFont()新增支持微软雅黑和楷体,对应的代码我注释了，可以打开看看，搜索“雅黑”或“楷体”关键字即可
 *
 * 可以用文本工具打开.svg文件看看里面的内容，其实就是一些标签解析
 *
 */

    /**
     * 解析.svg文件，获取bitmap，放大会变模糊
     */
    private void svgToBitmap() {
        try {
// Read an SVG from the assets folder
            SVG svg = SVG.getFromAsset(getAssets(), "abc.svg");

// Create a canvas to draw onto
            if (svg.getDocumentWidth() != -1) {
                Bitmap newBM = Bitmap.createBitmap((int) Math.ceil(svg.getDocumentWidth()),
                        (int) Math.ceil(svg.getDocumentHeight()),
                        Bitmap.Config.ARGB_8888);
                Canvas bmcanvas = new Canvas(newBM);

                // Clear background to white
//                bmcanvas.drawRGB(255, 255, 255);

                // Render our document onto our canvas
                svg.renderToCanvas(bmcanvas);
                iv_svg.setImageBitmap(newBM);
            }
        } catch (SVGParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析本地.svg文件，直接显示矢量图形，放大不变模糊
     */
    private void svgLocalRender() {
        try {
            InputStream inputStream = new FileInputStream(new File("/storage/emulated/0/mjyyfep/audio/pic.svg"));
            SVG svg = SVG.getFromInputStream(inputStream);
            iv_svg.setSVG(svg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SVGParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析网络.svg文件，直接显示矢量图形，放大不变模糊
     */
    private void svgNetRender() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://zhledu.cdn.bcebos.com/ktb/usercourse/cb32e1e7-87c0-4690-a81e-0ff73fd0db21");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    final SVG svg = SVG.getFromInputStream(conn.getInputStream());
                    iv_svg.post(new Runnable() {
                        @Override
                        public void run() {
                            iv_svg.setSVG(svg);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SVGParseException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 将svg转为PictureDrawable再显示
     */
    private void svgPicture() {
        try {
            InputStream inputStream = new FileInputStream(new File("/storage/emulated/0/mjyyfep/audio/pic.svg"));
            SVG svg = SVG.getFromInputStream(inputStream);
            my_iv.setImageDrawable(new PictureDrawable(svg.renderToPicture()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SVGParseException e) {
            e.printStackTrace();
        }
    }
}
