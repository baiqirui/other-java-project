package cn.com.testjquery.modle;

import java.io.IOException;
import java.io.Writer;

/**
 * 实现
 * 
 * @author  lenovo
 * @version  [版本号, Feb 13, 2014]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MyStringWriter extends  Writer
{
    
    private Writer out;
    
    private StringBuffer bufferStr = new StringBuffer(); 
    
    public MyStringWriter(Writer out)
    {
        this.out = out;
    }

    @Override
    public void close()
        throws IOException
    {
        
    }

    @Override
    public void flush()
        throws IOException
    {
        
    }

    @Override
    public void write(char[] cbuf, int off, int len)
        throws IOException
    {
        bufferStr.append(cbuf, off, len);
        out.write(cbuf, off, len);
    }

    public String getWriterContent()
    {
        return bufferStr.toString();
    }
}
