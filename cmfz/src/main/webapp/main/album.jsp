<%@page contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript">
        $(function(){


            $('#tt').treegrid({
                url:'${pageContext.request.contextPath}/album/selectAll',
                idField:'id',
                treeField:'name',
                columns:[[
                    {field:'id',title:'编号',width:60,align:'right',hidden:true},
                    {title:'名称',field:'name',width:180},
                    {field:'url',title:'下载路径',width:60,align:'right'},
                    {field:'size',title:'大小',width:80},
                    {field:'duration',title:'总时长',width:80}
                ]],
                onDblClickRow:function(rowIndex){
                    $("#mp3").dialog("open")
                    $("#yp").prop("src","${pageContext.request.contextPath}/mp3/"+rowIndex.url);
                },
                toolbar: [{
                    iconCls: 'icon-add',
                    text:"专辑详情",
                    handler: function(){
                        var row =  $('#tt').treegrid("getSelected");
                        if(row == null){
                            alert("请先选中专辑")
                        }else {
                            if(isNaN(row.id)){
                            $("#xq").dialog("open")
                            $('#zs').form('load',row);
                            var coverImg  = row.coverImg;
                            $("#tp").prop("src","${pageContext.request.contextPath}/upload/"+coverImg)
                            }else{
                                alert("请点击专辑")
                            }
                        }
                    }
                },'-',{
                    iconCls: 'icon-cut',
                    text: "添加专辑",
                    handler: function(){}
                },'-',{
                    iconCls: 'icon-cancel',
                    text: "添加章节",
                    handler: function(){}
                },'-',{
                    iconCls: 'icon-save',
                    text: "下载音频",
                    handler: function(){}
                }],
                method:"get",
                fit:true,
                fitColumns:true
            });
        })
    </script>

<table id="tt"></table>
<div id="mp3" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <audio id="yp" src="" controls = "controls" preload="auto"></audio>
</div>

<div id="xq" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="zs" method="post">
        <div>
            <label for="name">名字:</label>
            <input id="name" type="text" name="name"
                   style="width: 400px;" class="easyui-textbox"
                   data-options="required:true" />
        </div>
        <div>
            图片:<img id="tp" src="" style="width: 50px;height: 100px">
        </div>
        <div>
            <label for="broadCast">歌手:</label>
            <input id="broadCast" name="broadCast" style="width: 400px;" class="easyui-textbox"
                 data-options="required:true">
        </div>
        <div>
            <label for="brief">作者:</label>
            <input id="brief" name="brief" style="width: 400px;" class="easyui-textbox"
                   data-options="required:true">
        </div>
    </form>
</div>
