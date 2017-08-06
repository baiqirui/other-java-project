package cn.com.mail;

/**
 * 
 * @author Jiangwp
 * 
 */
public class SentEmailServer
{
    
    public static void main(String[] args)
    {
        SentEmailThread server = new SentEmailThread("teachportal", "portal");
        
        server.start();
    }
}
