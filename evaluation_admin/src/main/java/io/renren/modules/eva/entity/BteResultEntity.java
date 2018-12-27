package io.renren.modules.eva.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.interceptor.FiledFill;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-20 14:30:53
 */
@TableName("bte_result")
public class BteResultEntity implements Serializable, FiledFill {
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
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createDate;
	/**
	 * 
	 */
	private String evalSuggest;

	/**
	 * 客户端ip地址
	 */
	private String ipAddr;
	
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

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	@Override
	public Map<String, Object> insertFill() {
		Map<String, Object> fillMap =  new HashMap<>(2);
		fillMap.put("createDate", LocalDateTime.now());
		return fillMap;
	}
}
