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
        <form id="storeFrom" class="form-horizontal form-inline" action="/updatestore" method="post">
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
                            <input type="text" name="storeMax" class="form-control" value="${storeMax}" >
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><label>在库量下限</label></td>
                    <td>
                        <div class="input-group" style="width: 196px;">
                            <input type="text" name="storeMin" class="form-control" value="${storeMin}" >
                        </div>
                    </td>
                </tr>
            </table>
            <button class="btn btn-info" type="submit" class="btn btn-default" style="margin-left: 300px;">保存</button>
        </form>
    </div>

<div/>
</html>
