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
                    "class": 'text-center',
                    width: '40px'
                }, {
                    render: function (data, type, full, meta) {
                        if (full.operationType === "领用" || full.operationType === "归还"){
                            return full.staffName;
                        } else {
                            return full.raiseName;
                        }
                    }
                }, {
                    "data": 'acceptName'
                }, {
                    "data": 'machineName'
                }, {
                    "data": 'factoryName'
                }, {
                    "data": 'operationType',
                    width: '80px',
                    class: 'text-center',
                    render: function (data) {
                        var returnStr = "";
                        switch (data){
                            case "进货":
                                returnStr = "<span class='label label-info'>进货</span>";
                                break;
                            case "退货":
                                returnStr = "<span class='label label-warning'>退货</span>";
                                break;
                            case "分配":
                                returnStr = "<span class='label label-success'>分配</span>";
                                break;
                            case "领用":
                                returnStr = "<span class='label label-primary'>领用</span>";
                                break;
                            case "归还":
                                returnStr = "<span class='label label-danger'>归还</span>";
                                break;
                            default :
                                break;
                        }
                        return returnStr;
                    }
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
                    width: '100px',
                    class: 'text-center'
                }, {
                    "data": 'machineCount',
                    "width": '80px',
                    class: 'text-center',
                    render: function (data) {
                        return "<span class='label label-default' style='width: 50px; display: inline-block'>" + data +"</span>"
                    }
                }, {
                    "data": 'operationMemo'
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
            var dataJson = {};
            dataJson["idList"] = ["0","2"];
            $.post(base_url + "/select/listLocationAndMachineAndStaff", dataJson, function (data, status) {
                $('#machine-modal').modal('hide');
                context = $.extend(context,{"staffList": data.staffList, "machineList": data.machineList, "locationList": data.locationList});
                showRevRetModal(context);
            }, "json");
        });

        /**
         * 分配按钮点击事件
         */
        $('#assignBtn').on('click', function () {
            var context = {"title": "设备分配管理", "operationType": "0"};
            var dataJson = {};
            dataJson["idList"] = ["0","2"];
            $.post(base_url + "/select/listLocationAndMachine", dataJson, function (data, status) {
                $('#machine-modal').modal('hide');
                context = $.extend(context,{"locationList": data.locationList, "machineList": data.machineList});
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
            $('#operationDate').val(moment(new Date()).format("YYYY/MM/DD"));
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
                    staffId: {
                        required: true
                    },
                    machineId: {
                        required: true
                    },
                    machineCount: {
                        required: true
                    },
                    operationType: {
                        required: true
                    },
                    operationDate: {
                        /*required: true*/
                    }
                },
                messages: {
                    staffId: {
                        required: "发起方不能为空"
                    },
                    machineId: {
                        required: "设备名称不能为空"
                    },
                    machineCount: {
                        required: "设备数量不能为空"
                    },
                    operationType: {
                        required: "操作类型不能为空"
                    },
                    operationDate: {
                        required: "操作日期不能为空"
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
                    var operationData = $("#machine-manager-modal .form").serializeJSON();
                    $.post(base_url + "/operationLog/saveOperationLog", operationData, function (data, status) {
                        if (data.code === 200) {
                            $('#machine-manager-modal').modal('hide');
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
            var source = $("#recret-template").html();
            var template = Handlebars.compile(source);
            var html = template(context);
            $("#machine-manager-modal .modal-content").html(html);
            $('.date').datepicker({
                format: "yyyy/mm/dd",
                language: "zh-CN",
                autoclose: true,
                todayHighlight: true
            });
            $('#operationDate').val(moment(new Date()).format("YYYY/MM/DD"));
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
                    staffId: {
                        required: true
                    },
                    machineId: {
                        required: true
                    },
                    machineCount: {
                        required: true
                    },
                    operationType: {
                        required: true
                    },
                    operationDate: {
                        /*required: true*/
                    }
                },
                messages: {
                    staffId: {
                        required: "发起方不能为空"
                    },
                    machineId: {
                        required: "设备名称不能为空"
                    },
                    machineCount: {
                        required: "设备数量不能为空"
                    },
                    operationType: {
                        required: "操作类型不能为空"
                    },
                    operationDate: {
                        required: "操作日期不能为空"
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
                    var operationData = $("#machine-manager-modal .form").serializeJSON();
                    $.post(base_url + "/operationLog/saveOperationLog", operationData, function (data, status) {
                        if (data.code === 200) {
                            $('#machine-manager-modal').modal('hide');
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
         * 设备分配模态框
         * @param context 用于填充表单的信息
         */
        function showAssignModal(context) {
            var source = $("#assign-template").html();
            var template = Handlebars.compile(source);
            var html = template(context);
            $("#machine-manager-modal .modal-content").html(html);
            $('.date').datepicker({
                format: "yyyy/mm/dd",
                language: "zh-CN",
                autoclose: true,
                todayHighlight: true
            });
            $('#operationDate').val(moment(new Date()).format("YYYY/MM/DD"));
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
                    staffId: {
                        required: true
                    },
                    machineId: {
                        required: true
                    },
                    machineCount: {
                        required: true
                    },
                    operationType: {
                        required: true
                    },
                    operationDate: {
                        /*required: true*/
                    }
                },
                messages: {
                    staffId: {
                        required: "发起方不能为空"
                    },
                    machineId: {
                        required: "设备名称不能为空"
                    },
                    machineCount: {
                        required: "设备数量不能为空"
                    },
                    operationType: {
                        required: "操作类型不能为空"
                    },
                    operationDate: {
                        required: "操作日期不能为空"
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
                    var operationData = $("#machine-manager-modal .form").serializeJSON();
                    if (operationData["staffId"] !== operationData["locationId"]){
                        $.post(base_url + "/operationLog/saveOperationLog", operationData, function (data, status) {
                            if (data.code === 200) {
                                $('#machine-manager-modal').modal('hide');
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
                    } else {
                        layer.open({
                            title: '系统提示',
                            content: ("调配双方不能为同一对象"),
                            icon: '2'
                        });
                    }
                }
            });
            $('#machine-manager-modal').data('bs.modal',null);
            $('#machine-manager-modal').modal({backdrop: 'static', keyboard: false}).modal('show');
            $('#saveOperationBtn').on('click', function () {
                $("#machine-manager-modal .form").submit();
            });
        }
    });
})(window, document, window.jQuery);
