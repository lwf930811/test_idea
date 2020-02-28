<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript" src = "<%=request.getContextPath()%>/res/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src = "<%=request.getContextPath()%>/res/validate/jquery.validate.js"></script>
	<script type="text/javascript" src = "<%=request.getContextPath()%>/res/layer/layer.js"></script>
	<style type="text/css">
		.error{
			color:red;

	</style>
</head>
<body>
	<form id = "frm">
				<input type="hidden" value="${user.id}"  name="id">
				<input type="hidden" value="${token}"  name="token">
				<input type="hidden" value="${user.img}"  name="img">
		用户名:<input type = "text" name = "userName"  value="${user.userName}"><br>
		密码:<input type = "text" name = "userPwd"  value="${user.userPwd}"><br>
		邮箱:<input type = "text" name = "email" id="email"  value="${user.email}"><br/>
		手机号:<input type = "text" name = "phone" id="phone"  value="${user.phone}"><br/>
		头像：<img src="http://q4370wf9l.bkt.clouddn.com/${user.img}" width="100px" height="100px">
			<input type="file" name="file" accept="image/*" id="file"><br>
			<input type = "submit" value = "提交">
	</form>
</body>
	<script type="text/javascript">
		$(function (){
			$("#frm").validate({
				rules:{
					userName:{
						required:true,

					},
					userPwd:{
						required:true,
						minlength:3,
						digits:true
					},
					email:{
						required:true,
					},
					phone:{
						required:true,

					},

				},
				messages:{
					userName:{
						required:"请输入用户名",
					},
					userPwd:{
						required:"请输入密码",
						minlength:"输入符规则的长度",
						digits:"必须输入数字"
					},
					email:{
						required:"请输入邮箱号",

					},
					phone:{
						required:"请输入电话号",
					},

				}
			})
		})
		$.validator.setDefaults({
			submitHandler: function (){
				/*$.post(
						"<%=request.getContextPath()%>/user/update",
						$("#frm").serialize(),
						function (data){
							if(data.code != 200){
								layer.msg(data.msg, {icon: 5});
								layer.close(index);
								return;
							}
							layer.msg(data.msg, {
								icon: 6,
								time: 2000 //2秒关闭（如果不配置，默认是3秒）
							}, function(){
								//do something
								layer.close(index);
								parent.window.location.href = "<%=request.getContextPath()%>/user/toShow?token="+'${token}';
							});
						})*/
				var file = $('#file')[0].files[0];
				var formData = new FormData();
				formData.append("file", file);
				formData.append("token", '${token}');
				formData.append("id", '${user.id}');
				formData.append("img", '${user.img}');
				var index = layer.load(1,{shade:0.3});
				$.ajax({
					url:'<%= request.getContextPath() %>/user/update',
					dataType:'json',
					type:'POST',
					data: formData,
					processData : false, // 使数据不做处理
					contentType : false, // 不要设置Content-Type请求头信息
					success: function(data){
						layer.msg(data.msg,function () {
							layer.close(index);
							if (data.code != 200) {
								return;
							}
							parent.window.location.href = "<%= request.getContextPath() %>/user/toShow?token="+'${token}';
						})
					}
				});
			}

		})
	</script>
</html>