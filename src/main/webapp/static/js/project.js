(function (window, document, $, undefined) {
    $(function () {

        /**
         * 初始化表格，传了分页跟人员姓名信息
         * @type {*|jQuery}
         */
        var projectTable = $("#project_list").dataTable({
            "deferRender": true,
            "processing": true,
            "serverSide": true,
            "ajax": {
                url: base_url + "/project/listProject",
                type: "post",
                data: function (d) {
                    var obj = {};
                    console.log(d);
                    obj.name = $('#projectName').val();
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
                    "data": 'projectId',
                    "class": 'text-center',
                    "width": 60
                }, {
                    "data": 'projectName'
                }, {
                    "data": 'appName'
                }, {
                    "data": 'appLatestVersion'
                }, {
                    "data": 'staff.staffName'
                }, {
                    "data": 'staff.staffPhone'
                }, {
                    "data": 'status',
                    "render": function (data) {
                        if (data === "0") {
                            return "<span class='label label-info'>正常</span>"
                        } else {
                            return "<span class='label label-danger'>暂停</span>"
                        }
                    },
                    "class": 'text-center',
                    "width": 40
                }, {
                    "render": function (data, type, full) {
                        if (full.status === "0") {
                            return "<button class='btn btn-warning btn-xs edit'>编辑</button><span style='padding: 0 2px'></span><button class='btn btn-danger btn-xs quit'>暂停</button>";
                        } else {
                            return "<button class='btn btn-success btn-xs recovery'>正常</button>";
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
        $('#project_list tbody').on('click', 'button', function () {
            var data = projectTable.api().row($(this).parents('tr')).data();
            if (data.projectId) {
                if ($(this).hasClass('edit')) {
                    var context = {"title": "编辑厂商", "operationType": "1"};
                    context = $.extend(context, data);
                    var staff = {};
                    staff["staffStatus"] = "0";
                    $.post(base_url + "/staff/listAllStaff", staff, function (data, status) {
                        $('#machine-modal').modal('hide');
                        context = $.extend(context,{"staffList": data.data});
                        showProjectModal(context);
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
            //projectTable.fnDraw();
            showSimpleLayer('系统提示', '功能尚未开启', '4');
        });

        /**
         * 新增按钮事件，弹出模态框
         */
        $('#addProjectBtn').on('click', function () {
            var context = {"title": "新增厂商", "operationType": "0"};
            var staff = {};
            staff["staffStatus"] = "0";
            $.post(base_url + "/staff/listAllStaff", staff, function (data, status) {
                $('#machine-modal').modal('hide');
                context = $.extend(context,{"staffList": data.data});
                showProjectModal(context);
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
        function showProjectModal(context) {
            var source = $("#project-template").html();
            var template = Handlebars.compile(source);
            var html = template(context);
            $("#project-modal .modal-content").html(html);
            var operationType = context.operationType;
            var staffId = context.staffId;
            console.log(context);
            if (operationType === "1") {
                $('input[name = "projectId"]').attr("readonly", "readonly");
                if (staffId){
                    $("#staffId").val(staffId);
                }
            }
            var addModalValidate = $("#project-modal .form").validate({
                errorElement: 'span',
                errorClass: 'help-block',
                focusInvalid: true,
                rules: {
                    projectId: {
                        required: true
                    },
                    projectName: {
                        required: true
                    },
                    appName: {
                        required: true
                    },
                    appLatestVersion: {
                        required: true
                    },
                    staffId: {
                        required: true
                    }
                },
                messages: {
                    projectId: {
                        required: "项目编号不能为空"
                    },
                    projectName: {
                        required: "项目名称不能为空"
                    },
                    appName: {
                        required: "app名称不能为空"
                    },
                    appLatestVersion: {
                        required: "app最新版本不能为空"
                    },
                    staffId: {
                        required: "负责人不能为空"
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
                    $.post(base_url + "/project/saveProject", $("#project-modal .form").serialize() + "&operationType=" + operationType, function (data, status) {
                        if (data.code === 200) {
                            $('#project-modal').modal('hide');
                            layer.open({
                                title: '系统提示',
                                content: data.msg,
                                icon: '1',
                                end: function (layero, index) {
                                    projectTable.fnDraw();
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
            $('#project-modal').data('bs.modal',null);
            $('#project-modal').modal({backdrop: 'static', keyboard: false}).modal('show');
            $('#saveProjectBtn').on('click', function () {
                $("#project-modal .form").submit();
            });
        }
    });
})(window, document, window.jQuery);
