package webservice.client.process;

import webservice.client.common.DefaultWebServiceProcess;
import webservice.value.request.WebServiceRequest;
import webservice.value.response.WebServiceResponse;

/**
 * Token验证服务
 * 
 * @author baiqirui
 * @version [版本号, 2013-5-23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TokenVerifyProcess extends DefaultWebServiceProcess
{

    @Override
    protected WebServiceResponse execute(Object request)
        throws Exception
    {
//        EbagInfoSyncServiceStub stub = WebServiceUtil.getEbagInfoSyncServiceStub();
//        TaskResultRespDocument respDoc = stub.taskInfoSyn((TaskInfoSynReqDocument)request);
//        // 判断WebService调用是否成功;
//        if (null != respDoc && null != respDoc.getTaskResultResp())
//        {
//            if (WebServiceConstant.RESPONSE_SUCCESS.equals(respDoc.getTaskResultResp().getResultCode()))
//            {
//                return WebServiceUtil.response(ICommonResultCode.SUCCESS_NO_RESPONSE, respDoc);
//            }
//            else
//            {
//                throw new WebServiceException(Integer.parseInt(respDoc.getTaskResultResp().getResultCode()),
//                    respDoc.getTaskResultResp().getResultMsg());
//            }
//        }
//        else
//        {
//            throw new WebServiceException(ICommonResultCode.INVOKE_WEBSERVICE_FAILED, "invoke webservice falied.");
//        }
        return null;
    }

    @Override
    protected Object createRequestDoc(WebServiceRequest request)
        throws Exception
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
