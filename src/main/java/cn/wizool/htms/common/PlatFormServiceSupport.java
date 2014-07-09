package cn.wizool.htms.common;

import cn.wizool.htms.dao.ActivityDao;
import cn.wizool.htms.dao.ForeignDao;
import cn.wizool.htms.dao.StatisticsDao;
import cn.wizool.htms.dao.UserDao;
import cn.wizool.htms.dao.VisitDao;
import cn.wizool.htms.iwebutil.newlay.ServiceSupport;

public class PlatFormServiceSupport extends ServiceSupport {
	private static final String PLATFORM = "platform";

	private static final String USER_DAO = "UserDao";
	private static final String VISIT_DAO = "VisitDao";
	private static final String ACTIVITY_DAO = "ActivityDao";
	private static final String FOREIGN_DAO = "ForeignDao";
	private static final String STATISTICS_DAO = "StatisticsDao";

	public UserDao getUserDao() {
		return (UserDao) this.getDataAccessObject(PLATFORM, USER_DAO);
	}

	public StatisticsDao getStatisticsDao() {
		return (StatisticsDao) this.getDataAccessObject(PLATFORM,
				STATISTICS_DAO);
	}

	public ActivityDao getActivityDao() {
		return (ActivityDao) this.getDataAccessObject(PLATFORM, ACTIVITY_DAO);
	}

	public ForeignDao getForeignDao() {
		return (ForeignDao) this.getDataAccessObject(PLATFORM, FOREIGN_DAO);
	}

	public VisitDao getVisitDao() {
		return (VisitDao) this.getDataAccessObject(PLATFORM, VISIT_DAO);
	}

}
