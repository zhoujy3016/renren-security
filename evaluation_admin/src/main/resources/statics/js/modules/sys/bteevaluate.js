$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/bteevaluate/list',
        datatype: "json",
        colModel: [			
			{ label: 'dataNo', name: 'dataNo', index: 'data_no', width: 50, key: true, hidden:true },
			{ label: '测评名称', name: 'evalTitle', index: 'eval_title', width: 80 }, 			
			{ label: '测评说明', name: 'evalMemo', index: 'eval_memo', width: 80 }, 			
			{ label: '课程设置', name: '', index: '', width: 40, 
				  formatter:function(cellvalue, options, rowObject){
					    return '<a onclick="vm.lessonList(' + rowObject.dataNo+ ')">设置</a>';
				  }	
			}, 			
			{ label: '查看结果', name: '', index: '', width: 40,
				  formatter:function(cellvalue, options, rowObject){
					    return '<a onclick="vm.resultList(' + rowObject.dataNo+ ')">查看</a>';
				  }		
			},
			{ label: '状态', name: 'evalStateName', index: 'evalStateName', width: 40 }, 
			{ label: '二维码', name: '', index: '', width: 40 }, 	
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
		bteEvaluate: {},
		showState:true // 状态radio 控制
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.bteEvaluate = {};
		},
		update: function (event) {
			var dataNo = getSelectedRow();
			if(dataNo == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(dataNo)
            // 更新的情况下，允许显示状态radiobutton
            vm.showState = false;
		},
		saveOrUpdate: function (event) {
			var url = vm.bteEvaluate.dataNo == null ? "sys/bteevaluate/save" : "sys/bteevaluate/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.bteEvaluate),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var dataNos = getSelectedRows();
			if(dataNos == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/bteevaluate/delete",
                    contentType: "application/json",
				    data: JSON.stringify(dataNos),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(dataNo){
			$.get(baseURL + "sys/bteevaluate/info/"+dataNo, function(r){
                vm.bteEvaluate = r.bteEvaluate;
            });
		},
		// 课程设置、查看
		lessonList:function(dataNo) {
			alert(dataNo);
		},
		// 测评结果查看
		resultList:function(dataNo) {
			alert(dataNo);
		}
		reload: function (event) {
			vm.showList = true;
			vm.showState = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});