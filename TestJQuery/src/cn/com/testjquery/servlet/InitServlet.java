package cn.com.testjquery.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet
{
    private static final long serialVersionUID = -69805157157458983L;
    
    private static Integer userCount = 0;
    
    @Override
    public void init()
        throws ServletException
    {
        super.init();
        
        // 初始化页面需要用到的参数
        ServletContext ctx = getServletContext();
        
        
    }
    
}
