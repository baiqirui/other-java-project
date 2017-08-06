package cn.com.testjquery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public  final class WebUtils
{
    private WebUtils()
    {
        
    }
    
    /**
     * 格式化http请求头信息
     * 
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String getHttpHead(HttpServletRequest request)
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
    
    public static String formatException(Throwable e)
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
