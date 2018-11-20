$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'oa/oavacation/approvelist',
        datatype: "json",
        colModel: [
            { label: 'processId', name: 'processId', index: 'processId', width: 50, key: true,  hidden:true},
            { label: '标题', name: 'title', index: 'title', width: 80 },
            { label: '创建时间', name: 'requestDate', index: 'requestDate', width: 80 }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

var vm = new Vue({
    el:'#rrapp',
    data:{
        showList: true,
        title: null,
        oaVacation: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        approve: function (event) {
            var processId = getSelectedRow();
            if(processId == null){
                return ;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(processId)
        },
        saveApprove: function (event) {
            var url = "oa/oavacation/saveApprove";

            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify({"processId": vm.oaVacation.processId, "content": $("#content").val()}),
                success: function(r){
                    if(r.code === 0){
                        alert('审批成功', function(index){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },

        getInfo: function(processId){
            $.get(baseURL + "oa/oavacation/approveinfo/"+processId, function(r){
                vm.oaVacation = r.oaVacation;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                page:page
            }).trigger("reloadGrid");
        }
    }
});