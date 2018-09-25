package com.yue_health.dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.Where;
import com.yue_health.entity.UserPhysicalExaminationInfo;
import com.yue_health.util.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户体检信息的增删改查
 */
public class UserPhyExamInfoDao {

    private static final String TAG = "UserPhyExamInfoDao";
    private static UserPhyExamInfoDao instance;

    private Context context;

    private DatabaseHelper helper;

    private Dao<UserPhysicalExaminationInfo, Integer> userPhyExamDao;


    private UserPhyExamInfoDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            userPhyExamDao = (Dao<UserPhysicalExaminationInfo, Integer>) helper.getDao(UserPhysicalExaminationInfo.class);
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    /**
     * 通过单例模式获取 UserPhyExamInfoDao 对象
     *
     * @param context
     * @return
     */
    public static UserPhyExamInfoDao getInstance(Context context) {
        if (instance == null) {
            instance = new UserPhyExamInfoDao(context);
        }
        return instance;
    }

    /**
     * 添加单个 UserPhysicalExaminationInfo对象
     *
     * @param upei
     */
    public int add(UserPhysicalExaminationInfo upei) {
        int affectCloumn = 0;
        if (upei != null) {
            try {
                affectCloumn = userPhyExamDao.create(upei);
            } catch (SQLException e) {
                Log.d(TAG, "add: 添加 " + upei + "失败");
                e.printStackTrace();
            }
        }
        return affectCloumn;
    }

    /**
     * 添加多个 UserPhysicalExaminationInfo 对象
     *
     * @param upeis
     */
    public int add(List<UserPhysicalExaminationInfo> upeis) {
        int affectCloumn = 0;
        if (upeis != null && !upeis.isEmpty()) {
            for (UserPhysicalExaminationInfo upei : upeis) {
                affectCloumn += add(upei);
            }
        }
        return affectCloumn;
    }

    /**
     * 通过Id删除用户体检信息
     *
     * @param upeiId
     */
    public void delete(int upeiId) {
        try {
            userPhyExamDao.deleteById(upeiId);
        } catch (SQLException e) {
            Log.d(TAG, "delete: 删除用户体检信息 " + upeiId + "失败");
            e.printStackTrace();
        }
    }

    /**
     * 修改用户体检信息
     *
     * @param upei
     */
    public void update(UserPhysicalExaminationInfo upei) {
        if (upei != null) {
            try {
                userPhyExamDao.update(upei);
            } catch (SQLException e) {
                Log.d(TAG, "update: 修改 " + upei + "失败");
                e.printStackTrace();
            }
        }
    }


    /**
     * 通过用户id 和 项目Id查找 用户体检信息
     *
     * @param userId
     * @param projectId
     * @return
     */
    public List<UserPhysicalExaminationInfo> queryUPEIByUserIdAndId(int userId, int projectId) {

        List<UserPhysicalExaminationInfo> upeis = new ArrayList<>();

        try {
            Where<UserPhysicalExaminationInfo, Integer> where = userPhyExamDao.queryBuilder()
                    .orderBy("exam_time", true)
                    .where().eq("user_id", userId)
                    .and().eq("project_id", projectId);
            upeis.addAll(where.query());
        } catch (SQLException e) {
            Log.d(TAG, "queryUPEIByUserIdAndId: 查询失败");
            e.printStackTrace();
        }
        return upeis;
    }

    /**
     * 查询用户Id 在 startTime 到 endTime 时间内的体检记录
     *
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    public List<UserPhysicalExaminationInfo> queryUPEByUserId(int userId, String startTime, String endTime) {
        List<UserPhysicalExaminationInfo> upeis = new ArrayList<>();

        try {
            Where<UserPhysicalExaminationInfo, Integer> where = null;
            if (startTime != null && endTime != null && startTime.length() > 0 && endTime.length() > 0) {
                where = userPhyExamDao.queryBuilder()
                        .orderBy("exam_time", true)
                        .orderBy("add_time", true)
                        .orderBy("project_id", true)
                        .where().eq("user_id", userId)
                        .and()
                        .between("exam_time", startTime, endTime);
            } else {
                where = userPhyExamDao.queryBuilder()
                        .orderBy("exam_time", true)
                        .orderBy("project_id", true)
                        .where().eq("user_id", userId);
            }
            upeis.addAll(where.query());
        } catch (SQLException e) {
            Log.d(TAG, "queryUPEIByUserIdAndId: 查询失败");
            e.printStackTrace();
        }
        return upeis;
    }


}


