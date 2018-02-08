<!DOCTYPE html>
<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <script type="text/javascript" src="js/jquery-1.11.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7/dist/css/bootstrap.css" />
    <script type="text/javascript" src="bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="bootstrap-table/bootstrap-table.js"></script>
    <script type="text/javascript" src="bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
</head>
<body>
<table id="table_proInfo" style="border: 1px solid #000000;width: 85%;margin-left: 20px;margin-top: 10px;">
    <thead style="background-color: #B0E0E6" >
    <tr >
        <th>工厂</th>
        <th>站点</th>
        <th>在库量上限</th>
        <th>在库量下限</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>

    </tbody>
</table>
</body>


<script type="text/javascript">
    $("#table_proInfo").bootstrapTable({
        url:"/storeList",
        method: "post", //请求方式（*）
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        columns:[{
            field: 'factory',
            align: 'center'
        },{
            field: 'stepid',
            align: 'center',
        },{
            field: 'storeMax',
            align: 'center',
        },{
            field: 'storeMin',
            align: 'center',
        },{
            align: 'center',
            formatter: function(value, row, index){
                return '<button class="btn btn-xs btn-danger" id="deleteData" title="编辑"><i class="glyphicon glyphicon-pencil"></i></button>';

            },
            events: {
                'click #deleteData':deleteData,
            }
        }
        ]
    });


    function deleteData(e, v, row) {
        if(row.dictItemList!=""){
            if(confirm("确定删除吗？")){
                $.ajax({
                    url: "/deleteProduct",
                    type: "POST",
                    data: {
                        "productId": row.productId,
                        "productName":row.productName
                    },
                    success: function (data) {
                        if(data.success){
                            $("#table_proInfo").bootstrapTable('refresh');
                        }else {
                            alert(data.errorMessage);
                        }
                    }
                });
            }
        }

    }
</script>
</html>
