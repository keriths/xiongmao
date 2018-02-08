<!DOCTYPE html>
<html>
<head>
    <title>平台</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="css/easyui.css" rel="stylesheet" type="text/css" />
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/mainFrame.css?1=12" rel="stylesheet">
</head>
<body class="easyui-layout" style="background: #cccccc">
<div id="topdiv" region="north" class="head" style="background: #cccccc">
    top
</div>
<div region="west" title="导航菜单" class="left">
    <div class="sidebar-menu" id="menuBox">
        <a href="#oneMenuId_001" class="nav-header menu-first " data-toggle="collapse">在制品</a>
        <ul id="oneMenuId_001" class="nav nav-list  menu-second in collapse " >
            <li><a href="javascript:void(0)" class="menuItem" menuName="设备管理" menuId="1001" menuUrl="/toQueryStep">设备管理</a></li>
            <li><a href="javascript:void(0)" class="menuItem" menuName="在库容量设置" menuId="1002" menuUrl="/toQuery">在库容量设置</a></li>

        </ul>
        <a href="#oneMenuId_002" class="nav-header menu-first " data-toggle="collapse">文档</a>
        <ul id="oneMenuId_002" class="nav nav-list  menu-second in collapse " >
            <li><a href="javascript:void(0)" class="menuItem" menuName="接口文档" menuId="2001" menuUrl="/api/doc.html">接口文档</a></li>
        </ul>
        <a href="#oneMenuId_003" class="nav-header menu-first " data-toggle="collapse">产品管理</a>
        <ul id="oneMenuId_003" class="nav nav-list  menu-second in collapse " >
            <li><a href="javascript:void(0)" class="menuItem" menuName="产品管理" menuId="3001" menuUrl="/toQueryProduct">产品管理</a></li>
        </ul>
        <a href="#oneMenuId_004" class="nav-header menu-first " data-toggle="collapse">良品率</a>
        <ul id="oneMenuId_004" class="nav nav-list  menu-second in collapse " >
            <li><a href="javascript:void(0)" class="menuItem" menuName="目标良品率设置" menuId="4001" menuUrl="/toQueryStep">设备管理</a></li>
            <li><a href="javascript:void(0)" class="menuItem" menuName="单个目标良品率设置" menuId="4002" menuUrl="/toQueryStep">设备管理</a></li>
        </ul>
    </div>
</div>
<div id="mainPanle" region="center" class="right">
    <div id="tabs" class="easyui-tabs man-tab" fit="true" border="false">
        <!--<div title="首页" id="home">-->
        <!--<div class="quick-menu">-->
        <!--</div>-->
        <!--<div id="mainPanle_frame">-->

        <!--</div>-->
        <!--<iframe name="" class="iframe-main" scrolling="auto" frameborder="0" src="" style="width:100%"></iframe>-->
        <!--</div>-->
    </div>
</div>
<!--mainPanle-->


<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/jquery.easyui.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#menuBox").find(".menuItem").each(function(){
            var _menuItem = $(this);
            var menuName = _menuItem.attr("menuName");
            var menuId = _menuItem.attr("menuId");
            var menuUrl = _menuItem.attr("menuUrl");
            _menuItem.click(function(){
                openMenu(menuId,menuName,menuUrl);
            });
            _menuItem.dblclick(function(){
                refreashMenu(menuId,menuName,menuUrl);
            });
        });
    })

    var openMenu = function(menuId,menuName,menuUrl){
        var existed = $('#tabs').tabs('exists', menuName);
        if(existed){
            $('#tabs').tabs('select', menuName);
            return;
        }
        addMenuTab(menuName,menuUrl);
    }
    var refreashMenu = function(menuId,menuName,menuUrl){
        var existed = $('#tabs').tabs('exists', menuName);
        if(existed){
            updateMenuTab(menuName,menuUrl);
        }else{
            addMenuTab(menuName,menuUrl);
        }
    }
    var addMenuTab = function(title,openUrl){
        var options = {
            title: title,
            content: createFrame(openUrl),
            closable: true,
            cache: false,
            closed: true,
            width: $('#mainPanle').width() - 10,
            height: $('#mainPanle').height() - 26
        };
        $('#tabs').tabs('add', options);
    }
    var updateMenuTab = function(title,openUrl){
        var options = {
            title: title,
            content: createFrame(openUrl),
            closable: true,
            cache: false,
            closed: true,
            width: $('#mainPanle').width() - 10,
            height: $('#mainPanle').height() - 26
        };
        var thisTab = $("#tabs").tabs('getTab', title);
        $('#tabs').tabs('update', {
            tab: thisTab,
            options: options
        });
        $('#tabs').tabs('select', title);
    }
    function createFrame(url) {
        return '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
    }

    //
    //
    //    function createCookie(name, value, days) {
    //        if (days) {
    //            var date = new Date();
    //            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
    //            var expires = "; expires=" + date.toGMTString();
    //        } else var expires = "";
    //        document.cookie = name + "=" + value + expires + "; path=/";
    //    }
    //    function readCookie(name) {
    //        var nameEQ = name + "=";
    //        var ca = document.cookie.split(';');
    //        for (var i = 0; i < ca.length; i++) {
    //            var c = ca[i];
    //            while (c.charAt(0) == ' ')c = c.substring(1, c.length);
    //            if (c.indexOf(nameEQ) == 0)return c.substring(nameEQ.length, c.length);
    //        }
    //        return null;
    //    }
    //    function eraseCookie(name) {
    //        createCookie(name, "", -1);
    //    }
    //    function switchStylestyle(styleName) {
    //        $('link[rel*=style][title]').each(function (i) {
    //            this.disabled = true;
    //            if (this.getAttribute('title') == styleName) this.disabled = false;
    //        });
    //        createCookie('style', styleName, 365);
    //    }
    //    function addTab(title, href, update) {
    //        var options = {
    //            title: title,
    //            content: createFrame(href),
    //            closable: true,
    //            cache: false,
    //            closed: true,
    //            width: $('#mainPanle').width() - 10,
    //            height: $('#mainPanle').height() - 26
    //        };
    //        var existed = $('#tabs').tabs('exists', title);
    //        if (existed) {
    //            var thisTab = $("#tabs").tabs('getTab', title);
    //            if (update) {
    //                $('#tabs').tabs('update', {
    //                    tab: thisTab,
    //                    options: options
    //                });
    //            }
    //            $('#tabs').tabs('select', title);
    //        } else {
    //            $('#tabs').tabs('add', options);
    //        }
    //    }
    //    function closeSelf() {
    //        var thisTab = $("#tabs").tabs("getSelected");
    //        var thisTabIndex = $("#tabs").tabs("getTabIndex", thisTab);
    //        $("#tabs").tabs("close", thisTabIndex);
    //    }
    //    function closeThenAddTab(title, href, update) {
    //        closeSelf();
    //        addTab(title, href, update);
    //    }
    //
    //    $(function () {
    //        $("#mainPanle_frame").html('<iframe class="iframe-main" scrolling="auto" frameborder="0"  src="" style="width:100%;height:100%;"></iframe>');
    //        $(".iframe-main").load(function () {
    //            $(this).height($(window).height() - 100);
    //        });
    //        $('.set-skin a').click(function () {
    //            switchStylestyle(this.getAttribute("rel"));
    //            return false;
    //        });
    //        var c = readCookie('style');
    //        if (c) {
    //            switchStylestyle(c);
    //        }
    //    });
</script>
</body>
</html>