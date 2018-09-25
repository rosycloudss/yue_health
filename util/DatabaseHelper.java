package com.yue_health.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.yue_health.entity.PhysicalExaminationProject;
import com.yue_health.entity.User;
import com.yue_health.entity.UserPhysicalExaminationInfo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TABLE_NAME = "yue_health.db";

    private Map<String, Dao> daos = new HashMap<String, Dao>();


    private DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, PhysicalExaminationProject.class);//创建体检项目表
            TableUtils.createTable(connectionSource, User.class);//创建用户信息表
            TableUtils.createTable(connectionSource, UserPhysicalExaminationInfo.class);//创建用户体检信息表
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {

            TableUtils.dropTable(connectionSource, PhysicalExaminationProject.class, true);//删除体检项目表
            TableUtils.dropTable(connectionSource, User.class, true);//删除用户信息表
            TableUtils.dropTable(connectionSource, UserPhysicalExaminationInfo.class, true);//删除用户体检信息表
            onCreate(database, connectionSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DatabaseHelper instance;

    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }

        return instance;
    }

    /**
     * 根据class 获取相应的dao
     *
     * @param clazz
     * @return
     * @throws SQLException
     */
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName(); //通过反射获得类名

        if (daos.containsKey(className)) {
            dao = daos.get(className);
        } else {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();

        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
        daos.clear();
    }
}
