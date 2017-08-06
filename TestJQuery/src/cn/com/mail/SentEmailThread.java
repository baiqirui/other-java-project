package cn.com.mail;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;

/**
 * 
 * @author Jiangwp
 * 
 */
public class SentEmailThread extends Thread
{
    
    private static String AddressCode = PropertyUtil.getPropertyValue("AddressCode");
    
    private String projectName;
    
    private String message;
    
    /** 本机IP地址 */
    private static String ipAddr;
    
    static
    {
        try
        {
            InetAddress addr = InetAddress.getLocalHost();
            ipAddr = addr.getLocalHost().toString();
        }
        catch (UnknownHostException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public SentEmailThread(String projectName, String message)
    {
        this.projectName = projectName;
        this.message = message;
    }
    
    @Override
    public void run()
    {
        String toUserName = PropertyUtil.getPropertyValue(projectName + "_ToAddress");
        String[] userNames = toUserName.split(";");
        for (String userName : userNames)
        {
            MailSenderMsg mailInfo = new MailSenderMsg();
            mailInfo.setToAddress(userName);
            mailInfo.setSubject(MessageFormat.format(AddressCode, ipAddr, "【" + projectName + "】"));
            mailInfo.setContent(message);
            SimpleMailSender.sendHtmlMail(mailInfo);
        }
    }
    
    //
    // /**
    // *
    // * @author Jiangwp
    // *
    // */
    // public static void main(String[] args){
    //    	
    // SentEmailThread server = new SentEmailThread("XXXX", "test");
    // server.start();
    //   
    // }
    
}
