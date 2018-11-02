<%@page contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

    $(function () {

        $("#userbutton").linkbutton({
            onClick: function () {
                var titles = $("#user_dc").combotree("getText")
                var fileds = $("#user_dc").combotree("getValues")

                var a="";
                $.each(fileds, function (index, filed) {
                    if (index==fileds.length-1){
                        a+=filed;
                    }else {
                        a+=filed+",";
                    }
                })
                $("#user_from").form('submit',{
                    url:"${pageContext.request.contextPath}/poi/selectImport",
                    queryParams:{
                        titles:titles,
                        fileds:a
                    }
                })
            }
        })

        $('#userSelectAll').datagrid({
            url:'${pageContext.request.contextPath}/user/selectAll',
            columns:[[
                {field:'name',title:'昵称',width:100},
                {field:'sex',title:'性别',width:100,formatter: function(value,row){
                        if (row.sex == 0){
                            return "男";
                        } else {
                            return "女";
                        }
                    }
                },
                {field:'regDate',title:'生日',width:100,align:'right'}
            ]],
            striped: true,
            closable: true,
            fit: true,
            fitColumns: true,
            rownumbers:true,
            toolbar: [{
                iconCls: 'icon-add',
                text:"全部导出",
                handler: function(){
                    location.href="${pageContext.request.contextPath}/poi/deriveAll";
                }
            },'-',{
                iconCls: 'icon-cut',
                text: "全部导入",
                handler: function(){
                    $("#drinsert").dialog("open")

                }
            },'-',{
                iconCls: 'icon-cut',
                text: "自定义导出",
                handler: function(){
                    $("#user_all").dialog("open")
                }
            }]
        });
    });
</script>

<table id="userSelectAll"></table>

<div id="user_all" class="easyui-dialog" title="自定义导出" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <div style="text-align: center">
        <select id="user_dc" class="easyui-combotree" style="width:200px;" data-options="required:true,checkbox:true,multiple:true,onlyLeafCheck:true,
data:[{
		text: '请选择',
		state: 'closed',
		children: [{
		    id:'name',
			text: '昵称'
		},{
		id:'sex',
			text: '性别'
		},{
		id:'regDate',
			text: '生日'
		}]
	}]"></select>
        <form id="user_from" method="post">
            <a id="userbutton" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">确定</a>
        </form>
    </div>
</div>

<div id="drinsert" class="easyui-dialog" title="添加专辑" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'保存',
				handler:function(){
                    $('#drinsertform').form('submit',{
                url:'${pageContext.request.contextPath}/poi/importAll',
                success:function(data){
                    if(data){
                        alert('添加成功');
                        $('#drinsert').dialog('close');
                        $('#userSelectAll').datagrid('reload');
                    }else{
                        alert('添加失败');
                        $('#userSelectAll').datagrid('close');
                    }
                }
            })
				}
			},{
				text:'关闭',
				handler:function(){
				 $('#drinsert').dialog('close')
				}
			}]">
    <form id="drinsertform" method="post" enctype="multipart/form-data">
        <div>
            <label for="excel">导入:</label>
            <input id="excel" class="easyui-filebox" name="excel" style="width:300px">
        </div>
    </form>
</div>