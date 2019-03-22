package io.renren.dictionary.aspect;

import io.renren.common.utils.SpringContextUtils;
import io.renren.dictionary.annotation.DictionaryCache;
import io.renren.common.exception.RRException;
import io.renren.dictionary.aspect.strategy.IDictModifyHandler;
import io.renren.dictionary.constants.DictOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 数据字典切面处理类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2018-07-12 22:00
 */
@Aspect
@Component
public class DictionaryCacheAspect {

    @Pointcut("@annotation(io.renren.dictionary.annotation.DictionaryCache)")
    public void dictUpdatePointCut() {

    }

    /**
     * 目标方法更新之后对数据字典缓存进行更新操作
     * 目标方法抛异常之后不调用AfterReturning
     * @param point
     * @throws Throwable
     */
    @AfterReturning("dictUpdatePointCut()")
    public void dataFilter(JoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        DictionaryCache dataFilter = signature.getMethod().getAnnotation(DictionaryCache.class);
        Object param = point.getArgs()[0];
        // 数据字典类型：常规，自定义
        DictOperation dicType = dataFilter.dictType();
        if(param != null) {
            String beanName;
            if (dicType == DictOperation.T_NORMAL) {
                beanName = IDictModifyHandler.DICT_MODIFY_HANDLER;
            } else { // 自定义数据字典类型(DictOperationType.T_EXTRA)
                beanName = IDictModifyHandler.EXTRA_DICT_MODIFY_HANDLER;
            }
            IDictModifyHandler handler = SpringContextUtils.getBean(beanName, IDictModifyHandler.class);
            // 对缓存进行同步更新操作
            handler.updateDictionaryCache(dataFilter, param);
        } else {
            throw new RRException("数据字典操作接口参数，不能为NULL");
        }
    }
}
