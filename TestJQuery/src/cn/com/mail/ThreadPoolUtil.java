package cn.com.mail;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 线程池工具类
 * 
 * @author zengj
 * @version [版本号, 2012-2-19]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ThreadPoolUtil
{
    /** 单例 */
    private static ThreadPoolUtil threadPoolUtil = new ThreadPoolUtil();
    
    private ThreadPoolExecutor executor;
    
    /** 池中最小线程数 */
    private int corePoolSize;
    
    /** 池中最大线程数 */
    private int maximumPoolSize;
    
    /** 线程最多允许空闲的时间(s) */
    private int keepAliveTime;
    
    /** 最大任务缓存队列大小 */
    private int maxCacheSize;
    
    /** 任务缓存队列 */
    private BlockingQueue<Runnable> runnables;
    
    /** 当前池是否已经初始化 */
    private boolean initailized;
    
    private Object[] initLock = new Object[0];
    
    private ThreadPoolUtil()
    {
        
    }
    
    /**
     * 初始化
     * 
     * @see [类、类#方法、类#成员]
     */
    private void init()
    {
        synchronized (initLock)
        {
            if (!initailized)
            {
                corePoolSize = 50;
                maximumPoolSize = 200;
                keepAliveTime = 3000;
                maxCacheSize = 50;
                runnables = new ArrayBlockingQueue<Runnable>(maxCacheSize);
                // 实例化线程池
                executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                    runnables, new ThreadPoolExecutor.CallerRunsPolicy());
                initailized = true;
            }
        }
    }
    
    private void doWork(Runnable runnable)
    {
        if (!initailized)
        {
            this.init();
        }
        executor.execute(runnable);
    }
    
    /**
     * 
     * 在线程池中运行指定的任务
     * 
     * @param runnable
     * @see [类、类#方法、类#成员]
     */
    public static void executeRunner(Runnable runnable)
    {
        executeRunner(runnable, false);
    }
    
    /**
     * 
     * 在线程池中运行指定的任务
     * 
     * @param runnable
     * @see [类、类#方法、类#成员]
     */
    public static void executeRunner(Runnable runnable, boolean synchr)
    {
        if (synchr)
        {
            runnable.run();
        }
        else
        {
            threadPoolUtil.doWork(runnable);
        }
    }
}
