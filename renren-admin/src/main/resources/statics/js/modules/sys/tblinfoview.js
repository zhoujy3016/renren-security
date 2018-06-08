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
                $("#content").html(unescape(vm.tblInfo.infoContent));
            });
		}
	},
	created:function() {
		var url = location.search; //获取url中"?"符后的字串
		var theRequest = new Object();
		if (url.indexOf("?") != -1) {
			var str = url.substr(1);//substr()方法返回从参数值开始到结束的字符串；
			strs = str.split("&");
			for(var i = 0; i < strs.length; i ++) {
				theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
			}
			console.log(theRequest);//此时的theRequest就是我们需要的参数；
		}
		this.getInfo(theRequest['infoId']);
	}
});