<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type>
    <LINK rel=stylesheet type=text/css href="../css/style.css">
    <META name=GENERATOR content="MSHTML 8.00.7601.17514">
</HEAD>
<BODY>

<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
    <DIV class=search>
        <LABEL class="ui-green searchs">
            <a href="${pageContext.request.contextPath}/page/getHouse" title="">欢迎:${sessionScope.login.name}</a>
        </LABEL>
        <LABEL class="ui-green searchs">
            <a href="${pageContext.request.contextPath}/page/goFaBu" title="">发布房屋信息</a>
        </LABEL>
        <LABEL class="ui-green searchs">
            <a href="${pageContext.request.contextPath}/page/getBorswerHouse" title="">浏览房屋信息</a>
        </LABEL>
        <LABEL class=ui-green>
            <INPUT onclick='document.location="index.jsp"' value="退       出" type=button name=search>
        </LABEL>
    </DIV>
</DIV>
<DIV id=regLogin class=wrap>
    <DIV class=dialog>
        <DL class=clearfix>
            <DT>新房屋信息发布</DT>
            <DD class=past>填写房屋信息</DD>
        </DL>
        <DIV class=box>
            <FORM id=add_action method=post name=add.action
                  action=addHouse enctype="multipart/form-data">
                <DIV class=infos>
                    <TABLE class=field>
                        <TBODY>
                        <TR>
                            <TD class=field>标　　题：</TD>
                            <TD><INPUT id=add_action_title class=text type=text name=title></TD>
                        </TR>
                        <TR>
                            <TD class=field>户　　型：</TD>
                            <TD><SELECT class=text name=typeId>
                                <OPTION selected value="-1">请选择</OPTION>
                                <c:forEach items="${types}" var="t">
                                    <OPTION value="${t.id}">${t.name}</OPTION>
                                </c:forEach>
                            </SELECT>
                            </TD>
                        </TR>
                        <TR>
                            <TD class=field>面　　积：</TD>
                            <TD>
                                <INPUT id=add_action_floorage class=text type=text name=floorage>
                            </TD>
                        </TR>
                        <TR>
                            <TD class=field>价　　格：</TD>
                            <TD><INPUT id=add_action_price class=text type=text name=price></TD>
                        </TR>
                        <TR>
                            <TD class=field>发布日期：</TD>
                            <TD><INPUT class=text type=date name=pubdate></TD>
                        </TR>
                        <TR>
                            <TD class=field>位　　置：</TD>
                            <TD>区：
                                <SELECT class=text name=district_id id="district_id">
                                    <OPTION selected value="-1">请选择</OPTION>
                                    <c:forEach items="${districts}" var="d">
                                        <OPTION value="${d.id}">${d.name}</OPTION>
                                    </c:forEach>
                                </SELECT>
                                街：
                                <SELECT class=text name=streetId id="street_id">
                                    <OPTION selected value="-1">请选择</OPTION>
                                </SELECT>
                            </TD>
                        </TR>
                        <TR>
                            <TD class=field>房屋图片：</TD>
                            <TD><INPUT id=pfile class=text type=file name=pfile></TD>
                        </TR>
                        <TR>
                        <TR>
                            <TD class=field>联系方式：</TD>
                            <TD><INPUT id=add_action_contact class=text type=text name=contact></TD>
                        </TR>
                        <TR>
                            <TD class=field>详细信息：</TD>
                            <TD><TEXTAREA name=description></TEXTAREA></TD>
                        </TR>
                        </TBODY>
                    </TABLE>
                    <DIV class=buttons>
                        <INPUT value=立即发布 type=submit>
                    </DIV>
                </DIV>
            </FORM>
        </DIV>
    </DIV>
</DIV>
<DIV id=footer class=wrap>
    <DL>
        <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
        <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD>
    </DL>
</DIV>
</BODY>
</HTML>
<script src="js/jquery-1.8.3.js"></script>
<script type="application/javascript">
    $(function () {
        //当区域被选择时触发
        $("#district_id").change(function () {
            //获取当前区域id
            var did = $(this).val();
            //异步通过区域id获取当前区域下的所有街道信息
            $.post("getListStreet", {"id": did}, function (data) {
                //每一次获取新的信息,删除旧的信息
                $("#street_id>option:gt(0)").remove();
                for (var i = 0; i < data.length; i++) {
                    var option = "<option value='" + data[i].id + "'>" + data[i].name + "</option>"
                    $("#street_id").append(option);
                }
            }, "json");
        })
    })
</script>