<!DOCTYPE html>
<html>
<head>
    <title>设备管理平台</title>
<#import "/common/common.macro.ftl" as netCommon>
<@netCommon.commonStyle />
    <!-- DataTables -->
    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/plugins/datatables/dataTables.bootstrap.css">
    <style>
        #addModal .modal-dialog {
            max-width: 600px;
        }

        .help-block {
            margin-bottom: 0px;
            color: red;
        }
    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini <#if cookieMap?exists && "off" == cookieMap["xxljob_adminlte_settings"].value >sidebar-collapse</#if>">
<div class="wrapper">
    <!-- header -->
<@netCommon.commonHeader />
    <!-- left -->
<@netCommon.commonLeft "location" />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>数据字典
                <small>项目地点</small>
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
                        <span class="input-group-addon">项目地点</span>
                        <input type="text" class="form-control" id="locationName" autocomplete="on">
                        </select>
                    </div>
                </div>
                <div class="col-xs-1 col-xs-offset-6">
                    <button class="btn btn-block btn-info" id="searchBtn">搜索</button>
                </div>
                <div class="col-xs-1">
                    <button class="btn btn-block btn-success" id="addLocationBtn">新增</button>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header hide">
                            <h3 class="box-title">项目地点列表</h3>
                        </div>
                        <div class="box-body">
                            <table id="location_list" class="table table-hover table-bordered table-striped table-responsive">
                                <thead>
                                <tr>
                                    <th>地点编号</th>
                                    <th>地点名称</th>
                                    <th>对方负责人</th>
                                    <th>联系方式</th>
                                    <th>公司负责人</th>
                                    <th>联系方式</th>
                                    <th>项目名称</th>
                                    <th>状态</th>
                                    <th>操作</th>
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
<div class="modal fade" id="location-modal" role="dialog" aria-labelledby="project-modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        </div>
    </div>
</div>

<#--模态框弹出内容模板-->
<script id="location-template" type="text/x-handlebars-template">
    <div class="modal-header">
        <h4 class="modal-title">
            {{title}}
        </h4>
    </div>
    <div class="modal-body">
        <form class="form-horizontal form" role="form">
            <div class="form-group">
                <label for="locationId" class="col-sm-3 control-label">项目点编号：</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="locationId" placeholder="请输入地点编号..." maxlength="4" value="{{locationId}}">
                    <span class="col-sm help-block">项目编号请遵循【L010】这种格式,L开头加3位数字</span>
                </div>
            </div>
            <div class="form-group">
                <label for="locationName" class="col-sm-3 control-label">项目点名称：</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="locationName" placeholder="请输入地点名称..." value="{{locationName}}">
                </div>
            </div>
            <div class="form-group">
                <label for="locationChargerName" class="col-sm-3 control-label">对方负责人：</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="locationChargerName" placeholder="请输入对方负责人姓名..." value="{{locationChargerName}}">
                </div>
            </div>
            <div class="form-group">
                <label for="locationChargerPhone" class="col-sm-3 control-label">联系方式：</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="locationChargerPhone" placeholder="请输入最新版本号..." value="{{locationChargerPhone}}">
                </div>
            </div>
            <div class="form-group">
                <label for="staffId" class="col-sm-3 control-label">公司负责人：</label>
                <div class="col-sm-9">
                    <select class="form-control" name="staffId" id="staffId" >
                        {{#each staffList}}
                        <option value="{{staffId}}">{{staffName}}</option>
                        {{/each}}
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="projectId" class="col-sm-3 control-label">项目类型：</label>
                <div class="col-sm-9">
                    <select class="form-control" name="projectId" id="projectId" >
                        {{#each projectList}}
                        <option value="{{projectId}}">{{projectName}}</option>
                        {{/each}}
                    </select>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer text-center">
        <button type="button" id="saveLocationBtn" class="btn btn-primary">
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
<script src="${request.contextPath}/static/js/location.js"></script>
</body>
</html>
