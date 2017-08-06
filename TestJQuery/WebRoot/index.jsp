<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.6.2.js"></script>
	<script type="text/javascript">
	  function modifyMenu(menu_id)
	      {
				$.ajax({  
				    type : "POST",  
				    url : "getMenu.do" ,  
				    contentType : "application/json",  
				    dataType : 'json',  
				    success : function(data)
				     {  
					      for (var i = 0; i < data.menuInfoList.length; i ++)
						   {
							   alert(data.menuInfoList[i].menu_text + "------"  + data.menuInfoList[i].province_name)
						   }
				     },
				    error: function(data)
					   {
						   alert(data);
						   alert(data.menuInfoList);
					   }
				    
				});
		}
	
	</script>
  </head>
  
  <body>
    <fieldset>
       <legend>ajax测试:</legend>
    <input type="button" value="getMenu" onclick="modifyMenu('1');"/>
    </fieldset>
    <form action="ftl.do" method="post">
       <fieldset>
		    <legend>FreeMarker模板:</legend>
		      模板名称: <input type="text" name="template" value="email.ftl" /><br />
		      获取模板: <input type="submit" value="FTL" /><br />
       </fieldset>
    </form>
    
    
     <form action="login.do" method="post">
       <fieldset>
		    <legend>登录Session测试:</legend>
           用户名：<input type="text" name="userName" value="baiqirui" /><br />
           密   码：<input type="password" name="password" value="123456" /><br />
          <input type="submit" /> &nbsp;&nbsp;&nbsp;<input type="reset" />
       </fieldset>
      </form>
  </body>
</html>
