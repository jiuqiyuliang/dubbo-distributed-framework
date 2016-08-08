package com.dmsd.itoo.base.dao.changedb;

/**
 * Created by LiuYing on 2016/7/13.
 */
public class DatabaseDefineBean {
    private String driverClassName;
    private String userName;
    private String passWord;
    private String url;


    public DatabaseDefineBean( String url, String userName, String passWord) {
        super();
        this.url = url;
        this.userName = userName;
        this.passWord = passWord;
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
