package com.yin.yzjcourse.MaterialDesign;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 只要通知栏列表有足够的控件，扩展的通知栏都会自动被显示，否则就会被压缩
 * 扩展的通知栏只在Jelly Bean 4.1以上可用，老版本只有普通的通知栏可以显示
 * 可扩展的通知栏是要用两个手指收缩展开的
 *
 * 未讲到的内容：
 1：普通的通知栏各种方法

 2：通知栏上的操作事件
  setContentIntent()：用户点击通知时触发
 setFullScreenIntent()：//TODO 这个在通知显示的时候会被调用
 setDeleteIntent()：用户清除通知时触发，可以是点击清除按钮，也可以是左右滑动删除(当然了，前提是高版本)
 2.3及以下是无法处理自定义布局中的操作事件的，这样我们就不要去考虑增加自定义按钮了。

 3：自定义布局
 使用NotificationCampat来做自定义布局我们可以这样做：
 RemoteViews rvMain = new RemoteViews(context.getPackageName(), R.layout.notification_layout);
 //TODO rvMain...
 NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
 .setContent(rvMain);
 // TOOD ...


 在4.0以上，这样是没问题，但是在2.3的时候，你会发现这样根本无效，因此我们需要换一种方式：
 RemoteViews rvMain = new RemoteViews(context.getPackageName(), R.layout.notification_layout);
 //TODO rmMain...
 NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
 .setContent(rvMain);
 // TOOD ...
 Notification notification = builder.build();
 if(Build.VERSION.SDK_INT <= 10){
 notification.contentView = rvMain;
 }
 */
public class NotificationsActivity extends BaseActivity {
    private String bigText = "酸辣粉就两块时间发了控件时发送的经方考虑到收到了放假了快递商家收到的垃圾分类快递商家说得多了放假了的时间发" +
            "说得多了粉剂来点实际酸辣粉就两块时代峻峰收到了放假了时点击设计到的辅料可机都是六块腹肌了但是会计法考虑到时间发离开家收到了看" +
            "收到了开房记录可视对讲离开家思路肯定经济法颗粒但是积分离开家里事亏大发了可视对讲离开家施蒂利克就两块放大镜老实交代了记录时点击了时点" +
            "击了建档立卡见识到了就两块的间距历史地理施蒂利克家里事的老师的了快递费了但是了家里的事就了肯定是了解的是了凯迪拉克了当升科技了肯定是就两" +
            "块发生了客服哈控件时待恢复嘎哈个看到回时打开罚款咖喱火锅开会老手机了科技时代收到的经方离开家世纪东方离开家收到的积分离开接收到的龙卷风了" +
            "老司机辅料可聚少离多开房记录时打开就时代峻峰了建设路口动家法时点击发链接收拾房间拉克丝时间发第六空间少的地方就两块设定计量粉剂随访就离开" +
            "上了飞机少的地方就拉克丝的飞机立刻说服力可就时间管理考试大纲可拉伸了国航螯合钙哈利看到该考拉弟弟管理科梳理开会膏方拉黑了死神镰刀菲利克斯";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_show_large_text)
    public void showLargeText() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        final NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle(builder);
        bigTextStyle.setBigContentTitle("我是大标题")
                .bigText(bigText)
                .setSummaryText("附加的概要");

        Notification notification = builder
                .setContentTitle("Title")//如果用了BigTextStyle.setBigContentTitle则该方法不起作用
                .setContentText("This is a notification!")//如果用了BigTextStyle.bigText则该方法不起作用
                .setSubText("哈哈哈")//如果用了BigTextStyle.setSummaryText则该方法不起作用
                .setSmallIcon(R.mipmap.mingyi)//必须调用setSmallIcon才显示通知栏
                .build();
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        notificationManager.notify(0x1234, notification);
    }

    /**
     * 用于通知栏显示大图，图片的高度限制为256dp，切图片将会以ScaleType.CROP_CENTER的样式显示
     */
    @OnClick(R.id.bt_show_big_pic)
    public void showBigPic() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle(builder);
        final Bitmap picture = BitmapFactory.decodeResource(getResources(), R.mipmap.yzj_oang);
        final Bitmap largeExpandedAvatar = BitmapFactory.decodeResource(getResources(), R.mipmap.yzj_gua);

        bigPictureStyle.bigPicture(picture)
                .bigLargeIcon(largeExpandedAvatar)
                .setBigContentTitle("Expanded title")
                .setSummaryText("Summary text");

        Notification notification = builder
//                .setContentTitle("Title")//如果用了BigTextStyle.setBigContentTitle则该方法不起作用
//                .setContentText("This is a notification!")//如果用了BigTextStyle.bigText则该方法不起作用
//                .setSubText("哈哈哈")//如果用了BigTextStyle.setSummaryText则该方法不起作用
                .setSmallIcon(R.mipmap.mingyi)//必须调用setSmallIcon才显示通知栏
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(9527, notification);
    }

    /**
     * 显示多行文本，最多显示7行，其余的以省略号表示
     * 官方指导建议强调，每一个app应该无论什么时候都最多只有一个通知栏，这样才能保证通知栏列表的清洁。
     * 用InboxStyle可以实现往一个通知栏中展示多个通知，从而保证即显示历史通知也显示最新通知
     */
    @OnClick(R.id.bt_show_lines)
    public void showLines() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle(builder);
        inboxStyle.addLine("This is line #" + 1)
                .addLine("This is line #" + 2)
                .addLine("This is line #" + 3)
                .addLine("This is line #" + 4)
                .addLine("This is line #" + 5)
                .addLine("This is line #" + 6)
                .addLine("This is line #" + 7)
                .addLine("This is line #" + 8)
                .setBigContentTitle("Expanded title")
                .setSummaryText("Summary text");
        Notification notification = builder
                .setSmallIcon(R.mipmap.mingyi)//必须调用setSmallIcon才显示通知栏
                .build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(9528, notification);
    }
}
