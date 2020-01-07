package com.jg.mes.Datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.Map;

public class DataSoruceProxy  {
	private static final String DATASOURCE_TYPE_DEFAULT = "com.alibaba.druid.pool.DruidDataSource";

	private Logger logger =LoggerFactory.getLogger(DataSoruceProxy.class);
	@Autowired
	private Environment env;

	@SuppressWarnings("unchecked")
	public void addDataSource(String url, String name) {

		// 读取配置文件获取更多数据源
		try {
			String driverClassName = env.getProperty("spring.datasource.druid.driver-class-name");
			String username = env.getProperty("spring.datasource.druid.username");
			String password = env.getProperty("spring.datasource.druid.password");
			Class<? extends DataSource> dataSourceType = (Class<? extends DataSource>) Class
					.forName(DATASOURCE_TYPE_DEFAULT);
			DataSourceBuilder<? extends DataSource> factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
					.username(username).password(password).type(dataSourceType);
			DataSource ds = factory.build();
			DynamicDataSourceContextHolder.dataSourceIds.add(name);
			register(name, ds);
			logger.info("动态加入数据源，id:"+name);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Autowired
	private ApplicationContext ctx;

	@SuppressWarnings("unchecked")
	public void register(String key, DataSource value) {
		// 获取BeanFactory
		try {
			/*
			 * DefaultListableBeanFactory defaultListableBeanFactory =
			 * (DefaultListableBeanFactory) ctx .getAutowireCapableBeanFactory();
			 */
			DynamicDataSource datasource = ctx.getBean("dataSource", DynamicDataSource.class);
			Field targetDataSourcesfield = datasource.getClass().getSuperclass().getDeclaredField("targetDataSources");
			targetDataSourcesfield.setAccessible(true);
			Map<Object, Object> targetDataSources = (Map<Object, Object>) targetDataSourcesfield.get(datasource);

			targetDataSources.put(key, value);
			targetDataSourcesfield.set(datasource, targetDataSources);
			datasource.afterPropertiesSet();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
