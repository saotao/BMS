package stao.BMS.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理工具类
 *

 */
public final class DateUtil {

    /**
     * 此类不需要实例化
     */
    private DateUtil() {
    }

    public static Date date = null;
    public static DateFormat dateFormat = null;
    public static Calendar calendar = null;

    public static final long ONE_DAY = 60 * 1000 * 60 * 24;
    public static final long HOUR = 60 * 60;
    public static final long MIN = 60;

    public static String DEFAULT_FORMAT = "yyyy-MM-dd";
    public static String DEFAULT_FORMAT_hh = "yyyy-MM-dd hh:mm:ss";
    private static final String timePattern = "HH:mm";
    private static final String timePattern2 = "yyyyMMddhhmmss";

    /**
     * 时间转换：长整型转换为日期字符型
     *
     * @param format
     *            格式化类型：yyyy-MM-dd
     * @param time
     *            13位有效数字：1380123456789
     *
     * @return 格式化结果 (yyyy-MM-dd)
     */
    public static String formatToString(String format, long time) {
        if (time == 0) {
            return "";
        }
        return new SimpleDateFormat(format).format(new Date(time));
    }

    /**
     * 时间转换：日期字符型转换为长整型
     *
     * @param format
     *            格式化类型：yyyy-MM-dd
     *
     * @return 13位有效数字 (1380123456789)
     */
    public static long formatToLong(String format) {
        SimpleDateFormat f = new SimpleDateFormat(format);
        return Timestamp.valueOf(f.format(new Date())).getTime();
    }

    /**
     * 功能描述：格式化日期
     *
     * @param dateStr
     *            String 字符型日期
     * @param format
     *            String 格式
     * @return Date 日期
     */
    public static Date parseDate(String dateStr, String format) {
        try {
            dateFormat = new SimpleDateFormat(format);
            String dt = dateStr.replaceAll("-", "/");
            dt = dateStr;
            if ((!dt.equals("")) && (dt.length() < format.length())) {
                dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]", "0");
            }
            date = (Date) dateFormat.parse(dt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 功能描述：格式化输出日期
     *
     * @param date
     *            Date 日期
     * @param format
     *            String 格式
     * @return 返回字符型日期
     */
    public static String format(Date date, String format) {
        String result = "";
        try {
            if (date != null) {
                dateFormat = new SimpleDateFormat(format);
                result = dateFormat.format(date);
            }
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 获取日期时间戳，单位秒
     * @param date
     * @return
     */
    public static Long getTimestamp(Date date) {
        return (date == null) ? null : date.getTime() / DateUtils.MILLIS_PER_SECOND;
    }

    public static Long getTime(){
        return (date==null) ? null : new Date().getTime();
    }

    /**
     * 将timestamp转换为Date对象
     * @param timestamp
     * @return
     */
    public static Date fromTimestamp(Long timestamp) {
        return (timestamp == null) ? null : new Date(timestamp * DateUtils.MILLIS_PER_SECOND);
    }


    /**
     * 获取今天凌晨时间
     */
    public static long getTodayStart() {
        return getDayStart(0);
    }

    /**
     * 获取几天前的凌晨时间 毫秒
     */
    public static long getTimeStart(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_YEAR, day > 0 ? -day : day);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取距当前时间n天的起始时间
     *  n 为负数则为过去时间
     *  n 为正数为未来时间
     *  n=0 则为当前起始时间
     */
    public static long getDayStart(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取当月的起始时间
     *
     */
    public static long getThisMonthStart(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTimeInMillis();
    }
}
