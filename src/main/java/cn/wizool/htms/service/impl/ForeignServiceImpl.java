package cn.wizool.htms.service.impl;

import cn.wizool.htms.common.PlatFormServiceSupport;
import cn.wizool.htms.iwebutil.newlay.QueryListCallback;
import cn.wizool.htms.iwebutil.newlay.query.NamedConditions;
import cn.wizool.htms.model.Foreign;
import cn.wizool.htms.service.ForeignService;

public class ForeignServiceImpl extends PlatFormServiceSupport implements
		ForeignService {

	@Override
	public void transCreate(String uid, Foreign f) {
		getForeignDao().create(f);
	}

	@Override
	public void transDelete(String uid, String[] ids) {
		getForeignDao().delete(ids);
	}

	@Override
	public void transSelect(String uid, int start, int limit,
			NamedConditions cond, QueryListCallback<Foreign> callback) {
		callback.callback(getForeignDao().count(cond),
				getForeignDao().select(start, limit, cond));
	}

}
