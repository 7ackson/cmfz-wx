<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
</head>
<body>
<div id="zzt" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('zzt'));
    var option = {
        title: {
            text: '三个月内的注册信息'
        },
        tooltip: {},
        legend: {
            data:['注册数量']
        },
        xAxis: {
            data: ['第一周','第二周','第三周']
        },
        yAxis: {},
        series: [{
            name: '注册数量',
            type: 'bar',
        },]
    };
    $.ajax({
        url:"${pageContext.request.contextPath}/user/selectCount",
        type:"post",
        dataType:"json",
        success:function (data) {
            myChart.setOption(option);
            myChart.setOption({
                series: [{
                    name: '注册数量',
                    type: 'bar',
                    data:data
                }]
            });
        }
    })
</script>
</body>
</html>
