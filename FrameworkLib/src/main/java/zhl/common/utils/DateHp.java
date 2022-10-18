package zhl.common.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateHp {
    public static String getWeekName(Date date) {
        if (date == null)
            return "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_WEEK);

        if (day == Calendar.SATURDAY)
            return "周六";
        else if (day == Calendar.SUNDAY)
            return "周日";
        else if (day == Calendar.FRIDAY)
            return "周五";
        else if (day == Calendar.MONDAY)
            return "周一";
        else if (day == Calendar.THURSDAY)
            return "周四";
        else if (day == Calendar.TUESDAY)
            return "周二";
        else if (day == Calendar.WEDNESDAY)
            return "周三";
        return "";
    }

    public static String getWeekNameLong(Date date) {
        if (date == null)
            return "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_WEEK);

        if (day == Calendar.SATURDAY)
            return "星期六";
        else if (day == Calendar.SUNDAY)
            return "星期日";
        else if (day == Calendar.FRIDAY)
            return "星期五";
        else if (day == Calendar.MONDAY)
            return "星期一";
        else if (day == Calendar.THURSDAY)
            return "星期四";
        else if (day == Calendar.TUESDAY)
            return "星期二";
        else if (day == Calendar.WEDNESDAY)
            return "星期三";
        return "";
    }
    public static String getWeek(int day) {
        if (day == Calendar.SATURDAY)
            return "星期六";
        else if (day == Calendar.SUNDAY)
            return "星期日";
        else if (day == Calendar.FRIDAY)
            return "星期五";
        else if (day == Calendar.MONDAY)
            return "星期一";
        else if (day == Calendar.THURSDAY)
            return "星期四";
        else if (day == Calendar.TUESDAY)
            return "星期二";
        else if (day == Calendar.WEDNESDAY)
            return "星期三";
        return "";
    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    public static Date strToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 时间转换 格式yyyy-MM-dd
     *
     * @param datestr
     * @return
     */
    public static Date parseDate(String datestr) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间转换 格式yyyy-MM-dd
     *
     * @param datestr
     * @return
     */
    public static Date parseDate(String datestr, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 两个时间相减
     *
     * @param date1
     * @param date2
     * @return 返回小时数
     */
    public static long subDate(Date date1, Date date2) {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化时间
        try {
            String date1Str = d.format(date1);// 按以上格式 将当前时间转换成字符串
            String date2Str = d.format(date2);
            long result = (d.parse(date1Str).getTime() - d.parse(date2Str).getTime()) / 3600000;// 这个的除以1000得到秒，相应的60000得到分，3600000得到小时
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 两个时间相减
     *
     * @param date1
     * @param date2
     * @return 返回毫秒
     */
    public static long subDateMilisecond(Date date1, Date date2) {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化时间
        try {
            String date1Str = d.format(date1);// 按以上格式 将当前时间转换成字符串
            String date2Str = d.format(date2);
            long result = (d.parse(date1Str).getTime() - d.parse(date2Str).getTime());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取时间相加（天数）后的时间
     *
     * @param date 原时间
     * @param dayCount 相加天数，可为负数
     * @return
     */
    public static Date addDate(Date date, int dayCount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, dayCount);
        return cal.getTime();
    }

    public static Date SecondTodate(long second) {
        long s = second - System.currentTimeMillis() / 1000;
        long day = s / (60 * 60 * 24);
        return addDate(new Date(), (int) day);
    }


    /**
     * time是秒
     * @param time
     * @param format
     * @return
     */
    public static String strToTime(long time, String format) {
        try {
            if (TextUtils.isEmpty(format)) {
                return "";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(new Date(time * 1000));
        } catch (Exception e) {
            return "";
        }
    }


    public static String getDateStr(Date date, String format) {
        SimpleDateFormat d = new SimpleDateFormat(format);// 格式化时间
        return d.format(date);
    }

    public static String getDateStr(Long seconds, String format) {
        SimpleDateFormat d = new SimpleDateFormat(format);// 格式化时间
        return d.format(timeToDate(seconds));
    }

    public static Date timeToDate(Long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp * 1000);
        return calendar.getTime();
    }

    public static String getMothNameEng(int month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
        }
        return "January";
    }

    /**
     * 根据传入的时间格式显示字符串日期
     *
     * @param millis 时间搓
     * @param date   日期格式 "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getSimpleTime(long millis, String date) {
        return millis2String(millis, new SimpleDateFormat(date, Locale.getDefault()));
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为format</p>
     *
     * @param millis 毫秒时间戳
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String millis2String(final long millis, final DateFormat format) {
        return format.format(new Date(millis));
    }

    /**
     * 获取今天的日期
     *
     * @return
     */
    public static String getCurrentDate() {
        Date date = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }

    /**
     * 格式如下：
     * 今天：今天08:00
     * 昨天：昨天08:00
     * 今年昨天以前：3-16 08:00
     * 今年以前：2015-03-16 08:00
     *
     * @param time 服务器端10位时间戳
     * @return
     */
    public static String getListTime(long time) {
        String pattern = null;
        Date date = new Date(time * 1000);
        if (isToday(time)) {
            pattern = "今天 HH:mm";
        } else if (isYesterday(time)) {
            pattern = "昨天 HH:mm";
        }
//        else if (isCurrentYear(time)) {
//            pattern = "MM-dd HH:mm";
//        }
        else {
            pattern = "yyy-MM-dd HH:mm";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * @param time 服务器的10位时间戳
     * @return
     */
    public static boolean isToday(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000);
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());
        if ((calendar.get(Calendar.YEAR) == now.get(Calendar.YEAR)) &&
                (calendar.get(Calendar.MONTH) == now.get(Calendar.MONTH)) &&
                (calendar.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH))) {
            return true;
        }
        return false;
    }

    /**
     * @param time 服务器的十位时间戳
     * @return
     */
    public static boolean isYesterday(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000 + 24 * 60 * 60 * 1000);
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());
        if ((calendar.get(Calendar.YEAR) == now.get(Calendar.YEAR)) &&
                (calendar.get(Calendar.MONTH) == now.get(Calendar.MONTH)) &&
                (calendar.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH))) {
            return true;
        }
        return false;
    }

    /**
     * @param time 服务器端的10位时间戳
     * @return
     */
    public static boolean isCurrentYear(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000);
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());
        if (calendar.get(Calendar.YEAR) == now.get(Calendar.YEAR)) {
            return true;
        }
        return false;
    }

}
