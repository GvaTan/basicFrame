package top.anets;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class DataSourceC3P0ApplicationTests {
    @Autowired
	private JdbcTemplate jdbcTemplate;
	@Test
	void contextLoads() {
		System.out.println("你好");
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * FROM user");
		System.out.println(list.size());
	}

}
