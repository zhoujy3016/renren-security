package io.renren.common.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class CookieUtils {
    /**
     * 取得cookie中的sessionId
     * @param request
     * @return
     */
//    publis static final String KEY_SESSION = "JSESSIONID";

    public static final String KEY_SESSION = "SESSION";

    public static String getSessionId(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>(10);
        //获取所有的cookie值
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            Arrays.stream(cookies).forEach(cookie -> cookieMap.put(cookie.getName(), cookie));
            if(cookieMap.containsKey(KEY_SESSION)) {
                String sessionId = cookieMap.get(KEY_SESSION).getValue();
                byte[] decodedCookieBytes = Base64.getDecoder().decode(sessionId);
                return new String(decodedCookieBytes);
            }
        }
        return StringUtils.EMPTY;
    }

}
