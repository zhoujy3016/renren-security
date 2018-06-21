$(function () {
    $("#submint-btn").click(function(){
		 var kvName = {}, allSelected = true;
			 $(':checkbox,:radio').each(function () {
			 if (kvName[this.name]) return true;
		 if ($('[name="' + this.name + '"]:checked').length == 0) {
			 $("#"+ this.name +"").addClass("err")
			 this.focus(); 
			
			 }else{
			$("#"+ this.name +"").removeClass("err") 
		}
			kvName[this.name] = true//标志此组已经检查过，剩余的不需要遍历了，上面的第一句直接继续检查下一组
		});
	});
});

var vm = new Vue({
	el:'#wallpaper',
	data:{
		error: false,
		errorMsg: '',
		lesson:{},
		question:{}
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
			
		},
		setEvalData:function(result) {
			vm.lesson = result.lesson;
			vm.question = result.question;
			// 设置课程评价试题
			var strLesson = '';
			for(var i = 0; i < vm.lesson.length; i++) {
				var lesson = vm.lesson[i];
				strLesson += '<div class="question" id="q1">';
				strLesson += '<div class="question-title">'+ lesson.lessonTitle + '（' + lesson.lessonTeacherName +'）</div>';
				strLesson += '<ul  class="question-option list-inline">';
				strLesson += '<li><label><input type="radio" name="question_1_'+ i +'" value="单选" id="RadioGroup1_0">5</label></li>';
				strLesson += '<li><label><input type="radio" name="question_1_'+ i +'" value="单选" id="RadioGroup1_0">4</label></li>';
				strLesson += '<li><label><input type="radio" name="question_1_'+ i +'" value="单选" id="RadioGroup1_0">3</label></li>';
				strLesson += '<li><label><input type="radio" name="question_1_'+ i +'" value="单选" id="RadioGroup1_0">2</label></li>';
				strLesson += '<li><label><input type="radio" name="question_1_'+ i +'" value="单选" id="RadioGroup1_0">1</label></li>';
				strLesson += '</ul></div>';
			}
			$("#type_1").html(strLesson);
			
			for(var i = 0; i < vm.question.length; i++) {
				var question = vm.question[i];
				var typeId = question.questionTypeId;
				var strQuestion = '';
				strQuestion += '<div class="question" id="q1">';
				strQuestion += '<div class="question-title">'+ question.questionTitle +'</div>';
				strQuestion += '<ul  class="question-option list-inline">';
				strQuestion += '<li><label><input type="radio" name="question_'+ typeId +'_'+ i +'" value="单选" id="RadioGroup1_0">5</label></li>';
				strQuestion += '<li><label><input type="radio" name="question_'+ typeId +'_'+ i +'" value="单选" id="RadioGroup1_0">4</label></li>';
				strQuestion += '<li><label><input type="radio" name="question_'+ typeId+ '_'+ i +'" value="单选" id="RadioGroup1_0">3</label></li>';
				strQuestion += '<li><label><input type="radio" name="question_'+ typeId + '_'+ i +'" value="单选" id="RadioGroup1_0">2</label></li>';
				strQuestion += '<li><label><input type="radio" name="question_'+ typeId +'_'+ i +'" value="单选" id="RadioGroup1_0">1</label></li>';
				strQuestion += '</ul></div>';
				$("#type_" + typeId).append(strQuestion);
			}
		}
	},
	created:function() {
		var arrParam = getParameters(location.search);
		this.getEvalPaper(arrParam['evalId']);
	}
});