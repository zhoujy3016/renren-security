package io.renren.dictionary.aspect;

import io.renren.dictionary.annotation.DictionaryCache;
import io.renren.dictionary.component.DictComponent;
import io.renren.dictionary.config.DictYmlConfig;
import io.renren.common.exception.RRException;
import io.renren.dictionary.service.ExtraDictService;
import io.renren.common.utils.MapUtils;
import io.renren.dictionary.utils.DictConstant;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private ExtraDictService extraDictService;

    @Autowired(required = false)
    private DictYmlConfig dictYmlConfig;

    @Autowired
    DictComponent dictComponent;

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
        // 数据字典类型：常规，额外
        DictConstant.DictOperation dicType = dataFilter.dictType();
        if(param != null) {
            if (dicType == DictConstant.DictOperation.T_NORMAL) {
                updateDictionaryCache(dataFilter, param);
            } else { // 自定义数据字典类型(DictOperationType.T_EXTRA)
                updateExtraDictCache(dataFilter);
            }
        } else {
            throw new RRException("数据字典操作接口参数，不能为NULL");
        }

    }

    /**
     * 更新常规数据字典缓存
     * @param dataFilter
     * @param param
     */
    private void updateDictionaryCache(DictionaryCache dataFilter, Object param) {
        DictConstant.DictOperation operation = dataFilter.operation();
        switch (operation) {
            // 插入
            case OP_INSERT:
            // 更新
            case OP_UPDATE: dictionaryAddOrUpdateHandler(param);
                break;
            // 删除
            case OP_DELETE: dictionaryDeleteHandler(param);
                break;
            default:
                break;
        }
    }

    /**
     * OP_INSERT或者OP_UPDATE时，调用
     * @param param
     */
    private void dictionaryAddOrUpdateHandler(Object param) {
        // 将实体类型转为map类型
        Map<String, Object> dictEntity = MapUtils.transEntity2Map(param);
        // 将该类型重新载入缓存中
        dictComponent.reloadDictCacheData((String) dictEntity.get(DictConstant.DICT_TYPE));
    }

    /**
     * OP_DELETE时，调用
     * @param param
     */
    private void dictionaryDeleteHandler(Object param) {
        Long[] ids = (Long[]) param;
        dictComponent.reloadDictCacheData(ids);
    }

    /**
     * 更新额外的数据字典缓存
     * @param dataFilter
     */
    private void updateExtraDictCache(DictionaryCache dataFilter) {
        String[] arrKeys = dataFilter.dictKey();
        if(ArrayUtils.isEmpty(arrKeys)) {
            throw new RRException("数据字典参数为NULL，请指定key");
        }
        Map<String, Object> extraMap = new HashMap<>(10);
        // 配置文件中取得sql
        Map<String, String> sqlMap = dictYmlConfig.getExtraDict();
        Arrays.stream(arrKeys)
                .map(String::trim)
                .forEach(key-> extraMap.put(key, extraDictService.executeQuery(sqlMap.get(key))));
        dictComponent.reloadExtraCacheData(extraMap);
    }
}
