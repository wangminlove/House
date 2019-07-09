//调取后台数据并在表格中显示
$(function () {
    $('#dg').datagrid({
        title: '区域信息',
        toolbar: "#ToolBar",//工具栏
        fit:true,
        fitColumns:true,
        rownumbers: true,//显示行号
        url: 'getDistrict',//后台控制器地址
        pagination: true,  //启用分页
        pageList: [6, 9], //设置每页大小
        pageSize: 3, //每页三条
        columns: [[
            //在每一行添加复选框
            {field: 'ck', checkbox: 'true'},
            {field: 'id', title: '用户编号', width: 100},
            {field: 'name', title: '用户名称', width: 100},
            {field: 'Operation', title: '操作', width: 100,align:"center",
                formatter: function (value, row, index) {
                    var e = '<a href="#" id="delete" onclick="do_delete(' +row.id+ ')">删除</a>&nbsp;&nbsp;&nbsp;' +
                        '<a href="#" onclick="do_street(' +row.id+ ')">街道信息</a> ';
                    return e;
                }
            }
        ]]
    });
});
function Add(id,title) {
    //打开对话框
    $("#"+id).dialog('open').dialog('setTitle', title);
}


function CloseDialog(id) {
    //关闭对话框
    $("#" + id).dialog('close')
}

//添加功能
function SaveDialog() {
    /*传统使用ajax技术实现添加
    1.获取表单对象的数据  2.使用ajax方法|post方法发送异步请求 3.处理回调用函数*/
    //2.使用easyui提交表单
    $("#ModiyDialogForm").form('submit', {
        url: "AddDistrict",//地址
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
    $.post("getSingleDistrict", {"id": row.id}, function (data) {
        //在表单中回显数据
        $('#UpdateDialogForm').form('load', data);
    })

}

function UpdateSaveDialog() {
    $("#UpdateDialogForm").form('submit', {
        url: "UpdateDistrict",
        success: function (data) {//此时data返回的json字符串
            alert(data);
            data = $.parseJSON(data);//将json字符串转化json对象
            if (data.result == 1) {
                $("#UpdateDialog").dialog('close'); //关闭
                $("#dg").datagrid('reload'); //刷新
            } else {
                $.messager.alert("系统提示", "修改失败");
            }
        }
    });
}
//删除单条
function do_delete(id) {
    $.messager.confirm('系统提示', '确定删除吗?', function(r){
        if (r){
            $.getJSON(
                "deleteDistrict",
                {"id":id},
                function (data) {
                    if (data.result == 1) {
                        $.messager.alert("系统提示", "删除成功");
                        $("#dg").datagrid('reload'); //刷新
                    }else{
                        $.messager.alert("系统提示", "删除失败");
                    }
                }
            )
        }
    });
}
function DeleteMoreById() {
    var SelectRows = $("#dg").datagrid('getSelections');
    if (SelectRows.length == 0) {
        $.messager.alert("系统提示", "请至少选择一行数据", "warning");
        return;
    }
    var value="";
    for (var i = 0; i < SelectRows.length; i++) {
        if (i < SelectRows.length - 1) {
            value=value+SelectRows[i].id+",";
        }else{
            value=value+SelectRows[i].id;
        }

    }
    $.messager.confirm('系统提示', '确定批量删除吗?', function(r){
        if (r){
            $.getJSON("deleteMoreDistrict",{"ids":value},function (data) {
                if (data.result > 0) {
                    $.messager.alert("系统提示", "批量删除成功");
                    $("#dg").datagrid('reload'); //刷新
                }else{
                    $.messager.alert("系统提示", "批量删除失败");
                }
            })
        }
    });
}
function do_street(id) {
    $("#ShowStreetDialog").dialog('open').dialog('setTitle', '街道明细');
    $('#sd').datagrid({
        /*toolbar: "#ToolBar",//工具栏*/
        rownumbers: true,//显示行号
        url: 'StreetByDid?id='+id,//后台控制器地址
        pagination: true,  //启用分页
        pageList: [6, 9], //设置每页大小
        fit:true,
        fitColumns:true,
        pageSize: 3, //每页三条
        columns: [[
            //在每一行添加复选框
            {field: 'ck', checkbox: 'true'},
            {field: 'id', title: '街道编号', width: 100},
            {field: 'name', title: '街道名称', width: 100},
            {field:'districtName',title:'区域名称',width:100,align:"center"},
            {field: 'Operation', title: '操作', width: 100,align:"center",
                formatter: function (value, row, index) {
                    var e = '<a href="#" id="delete" onclick="deleteStreet(' +row.id+ ')">删除</a>'
                    return e;
                }
            }
        ]]
    });
    $("#district_id").val(id);
}
function AddStreet() {
    /*传统使用ajax技术实现添加
   1.获取表单对象的数据  2.使用ajax方法|post方法发送异步请求 3.处理回调用函数*/
    //2.使用easyui提交表单
    $("#StreetDialogForm").form('submit', {
        url: "AddStreet",//地址
        success: function (data) {//此时data返回的json字符串
            data = $.parseJSON(data);//将json字符串转化json对象
            if (data.result == 1) {
                $.messager.alert("系统提示", "添加成功");
                /*$("#ShowStreetDialog").dialog('close'); //关闭*/
                $("#sd").datagrid('reload'); //刷新
            } else {
                $.messager.alert("系统提示", "添加失败");
            }
        }
    });
}
function deleteStreet(id) {
    $.messager.confirm('系统提示', '确定删除吗?', function(r){
        if (r){
            $.getJSON(
                "deleteStreet",
                {"id":id},
                function (data) {
                    if (data.result == 1) {
                        $.messager.alert("系统提示", "删除成功");
                        $("#sd").datagrid('reload'); //刷新
                    }else{
                        $.messager.alert("系统提示", "删除失败");
                    }
                }
            )
        }
    });
}