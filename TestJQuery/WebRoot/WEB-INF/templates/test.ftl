<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Welcome!</title>
</head>
<body>
<h1>Welcome ${user!"Anonymous"}!
            <#if user!"Anonymous" == "baiqirui">
                 , our beloved leader
             <#else>
                 , our staff
            </#if>!
  </h1>
<p>Our latest product:
<a href="${latestProduct.url}">${latestProduct.name}</a>!
<table border="1">
  <caption>产品列表</caption>
  <tr>
     <th>序号<th>
     <th>产品名称<th>
     <th>产品价格<th>
     <th>产品数量<th>
     <th>产品总价<th>
  </tr>
    <#list productList as product>
        <tr>
            <td>${product_index + 1}<td>
            <td>${product.proName}<td>
            <td>${product.price}<td>
            <td>${product.quantity}<td>
            <td>${product.totalPrice()}<td>
       </tr>
    </#list>
  </table>  
    
    <br/>
    <br/>
    
   <table border="1">
    <caption>季节列表</caption>
    <!-- 利用自定义指令【自定义指令以@符号开始】,将包含在内的文本全部大写 -->
    <@to_Upper name="baiqirui" age=25 phone="18569066561">
    <#assign seq = ["winter", "spring", "summer", "autumn"]>
        <#list seq as x>
         <tr>
            <td>${x_index + 1}<td>
            <td>${x}<td>
            <!-- substring是自定义方法  -->
            <td>${substring(x, 3)}<td>
          </tr>
	  </#list>
	</@to_Upper>
</table>
<#include "/footer.html">
</body>
</html>