package cn.wizool.htms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

//统计类       存放 类型，对象，年份，月份，条数，参加总人数

@Entity
public class Statistics {

	@Id
	private String id;
	private String type;
	private String object;
	private String year;
	private String month;
	private Integer count;
	private Integer personNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public Integer getPersonNum() {
		return personNum;
	}

	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}
}
