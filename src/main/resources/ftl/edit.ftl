<!DOCTYPE html>
<html>
<head>
    <#--<link href="/bootstrap3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.css">
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet">-->
</head>
<body >
<div class="container" style="width:100%;">
    <div style="margin-bottom:10px;">
        <form id="Export" class="form-inline" action="/partnerList" method="POST">
            <div class="form-group">
                <label class="control-label">工厂:</label>
                <input class="form-control" size="20" name="partnerName" value="${factory}" type="text"  style="width: 120px;" id="partnerName">
            </div>
            <div class="form-group">
                <label class="control-label">在库量上限:</label>
                <input class="form-control" size="20" name="contactLinkman" value="${storeMax}" type="text"  style="width: 120px;" id="contactLinkman">
            </div>
            <div class="form-group">
                <label class="control-label">在库量下限:</label>
                <input class="form-control" size="20" name="contactLinkman" value="${storeMin}" type="text"  style="width: 120px;" id="contactLinkman">
            </div>
            <button class="btn btn-info" type="submit" class="btn btn-default">查询</button>
            <button type="submit" class="btn btn-danger"><a href="javascript:updateProject('/updateOrAdd')" style="color:black;text-decoration:none">添加</a></button>
        </form>
    </div>
    <div>
        <table border="1">
            <tr>
                <th>工厂</th>
                <th>在库量上限</th>
                <th>在库量下限</th>
                <th>操作</th>
            </tr>
            <#list storeDTOList as store>
                <tr>
                    <td>${store.factory}</td>
                    <td>${store.storeMin}</td>
                    <td>${store.storeMax}</td>
                    <td>
                        <a href="#">删除</a>
                        <a href="#">编辑</a>
                    </td>
                </tr>
            </#list>
        </table>
    </div>

<div/>
</html>
