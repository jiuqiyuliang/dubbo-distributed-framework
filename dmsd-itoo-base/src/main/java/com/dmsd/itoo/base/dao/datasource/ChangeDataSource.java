package com.dmsd.itoo.base.dao.datasource;

import com.dmsd.itoo.base.dao.changedb.DatabaseDefineBean;
import com.dmsd.itoo.base.dao.changedb.MyDataSource;
import net.sf.json.JSONObject;

/**
 * Created by LiuYing on 2016/7/16.
 */
public class ChangeDataSource {

    public static void ChangeDataSource(String jsonString) {

        JSONObject requestJsonObject = JSONObject.fromObject(jsonString);

        //        判断是否要切换数据源
        JSONObject database = requestJsonObject.getJSONObject("database");
        String driverClassName = database.containsKey("driverClassName") ? database.getString("driverClassName") : null;
        String userName = database.containsKey("userName") ? database.getString("userName") : null;
        String passWord = database.containsKey("passWord") ? database.getString("passWord") : null;
        String url = database.containsKey("url") ? database.getString("url") : null;

        MyDataSource.setDefineBeans(new DatabaseDefineBean( url, userName, passWord));

    }


    public static void ChangeDataSource(JSONObject jsonObject)
    {
        JSONObject database = jsonObject.getJSONObject("database");
        String driverClassName = database.containsKey("driverClassName") ? database.getString("driverClassName") : null;
        String userName = database.containsKey("userName") ? database.getString("userName") : null;
        String passWord = database.containsKey("passWord") ? database.getString("passWord") : null;
        String url = database.containsKey("url") ? database.getString("url") : null;

        MyDataSource.setDefineBeans(new DatabaseDefineBean( url, userName, passWord));
    }

}
