package io.renren.common.utils;

import java.util.Optional;

/**
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
}
