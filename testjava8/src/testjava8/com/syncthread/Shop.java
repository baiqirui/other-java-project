package testjava8.com.syncthread;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop
{
    private Random random = new Random();
    
    private String name;
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public Shop()
    {
        super();
    }
    
    public Shop(String name)
    {
        super();
        this.name = name;
    }
    
    public static void delay()
    {
        try
        {
            Thread.sleep(1000L);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 同步执行的方法
     *
     * @param product
     * @return[参数、异常说明] @return double [返回类型说明]
     * @see [类、类#方法、类#成员]
     */
    public double getPrice1(String product)
    {
        double price = calculatePrice(product);
        return price;
    }
    
    public String getPrice(String product)
    {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }
    
    private double calculatePrice(String product)
    {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
    
    private String calculatePrice1(String product)
    {
        List<String> title = Arrays.asList("Java8", "In", "Action");
        return product;
    }
    
    /**
     * 利用JDK自带的工程方法去创建Future
     *
     * @param product
     * @return[参数、异常说明] @return Future<Double> [返回类型说明]
     * @see [类、类#方法、类#成员]
     */
    public Future<Double> getASyncPrice2(String product)
    {
        CompletableFuture<Double> futurePrice = CompletableFuture.supplyAsync(() -> calculatePrice(product));
        return futurePrice;
    }
    
    public void test(String product)
    {
        CompletableFuture.supplyAsync(() ->calculatePrice1(product)).thenApply(Quote::parse);
    }
    
    public Future<Double> getASyncPrice(String product)
    {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try
            {
                double price = calculatePrice(product);
                // 如果这里报错，那么在调用get方法之后的线程将永久阻塞，比较好的方式是在这里try-catch
                String a = null;
                a.length();
                futurePrice.complete(price);
            }
            catch (Exception e)
            {
                futurePrice.completeExceptionally(e);
            }
            
        }).start();
        System.out.println("不等价格计算 继续执行1");
        System.out.println("不等价格计算 继续执行2");
        System.out.println("不等价格计算 继续执行3");
        return futurePrice;
    }
    
    public static void main(String[] args)
        throws Exception
    {
        Shop shop = new Shop();
        // System.out.println(shop.getPrice("Computer"));
        System.out.println(shop.getASyncPrice2("Computer").get());
        System.out.println("这里如果价格还没获取到，我是不会执行的");
        
    }
}
