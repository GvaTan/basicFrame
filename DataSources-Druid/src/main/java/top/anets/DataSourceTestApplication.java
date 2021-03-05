package top.anets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

import top.anets.dataSource.config.DataSourceConfig;
import top.anets.dataSource.config.DynamicDataSource;
import top.anets.dataSource.config.DynamicDataSourceContextHolder;
 
@Import({DataSourceConfig.class})
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class DataSourceTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(DataSourceTestApplication.class, args);
	}
}
