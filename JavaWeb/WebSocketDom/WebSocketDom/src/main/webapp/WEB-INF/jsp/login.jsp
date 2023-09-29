<%--
  Created by IntelliJ IDEA.
  User: wind
  Date: 2016/11/20
  Time: 22:37 下午
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
    String error = (String) request.getAttribute("error");
%>
<html>
<head>
    <title>登陆WebSocketDom之聊天室</title>
    <script>
        var ws_basePath = "${path}";
        var ws_basePathw = "${basePath}";
    </script>
    <style>
        .warningBar {
            font-weight: bold;
            font-size: 12px;
            margin-top: 15px;
            background: #fceee8;
            width: 240px;
            margin-left: 35px;
            padding: 4px 4px;
            border: #f6c8b5 solid 1px;
        }

    </style>
</head>
<body>
    <form action="login.do" id="forminfos" method="post">
        昵称:
        <input name="name" id="username" type="text" placeholder="请输入您的昵称" value="">
        <div id="warn" class="warningBar" style="display: none">
            <span id="warn_text"><%=error%></span>
        </div>
        <input type="submit" value="登录">
    </form>
</body>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script>
    if( $("#warn_text").text()&&$("#warn_text").text()!="null"){
        $("#warn").show();
    }
    <%--function addLogin() {--%>
        <%--$.ajax({--%>

            <%--url: "<%=basePath%>/msg/login.do",--%>

            <%--data:  $('#forminfos').serialize(),--%>

            <%--type: "post",--%>

            <%--cache : false,--%>

            <%--dataType:'json',--%>

            <%--success: function(data) {--%>

                <%--console.log("success:"+data.msg);--%>
                <%--if(data.code>0){--%>
                    <%--window.location.href = "<%=basePath%>/msg/talk.do?username="+$('#username').val();--%>
                <%--}else{--%>
                    <%--$("#warn_text").empty();--%>
                    <%--$("#warn_text").append(data.msg);--%>
                    <%--$("#warn").show();--%>
                <%--}--%>

            <%--},--%>

            <%--error:function() {--%>

                <%--console.log("error:"+result.msg);--%>

            <%--}--%>

        <%--});--%>
    <%--}--%>
    
    
</script>
</html>
