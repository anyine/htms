package cn.wizool.htms.servlet;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import cn.wizool.htms.common.PlatFormHttpServlet;
import cn.wizool.htms.iwebutil.DateUtil;
import cn.wizool.htms.iwebutil.JSON;
import cn.wizool.htms.iwebutil.JSONArray;
import cn.wizool.htms.iwebutil.StringUtil;
import cn.wizool.htms.iwebutil.newlay.QueryListCallback;
import cn.wizool.htms.iwebutil.newlay.query.NamedConditions;
import cn.wizool.htms.model.Activity;

public class ActivityServlet extends PlatFormHttpServlet {

	private static final long serialVersionUID = -3850487783192653008L;

	public void getAll() {
		final HttpServletResponse response = getResponse();
		int start = Integer.parseInt(getRequest().getParameter("start"));
		int limit = Integer.parseInt(getRequest().getParameter("limit"));

		// String date = getRequest().getParameter("date");
		String provinceLeader = getRequest().getParameter("provinceLeader");
		String jhdzLeader = getRequest().getParameter("jhdzLeader");
		String name = getRequest().getParameter("name");
		String content = getRequest().getParameter("content");
		String path = getRequest().getParameter("path");
		String type = getRequest().getParameter("type");
		NamedConditions cond = new NamedConditions("getAllOrderByNum");
		cond.putString("Type", type);
		cond.putString("ProvinceLeader", "%" + provinceLeader + "%");
		cond.putString("JhdzLeader", "%" + jhdzLeader + "%");
		cond.putString("Name", "%" + name + "%");
		cond.putString("Content", "%" + content + "%");
		cond.putString("Path", "%" + path + "%");

		try {
			getActivityService().transSelect(null, start, limit, cond,
					new QueryListCallback<Activity>() {
						@Override
						public void callback(int total, List<Activity> objs) {
							JSONArray json = new JSONArray(response);
							json.setSuccess(true);
							json.setTotal(total);
							for (Activity a : objs) {
								activityJsonList(a, json);
							}
							json.flush();
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void activityJsonList(Activity a, JSONArray json) {
		json.setAttribute("id", a.getId());
		json.setAttribute("name", a.getName());
		json.setAttribute("jhdzDep", a.getJhdzDep());
		json.setAttribute("jhdzLeader", a.getJhdzLeader());
		json.setAttribute("provinceLeader", a.getProvinceLeader());
		json.setAttribute("provinceDep", a.getProvinceDep());
		json.setAttribute("fileNum", a.getFileNum());
		json.setAttribute("serialNum", a.getSerialNum());
		json.setAttribute("content", a.getContent());
		json.setAttribute("locale", a.getLocale());
		json.setAttribute("meal", a.getMeal());
		json.setAttribute("path", a.getPath());
		json.setAttribute("peopleNum", a.getPeopleNum());
		json.setAttribute("peopleCond", a.getPeopleCond());
		json.setAttribute("remark", a.getRemark());
		json.setAttribute("date", DateUtil.format(a.getDate()));
	}

	public void edit() {
		String id = getRequest().getParameter("id");
		String date = getRequest().getParameter("date");
		String fileNum = getRequest().getParameter("fileNum");
		String name = getRequest().getParameter("name");
		String peopleCond = getRequest().getParameter("peopleCond");
		String provinceLeader = getRequest().getParameter("provinceLeader");
		String jhdzLeader = getRequest().getParameter("jhdzLeader");
		String provinceDep = getRequest().getParameter("provinceDep");
		String jhdzDep = getRequest().getParameter("jhdzDep");
		String content = getRequest().getParameter("content");
		String locale = getRequest().getParameter("locale");
		String peopleNum = getRequest().getParameter("peopleNum");
		String path = getRequest().getParameter("path");
		String meal = getRequest().getParameter("meal");
		String remark = getRequest().getParameter("remark");
		String type = getRequest().getParameter("type");

		if (!(StringUtil.notEmpty(id))) {
			try {
				Activity a = new Activity();
				a.setId(UUID.randomUUID().toString());
				a.setFileNum(fileNum);
				a.setName(name);
				a.setJhdzDep(jhdzDep);
				a.setJhdzLeader(jhdzLeader);
				a.setProvinceDep(provinceDep);
				a.setProvinceLeader(provinceLeader);
				a.setPeopleNum(peopleNum);
				a.setPath(path);
				a.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
				a.setType(type);

				if (type.equals("rite")) {
					a.setLocale(locale);
				} else {
					a.setPeopleCond(peopleCond);
					a.setContent(content);
					a.setRemark(remark);
					a.setMeal(meal);
				}

				a.setSerialNum(getActivityService().getCountByType(type) + 1);

				getActivityService().transCreate(null, a);
				JSON.sendSuccess(getResponse());
			} catch (Exception e) {
				JSON.sendErrorMessage(getResponse(), "新增对象有误！");
				e.printStackTrace();
			}
		} else {
			try {
				Activity a = getActivityService().getObjectById(id);
				a.setFileNum(fileNum);
				a.setName(name);
				a.setJhdzDep(jhdzDep);
				a.setJhdzLeader(jhdzLeader);
				a.setProvinceDep(provinceDep);
				a.setProvinceLeader(provinceLeader);
				a.setPeopleNum(peopleNum);
				a.setPath(path);
				a.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));

				if (type.equals("rite")) {
					a.setLocale(locale);
				} else {
					a.setPeopleCond(peopleCond);
					a.setContent(content);
					a.setRemark(remark);
					a.setMeal(meal);
				}

				getActivityService().transUpdate(null, a);
				JSON.sendSuccess(getResponse());
			} catch (Exception e) {
				JSON.sendErrorMessage(getResponse(), "修改对象有误！");
				e.printStackTrace();
			}
		}
	}

	public void delete() {
		String[] ids = getRequest().getParameterValues("ids");
		try {
			getActivityService().transDelete(null, ids);
			JSON.sendSuccess(getResponse());
		} catch (Exception e) {
			JSON.sendErrorMessage(getResponse(), "删除对象有误！");
			e.printStackTrace();
		}
	}

}
