package cn.wizool.htms.model;

//邀请外国人来访  对象

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Visit {
	@Id
	private String id;

	private Integer num; // 序号
	private String inviteName; // 拟邀请单位名称
	private Integer count; // 人数
	private String passportNum; // 护照号码
	private String comeDate; // 来访时间
	private String remain; // 停留
	private String reason; // 来华事由
	private String applyName; // 申请单位名称
	private String linkMan; // 联系人
	private String phone; // 联系电话
	private Date date; // 审核日期

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInviteName() {
		return inviteName;
	}

	public void setInviteName(String inviteName) {
		this.inviteName = inviteName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getPassportNum() {
		return passportNum;
	}

	public void setPassportNum(String passportNum) {
		this.passportNum = passportNum;
	}

	public String getComeDate() {
		return comeDate;
	}

	public void setComeDate(String comeDate) {
		this.comeDate = comeDate;
	}

	public String getRemain() {
		return remain;
	}

	public void setRemain(String remain) {
		this.remain = remain;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getNum() {
		return num;
	}

}
