package cn.com.testjquery.modle;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 自定义FreeMaker的方法
 * 
 * @author baiqirui
 * @version [版本号, Dec 4, 2013]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SubstringMethod implements TemplateMethodModel
{
    
    public Object exec(List arguments)
        throws TemplateModelException
    {
        if (null == arguments || arguments.size() < 2)
        {
            throw new TemplateModelException("Wrong arguments");
        }
        String param = (String)arguments.get(0);
        Integer index = Integer.valueOf((String)arguments.get(1));
        return param.substring(0, index);
    }
    
}
