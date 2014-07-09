package cn.wizool.htms.common;

import cn.wizool.htms.iwebutil.newlay.AbstractHttpServlet;
import cn.wizool.htms.service.ActivityService;
import cn.wizool.htms.service.ForeignService;
import cn.wizool.htms.service.StatisticsService;
import cn.wizool.htms.service.UserService;
import cn.wizool.htms.service.VisitService;

public abstract class PlatFormHttpServlet extends AbstractHttpServlet {

	private static final long serialVersionUID = -3295271408899300159L;
	private static final String PLATFORM = "platform";

	private static final String USER_SERVICE = "UserService";
	private static final String VISIT_SERVICE = "VisitService";
	private static final String ACTIVITY_SERVICE = "ActivityService";
	private static final String FOREIGN_SERVICE = "ForeignService";
	private static final String STATISTICS_SERVICE = "StatisticsService";

	public UserService getUserService() {
		return (UserService) this.getService(PLATFORM, USER_SERVICE);
	}

	public StatisticsService getStatisticsService() {
		return (StatisticsService) this
				.getService(PLATFORM, STATISTICS_SERVICE);
	}

	public ActivityService getActivityService() {
		return (ActivityService) this.getService(PLATFORM, ACTIVITY_SERVICE);
	}

	public ForeignService getForeignService() {
		return (ForeignService) this.getService(PLATFORM, FOREIGN_SERVICE);
	}

	public VisitService getVisitService() {
		return (VisitService) this.getService(PLATFORM, VISIT_SERVICE);
	}

}
