package io.renren.modules.eva.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.interceptor.FiledFill;
import io.renren.modules.sys.shiro.ShiroUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
public class BteQuestionEntity implements Serializable, FiledFill {
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
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createDate;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
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

	@Override
	public Map<String, Object> insertFill() {
		Map<String, Object> fillMap =  new HashMap<>(2);
		fillMap.put("createDate", LocalDateTime.now());
		fillMap.put("createUserId", ShiroUtils.getUserId());
		return fillMap;
	}
}
