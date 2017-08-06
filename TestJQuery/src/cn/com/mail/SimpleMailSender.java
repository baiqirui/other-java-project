package cn.com.mail;

import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 简单邮件（不带附件的邮件）发送器
 */
public class SimpleMailSender
{
    /**
     * 以文本格式发送邮件
     * 
     * @param mailInfo 待发送的邮件的信息
     */
    private static String userName = PropertyUtil.getPropertyValue("UserName");
    
    private static String password = PropertyUtil.getPropertyValue("Password");
    
    private static String fromAddress = PropertyUtil.getPropertyValue("FromAddress");
    
    public static Properties getProperties()
    {
        Properties p = new Properties();
        p.put("mail.smtp.host", PropertyUtil.getPropertyValue("mail.smtp.host"));
        p.put("mail.smtp.port", PropertyUtil.getPropertyValue("mail.smtp.port"));
        p.put("mail.smtp.auth", PropertyUtil.getPropertyValue("mail.smtp.auth") == null ? "false"
            : PropertyUtil.getPropertyValue("mail.smtp.auth"));
        return p;
    }
    
    public static boolean sendTextMail(MailSenderMsg mailInfo)
    {
        // 判断是否需要身份认证
        AuthenticatorWapper authenticator = null;
        Properties pro = getProperties();
        if (PropertyUtil.getPropertyValue("mail.smtp.auth") != null
            && "true".equals(PropertyUtil.getPropertyValue("mail.smtp.auth")))
        {
            // 如果需要身份认证，则创建一个密码验证器
            if (mailInfo.getUserName() != null && mailInfo.getPassword() != null)
            {
                authenticator = new AuthenticatorWapper(mailInfo.getUserName(), mailInfo.getPassword());
            }
            else
            {
                authenticator = new AuthenticatorWapper(userName, password);
            }
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try
        {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            
            Address from = null;
            if (mailInfo.getFromAddress() != null)
            {
                from = new InternetAddress(mailInfo.getFromAddress());
            }
            else
            {
                from = new InternetAddress(fromAddress);
            }
            
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(mailInfo.getToAddress());
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容
            String mailContent = mailInfo.getContent();
            mailMessage.setText(mailContent);
            
            Enumeration efile = mailInfo.getFile().elements();
            if (efile.hasMoreElements())
            {
                // 后面的BodyPart将加入到此处创建的Multipart中
                Multipart mp = new MimeMultipart();
                
                // 检查序列中是否还有更多的对象\r
                while (efile.hasMoreElements())
                {
                    MimeBodyPart mbp = new MimeBodyPart();
                    // 选择出每一个附件名\r
                    String filename = efile.nextElement().toString();
                    // 得到数据源
                    FileDataSource fds = new FileDataSource(filename);
                    // 得到附件本身并至入BodyPart
                    mbp.setDataHandler(new DataHandler(fds));
                    // 得到文件名同样至入BodyPart
                    mbp.setFileName(fds.getName());
                    mp.addBodyPart(mbp);
                }
                mailMessage.setContent(mp);
            }
            
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        }
        catch (MessagingException ex)
        {
            
            ex.printStackTrace();
        }
        return false;
    }
    
    /**
     * 以HTML格式发送邮件
     * 
     * @param mailInfo 待发送的邮件信息
     */
    public static boolean sendHtmlMail(MailSenderMsg mailInfo)
    {
        // 判断是否需要身份认证
        AuthenticatorWapper authenticator = null;
        Properties pro = getProperties();
        // 如果需要身份认证，则创建一个密码验证器
        if (PropertyUtil.getPropertyValue("mail.smtp.auth") != null
            && "true".equals(PropertyUtil.getPropertyValue("mail.smtp.auth")))
        {
            // 如果需要身份认证，则创建一个密码验证器
            if (mailInfo.getUserName() != null && mailInfo.getPassword() != null)
            {
                authenticator = new AuthenticatorWapper(mailInfo.getUserName(), mailInfo.getPassword());
            }
            else
            {
                authenticator = new AuthenticatorWapper(userName, password);
            }
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try
        {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = null;
            if (mailInfo.getFromAddress() != null)
            {
                from = new InternetAddress(mailInfo.getFromAddress());
            }
            else
            {
                from = new InternetAddress(fromAddress);
            }
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(mailInfo.getToAddress());
            // Message.RecipientType.TO属性表示接收者的类型为TO
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart mbp = new MimeBodyPart();
            // 设置HTML内容
            mbp.setContent(mailInfo.getContent(), "text/html; charset=UTF-8");
            mainPart.addBodyPart(mbp);
            
            Enumeration efile = mailInfo.getFile().elements();
            // 检查序列中是否还有更多的对象\r
            while (efile.hasMoreElements())
            {
                mbp = new MimeBodyPart();
                // 选择出每一个附件名\r
                String filename = efile.nextElement().toString();
                // 得到数据源
                FileDataSource fds = new FileDataSource(filename);
                // 得到附件本身并至入BodyPart
                mbp.setDataHandler(new DataHandler(fds));
                // 得到文件名同样至入BodyPart
                mbp.setFileName(fds.getName());
                mainPart.addBodyPart(mbp);
            }
            
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            // 发送邮件
            
            Transport.send(mailMessage);
            return true;
        }
        catch (MessagingException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }
}