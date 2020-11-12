package com.rd.scm.datasource;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class DatasourceTest {

    @Autowired
    private DSConfig dsConfig;

    @Test
    public void dynamicDataSource() {

        JdbcTemplate template = dsConfig.getDynamicDataSource("d12g");
        List<Map<String, Object>> list = template.queryForList("SELECT COUNT(*) FROM DUAL");
        System.out.println("list ==============> " + list);
        Assertions.assertEquals(1, list.size(), "lookup should return 1 count");

    }

}