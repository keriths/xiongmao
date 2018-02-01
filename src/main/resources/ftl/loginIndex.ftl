<!DOCTYPE html>
<html>
<head>
    <script>
        if (self.frameElement && self.frameElement.tagName == "IFRAME") {
            window.parent.document.location.href = "/login";
        }
    </script>
</head>
<body style="margin: 0px">
<div style="position: fixed;
    bottom: 0;
    top: 91px;">
    <div style="border: 1px solid #fff;
    border-radius: 5px;
    background-color: rgba(235, 235, 235, 0.4);
    width: 350px;
    position: fixed;
    top: 22%;
    right: 40%;
    height: 300px;">

        <div id="error" style=" margin-left: 50px;margin-top: 20px;font-family: serif;color: red;">
        ${errorMsg}
        </div>

        <div style=" margin-top: 30px;
                margin-left: 50px;
                font-size: 18px;
                font-weight: bold;">
            快速登录
        </div>

        <form action="doLogin" method="post">
            <div>
                <input name="name" style="height: 25px;
                                width: 290px;
                                margin-left: 25px;
                                margin-top: 35px;
                                border-radius: 3px;
                                border: 1px solid #ccc;" placeholder="请输入用户名" value="">
            </div>
            <div>
                <input name="password" type="password" style="height: 25px;
                                width: 290px;
                                margin-left: 25px;
                                margin-top: 35px;
                                border-radius: 3px;
                                border: 1px solid #ccc;" placeholder="请输入密码" value="">
            </div>
            <div>
                <input type="submit" style="height: 35px;
                            width: 290px;
                            margin-left: 25px;
                            margin-top: 35px;
                            border-radius: 3px;
                            border: 1px solid #ccc;
                            background-color: red;
                            line-height: 31px;cursor: pointer" value="登录">
            </div>
        </form>
    </div>
</div>
<script src="/js/jquery-1.8.2.min.js"></script>
</body>

<script type="text/javascript">
    $(document).ready(function () {
        $("#error").show().delay(2000).hide(0);
    });
</script>

</html>
