/*
 * 文 件 名:  PortalException.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  h00101670
 * 修改时间:  2009-1-10
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package webservice.exception;

/**
 * WebService异常类;
 * 
 * @author  baiqirui
 * @version  [版本号, 2013-6-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WebServiceException extends RuntimeException
{
    /**
     * 序列号
     */
    private static final long serialVersionUID = 6849794470754667711L;
    
    /**
     * WebServiceException
     * 
     * @param throwable String
     */
    public WebServiceException(Throwable throwable)
    {
        super(throwable);
    }
    
    /**
     * WebServiceException
     * 
     * @param throwable String
     */
    public WebServiceException(String message)
    {
        super(message);
    }
    
}
