package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-19 15:11:26
 */
@TableName("bte_question")
public class BteQuestionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer dataNo;
	/**
	 * 
	 */
	@NotBlank(message="题干名称不能为空！")
	private String questionTitle;
	/**
	 * 
	 */
	@NotNull(message="请选择试题所属分类！")
	private Integer questionTypeId;
	/**
	 * 
	 */
	private Integer questionStateId;
	/**
	 * 
	 */
	private Date createDate;
	/**
	 * 
	 */
	private Long createUserId;
	
	/**
	 * 所属分类
	 */
	@TableField(exist=false)
	private String questionTypeName;
	
	/**
	 * 状态
	 */
	@TableField(exist=false)
	private String questionStateName;

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
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	/**
	 * 获取：
	 */
	public String getQuestionTitle() {
		return questionTitle;
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
	public void setQuestionStateId(Integer questionStateId) {
		this.questionStateId = questionStateId;
	}
	/**
	 * 获取：
	 */
	public Integer getQuestionStateId() {
		return questionStateId;
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
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：
	 */
	public Long getCreateUserId() {
		return createUserId;
	}
	public String getQuestionTypeName() {
		return questionTypeName;
	}
	public void setQuestionTypeName(String questionTypeName) {
		this.questionTypeName = questionTypeName;
	}
	public String getQuestionStateName() {
		return questionStateName;
	}
	public void setQuestionStateName(String questionStateName) {
		this.questionStateName = questionStateName;
	}
	
	
}
