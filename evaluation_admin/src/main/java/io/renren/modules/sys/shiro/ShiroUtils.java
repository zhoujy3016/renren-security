/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.renren.modules.sys.shiro;

import io.renren.common.exception.RRException;
import io.renren.common.utils.RedisKeys;
import io.renren.modules.sys.entity.SysUserEntity;
import oracle.jdbc.proxy.annotation.Post;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Shiro工具类
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月12日 上午9:49:19
 */
@Component
public class ShiroUtils {

	@Autowired
	private RedisTemplate redisTemplate;

	private static RedisTemplate staticRedisTemplate;

	@PostConstruct
	public void init() {
		staticRedisTemplate = this.redisTemplate;
	}

	/**  加密算法 */
	public final static String hashAlgorithmName = "SHA-256";
	/**  循环次数 */
	public final static int hashIterations = 16;

	public static String sha256(String password, String salt) {
		return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
	}

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static SysUserEntity getUserEntity() {
		return (SysUserEntity)SecurityUtils.getSubject().getPrincipal();
	}

	public static Long getUserId() {
		return getUserEntity().getUserId();
	}
	
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}
	
	public static void login(String username, String password) {
		Subject subject = getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		subject.login(token);
	}

	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
	
	public static String getKaptcha(String key) {
		Object kaptcha = getSessionAttribute(key);
		if(kaptcha == null){
			throw new RRException("验证码已失效");
		}
		getSession().removeAttribute(key);
		return kaptcha.toString();
	}

	/**
	 * 从redis session中取得SysUserEntity对象
	 * @return
	 */
	public static SysUserEntity getUserEntityFromRedisSession(String sessionId) {
		Map<Object, Object> map = staticRedisTemplate.opsForHash().entries(RedisKeys.getShiroSessionKey(sessionId));
		SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) map.get("sessionAttr:" + DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
		return (SysUserEntity) simplePrincipalCollection.getPrimaryPrincipal();
	}
	// 调用方法
	// SysUserEntity sysUserEntity = ShiroUtils.getUserEntityFromRedisSession(CookieUtils.getSessionId(request));
}
