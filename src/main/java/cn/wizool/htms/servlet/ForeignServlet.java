package cn.wizool.htms.servlet;

import cn.wizool.htms.common.PlatFormHttpServlet;

public class ForeignServlet extends PlatFormHttpServlet {

	private static final long serialVersionUID = 7944521815999564870L;

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
		
	}

}
