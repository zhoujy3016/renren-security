$(function () {
	
});


var vm = new Vue({
	el:'#rrapp',
	data:{
		tblInfo: {},
		infoContent:null
	},
	methods: {
		getInfo: function(infoId){
			$.get(baseURL + "sys/tblinfo/info/"+infoId, function(r){
                vm.tblInfo = r.tblInfo;
                $("#title").append(vm.tblInfo.infoTitle);
                $("#content").html(unescape(vm.tblInfo.infoContent));
            });
		}
	},
	created:function() {
		var arrParam = getParameters(location.search);
		this.getInfo(arrParam['infoId']);
	}
});