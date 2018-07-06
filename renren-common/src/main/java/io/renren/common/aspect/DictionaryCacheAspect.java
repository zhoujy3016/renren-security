package io.renren.common.aspect;

import io.renren.common.annotation.DictOperation;
import io.renren.common.annotation.DictionaryCache;
import io.renren.common.component.DictComponent;
import io.renren.common.config.DictYmlConfig;
import io.renren.common.entity.SysDictEntity;
import io.renren.common.exception.RRException;
import io.renren.common.service.ExtraDictService;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据字典切面操作类
 */

@Aspect
@Component
public class DictionaryCacheAspect {

    @Autowired
    private ExtraDictService extraDictService;

    @Autowired
    private DictYmlConfig dictYmlConfig;

    @Autowired
    DictComponent dictComponent;

    @Pointcut("@annotation(io.renren.common.annotation.DictionaryCache)")
    public void dataFilterCut() {

    }

    @After("dataFilterCut()")
    public void dataFilter(JoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        DictionaryCache dataFilter = signature.getMethod().getAnnotation(DictionaryCache.class);
        Object param = point.getArgs()[0];
        // 数据字典类型：常规，额外
        DictOperation dicType = dataFilter.dictType();
        if(param != null) {
            if (dicType == DictOperation.T_NORMAL) {
                updateDictionaryCache(dataFilter, param);
            } else { // 自定义数据字典类型(DictOperationType.T_EXTRA)
                updateExtraDictCache(dataFilter, param);
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
        DictOperation operation = dataFilter.operation();
        switch (operation) {
            case OP_INSERT:
            case OP_UPDATE:
                    SysDictEntity sysDictEntity = (SysDictEntity) param;
                    String type = sysDictEntity.getType();
                    // 将该类型重新载入缓存中
                    dictComponent.reloadDictCacheData(type);
                break;
            case OP_DELETE:
                    Long[] ids = (Long[]) param;
                    dictComponent.reloadDictCacheData(ids);
                break;
        }

    }

    /**
     * 更新额外的数据字典缓存
     * @param dataFilter
     * @param param
     */
    private void updateExtraDictCache(DictionaryCache dataFilter, Object param) {
        String strKyes = dataFilter.dictKey();
        if(StringUtils.isEmpty(strKyes)) {
            throw new RRException("数据字典参数为NULL，请指定key");
        }
        String[] arrKeys = strKyes.split(",");
        Map<String, Object> extraMap = new HashMap<>();
        // 配置文件中取得sql
        Map<String, String> sqlMap = dictYmlConfig.getExtraDict();
        for(String key:arrKeys) {
            key = key.trim();
            // 根据key重新查询，并放入map中
            extraMap.put(key, extraDictService.excuteQuery(sqlMap.get(key)));
        }
        dictComponent.loadExtraDictDataToRedis(extraMap);
    }
}
