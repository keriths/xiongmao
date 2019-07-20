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
        <th>站点ID</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>

    </tbody>
</table>
</body>

<!-- 模态窗口 -->
<div class="modal fade" id="stepModel" tabindex="-1" role="dialog" aria-labelledby="userModelLabel" aria-hidden="true" data-backdrop="false">
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" >添加设备</h4>
            </div>
            <div class="modal-body">
                <form id="addForm"  class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-3">工厂：</label>
                        <div class="col-sm-8">
                            <input type="text"  id="factory" name="factory" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" >站点ID：</label>
                        <div class="col-sm-8">
                            <input type="text"  id="stepId" name="stepId" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary" id="userModelBtn">确定</button>
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal" id="Close">关闭</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="editStepModel" tabindex="-1" role="dialog" aria-labelledby="userModelLabel" aria-hidden="true" data-backdrop="false">
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" >编辑设备</h4>
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
                            <input type="text"  id="editStepId" name="stepId" class="form-control">
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

<script type="text/javascript">
    $("#table_proInfo").bootstrapTable({
        url:"/stepList",
        method: "post", //请求方式（*）
        striped: true, //是否显示行间隔色
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        columns:[{
            field: 'factory',
            align: 'center',
            width: '350px',
        },{
            field: 'stepId',
            align: 'left',
        },{
            align: 'center',
            width: '400px',
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

    //添加请求验证
    /*$("#editForm").validate({
        submitHandler: function(form) {
            $("#productModel").prop("disabled",true);
            $(form).ajaxSubmit({
                type:"post",
                dataType:"json",
                url:"/addProduct",
                success:function(data){
                    if(data.success==true){
                        $('#productModel').modal('hide')
                        $('#table_proInfo').bootstrapTable('refresh');
                    }else{
                        alert(data.errorMessage);
                    }
                    $("#productModel").prop("disabled",false);
                }
            });
        }
    });*/

    //提交用户信息
    $("#stepModel").on('click','#userModelBtn',function(){
        $("#addForm").submit();
    });

    //添加
    function addData(e, v, row) {
        //$("#editForm").resetForm();
        $("#addForm")[0].reset();
        $("#stepModel").modal("show");

    }
    //确定添加
    $("#userModelBtn").click(function () {
        if($("#factory").val()=="") {
            alert("工厂不能为空!");
            $("#factory").focus();
            return false;
        }
        if($("#stepId").val()=="") {
            alert("站点ID不能为空!");
            $("#stepId").focus();
            return false;
        }

        $.ajax({
            type: "POST",   //提交的方法
            url:"/addStep", //提交的地址
            data:$('#addForm').serialize(),// 序列化表单值
            async: false,
            dataType:"json",
            success: function (data) {
                if(data.success){
                    alert(data.errorMessage);
                    $("#stepModel").modal("hide");
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
        $("#editStepModel .modal-title").empty().html("编辑设备");
        $("#editForm input[name=factory]").val(row.factory);
        $("#editForm input[name=stepId]").val(row.stepId);
        $("#editStepModel").modal("show");
    }

    //确定编辑
    $("#editModelBtn").click(function () {
        /*if($("#editFactory").val()=="") {
            alert("工厂不能为空!");
            $("#editFactory").focus();
            return false;
        }*/
        if($("#editStepId").val()=="") {
            alert("站点ID不能为空!");
            $("#editStepId").focus();
            return false;
        }

        $.ajax({
            type: "POST",   //提交的方法
            url:"/updateStep", //提交的地址
            data:$('#editForm').serialize(),// 序列化表单值
            async: false,
            dataType:"json",
            success: function (data) {
                if(data.success){
                    alert(data.errorMessage);
                    $("#editStepModel").modal("hide");
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
                    url: "/deleteStep",
                    type: "POST",
                    dataType:"json",
                    data: {
                        "factory": row.factory,
                        "stepId":row.stepId
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