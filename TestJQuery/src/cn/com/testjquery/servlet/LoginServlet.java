package cn.com.testjquery.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.testjquery.listener.SessionBoundListener;
import cn.com.testjquery.vo.UserInfo;

/**
 * <功能详细描述>
 * 
 * @author lenovo
 * @version [版本号, Feb 7, 2014]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LoginServlet extends HttpServlet
{
    private static final String USER_INFO = "USER_INFO";
    
    private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        
        String loginTime = format.format(new Date());
        
        UserInfo user = new UserInfo(userName, password);
        user.setLoginTime(loginTime);
        user.setIp(getIp(req));
        
        HttpSession session = req.getSession();
        session.setAttribute("SessionBoundListener", new SessionBoundListener());
        session.setAttribute(USER_INFO, user);
        resp.sendRedirect("exit.jsp");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        super.doPost(req, resp);
    }
    
    private String getIp(HttpServletRequest request)
    {
        if (request.getHeader("x-forwarded-for") == null)
        {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }
}
