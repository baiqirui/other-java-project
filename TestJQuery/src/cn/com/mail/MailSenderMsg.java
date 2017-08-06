package cn.com.mail;
 import java.util.Vector;
 public class MailSenderMsg {   
     // 邮件发送者的地址   
     private String fromAddress = null;   
     // 登陆邮件发送服务器的用户名和密码   
     private String userName = null;   
     private String password = null;   
     // 邮件接收者的地址   
     private String toAddress;  
     // 邮件主题   
     private String subject;   
     // 邮件的文本内容   
     private String content;   
     
     // 邮件附件的文件名   
     private Vector file = new Vector();
    
     public String getFromAddress() {   
       return fromAddress;   
     }   
     public void setFromAddress(String fromAddress) {   
       this.fromAddress = fromAddress;   
     }  
     public String getPassword() {   
       return password;   
     }  
     public void setPassword(String password) {   
       this.password = password;   
     }  
     public String getToAddress() {   
       return toAddress;   
     }   
     public void setToAddress(String toAddress) {   
       this.toAddress = toAddress;   
     }   
     public String getUserName() {   
       return userName;   
     }  
     public void setUserName(String userName) {   
       this.userName = userName;   
     }  
     public String getSubject() {   
       return subject;   
     }  
     public void setSubject(String subject) {   
       this.subject = subject;   
     }  
     public String getContent() {   
       return content;   
     }  
     public void setContent(String textContent) {   
       this.content = textContent;   
     }
     public Vector getFile() {
        return file;
     }
     public void setFile(Vector file) {
        this.file = file;
     }  
     
 }   