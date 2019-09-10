package io.renren.common.utils;

import java.util.Optional;

/**
 * lnpc
 * Optional工具类
 */
public class OptionalUtils {

    /**
     * 将字符串转换为Integer并返回Optional<Integer>
     * @param s
     * @return
     */
    public static Optional<Integer> s2i(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch(NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * 调用字符串转int，并给出一个默认值0
     * @param s
     * @return
     */
    public static int stringToInt(String s) {
        return Optional.ofNullable(s)
                .flatMap(OptionalUtils::s2i)
                .orElse(0);
    }

    /**
     * 将字符串转换为Long并返回Optional<Long>
     * @param s
     * @return
     */
    public static Optional<Long> s2l(String s) {
        try {
            return Optional.of(Long.parseLong(s));
        } catch(NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * 调用字符串转long，并给出一个默认值0
     * @param s
     * @return
     */
    public static long stringToLong(String s) {
        return Optional.ofNullable(s)
                .flatMap(OptionalUtils::s2l)
                .orElse(0L);
    }

    /**
     * 字符串trim 返回Optional<String>
     * @param s
     * @return
     */
    public static Optional<String> s2trim(String s) {
        try {
            return Optional.of(s.trim());
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    /**
     * 返回一个trim后的字符串，给出默认值空
     * @param s
     * @return
     */
    public static String stringTrim(String s) {
        return Optional.ofNullable(s)
                .flatMap(OptionalUtils::s2trim)
                .orElse("");
    }

    /**
     * Object to String, 返回Optional<String>
     * @param o
     * @return
     */
    public static Optional<String> o2s(Object o) {
        try {
            return Optional.of(String.valueOf(o));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * Object to String
     * @param o
     * @return
     */
    public static String parseToString(Object o) {
        return Optional.ofNullable(o)
                .flatMap(OptionalUtils::o2s)
                .orElse("");
    }

}
