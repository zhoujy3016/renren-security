package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-20 14:30:53
 */
@TableName("bte_result")
public class BteResultExtendsEntity extends BteResultEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
	private Integer score1;
	
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
	public Integer getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(Integer avgScore) {
		this.avgScore = avgScore;
	}
	
	
}
