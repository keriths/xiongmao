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
                        <a href="/toEdit?factory=${store.factory}&storeMin=${store.storeMin}&storeMax=${store.storeMax}">编辑</a>
                    </td>
                </tr>
            </#list>
        </table>
    </div>

<div/>
</html>
