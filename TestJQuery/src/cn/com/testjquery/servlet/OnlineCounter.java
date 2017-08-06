package cn.com.testjquery.servlet;

import java.util.ArrayList;
import java.util.List;

import cn.com.testjquery.vo.UserInfo;

public class OnlineCounter
{
    private static List<UserInfo> userList = new ArrayList<UserInfo>();
    
    private OnlineCounter()
    {
    }
    
    public static void addUser(UserInfo userInfo)
    {
        if (null == userInfo)
        {
            return;
        }
        
        for (int i = 0; i < userList.size(); i++)
        {
            UserInfo onLineUser = userList.get(i);
            if (onLineUser.getUserName().equals(userInfo.getUserName())
                && onLineUser.getPassword().equals(userInfo.getPassword()))
            {
                onLineUser.setLoginTime(userInfo.getLoginTime());
                userList.set(i, onLineUser);
                return;
            }
        }
        userList.add(userInfo);
        
    }
    
    public static void deleteUser(UserInfo userInfo)
    {
        userList.remove(userInfo);
    }
    
    public static int getOnlineCount()
    {
        return userList.size();
    }
    
    public static List<UserInfo> getOnline()
    {
        return userList;
    }
    
}