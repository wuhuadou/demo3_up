package com.app.shop.mylibrary.utils;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input) || "null".equalsIgnoreCase(input)) {
            return true;
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static String addZero(int num) {
        String str = num + "";
        if (str.length() == 1) {
            str = "0" + str;
        }
        return str;
    }

    /**
     * 逗号拼接的字符串转集合
     *
     * @param str
     * @return
     */
    public static List strToList(String str) {
        List<String> list = new ArrayList<>();
        if (!StringUtil.isEmpty(str)) {
            String[] strings = str.split(",");
            list = Arrays.asList(strings);
        }

        return list;
    }

    /**
     * String集合转字符串
     *
     * @param stringList 集合
     * @param symbol     拼接符号
     */

    public static String ListToStr(List<String> stringList, String symbol) {

        String str = " ";
        StringBuffer sb = new StringBuffer();
        if (stringList != null && stringList.size() > 0) {
            for (int i = 0; i < stringList.size(); i++) {
                sb.append(stringList.get(i) + symbol);
            }
        }
        if (sb != null && sb.length() > 0) {
            str = sb.substring(0, sb.length() - 1);
        }
        return str;
    }


    /**
     * 判断是否为汉字
     *
     * @param string
     * @return
     */

    public static boolean isChinese(String string) {
        int n = 0;
        for (int i = 0; i < string.length(); i++) {
            n = (int) string.charAt(i);
            if (!(19968 <= n && n < 40869)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1.判断字符串是否仅为数字:
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {

        if (StringUtil.isEmpty(str)) {

            return false;

        } else {
            for (int i = str.length(); --i >= 0; ) {

                if (!Character.isDigit(str.charAt(i))) {

                    return false;
                }
            }
            return true;
        }
    }


    /**
     * 格式化数据返回 小数点后两位表示
     */
    public static String toTwoDianString(Double doubleValue) {
        if (doubleValue == null) {
            return "0";
        }
        DecimalFormat format = new DecimalFormat("#0.00");
        String result = format.format(doubleValue);
//        if (result.substring(result.length() - 2, result.length()).equals("00")) {
//            result = result.substring(0, result.length() - 3);
//        }
        return result;
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        return !TextUtils.isEmpty(mobiles) && mobiles.startsWith("1") && mobiles.length() == 11;
//        String telRegex = "(((13[0-9]{1})|(15[0123456789]{1})|145|147|(17[0123456789]{1})|(18[0123456789]{1}))+\\d{8})";
//        if (TextUtils.isEmpty(mobiles))
//            return false;
//        else
//            return mobiles.matches(telRegex);
    }

    /**
     * 验证码
     */
    public static boolean isCode(String code) {
        return !TextUtils.isEmpty(code) && code.length() == 6;
    }


    /**
     * 验证邮箱格式
     */
    public static boolean isEmail(String strEmail) {
//        String strPattern = "^[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
//        String strPattern = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
//        Pattern p = Pattern.compile(strPattern);
//        Matcher m = p.matcher(strEmail);
//        boolean b = m.matches();
//        return b;

        String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        //正则表达式的模式 编译正则表达式
        Pattern p = Pattern.compile(RULE_EMAIL);
        //正则表达式的匹配器
        Matcher m = p.matcher(strEmail);
        //进行正则匹配\
        return m.matches();

    }

    /**
     * 设置字符串
     *
     * @param str
     * @return
     */
    public static String getContent(String str) {

        if (isEmpty(str)) {
            return "--";
        } else {
            return str;
        }
    }

    /**
     * 设置字符串
     *
     * @param str
     * @return
     */
    public static String getContentEmpty(String str) {

        if (isEmpty(str)) {
            return "";
        } else {
            return str;
        }
    }

    public static boolean isPassword(String password) {
        String strPattern = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(password);
        boolean b = m.matches();
        return b;
    }

    public static void setPasswordEditInputType(EditText editText, int length) {
        String type = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        editText.setKeyListener(DigitsKeyListener.getInstance(type));
        InputFilter[] filters = {new InputFilter.LengthFilter(length)};
        editText.setFilters(filters);

    }

    public static void setNumEditInputType(EditText editText, int length) {
        String type = "0123456789";
        editText.setKeyListener(DigitsKeyListener.getInstance(type));
        InputFilter[] filters = {new InputFilter.LengthFilter(length)};
        editText.setFilters(filters);
    }

    public String addblankinmiddle(String str) {
        //字符串长度
        int strlenth = str.length();
        //需要加空格数量
        int blankcount = 0;
        //判断字符串长度
        if (strlenth <= 4) {
            blankcount = 0;
        } else {
            blankcount = strlenth % 4 > 0 ? strlenth / 4 : str.length() / 4 - 1; //需要加空格数量
        }
        //插入空格
        if (blankcount > 0) {
            for (int i = 0; i < blankcount; i++) {
                str = str.substring(0, (i + 1) * 4 + i) + " " + str.substring((i + 1) * 4 + i, strlenth + i);
            }
        } else {

        }
        //返回
        return str;
    }

    /**
     * 按长度格式化中文字符
     *
     * @param input
     * @param num
     * @return
     */
    public static String getChineseStringByWeiShu(String input, int num) {
        if (input == null || input.length() == 0) {
            return "";
            // return
            // MyApplication.instance.getResources().getString(R.string.default_value);
        }
        char[] ch = input.toCharArray();
        StringBuffer output = new StringBuffer();
        double valueLength = 0;
        int endCount = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                // 中文字符长度为1
                valueLength += 1;
            } else {
                valueLength += 0.5;
            }
            output.append(c);
            if (valueLength >= num) {
                endCount = i;
                break;
            }
        }
        if (valueLength < num) {
            return output.toString();
        } else if (endCount == ch.length - 1) {
            return output.toString();
        } else {
            return output.toString() + "...";
        }
    }

    /**
     * 计算字符长度
     *
     * @param input
     * @return
     */
    public static int getChineseLength(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        char[] ch = input.toCharArray();
        // StringBuffer output = new StringBuffer();
        int valueLength = 0;
        // int endCount = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                // 中文字符长度为1
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * 根据Unicode编码完美的判断中文汉字和符号
     *
     * @param c 需要判断的字符
     * @return 是否是中文字符
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

    /**
     * 验证密码
     */
    public static boolean isPwd(String pwd) {
        if (isEmpty(pwd)) {
            return false;
        }
        boolean flag = true;
        for (int i = 0; i < pwd.length(); i++) {
            // 内容只能是数字字符
            if (!Character.isDigit(pwd.charAt(i))
                    && !Character.isLetter(pwd.charAt(i))) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * Converts an InputStream to String
     *
     * @param
     * @return
     * @throws IOException
     */
    public static String Inputstr2Str_Reader(InputStream in) {
        StringBuffer sb = new StringBuffer();
        InputStreamReader isr = new InputStreamReader(in);
        char buf[] = new char[20];
        int nBufLen = 0;
        try {
            nBufLen = isr.read(buf);
            while (nBufLen != -1) {
                sb.append(new String(buf, 0, nBufLen));
                nBufLen = isr.read(buf);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Integer转字符串
     *
     * @param value
     * @return
     */
    public static Integer stringTransInt(String value) {
        if (StringUtil.isEmpty(value)) {
            return null;
        }
        return Integer.parseInt(value);
    }

    /**
     * 字符串为空的时候，返回"— —"
     *
     * @param content
     * @return
     */
    public static String showStringContent(String content) {
        if (isEmpty(content)) {
            return "—";
            // return
            // MyApplication.instance.getResources().getString(R.string.default_value);
        }
        return content;
    }

    public static String getModileNumber(String number) {
        if (StringUtil.isEmpty(number)) {
            return "";
        }
        String newNumber = "";
        if (number.length() > 7) {
            newNumber = number.substring(0, 3) + "****"
                    + number.substring(7, number.length());
        }
        return newNumber;
    }

    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId
                .substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null
                || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            // 如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    public static String onToTwo(int num) {
        if (num < 10) {
            return "0" + num;
        }
        return num + "";
    }

    /**
     * 传入20161209235959
     * 输出2016年12月09日23:59:59
     *
     * @param time
     * @return
     */
    public static String startModleTime(String time) {
        String mTime = null;

        String year = time.substring(0, 4);
        String Month = time.substring(4, 6);
        String day = time.substring(6, 8);
        String Time1 = time.substring(8, 10);
        String Time2 = time.substring(10, 12);
        String Time3 = time.substring(12, 14);
        mTime = "自" + year + "年" + Month + "月" + day + "日" + Time1 + ":" + Time2 + ":" + Time3 + "起";

        return mTime;
    }

    /**
     * 传入2016-12-09 23:59:59
     * 输出2016年12月09日
     *
     * @param time
     * @return
     */
    public static String getSampleTime(String time) {

        if (time.length() < 10) {
            return time;
        }

        String mTime = null;

        String year = time.substring(0, 4);
        String Month = time.substring(5, 7);
        String day = time.substring(8, 10);
        mTime = year + "年" + Month + "月";

        return mTime;
    }

    /**
     * 传入2016-12-09 23:59:59
     * 输出2016/12/09
     *
     * @param time
     * @return
     */
    public static String getSampleTime2(String time) {

        if (time.length() < 10) {
            return time;
        }

        String mTime = null;

        String year = time.substring(0, 4);
        String Month = time.substring(5, 7);
        String day = time.substring(8, 10);
        mTime = year + "/" + Month + "/" + day;

        return mTime;
    }

    /**
     * 传入2016年12月09日
     * 输出2016.12.09
     *
     * @param time
     * @return
     */
    public static String getSelectTimeStyle(String time) {

        if (time.length() < 10) {
            return time;
        }

        String mTime = null;

        String year = time.substring(0, 4);
        String Month = time.substring(5, 7);
        String day = time.substring(8, 10);
        mTime = year + "." + Month + "." + day;

        return mTime;
    }

    /**
     * 传入20161209235959
     * 输出 至2016年12月09日23:59:59止
     *
     * @param time
     * @return
     */
    public static String ModleTime(String time) {
        String mTime = null;

        String year = time.substring(0, 4);
        String Month = time.substring(4, 6);
        String day = time.substring(6, 8);
        String Time1 = time.substring(8, 10);
        String Time2 = time.substring(10, 12);
        String Time3 = time.substring(12, 14);
        mTime = "至" + year + "年" + Month + "月" + day + "日 " + Time1 + ":" + Time2 + ":" + Time3 + "止";

        return mTime;
    }


    /**
     * 传入 20161209235959
     * 输出 2016年12月09日 23:59:59
     *
     * @param time
     * @return
     */
    public static String ModleTime_Z(String time) {
        String mTime = null;

        String year = time.substring(0, 4);
        String Month = time.substring(4, 6);
        String day = time.substring(6, 8);
        String Time1 = time.substring(8, 10);
        String Time2 = time.substring(10, 12);
        String Time3 = time.substring(12, 14);
        mTime = year + "年" + Month + "月" + day + "日 " + Time1 + ":" + Time2 + ":" + Time3;

        return mTime;
    }

    /**
     * 传入20161209235959
     * 输出 2016年12月09日
     *
     * @param time
     * @return
     */
    public static String getModleTime(String time) {
        if (StringUtil.isEmpty(time) || time.length() < 12) {
            return "";
        }
        String mTime = null;
        String year = time.substring(0, 4);
        String Month = time.substring(4, 6);
        String day = time.substring(6, 8);
        String Time1 = time.substring(8, 10);
        String Time2 = time.substring(10, 12);
        String Time3 = time.substring(12, 14);
        mTime = year + "年" + Month + "月" + day + "日";

        return mTime;
    }

    /**
     * 传入20161209
     * 输出 2016-12-09
     *
     * @param time
     * @return
     */
    public static String getModleTimeToNew1(String time) {
        if (StringUtil.isEmpty(time) || time.length() < 8) {
            return "";
        }
        String mTime = null;
        String year = time.substring(0, 4);
        String Month = time.substring(4, 6);
        String day = time.substring(6, 8);

        mTime = year + "-" + Month + "-" + day;

        return mTime;
    }


    /**
     * 实现将初始日期时间2012年07月02日 16:45 拆分成20120702164545
     */
    public static String getCalendarByInintData(String initDateTime) {
        String ToTime;
        // 将初始日期时间2012年07月02日 拆分成年 月 日
        String date = spliteString(initDateTime, "日", "index", "front"); // 日期
        String yearStr = spliteString(date, "年", "index", "front"); // 年份
        String monthAndDay = spliteString(date, "年", "index", "back"); // 月日
        String monthStr = spliteString(monthAndDay, "月", "index", "front"); // 月
        String dayStr = spliteString(monthAndDay, "月", "index", "back"); // 日
        ToTime = yearStr + "" + monthStr + "" + dayStr + "000000";
        return ToTime;
    }

    /**
     * 实现将初始日期时间2012年07月02日拆分成20120702
     */
    public static String getCalendarByInintDatas(String initDateTime) {
        String ToTime;
        // 将初始日期时间2012年07月02日 拆分成年 月 日
        String date = spliteString(initDateTime, "日", "index", "front"); // 日期
        String yearStr = spliteString(date, "年", "index", "front"); // 年份
        String monthAndDay = spliteString(date, "年", "index", "back"); // 月日
        String monthStr = spliteString(monthAndDay, "月", "index", "front"); // 月
        String dayStr = spliteString(monthAndDay, "月", "index", "back"); // 日
        ToTime = yearStr + "" + monthStr + "" + dayStr;
        return ToTime;
    }


    /**
     * 截取子串
     *
     * @param srcStr      源串
     * @param pattern     匹配模式
     * @param indexOrLast
     * @param frontOrBack
     * @return
     */
    public static String spliteString(String srcStr, String pattern, String indexOrLast, String frontOrBack) {
        String result = "";
        int loc = -1;
        if (indexOrLast.equalsIgnoreCase("index")) {
            loc = srcStr.indexOf(pattern); // 取得字符串第一次出现的位置
        } else {
            loc = srcStr.lastIndexOf(pattern); // 最后一个匹配串的位置
        }
        if (frontOrBack.equalsIgnoreCase("front")) {
            if (loc != -1)
                result = srcStr.substring(0, loc); // 截取子串
        } else {
            if (loc != -1)
                result = srcStr.substring(loc + 1, srcStr.length()); // 截取子串
        }
        return result;
    }

    /**
     * 半角转换为全角
     *
     * @param input 包含半角字符的字符串
     * @return 全角字符
     */
    public static String DBCToSBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 获取中文的个数
     *
     * @param input
     * @return
     */
    public static int getChineseLengths(String input) {
        int l = input.length();
        int blen = 0;
        for (int i = 0; i < l; i++) {
            if ((input.charAt(i) & 0xff00) != 0) {
                blen++;
            }
        }
        return blen;
    }

    /**
     * 隐藏手机号码
     *
     * @param input
     * @return
     */
    public static String phoneNoHide(String input) {
        if (input != null) {
            int l = input.length();
            if (l == 11) {
                StringBuilder stringBuilder = new StringBuilder(input);
                return stringBuilder.replace(3, 7, "****").toString();
            }
        }
        return "";
    }

    /**
     * 隐藏身份证号码
     *
     * @param input 身份证号
     * @return 隐藏后的串
     */
    public static String IDCardNoHide(String input) {

        return IDCardNoHide(input, "****************");
    }

    /**
     * 隐藏身份证号码
     *
     * @param input   身份证号
     * @param replace 替换字符
     * @return 隐藏后的串
     */
    public static String IDCardNoHide(String input, String replace) {
        if (input != null) {
            int l = input.length();
            if (l == 18) {
                StringBuilder stringBuilder = new StringBuilder(input);
                return stringBuilder.replace(1, 17, replace).toString();
            } else if (l == 15) {
                StringBuilder stringBuilder = new StringBuilder(input);
                return stringBuilder.replace(1, 14, replace).toString();
            }
        }
        return "";
    }

    /**
     * 判断是否为url
     */
    public static boolean isURL(String input) {
        return input.matches("[a-zA-z]+://[^\\s]*");

    }


    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        DateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
        Date currentTime = null;
        try {
            currentTime = formatter.parse(str);
            String dateString = format1.format(currentTime);
            return dateString;
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return "";
    }

    public static long getStringTime(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

        try {
            return formatter.parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 随机指定范围内N个不重复的数
     * 最简单最基本的方法
     *
     * @param min 指定范围最小值
     * @param max 指定范围最大值
     * @param n   随机数个数
     */
    public static int[] randomCommon(int min, int max, int n) {
        int len = max - min + 1;

        if (max < min || n > len) {
            return null;
        }

        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = min; i < min + len; i++) {
            source[i - min] = i;
        }

        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            //待选数组0到(len-2)随机一个下标
            index = Math.abs(rd.nextInt() % len--);
            //将随机到的数放入结果集
            result[i] = source[index];
            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
            source[index] = source[len];
        }
        return result;
    }

    /**
     * 加减控件 获取价格|下单时，摘除“份”字
     *
     * @param oldNumber
     * @return
     */
    public static String getAddSubtractNum(String oldNumber) {
        String str = "";
        if (oldNumber.contains("份")) {
            str = oldNumber.substring(0, oldNumber.indexOf("份"));
        } else {
            str = oldNumber;
        }
        return str;
    }

    /**
     * 去除小数点 XX.0
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    /**
     * 价格工具类 分转换成元
     *
     * @param price
     * @return
     */
    public static String getPriceStr(String price) {
        float value = Float.parseFloat(price) / 100;
        return subZeroAndDot(value + "");
    }

    /**
     * 看好添加产品页，已选产品的name
     *
     * @param oldString
     * @return
     */
    public static String getNameString(String oldString) {
        if (TextUtils.isEmpty(oldString)) {
            return "";
        }
        if (oldString.length() > 8) {
            return oldString.substring(0, 7) + "...";
        }
        return oldString;
    }

    /**
     * @param priceUnit
     * @return
     */
    public static void priceUnit(String priceUnit, TextView textView) {
        if (priceUnit.contains("起")) {
            int index = priceUnit.indexOf("起");
            SpannableStringBuilder ssb = new SpannableStringBuilder(priceUnit);
            ssb.setSpan(new StyleSpan(Typeface.BOLD), 0, index, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            textView.setText(ssb);
        } else {
            textView.setText(priceUnit);
        }
    }

    @SuppressLint("ResourceAsColor")
    public static void priceUnit2(String priceUnit, TextView textView, @Nullable int color, int styleSpan) {
        if (priceUnit.contains("起")) {
            int index = priceUnit.indexOf("起");
            SpannableStringBuilder ssb = new SpannableStringBuilder(priceUnit);
            ssb.setSpan(new ForegroundColorSpan(color), index, priceUnit.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            ssb.setSpan(new StyleSpan(styleSpan), index, priceUnit.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            ssb.setSpan(new StyleSpan(Typeface.BOLD), 0, index, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            textView.setText(ssb);
        } else {
            textView.setText(priceUnit);
        }
    }

    @SuppressLint("ResourceAsColor")
    public static void priceUnit3(String priceUnit, TextView textView, @Nullable int color, int styleSpan) {
        if (priceUnit.contains("起")) {
            int index = priceUnit.indexOf("起");
            SpannableStringBuilder ssb = new SpannableStringBuilder(priceUnit);
            ssb.setSpan(new ForegroundColorSpan(color), index, priceUnit.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            ssb.setSpan(new StyleSpan(styleSpan), index, priceUnit.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            ssb.setSpan(new StyleSpan(Typeface.BOLD), 0, index, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            ssb.setSpan(new RelativeSizeSpan(0.8f), index, priceUnit.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            textView.setText(ssb);
        } else {
            textView.setText(priceUnit);
        }
    }

    @SuppressLint("ResourceAsColor")
    public static void priceUnit4(String msg, TextView textView, int priceLength, @Nullable int leftColor, @Nullable int rightColor) {


        SpannableString spannaString = new SpannableString(msg);
        spannaString.setSpan(new ForegroundColorSpan(leftColor), 0, msg.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (msg.contains("起")) {
            spannaString.setSpan(new ForegroundColorSpan(rightColor), msg.length() - 1, msg.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        spannaString.setSpan(new AbsoluteSizeSpan(24, true), 0, priceLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannaString.setSpan(new AbsoluteSizeSpan(11, true), priceLength, msg.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannaString);
    }

    @SuppressLint("ResourceAsColor")
    public static void priceUnit5(String msg, String key, TextView textView, int priceLength, @Nullable int leftColor, @Nullable int rightColor, int leftsize, int rightsize) {


        SpannableString spannaString = new SpannableString(msg);
        spannaString.setSpan(new ForegroundColorSpan(leftColor), 0, msg.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (msg.contains(key)) {
            spannaString.setSpan(new ForegroundColorSpan(rightColor), msg.indexOf(key), msg.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        spannaString.setSpan(new AbsoluteSizeSpan(leftsize, true), 0, priceLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannaString.setSpan(new AbsoluteSizeSpan(rightsize, true), priceLength, msg.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannaString);
    }

    @SuppressLint("ResourceAsColor")
    public static void priceUnit6(String msg, String key, TextView textView, int leftsize, int rightsize) {
        SpannableString spannaString = new SpannableString(msg);
        spannaString.setSpan(new AbsoluteSizeSpan(rightsize), 0, msg.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (msg.contains(key)) {
            spannaString.setSpan(new AbsoluteSizeSpan(leftsize), 0, msg.indexOf(key), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannaString.setSpan(new StyleSpan(Typeface.BOLD), 0, msg.indexOf(key), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        textView.setText(spannaString);
    }

    @SuppressLint("ResourceAsColor")
    public static void setString(String msg, String key, TextView textView) {
        if (msg.contains(key) && msg.indexOf(key) >= 0) {
            SpannableString spannaString = new SpannableString(msg);
            spannaString.setSpan(new StyleSpan(Typeface.BOLD), 0, msg.indexOf(key), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(spannaString);
        }
    }


    //读取数据
    public static String getString(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }



    @SuppressLint("ResourceAsColor")
    public static void setString2(String str1, String str2, String str3, int color1, int color2, TextView textView) {
        SpannableString spannaString = new SpannableString(str1 + str2 + str3);
        spannaString.setSpan(new ForegroundColorSpan(color1), str1.length(), (str1 + str2).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannaString);
    }
}
