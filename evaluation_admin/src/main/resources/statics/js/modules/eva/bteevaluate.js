$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'eva/bteevaluate/list',
        datatype: "json",
        colModel: [			
			{ label: 'dataNo', name: 'dataNo', index: 'data_no', width: 20, key: true, hidden:true },
			{ label: '测评名称', name: 'evalTitle', index: 'eval_title', width: 70 },
			{ label: '测评说明', name: 'evalMemo', index: 'eval_memo', width: 80 }, 			
			{ label: '课程设置', name: '', index: '', width: 20, 
				  formatter:function(value, options, rowObject){
					    return '<a style="cursor: pointer" onclick="vm.lessonList(' + rowObject.dataNo+ ')">设置</a>';
				  }	
			}, 			
			{ label: '查看结果', name: '', index: '', width: 20,
				  formatter:function(value, options, rowObject){
					    return '<a style="cursor: pointer" onclick="vm.resultList(' + rowObject.dataNo+ ')">查看</a>';
				  }		
			},
			{ label: '状态', name: 'evalStateName', index: 'evalStateName', width: 20, formatter: function(value, options, row){
					var state = "";
                    switch (row.evalStateId) {
						case 0 : state= 'warning'; break;
						case 1 : state= 'success'; break;
						case 2 : state= 'danger'; break;
						default: break;
                    }
                    return '<span class="label label-'+ state +'">'+value+'</span>';
			}},
			{ label: '二维码', name: '', index: '', width: 20,
				formatter:function(cellvalue, options, rowObject){
				    return '<a style="cursor: pointer" onclick="vm.downloadQr(' + rowObject.dataNo+ ')">显示</a>';
			  }	
			},
            { label: '创建时间', name: 'createDate', index: 'createDate', width: 30 }
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
			var url = vm.bteEvaluate.dataNo == null ? "eva/bteevaluate/save" : "eva/bteevaluate/update";
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
				    url: baseURL + "eva/bteevaluate/delete",
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
			$.get(baseURL + "eva/bteevaluate/info/"+dataNo, function(r){
                vm.bteEvaluate = r.bteEvaluate;
            });
		},
		// 课程设置、查看
		lessonList:function(dataNo) {
			window.location.href = "btelesson.html?evalId="+dataNo;
		},
		// 测评结果查看
		resultList:function(dataNo) {
			window.location.href = "bteresult.html?evalId="+dataNo;
		},
		// 生成二维码
		downloadQr:function(dataNo) {
			$.ajax({
				type: "POST",
			    url: baseURL + "eva/bteevaluate/downloadQr/" + dataNo,
	            contentType: "application/json",
			    success: function(r){
					if(r.code == 0){
						console.log(r.qrCode);
						$("#qrCode").attr("src", "data:image/png;base64," + r.qrCode);
					}else{
						alert(r.msg);
					}
				}
			});
			layer.open({
				type: 1,
				skin: 'layui-layer-molv',
				title: "二维码",
				area: ['300px', '350px'],
				shadeClose: false,
				content: jQuery("#qrLayer")
			});
		},
		// 点击未开始
		nostart:function() {
			this.changeState(baseURL + "eva/bteevaluate/nostart/0");
		},
		// 点击开始
		start:function() {
			this.changeState(baseURL + "eva/bteevaluate/start/1");
		},
		over:function() {
			this.changeState(baseURL + "eva/bteevaluate/over/2");
		},
		changeState:function(_url) {
			var dataNos = getSelectedRows();
			if(dataNos == null){
				return ;
			}
			confirm('确定要变更选中的记录的状态？', function(){
				$.ajax({
					type: "POST",
				    url: _url,
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