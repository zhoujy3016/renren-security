package io.renren.common.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CookieUtils {
    /**
     * 取得cookie中的sessionId
     * @param request
     * @return
     */
    public static String getSessionId(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        //获取所有的cookie值
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            Arrays.stream(cookies).forEach(cookie -> cookieMap.put(cookie.getName(),cookie));
            if(cookieMap.containsKey("JSESSIONID")) {
                return ((Cookie)cookieMap.get("JSESSIONID")).getValue();
            }
        }
        return StringUtils.EMPTY;
    }

}
