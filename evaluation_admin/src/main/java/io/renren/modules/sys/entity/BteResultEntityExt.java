package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-20 14:30:53
 */
@TableName("bte_result")
public class BteResultEntityExt extends BteResultEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 题干名称
	 */
	@TableField(exist=false)
	@Excel(name = "题干名称", orderNum = "0")
	private String questionTitle;

	/**
	 * 课程分类
	 */
	@TableField(exist=false)
	@Excel(name = "课程分类", orderNum = "1")
	private String lessonCategoryName;

	/**
	 * 课程类型
	 */
	@TableField(exist=false)
	@Excel(name = "课程类型", orderNum = "2")
	private String lessonTypeName;


	/**
	 * 教官姓名
	 */
	@TableField(exist=false)
	@Excel(name = "教官姓名", orderNum = "3")
	private String lessonTeacherName;

	/**
	 * 教官身份证
	 */
	@TableField(exist=false)
	@Excel(name = "教官身份证号", orderNum = "4")
	private String lessonPid;
	
	/**
	 * 提交人数
	 */
	@TableField(exist=false)
	@Excel(name = "提交人数", orderNum = "5")
	private Integer evaPersonNum;
	
	/**
	 * 1分人数
	 */
	@TableField(exist=false)
	@Excel(name = "1分人数", orderNum = "6")
	private Integer score1;
	
	/**
	 * 2分人数
	 */
	@TableField(exist=false)
	@Excel(name = "2分人数", orderNum = "7")
	private Integer score2;
	
	/**
	 * 3分人数
	 */
	@TableField(exist=false)
	@Excel(name = "3分人数", orderNum = "8")
	private Integer score3;
	
	/**
	 * 4分人数
	 */
	@TableField(exist=false)
	@Excel(name = "4分人数", orderNum = "9")
	private Integer score4;
	
	/**
	 * 5分人数
	 */
	@TableField(exist=false)
	@Excel(name = "5分人数", orderNum = "10")
	private Integer score5;
	
	/**
	 * 平均分
	 */
	@TableField(exist=false)
	@Excel(name = "平均分", orderNum = "11")
	private Double avgScore;


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

	public Integer getScore1() {
		return score1;
	}
	public void setScore1(Integer score1) {
		this.score1 = score1;
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
	public Double getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}

	public String getLessonCategoryName() {
		return lessonCategoryName;
	}

	public void setLessonCategoryName(String lessonCategoryName) {
		this.lessonCategoryName = lessonCategoryName;
	}

	public String getLessonTypeName() {
		return lessonTypeName;
	}

	public void setLessonTypeName(String lessonTypeName) {
		this.lessonTypeName = lessonTypeName;
	}

	public String getLessonTeacherName() {
		return lessonTeacherName;
	}

	public void setLessonTeacherName(String lessonTeacherName) {
		this.lessonTeacherName = lessonTeacherName;
	}

	public String getLessonPid() {
		return lessonPid;
	}

	public void setLessonPid(String lessonPid) {
		this.lessonPid = lessonPid;
	}
}
