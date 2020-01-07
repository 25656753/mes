package com.jg.mes.config;

import com.jg.mes.Datasource.DataSoruceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class proxyconfig {


	
	@Bean
	public DataSoruceProxy getproxy() {
		DataSoruceProxy proxy = new DataSoruceProxy();
		return proxy;
	}


	
	/*@Bean
	public DataSourceInitializer manDataSourceInitializer() {// 6
		DataSourceInitializer dsInitializer = new DataSourceInitializer();
		 dsInitializer.setDataSource(datasource);
         System.out.println("---->datainit");
		
		  
		  ResourceDatabasePopulator dbPopulator = new ResourceDatabasePopulator();
		  dbPopulator.addScript(new ClassPathResource("security-data.sql"));
		  dsInitializer.setDatabasePopulator(dbPopulator);
		  dsInitializer.setEnabled(true);
		  return dsInitializer;
	}
*/
	
	
}
