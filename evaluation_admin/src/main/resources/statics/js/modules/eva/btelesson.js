$(function () {
	// 通过url获得参数
	var arrParam = getParameters(location.search);
	
    $("#jqGrid").jqGrid({
        url: baseURL + 'eva/btelesson/list/' + arrParam['evalId'],
        datatype: "json",
        colModel: [			
			{ label: 'dataNo', name: 'dataNo', index: 'data_no', width: 50, key: true, hidden:true },
			{ label: '课程名称', name: 'lessonTitle', index: 'lesson_title', width: 80 }, 			
			{ label: '课程分类', name: 'lessonCategoryName', index: 'lessonCategoryName', width: 40 },
			{ label: '课程类型', name: 'bteLessonTypeEntity.typeName', index: 'lessonTypeName', width: 40 },
			{ label: '教官姓名', name: 'lessonTeacherName', index: 'lesson_teacher_name', width: 40 }, 			
			{ label: '教官身份证号', name: 'lessonPid', index: 'lesson_pid', width: 40 }	
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
        },
        loadComplete: function(data) {
        	setDictList(data.userdata);
        	vm.evalId = data.evalId;
        }
    });

});

function setDictList(r) {
	vm.gglx = r.gglx;
	vm.zylx = r.zylx;
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		bteLesson: {},
		evalId:null,
		dictKclx:{},
		gglx:{},
		zylx:{}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.bteLesson = {lessonCategoryId:''}; // 默认选中“请选择”
            vm.dictKclx = {}; // 课程类型下拉菜单数据清空
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
			var url = vm.bteLesson.dataNo == null ? "eva/btelesson/save" : "eva/btelesson/update";
			vm.bteLesson.evalId = vm.evalId; // 测评信息id
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.bteLesson),
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
				    url: baseURL + "eva/btelesson/delete",
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
			$.get(baseURL + "eva/btelesson/info/"+dataNo, function(r){
	            if(r.bteLesson.lessonCategoryId == "1") {
	            	vm.dictKclx = vm.gglx;
	            } else {
	            	vm.dictKclx = vm.zylx;
	            }
                vm.bteLesson = r.bteLesson;
			});
		},
		back: function (event) {
			history.go(-1);
		},
		// 分类下拉菜单变化时
		selectOnChange:function() {
	    	 var categoryId = this.bteLesson.lessonCategoryId;
	    	 // 根据分类变化，后台查询相应分类的课程类型
	    	 if(!isBlank(categoryId)) {
	 			if(categoryId == "1") {
	 				vm.dictKclx = vm.gglx;
	 			} else {
	 				vm.dictKclx = vm.zylx;
	 			}
	    	 } else {
	    		 vm.dictKclx = '';
	    	 }
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