package cn.wizool.htms.dao.impl;

import cn.wizool.htms.dao.ForeignDao;
import cn.wizool.htms.iwebutil.newlay.CommonDataAccessSupport;
import cn.wizool.htms.model.Foreign;

public class ForeignDaoImpl extends CommonDataAccessSupport<Foreign> implements
		ForeignDao {

	@Override
	protected String getModelName() {
		return Foreign.class.getName();
	}

}
