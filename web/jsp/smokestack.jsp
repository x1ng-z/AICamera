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
    <link rel="stylesheet" href="../js/layui/css/layui.css" media="all">
    <style type="text/css">
        .layui-table-cell {
            text-align: center;
            height: auto;
            white-space: normal;
        }

        .layui-table img {
            max-width: 300px;
        }

        /*.layui-admin-card-header{*/
        /*    padding: 150px;*/
        /*}*/
        /*.layui-admin-card-body{*/
        /*    padding: 500px;*/
        /*}*/
    </style>

</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>历史查询</legend>
</fieldset>

<form class="layui-form" action="" method="post">

    <div class="layui-form-item">

        <label class="layui-form-label">区域：</label>
        <div class="layui-input-inline" style="padding-right:13px;">
            <select name="quiz1" id="selProvince" onchange="provinceChange();" lay-verify="required"></select>
        </div>
        <label class="layui-form-label">子公司：</label>
        <div class="layui-input-inline" style="padding-right:14px;">
            <select name="quiz2" id="selCity" lay-verify="required"></select>
        </div>


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


<table class="layui-hide" id="test" width="900px" height="900px"></table>


<script src="../js/layui/layui.js" charset="utf-8"></script>
<script src="../js/jquery-3.0.0.js"></script>

<script type="text/html" id="switchTpl1">
    <!-- 这里的 checked 的状态只是演示 -->
    <input type="checkbox" name="man_except" value="{{d.man_except}}" lay-skin="switch" lay-text="异常|正常" lay-filter="sexDemo" {{ d.man_except == "异常" ? 'checked' : '' }}>
</script>
<script type="text/html" id="switchTpl2">
    <!-- 这里的 checked 的状态只是演示 -->
    <input type="checkbox"  disabled name="auto_except" value="{{d.auto_except}}" lay-skin="switch" lay-text="异常|正常" lay-filter="sexDemo" {{ d.auto_except == "异常" ? 'checked' : '' }}>
</script>

<script>
    var table;
    var form;
    layui.use('table', function () {
        table = layui.table;
        form = layui.form;

        table.render({
            "elem": '#test'
            //, "hight": "full-20"
            //, "width": 900
            // ,url:'/demo/table/user/'
            , "data":${data}//[{"id":10000,"username":"user-0","sex":"女","city":"城市-0","sign":"签名-0","experience":255,"logins":24,"wealth":82830700,"classify":"作家","score":57}]
            , "cellMinWidth": 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , "cols": [[
                {field: 'id', width: 80, title: 'ID', sort: true}
                , {field: 'cameraComment', width: 80, title: '摄像头名称'}
                , {field: 'pictureUrl', width: 350, title: '截图', templet: "#imgtmp"}
                ,{field:'man_except', title:'人工判定', width:150, templet: '#switchTpl1', unresize: true}
                , {field:'auto_except', title:'系统判定', width:150, templet: '#switchTpl2', unresize: true}
                , {field: 'saveTime', width: 400, title: '截图时间'}
            ]]
        });
        //监听性别操作
        form.on('switch(sexDemo)', function(obj){
            layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
        });
    });





    //定义数组，存储省份信息
    var province = ["-","浙江区域", "杭州区域", "江西区域", "湖南区域", "福建区域", "云南区域", "贵州区域", "广西区域", "四川区域", "集团直属", "海外区域"];

    //定义数组,存储城市信息
    var zhejiang = ["兰溪红狮", "兰溪超峰"];
    var hangzhou = ["建德红狮", "桐庐红狮", "青龙山"];
    var jiangxi = ["高安红狮", "会昌红狮", "江西鹰鹏", "宜春红狮", "吉安宏光", "抚州红狮"];
    var hunan = ["永州红狮", "衡阳红狮", "宁远莲花", "宜章红狮"];
    var fujian = ["漳平红狮", "漳平三期", "大田红狮", "龙海红狮", "漳州紫金"];
    var yunnan = ["宜良红狮", "永平无量山"];
    var guizhou = ["龙里红狮", "贵州茂鑫"];
    var guangxi = ["崇左红狮", "南宁红狮", "广西恒庆"];
    var sichuan = ["邻水红狮", "江油红狮", "长宁红狮", "南充红狮"];
    var jituanzhishu = ["库车红狮", "兰州红狮"];
    var haiwaiquyu = ["万象红狮", "希望红狮"];




    layui.use('form', function () {
        form = layui.form;
        form.render(); //更新全部
        // form.render('input'); //刷新select选择框渲染

        //各种基于事件的操作，下面会有进一步介绍



        //各种基于事件的操作，下面会有进一步介绍
        //设置省份数据
        setProvince();
        //监听下拉事件
        form.on('select', function (data) {
            setCity(data.value);
            console.log(data.value);

        });



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
                        "data": JSON.parse(result),
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


    //设置省份数据
    function setProvince() {
        //给省份下拉列表赋值

        var $sel = $("#selProvince");

        //获取对应省份城市
        for (var i = 0, len = province.length; i < len; i++) {
            modelVal = province[i];
            var option = $("<option value='" + province[i] + "'>" + province[i] + "</option>");

            //添加到 select 元素中
            $sel.append(option);
        }
        form.render('select');
        setCity($($sel.get(0)).val());

    }

    //根据选中的省份获取对应的城市
    function setCity(provinec) {
        var $city = $("#selCity");
        var proCity=null, option, modelVal;

        //通过省份名称，获取省份对应城市的数组名
        switch (provinec) {
            case "浙江区域":
                proCity = zhejiang;
                break;
            case "杭州区域":
                proCity = hangzhou;
                break;
            case "江西区域":
                proCity = jiangxi;
                break;
            case "湖南区域":
                proCity = hunan;
                break;
            case "福建区域":
                proCity = fujian;
                break;
            case "贵州区域":
                proCity = guizhou;
                break;
            case "广西区域":
                proCity = guangxi;
                break;
            case "四川区域":
                proCity = sichuan;
                break;
            case "集团直属":
                proCity = jituanzhishu;
                break;
            case "海外区域":
                proCity = haiwaiquyu;
                break;
            default:
                proCity=null;

        }

        //先清空之前绑定的值
        $city.empty();
        if(proCity!=null){
            //设置对应省份的城市
            for (var i = 0, len = proCity.length; i < len; i++) {
                modelVal = proCity[i];
                option = "<option value='" + modelVal + "'>" + modelVal + "</option>";

                //添加
                $city.append(option);
            }
            form.render('select');
        }

    }

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
            content: '<div style="text-align:center"><img src="' + $(t).attr('src') + '"  /></div>'
        });
    }

</script>


</body>
</html>
