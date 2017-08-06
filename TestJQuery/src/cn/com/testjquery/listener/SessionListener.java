package cn.com.testjquery.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener
{

    @Override
    public void sessionCreated(HttpSessionEvent event)
    {
//        System.out.println("sessionCreated event.getSource ==== " + event.getSource());
//        System.out.println("sessionCreated event.getSession ==== " + event.getSession());
        
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event)
    {
//        System.out.println("sessionDestroyed event.getSource ==== " + event.getSource());
//        System.out.println("sessionDestroyed event.getSession ==== " + event.getSession());
    }
    
    
}
