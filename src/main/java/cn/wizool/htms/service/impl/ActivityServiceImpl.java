package cn.wizool.htms.service.impl;

import cn.wizool.htms.common.PlatFormServiceSupport;
import cn.wizool.htms.iwebutil.newlay.QueryListCallback;
import cn.wizool.htms.iwebutil.newlay.query.NamedConditions;
import cn.wizool.htms.model.Activity;
import cn.wizool.htms.service.ActivityService;

public class ActivityServiceImpl extends PlatFormServiceSupport implements
		ActivityService {

	@Override
	public void transCreate(String uid, Activity a) {
		getActivityDao().create(a);
	}

	@Override
	public void transUpdate(String uid, Activity a) {
		getActivityDao().update(a);
	}

	@Override
	public void transDelete(String uid, String[] ids) {
		getActivityDao().delete(ids);
	}

	@Override
	public void transSelect(String uid, int start, int limit,
			NamedConditions cond, QueryListCallback<Activity> callback) {
		callback.callback(getActivityDao().count(cond), getActivityDao()
				.select(start, limit, cond));
	}

	@Override
	public int getCountByType(String type) {
		NamedConditions cond = new NamedConditions("getCountByType");
		cond.putString("Type", type);
		return getActivityDao().count(cond);
	}

	@Override
	public Activity getObjectById(String id) {
		return getActivityDao().select(id);
	}

}
