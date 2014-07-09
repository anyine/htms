package cn.wizool.htms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 外事管理
 * 
 * @author Administrator
 * 
 */
@Entity
public class Foreign {
	@Id
	private String id;

	private int serialNum;// 序号————每添加一条，按倒序排序
	private String fileNum;// 文号
	private String unitame;// 单位名称/组团单位
	private String task;// 出国任务
	private String peopleNum;// 人数
	private String name; // 名字
	private String peopleCond;// 团员情况
	private String unit;// 单位
	private String post;// 职务
	private Date outDate;// 出访时间
	private String remain;// 在外停留
	private String address;// 出访地
	private Date date; // 审核日期
	private String linkMan; // 联系人
	private String phone; // 联系电话
	private String remark;// 备注
	private String type;// 外事类型

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}

	public String getFileNum() {
		return fileNum;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}

	public String getUnitame() {
		return unitame;
	}

	public void setUnitame(String unitame) {
		this.unitame = unitame;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(String peopleNum) {
		this.peopleNum = peopleNum;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getRemain() {
		return remain;
	}

	public void setRemain(String remain) {
		this.remain = remain;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
