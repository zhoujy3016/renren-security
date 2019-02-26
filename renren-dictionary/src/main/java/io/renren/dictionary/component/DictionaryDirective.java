package io.renren.dictionary.component;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.*;
import io.renren.dictionary.utils.DictConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 用户Token
 *
 * @author zhoujy
 * @email
 * @date 2017-07-24
 */

@Component
public class DictionaryDirective implements TemplateDirectiveModel {
    @Autowired
    private freemarker.template.Configuration configuration;

    @Autowired
    private DictComponent dictComponent;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String id = params.get(DictConstant.PROPERTY_ID).toString();
        List dictList = dictComponent.getDictCacheDataByType(id);
        env.setVariable(DictConstant.KEY_DICTLIST, getBeansWrapper().wrap(dictList));
        body.render(env.getOut());
    }

    public static BeansWrapper getBeansWrapper() {
        BeansWrapper beansWrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_28).build();
        return beansWrapper;
    }

    @PostConstruct
    public void setSharedVariable() {
        // 标签名
        configuration.setSharedVariable(DictConstant.TAG_CACHE, this);
    }
}
