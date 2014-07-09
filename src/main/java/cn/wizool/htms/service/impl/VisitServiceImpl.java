package cn.wizool.htms.service.impl;

import cn.wizool.htms.common.PlatFormServiceSupport;
import cn.wizool.htms.iwebutil.newlay.QueryListCallback;
import cn.wizool.htms.iwebutil.newlay.query.NamedConditions;
import cn.wizool.htms.model.Visit;
import cn.wizool.htms.service.VisitService;

public class VisitServiceImpl extends PlatFormServiceSupport implements
		VisitService {

	@Override
	public void transCreate(String uid, Visit v) {
		getVisitDao().create(v);
	}

	@Override
	public void transUpdate(String uid, Visit v) {
		getVisitDao().update(v);
	}

	@Override
	public void transDelete(String uid, String[] ids) {
		getVisitDao().delete(ids);
	}

	@Override
	public void transSelectByCond(String uid, int start, int limit,
			NamedConditions cond, QueryListCallback<Visit> callback) {
		callback.callback(getVisitDao().count(cond),
				getVisitDao().select(start, limit, cond));
	}

	@Override
	public int getCount() {
		return getVisitDao().count();
	}

	@Override
	public Visit getObjectById(String id) {
		return getVisitDao().select(id);
	}
}
