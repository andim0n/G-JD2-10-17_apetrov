package spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itacademy.jd2.apetrov.cms.service.impl.BlockServiceImpl;
import com.itacademy.jd2.apetrov.cms.service.impl.ChangeHistoryServiceImpl;
import com.itacademy.jd2.apetrov.cms.service.impl.ContentServiceImpl;
import com.itacademy.jd2.apetrov.cms.service.impl.MenuItemServiceImpl;
import com.itacademy.jd2.apetrov.cms.service.impl.PageServiceImpl;
import com.itacademy.jd2.apetrov.cms.service.impl.RoleOnSiteServiceImpl;
import com.itacademy.jd2.apetrov.cms.service.impl.SiteServiceImpl;
import com.itacademy.jd2.apetrov.cms.service.impl.UserAccountServiceImpl;

public class ServiceSpringContextExample {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServiceSpringContextExample.class);

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"service-context.xml");
		LOGGER.info(context.getBean(SiteServiceImpl.class).toString());
		LOGGER.info(context.getBean(PageServiceImpl.class).toString());
		LOGGER.info(context.getBean(BlockServiceImpl.class).toString());
		LOGGER.info(context.getBean(ContentServiceImpl.class).toString());
		LOGGER.info(context.getBean(UserAccountServiceImpl.class).toString());
		LOGGER.info(context.getBean(RoleOnSiteServiceImpl.class).toString());
		LOGGER.info(context.getBean(MenuItemServiceImpl.class).toString());
		LOGGER.info(context.getBean(ChangeHistoryServiceImpl.class).toString());

		// TODO show multiple candidates with Qualifier

	}
}
