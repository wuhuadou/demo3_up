package com.app.shop.mylibrary.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @anthor : 大海
 * 每天进步一点点
 * @time :   2019/5/22
 * @description :
 */


public class TimeUtil {

    public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 传入当前时间，获取到次日凌晨时长
     *
     * @return
     */
    public static Long getSecondsNowToNext() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        // 改成这样就好了
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }


    /**
     * 将时间转换成需要的格式 00:00:00
     *
     * @param time 秒
     * @return
     */

    public static String formatLongToTimeStr(Long time) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        String hour2 = "";
        String minute2 = "";
        String second2 = "";
        second = time.intValue();
        if (second > 60) {
            minute = second / 60;    //整数部分---分钟
            second = second % 60;    //余数部分---秒
        }
        if (minute > 60) {
            hour = minute / 60;     //整数部分---时
            minute = minute % 60;   //余数部分---分钟
        }
        if ((hour + "").length() == 1) {  //时
            hour2 = "0" + hour;
        } else {
            hour2 = hour + "";
        }
        if ((minute + "").length() == 1) {//分
            minute2 = "0" + minute;
        } else {
            minute2 = minute + "";
        }
        if ((second + "").length() == 1) {//秒
            second2 = "0" + second;
        } else {
            second2 = second + "";
        }
        String strtime = hour2 + ":" + minute2 + ":" + second2;
        return strtime;
    }


    /**
     * 获取当前日期
     *
     * @param format 格式
     * @return
     */
    //获取当前日期 yyyy-MM-dd
    public static String getTodayData(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);//设置日期格式
        String str = df.format(new Date());// new Date()为获取当前系统时间
        return str;
    }


    // string类型转换为date类型
    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        try {
            date = formatter.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 把获取的时间从一种格式转换成另一种格式
     *
     * @param time
     * @param formatBefor
     * @param formatAfter
     * @return
     */
    public static String getFormatTime(String time, String formatBefor, String formatAfter) {
        if (!TextUtils.isEmpty(time) && !TextUtils.isEmpty(formatBefor) && !TextUtils.isEmpty(formatAfter)) {
            Date date = stringToDate(time, formatBefor);
            SimpleDateFormat simpleDateFormatAfter = new SimpleDateFormat(formatAfter);
            return simpleDateFormatAfter.format(date);
        }
        return "";
    }


    /**
     * 几天之后
     *
     * @param begin_time 开始日期 yyyy-MM-dd
     * @param days_after 几天之后
     */
    public static String getAfterDate(String begin_time, int days_after) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date_begin = null;
        try {
            date_begin = dateFormat.parse(begin_time);
            calendar.setTime(date_begin);
            //将日期设置为几天之后    单位由第一个参数控制
            calendar.add(Calendar.DAY_OF_YEAR, days_after);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //获取的新时间
        String day_new = dateFormat.format(calendar.getTime());
        return day_new;
    }


    public static String stringToString(String time, String beforFormatType, String toFormatType) {
        if (StringUtil.isEmpty(time) || StringUtil.isEmpty(beforFormatType) || StringUtil.isEmpty(toFormatType))
            return "";
        Date date = stringToDate(time, beforFormatType);
        return dateToString(date, toFormatType);
    }

    // date类型转换为String类型
    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }


    /**
     * 将日期格式的字符串转换为长整型
     *
     * @param date
     * @param format
     * @return
     */
    public static long str2Long(String date, String format) {
        try {
            if (!TextUtils.isEmpty(date)) {
                if (TextUtils.isEmpty(format)) {
                    format = FORMAT;
                }
                SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
                return formatter.parse(date).getTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
