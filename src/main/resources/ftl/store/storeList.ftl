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

<!-- 模态窗口 -->
<div class="modal fade" id="addStoreModel" tabindex="-1" role="dialog" aria-labelledby="userModelLabel" aria-hidden="true" data-backdrop="false">
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" >添加在库量</h4>
            </div>
            <div class="modal-body">
                <form id="addForm"  class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-3">工厂：</label>
                        <div class="col-sm-8">
                            <input type="text"  id="addFactory" name="factory" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >站点：</label>
                        <div class="col-sm-8">
                            <input type="text"  id="addStepid" name="stepid" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >在库量上限：</label>
                        <div class="col-sm-8">
                            <input type="text"  id="addStoreMax" name="storeMax" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >在库量下限：</label>
                        <div class="col-sm-8">
                            <input type="text"  id="addStoreMin" name="storeMin" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary" id="addModelBtn">确定</button>
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal" id="Close">关闭</button>
            </div>
        </div>
    </div>
</div>

<!-- 模态窗口 -->
<div class="modal fade" id="editStoreModel" tabindex="-1" role="dialog" aria-labelledby="userModelLabel" aria-hidden="true" data-backdrop="false">
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" >编辑在库量</h4>
            </div>
            <div class="modal-body">
                <form id="editForm"  class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-3">工厂：</label>
                        <div class="col-sm-8">
                            <input type="text" readonly id="editFactory" name="factory" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >站点：</label>
                        <div class="col-sm-8">
                            <input type="text" readonly id="editStepid" name="stepid" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >在库量上限：</label>
                        <div class="col-sm-8">
                            <input type="text"  id="editStoreMax" name="storeMax" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >在库量下限：</label>
                        <div class="col-sm-8">
                            <input type="text"  id="editStoreMin" name="storeMin" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary" id="editModelBtn">确定</button>
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal" id="Close">关闭</button>
            </div>
        </div>
    </div>
</div>

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
                return '<button class="btn btn-xs btn-danger" id="deleteData" title="删除"><i class="glyphicon glyphicon-trash"></i></button>'
                        +'&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-xs btn-danger" id="addData" title="添加"><i class="glyphicon glyphicon-plus"></i></button>'
                        +'&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-xs btn-danger" id="editData" title="编辑"><i class="glyphicon glyphicon-pencil"></i></button>';

            },
            events: {
                'click #editData':editData,
                'click #deleteData':deleteData,
                'click #addData':addData,
            }
        }
        ]
    });


    //添加
    function addData(e, v, row) {
        $("#addForm")[0].reset();
        $("#addStoreModel").modal("show");

    }
    //确定添加
    $("#addModelBtn").click(function () {
        if($("#addFactory").val()=="") {
            alert("工厂不能为空!");
            $("#addFactory").focus();
            return false;
        }
        if($("#addStepid").val()=="") {
            alert("站点不能为空!");
            $("#addStepid").focus();
            return false;
        }
        if($("#addStoreMax").val()=="") {
            alert("在库量上限不能为空!");
            $("#addStoreMax").focus();
            return false;
        }
        if($("#addStoreMin").val()=="") {
            alert("在库量下限不能为空!");
            $("#addStoreMin").focus();
            return false;
        }

        $.ajax({
            type: "POST",   //提交的方法
            url:"/addStore", //提交的地址
            data:$('#addForm').serialize(),// 序列化表单值
            async: false,
            dataType:"json",
            success: function (data) {
                if(data.success){
                    alert(data.errorMessage);
                    $("#addStoreModel").modal("hide");
                    $("#table_proInfo").bootstrapTable('refresh');
                }else {
                    alert(data.errorMessage);
                }
            }
        });
    });

    //编辑
    function editData(e, v, row) {
        $("#editForm")[0].reset();
        $("#editStoreModel .modal-title").empty().html("编辑在库量");
        $("#editForm input[name=factory]").val(row.factory);
        $("#editForm input[name=stepid]").val(row.stepid);
        $("#editForm input[name=storeMax]").val(row.storeMax);
        $("#editForm input[name=storeMin]").val(row.storeMin);
        $("#editStoreModel").modal("show");

    }
    //确定编辑
    $("#editModelBtn").click(function () {
        /*if($("#editFactory").val()=="") {
            alert("工厂不能为空!");
            $("#editFactory").focus();
            return false;
        }
        if($("#editStepid").val()=="") {
            alert("站点不能为空!");
            $("#editStepid").focus();
            return false;
        }*/
        if($("#editStoreMax").val()=="") {
            alert("在库量上限不能为空!");
            $("#editStoreMax").focus();
            return false;
        }
        if($("#editStoreMin").val()=="") {
            alert("在库量下限不能为空!");
            $("#editStoreMin").focus();
            return false;
        }

        $.ajax({
            type: "POST",   //提交的方法
            url:"/updateStore", //提交的地址
            data:$('#editForm').serialize(),// 序列化表单值
            async: false,
            dataType:"json",
            success: function (data) {
                if(data.success){
                    alert(data.errorMessage);
                    $("#editStoreModel").modal("hide");
                    $("#table_proInfo").bootstrapTable('refresh');
                }else {
                    alert(data.errorMessage);
                }
            }
        });
    });


    function deleteData(e, v, row) {
        if(row.dictItemList!=""){
            if(confirm("确定删除吗？")){
                $.ajax({
                    url: "/deleteStore",
                    type: "POST",
                    dataType:"json",
                    data: {
                        "factory": row.factory,
                        "stepid":row.stepid
                    },
                    success: function (data) {
                        if(data.success){
                            alert(data.errorMessage);
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
