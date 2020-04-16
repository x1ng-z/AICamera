<%--
  Created by IntelliJ IDEA.
  User: zaixz
  Date: 2020/4/15
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%pageContext.setAttribute("path", request.getContextPath()); %>
<!DOCTYPE html>
<html>

    
<head>
            
    <meta charset="UTF-8">
            <title></title>
            
    <link rel="stylesheet" href="${path}/static/plugins/layui/css/layui.css" media="all"/>
            
    <link rel="stylesheet" href="${path}/static/css/main.css"/>
        
</head>

    
<body>
        
<div class="admin-main">
                欢迎来到教务系统！请选择服务！！！
            
</div>
        
<table class="layui-table">
     
    <colgroup>
           
        <col width="150">
           
        <col width="200">
           
        <col>
         
    </colgroup>
     
    <thead>
       
    <tr>
             
        <th>昵称</th>
             
        <th>加入时间</th>
             
        <th>签名</th>
           
    </tr>
     
     
    </thead>
     
    <tbody>
       
    <tr>
             
        <td>贤心</td>
             
        <td>2016-11-29</td>
             
        <td>人生就像是一场修行</td>
           
    </tr>
       
    <tr>
             
        <td>许闲心</td>
             
        <td>2016-11-28</td>
             
        <td>于千万人之中遇见你所遇见的人，于千万年之中，时间的无涯的荒野里…</td>
           
    </tr>
     
    </tbody>
</table>
      
    
</body>

</html>
