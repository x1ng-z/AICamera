<%--
  Created by IntelliJ IDEA.
  User: zaixz
  Date: 2020/4/15
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%pageContext.setAttribute("path", request.getContextPath()); %>


<html>

    
<head>
            
    <meta charset="utf-8">
            <title>后台教务系统</title>
            
            
    <meta name="renderer" content="webkit">
            
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
            
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
            
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
            
    <meta name="apple-mobile-web-app-capable" content="yes">
            
    <meta name="format-detection" content="telephone=no">

            
    <link rel="stylesheet" href="../layui/css/layui.css" media="all"/>
            
    <script type="text/javascript" src="../layui/layui.js"></script>
            
    <link rel="stylesheet" href="../css/global.css" media="all">
            
    <link rel="stylesheet" href="${path}/static/plugins/font-awesome/css/font-awesome.min.css">
            
            
            
    <script type="text/javascript" src="${path}/static/js/nav.js"></script>
            
    <script src="${path}/static/js/index.js"></script>

        
</head>

    
<body>
        
<div class="layui-layout layui-layout-admin" style="border-bottom: solid 5px #1aa094;">
                
    <div class="layui-header header header-demo">
                        
        <div class="layui-main">
                                
            <div class="admin-login-box">
                                        <a class="logo" style="left: 0;" href="${path}/indexTo">
                                            <span style="font-size: 22px;color:#009688;">后台教务系统</span>
                                        </a>
                                        
                <div class="admin-side-toggle">
                                                <i class="fa fa-bars" aria-hidden="true"></i>
                                            
                </div>
                                        
                <div class="admin-side-full">
                                                <i class="fa fa-life-bouy" aria-hidden="true"></i>
                                            
                </div>
                                    
            </div>
                                
            <ul class="layui-nav admin-header-item">
                                        
                <li class="layui-nav-item">
                                                <a href="javascript:;">清除缓存</a>
                                            
                </li>
                                        
                                        
                <li class="layui-nav-item">
                                                <a href="javascript:;">浏览网站</a>
                                            
                </li>
                                        
                <li class="layui-nav-item" id="video1">
                                                <a href="javascript:;">精彩视频</a>
                                            
                </li>
                                        
                <li class="layui-nav-item">
                                                <a href="javascript:;" class="admin-header-user">
                                                    <img src="${path}/static/images/myself.jpg"/>
                                                    <span style="color:#009688">欢迎:${user.username }登陆!</span>
                                                </a>
                                                
                    <dl class="layui-nav-child">
                                                        
                        <dd>
                                                                <a href="javascript:;"><i class="fa fa-user-circle"
                                                                                          aria-hidden="true"></i>
                            个人信息</a>
                                                            
                        </dd>
                                                        
                        <dd>
                                                                <a href="javascript:;"><i class="fa fa-gear"
                                                                                          aria-hidden="true"></i> 设置</a>
                                                            
                        </dd>
                                                        
                        <dd id="lock">
                                                                <a href="javascript:;">
                                                                    <i class="fa fa-lock" aria-hidden="true"
                                                                       style="padding-right: 3px;padding-left: 1px;"></i>
                            锁屏 (Alt+L)
                                                                </a>
                                                            
                        </dd>
                                                        
                        <dd>
                                                                <a href="${path}/loginOutTo"><i class="fa fa-sign-out"
                                                                                                aria-hidden="true"></i>
                            注销</a>
                                                            
                        </dd>
                                                    
                    </dl>
                                            
                </li>
                                    
            </ul>
                                
            <ul class="layui-nav admin-header-item-mobile">
                                        
                <li class="layui-nav-item">
                                                <a href="${path}/loginOutTo"><i class="fa fa-sign-out"
                                                                                aria-hidden="true"></i> 注销</a>
                                            
                </li>
                                    
            </ul>
                            
        </div>
                    
    </div>
                
    <div class="layui-side layui-bg-black" id="admin-side">
                        
        <div class="layui-side-scroll" id="admin-navbar-side" lay-filter="side"></div>
                    
    </div>
                
    <div class="layui-body" style="bottom: 0;border-left: solid 2px #1AA094;" id="admin-body">
                        
        <div class="layui-tab admin-nav-card layui-tab-brief" lay-filter="admin-tab">
                                
            <ul class="layui-tab-title">
                                        
                <li class="layui-this">
                                                <i class="fa fa-dashboard" aria-hidden="true"></i>
                                                <cite>主界面</cite>
                                            
                </li>
                                    
            </ul>
                                
            <div class="layui-tab-content" style="min-height: 150px; padding: 5px 0 0 0;">
                                        
                <div class="layui-tab-item layui-show">
                                                
                    <iframe src="${path}/mainTo"></iframe>
                                            
                </div>
                                    
            </div>
                            
        </div>
                    
    </div>
                
    <div class="layui-footer footer footer-demo" id="admin-footer">
                        
        <div class="layui-main">
                                <p>2019年3月&nbsp;author:李庆照 &nbsp;class:软件技术161301
                                    <a href="http://weibo.com"></a>&nbsp;<a href="http://www.mycodes.net/"
                                                                            target="_blank">指导老师:吕太之</a>
                                </p>
                            
        </div>
                    
    </div>
                
    <div class="site-tree-mobile layui-hide">
                        <i class="layui-icon">&#xe602;</i>
                    
    </div>
                
    <div class="site-mobile-shade"></div>
                
                <!--锁屏模板 start-->
                
    <script type="text/javascript" id="lock-temp">
        <%--<div class="admin-header-lock" id="lock-box">--%>
        <%--    <div class="admin-header-lock-img">--%>
        <%--    <img src="${path}/static/images/0.jpg"/>--%>
        <%--    </div>--%>
        <%--    <div class="admin-header-lock-name" id="lockUserName">beginner</div>--%>
        <%--    <input type="text" class="admin-header-lock-input" value="输入密码解锁.." name="lockPwd" id="lockPwd" />--%>
        <%--    <button class="layui-btn layui-btn-small" id="unlock">解锁</button>--%>
        <%--    </div>--%>
    </script>
                <!--锁屏模板 end -->
              
            
</div>
    
</body>
    
<script>
    layui.use('layer', function () {
        var $ = layui.jquery,
            layer = layui.layer;

        $('#video1').on('click', function () {
            layer.open({
                title: 'YouTube',
                maxmin: true,
                type: 2,
                content: '${path}/videoTo',
                area: ['800px', '500px']
            });
        });
        $('#pay').on('click', function () {
            layer.open({
                title: false,
                type: 1,
                content: '<img src="${path}/static/images/xx.png" />',
                area: ['500px', '250px'],
                shadeClose: true
            });
        });


    });
</script>
</html>
