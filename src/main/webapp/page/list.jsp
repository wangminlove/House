<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>青鸟租房 - 首页</TITLE>
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
<DIV id=navbar class=wrap>
    <form id="searchForm" action="getBorswerHouse" method="post">
        <input type="hidden" name="page" id="pageIndex" value="1">
        标题:<input type="text" name="title" id="title" value="${condition.title}">
        房屋类型:<select name="typeid" id="typeid">
        <option value="">不限</option>
    </select>
        区域:<select name="districtid" id="districtid">
        <option value="">不限</option>
    </select>
        标题:<select name="streetid" id="streetid">
        <option value="">不限</option>
    </select>
        价格:<input type="text" name="min_price" id="min_price" value="${condition.min_price}">
        -<input type="text" name="max_price" id="max_price" value="${condition.max_price}">
        <LABEL class=ui-blue><INPUT value=搜索房屋 type=submit name=search></LABEL>
    </form>
</DIV>
<DIV class="main wrap">
    <TABLE class=house-list>
        <TBODY>
        <c:forEach items="${pageInfo.list}" var="h">
            <TR>
                <TD class=house-thumb><span><A href="javascript:getDetails(${h.id})" target="_blank">
                    <img src="http://localhost:81/${h.path}" width="100" height="75" alt=""></a>
                </span></TD>
                <TD>
                    <DL>
                        <DT><A href="javascript:getDetails(${h.id})" target="_blank">${h.title}</A></DT>
                        <DD>${h.dname}${h.sname},${h.floorage}平米<BR>联系方式：${h.contact}</DD>
                    </DL>
                </TD>
                <TD class=house-type>${h.tname}</TD>
                <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD>
            </TR>
        </c:forEach>
        </TBODY>
    </TABLE>
    <DIV class=pager>
        <UL>
            <LI class=current><A href="javascript:do_page(1)">首页</A></LI>
            <LI><A href="javascript:do_page(${pageInfo.prePage})">上一页</A></LI>
            <LI><A href="javascript:do_page(${pageInfo.nextPage})">下一页</A></LI>
            <LI><A href="javascript:do_page(${pageInfo.pages})">末页</A></LI>
        </UL>
        <SPAN class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN></DIV>
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
        //房屋类型
        $.post("getAllType", null, function (data) {
            for (var i = 0; i < data.length; i++) {
                var option = "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                $("#typeid").append(option);
            }
            //设置房屋类型选中项
            $("#typeid").val(${condition.typeid})
        }, "json");
        //所有的区域信息
        $.post("getAllDistrict", null, function (data) {
            for (var i = 0; i < data.length; i++) {
                var option = "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                $("#districtid").append(option);
            }
            ;
            //设置区域id选中项
            $("#districtid").val(${condition.districtid});

        }, "json");
        $("#districtid").change(function () {
            do_street($("#districtid").val());
        })
    })

    function do_street(did) {
        $.post("getListStreet", {"id": did}, function (data) {
            $("#streetid>option:gt(0)").remove();
            for (var i = 0; i < data.length; i++) {
                var option = "<option value='" + data[i].id + "'>" + data[i].name + "</option>"
                $("#streetid").append(option);
            }
            //设置街道选中项
            $("#streetid").val(${condition.streetid})
        }, "json");
    }
    function do_page(pn) {
        $("#pageIndex").val(pn);
        $("#searchForm").submit();
    }
    function getDetails(id){
        location.href="getDetail?id="+id;
    }
</script>