$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'eva/btelessontype/list',
        datatype: "json",
        colModel: [			
			{ label: 'dataNo', name: 'dataNo', index: 'data_no', width: 50, key: true, hidden:true },
			{ label: '课程所属分类', name: 'categoryName', index: 'categoryName', width: 40 },
			{ label: '课程类型名称', name: 'typeName', index: 'type_name', width: 80 }
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
		bteLessonType: {},
        q:{
            category:''
        }

	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.bteLessonType = {categoryId:''}; // 默认选中“请选择”
		},
		update: function (event) {
			var dataNo = getSelectedRow();
			if(dataNo == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(dataNo)
		},
		saveOrUpdate: function (event) {
			var url = vm.bteLessonType.dataNo == null ? "eva/btelessontype/save" : "eva/btelessontype/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.bteLessonType),
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
				    url: baseURL + "eva/btelessontype/delete",
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
			$.get(baseURL + "eva/btelessontype/info/"+dataNo, function(r){
                vm.bteLessonType = r.bteLessonType;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{"categoryId":vm.q.category},
                page:page,
            }).trigger("reloadGrid");
		}
	}
});