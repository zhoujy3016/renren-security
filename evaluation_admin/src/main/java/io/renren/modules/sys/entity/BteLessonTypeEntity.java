package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-25 13:29:14
 */
@TableName("bte_lesson_type")
public class BteLessonTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer dataNo;
	/**
	 * 1:公共类 2：专业类
	 */
	private Integer categoryId;
	/**
	 * 
	 */
	private String typeName;
	
	@TableField(exist=false)
	private Integer code;
	
	@TableField(exist=false)
	private String value;

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
	 * 设置：1:公共类 2：专业类
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * 获取：1:公共类 2：专业类
	 */
	public Integer getCategoryId() {
		return categoryId;
	}
	/**
	 * 设置：
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * 获取：
	 */
	public String getTypeName() {
		return typeName;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
