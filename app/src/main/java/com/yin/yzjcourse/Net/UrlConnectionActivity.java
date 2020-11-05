package com.yin.yzjcourse.Net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UrlConnectionActivity extends BaseActivity {
    //试试我的台式机
    @BindView(R.id.iv_response)
    ImageView iv_response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_connection);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.get_bitmap,R.id.get_string})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_bitmap:
                doGetBitmap();
                break;
            case R.id.get_string:
                doGetString();
                break;
        }
    }

    private void doGetString() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    String strUrl = "http://www.baidu.com";
                    String strUrl = "https://www.cnblogs.com/zhuziyu/p/9167115.html";
                    URL url = new URL(strUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5000);
                    conn.setRequestMethod("GET");
                    if (conn.getResponseCode() == 200) {
                        InputStream in = conn.getInputStream();
                        byte[] data = read(in);
                        String html = new String(data, "UTF-8");
                        DLog.eLog(html);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    DLog.eLog("报错："+e.getMessage());
                }
            }
        }).start();
    }

    private void doGetBitmap() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String imgUrl = "http://d.hiphotos.baidu.com/image/pic/item/b03533fa828ba61e0bd9f7ef4534970a304e593e.jpg";
                    URL url = new URL(imgUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    // 设置连接超时为5秒
                    conn.setConnectTimeout(5000);
                    // 设置请求类型为Get类型
                    conn.setRequestMethod("GET");
                    // 判断请求Url是否成功
                    if (conn.getResponseCode() != 200) {
                        throw new RuntimeException("请求url失败");
                    }
                    InputStream inStream = conn.getInputStream();
                    byte[] bt = read(inStream);
                    inStream.close();
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bt, 0, bt.length);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            iv_response.setImageBitmap(bitmap);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //从流中读取数据(返回字节数组)
    private byte[] read(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len = inStream.read(buffer)) != -1)
        {
            outStream.write(buffer,0,len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}
