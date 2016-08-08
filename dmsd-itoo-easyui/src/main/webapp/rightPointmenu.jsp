<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="http://222.222.124.77:9001/script/rightPointmenu.js">
	</script>
</head>
<body>
  <!--放置一个隐藏的菜单Div用于右键弹出----宋笑-----2015年12月26日-->
	<div id="RightPointmenu" class="easyui-menu"
		style="width: 50px; display: none;">
		<div data-options="iconCls:'icon-add'" onclick="add()">添加</div>
		<div data-options="iconCls:'icon-remove'"
			onclick="del()">删除</div>
		 <div data-options="iconCls:'icon-edit'" onclick="edit()">编辑</div> 
	</div>
	<!--放置一个隐藏的菜单Div用于右键弹出----宋笑-----2015年12月26日-->
</body>
</html>