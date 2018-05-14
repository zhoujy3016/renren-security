package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

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
	private Integer personId;
	/**
	 * 
	 */
	@NotBlank(message="姓名不能为空")
	private String personName;
	/**
	 * 
	 */
	@NotNull(message="民族不能为空")
	private Integer nationality;
	/**
	 * 
	 */
	@NotNull(message="地区不能为空")
	private Integer area;
	/**
	 * 
	 */
	private String remark;
	
	private String telephone;
	
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
