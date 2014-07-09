package cn.wizool.htms.servlet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import cn.wizool.htms.common.PlatFormHttpServlet;
import cn.wizool.htms.iwebutil.DateUtil;
import cn.wizool.htms.iwebutil.DateUtil.DateFormatType;
import cn.wizool.htms.iwebutil.JSON;
import cn.wizool.htms.iwebutil.JSONArray;
import cn.wizool.htms.iwebutil.StringUtil;
import cn.wizool.htms.iwebutil.newlay.QueryListCallback;
import cn.wizool.htms.iwebutil.newlay.query.NamedConditions;
import cn.wizool.htms.model.Visit;

public class VisitServlet extends PlatFormHttpServlet {

	private static final long serialVersionUID = 1726254908752016961L;

	public void getAll() throws ParseException {
		final HttpServletResponse response = getResponse();
		int start = Integer.parseInt(getRequest().getParameter("start"));
		int limit = Integer.parseInt(getRequest().getParameter("limit"));
		String passportNum = getRequest().getParameter("passportNum");
		String applyName = getRequest().getParameter("applyName");
		String year = getRequest().getParameter("year");
		String beginDate = getRequest().getParameter("beginDate");
		String endDate = getRequest().getParameter("endDate");

		NamedConditions conditions = new NamedConditions("getAllOrderByNum");
		conditions.putString("PassportNum", "%" + passportNum + "%");
		conditions.putString("ApplyName", "%" + applyName + "%");
		conditions.putDate("BeginYear", new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss").parse(year + "-01-01 00:00:00"));
		conditions.putDate("EndYear", new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss").parse(year + "-12-31 23:59:59"));
		dateConditions(year, beginDate, endDate, conditions);

		try {
			getVisitService().transSelectByCond(null, start, limit, conditions,
					new QueryListCallback<Visit>() {
						@Override
						public void callback(int total, List<Visit> objs) {
							JSONArray json = new JSONArray(response);
							json.setSuccess(true);
							json.setTotal(total);
							for (Visit v : objs) {
								visitJsonList(v, json);
							}
							json.flush();
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void visitJsonList(Visit v, JSONArray json) {
		json.setAttribute("id", v.getId());
		json.setAttribute("num", v.getNum());
		json.setAttribute("applyName", v.getApplyName());
		json.setAttribute("comeDate", v.getComeDate());
		json.setAttribute("inviteName", v.getInviteName());
		json.setAttribute("linkMan", v.getLinkMan());
		json.setAttribute("passportNum", v.getPassportNum());
		json.setAttribute("phone", v.getPhone());
		json.setAttribute("date",
				DateUtil.format(v.getDate(), DateFormatType.SIMPLE_TYPE));
		json.setAttribute("remain", v.getRemain());
		json.setAttribute("reason", v.getReason());
		json.setAttribute("count", v.getCount());
	}

	private void dateConditions(String year, String beginDate, String endDate,
			NamedConditions conditions) {
		String bd = "";
		String ed = "";

		try {
			if ((!(StringUtil.notEmpty(beginDate)))
					&& (!(StringUtil.notEmpty(endDate)))) {
				conditions.putString("BeginDate", "");
				conditions.putString("EndDate", "");
			} else if (!(StringUtil.notEmpty(beginDate))) {
				int i2 = endDate.indexOf("T");
				ed = endDate.substring(0, i2);
				conditions.putDate("BeginDate", new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss").parse(year + "-01-01 00:00:00"));
				conditions.putDate("EndDate", new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss").parse(ed + " 00:00:00"));
			} else if (!(StringUtil.notEmpty(endDate))) {
				int i1 = beginDate.indexOf("T");
				bd = beginDate.substring(0, i1);
				conditions.putDate("BeginDate", new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss").parse(bd + " 00:00:00"));
				conditions.putDate("EndDate", new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss").parse(year + "-12-31 23:59:59"));
			} else {
				int i1 = beginDate.indexOf("T");
				bd = beginDate.substring(0, i1);
				int i2 = endDate.indexOf("T");
				ed = endDate.substring(0, i2);
				conditions.putDate("BeginDate", new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss").parse(bd + " 00:00:00"));
				conditions.putDate("EndDate", new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss").parse(ed + " 00:00:00"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void edit() {
		String id = getRequest().getParameter("id");
		String inviteName = getRequest().getParameter("inviteName");
		String count = getRequest().getParameter("count");
		String passportNum = getRequest().getParameter("passportNum");
		String comeDate = getRequest().getParameter("comeDate");
		String remain = getRequest().getParameter("remain");
		String reason = getRequest().getParameter("reason");
		String applyName = getRequest().getParameter("applyName");
		String linkMan = getRequest().getParameter("linkMan");
		String phone = getRequest().getParameter("phone");
		String date = getRequest().getParameter("date");

		if (!(StringUtil.notEmpty(id))) {
			try {
				Visit v = new Visit();
				String vid = UUID.randomUUID().toString();
				v.setId(vid);
				v.setNum(getVisitService().getCount() + 1);
				v.setInviteName(inviteName);
				v.setCount((count == null || "".equals(count)) ? 0 : Integer
						.parseInt(count));
				v.setPassportNum(passportNum);
				v.setComeDate(comeDate);
				v.setRemain(remain);
				v.setReason(reason);
				v.setApplyName(applyName);
				v.setLinkMan(linkMan);
				v.setPhone(phone);
				v.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));

				getVisitService().transCreate(null, v);
				JSON.sendSuccess(getResponse());
			} catch (Exception e) {
				JSON.sendErrorMessage(getResponse(), "新增邀请外国人来访有误！");
				e.printStackTrace();
			}
		} else {
			try {
				Visit v = getVisitService().getObjectById(id);
				v.setInviteName(inviteName);
				v.setCount((count == null || "".equals(count)) ? v.getCount()
						: Integer.parseInt(count));
				v.setPassportNum(passportNum);
				v.setComeDate(comeDate);
				v.setRemain(remain);
				v.setReason(reason);
				v.setApplyName(applyName);
				v.setLinkMan(linkMan);
				v.setPhone(phone);
				v.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));

				getVisitService().transUpdate(null, v);
				JSON.sendSuccess(getResponse());
			} catch (Exception e) {
				JSON.sendErrorMessage(getResponse(), "修改邀请外国人来访有误！");
				e.printStackTrace();
			}
		}
	}

	public void delete() {
		String[] ids = getRequest().getParameterValues("ids");
		try {
			getVisitService().transDelete(null, ids);
			JSON.sendSuccess(getResponse());
		} catch (Exception e) {
			JSON.sendErrorMessage(getResponse(), "删除邀请外国人来访有误！");
			e.printStackTrace();
		}
	}

}
