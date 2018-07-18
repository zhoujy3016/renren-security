var baseURL = "../";

//工具集合Tools
window.T = {};

// 获取请求参数
// 使用示例
// location.href = http://localhost:8080/index.html?id=123
// T.p('id') --> 123;
var url = function(name) {
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
};
T.p = url;

//全局配置
$.ajaxSetup({
	dataType: "json",
	cache: false
});

//判断是否为空
function isBlank(value) {
    return !value || !/\S/.test(value)
}

// 取得url?后边的参数
function getParameters(url) {
	var arrParam = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);//substr()方法返回从参数值开始到结束的字符串；
		strs = str.split("&");
		for(var i = 0; i < strs.length; i ++) {
			arrParam[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
		}
	}
	return arrParam;
}
