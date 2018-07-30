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
public class j8DateUtils {
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

}
