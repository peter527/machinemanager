(function (window, document, $, undefined) {
    $(function () {

        /**
         * 初始化表格，传了分页跟人员姓名信息
         * @type {*|jQuery}
         */
        var staffMachineTable = $("#staffMaching-list").dataTable({
            "deferRender": true,
            "processing": true,
            "serverSide": true,
            "ajax": {
                url: base_url + "/machineStatistic/listStaffMachine",
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
                    "data": 'staff.staffName'
                }, {
                    "data": 'machine.machineName'
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

        var locationMachineTable = $("#locationMaching-list").dataTable({
            "deferRender": true,
            "processing": true,
            "serverSide": true,
            "ajax": {
                url: base_url + "/machineStatistic/listLocationMachine",
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
                    "data": 'location.locationName'
                }, {
                    "data": 'machine.machineName'
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

    });
})(window, document, window.jQuery);
