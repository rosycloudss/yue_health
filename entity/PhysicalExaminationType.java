package com.yue_health.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 体检项目的分类(大类) 比如 基本信息,血常规,肝肾功能,尿常规,妇科
 */

@DatabaseTable(tableName = "tb_physical_examination_type")
public class PhysicalExaminationType {


    @DatabaseField(id = true, columnName = "type_id")
    private int typeId;//体检项目号

    @DatabaseField(columnName = "type_name")
    private String typeName;//体检项目分类名称

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "PhysicalExaminationType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
