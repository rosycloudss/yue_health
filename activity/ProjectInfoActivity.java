package com.yue_health.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;

import com.j256.ormlite.stmt.query.In;
import com.yue_health.R;
import com.yue_health.adapter.ProjectAdapter;
import com.yue_health.dao.PhysicalExaminationProjectDao;
import com.yue_health.entity.PhysicalExaminationProject;

import java.util.ArrayList;
import java.util.List;

public class ProjectInfoActivity extends BaseActivity {


    //初始化组件
    private TextView mTitleName;
    private ListView mListView;
    private EditText mSearchInputEditText;
    private ImageButton mSearchButton;
    private ImageButton mBackButton;

    private String projectName = "";
    private int typeId;

    private Context context = ProjectInfoActivity.this;
    private PhysicalExaminationProjectDao pepDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_info);

        Intent intent = getIntent();
        projectName = intent.getStringExtra("project_name");
        typeId = intent.getIntExtra("type_id", 0);
        if (typeId == 0) {
            onBackPressed();
        }
        initView();
        initEvents();
        initDatas();
    }

    @Override
    public void initView() {
        //初始化组件
        mBackButton = findViewById(R.id.id_back_file);
        mTitleName = findViewById(R.id.id_project_info_name);
        mListView = findViewById(R.id.id_listview_project_property);
        mSearchInputEditText = findViewById(R.id.id_search_input_box);
        mSearchButton = findViewById(R.id.id_search_button);
    }

    @Override
    public void initEvents() {
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.actionStart(context, 1);
                onDestroy();
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProjectAdapter.ListItemView listItemView = (ProjectAdapter.ListItemView) view.getTag();
                ProjectRecordChartActivity.actionStart(context, listItemView.pep.getProjectId(), listItemView.pep.getName());
            }
        });

    }

    private List<PhysicalExaminationProject> peps = new ArrayList<>();

    @Override
    public void initDatas() {
        peps.clear();
        pepDao = PhysicalExaminationProjectDao.getInstance(context);
        peps.addAll(pepDao.queryProjectByTypeId(typeId));

        ProjectAdapter projectAdapter = new ProjectAdapter(context, peps);
        mListView.setAdapter(projectAdapter);

        mTitleName.setText(projectName);
    }


    public static void actionStart(Context context, String projectName, int typeId) {
        Intent intent = new Intent(context, ProjectInfoActivity.class);
        intent.putExtra("project_name", projectName);
        intent.putExtra("type_id", typeId);
        context.startActivity(intent);
    }
}
