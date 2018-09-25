package com.yue_health.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 用户体检项目信息
 */
@DatabaseTable(tableName = "tb_user_physical_examination_info")
public class UserPhysicalExaminationInfo {

    @DatabaseField(generatedId = true, columnName = "id")
    private int id;

    @DatabaseField(columnName = "project_id")
    private int projectId;

    @DatabaseField(columnName = "user_id")
    private int userId;

    @DatabaseField(columnName = "data")
    private float data; //体检数值

    @DatabaseField(columnName = "add_time")
    private long addTime;//体检表生成时间

    @DatabaseField(columnName = "times")
    private int times;//体检次数

    @DatabaseField(columnName = "exam_time")
    private String examTime;//体检时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getData() {
        return data;
    }

    public void setData(float data) {
        this.data = data;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    @Override
    public String toString() {
        return "UserPhysicalExaminationInfo{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", userId=" + userId +
                ", data=" + data +
                ", addTime=" + addTime +
                ", times=" + times +
                ", examTime='" + examTime + '\'' +
                '}';
    }
}
