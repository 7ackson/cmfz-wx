<%@page contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript">
        $(function(){
            $('#tt').treegrid({
                url:'${pageContext.request.contextPath}/album/selectAll',
                idField:'id',
                treeField:'name',
                columns:[[
                    {title:'名称',field:'name',width:180},
                    {field:'url',title:'下载路径',width:60,align:'right'},
                    {field:'size',title:'大小',width:80},
                    {field:'duration',title:'总时长',width:80}
                ]],
                toolbar: [{
                    iconCls: 'icon-add',
                    text:"添加",
                    handler: function(){$("#insert").dialog("open");}
                },'-',{
                    iconCls: 'icon-cut',
                    text: "修改",
                    handler: function(){
                        var row = $("#dg").edatagrid("getSelected");
                        if (row == null) {
                            alert("请先选中行")
                        } else {
                            var index = $('#dg').edatagrid("getRowIndex",row);
                            $('#dg').edatagrid("editRow",index);
                        }
                    }
                },'-',{
                    iconCls: 'icon-cancel',
                    text: "删除",
                    handler: function(){
                        var id = $("#dg").datagrid("getSelections");
                        if(id.length != 0){
                            $.messager.confirm('确认对话框', '您想要删除该数据吗？', function(r){
                                if (r){
                                    var ids = new Array(id.length);
                                    for(var i = 0;i<ids.length;i++){
                                        ids[i]=id[i].id;
                                    }
                                    $.ajax({
                                        url:"${pageContext.request.contextPath}/banner/delete",
                                        data:{"ids":ids},
                                        traditional:true,
                                        async:false,
                                        success:function(data){
                                            if(data){
                                                alert("删除成功")
                                                $("#dg").datagrid("reload");
                                            }else{
                                                alert("删除失败")
                                                $("#dg").datagrid("reload");
                                            }
                                        }
                                    })
                                }
                            });
                        }
                    }
                },'-',{
                    iconCls: 'icon-save',
                    text: "保存",
                    handler: function(){
                        $('#dg').edatagrid({
                            updateUrl: '${pageContext.request.contextPath}/banner/update',
                        });
                    }
                }],
                method:"get",
                fit:true,
                fitColumns:true
            });
        })
    </script>

<table id="tt"></table>
