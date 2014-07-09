package cn.wizool.htms.service;

import cn.wizool.htms.iwebutil.newlay.ICommonServiceSupport;
import cn.wizool.htms.iwebutil.newlay.QueryListCallback;
import cn.wizool.htms.iwebutil.newlay.query.NamedConditions;
import cn.wizool.htms.model.Visit;

public interface VisitService extends ICommonServiceSupport {

	public abstract void transCreate(String uid, Visit v);

	public abstract void transUpdate(String uid, Visit v);

	public abstract void transDelete(String uid, String[] ids);

	public abstract void transSelectByCond(String uid, int start, int limit,
			NamedConditions cond, QueryListCallback<Visit> callback);

	public abstract int getCount();

	public abstract Visit getObjectById(String id);
}
