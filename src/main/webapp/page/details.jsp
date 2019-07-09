<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <FORM method=get>
            <INPUT class=text type=text name=keywords>
            <LABEL class="ui-green searchs">
                <a href="list.jsp" target="_blank">搜索房屋</a>
            </LABEL>
        </FORM>
    </DIV>
</DIV>
<DIV id=navbar class=wrap>
    <DL class="search clearfix">
        <form id="searchForm" action="getBorswerHouse" method="post">
            <input type="hidden" name="page" id="pageIndex" value="1">
            标题:<input type="text" name="title" id="title" >
            房屋类型:<select name="typeid" id="typeid">
            <option value="">不限</option>
        </select>
            区域:<select name="districtid" id="districtid">
            <option value="">不限</option>
        </select>
            标题:<select name="streetid" id="streetid">
            <option value="">不限</option>
        </select>
            价格:<input type="text" name="min_price" id="min_price">
            -<input type="text" name="max_price" id="max_price">
            <LABEL class=ui-blue><INPUT value=搜索房屋 type=submit name=search></LABEL>
        </form>
    </DL>
</DIV>
<DIV id=position class=wrap>当前位置：青鸟租房网 - 浏览房源</DIV>
<DIV id=view class="main wrap">
    <DIV class=intro>
        <DIV class=lefter>
            <H1>${house.title}</H1>
            <DIV class=subinfo><fmt:formatDate value="${house.pubdate}" pattern="yyyy-MM-dd"></fmt:formatDate></DIV>
            <DIV class=houseinfo>
                <P>户　　型：<SPAN>${house.tname}</SPAN></P>
                <P>面　　积：<SPAN>${house.floorage}m<SUP>2</SUP></SPAN></P>
                <P>位　　置：<SPAN>${house.dname}${house.sname}</SPAN></P>
                <P>联系方式：<SPAN>${house.contact}</SPAN></P>
                图片展示:<br>
                <img src="http://localhost:81/${house.path}" width="200" height="150" alt="">
            </DIV>
        </DIV>
        <DIV class=side>
            <P><A class=bold href="http://localhost:8080/House-2/#">北京青鸟房地产经纪公司</A></P>
            <P>资质证书：有</P>
            <P>内部编号:1000</P>
            <P>联 系 人：</P>
            <P>联系电话：<SPAN></SPAN></P>
            <P>手机号码：<SPAN>13647225040</SPAN></P></DIV>
        <DIV class=clear></DIV>
        <DIV class=introduction>
            <H2><SPAN><STRONG>房源详细信息</STRONG></SPAN></H2>
            <DIV class=content>
                <P>${house.description}</P></DIV>
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
