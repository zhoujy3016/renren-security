package io.renren.modules.sys.service.impl;

import io.renren.dictionary.annotation.DictOperation;
import io.renren.dictionary.annotation.DictionaryCache;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.BteLessonTypeDao;
import io.renren.modules.sys.entity.BteLessonTypeEntity;
import io.renren.modules.sys.service.BteLessonTypeService;


@Service("bteLessonTypeService")
public class BteLessonTypeServiceImpl extends ServiceImpl<BteLessonTypeDao, BteLessonTypeEntity> implements BteLessonTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BteLessonTypeEntity> page = new Query<BteLessonTypeEntity>(params).getPage();
        page.setRecords(baseMapper.queryLessonTypeList(page, params));
        return new PageUtils(page);
    }

    @Override
    @DictionaryCache(dictType = DictOperation.T_EXTRA, dictKey = "gglx,zylx")
    public void insertLessonType(BteLessonTypeEntity bteLessonType) {
        this.insert(bteLessonType);
    }

    @Override
    @DictionaryCache(dictType = DictOperation.T_EXTRA, dictKey = "gglx,zylx")
    public void updateLessonType(BteLessonTypeEntity bteLessonType) {
        this.updateAllColumnById(bteLessonType);
    }

    @Override
    @DictionaryCache(dictType = DictOperation.T_EXTRA, dictKey = "gglx,zylx")
    public void deleteLessonType(Integer[] ids) {
        this.deleteBatchIds(Arrays.asList(ids));
    }
}
