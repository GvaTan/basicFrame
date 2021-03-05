package top.anets;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataSourceHikariCpApplicationTests {
	@Autowired
    private DataSource dataSource;
	@Test
	void contextLoads() throws SQLException {
		System.out.println(dataSource.getConnection());
	}

}
