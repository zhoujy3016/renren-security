package io.renren.common.component;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import io.renren.common.config.DictConfig;
import io.renren.modules.sys.entity.SysDictEntity;
import io.renren.modules.sys.service.SysDictService;

@Component
public class AppRunner implements ApplicationRunner{

    @Autowired
    private SysDictService sysDictService;
    
    @Autowired
    private DictConfig dictConfigBean;
    
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// 加载数据字典
		this.makeDictCache();
	}
	
	
	/**
	 * 将数据字典所有数据分类封装到dictConfigBean的map中
	 */
	private void makeDictCache() {
		Map<String, List<SysDictEntity>> map = dictConfigBean.getDictMap();
		// 取得数据字典所有类型
		List<SysDictEntity> typeList = sysDictService.getSysDictEntityGroupByType();
		for(int i = 0; i < typeList.size(); i++) {
		String type = typeList.get(i).getType();
		// 根据类型查询每种数据字典，添加到map中
		List<SysDictEntity> dictList = sysDictService.getSysDictEntity(type);
		dictList.add(0, new SysDictEntity());
		map.put(type, dictList);
	}
}
	

}
