$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'eva/btequestion/list',
        datatype: "json",
        colModel: [			
			{ label: 'dataNo', name: 'dataNo', index: 'data_no', width: 50, key: true, hidden:true },
			{ label: '题干名称', name: 'questionTitle', index: 'questionTitle', width: 80 }, 			
			{ label: '所属分类', name: 'questionTypeName', index: 'questionTypeName', width: 80 }, 			
			{ label: '启用状态', name: 'questionStateName', index: 'questionStateName', width: 40 , formatter: function(value, options, row){
                    var state = "";
                    if (row.questionStateId == 0) {
                    	state= 'danger'
                    } else {
                    	state= 'success';
                    }
                    return '<span class="label label-'+ state +'">'+value+'</span>';
			}}
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
		bteQuestion: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.bteQuestion = {questionTypeId:''}; // 默认选中“请选择”
		},
		update: function (event) {
			console.log(dataNo);
			var dataNo = getSelectedRow();
			if(dataNo == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(dataNo)
		},
		saveOrUpdate: function (event) {
			var url = vm.bteQuestion.dataNo == null ? "eva/btequestion/save" : "eva/btequestion/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.bteQuestion),
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
				    url: baseURL + "eva/btequestion/delete",
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
		state:function() {
			var dataNos = getSelectedRows();
			if(dataNos == null){
				return ;
			}
			confirm('确定要变更选中的记录的状态？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "eva/btequestion/state",
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
			$.get(baseURL + "eva/btequestion/info/"+dataNo, function(r){
                vm.bteQuestion = r.bteQuestion;
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