$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'oa/oavacation/list',
        datatype: "json",
        colModel: [			
			{ label: 'vaId', name: 'vaId', index: 'va_id', width: 50, key: true,  hidden:true},
			{ label: '标题', name: 'title', index: 'title', width: 80 },
            { label: '创建时间', name: 'createDateTime', index: 'createDateTime', width: 80 }
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
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.oaVacation = {vaType : ''};
            $("#comment").html("");
		},
		update: function (event) {
			var vaId = getSelectedRow();
			if(vaId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(vaId)
		},
		saveOrUpdate: function (event) {
			var url = vm.oaVacation.vaId == null ? "oa/oavacation/save" : "oa/oavacation/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.oaVacation),
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
		getTask:function(event) {
            $.ajax({
                type: "POST",
                url: baseURL + "oa/oavacation/getTask/1",
                contentType: "application/json",
                success: function(r){
                    if(r.code == 0){

                    }
                }
            });
		},
        getHistory:function(event) {
            $.ajax({
                type: "POST",
                url: baseURL + "oa/oavacation/getHistory/1",
                contentType: "application/json",
                success: function(r){
                    if(r.code == 0){

                    }
                }
            });
		},
		del: function (event) {
			var vaIds = getSelectedRows();
			if(vaIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "oa/oavacation/delete",
                    contentType: "application/json",
				    data: JSON.stringify(vaIds),
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
		getInfo: function(vaId){
			$.get(baseURL + "oa/oavacation/info/"+vaId, function(r){
                vm.oaVacation = r.oaVacation;
                console.log(vm.oaVacation);
                $("#comment").html("");
                for(var i = 0; i < r.commentList.length; i++) {
                	$("#comment").append(r.commentList[i].message).append("</br>");
				}
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