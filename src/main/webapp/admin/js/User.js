$(function () {
    $('#dg').datagrid({
        title: '管理员信息',
        toolbar: "#ToolBar",//工具栏
        rownumbers: true,//显示行号
        url: 'getUsers',//后台控制器地址
        pagination: true,  //启用分页
        fit:true,
        fitColumns:true,
        pageList: [6, 9], //设置每页大小
        pageSize: 3, //每页三条
        columns: [[
            //在每一行添加复选框
            {field: 'ck', checkbox: 'true'},
            {field: 'id', title: '用户编号', width: 100},
            {field: 'name', title: '用户名称', width: 100},
            {field: 'telephone', title: '联系方式', width: 100},
            {field: 'Operation', title: '操作', width: 100,align:"center",
                formatter: function (value, row, index) {
                    var e = '<a href="#" id="delete" onclick="do_delete(' +row.id+ ')">删除</a> ';
                    return e;
                }
            }
        ]]
    });
});
function search() {
    //实现搜索查询
    //datagrid的load方法是重新加载，它会将查询条件，随着页码,页大小
    //一起发送到当前控制所指定的服务器地址进行处理
    //$("#dg").datagrid("load",传查询条件:{键:值,键:值});
    var sname=$("#name").val();
    var tle=$("#telephone").val();
    $("#dg").datagrid("load",{"name":sname,"telephone":tle})
}
function Add(id,title) {
    //打开对话框
    $("#"+id).dialog('open').dialog('setTitle', title);
}
function CloseDialog(id) {
    //关闭对话框
    $("#" + id).dialog('close')
}
//添加
function SaveDialog() {
    $("#ModiyDialogForm").form('submit', {
        url: "AddUsers",//地址
        success: function (data) {//此时data返回的json字符串
            data = $.parseJSON(data);//将json字符串转化json对象
            if (data.result == 1) {
                $.messager.alert("系统提示", "添加成功");
                $("#AddDialog").dialog('close'); //关闭
                $("#dg").datagrid('reload'); //刷新
            } else {
                $.messager.alert("系统提示", "添加失败");
            }
        }
    });
}
//修改
function ModifyBySelect() {
    //获取datagrid选中行,返回数组
    var SelectRows = $("#dg").datagrid('getSelections');
    //判断SelectRows数组长度是否等于1,不等于1的情况下发出提示
    if (SelectRows.length != 1) {
        $.messager.alert("系统提示", "请选择一行数据", "info");
        return;
    }
    //当长度==1时,先打开弹窗
    $("#UpdateDialog").dialog('open').dialog('setTitle', "修改数据>>>>>");
    /*将数据回显示到表单中  键需和表单对象名称相同
    $('#addDialogForm').form('load',json对象:{键:值,键:值..});*/
    var row = SelectRows[0];
    /*1.表中字段较少,页面可以全部显示时获取选中行对象进行回显示 {"id":xxx,"name":sss}
    $('#upDialogForm').form('load',row);*/
    /* 2.通过主键从数据库查询单条对象进行回显(当datagrid的行数据无法满足表单显示时)
     发送异步请求获取对象进行回显*/
    $.post("getSingleUsers", {"id": row.id}, function (data) {
        //在表单中回显数据
        $('#UpdateDialogForm').form('load', data);
    })
}

function UpdateSaveDialog() {
    $("#UpdateDialogForm").form('submit', {
        url: "UpdateUsers",
        success: function (data) {//此时data返回的json字符串
            alert(data);
            data = $.parseJSON(data);//将json字符串转化json对象
            if (data.result == 1) {
                $.messager.alert("系统提示", "修改成功");
                $("#UpdateDialog").dialog('close'); //关闭
                $("#dg").datagrid('reload'); //刷新
            } else {
                $.messager.alert("系统提示", "修改失败");
            }
        }
    });
}
function do_delete(id) {
    $.messager.confirm("操作提示", "您确定要执行删除吗？", function (data) {
        if (data) {
            $.getJSON(
                "deleteUsers",
                {"id":id},
                function (data) {
                    if (data.result==1){
                        $.messager.alert("系统提示", "删除成功");
                        $("#dg").datagrid('reload'); //刷新
                    }else {
                        $.messager.alert("系统提示", "删除失败");
                    }
                }
            )
        }
    });
}
function DeleteMoreById() {
    var SelectRows = $("#dg").datagrid('getSelections');
    //判断SelectRows数组长度是否等于1,不等于1的情况下发出提示
    if (SelectRows.length == 0) {
        $.messager.alert("系统提示", "请至少选择一行数据", "info");
        return;
    }
    var value="";
    for (var i = 0; i < SelectRows.length; i++) {
        value=value+SelectRows[i].id+",";
    }
    value=value.substring(0,value.length-1);
    $.messager.confirm("操作提示", "您确定要执行批量删除吗？", function (r) {
        if (r) {
            $.getJSON("deleteUserList", {"id": value}, function (data) {
                if (data.result > 0) {
                    $.messager.alert("系统提示", "删除成功");
                    $("#dg").datagrid('reload'); //刷新
                } else {
                    $.messager.alert("系统提示", "删除失败");
                }
            })
        }
    });
}