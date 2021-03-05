/**
 * 
 */
package top.anets.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author Administrator
 *
 */
@Configuration
public class DataSourceConfiguration {
	// c3p0 连接池
	@Bean(name = "dataSource")
	@Qualifier(value = "dataSource")
	@Primary
	@ConfigurationProperties(prefix = "c3p0")
	public DataSource dataSource() {
		return   DataSourceBuilder.create().type(com.mchange.v2.c3p0.ComboPooledDataSource.class).build();
	}
}
