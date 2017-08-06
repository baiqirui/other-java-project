package webservice.client.common;

import java.util.function.Function;

import webservice.exception.WebServiceException;
import webservice.value.request.WebServiceRequest;
import webservice.value.response.WebServiceResponse;

/**
 * 
 * 提供一个默认的处理器
 * 
 * @author baiqirui
 * @version [版本号, 2013-5-22]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class DefaultWebServiceProcess implements WebServiceProcess
{
    
    /**
     * 提供一个默认的处理;
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public <R> WebServiceResponse process(WebServiceRequest request, Function<WebServiceRequest, R> f) throws Exception
    {
        validateParam(request);
        // 将系统中的请求对象转换成WebService端需要的值对象类型;
        R webReqDoc = createRequestDoc1(request, f);
        WebServiceResponse response = execute(webReqDoc);
        return response;
    }
    
    /**
     * 提供一个默认的处理;
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public WebServiceResponse process(WebServiceRequest request)
        throws WebServiceException
    {
        try
        {
            // 检验参数的合法性;
            validateParam(request);
            // 将系统中的请求对象转换成WebService端需要的值对象类型;
            Object webReqDoc = createRequestDoc(request);
            // if (LOGGER.isDebugEnabled())
            // {
            // LOGGER.debug("请求前的参数：" + request);
            // LOGGER.debug("WebService请求报文：\n" + webReqDoc);
            // }
            // 执行服务;
            WebServiceResponse response = execute(webReqDoc);
            return response;
        }
        catch (Exception e)
        {
            // LOGGER.error(e);
            if (e instanceof WebServiceException)
            {
                throw (WebServiceException)e;
            }
            else
            {
                throw new WebServiceException(e);
            }
        }
    }
    
    /**
     * 执行服务(实现类用于调用外部WebService的方法);
     * 
     * @param request
     * @return [参数说明]
     * 
     * @return Response [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    protected abstract WebServiceResponse execute(Object request)
        throws Exception;
    
    /**
     * 创建WebService端需要的请求值对象;
     * 
     * @param request
     * @return
     * @throws Exception [参数说明]
     * 
     * @return Object [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    protected abstract Object createRequestDoc(WebServiceRequest request)
        throws Exception;
    
    protected <R> R createRequestDoc1(WebServiceRequest t, Function<WebServiceRequest, R> f)
        throws Exception
    {
        return f.apply(t);
    }
    
    /**
     * 参数检验方法,主要校验参数的数据有效性,不做相关业务性校验 父类主要是对一些基本的公共参数进行校验
     * 
     * @param request [实际的请求体]
     * @throws WebServiceException [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    protected void validateParam(WebServiceRequest request)
        throws WebServiceException
    {
    }
    
}
