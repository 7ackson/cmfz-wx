<%@page contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript">
        $(function(){

            $('#dg').edatagrid({
                url:'${pageContext.request.contextPath}/banner/selectByPage',
                pagination: true ,
                updateUrl: "${pageContext.request.contextPath}/banner/update",
                destroyUrl:'${pageContext.request.contextPath}/banner/delete',
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
                    handler: function() {
                        $("#dg").edatagrid("destroyRow");
                    }
                },'-',{
                    iconCls: 'icon-save',
                    text: "保存",
                    handler: function(){
                        $('#dg').edatagrid("saveRow");
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
                        '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/upload/' + rowData.url + '" style="height:50px;"></td>' +
                        '<td style="border:0">' +
                        '<p>描述: ' + rowData.description + '</p>' +
                        '<p>上传日期: ' + rowData.createdate + '</p>' +
                        '</td>' +
                        '</tr></table>';
                }
            });
        });


    </script>
<table id="dg"></table>
<div id="Add">
    <a class="easyui-linkbutton" onclick="doinsert()">添加</a>
</div>
<div id="insert" class="easyui-dialog" title="添加轮播图" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'保存',
				handler:function(){
                    $('#insertform').form('submit',{
                url:'${pageContext.request.contextPath}/banner/insert',
                success:function(data){
                    if(data){
                        alert('添加成功');
                        $('#insert').dialog('close');
                        $('#dg').datagrid('reload');
                    }else{
                        alert('添加失败');
                        $('#insert').dialog('close');
                    }
                }
            })
				}
			},{
				text:'关闭',
				handler:function(){
				 $('#insert').dialog('close')
				}
			}]">
    <form id="insertform" method="post" enctype="multipart/form-data">
        <div>
            <label for="name">名字:</label>
            <input id="name" class="easyui-validatebox" type="text" name="name" data-options="required:true" />
        </div>
        <div>
            <label for="description">描述:</label>
            <input id="description" class="easyui-validatebox" type="text" name="description" data-options="required:true" />
        </div>
        <div>
            <label for="url">图片:</label>
            <input id="url" class="easyui-filebox" name="img" style="width:300px">
        </div>

        <select id="status" class="easyui-combobox" name="status" style="width:200px;">
            <option value="0">展示</option>
            <option value="1">不展示</option>
        </select>
    </form>

</div>

