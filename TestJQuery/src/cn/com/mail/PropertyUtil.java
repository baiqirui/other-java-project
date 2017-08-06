/*
 * @文件名：  PropertyUtil.java
 * @系统名：  Archive-V1.0
 * @开发单位：信安软件（中国）有限公司
 * @版权：    本文件版权归属  信安软件
 * 
 * @作者       江文平
 * @开发日期:  6 9, 2010
 * @JDK版本    jdk1.6
 *
 * @修改内容：
 * @最后修改时间： Mar 25, 2010
 * @修改人：   江文平
 * @复审人：   江文平
 */
package cn.com.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil
{
    private static String EMAIL_CONFIG = "email_config.properties";
    
    private static Properties properties = new Properties();
    
    static
    {
        InputStream in = null;
        try
        {
            in = PropertyUtil.class.getClassLoader().getResourceAsStream(EMAIL_CONFIG);
            properties.load(in);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            in = null;
        }
    }
    
    public static String getPropertyValue(String key)
    {
        String propertyValue = (String)properties.get(key);
        return propertyValue;
    }
    
    public static void setPropertyValue(String key, String value)
    {
        properties.setProperty(key, value);
    }
    
    public static void main(String args[])
    {
        System.out.println(PropertyUtil.getPropertyValue("mail.smtp.hostw"));
    }
    
}
