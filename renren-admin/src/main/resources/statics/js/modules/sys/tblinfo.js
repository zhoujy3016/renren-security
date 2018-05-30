$(function () {
    // 初始化下拉菜单
    getDictList();
    // 表格生成
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/tblinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'infoId', name: 'infoId', index: 'info_id', width: 50, key: true, hidden:true },
			{ label: '标题', name: 'infoTitle', index: 'info_title', width: 80 }, 			
			{ label: '类型', name: 'infoTypeName', index: '', width: 40 },
			{ label: '创建时间', name: 'infoCreateTime', index: 'info_create_time', width: 40 }
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
    
    CKEDITOR.replace('ckeditor');
});


function getDictList() {
	var r = initDictData("xwlx");
	vm.dictXwlx = r.xwlx;
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			title:null,
			type:null
		},
		showList: true,
		title: null,
		tblInfo: {},
		dictXwlx:{}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tblInfo = {};
		},
		update: function (event) {
			var infoId = getSelectedRow();
			if(infoId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(infoId)
		},
		view:function(event) {
			var infoId = getSelectedRow();
			if(infoId == null){
				return ;
			}
			window.location.href = "tblinfoview.html?infoId="+infoId;
		},
		saveOrUpdate: function (event) {
			var url = vm.tblInfo.infoId == null ? "sys/tblinfo/save" : "sys/tblinfo/update";
			var content = CKEDITOR.instances.ckeditor.getData();
			vm.tblInfo.infoContent = escape(content);
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.tblInfo),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
							CKEDITOR.instances.ckeditor.setData('');
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var infoIds = getSelectedRows();
			if(infoIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/tblinfo/delete",
                    contentType: "application/json",
				    data: JSON.stringify(infoIds),
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
		getInfo: function(infoId){
			$.get(baseURL + "sys/tblinfo/info/"+infoId, function(r){
                vm.tblInfo = r.tblInfo;
                CKEDITOR.instances.ckeditor.setData(unescape(vm.tblInfo.infoContent));
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{"title":vm.q.title, "type":vm.q.type},
                page:page
            }).trigger("reloadGrid");
		},
	}
});