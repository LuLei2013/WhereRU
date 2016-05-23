package com.whereru.greengrass.goforit.commonmodule.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.text.TextUtils;


/**
 * 日期/时间工具类
 *
 * @author WangZengYang
 * @since 2013-8-30
 */
public class TimeUtil {
    /**
     * 时间格式：MM-dd HH:mm:ss
     */
    public final static String MD_HMS = "MM-dd HH:mm:ss";
    /**
     * 时间格式：MM-dd HH:mm
     */
    public final static String MD_HM = "MM-dd HH:mm";
    /**
     * 时间格式：MM/dd HH:mm
     */
    public final static String MDorHM = "MM/dd HH:mm";
    /**
     * 时间格式：MM月dd日 HH:mm
     */
    public final static String MDHM = "MM月dd日 HH:mm";
    /**
     * 时间格式：yyyy-MM-dd
     */
    public final static String YMD = "yyyy-MM-dd";
    /**
     * 时间格式：yyyy-MM-dd HH:mm:ss
     */
    public final static String YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间格式：yyyy-MM-dd HH:mm:ss.SSS
     */
    public final static String YMD_HMSS = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 时间格式：HH:mm
     */
    public final static String HM = "HH:mm";
    /**
     * 时间格式：MM月dd日
     */
    public final static String MD = "MM月dd日";
    /**
     * 时间格式：yyyy年-MM月dd日
     */
    public final static String YYMD = "yyyy年MM月dd日";

    /**
     * 获取当前时间，单位:毫秒
     *
     * @return
     */
    public static long currentTimeMillis() {
        long time = System.currentTimeMillis();
        if (time < 1e11) {
            time *= 1000;
        }
        return time;
    }

    /**
     * 获取当前时间，单位：秒
     *
     * @return
     */
    public static long currentTimeSeconds() {
        long time = System.currentTimeMillis();
        if (time > 1e11) {
            time /= 1000;
        }
        return time;
    }

    public static boolean isSameDay(long millisec1, long millisec2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        c1.setTimeInMillis(millisec1);

        Calendar c2 = Calendar.getInstance();
        c2.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        c2.setTimeInMillis(millisec2);
        // 同一年
        if (c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR) == 0) {
            return c1.get(Calendar.DAY_OF_YEAR) - c2.get(Calendar.DAY_OF_YEAR) == 0;
        }
        return false;
    }


    /**
     * 格式化时间为00:00
     *
     * @param hour
     * @param minute
     * @return
     */
    public static String getTimeFormat(int hour, int minute) {
        return String.format("%1$,02d:%2$,02d", hour, minute);
    }

    /**
     * 格式化时间为:XX:XX
     *
     * @param minute
     * @param second
     * @return
     */
    public static String getTimeFormatStyleMs(int minute, int second) {
        return String.format("%1$,02d:%2$,02d", minute, second);
    }

    /**
     * 根据指定的格式获得格式化后的时间格式
     *
     * @param ms     毫秒
     * @param format 需要转换时间的格式
     * @return
     */
    public static String formatTime(long ms, String format) {
        if (TextUtils.isEmpty(format)) {
            // 之前这throw了一个异常，没必要啊，return null的话，怕引起其他异常，直接return个空字符串
            return "";
        }
        Date date = new Date(ms);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 根据制定的格式获得当期那时间格式化后的时间格式
     *
     * @param format 需要转换时间的格式
     * @return
     */
    public static String formatCurrentTime(String format) {
        return formatTime(currentTimeMillis(), format);
    }

    /**
     * 将日期转成毫秒
     *
     * @param date 字符型日期
     */
    public static long converDateToMillisecond(String date) {
        if (isTextEmpty(date)) {
            return 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(YMD_HMS);
        long mills = 0;
        try {
            Date d = sdf.parse(date);
            mills = d.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mills;
    }

    /**
     * 判断字符串是否为空
     */
    private static boolean isTextEmpty(String text) {
        if (TextUtils.isEmpty(text)) {
            return true;
        }
        return "null".equalsIgnoreCase(text);
    }

    /**
     * 两个时间点相隔的天数,不处理跨2年以上的情况，time2比time1大
     */
    public static int getDays(long time1, long time2) {
        if (time1 > time2) {
            return 0;
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(time1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(time2);
        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);
        int dayOfYear1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int dayOfYear2 = calendar2.get(Calendar.DAY_OF_YEAR);
        int days = dayOfYear2 - dayOfYear1;
        if (year1 < year2) {
            int maxDayOfYear = calendar1.getActualMaximum(Calendar.DAY_OF_YEAR);
            days = dayOfYear2 + maxDayOfYear - dayOfYear1;
        }
        return days;
    }

    /**
     * 从时间戳得到当前的时间（几号，几点）
     */
    public static int[] getDayHourFromTimeStamp(long time) {
        int[] data = new int[2];
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        data[0] = day;
        data[1] = hour;
        return data;
    }

    /**
     * 比较时间的大小（以小时为最小单位）
     *
     * @param time1
     * @param time2
     * @return 0相等，-1小于(表示time1时间更早)，1大于(表示time1时间更晚)
     */
    public static int compareTimeByHour(long time1, long time2) {
        int compare = 0;
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(time1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(time2);
        if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
            if (cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)) {
                if (cal1.get(Calendar.HOUR_OF_DAY) == cal2.get(Calendar.HOUR_OF_DAY)) {
                    compare = 0;
                } else if (cal1.get(Calendar.HOUR_OF_DAY) > cal2.get(Calendar.HOUR_OF_DAY)) {
                    compare = 1;
                } else if (cal1.get(Calendar.HOUR_OF_DAY) < cal2.get(Calendar.HOUR_OF_DAY)) {
                    compare = -1;
                }

            } else if (cal1.get(Calendar.DAY_OF_YEAR) > cal2.get(Calendar.DAY_OF_YEAR)) {
                compare = 1;
            } else if (cal1.get(Calendar.DAY_OF_YEAR) < cal2.get(Calendar.DAY_OF_YEAR)) {
                compare = -1;
            }

        } else if (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR)) {
            compare = 1;
        } else if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) {
            compare = -1;
        }
        return compare;
    }

    /**
     * 判断time1是否在time2的三天内,不用精确到小时
     */
    public static boolean CompareTime(long time1, long time2, int days) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(time1);
        calendar1.add(Calendar.DAY_OF_MONTH, 3);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(time2);
        if (calendar2.get(Calendar.YEAR) < calendar1.get(Calendar.YEAR)
                || calendar2.get(Calendar.MONTH) < calendar1.get(Calendar.MONTH)
                || calendar2.get(Calendar.DAY_OF_MONTH) < calendar1.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }

        return false;
    }

    /**
     * 去掉分钟数
     */
    public static long trimMinute(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        long dTime = calendar.getTimeInMillis();
        return dTime;
    }

}
