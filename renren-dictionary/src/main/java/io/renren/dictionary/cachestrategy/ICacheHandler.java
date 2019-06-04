package io.renren.dictionary.cachestrategy;

/**
 * cache操作接口
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2019-03-20 15:30
 */
public interface ICacheHandler<K, V> {
    /**
     * 初始化
     */
    void initialize();
    /**
     * 设置
     * @param key
     * @param value
     */
    void set(K key, V value);

    /**
     * 取得
     * @param key
     * @return
     */
    V get(K key);
}
