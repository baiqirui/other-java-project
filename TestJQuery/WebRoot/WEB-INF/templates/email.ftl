<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body style="font-size: 12px">
	<div style="padding-left: 20px;">
		<div>
			<h3>局点信息</h3>
			<div>${email.bureauInfo}</div>
		</div>
		<div>
			<h3>版本信息</h3>
			<div>${email.version}</div>
		</div>
		<div>
			<h3>请求路径</h3>
			<div>${email.url}</div>
		</div>
		<div>
			<h3>请求头</h3>
			<div>${email.head}</div>
		</div>
		<div style="font-size: 14px;">
			<h3>错误堆栈</h3>
			<pre>${email.trace}</pre>
		</div>
		<div>
			<h3>请求体</h3>
			<div>
				<textarea rows="20" cols="80" readonly="readonly">${email.reqBody}</textarea>
			</div>
		</div>
	</div>
</body>
</html>