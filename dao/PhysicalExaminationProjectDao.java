package com.yue_health.dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.Where;
import com.yue_health.entity.PhysicalExaminationProject;
import com.yue_health.util.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 体检项目的增删改查
 */
public class PhysicalExaminationProjectDao {
    private static final String TAG = "PEPDao";

    private static PhysicalExaminationProjectDao instance;

    private Context context;

    private DatabaseHelper helper;

    private Dao<PhysicalExaminationProject, Integer> pEPIntegerDao;

    private PhysicalExaminationProjectDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            pEPIntegerDao = (Dao<PhysicalExaminationProject, Integer>) helper.getDao(PhysicalExaminationProject.class);
        } catch (SQLException e) {
            Log.d(TAG, "UserDao: 创建 UserDao 对象出现异常");
            e.printStackTrace();
        }
    }


    /**
     * PhysicalExaminationProjectDao 对象
     *
     * @param context
     * @return
     */
    public static PhysicalExaminationProjectDao getInstance(Context context) {
        if (instance == null) {
            instance = new PhysicalExaminationProjectDao(context);
        }
        return instance;
    }

    /**
     * 添加单个 PhysicalExaminationProject对象信息
     *
     * @param pep
     */
    public void add(PhysicalExaminationProject pep) {
        if (pep != null) {
            try {
                pEPIntegerDao.createOrUpdate(pep);
            } catch (SQLException e) {
                Log.d(TAG, "add: 添加体检项目" + pep.toString() + "失败");
                e.printStackTrace();
            }
        }
    }

    /**
     * 批量添加体检项目信息
     *
     * @param peps
     */
    public void add(List<PhysicalExaminationProject> peps) {
        if (peps != null && !peps.isEmpty()) {
            for (PhysicalExaminationProject pep : peps) {
                add(pep);
            }
        }
    }

    /**
     * 根据 项目Id删除项目信息
     *
     * @param pepId
     */
    public void delete(int pepId) {
        try {
            pEPIntegerDao.deleteById(pepId);
        } catch (SQLException e) {
            Log.d(TAG, "delete: 删除体检项目: " + pepId + " 失败");
            e.printStackTrace();
        }
    }

    /**
     * 修改体检项目
     *
     * @param pep
     */
    public void update(PhysicalExaminationProject pep) {
        try {
            pEPIntegerDao.update(pep);
        } catch (SQLException e) {
            Log.d(TAG, "update: 修改 体检项目:" + pep.toString() + "失败");
            e.printStackTrace();
        }
    }

    /**
     * 通过分类Id查找体检项目
     *
     * @param typeId
     * @return
     */
    public List<PhysicalExaminationProject> queryProjectByTypeId(int typeId) {
        List<PhysicalExaminationProject> peps = new ArrayList<>();
        try {
            Where<PhysicalExaminationProject, Integer> where = pEPIntegerDao.queryBuilder()
                    .where().eq("type_id", typeId);
            peps.addAll(where.query());
        } catch (SQLException e) {
            Log.d(TAG, "queryProjectByTypeId: 查询体检项目失败");
            e.printStackTrace();
        }
        return peps;
    }

    /**
     * 通过项目Id查找体检项目信息
     *
     * @param projectId
     * @return
     */
    public PhysicalExaminationProject queryProjectById(int projectId) {
        PhysicalExaminationProject pep = null;
        try {
            pep = pEPIntegerDao.queryForId(projectId);
        } catch (SQLException e) {
            Log.d(TAG, "queryProjectById: 查询体检项目失败");
            e.printStackTrace();
        }
        return pep;
    }

    /**
     * 通过项目名称查找项目信息
     *
     * @param projectName
     * @return
     */
    public List<PhysicalExaminationProject> queryProjectByName(String projectName) {
        List<PhysicalExaminationProject> peps = new ArrayList<>();
        if (projectName != null) {
            try {
                Where<PhysicalExaminationProject, Integer> where = pEPIntegerDao.queryBuilder().where()
                        .eq("project_name", projectName);
                peps.addAll(where.query());
            } catch (SQLException e) {
                Log.d(TAG, "queryProjectByTypeId: 查询" + projectName + "项目失败");
                e.printStackTrace();
            }
        }
        return peps;
    }


}
