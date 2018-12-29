官网：https://www.renren.io/guide/security
增加module renren-dictionary ：
  启动加载数据字典到redis的功能，加载分为两部分：
  一、数据字典表
  二、任意需要做数据字典的业务表
  增加数据字典增删改切面更新。
  页面使用freemarker macro 直接读取redis数据。

