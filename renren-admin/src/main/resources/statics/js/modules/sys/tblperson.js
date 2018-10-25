$(function () {
	// 表格生成
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/tblperson/list',
        datatype: "json", // 初始化 json：加载数据  local:不加载
        colModel: [
        	{ label: 'ID', name: 'personId', index: "person_id", width: 45, key: true, hidden:true},
			{ label: '姓名', name: 'personName', index: 'person_name', width: 80 },
			{ label: '民族', name: 'nationalityName', index: 'nationalityName', width: 50 },
			{ label: '地区', name: 'areaName', index: 'area', width: 50 },
			{ label: '电话', name: 'telephone', index: 'telephone', width: 50 },
			{ label: '备注', name: 'remark', index: 'remark', width: 150 }
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

    // 人员导入
    new AjaxUpload('#import', {
        action: baseURL + "sys/tblperson/importUsers",
        name: 'excel_users',
        autoSubmit:true,
        responseType:"json",
        onSubmit:function(file, extension){
            if (!(extension && /^(xlsx|xls)$/.test(extension.toLowerCase()))){
                alert('只支持xlsx,xls格式');
                return false;
            }
        },
        onComplete : function(file, r){
            console.log(r);
            if(r.code == 0){
                vm.reload();
            }
            alert(r.msg);
        }
    });

    // 上传照片
    new AjaxUpload('#uploadImage', {
        action: baseURL + "sys/tblperson/uploadUserImage",
        name: 'img_user',
        autoSubmit:true,
        responseType:"json",
        onSubmit:function(file, extension){
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))){
                alert('只支持jpg、png、gif格式的图片！');
                return false;
            }
        },
        onComplete : function(file, r){
            console.log(r);
        	console.log(r.headImg);
            $("#headImage").attr("src", baseURL + "sys/tblperson/showImage/" + r.headImg);

        }
    });

});

var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
        	name: null,
        	nationality:'',
        	area:''
        },
		showList: true,
		title: null,
		tblPerson: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tblPerson = {nationality:'', area:''};
		},
		update: function (event) {
			var personId = getSelectedRow();
			if(personId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(personId)
		},
		saveOrUpdate: function (event) {
			var url = vm.tblPerson.personId == null ? "sys/tblperson/save" : "sys/tblperson/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.tblPerson),
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
			var personIds = getSelectedRows();
			if(personIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/tblperson/delete",
                    contentType: "application/json",
				    data: JSON.stringify(personIds),
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
		getInfo: function(personId){
			$.get(baseURL + "sys/tblperson/info/"+personId, function(r){
                vm.tblPerson = r.tblPerson;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{'name': vm.q.name, 'nationality' : vm.q.nationality, "area":vm.q.area},
                page:page
            }).trigger("reloadGrid");
		}
	}
});