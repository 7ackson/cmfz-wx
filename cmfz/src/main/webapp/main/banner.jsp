<%@page contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript">
        $(function(){

            $("#insert").dialog({
                title:"添加",
                closed:true,
                buttons:"#Add"
            });

            $('#dg').edatagrid({
                url:'${pageContext.request.contextPath}/banner/selectByPage',
                pagination: true ,
                columns:[[
                    {field:'id',title:'编号',width:100,hidden:true},
                    {field:'name',title:'名字',width:100},
                    {field:'status',title:'状态',width:100,
                        editor: {
                            type: "text",
                            options: {
                                required: true
                            }
                        }
                    },
                ]],
                striped: true,
                closable: true,
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
                fit: true,
                fitColumns: true,
                pageSize: 5,
                pageList: [5, 10, 15],
                rownumbers:true,
                view: detailview,
                detailFormatter: function(rowIndex, rowData){
                    return '<table><tr>' +
                        '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/' + rowData.url + '" style="height:50px;"></td>' +
                        '<td style="border:0">' +
                        '<p>描述: ' + rowData.description + '</p>' +
                        '<p>上传日期: ' + rowData.createdate + '</p>' +
                        '</td>' +
                        '</tr></table>';
                }
            });
        });

        function doinsert(){
            $("#insertform").form("submit",{
                url:"${pageContext.request.contextPath}/banner/insert",
                success:function(data){
                    if(data){
                        alert("添加成功");
                        $("#insert").dialog("close");
                        $("#dg").datagrid("reload");
                    }else{
                        alert("添加失败");
                        $("#insert").dialog("close");
                    }
                }
            })
        }

    </script>
<table id="dg"></table>
<div id="Add">
    <a class="easyui-linkbutton" onclick="doinsert()">添加</a>
</div>
<div id="insert">
    <form id="insertform" method="post">
        名字:<input name="name" type="text"><br>
        地址:<input name="url" type="text"><br>
        描述:<input name="description" type="text"><br>
    </form>
</div>
