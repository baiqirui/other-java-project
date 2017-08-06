package cn.com.testjquery.modle;

import java.io.Serializable;

/**
 * 邮箱内容信息;
 * 
 * @author baiqirui
 * @version [版本号, Feb 12, 2014]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class EmailInfo implements Serializable
{
    
    private static final long serialVersionUID = 6064564921684066094L;
    
    private String bureauInfo;
    
    private String version;
    
    private String url;
    
    private String head;
    
    private String trace;
    
    private String reqBody;
    
    public String getBureauInfo()
    {
        return bureauInfo;
    }
    
    public void setBureauInfo(String bureauInfo)
    {
        this.bureauInfo = bureauInfo;
    }
    
    public String getVersion()
    {
        return version;
    }
    
    public void setVersion(String version)
    {
        this.version = version;
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    public String getHead()
    {
        return head;
    }
    
    public void setHead(String head)
    {
        this.head = head;
    }
    
    public String getTrace()
    {
        return trace;
    }
    
    public void setTrace(String trace)
    {
        this.trace = trace;
    }
    
    public String getReqBody()
    {
        return reqBody;
    }
    
    public void setReqBody(String reqBody)
    {
        this.reqBody = reqBody;
    }
    
}
