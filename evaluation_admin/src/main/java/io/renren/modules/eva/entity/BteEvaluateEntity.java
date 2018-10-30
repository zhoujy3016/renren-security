package io.renren.modules.eva.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.common.utils.J8DateUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-19 21:00:48
 */
@TableName("bte_evaluate")
public class BteEvaluateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer dataNo;
	/**
	 * 
	 */
	@NotBlank(message="测评名称不能为空！")
	private String evalTitle;
	/**
	 * 
	 */
	private String evalMemo;
	/**
	 * 
	 */
	private Integer evalStateId;
	/**
	 * 
	 */
	@JsonFormat(pattern = J8DateUtils.DATE_TIME_PATTERN)
	private LocalDateTime createDate;
	/**
	 * 
	 */
	private Long createUserId;
	
	/**
	 * 状态名称
	 */
	@TableField(exist=false)
	private String evalStateName;

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
	public void setEvalTitle(String evalTitle) {
		this.evalTitle = evalTitle;
	}
	/**
	 * 获取：
	 */
	public String getEvalTitle() {
		return evalTitle;
	}
	/**
	 * 设置：
	 */
	public void setEvalMemo(String evalMemo) {
		this.evalMemo = evalMemo;
	}
	/**
	 * 获取：
	 */
	public String getEvalMemo() {
		return evalMemo;
	}
	/**
	 * 设置：
	 */
	public void setEvalStateId(Integer evalStateId) {
		this.evalStateId = evalStateId;
	}
	/**
	 * 获取：
	 */
	public Integer getEvalStateId() {
		return evalStateId;
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
	public String getEvalStateName() {
		return evalStateName;
	}
	public void setEvalStateName(String evalStateName) {
		this.evalStateName = evalStateName;
	}
	
	
}
