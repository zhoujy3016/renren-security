package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
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
	private Date createDate;
	/**
	 * 
	 */
	private String evalSuggest;
	
	/**
	 * 题干名称
	 */
	@TableField(exist=false)
	private String questionTitle;
	
	/**
	 * 提交人数
	 */
	@TableField(exist=false)
	private Integer evaPersonNum;
	
	/**
	 * 1分人数
	 */
	@TableField(exist=false)
	private Integer socre1;
	
	/**
	 * 2分人数
	 */
	@TableField(exist=false)
	private Integer score2;
	
	/**
	 * 3分人数
	 */
	@TableField(exist=false)
	private Integer score3;
	
	/**
	 * 4分人数
	 */
	@TableField(exist=false)
	private Integer score4;
	
	/**
	 * 5分人数
	 */
	@TableField(exist=false)
	private Integer score5;
	
	/**
	 * 平均分
	 */
	@TableField(exist=false)
	private Integer avgScore;

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
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：
	 */
	public Date getCreateDate() {
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

	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public Integer getEvaPersonNum() {
		return evaPersonNum;
	}
	public void setEvaPersonNum(Integer evaPersonNum) {
		this.evaPersonNum = evaPersonNum;
	}
	public Integer getSocre1() {
		return socre1;
	}
	public void setSocre1(Integer socre1) {
		this.socre1 = socre1;
	}
	public Integer getScore2() {
		return score2;
	}
	public void setScore2(Integer score2) {
		this.score2 = score2;
	}
	public Integer getScore3() {
		return score3;
	}
	public void setScore3(Integer score3) {
		this.score3 = score3;
	}
	public Integer getScore4() {
		return score4;
	}
	public void setScore4(Integer score4) {
		this.score4 = score4;
	}
	public Integer getScore5() {
		return score5;
	}
	public void setScore5(Integer score5) {
		this.score5 = score5;
	}
	public Integer getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(Integer avgScore) {
		this.avgScore = avgScore;
	}
	
	
}
