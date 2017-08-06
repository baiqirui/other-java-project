package webservice.client.common;

import webservice.exception.WebServiceException;
import webservice.value.request.WebServiceRequest;
import webservice.value.response.WebServiceResponse;

/**
 * 处理类的接口,每个对服务处理的类都需要实现该接口
 * 
 * @author baiqirui
 * @version [版本号, 2013-5-22]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface WebServiceProcess<R extends WebServiceRequest>
{
    
    /**
     * 接口服务处理方法;
     * 
     * @param request [请求参数]
     * @return Object [响应参数]
     * @throws WebServiceException [参数说明]
     * 
     * @return Object [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public WebServiceResponse process(R request);
}
