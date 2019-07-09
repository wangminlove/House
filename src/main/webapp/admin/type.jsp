
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
<link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
<script src="js/jquery-1.8.3.js"></script>
<!--jquery.easyui.min.js包含了easyUI中的所有插件-->
<script src="js/jquery.easyui.min.js"></script>
<script src="js/type.js"></script>
<head>
    <title>房屋类型</title>
</head>
<body>
<%--显示的表单--%>
<table id="dd"></table>

<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:Add('AddDialog','添加房屋类型>>>>>')" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a> <a
            href="javascript:ModifyBySelect()" class="easyui-linkbutton"
            iconCls="icon-edit" plain="true">修改</a> <a
            href="javascript:DeleteMoreById()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">删除</a>
    </div>
</div>
<%--添加操作的弹窗--%>
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <%--添加弹窗中的表单--%>
    <form id="ModiyDialogForm" method="post">
        <table>
            <tr>
                <td>房屋类型:</td>
                <td><input type="text" name="name" id="name"/></td>
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
    <%--添加弹窗中的表单--%>
    <form id="UpdateDialogForm" method="post">
        <table>
            <tr>
                <td>类型编号:</td>
                <td><input type="text" readonly style="border: none" name="id" id="bid"/></td>
            </tr>
            <tr>
                <td>房屋类型:</td>
                <td><input type="text" name="name" id="bname"/></td>
            </tr>
        </table>
    </form>
</div>
<%--添加的弹窗中的保存和取消的按钮--%>
<div id="UpdateDialogButtons">
    <a href="javascript:UpdateSaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">修改</a>
    <a href="javascript:CloseDialog('UpdateDialog')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
</body>
</html>
