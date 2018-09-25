package com.yue_health.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yue_health.R;
import com.yue_health.entity.PhysicalExaminationProject;

import java.util.List;

public class ProjectAdapter extends BaseAdapter {

    private List<PhysicalExaminationProject> peProjects;

    private Context context;   //运行上下文
    private LayoutInflater listContainer;     //视图容器

    public ProjectAdapter(Context context, List<PhysicalExaminationProject> peProjects) {
        this.context = context;
        listContainer = LayoutInflater.from(context);//创建视图容器并设置上下文
        this.peProjects = peProjects;
    }

    @Override
    public int getCount() {
        if (peProjects != null) {
            return peProjects.size();
        }
        return 0;

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ListItemView listItemView = null;

        if (view == null) {
            listItemView = new ListItemView();
            view = listContainer.inflate(R.layout.project_item_list, null);
            listItemView.projectName = view.findViewById(R.id.id_project_name);
            view.setTag(listItemView);
        } else {
            listItemView = (ListItemView) view.getTag();
        }

        PhysicalExaminationProject pep = peProjects.get(position);
        listItemView.pep = pep;
        listItemView.projectName.setText(pep.getName());
        return view;
    }

    public class ListItemView {
        public PhysicalExaminationProject pep;
        public TextView projectName;
    }
}
