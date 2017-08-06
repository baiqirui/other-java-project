package cn.com.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * 错误邮件发送器
 * 
 * @author zengjie
 * @since 2013-2-28
 */
public class MailSender
{
    private static final String HTTP_URL_REPLACEMENT = "${url}";
    
    private static final String HTTP_HEAD_REPLACEMENT = "${head}";
    
    private static final String ERR_TRACE_REPLACEMENT = "${trace}";
    
    private static final String ERR_BUREAUCODE_REPLACEMENT = "${bureauInfo}";
    
    private static final String ERR_VERSION_REPLACEMENT = "${versionInfo}";
    
    private static final String ERR_REQ_REPLACEMENT = "${req}";
    
    private static String ERR_MAIL_TEMPLATE = (HTTP_URL_REPLACEMENT + HTTP_HEAD_REPLACEMENT + ERR_TRACE_REPLACEMENT
        + ERR_BUREAUCODE_REPLACEMENT + ERR_VERSION_REPLACEMENT + ERR_REQ_REPLACEMENT);
    
    static
    {
//        InputStream stream = MailSender.class.getResourceAsStream("/ErrMailTemplate.html");
//        try
//        {
//            //ERR_MAIL_TEMPLATE = IOUtils.toString(stream, "UTF-8");
//        }
//        catch (IOException e)
//        {
//        }
//        finally
//        {
//           // IOUtils.closeQuietly(stream);
//        }
    }
    
    /**
     * 发送指定信息的错误邮件
     * 
     * @param project 当前web项目
     * @param req 当前错误的http请求
     * @param t Java错误对象
     * @param reqBody 请求JSON体
     */
    public static void sendErrMail(String project, HttpServletRequest req, Throwable t, String reqBody)
    {
        String mailBody = ERR_MAIL_TEMPLATE.replace(HTTP_URL_REPLACEMENT, req.getRequestURL());
        mailBody = mailBody.replace(HTTP_HEAD_REPLACEMENT, formatHttpHead(req));
        mailBody = mailBody.replace(ERR_TRACE_REPLACEMENT, formatException(t));
        mailBody = mailBody.replace(ERR_BUREAUCODE_REPLACEMENT, getBureauInfo());
        mailBody = mailBody.replace(ERR_VERSION_REPLACEMENT, "Aischool V002R001C01");
        if (null != reqBody)
        {
            mailBody = mailBody.replace(ERR_REQ_REPLACEMENT, reqBody);
        }
        SentEmailThread sendMail = new SentEmailThread(project, mailBody);
        ThreadPoolUtil.executeRunner(sendMail);
    }
    
    /**
     * 发送指定信息的错误邮件
     * 
     * @param project 当前web项目
     * @param req 当前错误的http请求
     * @param t Java错误对象
     * @param reqBody 请求JSON体
     * @param version 版本号
     */
    public static void sendErrMail(String project, HttpServletRequest req, Throwable t, String reqBody, String version)
    {
        String mailBody = ERR_MAIL_TEMPLATE.replace(HTTP_URL_REPLACEMENT, req.getRequestURL());
        mailBody = mailBody.replace(HTTP_HEAD_REPLACEMENT, formatHttpHead(req));
        mailBody = mailBody.replace(ERR_TRACE_REPLACEMENT, formatException(t));
        mailBody = mailBody.replace(ERR_BUREAUCODE_REPLACEMENT, getBureauInfo());
        mailBody = mailBody.replace(ERR_VERSION_REPLACEMENT, version);
        if (null != reqBody)
        {
            mailBody = mailBody.replace(ERR_REQ_REPLACEMENT, reqBody);
        }
        SentEmailThread sendMail = new SentEmailThread(project, mailBody);
        ThreadPoolUtil.executeRunner(sendMail);
    }
    
    private static String getBureauInfo()
    {
        String def = "unknown_branch_number";
        String code = "CNBJTW2";
        if (code == null)
        {
            return def;
        }
        return code;
    }
    
    /**
     * 格式化http请求头信息
     * 
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    private static String formatHttpHead(HttpServletRequest request)
    {
        StringBuilder builder = new StringBuilder();
        Enumeration<String> e = request.getHeaderNames();
        boolean useRedFont = false;
        while (e.hasMoreElements())
        {
            String headName = e.nextElement();
            if (headName == null)
            {
                continue;
            }
            if (headName.toLowerCase().startsWith("x-"))
            {
                useRedFont = true;
            }
            Enumeration<String> values = request.getHeaders(headName);
            while (values.hasMoreElements())
            {
                String value = values.nextElement();
                if (useRedFont)
                {
                    builder.append("<div><font color=\"red\">")
                        .append(headName)
                        .append(":")
                        .append(value)
                        .append("</font></div>");
                }
                else
                {
                    builder.append("<div>").append(headName).append(":").append(value).append("</div>");
                }
            }
            useRedFont = false;
        }
        return builder.toString();
    }
    
    private static String formatException(Throwable e)
    {
        StringWriter writer = new StringWriter();
        PrintWriter printer = new PrintWriter(writer);
        e.printStackTrace(printer);
        try
        {
            writer.close();
            StringReader sr = new StringReader(writer.toString());
            BufferedReader reader = new BufferedReader(sr);
            StringBuilder out = new StringBuilder("<pre>");
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                if (line.contains("tianwen"))
                {
                    out.append("<font color=\"red\"><b>");
                    out.append(line);
                    out.append("</b></font>\n");
                }
                else
                {
                    out.append(line).append("\n");
                }
            }
            out.append("</pre>");
            return out.toString();
        }
        catch (IOException e1)
        {
        }
        return writer.toString();
    }
    
}
