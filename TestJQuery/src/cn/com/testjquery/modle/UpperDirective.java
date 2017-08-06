package cn.com.testjquery.modle;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 自定义FreeMarker指令,该指令用于将包含在内的文本全部大写;
 * 
 * @author baiqirui
 * @version [版本号, Dec 3, 2013]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class UpperDirective implements TemplateDirectiveModel
{
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
        throws TemplateException, IOException
    {
        
        System.out.println(params);
        System.out.println(loopVars);
        // // 检查参数是否传入
        // if (!params.isEmpty())
        // {
        // throw new TemplateModelException("This directive doesn't allow parameters.");
        // }
        // if (loopVars.length != 0)
        // {
        // throw new TemplateModelException("This directive doesn't allow loop variables.");
        // }
        // 是否有非空的嵌入内容
        if (body != null)
        {
            // 执行嵌入体部分，和FTL中的<#nested>一样，除了
            // 我们使用我们自己的writer来代替当前的output writer.
            body.render(new UpperCaseFilterWriter(env.getOut()));
        }
        else
        {
            throw new RuntimeException("missing body");
        }
    }
    
    /**
     * {@link Writer}改变字符流到大写形式， 而且把它发送到另外一个{@link Writer}中。
     */
    private static class UpperCaseFilterWriter extends Writer
    {
        private final Writer out;
        
        UpperCaseFilterWriter(Writer out)
        {
            this.out = out;
        }
        
        public void write(char[] cbuf, int off, int len)
            throws IOException
        {
            char[] transformedCbuf = new char[len];
            for (int i = 0; i < len; i++)
            {
                transformedCbuf[i] = Character.toUpperCase(cbuf[i + off]);
            }
            out.write(transformedCbuf);
        }
        
        public void flush()
            throws IOException
        {
            out.flush();
        }
        
        public void close()
            throws IOException
        {
            out.close();
        }
    }
}