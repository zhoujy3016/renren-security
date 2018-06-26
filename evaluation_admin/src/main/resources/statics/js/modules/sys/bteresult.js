$(function () {
	// 通过url获得参数
	var arrParam = getParameters(location.search);
	
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/bteresult/list/' + arrParam['evalId'],
        datatype: "json",
        colModel: [			
			{ label: '题干名称', name: 'questionTitle', index: 'questionTitle', width: 80 }, 			
			{ label: '提交人数', name: 'evaPersonNum', index: 'evaPersonNum', width: 20 }, 			
			{ label: '1分人数', name: 'score1', index: 'score1', width: 20 }, 			
			{ label: '2分人数', name: 'score2', index: 'score2', width: 20 }, 			
			{ label: '3分人数', name: 'score3', index: 'score3', width: 20 }, 			
			{ label: '4分人数', name: 'score4', index: 'score4', width: 20 },
			{ label: '5分人数', name: 'score5', index: 'score5', width: 20 },
			{ label: '平均分', name: 'avgScore', index: 'avgScore', width: 20 }
        ],
		viewrecords: true,
        height: 385,
//        rowNum: 10,
//		rowList : [10,30,50],
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
		}, 
		// 测评结果导出
		exportResult:function() {
			window.location.href = baseURL + "sys/bteresult/exportResult/" + vm.evalId;
		},
		// 具体建议导出
		exportSuggest:function() {
			
		}
	}
});