<!DOCTYPE html>
<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7/dist/css/bootstrap.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="bootstrap-table/bootstrap-table.js"></script>
    <script type="text/javascript" src="bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script src="/js/jquery-1.8.2.min.js" type="text/javascript"></script>
</head>
<body>
<table id="table_proInfo" style="border: 1px solid #000000;width: 200px;">
    <thead style="background-color: #B0E0E6" >
    <tr >
        <th>操作</th>
        <th>产品ID</th>
        <th>产品名称</th>
    </tr>
    </thead>
    <tbody>

    </tbody>
</table>
<script type="text/javascript">
    $("#table_proInfo").bootstrapTable({
        url:"/productList",
        method: 'get', //请求方式（*）
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        columns:[{
            field: 'id'
        },{
            field: 'name'
        }
        ]

    });
</script>
</body>
</html>