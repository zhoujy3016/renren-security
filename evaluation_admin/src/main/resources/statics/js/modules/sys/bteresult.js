$(function () {
	// 通过url获得参数
	var arrParam = getParameters(location.search);
	
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/bteresult/list/' + arrParam['evalId'],
        datatype: "json",
        colModel: [			
			{ label: '题干名称', name: 'questionTitle', index: 'questionTitle', width: 80 }, 			
			{ label: '提交人数', name: 'evaPersonNum', index: 'evaPersonNum', width: 20 }, 			
			{ label: '1分人数', name: 'socre1', index: 'socre1', width: 20 }, 			
			{ label: '2分人数', name: 'socre2', index: 'socre2', width: 20 }, 			
			{ label: '3分人数', name: 'socre3', index: 'socre3', width: 20 }, 			
			{ label: '4分人数', name: 'socre4', index: 'socre4', width: 20 },
			{ label: '5分人数', name: 'socre5', index: 'socre5', width: 20 },
			{ label: '平均分', name: 'avgScore', index: 'avgScore', width: 20 }
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
            root: "result"
//            page: "page.currPage",
//            total: "page.totalPage",
//            records: "page.totalCount"
        },
        prmNames : {
//            page:"page", 
//            rows:"limit", 
//            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        },
        loadComplete: function(data) {
        	vm.evalId = data.evalId;
        }
        
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		bteResult: {},
		evalId:null
	},
	methods: {
		back: function (event) {
			history.go(-1);
		}
//		query: function () {
//			vm.reload();
//		},
//		add: function(){
//			vm.showList = false;
//			vm.title = "新增";
//			vm.bteResult = {};
//		},
//		update: function (event) {
//			var dataNo = getSelectedRow();
//			if(dataNo == null){
//				return ;
//			}
//			vm.showList = false;
//            vm.title = "修改";
//            
//            vm.getInfo(dataNo)
//		},
//		saveOrUpdate: function (event) {
//			var url = vm.bteResult.dataNo == null ? "sys/bteresult/save" : "sys/bteresult/update";
//			$.ajax({
//				type: "POST",
//			    url: baseURL + url,
//                contentType: "application/json",
//			    data: JSON.stringify(vm.bteResult),
//			    success: function(r){
//			    	if(r.code === 0){
//						alert('操作成功', function(index){
//							vm.reload();
//						});
//					}else{
//						alert(r.msg);
//					}
//				}
//			});
//		},
//		del: function (event) {
//			var dataNos = getSelectedRows();
//			if(dataNos == null){
//				return ;
//			}
//			
//			confirm('确定要删除选中的记录？', function(){
//				$.ajax({
//					type: "POST",
//				    url: baseURL + "sys/bteresult/delete",
//                    contentType: "application/json",
//				    data: JSON.stringify(dataNos),
//				    success: function(r){
//						if(r.code == 0){
//							alert('操作成功', function(index){
//								$("#jqGrid").trigger("reloadGrid");
//							});
//						}else{
//							alert(r.msg);
//						}
//					}
//				});
//			});
//		},
//		getInfo: function(dataNo){
//			$.get(baseURL + "sys/bteresult/info/"+dataNo, function(r){
//                vm.bteResult = r.bteResult;
//            });
//		},
//		reload: function (event) {
//			vm.showList = true;
//			var page = $("#jqGrid").jqGrid('getGridParam','page');
//			$("#jqGrid").jqGrid('setGridParam',{ 
//                page:page
//            }).trigger("reloadGrid");
//		}
	}
});