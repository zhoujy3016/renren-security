package io.renren.modules.eva.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.dictionary.annotation.DictionaryCache;
import io.renren.dictionary.constants.DictOperation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.eva.dao.BteLessonTypeDao;
import io.renren.modules.eva.entity.BteLessonTypeEntity;
import io.renren.modules.eva.service.BteLessonTypeService;
import org.springframework.transaction.annotation.Transactional;


@Service("bteLessonTypeService")
public class BteLessonTypeServiceImpl extends ServiceImpl<BteLessonTypeDao, BteLessonTypeEntity> implements BteLessonTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BteLessonTypeEntity> page = new Query<BteLessonTypeEntity>(params).getPage();
        page.setRecords(baseMapper.queryLessonTypeList(page, params));
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @DictionaryCache(dictType = DictOperation.EXTRA, dictKey = {"gglx", "zylx"})
    public void insertLessonType(BteLessonTypeEntity bteLessonType) {
        this.save(bteLessonType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @DictionaryCache(dictType = DictOperation.EXTRA, dictKey = {"gglx", "zylx"})
    public void updateLessonType(BteLessonTypeEntity bteLessonType) {
        this.updateById(bteLessonType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @DictionaryCache(dictType = DictOperation.EXTRA, dictKey = {"gglx", "zylx"})
    public void deleteLessonType(Integer[] ids) {
        this.removeByIds(Arrays.asList(ids));
    }
}
