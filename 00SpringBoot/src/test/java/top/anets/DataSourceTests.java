package top.anets;


import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataSourceTests {
    @Autowired
    private DataSource dataSource;
    @Test
        public void testConnection() throws Exception{
                System.out.println(dataSource.getConnection());
        }
}