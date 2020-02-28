<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src = "<%=request.getContextPath()%>/res/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src = "<%=request.getContextPath()%>/res/layer/layer.js"></script>
</head>
<body>
	<form id="frm">
		<input type = "button" value = "新增" onclick = "insert()">
		<input type="hidden" value="1" id="pageNo" name="page">
		<input type="hidden" value="${token}"  name="token">
		<table border="1px solid red" cellpadding="20" >
			<tr>
				<th>用户</th>
				<th>密码</th>
				<th>电话</th>
				<th>邮箱</th>
				<th>职位</th>
				<th>头像</th>
				<th colspan="2" align="center">操作</th>
			</tr>
			<tbody id = "tbd">
			</tbody>
		</table>
	</form>
	<div id="pageInfo"></div>
</body>
	<script type="text/javascript">
	$(function(){
		show();	
	})
	function show(){
		$.post("<%=request.getContextPath()%>/user/userShow",
				$("#frm").serialize(),
				function(data) {
					var html = "";
					for (var i = 0; i < data.data.userList.length; i++) {
						var user = data.data.userList[i];
						html+="<tr>";
						html+="<td>"+user.userName+"</td>"
						html+="<td>"+user.userPwd+"</td>"
						html+="<td>"+user.phone+"</td>"
						html+="<td>"+user.email+"</td>"
						html+="<td>"+user.levelShow+"</td>"
						html+="<td>"
						html+='<img src="http://q4370wf9l.bkt.clouddn.com/'+user.img+'" width="100px" height="100px">'
						html+="</td>"
						html+="<td><input type='button' value='修改'  onclick='toUpdate("+user.id+")'></td>"
						html+="<td><input type='button' value='删除'  onclick='del("+user.id+")'></td>"
						html+="</tr>"
				}
			$("#tbd").html(html);
			var pageNo = $("#pageNo").val();
			var pageInfo = "<input type='button' value='上一页'  onclick='page("+(parseInt(pageNo)-1)+")'>"
				pageInfo +="<input type='button' value='下一页'  onclick='page("+(parseInt(pageNo)+1)+","+data.data.total+")'>"
			$("#pageInfo").html(pageInfo);
		})
	}
	
	//增加用户
	function insert(){
		layer.open({
	      type: 2,
		  title: '增加页面',
		  shadeClose: true,
		  shade: 0.8,
		  area: ['380px', '70%'],
		  //路径
            content:  "<%=request.getContextPath()%>/user/toRegister?token="+'${token}'
		});
	}
		

	function toUpdate(id) {
			layer.open({
		          type: 2,
				  title: '修改页面',
				  shadeClose: true,
				  shade: 0.8,
				  area: ['385px', '75%'],
			  //路径
		            content: "<%=request.getContextPath()%>/user/toUpdate?id="+id+"&token="+'${token}'

			}); 

}
	function del(id){
		layer.confirm('确定删除？', {icon: 3, title:'提示'}, function(index){
		    layer.close(index);
			$.post("<%=request.getContextPath()%>/user/deleteUser",
					{"id":id,"token":'${token}'},
		    		function(data){
		    			if(data.code == 200){
		    				layer.msg(data.msg, {
								  icon: 6,
								  time: 2000 //2秒关闭（如果不配置，默认是3秒）
								}, function(){
								  //do something
		    						window.location.href="<%=request.getContextPath()%>/user/toShow?token=${token}";
								});   
		    				return;
		    			}
		    			layer.msg(data.msg, {icon: 5});
		    		})
		    	});
	}
	
	function page (page,total) {
		if (page < 1) {
			layer.msg("已经是首页了");
			return;
		}
		if (total < page) {
			layer.msg("已经是尾页了");
			return;
		}
		 $("#pageNo").val(page);
		 show();
	}
	function insert(){
		layer.open({
			type: 2,
			title: '增加页面',
			shadeClose: true,
			shade: 0.8,
			area: ['380px', '70%'],
			//路径
			content:  '<%=request.getContextPath()%>/user/toRegister?token='+'${token}'
		});
	}
	</script>
</html>