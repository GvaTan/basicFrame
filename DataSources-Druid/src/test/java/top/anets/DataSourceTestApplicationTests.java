package top.anets;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class DataSourceTestApplicationTests {
	@Autowired
    JdbcTemplate jdbcTemplate;
	@Test
	void contextLoads() {
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * FROM user");
        System.out.println( list.get(0));
	}

}
