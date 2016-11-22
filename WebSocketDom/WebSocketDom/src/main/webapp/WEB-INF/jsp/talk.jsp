<%--
  Created by IntelliJ IDEA.
  User: wind
  Date: 2016/11/20
  Time: 22:46 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getServerName() + ":"
            + request.getServerPort() + path + "/";
    String basePath2 = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String uid = (String) request.getAttribute("uid");
    String uname = (String) request.getAttribute("uname");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>WebSocketDom-聊天室</title>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" charset="utf-8" src="<%=basePath2%>/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath2%>/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="<%=basePath2%>/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script>
        var ws_basePath = "<%=basePath%>";
        var ws_uid ="<%=uid%>";
        var ws_uname ="<%=uname%>";
        <%--var basePath =  --%>
        <%--var ws_basePath2 = "${path}";--%>
        <%--var ws_basePathw = "${basePath}";--%>
        <%--var ws_basePath3 = "${basePath2}";--%>
        <%--var ws_uid ="${uid}";--%>
        <%--var ws_uname ="${uname}";--%>
    </script>
    <style>
        .name {
            color: gray;
            font-size: 12px;
        }
        .fmsg_text {
            color: white;
            background-color: rgb(66, 138, 140);
            font-size: 18px;
            border-radius: 5px;
            padding: 2px;
        }
        .sbtext{
            color: white;
            background-color: rgb(166, 161, 161);
            font-size: 12px;
            border-radius: 5px;
            padding: 2px;
        }
        .adminftext{
            color: white;
            background-color: rgb(235, 95, 95);
            font-size: 12px;
            border-radius: 5px;
            padding: 2px;
        }
        .fmsg_text {
            color: white;
            background-color: rgb(66, 138, 140);
            font-size: 18px;
            border-radius: 5px;
            padding: 2px;
        }
        .tmsg_text {
            color: white;
            background-color: rgb(47, 47, 47);
            font-size: 18px;
            border-radius: 5px;
            padding: 2px;
        }
        .sfmsg_text {
            color: white;
            background-color: rgb(148, 16, 16);
            font-size: 18px;
            border-radius: 5px;
            padding: 2px;
        }
    </style>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-primary">
                <div class="panel-heading">聊天室</div>
                <div id="msg" class="panel-body">

                </div>
                <div class="panel-footer">
                    在线人数<span id="onlineCount">1</span>人
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <script id="editor" type="text/plain" style="width:1024px;height:200px;"></script>
        </div>
    </div>

</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <p class="text-right">
            <form role="form">
                <div class="form-group">
                    <label for="to">选择要发送者</label>
                    <select class="form-control" name="to" id="to">
                        <option value="all" selected = "selected">所有人</option>
                    </select>
                </div>
            </form>
                <button onclick="sendMsg();" class="btn btn-success">发送</button>
            </p>
        </div>
    </div>

