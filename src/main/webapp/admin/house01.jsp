<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
<link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
<script src="js/jquery-1.8.3.js"></script>
<!--jquery.easyui.min.js包含了easyUI中的所有插件-->
<script src="js/jquery.easyui.min.js"></script>
<script src="js/house01.js"></script>
<head>
    <title>Title</title>
</head>
<body>
<table id="dg"></table>
<%--表单中的按钮--%>
<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:Add('AddDialog','添加数据>>>>>')" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a> <a
            href="javascript:ModifyBySelect()" class="easyui-linkbutton"
            iconCls="icon-edit" plain="true">修改</a> <a
            href="javascript:DeleteMoreById()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">批量删除</a>
    </div>
    <div>
        房屋标题:<input type="text" name="title" id="title">
        租金:<input type="text" name="min_price" id="min_price">到
        <input type="text" name="max_price" id="max_price">
        <a href="javascript:search()" class="easyui-linkbutton"
           iconCls="icon-search" plain="true">查询</a>
    </div>
</div>
<%--添加操作的弹窗--%>
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <%--添加弹窗中的表单--%>
    <form id="ModiyDialogForm" method="post">
        <table>
            <input type="hidden" name="isadmin" value=1>
            <tr>
                <td>姓名:</td>
                <td><input type="text" name="name" id="sname"/></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input type="text" name="password" id="pwd"/></td>
            </tr>
            <tr>
                <td>电话:</td>
                <td><input type="text" name="telephone" id="tle"/></td>
            </tr>
        </table>
    </form>
</div>
<%--添加的弹窗中的保存和取消的按钮--%>
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog('AddDialog')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
<%--修改操作的弹窗--%>
<div id="UpdateDialog" class="easyui-dialog" buttons="#UpdateDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <%--修改弹窗中的表单--%>
    <form id="UpdateDialogForm" method="post">
        <table>
            <input type="hidden" name="isadmin" value=1>
            <tr>
                <td>姓名:</td>
                <td><input type="text" readonly style="border: none" name="id" id="xid"/></td>
            </tr>
            <tr>
                <td>姓名:</td>
                <td><input type="text" name="name" id="xname"/></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input type="text" name="password" id="xpwd"/></td>
            </tr>
            <tr>
                <td>电话:</td>
                <td><input type="text" name="telephone" id="xtle"/></td>
            </tr>
        </table>
    </form>
</div>
<%--修改的弹窗中的保存和取消的按钮--%>
<div id="UpdateDialogButtons">
    <a href="javascript:UpdateSaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">修改</a>
    <a href="javascript:CloseDialog('UpdateDialog')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
</body>
</html>
