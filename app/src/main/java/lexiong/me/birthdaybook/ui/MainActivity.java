package lexiong.me.birthdaybook.ui;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lexiong.me.birthdaybook.R;
import lexiong.me.birthdaybook.base.BaseFragment;
import lexiong.me.birthdaybook.base.dummy.DummyContent;
import lexiong.me.birthdaybook.fragment.HomeFragment;
import lexiong.me.birthdaybook.databinding.ActivityMainBinding;
import lexiong.me.birthdaybook.fragment.PersonBirthdayInfoFragment;
import lexiong.me.birthdaybook.fragment.RecordFragment;
import lexiong.me.birthdaybook.fragment.RootFragment;
import lexiong.me.birthdaybook.fragment.SettingFragment;

public class MainActivity extends AppCompatActivity implements PersonBirthdayInfoFragment.OnFragmentInteractionListener,
        RecordFragment.OnListFragmentInteractionListener,SettingFragment.OnFragmentInteractionListener {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding bind;
    private VpAdapter adapter;


    //1、实例化类对象
//    private HomeFragment homeFragment = new HomeFragment();
    //collections
    private List<Fragment> fragments;// used for ViewPager adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test_bottom_nav_view_ex);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initData(savedInstanceState);
        initEvent();



        /**
         *  todo 测试代码
         */
        /*
        //2、调用对象的set方法，回传接口对象
        homeFragment.setOnButtonClick(new HomeFragment.OnButtonClick() {
            //3、实现接口对象的方法，
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PersonBirthdayInfoFragment fragment = new PersonBirthdayInfoFragment();
                fragmentTransaction.replace(R.id.fragmentHome, fragment);
                fragmentTransaction.commit();
            }
        });*/
    }

    private void initData(Bundle savedInstanceState) {
        fragments = new ArrayList<>(3);

        HomeFragment homeFragment = null;
        RecordFragment recordFragment =null;
        SettingFragment settingFragment =null;

        if(savedInstanceState!=null){
            homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG);
            recordFragment = (RecordFragment) getSupportFragmentManager().findFragmentByTag(RecordFragment.TAG);
            settingFragment = (SettingFragment) getSupportFragmentManager().findFragmentByTag(SettingFragment.TAG);
        }
        if(homeFragment == null){
            homeFragment = HomeFragment.newInstance();
            recordFragment = RecordFragment.newInstance(1);
            settingFragment = SettingFragment.newInstance("","");
        }
        // create music fragment and add it
        Bundle bundle;

        // create backup fragment and add it
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.backup));
        recordFragment.setArguments(bundle);

        // create friends fragment and add it
        bundle = new Bundle();
        bundle.putString("title", getString(R.string.friends));
        settingFragment.setArguments(bundle);

        // add to fragments for adapter
        fragments.add(homeFragment);
        fragments.add(recordFragment);
        fragments.add(settingFragment);

        // set adapter
        adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        bind.viewpager.setAdapter(adapter);

        // binding with ViewPager
        bind.mainbottomnav.setupWithViewPager(bind.viewpager);
    }

    private void initEvent() {
        // set listener to do something then item selected
        bind.mainbottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d(TAG, item.getItemId() + " item was selected-------------------");
                // you can return false to cancel select
                return true;
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    /**
     * view pager adapter
     */
    private static class VpAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        public VpAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0)
                return new RootFragment();
            else
                return data.get(position);
//            return data.get(position);
        }
    }

    public void showList(View view){
        Intent intent = new Intent(this, ListActivity.class);
        TextView testView = (TextView) findViewById(R.id.message);
        String message = testView.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
