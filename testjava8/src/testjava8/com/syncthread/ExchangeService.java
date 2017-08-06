package testjava8.com.syncthread;

import java.util.Random;

public class ExchangeService
{
    
    private static Random random = new Random();
    
    public static double getRate(String m1, String m2)
    {
        return random.nextDouble() / 100 ;
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