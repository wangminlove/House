<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<span id="time"></span>
</body>
</html>
<script src="page/js/jquery-1.8.3.js"></script>
<script type="application/javascript">
    $(function () {
        setInterval("DateTime()","1000")

    });
function DateTime() {
    var date=new Date;
    var year=date.getFullYear();//年
    var month=date.getMonth()+1;//月
    if (month<10){//当月份小于10时,前面补0
        month="0"+month;
    }
    var day=date.getDate();//日
    if (day<10){
        day="0"+day;
    }
    var hours=date.getHours();//小时
    if (hours<10){
        hours="0"+hours;
    }
    var minute=date.getMinutes();//分钟
    if (minute<10){
        minute="0"+minute;
    }
    var second=date.getSeconds();//秒钟
    if (second<10){
        second="0"+second;
    }
    var time=year+"-"+month+"-"+day+" "+hours+":"+minute+":"+second;
    $("#time").html(time);
}
</script>