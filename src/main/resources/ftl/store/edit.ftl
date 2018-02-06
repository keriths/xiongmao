<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7/dist/css/bootstrap.css" />
    <script type="text/javascript" src="bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="bootstrap-table/bootstrap-table.js"></script>
    <script type="text/javascript" src="bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script src="/js/jquery-1.8.2.min.js" type="text/javascript"></script>
</head>
<body >
    <div class="container" style="width:100%;">
        <div>
            <form id="storeFrom" class="form-horizontal form-inline" accept-charset="UTF-8">
                <table class="table table-condensed table-striped ">
                    <tr>
                        <td><label>工厂</label></td>
                        <td>
                            <div class="input-group" style="width: 196px;">
                                <input type="text" name="factory"  required="true" class="form-control" value="${factory}" >
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><label>站点</label></td>
                        <td>
                            <div class="input-group" style="width: 196px;">
                                <input type="text" name="stepid"  required="true" class="form-control" value="${stepid}" >
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><label>在库量上限</label></td>
                        <td>
                            <div class="input-group" style="width: 196px;">
                                <input type="text" name="storeMax" required="true" class="form-control" value="${storeMax}" >
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td><label>在库量下限</label></td>
                        <td>
                            <div class="input-group" style="width: 196px;">
                                <input type="text" name="storeMin" required="true" class="form-control" value="${storeMin}" >
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button class="btn btn-info" type="button" class="btn btn-default" id="editStore" style="margin-left: 150px;">保存</button>
                        </td>
                    </tr>
                </table>

            </form>
        </div>
    </div>
</body>


<script type="text/javascript">

    $("#editStore").click(function () {
        $.ajax({
            type: "POST",   //提交的方法
            url:"/updatestore", //提交的地址
            data:$('#storeFrom').serialize(),// 序列化表单值
            async: false,
            /*error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
                alert(data);
                window.location.href="跳转页面"
            }*/
        });

    });
</script>

</html>
