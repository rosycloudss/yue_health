package com.yue_health.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.yue_health.R;
import com.yue_health.fragment.HomeFrament;
import com.yue_health.fragment.MyFragment;
import com.yue_health.fragment.RecordsFragment;
import com.yue_health.fragment.SpecialistFragment;

import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {


    private ViewPager mViewPager;

    //适配器
    private FragmentPagerAdapter mAdapter;
    //装载Fragment的集合
    private List<Fragment> mFragments;

    //底部导航栏
    private LinearLayout mTabHome;
    private LinearLayout mTabFile;
    private LinearLayout mTabSpecialist;
    private LinearLayout mTabMy;

    //底部导航栏图片
    private ImageButton mTabHomeImg;
    private ImageButton mTabFileImg;
    private ImageButton mTabSpecialistImg;
    private ImageButton mTabMyImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//初始化组件
        initDatas();//初始化数据

        Intent intent = getIntent();
        int tabId = intent.getIntExtra("tabId", 0);
        selectTab(tabId);

        initEvents();//初始化事件
    }

    @Override
    public void initView() {
        mViewPager = findViewById(R.id.id_viewpager);

        //初始化底部导航栏按钮
        mTabHome = findViewById(R.id.id_tab_home);
        mTabFile = findViewById(R.id.id_tab_file);
        mTabSpecialist = findViewById(R.id.id_tab_specialist);
        mTabMy = findViewById(R.id.id_tab_my);

        //初始化底部导航栏按钮图片
        mTabHomeImg = findViewById(R.id.id_tab_home_img);
        mTabFileImg = findViewById(R.id.id_tab_file_img);
        mTabSpecialistImg = findViewById(R.id.id_tab_specialist_img);
        mTabMyImg = findViewById(R.id.id_tab_my_img);

    }

    /**
     * 为各组件绑定事件
     */
    @Override
    public void initEvents() {

        mTabHome.setOnClickListener(this);
        mTabFile.setOnClickListener(this);
        mTabSpecialist.setOnClickListener(this);
        mTabMy.setOnClickListener(this);

        //添加ViewPager的切换Tab的监听事件
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //获取ViewPager的当前Tab
                int currentItem = mViewPager.getCurrentItem();
                //将所以的ImageButton设置成灰色
                resetImgs();
                //将当前Tab对应的ImageButton设置成绿色
                switch (currentItem) {
                    case 0:
                        mTabHomeImg.setImageResource(R.mipmap.home_selected);
                        break;
                    case 1:
                        mTabFileImg.setImageResource(R.mipmap.file_selected);
                        break;
                    case 2:
                        mTabSpecialistImg.setImageResource(R.mipmap.specialist_selected);
                        break;
                    case 3:
                        mTabMyImg.setImageResource(R.mipmap.my_selected);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 初始化数据
     */
    @Override
    public void initDatas() {

        mFragments = new ArrayList<>();

        mFragments.add(new HomeFrament());
        mFragments.add(new RecordsFragment());
        mFragments.add(new SpecialistFragment());
        mFragments.add(new MyFragment());

        //初始化适配器
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {//从集合中获取对应位置的Fragment
                return mFragments.get(position);
            }

            @Override
            public int getCount() {//获取集合中Fragment的总数
                return mFragments.size();
            }

        };

        //设置ViewPager的适配器
        mViewPager.setAdapter(mAdapter);
        //设置ViewPager的切换监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            //页面滚动事件
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //页面选中事件
            @Override
            public void onPageSelected(int position) {
                //设置position对应的集合中的Fragment
                mViewPager.setCurrentItem(position);
                resetImgs();
                selectTab(position);
            }

            @Override
            //页面滚动状态改变事件
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void selectTab(int position) {
        //根据点击的Tab设置对应的ImageButton为绿色
        switch (position) {
            case 0:
                mTabHomeImg.setImageResource(R.mipmap.home_selected);
                break;
            case 1:
                mTabFileImg.setImageResource(R.mipmap.file_selected);
                break;
            case 2:
                mTabSpecialistImg.setImageResource(R.mipmap.specialist_selected);
                break;
            case 3:
                mTabMyImg.setImageResource(R.mipmap.my_selected);
                break;
        }
        //设置viewPager的当前Tab
        mViewPager.setCurrentItem(position);

    }

    @Override
    public void onClick(View view) {
        //先将四个ImageButton都设置成灰色
        resetImgs();
        switch (view.getId()) {
            case R.id.id_tab_home:
                selectTab(0);
                break;
            case R.id.id_tab_file:
                selectTab(1);
                break;
            case R.id.id_tab_specialist:
                selectTab(2);
                break;
            case R.id.id_tab_my:
                selectTab(3);
                break;
        }
    }

    /**
     * 将四个ImageButton都设置成灰色
     */
    private void resetImgs() {
        mTabHomeImg.setImageResource(R.mipmap.home);
        mTabFileImg.setImageResource(R.mipmap.file);
        mTabSpecialistImg.setImageResource(R.mipmap.specialist);
        mTabMyImg.setImageResource(R.mipmap.my);
    }

    /**
     * 启动当前界面
     *
     * @param tabId
     */
    public static void actionStart(Context context, int tabId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("tabId", tabId);
        context.startActivity(intent);
    }

    private static Boolean isQuit = false;
    private long mExitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {//
                // 如果两次按键时间间隔大于2000毫秒，则不退出
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();// 更新mExitTime
            } else {
                System.exit(0);// 否则退出程序
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
}
