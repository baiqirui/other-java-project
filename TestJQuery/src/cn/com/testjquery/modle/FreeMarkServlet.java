package cn.com.testjquery.modle;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.mail.SentEmailThread;
import cn.com.testjquery.WebUtils;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * <功能详细描述>
 * 
 * @author lenovo
 * @version [版本号, Dec 2, 2013]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class FreeMarkServlet extends HttpServlet
{
    
    private Configuration cfg;
    
    @Override
    public void init(ServletConfig config)
        throws ServletException
    {
        try
        {
//            String path = config.getServletContext().getRealPath("/WEB-INF/templates");
            cfg = new Configuration();
            // 指定模板文件从何处加载的数据源，这里设置成一个文件目录。
            //cfg.setDirectoryForTemplateLoading(new File(path));
            
            //第二种加载模板的方式;
            cfg.setServletContextForTemplateLoading(config.getServletContext(), "/WEB-INF/templates");
            
            //第三种加载模板的方式;
//            cfg.setClassForTemplateLoading(FreeMarkServlet.class, "../templates");
            
            // 指定模板如何检索数据模型，这是一个高级的主题了…
            // 但先可以这么来用：
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            
            //将自定义指令存入共享变量中;
            cfg.setSharedVariable("to_Upper", new UpperDirective());
            //将自定义方法标签存入共享变量中
            cfg.setSharedVariable("substring", new SubstringMethod());
            
//            cfg.setEncoding(Locale.getDefault(), "UTF-8");
            cfg.setDefaultEncoding("UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        super.init(config);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        try
        {
            Template template = cfg.getTemplate(req.getParameter("template"));
            if ("email.ftl".equals(template.getName()))
            {
                sendEmail(template, req, resp);
            }
            else
            {
                template.setEncoding("UTF-8");
                /* 创建数据模型 */
                Map<String, Object> root = new HashMap<String, Object>();
//                root.put("user", "baiqirui");
                List<Product> productList = new ArrayList<Product>();
                productList.add(new Product("IBM笔记本", 999, 10));
                productList.add(new Product("IPad笔记本", 7999, 88));
                productList.add(new Product("惠普笔记本", 888, 2));
                root.put("productList", productList);
                
                Map<String, String> latest = new HashMap<String, String>();
                root.put("latestProduct", latest);
                latest.put("url", "www.baiqirui.com");
                latest.put("name", "green mouse");
                
                /* 将模板和数据模型合并 */
                Writer out = resp.getWriter();
                template.process(root, out);
                out.flush();
            }
            
//         
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void sendEmail(Template template, HttpServletRequest request, HttpServletResponse resp) throws Exception
    {
        EmailInfo emailInfo = new EmailInfo();
        emailInfo.setUrl(request.getRequestURL().toString());
        emailInfo.setBureauInfo("东莞局点");
        emailInfo.setHead(WebUtils.getHttpHead(request));
        emailInfo.setReqBody("芳姐在东莞玩密室大逃亡，发生错误！");
        emailInfo.setVersion("WUFANG V001R002C03SP01");
        emailInfo.setTrace("芳姐在东莞");
        
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("email", emailInfo);
        //使用自己封装的Writer进行输出;
        MyStringWriter out = new MyStringWriter(resp.getWriter());
        
//        StringWriter sw = new StringWriter();
        template.process(root, out);
//        template.process(root, sw);
        System.out.println(out.getWriterContent());
        SentEmailThread sendMail = new SentEmailThread("teachportal",   out.getWriterContent());
        sendMail.start();
//        ThreadPoolUtil.executeRunner(sendMail);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        this.doPost(req, resp);
    }
    
}
