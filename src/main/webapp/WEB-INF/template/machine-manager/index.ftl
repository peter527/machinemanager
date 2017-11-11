<!DOCTYPE html>
<html>
<head>
    <title>设备管理平台</title>
<#import "/common/common.macro.ftl" as netCommon>
<@netCommon.commonStyle />
    <!-- DataTables -->
    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/plugins/datapicker/css/bootstrap-datepicker3.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini <#if cookieMap?exists && "off" == cookieMap["xxljob_adminlte_settings"].value >sidebar-collapse</#if>">
<div class="wrapper">
    <!-- header -->
<@netCommon.commonHeader />
    <!-- left -->
<@netCommon.commonLeft "jobinfo" />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>人脸设备
                <small>设备管理</small>
            </h1>
            <!--
            <ol class="breadcrumb">
                <li><a><i class="fa fa-dashboard"></i>调度管理</a></li>
                <li class="active">调度中心</li>
            </ol>
            -->
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-xs-4">
                    <div class="input-group">
                        <span class="input-group-addon">发起方</span>
                        <input type="text" class="form-control" id="executorHandler" autocomplete="on">
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="input-group">
                        <span class="input-group-addon">操作类型</span>
                        <select class="form-control" id="jobGroup">
                            <option value="全部操作">全部操作</option>
                            <option value="进货">进货</option>
                            <option value="出货">出货</option>
                            <option value="分配">分配</option>
                            <option value="领用">领用</option>
                            <option value="归还">归还</option>
                        </select>
                    </div>
                </div>

                <div class="col-xs-1">
                    <button class="btn btn-block btn-info" id="searchBtn">搜索</button>
                </div>
                <div class="col-xs-1">
                    <button class="btn btn-block btn-primary" id="inOutBtn">进货/退货</button>
                </div>
                <div class="col-xs-1">
                    <button class="btn btn-block btn-success" id="receiveReturnBtn">领用/归还</button>
                </div>
                <div class="col-xs-1">
                    <button class="btn btn-block btn-warning" id="assignBtn">设备调配</button>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header hide">
                            <h3 class="box-title">设备管理日志</h3>
                        </div>
                        <div class="box-body">
                            <table id="operationLog_list" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>发起方/领用人</th>
                                    <th>接收方</th>
                                    <th>设备型号</th>
                                    <th>设备厂商</th>
                                    <th>操作类型</th>
                                    <th>操作时间</th>
                                    <th>设备数量</th>
                                    <th>设备备注</th>
                                </tr>
                                </thead>
                                <tbody></tbody>
                                <tfoot></tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <!-- footer -->
<@netCommon.commonFooter />
</div>

<#--模态框模板-->
<div class="modal fade" id="machine-manager-modal" role="dialog" aria-labelledby="machine-manager-modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        </div>
    </div>
</div>

