package io.renren.dictionary.component;

import io.renren.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 获取数据字典接口
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-04-08
 */
@RestController
@RequestMapping("dictionary/")
public class DictionaryController {
    @Autowired
    private DictComponent dictComponent;

    @GetMapping("keys/{keys}")
    public R getDictionaries(@PathVariable String keys) {
        Map<String, Object> dictMaps = dictComponent.getDictCacheDataByTypes(keys);
        return R.ok(dictMaps);
    }
}
