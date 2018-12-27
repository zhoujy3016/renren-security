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
 * @date 2018-06-20 09:55:48
 */
@TableName("bte_lesson")
public class BteLessonEntity implements Serializable, FiledFill {
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
	@NotBlank(message="课程名称不能为空！")
	private String lessonTitle;
	
	/**
	 * 课程分类
	 */
	@NotNull(message="请选择课程分类！")
	private Integer lessonCategoryId;
	
	@TableField(exist=false)
	private String lessonCategoryName;
	
	/**
	 * 
	 */
	@NotNull(message="请选择课程类型！")
	private Integer lessonTypeId;
	/**
	 * 
	 */
	@NotBlank(message="教官姓名不能为空！")
	private String lessonTeacherName;
	/**
	 * 
	 */
	@NotBlank(message="教官身份证不能为空！")
	private String lessonPid;
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
	 * 课程类型嵌套类
	 */
	@TableField(exist=false)
	private BteLessonTypeEntity bteLessonTypeEntity;

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
	public void setLessonTitle(String lessonTitle) {
		this.lessonTitle = lessonTitle;
	}
	/**
	 * 获取：
	 */
	public String getLessonTitle() {
		return lessonTitle;
	}
	
	public Integer getLessonCategoryId() {
		return lessonCategoryId;
	}
	public void setLessonCategoryId(Integer lessonCategoryId) {
		this.lessonCategoryId = lessonCategoryId;
	}
	/**
	 * 设置：
	 */
	public void setLessonTypeId(Integer lessonTypeId) {
		this.lessonTypeId = lessonTypeId;
	}
	/**
	 * 获取：
	 */
	public Integer getLessonTypeId() {
		return lessonTypeId;
	}
	/**
	 * 设置：
	 */
	public void setLessonTeacherName(String lessonTeacherName) {
		this.lessonTeacherName = lessonTeacherName;
	}
	/**
	 * 获取：
	 */
	public String getLessonTeacherName() {
		return lessonTeacherName;
	}
	/**
	 * 设置：
	 */
	public void setLessonPid(String lessonPid) {
		this.lessonPid = lessonPid;
	}
	/**
	 * 获取：
	 */
	public String getLessonPid() {
		return lessonPid;
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

	public String getLessonCategoryName() {
		return lessonCategoryName;
	}
	
	public void setLessonCategoryName(String lessonCategoryName) {
		this.lessonCategoryName = lessonCategoryName;
	}

	public void setBteLessonTypeEntity(BteLessonTypeEntity bteLessonTypeEntity) {
		this.bteLessonTypeEntity = bteLessonTypeEntity;
	}

	public BteLessonTypeEntity getBteLessonTypeEntity() {
		return bteLessonTypeEntity;
	}

	@Override
	public Map<String, Object> insertFill() {
		Map<String, Object> fillMap =  new HashMap<>(2);
		fillMap.put("createDate", LocalDateTime.now());
		fillMap.put("createUserId", ShiroUtils.getUserId());
		return fillMap;
	}
}
