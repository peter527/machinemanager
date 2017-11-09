(function (window, document, $, undefined) {
    $(function () {

        /**
         * 初始化表格，传了分页跟人员姓名信息
         * @type {*|jQuery}
         */
        var operationLogTable = $("#operationLog_list").dataTable({
            "deferRender": true,
            "processing": true,
            "serverSide": true,
            "ajax": {
                url: base_url + "/operationLog/listOperationLog",
                type: "post",
                data: function (d) {
                    var obj = {};
                    obj.start = d.start;
                    obj.length = d.length;
                    return obj;
                }
            },
            "searching": false,
            "ordering": false,
            //"scrollX": true,	// X轴滚动条，取消自适应
            "columns": [
                {
                    render: function (data, type, full, meta) {
                        return meta.settings._iDisplayStart + meta.row + 1;
                    },
                    "class": 'bg-order-column',
                    targets: 0
                }, {
                    "data": 'staffId'
                }, {
                    "data": 'locationId'
                }, {
                    "data": 'machineId'
                }, {
                    "data": 'operationType'
                }, {
                    "data": 'operationDate',
                    render: function (data, type, full, meta) {
                        if(data == null || data ==''){
                            return '';
                        }else{
                            var date = moment(data);
                            if (date)
                                return date.format("YYYY年MM月DD日");
                            else
                                return '';
                        }
                    },
                }, {
                    "data": 'machineCount'
                }
            ],
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "每页 _MENU_ 条记录",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "第 _PAGE_ 页 ( 总共 _PAGES_ 页，_TOTAL_ 条记录 )",
                "sInfoEmpty": "无记录",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            }
        });

        /**
         * 表格中操作栏中的按钮点击事件
         */
        $('#location_list tbody').on('click', 'button', function () {
            var data = operationLogTable.api().row($(this).parents('tr')).data();
            if (data.locationId) {
                if ($(this).hasClass('edit')) {
                    var context = {"title": "编辑项目点", "operationType": "1"};
                    context = $.extend(context, data);
                    var staff = {};
                    staff["staffStatus"] = "0";
                    staff["status"] = "0";
                    $.post(base_url + "/staff/listStaffAndProject", staff, function (data, status) {
                        $('#machine-modal').modal('hide');
                        context = $.extend(context,{"staffList": data.staffList, "projectList": data.projectList});
                        showLocationModal(context);
                    }, "json");
                }
                if ($(this).hasClass('quit')) {
                    showSimpleLayer('系统提示', '功能尚未开启', '4');
                }
            }
        });

        /**
         * 搜索按钮事件，重绘表格
         */
        $('#searchBtn').on('click', function () {
            //locationTable.fnDraw();
            showSimpleLayer('系统提示', '功能尚未开启', '4');
        });

        /**
         * 进出货按钮点击事件
         */
        $('#inOutBtn').on('click', function () {
            var context = {"title": "设备进出管理", "operationType": "0"};
            var dataJson = {};
            dataJson["idList"] = ["0","2"];
            $.post(base_url + "/select/listLocationAndMachine", dataJson, function (data, status) {
                $('#machine-modal').modal('hide');
                context = $.extend(context,{"machineList": data.machineList, "locationList": data.locationList});
                showInOutModal(context);
            }, "json");
        });

        /**
         * 领用按钮点击事件
         */
        $('#receiveReturnBtn').on('click', function () {
            var context = {"title": "设备领用管理", "operationType": "0"};
            var staff = {};
            staff["staffStatus"] = "0";
            staff["status"] = "0";
            $.post(base_url + "/staff/listStaffAndProject", staff, function (data, status) {
                $('#machine-modal').modal('hide');
                context = $.extend(context,{"staffList": data.staffList, "projectList": data.projectList});
                showRevRetModal(context);
            }, "json");
        });

        /**
         * 分配按钮点击事件
         */
        $('#assignBtn').on('click', function () {
            var context = {"title": "设备分配管理", "operationType": "0"};
            var staff = {};
            staff["staffStatus"] = "0";
            staff["status"] = "0";
            $.post(base_url + "/staff/listStaffAndProject", staff, function (data, status) {
                $('#machine-modal').modal('hide');
                context = $.extend(context,{"staffList": data.staffList, "projectList": data.projectList});
                showAssignModal(context);
            }, "json");
        });

        /**
         * 弹出框的通用方法
         * @param title 标题
         * @param content 内容
         * @param icon 显示的图标
         */
        function showSimpleLayer(title, content, icon) {
            layer.open({
                title: title,
                content: content,
                icon: icon
            });
        }

        /**
         * 设备进出货模态框
         * @param context 用于填充表单的信息
         */
        function showInOutModal(context) {
            var source = $("#inout-template").html();
            var template = Handlebars.compile(source);
            var html = template(context);
            $("#machine-manager-modal .modal-content").html(html);
            $('.date').datepicker({
                format: "yyyy/mm/dd",
                language: "zh-CN",
                autoclose: true,
                todayHighlight: true
            });
            var operationType = context.operationType;
            var staffId = context.staffId;
            var projectId = context.projectId;
            console.log(context);
            if (operationType === "1") {
                $('input[name = "locationId"]').attr("readonly", "readonly");
                if (staffId){
                    $("#staffId").val(staffId);
                }
                if (projectId){
                    $("#projectId").val(projectId);
                }
            }
            var addModalValidate = $("#machine-manager-modal .form").validate({
                errorElement: 'span',
                errorClass: 'help-block',
                focusInvalid: true,
                rules: {
                    locationId: {
                        required: true
                    },
                    locationName: {
                        required: true
                    },
                    locationChargerName: {
                        required: true
                    },
                    locationChargerPhone: {
                        required: true
                    },
                    staffId: {
                        required: true
                    },
                    projectId: {
                        required: true
                    }
                },
                messages: {
                    locationId: {
                        required: "项目点编号不能为空"
                    },
                    locationName: {
                        required: "项目点名称不能为空"
                    },
                    locationChargerName: {
                        required: "对方负责人不能为空"
                    },
                    locationChargerPhone: {
                        required: "对方负责人联系方式不能为空"
                    },
                    staffId: {
                        required: "负责人不能为空"
                    },
                    projectId: {
                        required: "项目名称不能为空"
                    }
                },
                highlight: function (element) {
                    $(element).closest('.form-group').addClass('has-error');
                },
                success: function (label) {
                    label.closest('.form-group').removeClass('has-error');
                    label.remove();
                },
                errorPlacement: function (error, element) {
                    element.parent('div').append(error);
                },
                submitHandler: function (form) {
                    var locationData = $("#machine-manager-modal .form").serializeJSON();
                    $.post(base_url + "/operationLog/saveOperationLog", locationData, function (data, status) {
                        if (data.code === 200) {
                            $('#location-modal').modal('hide');
                            layer.open({
                                title: '系统提示',
                                content: data.msg,
                                icon: '1',
                                end: function (layero, index) {
                                    operationLogTable.fnDraw();
                                }
                            });
                        } else {
                            layer.open({
                                title: '系统提示',
                                content: (data.msg || "新增失败"),
                                icon: '2'
                            });
                        }
                    }, "json");
                }
            });
            $('#machine-manager-modal').data('bs.modal',null);
            $('#machine-manager-modal').modal({backdrop: 'static', keyboard: false}).modal('show');
            $('#saveOperationBtn').on('click', function () {
                $("#machine-manager-modal .form").submit();
            });
        }

        /**
         * 设备领用模态框
         * @param context 用于填充表单的信息
         */
        function showRevRetModal(context) {
            var source = $("#inout-template").html();
            var template = Handlebars.compile(source);
            var html = template(context);
            $("#machine-manager-modal .modal-content").html(html);
            var operationType = context.operationType;
            var staffId = context.staffId;
            var projectId = context.projectId;
            console.log(context);
            if (operationType === "1") {
                $('input[name = "locationId"]').attr("readonly", "readonly");
                if (staffId){
                    $("#staffId").val(staffId);
                }
                if (projectId){
                    $("#projectId").val(projectId);
                }
            }
            var addModalValidate = $("#machine-manager-modal .form").validate({
                errorElement: 'span',
                errorClass: 'help-block',
                focusInvalid: true,
                rules: {
                    locationId: {
                        required: true
                    },
                    locationName: {
                        required: true
                    },
                    locationChargerName: {
                        required: true
                    },
                    locationChargerPhone: {
                        required: true
                    },
                    staffId: {
                        required: true
                    },
                    projectId: {
                        required: true
                    }
                },
                messages: {
                    locationId: {
                        required: "项目点编号不能为空"
                    },
                    locationName: {
                        required: "项目点名称不能为空"
                    },
                    locationChargerName: {
                        required: "对方负责人不能为空"
                    },
                    locationChargerPhone: {
                        required: "对方负责人联系方式不能为空"
                    },
                    staffId: {
                        required: "负责人不能为空"
                    },
                    projectId: {
                        required: "项目名称不能为空"
                    }
                },
                highlight: function (element) {
                    $(element).closest('.form-group').addClass('has-error');
                },
                success: function (label) {
                    label.closest('.form-group').removeClass('has-error');
                    label.remove();
                },
                errorPlacement: function (error, element) {
                    element.parent('div').append(error);
                },
                submitHandler: function (form) {
                    var locationData = $("#machine-manager-modal .form").serializeJSON();
                    locationData["operationType"] = operationType;
                    locationData["locationType"] = "2";
                    console.log(locationData);
                    $.post(base_url + "/location/saveLocation", locationData, function (data, status) {
                        if (data.code === 200) {
                            $('#location-modal').modal('hide');
                            layer.open({
                                title: '系统提示',
                                content: data.msg,
                                icon: '1',
                                end: function (layero, index) {
                                    operationLogTable.fnDraw();
                                }
                            });
                        } else {
                            layer.open({
                                title: '系统提示',
                                content: (data.msg || "新增失败"),
                                icon: '2'
                            });
                        }
                    }, "json");
                }
            });
            $('#machine-manager-modal').data('bs.modal',null);
            $('#machine-manager-modal').modal({backdrop: 'static', keyboard: false}).modal('show');
            $('#saveLocationBtn').on('click', function () {
                $("machine-manager-modal .form").submit();
            });
        }

        /**
         * 设备分配模态框
         * @param context 用于填充表单的信息
         */
        function showAssignModal(context) {
            var source = $("#inout-template").html();
            var template = Handlebars.compile(source);
            var html = template(context);
            $("#machine-manager-modal .modal-content").html(html);
            var operationType = context.operationType;
            var staffId = context.staffId;
            var projectId = context.projectId;
            console.log(context);
            if (operationType === "1") {
                $('input[name = "locationId"]').attr("readonly", "readonly");
                if (staffId){
                    $("#staffId").val(staffId);
                }
                if (projectId){
                    $("#projectId").val(projectId);
                }
            }
            var addModalValidate = $("#machine-manager-modal .form").validate({
                errorElement: 'span',
                errorClass: 'help-block',
                focusInvalid: true,
                rules: {
                    locationId: {
                        required: true
                    },
                    locationName: {
                        required: true
                    },
                    locationChargerName: {
                        required: true
                    },
                    locationChargerPhone: {
                        required: true
                    },
                    staffId: {
                        required: true
                    },
                    projectId: {
                        required: true
                    }
                },
                messages: {
                    locationId: {
                        required: "项目点编号不能为空"
                    },
                    locationName: {
                        required: "项目点名称不能为空"
                    },
                    locationChargerName: {
                        required: "对方负责人不能为空"
                    },
                    locationChargerPhone: {
                        required: "对方负责人联系方式不能为空"
                    },
                    staffId: {
                        required: "负责人不能为空"
                    },
                    projectId: {
                        required: "项目名称不能为空"
                    }
                },
                highlight: function (element) {
                    $(element).closest('.form-group').addClass('has-error');
                },
                success: function (label) {
                    label.closest('.form-group').removeClass('has-error');
                    label.remove();
                },
                errorPlacement: function (error, element) {
                    element.parent('div').append(error);
                },
                submitHandler: function (form) {
                    var locationData = $("#machine-manager-modal .form").serializeJSON();
                    locationData["operationType"] = operationType;
                    locationData["locationType"] = "2";
                    console.log(locationData);
                    $.post(base_url + "/location/saveLocation", locationData, function (data, status) {
                        if (data.code === 200) {
                            $('#location-modal').modal('hide');
                            layer.open({
                                title: '系统提示',
                                content: data.msg,
                                icon: '1',
                                end: function (layero, index) {
                                    operationLogTable.fnDraw();
                                }
                            });
                        } else {
                            layer.open({
                                title: '系统提示',
                                content: (data.msg || "新增失败"),
                                icon: '2'
                            });
                        }
                    }, "json");
                }
            });
            $('#machine-manager-modal').data('bs.modal',null);
            $('#machine-manager-modal').modal({backdrop: 'static', keyboard: false}).modal('show');
            $('#saveLocationBtn').on('click', function () {
                $("machine-manager-modal .form").submit();
            });
        }
    });
})(window, document, window.jQuery);
