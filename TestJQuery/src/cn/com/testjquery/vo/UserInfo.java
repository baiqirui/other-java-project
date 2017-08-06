package cn.com.testjquery.vo;

import java.io.Serializable;

public class UserInfo implements Serializable
{
    
    private static final long serialVersionUID = -8033366868397486930L;
    
    private String userName;
    
    private String password;
    
    private String ip;
    
    private String loginTime;
    
    public String getIp()
    {
        return ip;
    }
    
    public void setIp(String ip)
    {
        this.ip = ip;
    }
    
    public String getLoginTime()
    {
        return loginTime;
    }
    
    public void setLoginTime(String loginTime)
    {
        this.loginTime = loginTime;
    }
    
    public UserInfo()
    {
        super();
    }
    
    public UserInfo(String userName, String password)
    {
        super();
        this.userName = userName;
        this.password = password;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    @Override
    public String toString()
    {
        return "UserInfo [userName=" + userName + ", password=" + password + ", ip=" + ip + ", loginTime=" + loginTime
            + "]";
    }
}
