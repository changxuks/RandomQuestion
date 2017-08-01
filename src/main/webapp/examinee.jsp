<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>考试界面</title>
<script type="text/javascript" src="scripts/jquery-3.2.1.js">
	
</script>
<script type="text/javascript">
	$(function() {
		var url = "servlet/currentQuestion";
		var args = {
			"time" : new Date()
		};
		$.get(url, args, function(data) {
			var id = data.id;
			var title = data.title;
			var resultA = data.resultA;
			var resultB = data.resultB;
			var resultC = data.resultC;
			var resultD = data.resultD;

			$("#details").empty().append(id + "\." + title + "<br>").append(
					"<input  type='radio'  name='select' value='A'>" + resultA
							+ "<br>").append(
					"<input  type='radio'  name='select' value='B'>" + resultB
							+ "<br>").append(
					"<input  type='radio'  name='select' value='C'>" + resultC
							+ "<br>").append(
					"<input  type='radio'  name='select' value='D'>" + resultD
							+ "<br>");
		});
		$("#pre").click(
				function() {
					var url = this.href;
					var value = $('input:radio[name="select"]:checked').val();
					var args = {
						"time" : new Date(),
						"select" : value
					};
					$.get(url, args, function(data) {

						var message = data.message;
						if (message != undefined) {
							alert(message);
						} else {
							var id = data.id;
							var title = data.title;
							var resultA = data.resultA;
							var resultB = data.resultB;
							var resultC = data.resultC;
							var resultD = data.resultD;

							$("#details").empty().append(
									id + "\." + title + "<br>").append(
									"<input  type='radio'  name='select' value='A'>"
											+ resultA + "<br>").append(
									"<input  type='radio'  name='select' value='B'>"
											+ resultB + "<br>").append(
									"<input  type='radio'  name='select' value='C'>"
											+ resultC + "<br>").append(
									"<input  type='radio'  name='select' value='D'>"
											+ resultD + "<br>");
						}

					});

					return false;

				});

		$("#next").click(
				function() {
					var url = this.href;
					var value = $('input:radio[name="select"]:checked').val();
					var args = {
						"time" : new Date(),
						"select" : value
					};
					$.get(url, args, function(data) {

						var message = data.message;
						if (message != undefined) {
							alert(message);
						} else {
							var id = data.id;
							var title = data.title;
							var resultA = data.resultA;
							var resultB = data.resultB;
							var resultC = data.resultC;
							var resultD = data.resultD;

							$("#details").empty().append(
									id + "\." + title + "<br>").append(
									"<input  type='radio'  name='select' value='A'>"
											+ resultA + "<br>").append(
									"<input  type='radio'  name='select' value='B'>"
											+ resultB + "<br>").append(
									"<input  type='radio'  name='select' value='C'>"
											+ resultC + "<br>").append(
									"<input  type='radio'  name='select' value='D'>"
											+ resultD + "<br>");
						}

					});

					return false;

				});
	});
</script>

</head>
<body>
	<div id="details"></div>
	<a id="pre" href="servlet/preQuestion">上一题</a>
	<a id="next" href="servlet/nextQuestion">下一题</a>
	<input id="hand" type="submit" value="交卷" />
</body>
</html>