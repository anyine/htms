package cn.wizool.htms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 活动管理
 * 
 * @author Administrator
 * 
 */
@Entity
public class Activity {
	@Id
	private String id;

	private int serialNum;// 序号————每添加一条，按倒序排序
	private String fileNum;// 文号
	private Date date;// 日期
	private String name;// 活动名称/团组名称
	private String peopleCond;// 人员情况
	private String provinceLeader;// 国家/省/市 领导
	private String jhdzLeader;// 高新区领导
	private String provinceDep;// 国家/省/市 部门
	private String jhdzDep;// 高新区 部门
	private String content;// 活动内容
	private String locale;// 地点
	private String peopleNum;// 人数
	private String path;// 活动路线
	private String meal;// 用餐情况
	private String remark;// 备注
	private String type;// 活动类型

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileNum() {
		return fileNum;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPeopleCond() {
		return peopleCond;
	}

	public void setPeopleCond(String peopleCond) {
		this.peopleCond = peopleCond;
	}

	public String getProvinceLeader() {
		return provinceLeader;
	}

	public void setProvinceLeader(String provinceLeader) {
		this.provinceLeader = provinceLeader;
	}

	public String getJhdzLeader() {
		return jhdzLeader;
	}

	public void setJhdzLeader(String jhdzLeader) {
		this.jhdzLeader = jhdzLeader;
	}

	public String getProvinceDep() {
		return provinceDep;
	}

	public void setProvinceDep(String provinceDep) {
		this.provinceDep = provinceDep;
	}

	public String getJhdzDep() {
		return jhdzDep;
	}

	public void setJhdzDep(String jhdzDep) {
		this.jhdzDep = jhdzDep;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(String peopleNum) {
		this.peopleNum = peopleNum;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
