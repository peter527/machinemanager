(function (window, document, $, undefined) {
    $(function () {

        /**
         * 初始化表格，传了分页跟人员姓名信息
         * @type {*|jQuery}
         */
        var machineTable = $("#machine_list").dataTable({
            "deferRender": true,
            "processing": true,
            "serverSide": true,
            "ajax": {
                url: base_url + "/machine/listMachine",
                type: "post",
                data: function (d) {
                    var obj = {};
                    console.log(d);
                    obj.name = $('#factoryName').val();
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
                    "data": 'machineId',
                    "class": 'text-center',
                    "width": 60
                }, {
                    "data": 'machineName'
                }, {
                    "data": 'factory.factoryName'
                }, {
                    "data": 'factory.factoryCharger'
                }, {
                    "data": 'factory.chargerPhone'
                }, {
                    "data": 'status',
                    "render": function (data) {
                        if (data === "0") {
                            return "<span class='label label-info'>正常采购</span>"
                        } else {
                            return "<span class='label label-danger'>暂停采购</span>"
                        }
                    },
                    "class": 'text-center',
                    "width": 40
                }, {
                    "render": function (data, type, full) {
                        if (full.status === "0") {
                            return "<button class='btn btn-warning btn-xs edit'>编辑</button><span style='padding: 0 2px'></span><button class='btn btn-danger btn-xs quit'>暂停采购</button>";
                        } else {
                            return "<button class='btn btn-success btn-xs recovery'>正常采购</button>";
                        }
                    },
                    "class": 'text-center',
                    "width": 80
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
        $('#machine_list tbody').on('click', 'button', function () {
            var data = machineTable.api().row($(this).parents('tr')).data();
            if (data.factoryId) {
                if ($(this).hasClass('edit')) {
                    var context = {"title": "编辑厂商", "operationType": "1"};
                    context = $.extend(context, data);
                    var factory = {};
                    factory["status"] = "0";
                    $.post(base_url + "/factory/listAllFactory", factory, function (data, status) {
                        $('#machine-modal').modal('hide');
                        context = $.extend(context,{"factoryList": data.data});
                        showMachineModal(context);
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
            //machineTable.fnDraw();
            showSimpleLayer('系统提示', '功能尚未开启', '4');
        });

        /**
         * 新增按钮事件，弹出模态框
         */
        $('#addMachineBtn').on('click', function () {
            var context = {"title": "新增厂商", "operationType": "0"};
            var factory = {};
            factory["status"] = "0";
            $.post(base_url + "/factory/listAllFactory", factory, function (data, status) {
                $('#machine-modal').modal('hide');
                context = $.extend(context,{"factoryList": data.data});
                showMachineModal(context);
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
         * 员工模态框弹出方法
         * @param context 用于填充表单的信息
         */
        function showMachineModal(context) {
            var source = $("#machine-template").html();
            var template = Handlebars.compile(source);
            var html = template(context);
            $("#machine-modal .modal-content").html(html);
            var operationType = context.operationType;
            var factoryId = context.factoryId;
            if (operationType === "1") {
                $('input[name = "factoryId"]').attr("readonly", "readonly");
                if (factoryId){
                    $("#factoryId").val(factoryId);
                }
            }
            var addModalValidate = $("#machine-modal .form").validate({
                errorElement: 'span',
                errorClass: 'help-block',
                focusInvalid: true,
                rules: {
                    machineId: {
                        required: true,
                    },
                    machineName: {
                        required: true
                    },
                    factoryId: {
                        required: true
                    }
                },
                messages: {
                    machineId: {
                        required: "设备编号不能为空"
                    },
                    machineName: {
                        required: "设备名称不能为空"
                    },
                    factoryId: {
                        required: "设备厂商不能为空"
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
                    $.post(base_url + "/machine/saveMachine", $("#machine-modal .form").serialize() + "&operationType=" + operationType, function (data, status) {
                        if (data.code === 200) {
                            $('#machine-modal').modal('hide');
                            layer.open({
                                title: '系统提示',
                                content: data.msg,
                                icon: '1',
                                end: function (layero, index) {
                                    machineTable.fnDraw();
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
            $('#machine-modal').data('bs.modal',null);
            $('#machine-modal').modal({backdrop: 'static', keyboard: false}).modal('show');
            $('#saveMachineBtn').on('click', function () {
                $("#machine-modal .form").submit();
            });
        }
    });
})(window, document, window.jQuery);
