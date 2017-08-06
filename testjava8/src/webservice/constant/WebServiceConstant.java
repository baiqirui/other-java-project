package webservice.constant;

/**
 * WebService相关的常量
 * 
 * @author baiqirui
 * @version [版本号, 2013-5-22]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public final class WebServiceConstant
{
    private WebServiceConstant()
    {
        
    }
    
    /**
     * 成功的响应码;
     */
    public static final String RESPONSE_SUCCESS = "0";
    
    /**
     * 同步WebService的开关;
     */
    public static final boolean SWITCH_OPEN = false;
    
    /**
     * 用户信息同步服务的地址;
     */
    public static final String USERINFOSYNC_STUB =
        "http://113.108.186.130:7770/yxt-ifservice-server/services/UserInfoSyncService?wsdl";
    
    /**
     * 电子书包相关服务的地址;
     */
    public static final String EBAGSYNC_STUB =
        "http://113.108.186.130:7770/yxt-ifservice-server/services/EbagInfoSyncService?wsdl";
    
    /**
     * 电子书包相关服务的地址;
     */
    public static final String LOGINSERVICE_STUB =
        "http://113.108.186.130:7770/eip-platform-sso-server/services/LoginService?wsdl";
    
    /**
     * 电子书包相关服务的地址;
     */
    public static final String USERBIZINFOSYNC_STUB = 
        "http://113.108.186.130:7770/yxt-ifservice-server/services/UserBizInfoSyncService?wsdl";
    
    /**
     * 用户信息同步接口;
     */
    public static final String USERINFO_SYN = "userInfoSyn";
    
}
