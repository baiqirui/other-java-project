package cn.com.testjquery.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <功能详细描述>
 * 
 * @author  lenovo
 * @version  [版本号, Feb 7, 2014]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ExitServlet  extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        req.getSession().invalidate();
    }
      
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
          req.getSession().invalidate();
    }
}
