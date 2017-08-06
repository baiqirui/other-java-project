package cn.com.mail;

import javax.mail.*;

public class AuthenticatorWapper extends Authenticator
{
    String userName = null;
    
    String password = null;
    
    public AuthenticatorWapper()
    {
    }
    
    public AuthenticatorWapper(String username, String password)
    {
        this.userName = username;
        this.password = password;
    }
    
    protected PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(userName, password);
    }
}
