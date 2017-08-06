package cn.com.memcache;

import java.util.Date;
import java.util.Map;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/**
 * <功能详细描述>
 * 
 * @author lenovo
 * @version [版本号, Feb 13, 2014]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MemcacheManager
{
    // 构建缓存客户端
    private static MemCachedClient cachedClient;// 单例模式实现客户端管理类
    
    private static MemcacheManager INSTANCE = new MemcacheManager();
    
    private MemcacheManager()
    {
        cachedClient = new MemCachedClient("myMemcache");
        // 获取连接池实例
        SockIOPool pool = SockIOPool.getInstance("myMemcache");// 设置缓存服务器地址，可以设置多个实现分布式缓存
        pool.setServers(new String[] {"192.168.102.241:11215"});// 设置初始连接5
        pool.setInitConn(5);
        // 设置最小连接5
        pool.setMinConn(5);
        // 设置最大连接250
        pool.setMaxConn(250);
        // 设置每个连接最大空闲时间3个小时
        pool.setMaxIdle(1000 * 60 * 60 * 3);
        pool.setMaintSleep(30);
        pool.setNagle(false);
        pool.setSocketTO(3000);
        pool.setSocketConnectTO(0);
        pool.initialize();
    }
    
    /**
     * * 获取缓存管理器唯一实例 *
     * 
     * @return
     */
    public static MemcacheManager getInstance()
    {
        return INSTANCE;
    }
    
    /**
     * 向memcached保存数据时可以指定期限(秒)。不指定期限时，memcached按照LRU算法保存数据。 这三个方法的区别如下： add 仅当存储空间中不存在键相同的数据时才保存 replace
     * 仅当存储空间中存在键相同的数据时才保存 set 与add和replace不同，无论何时都保存
     */
    public void add(String key, Object value)
    {
        cachedClient.set(key, value);
    }
    
    public void add(String key, Object value, int milliseconds)
    {
        cachedClient.set(key, value, milliseconds);
    }
    
    public void remove(String key)
    {
        cachedClient.delete(key);
    }
    
    public boolean delete(String key, Date expiry)
    {
        return cachedClient.delete(key, expiry);
    }
    
    public void update(String key, Object value, int milliseconds)
    {
        cachedClient.replace(key, value, milliseconds);
    }
    
    public void update(String key, Object value)
    {
        cachedClient.replace(key, value);
    }
    
    public Object get(String key)
    {
        return cachedClient.get(key);
    }
    
    /**
     * 获取多个值,以Map形式返回;
     * 
     * @param keys
     * @return[参数、异常说明]
     * @return Map [返回类型说明]
     * @see [类、类#方法、类#成员]
     */
    public Map getMuti(String... keys)
    {
        return cachedClient.getMulti(keys);
    }
    
    /**
     * 增加一个指定键的计数器+value
     * 
     * @param key
     * @param value
     * @return
     */
    public long incr(String key, long value)
    {
        return cachedClient.incr(key, value);
    }
    
    public long decr(String key)
    {
        return cachedClient.decr(key);
    }
    
    public long decr(String key, long value)
    {
        return cachedClient.decr(key, value);
    }
    
    public long getCounter(String key)
    {
        return cachedClient.getCounter(key);
    }
    
    public void flushAll()
    {
        cachedClient.flushAll();
    }
}