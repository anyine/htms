package cn.wizool.htms.dao.impl;

import cn.wizool.htms.dao.StatisticsDao;
import cn.wizool.htms.iwebutil.newlay.CommonDataAccessSupport;
import cn.wizool.htms.model.Statistics;

public class StatisticsDaoImpl extends CommonDataAccessSupport<Statistics>
		implements StatisticsDao {

	@Override
	protected String getModelName() {
		return Statistics.class.getName();
	}

}
