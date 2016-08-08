package com.dmsd.itoo.base.dao.changedb;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.sql.DataSource;


import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class MyDataSource extends AbstractRoutingDataSource {

    private String driverClassName;
    private String userName;
    private String passWord;
    private String url;
    static ThreadLocal<DatabaseDefineBean> defineBeans = new ThreadLocal<DatabaseDefineBean>();

    public MyDataSource() {
        this.setTargetDataSources(new HashMap<Object, Object>());
    }

    public static void setDefineBeans(DatabaseDefineBean bean) {
        defineBeans.set(bean);
    }

    public Logger getParentLogger()  {
        return Logger.getLogger(MyDataSource.class.getName());
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return defineBeans.get();
    }

    @Override
    protected DataSource determineTargetDataSource() {
        DriverManagerDataSource dataSource = getDataSource((DatabaseDefineBean) determineCurrentLookupKey());
        return dataSource;
    }

    private DriverManagerDataSource getDataSource(DatabaseDefineBean database) {
        DriverManagerDataSource dataSource;
        if (database == null) {
            dataSource = new DriverManagerDataSource(url, userName,
                    passWord);
            dataSource.setDriverClassName(driverClassName);
        } else {

            dataSource = new DriverManagerDataSource(database.getUrl()+"?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull", database.getUserName(),
                    database.getPassWord());
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        }

        return dataSource;

    }


    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}