package cn.wizool.htms.dao.impl;

import cn.wizool.htms.dao.UserDao;
import cn.wizool.htms.iwebutil.newlay.CommonDataAccessSupport;
import cn.wizool.htms.model.User;

public class UserDaoImpl extends CommonDataAccessSupport<User> implements
		UserDao {

	@Override
	protected String getModelName() {
		return User.class.getName();
	}

}
