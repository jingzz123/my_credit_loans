package cn.creditloans.tools.context;

import javax.servlet.ServletContext;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.context.support.ServletContextResource;

/**
 * 获取配置文件信息
 * 
 * @author Ash
 * 
 */
public class AppContextUtils {

	/**
	 * 返回Resource
	 * @param context
	 * @param resourcePath
	 * @return
	 */
	public static Resource getResource(ServletContext context, String resourcePath) {
		try {
			Resource r = null;
			String[] parts = resourcePath.split(":");
			if (parts.length > 1) {
				if (parts[0].equalsIgnoreCase("classpath")) {
					r = new ClassPathResource(parts[1]);
				} else {
					r = new UrlResource(resourcePath);
				}
			} else {
				r = new ServletContextResource(context, resourcePath);
			}
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
