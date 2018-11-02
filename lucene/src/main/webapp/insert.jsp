<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head >

    <meta http-equiv="content-type" content="text/html;charset=UTF-8" >
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#sub").click(function () {
                $("#form2").form("submit",{
                    url:"${pageContext.request.contextPath }/lucene/insert",
                    success:function(data){
                        alert(data)
                        if(data=="true"){
                            window.location.href="${pageContext.request.contextPath }/selectAll.jsp"
                        }else{
                            window.location.href="${pageContext.request.contextPath }/insert.jsp"
                        }
                    }
                })
            })
        })

    </script>

</head>
<body>


<h1>添加商品</h1>
<form id="form2" >
    商品名称:<input type="text" name="name"><br/>
    商品描述:<input type="text" name="desc"><br/>
    商品图片:<input type="text" name="url"><br/>
    <input type="button" value="提交" id="sub"><br/>
</form>


</body>
