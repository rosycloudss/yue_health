package com.yue_health.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.yue_health.dao.PhysicalExaminationProjectDao;
import com.yue_health.dao.UserDao;
import com.yue_health.dao.UserPhyExamInfoDao;
import com.yue_health.entity.PhysicalExaminationProject;
import com.yue_health.entity.User;
import com.yue_health.entity.UserPhysicalExaminationInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 初始化app
 */
public class AppInit {

    private static final String TAG = "AppInit";
    private Context context;//上下文


    public AppInit(Context context) {
        this.context = context;
    }

    /**
     * 修改App运行信息
     */
    public void updateAppInfo() {

        if (getAppRunTime() == 0) {
            addPhyExamProject();
            addUserRecords();
            addUser();
        }
        updateAppRunTime();

    }

    /**
     * 获取App运行次数
     *
     * @return
     */
    public int getAppRunTime() {
        SharedPreferences preferences = context.getSharedPreferences("app_run_times", Context.MODE_PRIVATE);
        return preferences.getInt("run_times", 0);
    }

    /**
     * 修改App运行次数
     */
    public void updateAppRunTime() {
        SharedPreferences.Editor editor = context.getSharedPreferences("app_run_times",
                Context.MODE_PRIVATE).edit();
        editor.putInt("run_times", getAppRunTime() + 1);
        editor.apply();
    }

    /**
     * 添加体检项目到数据库中
     */
    public void addPhyExamProject() {
        PhysicalExaminationProjectDao pepd = PhysicalExaminationProjectDao.getInstance(context);
        List<PhysicalExaminationProject> projects = new ArrayList<>();

        //基本信息
        String[] baseInfo = new String[]{"身高", "体重", "收缩压", "舒张压", "甘油三酯(TG)", "空腹血糖(GLU)", "癌胚抗原(CEA)"};
        int baseInfoId = 1001;
        for (String str : baseInfo) {
            PhysicalExaminationProject pep = new PhysicalExaminationProject(baseInfoId++, 1, str, 0);
            projects.add(pep);
        }

        int bloodId = 2001;
        //血常规
        String[] blood = new String[]{"大血小板比率(P-LCR)", "血红蛋白浓度(Hb)", "红细胞比容(HCT)", "淋巴细胞百分比(LYMPH%)", "血小板压积(PCT)"};
        for (String str : blood) {
            PhysicalExaminationProject pep = new PhysicalExaminationProject(bloodId++, 2, str, 0);
            projects.add(pep);
        }

        int kidneyId = 3001;
        //肝肾功能
        String[] kidney = new String[]{"谷丙转氨酶(ALT)", "谷草转氨酶(AST)", "谷草/谷丙(AST/ALT)", "肌酣(Cr)", "尿素氮"};
        for (String str : kidney) {
            PhysicalExaminationProject pep = new PhysicalExaminationProject(kidneyId++, 3, str, 0);
            projects.add(pep);
        }

        int urineId = 4001;
        //尿常规
        String[] urine = new String[]{"尿酸碱度(pH)", "尿葡萄糖(GLU)", "尿比重(SG)", "尿蛋白(PRO)", "尿酮体(KET)"};
        for (String str : urine) {
            PhysicalExaminationProject pep = new PhysicalExaminationProject(urineId++, 4, str, 0);
            projects.add(pep);
        }

        int gynaecologyId = 5001;
        //妇科
        String[] gynaecology = new String[]{"外阴", "阴道", "子宫", "宫颈", "双侧乳腺"};
        for (String str : gynaecology) {
            PhysicalExaminationProject pep = new PhysicalExaminationProject(gynaecologyId++, 5, str, 2);
            projects.add(pep);
        }

        //添加项目信息到数据库中
        pepd.add(projects);

        Log.d(TAG, "addPhyExamProject: " + projects);


    }


    /**
     * 添加一个默认用户
     */
    public void addUser() {
        User user = new User();
        UserDao userDao = UserDao.getInstance(context);

        user.setPhone("17803878845");
        user.setPassword("123456");
        user.setOccupation("学生");
        user.setBirthday("1997-10");
        user.setNickname("rosycloud");
//        user.setAge(20);
        user.setEmail("1759840027@qq.com");
        user.setGender(1);
        userDao.createUser(user);
        Log.d(TAG, "addUser: " + user);
    }

    public void addUserRecords() {
        List<UserPhysicalExaminationInfo> upeInfos = new ArrayList<>();
        int userId = 1;
        for (int i = 0; i < 20; i++) {
            UserPhysicalExaminationInfo upeInfo = new UserPhysicalExaminationInfo();
            upeInfo.setProjectId(1001);
            upeInfo.setUserId(1);
            upeInfo.setData((float) (Math.random() * 100 + 100));
            upeInfo.setExamTime("2018-09-23");
            upeInfo.setAddTime(new Date().getTime());
            upeInfos.add(upeInfo);
        }

        UserPhyExamInfoDao upeiDao = UserPhyExamInfoDao.getInstance(context);
        upeiDao.add(upeInfos);
        Log.d(TAG, "addUserRecords: " + upeInfos);

    }


}
