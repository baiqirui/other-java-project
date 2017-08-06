package cn.com.testjquery.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import cn.com.testjquery.constants.WebConstant;
import cn.com.testjquery.servlet.OnlineCounter;
import cn.com.testjquery.vo.UserInfo;

/**
 * 对于登录信息时使用session存储的，所以我这里是通过实现HttpSessionAttributeListener这个接口完成的。 　　1、实现接口类，在web.xml文件中配置监听类，从而可以使该类完成其工作。
 */
public class SessionAttributeListener implements HttpSessionAttributeListener
{
    
    @Override
    public void attributeAdded(HttpSessionBindingEvent event)
    {
//        System.out.println("attributeAdded ==== " + event.getName());
//        System.out.println("attributeAdded ==== " + event.getValue());
//        System.out.println("attributeAdded ==== " + event.getSource());
        if (WebConstant.USER_INFO.equals(event.getName()))
        {
            OnlineCounter.addUser((UserInfo)event.getValue());
        }
        System.out.println("attributeAdded OnlineCounter.getOnline ==== " + OnlineCounter.getOnline());
        
       
    }
    
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event)
    {
//        System.out.println("attributeRemoved ==== " + event.getName());
//        System.out.println("attributeRemoved ==== " + event.getValue());
//        System.out.println("attributeRemoved ==== " + event.getSource());
        if (WebConstant.USER_INFO.equals(event.getName()))
        {
            OnlineCounter.deleteUser((UserInfo)event.getValue());
        }
        System.out.println("attributeRemoved OnlineCounter.getOnline ==== " + OnlineCounter.getOnline());
    }
    
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event)
    {
        
    }
    
}
