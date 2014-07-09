package cn.wizool.htms.dao.impl;

import cn.wizool.htms.dao.VisitDao;
import cn.wizool.htms.iwebutil.newlay.CommonDataAccessSupport;
import cn.wizool.htms.iwebutil.newlay.query.ParameterString;
import cn.wizool.htms.model.Visit;

public class VisitDaoImpl extends CommonDataAccessSupport<Visit> implements
		VisitDao {

	@Override
	protected String getModelName() {
		return Visit.class.getName();
	}

	@Override
	protected void createHqlTemplates(ParameterString tmp) {
		tmp.setTemplate(
				"getAllOrderByNum",
				"from <model> v where "
						+ " v.date between :tBeginYear and :tEndYear"
						+ " and (v.applyName like :sApplyName or :esApplyName = false)"
						+ " and (v.passportNum like :sPassportNum or :esPassportNum = false)"
						+ " and (v.date>=:tBeginDate or :etBeginDate=false)"
						+ " and (v.date<=:tEndDate or :etEndDate=false)"
						+ " order by v.num desc");

		super.createHqlTemplates(tmp);
	}
}