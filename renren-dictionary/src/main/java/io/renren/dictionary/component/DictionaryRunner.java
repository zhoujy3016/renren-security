package io.renren.dictionary.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class DictionaryRunner implements CommandLineRunner {
    @Autowired
    private DictComponent dictComponent;

    @Override
    public void run(String... args) throws Exception {
        dictComponent.initDictCacheData();
    }
}
