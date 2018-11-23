package io.renren.modules.oa.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.common.utils.J8DateUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-11-19 10:18:19
 */
@TableName("oa_vacation")
public class OaVacationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer vaId;
	/**
	 * 休假天数
	 */
    @NotNull(message="请假天数不能为空！")
	private Integer vaDays;
	/**
	 * 开始日期
	 */
    @NotNull(message="开始日期不能为空！")
	@JsonFormat(pattern = J8DateUtils.DATE_PATTERN)
	private LocalDate vaBegin;
	/**
	 * 结束日期
	 */
    @NotNull(message="结束日期不能为空！")
	@JsonFormat(pattern = J8DateUtils.DATE_PATTERN)
	private LocalDate vaEnd;
	/**
	 * 请假类型
	 */
    @NotNull(message="请选择请假类型！")
	private Integer vaType;
	/**
	 * 请假原因
	 */
    @NotBlank(message="请假原因不能为空！")
	private String vaReason;

	/**
	 * 申请人id
	 */
	private Long userId;

	/**
	 * 流程id
	 */
	private String processId;

	/**
	 * 设置：
	 */
	public void setVaId(Integer vaId) {
		this.vaId = vaId;
	}
	/**
	 * 获取：
	 */
	public Integer getVaId() {
		return vaId;
	}
	/**
	 * 设置：休假日期
	 */
	public void setVaDays(Integer vaDays) {
		this.vaDays = vaDays;
	}
	/**
	 * 获取：休假日期
	 */
	public Integer getVaDays() {
		return vaDays;
	}
	/**
	 * 设置：开始日期
	 */
	public void setVaBegin(LocalDate vaBegin) {
		this.vaBegin = vaBegin;
	}
	/**
	 * 获取：开始日期
	 */
	public LocalDate getVaBegin() {
		return vaBegin;
	}
	/**
	 * 设置：结束日期
	 */
	public void setVaEnd(LocalDate vaEnd) {
		this.vaEnd = vaEnd;
	}
	/**
	 * 获取：结束日期
	 */
	public LocalDate getVaEnd() {
		return vaEnd;
	}
	/**
	 * 设置：请假类型
	 */
	public void setVaType(Integer vaType) {
		this.vaType = vaType;
	}
	/**
	 * 获取：请假类型
	 */
	public Integer getVaType() {
		return vaType;
	}
	/**
	 * 设置：请假原因
	 */
	public void setVaReason(String vaReason) {
		this.vaReason = vaReason;
	}
	/**
	 * 获取：请假原因
	 */
	public String getVaReason() {
		return vaReason;
	}

	/**
	 * 设置：申请人id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：申请人id
	 */
	public Long getUserId() {
		return userId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}
}
