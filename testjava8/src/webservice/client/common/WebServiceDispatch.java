package webservice.client.common;


import webservice.constant.WebServiceConstant;
import webservice.exception.WebServiceException;
import webservice.value.request.WebServiceRequest;
import webservice.value.response.WebServiceResponse;

/**
 * WebService服务分发类;
 * 
 * @author baiqirui
 * @version [版本号, 2012-5-14]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public final class WebServiceDispatch
{
    
    private WebServiceDispatch()
    {
        
    }
    
    /**
     * WebService服务分发;
     * 
     * @param request [请求参数]
     * @param serviceName [方法名称]
     * @return
     * @throws PortalException [参数说明]
     * 
     * @return Object [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static WebServiceResponse dispatch(WebServiceRequest request, String serviceName)
        throws WebServiceException
    {
        // 根据系统开关判断是否需要调用WebService接口来同步数据;
        if (WebServiceConstant.SWITCH_OPEN)
        {
            WebServiceProcess processor = getBean(serviceName);
            if (null == processor)
            {
                throw new WebServiceException("can not find service, serviceName is " + serviceName);
            }
            return processor.process(request);
        }
        else
        {
            return null;
        }
    }

    private static WebServiceProcess getBean(String serviceName)
    {
        return null;
    }
    
}
