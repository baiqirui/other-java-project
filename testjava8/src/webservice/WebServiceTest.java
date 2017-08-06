package webservice;

import webservice.client.common.DefaultWebServiceProcess2;
import webservice.client.process.TokenVerifyProcess;
import webservice.value.request.TokenVerifyRequest;
import webservice.value.request.TokenVerifyRequestDoc;
import webservice.value.response.WebServiceResponse;

public class WebServiceTest
{
    public static void main(String[] args) throws Exception
    {
        TokenVerifyProcess process = new TokenVerifyProcess();
        TokenVerifyRequest request = new TokenVerifyRequest();
//        process.process(request, (r -> {
//             return null;
//        }));
        
        
        DefaultWebServiceProcess2<TokenVerifyRequestDoc> defaultProcess = new DefaultWebServiceProcess2<TokenVerifyRequestDoc>();
        WebServiceResponse response = defaultProcess.process(request, r -> {
            if (null == r)
            {
                throw new NullPointerException();   
            }
          },
          r -> {
             return null;
        },
          r -> {
              return null;
         });
        System.out.println(response);
    }
}
