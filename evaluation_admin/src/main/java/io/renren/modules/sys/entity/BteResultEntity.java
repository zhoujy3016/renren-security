package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-20 14:30:53
 */
@TableName("bte_result")
public class BteResultEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public BteResultEntity() {
		
	}
	/**
	 * 
	 */
	@TableId
	private Integer dataNo;
	/**
	 * 
	 */
	private Integer evalId;
	/**
	 * 
	 */
	private Integer questionTypeId;
	/**
	 * 
	 */
	private Integer questionId;
	/**
	 * 
	 */
	private Integer questionScore;
	/**
	 * 
	 */
	private LocalDateTime createDate;
	/**
	 * 
	 */
	private String evalSuggest;
	
	/**
	 * 设置：
	 */
	public void setDataNo(Integer dataNo) {
		this.dataNo = dataNo;
	}
	/**
	 * 获取：
	 */
	public Integer getDataNo() {
		return dataNo;
	}
	/**
	 * 设置：
	 */
	public void setEvalId(Integer evalId) {
		this.evalId = evalId;
	}
	/**
	 * 获取：
	 */
	public Integer getEvalId() {
		return evalId;
	}
	/**
	 * 设置：
	 */
	public void setQuestionTypeId(Integer questionTypeId) {
		this.questionTypeId = questionTypeId;
	}
	/**
	 * 获取：
	 */
	public Integer getQuestionTypeId() {
		return questionTypeId;
	}
	/**
	 * 设置：
	 */
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	/**
	 * 获取：
	 */
	public Integer getQuestionId() {
		return questionId;
	}
	/**
	 * 设置：
	 */
	public void setQuestionScore(Integer questionScore) {
		this.questionScore = questionScore;
	}
	/**
	 * 获取：
	 */
	public Integer getQuestionScore() {
		return questionScore;
	}
	/**
	 * 设置：
	 */
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：
	 */
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：
	 */
	public void setEvalSuggest(String evalSuggest) {
		this.evalSuggest = evalSuggest;
	}
	/**
	 * 获取：
	 */
	public String getEvalSuggest() {
		return evalSuggest;
	}

}
