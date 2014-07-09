package cn.wizool.htms.iwebutil;

import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletCreateListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		final ServletContext sc = sce.getServletContext();
		
		String[] packages = sc.getInitParameter("servletPackages").split(":");
		for (String p : packages) {
			PackageSearcher cs = new PackageSearcher();
			cs.setPackageName(p.replace('.', '/'));
			cs.setListener(new PackageSearcherListener() {
				@Override
				public void found(String path, boolean isDir, String resource) {
					if (resource.endsWith("Servlet.class")
							&& !resource.contains("$")) {
						String map = "/"
								+ resource.replaceAll("Servlet\\.class$",
										"Servlet") + "/*";
						String cls = resource.replaceAll("Servlet\\.class$",
								"Servlet").replaceAll("/", ".");
						sc.addServlet(UUID.randomUUID().toString(), cls)
								.addMapping(map);
					}
				}
			});
			cs.search();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
