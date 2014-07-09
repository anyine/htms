package cn.wizool.htms.service;

/**
 * 外事管理
 * 
 * @author Administrator
 * 
 */
import cn.wizool.htms.iwebutil.newlay.ICommonServiceSupport;
import cn.wizool.htms.iwebutil.newlay.QueryListCallback;
import cn.wizool.htms.iwebutil.newlay.query.NamedConditions;
import cn.wizool.htms.model.Foreign;

public interface ForeignService extends ICommonServiceSupport {

	public abstract void transCreate(String uid, Foreign f);

	public abstract void transDelete(String uid, String[] ids);

	public abstract void transSelect(String uid, int start, int limit,
			NamedConditions cond, QueryListCallback<Foreign> callback);

}
