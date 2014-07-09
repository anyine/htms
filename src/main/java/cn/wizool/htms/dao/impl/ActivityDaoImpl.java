package cn.wizool.htms.dao.impl;

import cn.wizool.htms.dao.ActivityDao;
import cn.wizool.htms.iwebutil.newlay.CommonDataAccessSupport;
import cn.wizool.htms.iwebutil.newlay.query.ParameterString;
import cn.wizool.htms.model.Activity;

public class ActivityDaoImpl extends CommonDataAccessSupport<Activity>
		implements ActivityDao {

	@Override
	protected String getModelName() {
		return Activity.class.getName();
	}

	@Override
	protected void createHqlTemplates(ParameterString tmp) {
		tmp.setTemplate("getCountByType",
				"from <model> a where a.type = :sType");

		tmp.setTemplate(
				"getAllOrderByNum",
				"from <model> a where a.type = :sType "
						+ " and ( a.provinceLeader like :sProvinceLeader or :esProvinceLeader=false ) "
						+ " and ( a.jhdzLeader like :sJhdzLeader or :esJhdzLeader = false ) "
						+ " and ( a.name like :sName or :esName = false ) "
						+ " and ( a.content like :sContent or :esContent = false ) "
						+ " and ( a.path like :sPath or :esPath = false ) ");
		super.createHqlTemplates(tmp);
	}

}
