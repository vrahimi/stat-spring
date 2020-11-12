package com.rd.scm.datasource;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

import javax.sql.DataSource;

import com.rd.scm.dao.DbConfigDaoJpa;
import com.rd.scm.model.DbConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/*ENC(PLQF5O2UZf1IiqSL03j8Ufr/pQadvNYV)*/

@Component
public class DSConfig {

    private static final Logger log = LoggerFactory.getLogger(DSConfig.class);

    static final String ORA_DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
    static final String SQL_DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";

    private final DbConfigDaoJpa dbConfigDaoJpa;

    @Autowired
    public DSConfig(DbConfigDaoJpa dbConfigDaoJpa) {
        this.dbConfigDaoJpa = dbConfigDaoJpa;
    }

    static Map<String, Properties> dsProps = new HashMap<String, Properties>();
    static Map<String, DataSource> dsMap = new HashMap<String, DataSource>();
    static Map<String, JdbcTemplate> jtMap = new HashMap<String, JdbcTemplate>();


    public JdbcTemplate getDynamicDataSource(String dbCd) {

        DbConfig dbConfig = dbConfigDaoJpa.findById(dbCd).orElseThrow(() -> 
            new NoSuchElementException("Database connection does not exist " + dbCd));

        Properties newProp = makeProperties(dbConfig);
        boolean resetConnection = false;
        if ( null == dsProps.get(dbCd)) {
            
            dsProps.put(dbCd, newProp);             
        } else {
            Properties oldProp = dsProps.get(dbCd);
            if(!oldProp.equals(newProp)) {
                resetConnection = true;
            }
        }

        String url = null;
        String driver = null;
        if("ORA".equals(newProp.get("dbType"))) {
            driver = ORA_DRIVER_CLASS;
            url = "jdbc:oracle:thin:@"+newProp.get("host")+":"+newProp.get("port")+":"+newProp.get("service");

            if(log.isDebugEnabled()) {
                log.debug("url: " + newProp.get("url"));
            }
        } else {
            
        }   

        return getJdbcTemplate(dbCd, url, driver, 
                              dbConfig.getSrvLogid(), dbConfig.getSrvPwd(), resetConnection);
    }

    private JdbcTemplate getJdbcTemplate(String dbCd, String url, String driver, String username, 
                                        String password, boolean resetConnection) {
        if ( null == jtMap.get(dbCd)) {
            JdbcTemplate jt =  new JdbcTemplate(buildDataSource(dbCd, url, driver, 
                                    username, password, resetConnection));
            jtMap.put(dbCd, jt);
            return jt;
        } else {
            return jtMap.get(dbCd);
        }
    }

    private DataSource buildDataSource(String dbCd, String url, String driver, String username, 
                                         String password, boolean resetConnection) {
        DataSource ds = null;
        if ( null == dsMap.get(dbCd) || resetConnection) {
            ds = DataSourceBuilder.create()
                    .driverClassName(driver)
                    .url(url)
                    .username(username)
                    .password(password)
                    .build();
            dsMap.put(dbCd, ds);
        } else {
            ds = dsMap.get("dbCd");
        }

        return ds;
    }

    private Properties makeProperties(DbConfig dbConfig) {
        Properties prop = new Properties();
        prop.put("dbType", dbConfig.getDbType());
        prop.put("host", dbConfig.getHostname());
        prop.put("port", dbConfig.getDbListenerPort());
        prop.put("service", dbConfig.getServiceName());
        prop.put("username", dbConfig.getSrvLogid());
        prop.put("password", dbConfig.getSrvPwd());

        return prop;
    }
}