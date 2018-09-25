package com.yue_health.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 体检项目信息
 */

@DatabaseTable(tableName = "tb_physical_examination_project")
public class PhysicalExaminationProject {

    @DatabaseField(id = true, columnName = "project_id")
    private int projectId;//体检项目号

    @DatabaseField(columnName = "type_id")
    private int typeId;//隶属分类Id

    @DatabaseField(columnName = "project_name")
    private String name = "";//体检项目名称

    @DatabaseField(columnName = "project_gender")
    private int gender = 0;//适用性别 0.通用,1.男,2.女

    @DatabaseField(columnName = "unit")
    private String unit = "";//单位

    @DatabaseField(columnName = "min_data")
    private float minData;//正常最小值

    @DatabaseField(columnName = "max_data")
    private float maxData;//正常最大值

    public PhysicalExaminationProject(int projectId, int typeId, String name, int gender) {
        this.projectId = projectId;
        this.typeId = typeId;
        this.name = name;
        this.gender = gender;
    }

    public PhysicalExaminationProject() {
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public float getMinData() {
        return minData;
    }

    public void setMinData(float minData) {
        this.minData = minData;
    }

    public float getMaxData() {
        return maxData;
    }

    public void setMaxData(float maxData) {
        this.maxData = maxData;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "PhysicalExaminationProject{" +
                "projectId=" + projectId +
                ", typeId=" + typeId +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", unit='" + unit + '\'' +
                ", minData=" + minData +
                ", maxData=" + maxData +
                '}';
    }
}
