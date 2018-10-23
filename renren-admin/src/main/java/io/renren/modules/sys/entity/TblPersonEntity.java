package io.renren.modules.sys.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;




/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-04-26 20:42:54
 */
@TableName("tbl_person")
public class TblPersonEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty(name="主键id")
	private Integer personId;
	/**
	 * 
	 */
	@NotBlank(message="{person.name.isEmpty}")
	@ApiModelProperty(name="姓名")
	@Excel(name = "姓名", orderNum = "0")
	private String personName;
	/**
	 * 
	 */
	@NotNull(message="{person.nationality.isNull}")
	@ApiModelProperty(name="民族")
	@Excel(name = "民族", orderNum = "1")
	private Integer nationality;
	/**
	 * 
	 */
	@NotNull(message="{person.area.isNull}")
	@ApiModelProperty(name="地区")
	@Excel(name = "地区", orderNum = "2")
	private Integer area;

	/**
	 *
	 */
	@ApiModelProperty(name="电话")
	@Excel(name = "电话", orderNum = "3")
	private String telephone;
	/**
	 * 
	 */
	@ApiModelProperty(name="备注")
	@Excel(name = "备注", orderNum = "4")
	private String remark;

	/**
	 * 民族名称
	 */
	@TableField(exist=false)
	private String nationalityName;
	
	@TableField(exist=false)
	private String areaName;

	/**
	 * 设置：
	 */
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	/**
	 * 获取：
	 */
	public Integer getPersonId() {
		return personId;
	}
	/**
	 * 设置：
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	/**
	 * 获取：
	 */
	public String getPersonName() {
		return personName;
	}
	/**
	 * 设置：
	 */
	public void setNationality(Integer nationality) {
		this.nationality = nationality;
	}
	/**
	 * 获取：
	 */
	public Integer getNationality() {
		return nationality;
	}
	/**
	 * 设置：
	 */
	public void setArea(Integer area) {
		this.area = area;
	}
	/**
	 * 获取：
	 */
	public Integer getArea() {
		return area;
	}
	/**
	 * 设置：
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：
	 */
	public String getRemark() {
		return remark;
	}
	
	public String getNationalityName() {
		return nationalityName;
	}
	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
