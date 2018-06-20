package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-20 09:55:48
 */
@TableName("bte_lesson")
public class BteLessonEntity implements Serializable {
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
	private String lessonTitle;
	/**
	 * 
	 */
	private Integer lessonTypeId;
	/**
	 * 
	 */
	private String lessonTeacherName;
	/**
	 * 
	 */
	private String lessonPid;
	/**
	 * 
	 */
	private Date createDate;
	/**
	 * 
	 */
	private Integer createUserId;

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
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：
	 */
	public Integer getCreateUserId() {
		return createUserId;
	}
}
