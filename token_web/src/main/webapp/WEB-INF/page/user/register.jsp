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
	}
</style>
</head>
<body>
	<form id = "frm">
				<input type="hidden" value="${token}"  name="token">
		用户名:<input type = "text" name = "userName" id="userName" ><br>
		密码:<input type = "text" name = "userPwd"><br>
		邮箱:<input type = "text" name = "email" id="email"><br/>
		手机号:<input type = "text" name = "phone" id="phone"><br/>
		头像：<input type="file" name="file"><br/>
		<input type = "submit" value = "提交">
	</form>
</body>
	<script type="text/javascript">
 $(function (){
		$("#frm").validate({
			rules:{
					userName:{
						required:true,
						remote:{
							type: "post",
							url:"<%=request.getContextPath()%>/user/findNameOrPhoneOrEmail?token="+'${token}',
							data: {
								userName:function () {
									return $("#userName").val();
								},
								dataType: "json",
								dataFilter:function (data,type){
									if (data == "true"){
										return true;
									} else {
										return false;
									}
								}
							}
							
						}
					},
					userPwd:{
						required:true,
						minlength:3,
						digits:true  
					},
					email:{
						required:true,
						email:true,
						remote:{
							type: "post",
							url:"<%=request.getContextPath()%>/user/findNameOrPhoneOrEmail?token="+'${token}',
							data: {
								email:function () {
									return $("#email").val();
								},
								dataType: "json",
								dataFilter:function (data,type){
									if (data == "true"){
										return true;
									} else {
										return false;
									}
								}
							}
							
						}
					},
					phone:{
						required:true,
						digits:true,
						phone:true,
						remote:{
							type: "post",
							url:"<%=request.getContextPath()%>/user/findNameOrPhoneOrEmail?token="+'${token}',
							data: {
								phone:function () {
									return $("#phone").val();
								},
								dataType: "json",
								dataFilter:function (data,type){
									if (data == "true"){
										return true;
									} else {
										return false;
									}
								}
							}
							
						}
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
					remote: "該邮箱号已存在，請重新输入"
					
				},
				phone:{
					required:"请输入电话号",
					remote: "該电话号已存在，請重新输入"
				},
				}
		})
	})
	//邮箱验证
 jQuery.validator.addMethod("email", 
 function(value, element) { 
	 var ema =  /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/; 
 	 return ema.test(value)
  }, "请正确填写您的邮箱号");
		
//手机验证
 jQuery.validator.addMethod("phone", 
		 function(value, element) { 
			 var ph =/^1[3578]\d{9}$/; 
		 return ph.test(value)
		  }, "请正确填写您的电话号码");
 
		// 注册
		$.validator.setDefaults({
			submitHandler: function (){
				var index = layer.load(0,{shade:0.5});
				$.post(
						"<%=request.getContextPath()%>/user/insertUser",
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
				})
			}
			
		})
	</script>
</html>