package io.renren.common.component;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * class:
 * description：
 * author:zhoujy
 * date:2018/7/24
 */

@Component
public class DictionaryDirective implements TemplateDirectiveModel {

    @Autowired
    private DictComponent dictComponent;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String id = params.get("id").toString();
        List dictList = dictComponent.getDictCacheDataByType(id);
        env.setVariable("dictList", getBeansWrapper().wrap(dictList));
        body.render(env.getOut());
    }

    public static BeansWrapper getBeansWrapper() {
        BeansWrapper beansWrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_28).build();
        return beansWrapper;
    }
}
