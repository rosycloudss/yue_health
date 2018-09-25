package com.yue_health.entity;

import android.widget.TextView;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;
import com.yue_health.dao.PhysicalExaminationProjectDao;

import java.util.ArrayList;
import java.util.List;

@SmartTable(name = "用户体检列表")
public class TableItem {

    @SmartColumn(id = 1, name = "体检时间")
    private String examTime = "";

    @SmartColumn(id = 2, name = "项目名称")
    private String projectName = "";

    @SmartColumn(id = 3, name = "体检数值")
    private String value = "";

    @SmartColumn(id = 4, name = "正常值")
    private String valueRange = "";

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueRange() {
        return valueRange;
    }

    public void setValueRange(String valueRange) {
        this.valueRange = valueRange;
    }

    @Override
    public String toString() {
        return "TableItem{" +
                "examTime='" + examTime + '\'' +
                ", projectName='" + projectName + '\'' +
                ", value='" + value + '\'' +
                ", valueRange='" + valueRange + '\'' +
                '}';
    }

    /**
     * 将用户体检信息转换为表格相对应的格式
     *
     * @param upeis
     * @param pepDao
     * @return
     */
    public static List<TableItem> toTableItem(List<UserPhysicalExaminationInfo> upeis, PhysicalExaminationProjectDao pepDao) {
        List<TableItem> tableItems = new ArrayList<>();
        if (upeis != null && !upeis.isEmpty()) {
            for (UserPhysicalExaminationInfo upei : upeis) {
                TableItem tableItem = new TableItem();
                PhysicalExaminationProject pep = pepDao.queryProjectById(upei.getProjectId());
                tableItem.setExamTime(upei.getExamTime());
                if (pep != null) {
                    tableItem.setValue(upei.getData() + pep.getUnit());
                    tableItem.setProjectName(pep.getName());
                    tableItem.setValueRange(pep.getMinData() + "~" + pep.getMinData() + pep.getUnit());
                } else {
                    tableItem.setValue(upei.getData() + "");
                }
                tableItems.add(tableItem);
            }
        }
        return tableItems;
    }
}
