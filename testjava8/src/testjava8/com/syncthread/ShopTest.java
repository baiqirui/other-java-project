package testjava8.com.syncthread;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
public class ShopTest
{
    List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
        new Shop("LetsSaveBig"),
        new Shop("MyFavoriteShop"),
        new Shop("BuyItAll"));
    
    public List<String> findPrices(String product) throws Exception
    {
        //初级版本使用普通的stream
//        return shops.stream()
//            .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
//            .collect(toList());
        
        //中极版本，使用并行流
//        return shops.parallelStream()
//            .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
//            .collect(toList());
        
        //高级版本，使用furure+并行流
//            CompletableFuture<List<String>> futurePrices = CompletableFuture.supplyAsync(() ->
//                shops.parallelStream()
//              .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
//              .collect(toList()));
        
            
            
//        List<CompletableFuture<String>> futurePrices1 = shops.parallelStream()
//             .map(shop -> CompletableFuture.supplyAsync(
//                 () ->String.format("%s price is %.2f", 
//                     shop.getName(), shop.getPrice(product))))
//          .collect(toList());
        
        
        Executor executor = Executors.newFixedThreadPool(60);
        List<CompletableFuture<String>> futurePrices1 = shops.parallelStream()
            .map(shop -> CompletableFuture.supplyAsync(
                () ->String.format("%s price is %.2f", 
                    shop.getName(), shop.getPrice(product)), executor))
         .collect(toList());
        
        return futurePrices1.parallelStream().map(CompletableFuture::join).collect(toList());
//          return   futurePrices.get();
    }
    public List<Object> findPrices1(String product) 
    {
        //第一种非并行流方案
//        return shops.stream().map(shop -> shop.getPrice(product)).map(Quote::parse).map(Discount::applyDiscount).collect(toList());
        //第二种并行流方案
        return shops.parallelStream().map(shop -> shop.getPrice(product)).map(Quote::parse).map(Discount::applyDiscount).collect(toList());
        //第三种方式(使用 CompletableFuture 提供的特性，以异步方式重新实现 findPrices 方法)
//        Executor executor = Executors.newFixedThreadPool(12);
//        List<CompletableFuture<Object>> priceFutures =
//        shops.stream()
//        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
//        .map(future -> future.thenApply(Quote::parse))
//        .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor))).collect(toList());
//        return priceFutures.stream().map(CompletableFuture::join).collect(toList());
    }
    
    /**
     * 另一种场景(将两个 CompletableFuture 对象整合起来，无论它们是否存在依赖)
     *
     *场景举例：比较常见的情况是，你需要将两个完全不相干的 CompletableFuture 对象的结果整合起来，
     *                 而且你也不希望等到第一个任务完全结束才开始第二项任务
     *有一家商店提供的价格是以欧元（EUR）计价的，但是你希望以美元的方式提供给你的客户你可以用异步的方式向商店查询指定商品的价格，
     *同时从远程的汇率服务那里查到欧元和美元之间的汇率。当二者都结束时，再将这两个结果结合起来，用返回的商品价格乘以当时的汇率，得到以美元计价的商品价格
     * @param product
     * @return[参数、异常说明]
     * @return List<Object> [返回类型说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Object> findPrices2(String product) 
    {
        //第一种非并行流方案
//        return shops.stream().map(shop -> shop.getPrice(product)).map(Quote::parse).map(Discount::applyDiscount).collect(toList());
        //第二种并行流方案
        return shops.parallelStream().map(shop -> shop.getPrice(product)).map(Quote::parse).map(Discount::applyDiscount).collect(toList());
        //第三种方式(使用 CompletableFuture 提供的特性，以异步方式重新实现 findPrices 方法)
//        Executor executor = Executors.newFixedThreadPool(12);
//        List<CompletableFuture<Object>> priceFutures =
//        shops.stream()
//        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
//        .map(future -> future.thenApply(Quote::parse))
//        .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
//        .collect(toList());
//        .map(priceFuture -> priceFuture.thenCombine(CompletableFuture.supplyAsync(() -> ExchangeService.getRate("EUR", "USD")), (price, rate) -> price * rate))
        
//        return priceFutures.stream().map(CompletableFuture::join).collect(toList());
    }
    
    
    /**
     * 另一种场景(响应 CompletableFuture 的 completion 事件)
     *
     *场景举例：现实世界中，你的应用访问各个远程服务时很可能遭遇无法预知的延迟，触发的原因多种多样，
     *                 从服务器的负荷到网络的延迟，有些甚至是源于远程服务如何评估你应用的商业价值，
     *                 即可能相对于其他的应用，你的应用每次查询的消耗时间更长
     * @param product
     * @return[参数、异常说明]
     * @return List<Object> [返回类型说明]
     * @see [类、类#方法、类#成员]
     */
    public  Stream<CompletableFuture<String>> findPricesStream(String product) 
    {
//        Executor executor = Executors.newFixedThreadPool(12);
//        return 
//        shops.stream()
//        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
//        .map(future -> future.thenApply(Quote::parse))
//        .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
        return null;
    }
    
    
    public static void main(String[] args) throws Exception
    {
//        System.out.println( Runtime.getRuntime().availableProcessors());
        ShopTest t = new ShopTest();
//        long start = System.nanoTime();
//        System.out.println(t.findPrices3("car"));
//        long duration = (System.nanoTime() - start) / 1_000_000;
//        System.out.println("Done in " + duration + " msecs");
        
        long start = System.nanoTime();
        /**
         * allOf 工厂方法接收一个由 CompletableFuture 构成的数组，数组中的所有 Completable-
Future 对象执行完成之后，它返回一个 CompletableFuture<Void> 对象。这意味着，如果你需
要等待最初 Stream 中的所有 CompletableFuture 对象执行完毕，对 allOf 方法返回的
CompletableFuture 执行 join 操作是个不错的主意。这个方法对“最佳价格查询器”应用也是
有用的，因为你的用户可能会困惑是否后面还有一些价格没有返回，使用这个方法，你可以在执
行完毕之后打印输出一条消息“All shops returned results or timed out”。
然而在另一些场景中，你可能希望只要 CompletableFuture 对象数组中有任何一个执行完
毕就不再等待，比如，你正在查询两个汇率服务器，任何一个返回了结果都能满足你的需求。在
这种情况下，你可以使用一个类似的工厂方法 anyOf 。该方法接收一个 CompletableFuture 对象
构成的数组，返回由第一个执行完毕的 CompletableFuture 对象的返回值构成的 Completable-
Future<Object> 。
         */
        
        CompletableFuture[] futures = t.findPricesStream("myPhone27S")
        .map(f -> f.thenAccept(
        s -> System.out.println(s + " (done in " +
        ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
        .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in "
        + ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }
}
