<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理界面</title>
<script type="text/javaScript"  src="scripts/jquery-3.2.1.js"></script>



		
</head>
<body>
	<div>
		<div>管理
		
		</div>
		<div class="student">
			<div>考生信息录入</div>
			<div>
				<form action="./servlet/downloadServlet" method="post"
					enctype="multipart/form-data">
					考试题目导入 <br /> <input type="file" name="选择文件"
						style="border-style: solid" /><br /> <input type="submit"
						value="上传" />
				</form>
				<ul>
					<li><a id="xml"  href="doc/content.json">显示题目信息</a></li>
				<ul>
			</div>
			<div>成绩展示区</div>
		</div>
	</div>
</body>
</html>