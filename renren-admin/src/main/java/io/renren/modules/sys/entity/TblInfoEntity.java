package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.common.utils.J8DateUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-27 14:01:50
 */
@TableName("tbl_info")
public class TblInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer infoId;
	/**
	 * 
	 */
	@NotBlank(message="信息标题不能为空！")
	private String infoTitle;
	/**
	 * 
	 */
	@NotNull(message="请选择信息类型！")
	private Integer infoType;
	
	
	@TableField(exist=false)
	private String infoTypeName;
	
	/**
	 * 
	 */
	private String infoContent;
	
	/**
	 * 	创建时间
	 */
	@JsonFormat(pattern = J8DateUtils.DATE_TIME_PATTERN)
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime infoCreateTime;

	/**
	 * 设置：
	 */
	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}
	/**
	 * 获取：
	 */
	public Integer getInfoId() {
		return infoId;
	}
	/**
	 * 设置：
	 */
	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}
	/**
	 * 获取：
	 */
	public String getInfoTitle() {
		return infoTitle;
	}
	/**
	 * 设置：
	 */
	public void setInfoType(Integer infoType) {
		this.infoType = infoType;
	}
	/**
	 * 获取：
	 */
	public Integer getInfoType() {
		return infoType;
	}
	/**
	 * 设置：
	 */
	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}
	/**
	 * 获取：
	 */
	public String getInfoContent() {
		return infoContent;
	}
	public String getInfoTypeName() {
		return infoTypeName;
	}
	public void setInfoTypeName(String infoTypeName) {
		this.infoTypeName = infoTypeName;
	}
	public LocalDateTime getInfoCreateTime() {
		return infoCreateTime;
	}
	public void setInfoCreateTime(LocalDateTime infoCreateTime) {
		this.infoCreateTime = infoCreateTime;
	}
	
	
}
