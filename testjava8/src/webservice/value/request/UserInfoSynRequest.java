package webservice.value.request;


/**
 * 同步用户信息的请求值对象;
 * 
 * @author baiqirui
 * @version [版本号, 2013-5-22]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class UserInfoSynRequest extends WebServiceRequest
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -3683353597067646062L;
    
    /**
     * 用户在电子书包系统的唯一标识
     */
    private String userId;
    
    /**
     * 用户账号
     */
    private String userAccount;
    
    /**
     * 移动电话
     */
    private String mobile;
    
    /**
     * 邮箱地址
     */
    private String email;
    
    /**
     * 登录密码(MD5加密)
     */
    private String password;
    
    /**
     * 用户姓名
     */
    private String userName;
    
    /**
     * 用户类型(1:学生;2:家长;3:教师;4:学校管理员;5:校长)
     */
    private String userType;
    
    /**
     * 区域编码（国家行政区划代码）（例如北京市110000，广州市天河区440106）
     */
    private String areaCode;
    
    /**
     * 家长在电子书包系统中的ID，此字段当用户类型为1学生时有效
     */
    private String parentID;
    
    /**
     * 操作类型( 枚举值：1:注册;2:变更;3:销户)
     */
    private int opFlag;
    
    public String getUserId()
    {
        return userId;
    }
    
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    
    public String getUserAccount()
    {
        return userAccount;
    }
    
    public void setUserAccount(String userAccount)
    {
        this.userAccount = userAccount;
    }
    
    public String getMobile()
    {
        return mobile;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public String getUserType()
    {
        return userType;
    }
    
    public void setUserType(String userType)
    {
        this.userType = userType;
    }
    
    public String getAreaCode()
    {
        return areaCode;
    }
    
    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }
    
    public String getParentID()
    {
        return parentID;
    }
    
    public void setParentID(String parentID)
    {
        this.parentID = parentID;
    }
    
    public int getOpFlag()
    {
        return opFlag;
    }
    
    public void setOpFlag(int opFlag)
    {
        this.opFlag = opFlag;
    }
}
