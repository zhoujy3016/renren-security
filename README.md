自定义freemarker控件select与radio，避免需要数据字典中的数据而每次从controller获取数据的冗余代码，增加数据字典缓存模块，提供若干API，实现几个数据接口之外与业务模块完全解耦，定义Aspect实现数据增、删、改操作后与缓存同步，缓存方式暂支持redis或memory。
