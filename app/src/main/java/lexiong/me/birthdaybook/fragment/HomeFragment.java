package lexiong.me.birthdaybook.fragment;

import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import lexiong.me.birthdaybook.R;
import lexiong.me.birthdaybook.base.BaseFragment;
import lexiong.me.birthdaybook.databinding.FragBaseBinding;
import lexiong.me.birthdaybook.databinding.FragmentHomeBinding;
import lexiong.me.birthdaybook.fragment.PersonBirthdayInfoFragment;

/**
 * Created by yueli on 2018/4/24.
 * lexiong.me.birthdaybook.base
 */

public class HomeFragment extends BaseFragment {
    public static final String TAG = HomeFragment.class.getSimpleName();
    private Button btn;
    private OnButtonClick onButtonClick;//2、定义接口成员变量
    private String title;
    FragmentHomeBinding binding;

    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        binding.personName.setText(getBirthdayPersonName());
        binding.message.setText(title);
        btn = binding.buttonViewDetail;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //4、如果接口成员变量不为空null，则调用接口变量的方法。
                if(onButtonClick!=null){
                    onButtonClick.onClick(btn);
                }
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(
                        R.id.root_frame,
                        PersonBirthdayInfoFragment.newInstance(binding.personName.getText().toString()),
                        PersonBirthdayInfoFragment.TAG
                );
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    protected void afterCreateView(View view) {
        binding = DataBindingUtil.bind(view);
        //获取Activity中赋值的参数
        title = getArguments().getString("homeTitle");
    }

    public String getBirthdayPersonName(){
        String personName;
        //todo 读取本地库
        personName = "熊宝";
        return personName;
    }


    //定义接口变量的get方法
    public OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }
    //定义接口变量的set方法
    public void setOnButtonClick(OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }
    //1、定义接口
    public interface OnButtonClick{
        void onClick(View view);
    }
}
