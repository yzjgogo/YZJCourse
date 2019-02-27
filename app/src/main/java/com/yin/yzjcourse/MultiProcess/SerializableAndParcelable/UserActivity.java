package com.yin.yzjcourse.MultiProcess.SerializableAndParcelable;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Serializable的用法参考:com.yin.yzjcourse.MultiProcess.SerializableAndParcelable.SerUser
 * Student的用法参考：com.yin.yzjcourse.MultiProcess.SerializableAndParcelable.Student
 */
public class UserActivity extends BaseActivity {

    public static final String CHAPTER_2_PATH = Environment
            .getExternalStorageDirectory().getPath()
            + "/singwhatiwanna/chapter_2/";

    public static final String CACHE_FILE_PATH = CHAPTER_2_PATH + "usercache";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.bt_ser, R.id.bt_ser_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_ser:
                persistToFile();
                break;
            case R.id.bt_ser_back:
                recoverFromFile();
                break;
        }
    }

    /**
     * 序列化的过程
     */
    private void persistToFile() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                SerUser user = new SerUser(1, "hello world", false);
                File dir = new File(CHAPTER_2_PATH);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File cachedFile = new File(CACHE_FILE_PATH);
                ObjectOutputStream objectOutputStream = null;
                try {
                    objectOutputStream = new ObjectOutputStream(
                            new FileOutputStream(cachedFile));
                    objectOutputStream.writeObject(user);
                    DLog.eLog( "序列化用户 :" + user);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    close(objectOutputStream);
                }
            }
        }).start();
    }

    /**
     * 反序列化的过程
     */

    private void recoverFromFile() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                SerUser user = null;
                File cachedFile = new File(CACHE_FILE_PATH);
                if (cachedFile.exists()) {
                    ObjectInputStream objectInputStream = null;
                    try {
                        objectInputStream = new ObjectInputStream(
                                new FileInputStream(cachedFile));
                        user = (SerUser) objectInputStream.readObject();
                        DLog.eLog("恢复用户:" + user);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        close(objectInputStream);
                    }
                }
            }
        }).start();
    }

    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
