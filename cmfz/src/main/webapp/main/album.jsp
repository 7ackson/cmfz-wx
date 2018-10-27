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
                    {field:'size',title:'大小',width:80},
                    {field:'duration',title:'总时长',width:80}
                ]],
                onDblClickRow:function(rowIndex){
                    $("#mp3").dialog("open")
                    $("#yp").prop("src","${pageContext.request.contextPath}/mp3/"+rowIndex.url);
                },
                toolbar: [{
                    iconCls: 'icon-search',
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
                    iconCls: 'icon-add',
                    text: "添加专辑",
                    handler: function(){$("#zjinsert").dialog("open");}
                },'-',{
                    iconCls: 'icon-add',
                    text: "添加章节",
                    handler: function(){
                        var row =  $('#tt').treegrid("getSelected");
                        if(row == null){
                            alert("请先选中专辑")
                        }else {
                            if(isNaN(row.id)){
                                $("#albumName").val(row.id)
                                $("#chapterinsert").dialog("open")
                            }else{
                                alert("请点击专辑")
                            }
                        }
                    }
                },'-',{
                    iconCls: 'icon-save',
                    text: "下载音频",
                    handler: function(){
                        var row = $("#tt").treegrid("getSelected");
                        if(row == null){
                            alert("请先选中章节")
                        }else {
                            if(!isNaN(row.id)){
                                location.href="${pageContext.request.contextPath}/chapter/down?name="+row.name+"&url="+row.url;
                            }else{
                            alert("请点击章节")
                            }
                        }
                    }
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

<div id="zjinsert" class="easyui-dialog" title="添加专辑" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'保存',
				handler:function(){
                    $('#zjinsertform').form('submit',{
                url:'${pageContext.request.contextPath}/album/zjinsert',
                success:function(data){
                    if(data){
                        alert('添加成功');
                        $('#zjinsert').dialog('close');
                         $('#tt').treegrid('reload');
                    }else{
                        alert('添加失败');
                         $('#tt').treegrid('close');
                    }
                }
            })
				}
			},{
				text:'关闭',
				handler:function(){
				 $('#zjinsert').dialog('close')
				}
			}]">
    <form id="zjinsertform" method="post" enctype="multipart/form-data">

        <div>
            <label for="name1">名字:</label>
            <input id="name1" class="easyui-validatebox" type="text" name="name" data-options="required:true" />
        </div>

        <div>
            <label for="url">图片:</label>
            <input id="url" class="easyui-filebox" name="cover" style="width:300px">
        </div>

        <div>
            <label for="score">评分:</label>
            <input id="score" class="easyui-validatebox" type="text" name="score" data-options="required:true" />
        </div>

        <div>
            <label for="author">作者:</label>
            <input id="author" class="easyui-validatebox" type="text" name="author" data-options="required:true" />
        </div>

        <div>
            <label for="broadCast1">歌手:</label>
            <input id="broadCast1" class="easyui-validatebox" type="text" name="broadCast" data-options="required:true" />
        </div>

        <div>
            <label for="brief1">描述:</label>
            <input id="brief1" class="easyui-validatebox" type="text" name="brief" data-options="required:true" />
        </div>
    </form>
</div>

<div id="chapterinsert" class="easyui-dialog" title="添加专辑" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'保存',
				handler:function(){
                    $('#chapterinsertform').form('submit',{
                url:'${pageContext.request.contextPath}/chapter/chapterinsert',
                success:function(data){
                    if(data){
                        alert('添加成功');
                        $('#chapterinsert').dialog('close');
                         $('#tt').treegrid('reload');
                    }else{
                        alert('添加失败');
                         $('#tt').treegrid('close');
                    }
                }
            })
				}
			},{
				text:'关闭',
				handler:function(){
				 $('#chapterinsert').dialog('close')
				}
			}]">
    <form id="chapterinsertform" method="post" enctype="multipart/form-data">
        <div>
            <input id="albumName" name="albumName" type="text">
        </div>
        <div>
            <label for="title">章节名:</label>
            <input id="title" class="easyui-validatebox" type="text" name="name" data-options="required:true" />
        </div>

        <div>
            <label for="url1">歌曲:</label>
            <input id="url1" class="easyui-filebox" name="urlMp3" style="width:300px">
        </div>

    </form>
</div>

