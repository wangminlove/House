$(function () {
    $('#dg').datagrid({
        title: '未审核房屋信息',
        toolbar: "#ToolBar",//工具栏
        rownumbers: true,//显示行号
        url: 'ispassHouse',//后台控制器地址
        pagination: true,  //启用分页
        fit:true,
        fitColumns:true,
        pageList: [6, 9], //设置每页大小
        pageSize: 3, //每页三条
        columns: [[
            //在每一行添加复选框
            {field: 'ck', checkbox: 'true'},
            {field: 'id', title: '房屋编号', width: 100},
            {field: 'title', title: '标题', width: 100},
            {field: 'pubdate', title: '发布时间', width: 100,
                formatter: function (value, row, index) {
                    var date=new Date(value);
                    var year=date.getFullYear();
                    var month=date.getMonth()+1;
                    var day=date.getDay();
                    return year+"年"+month+"月"+day+"日";
                }},
            {field: 'floorage', title: '面积', width: 100},
            {field: 'price', title: '房租', width: 100},
            {field: 'contact', title: '联系方式', width: 100},
            {field: 'tname', title: '房屋类型', width: 100},
            {field: 'dname', title: '区域', width: 100},
            {field: 'sname', title: '街道', width: 100},
            {field: 'description', title: '房屋描述', width: 100},
            {field: 'Operation', title: '操作', width: 100,align:"center",
                formatter: function (value, row, index) {
                    var e = '<a href="#" id="delete" onclick="HouseNoPass(' +row.id+ ')">未审核</a> ';
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
    var title=$("#title").val();
    var min_price=$("#min_price").val();
    var max_price=$("#max_price").val();
    $("#dg").datagrid("load",{"title":title,"min_price":min_price,"max_price":max_price})
}
function HouseNoPass(id) {
    $.post("HouseNoPass",{"id":id},function (data) {
        if (data.result>0){
            $.messager.alert("提示框","房屋审核状态已更新为未审核!")
            $("#dg").datagrid("reload")
        }else{
            $.messager.alert("提示框","操作失败!")
        }
    },"json")
}