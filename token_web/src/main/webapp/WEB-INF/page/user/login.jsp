<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
	<script type="text/javascript" src = "<%=request.getContextPath()%>/res/jquery/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src = "<%=request.getContextPath()%>/res/jsencrypt/jsencrypt.min.js"></script>
	<script type="text/javascript" src = "<%=request.getContextPath()%>/res/layer/layer.js"></script>
</head>
<body>
	<form id = "frm">
		用户名：<input type = "text" name = "userName" ><br/>
		密码: <input type = "password" name = "userPwd"><br/>
		<input type = "button" value = "登陆" id= "dj_button">
	</form>
</body>
	<script type="text/javascript">
		/*function login(){
			var index = layer.load(1,{shade:0.5});
			$.post(
					"<%=request.getContextPath()%>/user/login",
					$("#frm").serialize(),
					function (data){
						var token = data.data.token;
					layer.close(index);
					if(data.code != 200){
						layer.msg(data.msg, {icon: 5});
						return;
					}
					layer.msg("登录成功", {icon: 6});
					window.location.href="<%=request.getContextPath()%>/index/toIndex?token="+token;
			})
		}*/
		$(function() {
			$('#dj_button').click(function() {
				var encrypt = new JSEncrypt();
				// 公钥
				encrypt.setPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnLfeqUW0W8nGUWWzrLanehKh03HfZuSaoY4yhl6g/zc60YrZzI6I6HycjEKwqqVC/xSE9QOov2fWyCnJfKNJUf7S5fZYeyS7jAAnHSY6WmKs0444vgmX0jKJkzM5+rBKXWsFTE4vOPvh3fRZLkfWJ43+t2w+PL6Fr1RH3lPehk6Gay7Cjt2VXxZDQRzmbPVXDgzprR1L3GfhS+PIHS8gbBHy7s6Dq8ZOoXTIep5F22gzEC9NpiHbLptszfpOeX1aVpvLdtLGm06WE/3HGZkYK4S+8L3Dw6Ed9qcIr/Alcu4yJYwUNPqDySdNpUmM4eBci2WGpRvpWBuR7LUBJB8iZwIDAQAB");
				// 加密方式   == 数据格式     "name="+$("#name").val()+"&pwd="+$("#name").val()
				var encrypted = encrypt.encrypt($("#frm").serialize());
				/* $("#age").val(encrypted); */
				var index = layer.load(1,{shade:0.5});
				$.post(
						"<%=request.getContextPath()%>/user/login.rsa",
						{"data":encrypted},
						function(data){
							var token = data.data.token;
							layer.close(index);
							if(data.code != 200){
								layer.msg(data.msg, {icon: 5});
								return;
							}
							layer.msg("登录成功", {icon: 6});
							window.location.href="<%=request.getContextPath()%>/index/toIndex?token="+token;
						});
			});
		});
		
	</script>
</html>