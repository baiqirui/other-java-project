package webservice.value.response;

/**
 * Token验证接口的响应值对象;
 * 
 * @author baiqirui
 * @version [版本号, 2013-5-23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TokenVerifyResponse extends WebServiceResponse
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -1990563358493136601L;
    
    /**
     * 省份编码(遗留字段)
     */
    private String provinceCode;
    
    /**
     * 用户账号
     */
    private String UserAccount;
    
    public String getProvinceCode()
    {
        return provinceCode;
    }
    
    public void setProvinceCode(String provinceCode)
    {
        this.provinceCode = provinceCode;
    }
    
    public String getUserAccount()
    {
        return UserAccount;
    }
    
    public void setUserAccount(String userAccount)
    {
        UserAccount = userAccount;
    }
}
