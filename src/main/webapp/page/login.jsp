<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0032)http://localhost:8080/HouseRent/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
  <TITLE>青鸟租房 - 用户登录</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DIV class=box>
<H4>用户登录</H4>
<FORM id=user method=post name=user action=loginAction>
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <TR>
    <TD colSpan=2></TD></TR>
  <TR>
    <TD class=field>用 户 名：</TD>
    <TD><!-- <input type="text" class="text" name="name" /> -->
      <INPUT id=user_name class=text type=text name=name> </TD>
  </TR>
  <TR>
    <TD class=field>密　　码：</TD>
    <TD><!-- <input type="password" class="text" name="password" /> -->
      <INPUT id=user_password class=text type=password name=password>
    </TD>
  </TR>
  <%--<tr>
    <td class="field">手 机 号：</td>
    <td><input type="text" class="text verycode" name="telephone" id="telephone"/>
        <input type="button" id="btn" value="获取验证码" style="padding: 2px 6px">
      <span id="tel" style="font-size: 14px"></span><br>
    </td>
  </tr>
  <tr>
  <td class="field">验 证 码：</td>
  <td><input type="text" class="text verycode" name="veryCode"/></td>
  </tr>--%>
  <TR>
    <TD colspan="2" style="color: red;text-align: center" ><h3>${info}</h3></TD>
  </TR>
  </TBODY>
</TABLE>
<DIV class=buttons>
  <INPUT  value=登陆 type="submit">
  <INPUT onclick='document.location="regs.jsp"' value=注册 type=button>
</DIV>
</DIV>
</FORM>
</DIV>
</DIV>
</DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 蓝天青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
<script src="js/jquery-1.8.3.js"></script>
<script type="application/javascript">
    var ObjTime;
  $("#btn").click(function () {
      var tel=$("#telephone").val();
      //电话号码的正则表达式,当电话合乎规范时发送请求
      if (/^1(3|4|5|7|8)\d{9}$/.test(tel)){
          $.post("getCode",{"telephone":tel},function (data) {
           if (data.result > 0) {
            ObjTime=setInterval("backTime()",1000);//每秒执行一次
          }
           },"json")
      }else{
         $("#tel").html("请输入正确的电话号码").css("color","red");
      }

  });
  var time=120;
  function backTime() {
     if (time != 0){
         time--;
         $("#btn").css("padding","2px 6px");
         $("#btn")[0].disabled=true;
         $("#btn").val(time+"秒");
     }else{
         clearInterval(ObjTime);
         $("#btn")[0].disabled=false;
         //location.reload(true);
         $("#btn").val("重新获取短信验证码")
         time=120;
     }
  }
</script>