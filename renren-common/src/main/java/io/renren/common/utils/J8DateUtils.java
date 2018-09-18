package io.renren.common.utils;

import org.apache.commons.lang.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Date工具类型java8
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2018-07-30
 */
public class J8DateUtils {
    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param dateTime  日期
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(LocalDateTime dateTime) {
        return format(dateTime, DATE_PATTERN);
    }

    public static String format(LocalDateTime dateTime, String pattern) {
        if(dateTime != null) {
            return dateTime.format(DateTimeFormatter.ofPattern(pattern));
        } else {
            return "";
        }
    }

    /**
     * 字符串转换成日期
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static LocalDateTime stringToDate(String strDate, String pattern) {
        if(StringUtils.isBlank(strDate)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(strDate, formatter);
    }

    /**
     * 根据周数，获取开始日期、结束日期
     * @param week  周期  0本周，-1上周，-2上上周，1下周，2下下周
     * @return  返回date[0]开始日期、date[1]结束日期
     */
    public static LocalDate[] getWeekStartAndEnd(int week) {
        LocalDate localDate = LocalDate.now();
        LocalDate beginDate = localDate.plusWeeks(week);
        LocalDate endDate = beginDate.plusDays(6);
        return new LocalDate[]{beginDate, endDate};
    }


    /**
     * 对日期的【秒】进行加/减
     *
     * @param localDateTime 日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static LocalDateTime addDateSeconds(LocalDateTime localDateTime, int seconds) {
        return localDateTime.plusSeconds(seconds);
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param localDateTime 日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static LocalDateTime addDateMinutes(LocalDateTime localDateTime, int minutes) {
        return localDateTime.plusMinutes(minutes);
    }


    /**
     * 对日期的【小时】进行加/减
     *
     * @param localDateTime 日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static LocalDateTime addDateHours(LocalDateTime localDateTime, int hours) {
        return localDateTime.plusHours(hours);
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param localDateTime 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static LocalDateTime addDateDays(LocalDateTime localDateTime, int days) {
        return localDateTime.plusDays(days);
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param localDateTime 日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static LocalDateTime addDateWeeks(LocalDateTime localDateTime, int weeks) {
        return localDateTime.plusWeeks(weeks);
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param localDateTime 日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static LocalDateTime addDateMonths(LocalDateTime localDateTime, int months) {
        return localDateTime.plusMonths(months);
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param localDateTime 日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static LocalDateTime addDateYears(LocalDateTime localDateTime, int years) {
        return localDateTime.plusYears(years);
    }

    /**
     * test
     * @param args
     */
    public static void main(String[] args) {
        String dateTime = format(addDateYears(stringToDate("2013-09-18 14:30:00", DATE_TIME_PATTERN), 5), DATE_TIME_PATTERN);
        System.out.println(dateTime);
    }


}
