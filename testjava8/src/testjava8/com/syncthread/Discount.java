package testjava8.com.syncthread;

import java.util.Random;

public class Discount
{
    public enum Code
    {
        优免(0), 微信98折(5), 支付宝95折(10), 老板熟人85折(15), 中奖全免(20);
        
        private final int percentage;
        
        Code(int percentage)
        {
            this.percentage = percentage;
        }
    }
    
    private static final Random random = new Random();
    
    public static void randomDelay()
    {
        int delay = 500 + random.nextInt(2000);
        try
        {
            Thread.sleep(delay);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    public static String applyDiscount(Quote quote)
    {
        return String.format(quote.getShopName() + " price is %.2f",
            Discount.apply(quote.getPrice(), quote.getDiscountCode()));
    }
    
    private static double apply(double price, Code code)
    {
        randomDelay();
        // return format(price * (100 - code.percentage) / 100);
        return price * (100 - code.percentage) / 100;
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
}