<#--设备进出货模态框弹出内容模板-->
<script id="inout-template" type="text/x-handlebars-template">
    <div class="modal-header">
        <h4 class="modal-title">
            {{title}}
        </h4>
    </div>
    <div class="modal-body">
        <form class="form-horizontal form" role="form">
            <div class="form-group">
                <label for="staffId" class="col-sm-2 control-label">发起方：</label>
                <div class="col-sm-10">
                    <select class="form-control" name="staffId" id="staffId" >
                        {{#each locationList}}
                        <option value="{{locationId}}">{{locationName}}</option>
                        {{/each}}
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="machineId" class="col-sm-2 control-label">设备型号：</label>
                <div class="col-sm-10">
                    <select class="form-control" name="machineId" id="machineId" >
                        {{#each machineList}}
                        <option value="{{machineId}}">{{machineName}}</option>
                        {{/each}}
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="machineCount" class="col-sm-2 control-label">设备数量：</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="machineCount" placeholder="请输入设备数量..." value="{{machineCount}}">
                </div>
            </div>
            <div class="form-group">
                <label for="operationType" class="col-sm-2 control-label">操作类型：</label>
                <div class="col-sm-10">
                    <select class="form-control" name="operationType" id="operationType" >
                        <option value="进货">进货</option>
                        <option value="退货">退货</option>
                    </select>
                </div>
            </div>
            <div class="form-group date">
                <label for="operationTime" class="col-sm-2 control-label">操作时间：</label>
                <div class="input-group col-sm-10" style="padding: 0 15px">
                    <input type="text" class="form-control"  name="operationDate" id="operationDate">
                    <div class="input-group-addon">
                        <span class="glyphicon glyphicon-th"></span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="operationMemo" class="col-sm-2 control-label">操作备注：</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="operationMemo" placeholder="请输入操作备注..." value="{{operationMemo}}">
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer text-center">
        <button type="button" id="saveOperationBtn" class="btn btn-primary">
            <em class="glyphicon glyphicon-floppy-disk"></em> 保存
        </button>
        <button type="button" class="btn btn-default"
                data-dismiss="modal">取消
        </button>
    </div>
</script>

<#--设备领用模态框弹出内容模板-->
<script id="recret-template" type="text/x-handlebars-template">
    <div class="modal-header">
        <h4 class="modal-title">
            {{title}}
        </h4>
    </div>
    <div class="modal-body">
        <form class="form-horizontal form" role="form">
            <div class="form-group">
                <label for="staffId" class="col-sm-2 control-label">领用人：</label>
                <div class="col-sm-10">
                    <select class="form-control" name="staffId" id="staffId" >
                        {{#each staffList}}
                        <option value="{{staffId}}">{{staffName}}</option>
                        {{/each}}
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="locationId" class="col-sm-2 control-label">接收方：</label>
                <div class="col-sm-10">
                    <select class="form-control" name="locationId" id="locationId" >
                        {{#each locationList}}
                        <option value="{{locationId}}">{{locationName}}</option>
                        {{/each}}
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="machineId" class="col-sm-2 control-label">设备型号：</label>
                <div class="col-sm-10">
                    <select class="form-control" name="machineId" id="machineId" >
                        {{#each machineList}}
                        <option value="{{machineId}}">{{machineName}}</option>
                        {{/each}}
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="machineCount" class="col-sm-2 control-label">设备数量：</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="machineCount" placeholder="请输入设备数量..." value="{{machineCount}}">
                </div>
            </div>
            <div class="form-group">
                <label for="operationType" class="col-sm-2 control-label">操作类型：</label>
                <div class="col-sm-10">
                    <select class="form-control" name="operationType" id="operationType" >
                        <option value="领用">领用</option>
                        <option value="归还">归还</option>
                    </select>
                </div>
            </div>
            <div class="form-group date">
                <label for="operationTime" class="col-sm-2 control-label">操作时间：</label>
                <div class="input-group col-sm-10" style="padding: 0 15px">
                    <input type="text" class="form-control"  name="operationDate" id="operationDate">
                    <div class="input-group-addon">
                        <span class="glyphicon glyphicon-th"></span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="operationMemo" class="col-sm-2 control-label">操作备注：</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="operationMemo" placeholder="请输入操作备注..." value="{{operationMemo}}">
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer text-center">
        <button type="button" id="saveOperationBtn" class="btn btn-primary">
            <em class="glyphicon glyphicon-floppy-disk"></em> 保存
        </button>
        <button type="button" class="btn btn-default"
                data-dismiss="modal">取消
        </button>
    </div>
</script>

<#--设备分配模态框弹出内容模板-->
<script id="assign-template" type="text/x-handlebars-template">
    <div class="modal-header">
        <h4 class="modal-title">
            {{title}}
        </h4>
    </div>
    <div class="modal-body">
        <form class="form-horizontal form" role="form">
            <div class="form-group">
                <label for="staffId" class="col-sm-2 control-label">发起方：</label>
                <div class="col-sm-10">
                    <select class="form-control" name="staffId" id="staffId" >
                        {{#each locationList}}
                        <option value="{{locationId}}">{{locationName}}</option>
                        {{/each}}
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="locationId" class="col-sm-2 control-label">接收方：</label>
                <div class="col-sm-10">
                    <select class="form-control" name="locationId" id="locationId" >
                        {{#each locationList}}
                        <option value="{{locationId}}">{{locationName}}</option>
                        {{/each}}
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="machineId" class="col-sm-2 control-label">设备型号：</label>
                <div class="col-sm-10">
                    <select class="form-control" name="machineId" id="machineId" >
                        {{#each machineList}}
                        <option value="{{machineId}}">{{machineName}}</option>
                        {{/each}}
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="machineCount" class="col-sm-2 control-label">设备数量：</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="machineCount" placeholder="请输入设备数量..." value="{{machineCount}}">
                </div>
            </div>
            <div class="form-group">
                <label for="operationType" class="col-sm-2 control-label">操作类型：</label>
                <div class="col-sm-10">
                    <select class="form-control" name="operationType" id="operationType" >
                        <option value="分配">分配</option>
                    </select>
                </div>
            </div>
            <div class="form-group date">
                <label for="operationTime" class="col-sm-2 control-label">操作时间：</label>
                <div class="input-group col-sm-10" style="padding: 0 15px">
                    <input type="text" class="form-control"  name="operationDate" id="operationDate">
                    <div class="input-group-addon">
                        <span class="glyphicon glyphicon-th"></span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="operationMemo" class="col-sm-2 control-label">操作备注：</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="operationMemo" placeholder="请输入操作备注..." value="{{operationMemo}}">
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer text-center">
        <button type="button" id="saveOperationBtn" class="btn btn-primary">
            <em class="glyphicon glyphicon-floppy-disk"></em> 保存
        </button>
        <button type="button" class="btn btn-default"
                data-dismiss="modal">取消
        </button>
    </div>
</script>

<@netCommon.commonScript />
<!-- DataTables -->
<script src="${request.contextPath}/static/adminlte/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${request.contextPath}/static/adminlte/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${request.contextPath}/static/plugins/jquery/jquery.validate.min.js"></script>
<!-- moment -->
<script src="${request.contextPath}/static/adminlte/plugins/daterangepicker/moment.min.js"></script>
<script src="${request.contextPath}/static/adminlte/plugins/datapicker/js/bootstrap-datepicker.min.js"></script>
<script src="${request.contextPath}/static/adminlte/plugins/datapicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${request.contextPath}/static/js/machine-manager.js"></script>
</body>
</html>
