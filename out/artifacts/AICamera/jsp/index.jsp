<%--
  Created by IntelliJ IDEA.
  User: zaixz
  Date: 2020/4/6
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../layui/css/layui.css" media="all">
    <style type="text/css">
        .layui-table-cell {
            text-align: center;
            height: auto;
            white-space: normal;
        }

        .layui-table img {
            max-width: 300px
        }
    </style>

</head>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>历史查询</legend>
</fieldset>

<form class="layui-form" action="" method="post">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">日期范围</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" id="sltdate" placeholder=" - ">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">时间范围</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" id="slttime" placeholder=" - ">
            </div>
        </div>
    </div>

    <div class="layui-input-block">
        <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">查询</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
</form>


<table class="layui-hide" id="test" width="900px"></table>
<script src="../layui/layui.js" charset="utf-8"></script>
<script src="../js/jquery-3.0.0.js"></script>

<script>
    var table;
    layui.use('table', function () {
        table = layui.table;

        table.render({
            "elem": '#test'
            ,"width":900
            // ,url:'/demo/table/user/'
            , "data":${data}//[{"id":10000,"username":"user-0","sex":"女","city":"城市-0","sign":"签名-0","experience":255,"logins":24,"wealth":82830700,"classify":"作家","score":57}]
            , "cellMinWidth": 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , "cols": [[
                {field: 'id', width: 80, title: 'ID', sort: true}
                , {field: 'cameraComment', width: 80, title: '摄像头名称'}
                , {field: 'pictureUrl', width: 350, title: '截图', templet: "#imgtmp"}
                , {field: 'saveTime', width: 400, title: '截图时间'}
            ]]
        });
    });


    layui.use('form', function () {
        var form = layui.form;
        form.render(); //更新全部
        // form.render('input'); //刷新select选择框渲染

        //各种基于事件的操作，下面会有进一步介绍

        //监听提交
        form.on('submit(demo1)', function (data) {
            // layer.alert(JSON.stringify(data.field), {
            //     title: '最终的提交信息'
            // })

            console.log($('#sltdate').val());
            console.log($('#slttime').val());


            // console.log(table.cache);
            // console.log(typeof table.cache);
            <%--${pageContext.request.contextPath}--%>
            $.ajax({
                url: "/status/findhostory.do" + "?" + Math.random(),
                async: true,
                data: {
                    "date": $('#sltdate').val(),
                    "time": $('#slttime').val()
                },
                type: "POST",
                success: function (result) {
                    console.log(result);
                    // location.href=result;
                    //window.location.href("result")
                    // var json = JSON.parse(result);
                    table.reload('test', {
                        "data":JSON.parse(result),
                        // url: '/api/table/search'
                        // ,where: {} //设定异步数据接口的额外参数
                        //,height: 300
                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                    });
                }
            });


            console.log(JSON.stringify(data.field))
            return false;
        });
    });









    layui.use('laydate', function () {
        var laydate = layui.laydate;


        //日期范围
        laydate.render({
            elem: '#sltdate'
            , range: true
        });


        //时间范围
        laydate.render({
            elem: '#slttime'
            , type: 'time'
            , range: true
        });

    });
</script>


<script type="text/html" id="imgtmp">

    <div onclick="show_img(this)"><img src="{{ d.pictureUrl }}"></div>



</script>

<script>
    function show_img(t) {
        var t = $(t).find("img");
        //页面层
        layer.open({
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['80%', '80%'], //宽高
            shadeClose: true, //开启遮罩关闭
            end: function (index, layero) {
                return false;
            },
            content: '<div style="text-align:center"><img src="' + $(t).attr('src') + '" /></div>'
        });
    }

</script>

</body>
</html>
