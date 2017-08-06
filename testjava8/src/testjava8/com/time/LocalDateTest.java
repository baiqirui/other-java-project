package testjava8.com.time;

import java.time.LocalDateTime;

public class LocalDateTest
{
    public static void main(String[] args)
    {
        LocalDateTime date = LocalDateTime.parse("2014-03-18 17:27:55");
        System.out.println(date);
    }
}
