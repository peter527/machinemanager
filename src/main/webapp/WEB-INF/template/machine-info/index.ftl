<!DOCTYPE html>
<html>
<head>
    <title>设备管理平台</title>
<#import "/common/common.macro.ftl" as netCommon>
<@netCommon.commonStyle />
    <!-- DataTables -->
    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/plugins/datatables/dataTables.bootstrap.css">

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
                <small>设备分布</small>
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

            <div class="row hide">
                <div class="col-xs-4">
                    <div class="input-group">
                        <span class="input-group-addon">执行器</span>
                        <select class="form-control" id="jobGroup">
                        <#list JobGroupList as group>
                            <option value="${group.id}" <#if jobGroup==group.id>selected</#if>>${group.title}</option>
                        </#list>
                        </select>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="input-group">
                        <span class="input-group-addon">JobHandler</span>
                        <input type="text" class="form-control" id="executorHandler" autocomplete="on">
                    </div>
                </div>
                <div class="col-xs-2">
                    <button class="btn btn-block btn-info" id="searchBtn">搜索</button>
                </div>
                <div class="col-xs-2">
                    <button class="btn btn-block btn-success add" type="button">+新增任务</button>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header text-center">
                            <h3 class="box-title">设备领用统计</h3>
                        </div>
                        <div class="box-body">
                            <table id="staffMaching-list" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>领用人</th>
                                    <th>设备型号</th>
                                    <th>设备数量</th>
                                </tr>
                                </thead>
                                <tbody></tbody>
                                <tfoot></tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header text-center">
                            <h3 class="box-title">设备库存统计</h3>
                        </div>
                        <div class="box-body">
                            <table id="locationMaching-list" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>设备所在地</th>
                                    <th>设备型号</th>
                                    <th>设备数量</th>
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

<@netCommon.commonScript />
<!-- DataTables -->
<script src="${request.contextPath}/static/adminlte/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${request.contextPath}/static/adminlte/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${request.contextPath}/static/plugins/jquery/jquery.validate.min.js"></script>
<!-- moment -->
<script src="${request.contextPath}/static/adminlte/plugins/daterangepicker/moment.min.js"></script>
<script src="${request.contextPath}/static/js/machine-info.js"></script>
</body>
</html>
