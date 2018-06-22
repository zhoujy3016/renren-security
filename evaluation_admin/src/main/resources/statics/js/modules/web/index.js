$(function () {

});

var vm = new Vue({
	el:'#wallpaper',
	data:{
		error: false,
		errorMsg: '',
		evalId:null,
		lesson:{},
		question:{},
		result:{}
	},
	beforeCreate: function(){

	},
	methods: {
		getEvalPaper:function(evalId) {
			$.ajax({
				type: "POST",
			    url: baseURL + "home/evalPaper/" + evalId,
			    dataType: "json",
			    success: function(result){
					if(result.code == 0){
						vm.setEvalData(result);
					}else{
						vm.error = true;
						vm.errorMsg = result.msg;
					}
				}
			});
		},
		saveEval:function() {
			// 数据校验
			this.checkData();
			
			
			var index = 0;
			// 课程类型
			for(var i = 0; i < vm.lesson.length; i++, index++) {
				var radioValue = $('input[name="question_1_'+ i +'"]:checked').val();
				if(isBlank(radioValue)) {
					alert("有未完成的试题！");
					return;
				}
				vm.result[index] = {"questionTypeId" : 1, "evalId" : vm.evalId, "questionId" : vm.lesson[i].dataNo, "questionScore": radioValue};
			}
			// 常规试题
			for(var i = 0; i < vm.question.length; i++, index++) {
				var typeId = vm.question[i].questionTypeId;
				if(typeId != 5) {
					var radioValue = $('input[name="question_'+ typeId +'_'+ i +'"]:checked').val()
					if(isBlank(radioValue)) {
						alert("有未完成的试题！");
						return;
					}
					vm.result[index] = {"questionTypeId" : typeId, "evalId" : vm.evalId, "questionId" : vm.question[i].dataNo, "questionScore": radioValue};	
				} else { // 其他建议
					var suggest = $("#question_"+ typeId + "_" +  i).val();
					if(isBlank(radioValue)) {
						suggest = "";
					}
					vm.result[index] = {"questionTypeId" : typeId, "evalId" : vm.evalId, "questionId" : vm.question[i].dataNo, "evalSuggest": suggest};
				}
			}
			
			$.ajax({
				type: "POST",
			    url: baseURL + "home/saveEval",
			    contentType: "application/json",
			    data: JSON.stringify(vm.result),
			    success: function(result){
					if(result.code == 0){
						alert("测评提交成功,请勿重复提交。");
						location.reload(); 
					}else{
						vm.error = true;
						vm.errorMsg = result.msg;
					}
				}
			});
			
		},
		setEvalData:function(result) {
			vm.lesson = result.lesson;
			vm.question = result.question;
			console.log(result);
			// 测评编号
			vm.evalId = result.evalId;
			var row = 1;
			// 设置课程评价试题
			var strLesson = '';
			for(var i = 0; i < vm.lesson.length; i++) {
				var lesson = vm.lesson[i];
				strLesson += '<div class="question" id="q1">';
				strLesson += '<div class="question-title">'+ (row++) + ' . ' + lesson.lessonTitle + '（' + lesson.lessonTeacherName +'）</div>';
				strLesson += '<ul  class="question-option list-inline">';
				strLesson += '<li><label><input type="radio" name="question_1_'+ i +'" value="5" id="RadioGroup1_0"> 5</label></li>';
				strLesson += '<li><label><input type="radio" name="question_1_'+ i +'" value="4" id="RadioGroup1_0"> 4</label></li>';
				strLesson += '<li><label><input type="radio" name="question_1_'+ i +'" value="3" id="RadioGroup1_0"> 3</label></li>';
				strLesson += '<li><label><input type="radio" name="question_1_'+ i +'" value="2" id="RadioGroup1_0"> 2</label></li>';
				strLesson += '<li><label><input type="radio" name="question_1_'+ i +'" value="1" id="RadioGroup1_0"> 1</label></li>';
				strLesson += '</ul></div>';
			}
			$("#type_1").html(strLesson);
			
			for(var i = 0; i < vm.question.length; i++) {
				var question = vm.question[i];
				var typeId = question.questionTypeId;
				var strQuestion = '';
				strQuestion += '<div class="question" id="q1">';
				strQuestion += '<div class="question-title">'+ (row++) + ' . ' + question.questionTitle +'</div>';
				strQuestion += '<ul  class="question-option list-inline">';
				// 非其他建议的情况下
				if(typeId != 5) {
					strQuestion += '<li><label><input type="radio" name="question_'+ typeId +'_'+ i +'" value="5" id="RadioGroup1_0"> 5</label></li>';
					strQuestion += '<li><label><input type="radio" name="question_'+ typeId +'_'+ i +'" value="4" id="RadioGroup1_0"> 4</label></li>';
					strQuestion += '<li><label><input type="radio" name="question_'+ typeId+ '_'+ i +'" value="3" id="RadioGroup1_0"> 3</label></li>';
					strQuestion += '<li><label><input type="radio" name="question_'+ typeId + '_'+ i +'" value="2" id="RadioGroup1_0"> 2</label></li>';
					strQuestion += '<li><label><input type="radio" name="question_'+ typeId +'_'+ i +'" value="1" id="RadioGroup1_0"> 1</label></li>';
				}  else { // 其他建议用textarea
					strQuestion += '<textarea class="form-control" rows="5" cols="100" id="question_'+ typeId +'_'+ i +'" />';
				}
				strQuestion += '</ul></div>';
				$("#type_" + typeId).append(strQuestion);
			}
		},
		checkData:function() {
//			var kvName = {}, allSelected = true;
//			 $(':radio').each(function () {
//				 if (kvName[this.name]) return true;
//				 if ($('[name="' + this.name + '"]:checked').length == 0) {
//					 $("#"+ this.name +"").addClass("err")
//					 this.focus(); 
//				 }else{
//				$("#"+ this.name +"").removeClass("err") 
//			}
//				kvName[this.name] = true//标志此组已经检查过，剩余的不需要遍历了，上面的第一句直接继续检查下一组
//			});
		}
	},

	created:function() {
		var arrParam = getParameters(location.search);
		this.getEvalPaper(arrParam['evalId']);
	}
});