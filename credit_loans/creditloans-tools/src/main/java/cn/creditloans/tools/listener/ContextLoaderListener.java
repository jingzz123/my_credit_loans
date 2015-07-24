package cn.creditloans.tools.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.creditloans.tools.context.ContextLoader;

public class ContextLoaderListener extends ContextLoader implements ServletContextListener {
	private ContextLoader contextLoader = null;

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if(contextLoader != null) {
			contextLoader.closeWebAppContext(sce.getServletContext());
			contextLoader = null;
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		if(contextLoader == null) {
			contextLoader = this;
		}
		contextLoader.initWebAppContext(sce.getServletContext());
	}

}
