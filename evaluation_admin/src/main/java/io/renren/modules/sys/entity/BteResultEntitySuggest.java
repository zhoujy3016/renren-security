package io.renren.modules.sys.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * 建议导出的结果entity
 * @author zhoujy
 *
 */
public class BteResultEntitySuggest {
	
	public BteResultEntitySuggest() {
		// TODO Auto-generated constructor stub
	}
	
	@Excel(name = "题干名称", orderNum = "0")
	private String questionTitle;
	
	@Excel(name = "建议内容", orderNum = "1")
	private String evalSuggest;
	
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getEvalSuggest() {
		return evalSuggest;
	}
	public void setEvalSuggest(String evalSuggest) {
		this.evalSuggest = evalSuggest;
	}
	
	
}
