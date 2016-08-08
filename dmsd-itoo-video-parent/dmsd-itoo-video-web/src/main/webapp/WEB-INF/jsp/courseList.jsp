<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://222.222.124.77:9001/jquery.min.js"></script>
<script type="text/javascript" src="http://222.222.124.77:9001/jquery.easyui.min.js"></script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <%@ include file="/common.jsp" %>
    <base href="<%=basePath%>">
</head>

<script type="text/javascript">
    //添加课程实体，直接用hibernate封装的底层方法即可
     function addCourse(){
         //添加用POST提交方式，提交路径中为名词
         $.ajax({
             type:"POST",
             url:'<%=basePath%>'+"course/course1"
         })
     }

     //根据课程id查询课程信息 肖红  该方法返回的单个实体，用hibernate封装的底层方法即可
     function findCourseByCourseId(){
  	   $.ajax({
  		   type:"GET",
  		   url:'<%=basePath%>'+"course/course2/courseId/NknSa5kBA1JUkE56D3r3hU"
  	   })
     }
    
	//根据课程id删除课程    肖红 该方法删除的是单个课程实体，用hibernate封装的底层方法即可
   function deleteCourseByCourseId()
   {
       $.ajax({
           type:"POST",
           url:'<%=basePath%>'+"course/deleteCourse/Mis27v132ro4HodPivM1j9"
       })
   }

   
    
    //根据学生id查询课程名称 返回数据包括学生code，课程名称，课程id 封装为一个dto肖红  对外提供接口
    function getStudentCoruseById(){
    	$.ajax({
    		type:"GET",
    		url:'<%=basePath%>'+"course/course3/id/01b8774e-3a74-480189"
    	})
    }
    
    //根据课程id查询课程信息 需要调用基础系统的服务  肖红  
    function getCourseInfoByCourseId(){
    	$.ajax({
    		type:"GET",
    		url:'<%=basePath%>'+"course/course5/courseId/01b8774e-3a74-4801-97c"
    	})
    }

</script>
<body class="easyui-layout" fit="true">

<div>
	<input id="findCourseByCourseId" type="button" value="查询课程实体，调用hibernate封装的底层" onclick="findCourseByCourseId()">
    <input id="addCourse" type="button" value="添加课程，调用hibernate封装的底层" onclick="addCourse()">
    <input id="deleteCourseByCourseId" type="button" value="删除单个课程实体，调用hibernate封装的底层" onclick="deleteCourseByCourseId()">
    <input id="getStudentCoruseById" type="button" value="根据学生id查询返回dto实体，提供的接口" onclick="getStudentCoruseById()">
    <input id="getCourseInfoByCourseId" type="button" value="查询基础课程实体,调用基础服务" onclick="getCourseInfoByCourseId()">
</div>
</body>
</html>