<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://222.222.124.77:9001/script/itoo_common.js"></script>
<link href="http://222.222.124.77:9001/style/itoo_common.css" type="text/css"
	rel="stylesheet"></link>
<title>视图工具 控件</title>
</head>
<body>
	<div id="pageTools" style="clear: both;">
		<!-- 视图转换 -->
		<!-- <input type="button" id="viewSwitch_center" value="Switch"
			class="easyui-linkbutton" data-options="iconCls:'icon-reload'"
			style="width: 4%; margin: 0 5px; float: right;"
			onclick="SwitchToggle()"> -->
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-reload" style="width: 4%; margin: 0 5px; float: right;"
			onClick="SwitchToggle()"></a>
		<!-- 视图缩放 -->
		<input id="pageZoomit" class="easyui-slider"
			style="width: 15%; margin: 0; padding: 0"
			data-options="showTip:false,min:12,max:24,step:6,
			 tipFormatter: function(value){
							return value+'px';
							},							
							onChange: function(value){
							$('body *').css('font-size',value);
							//搜索按钮设置--放大倍数为 1.5 
							SearchButtonDyn(zoomitPercent,value+'px');
							// 搜索文本框 设置
							SearchTextDyn(zoomitPercent,value+'px')
							}
			">

		<!-- <input type="button" id="viewSwitch" value="表格"  class="easyui-linkbutton"
			data-options="iconCls:'icon-reload'" style="display:none; width: 3%;float:right" onclick="Switch()">	 -->
	</div>
</body>
</html>