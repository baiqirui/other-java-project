package webservice.value.request;

import webservice.constant.WebServiceConstant;

/**
 * WebService的请求类的父类(每个基于WebService调用的请求对象都应该实现该类)
 * 
 * @author baiqirui
 * @version [版本号, 2013-5-22]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class WebServiceRequest extends Request
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -6062885863681242495L;
    
    
    /**
     * 调用方系统标识
     */
    private String caller = "caller";
    
    /**
     * 省份编码(详情参考配置项)
     */
    private String provinceCode = "PROVINCE_CODE";
    
    public String getCaller()
    {
        return caller;
    }
    
    public void setCaller(String caller)
    {
        this.caller = caller;
    }
    
    public String getProvinceCode()
    {
        return provinceCode;
    }
    
    public void setProvinceCode(String provinceCode)
    {
        this.provinceCode = provinceCode;
    }

    @Override
    public String toString()
    {
        return "WebServiceRequest [caller=" + caller + ", provinceCode=" + provinceCode + "]";
    }
}