</div>
</body>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="//cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
<script type="text/javascript">
    var ue = UE.getEditor('editor');
    var websocket = null;
    if("WebSocket" in window){
        console.log("WebSocket:_WebSocket");
        websocket = new WebSocket("ws://"+ws_basePath+"websocket.do?uid="+ws_uid);
    }else if("MozWebSocket" in window){
        console.log("WebSocket:_MozWebSocket");
        websocket = new MozWebSocket("ws://" + ws_basePath + "/websocket.do?uid="+ws_uid);
    }else{
        console.log("WebSocket:_SockJS");
        websocket = new SockJS("http://" + ws_basePath + "/websocket/sockjs.do?uid="+ws_uid);
    }
    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("error");
    };
    //连接成功建立的回调方法
    websocket.onopen = function (event) {
//        setMessageInnerHTML("系统消息："+ws_uname+"加入连接");
         sendAdmin("系统消息："+ws_uname+"加入连接");
    }

    //接收到信息的回调方法
    websocket.onmessage = function (event) {
        var data=JSON.parse(event.data);
        console.log("WebSocket:收到一条消息",data);
        var ws_message = data.text;
        if( (data.from=="admin"&&data.fromName=="系统消息") && (ws_message.indexOf("系统消息：")>=0&&
                (ws_message.indexOf("加入连接")>=0||ws_message.indexOf("断开连接")>=0))){
            select_relod();
        }
        if(data.from==ws_uid){
            setMessageInnerHTML("<div class='tmsg'><label class='name'>我&nbsp;"+data.date+"</label><div class='sfmsg_text'>"+data.text+"</div></div>");
        }else if(data.from=="admin"&&data.fromName=="系统消息") {
            setMessageInnerHTML("<div class='fmsg'><label class='name'>" + data.fromName + "&nbsp;" + data.date + "</label><div class='sbtext'>" + data.text + "</div></div>");
        }else if(data.from=="admin"&&data.fromName=="系统广播"){
            setMessageInnerHTML("<div class='fmsg'><label class='name'>" + data.fromName + "&nbsp;" + data.date + "</label><div class='adminftext'>" + data.text + "</div></div>");
        }else{
            setMessageInnerHTML("<div class='fmsg'><label class='name'>"+data.fromName+"&nbsp;"+data.date+"</label><div class='tmsg_text'>"+data.text+"</div></div>");
        }

    };
    websocket.onclose = function () {
//        setMessageInnerHTML(ws_uname+"断开连接");
        sendAdmin("系统消息："+ws_uname+"断开连接");
    };
    function select_relod() {
        $.ajax({
            url: "http://" + ws_basePath + "msg/getUserList.do",
            type: "post",
            dataType: 'json',
            data: null,
            success: function (d) {
                console.log(d.code);
                console.log(d.msg);
                if (d.code > 0) {
                    var dataInfo = JSON.parse(d.msg);
                    var users = dataInfo.objs;
                    $("#onlineCount").empty();
                    $("#onlineCount").append(dataInfo.num);
                    var str = "";
                    var usersLen = users.length;
//                         user = null;
                    $("#to").empty();
                    str += " <option value='all' selected = 'selected'>所有人</option>";
                    for (var i = 0; i < usersLen; i++) {
                        var user = users[i];
                        if (user.id == ws_uid) {
                            continue;
                        } else {
                            str += "<option value='" + user.id + "' >" + user.name + "</option>";
                        }
                    }
                    $("#to").append(str);
                }
            }
        });
    }
    //关闭连接
    function closeWebSocket() {
        sendAdmin("系统消息："+ws_uname+"断开连接");
        websocket.close();
    }

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        $("#msg").append(innerHTML+"<br/>")
    };
    function sendMsg(){
        var msg = ue.getContent();
        if(msg==""){
            return;
        }else{
            var data={};
            data["from"]=ws_uid;
            data["fromName"]=ws_uname;
            data["to"]=$("#to").val();
            data["text"]=msg;
        }
        websocket.send(JSON.stringify(data));
        ue.setContent('');
//        select_relod();
    }
    function sendAdmin(msg) {
        if(msg==""){
            return;
        }else{
            var data={};
            data["from"]="admin";
            data["fromName"]="系统消息";
            data["to"]="all";
            data["text"]=msg;
            websocket.send(JSON.stringify(data));
        }
    }
    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，
    // 防止连接还没断开就关闭窗口，server端会抛异常。
    var mb = myBrowser();
    window.onbeforeunload = function (event) {
        if(event.clientX<=0 && event.clientY<0) {
            if ("Chrome" == mb) {
                return "确定关闭窗口？";
            }else{
                var is = confirm("确定关闭窗口？");

                if (is){

                    websocket.close();

                }
            }
        }else{
            websocket.close();
        }

    };
    window.onunload = function(event){
        if(event.clientX<=0 && event.clientY<0) {
            if ("Chrome" == mb) {
                websocket.close();
            }

        }else{
            websocket.close();
        }

    };
    function myBrowser(){
        var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
        var isOpera = userAgent.indexOf("Opera") > -1;
        if (isOpera) {
            return "Opera"
        }; //判断是否Opera浏览器
        if (userAgent.indexOf("Firefox") > -1) {
            return "FF";
        } //判断是否Firefox浏览器
        if (userAgent.indexOf("Chrome") > -1){
            return "Chrome";
        }
        if (userAgent.indexOf("Safari") > -1) {
            return "Safari";
        } //判断是否Safari浏览器
        if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
            return "IE";
        }; //判断是否IE浏览器
    }
</script>
</html>
