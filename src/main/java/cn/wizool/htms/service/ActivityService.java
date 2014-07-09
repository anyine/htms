package cn.wizool.htms.service;

/**
 * 活动管理
 * 
 * @author Administrator
 * 
 */

import cn.wizool.htms.iwebutil.newlay.ICommonServiceSupport;
import cn.wizool.htms.iwebutil.newlay.QueryListCallback;
import cn.wizool.htms.iwebutil.newlay.query.NamedConditions;
import cn.wizool.htms.model.Activity;

public interface ActivityService extends ICommonServiceSupport {

	public abstract void transCreate(String uid, Activity a);

	public abstract void transUpdate(String uid, Activity a);

	public abstract void transDelete(String uid, String[] ids);

	public abstract void transSelect(String uid, int start, int limit,
			NamedConditions cond, QueryListCallback<Activity> callback);

	public abstract int getCountByType(String type);

	public abstract Activity getObjectById(String id);

}
