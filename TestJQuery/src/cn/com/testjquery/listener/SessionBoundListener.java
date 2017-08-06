package cn.com.testjquery.listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class SessionBoundListener implements HttpSessionBindingListener
{

    @Override
    public void valueBound(HttpSessionBindingEvent event)
    {
        System.out.println("valueBound ==== " + event.getName());
        System.out.println("valueBound ==== " + event.getValue());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event)
    {
        System.out.println("valueUnbound ==== " + event.getName());
        System.out.println("valueUnbound ==== " + event.getValue());
    }

    
    
}
