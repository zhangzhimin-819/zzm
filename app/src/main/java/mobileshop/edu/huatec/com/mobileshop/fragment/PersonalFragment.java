package mobileshop.edu.huatec.com.mobileshop.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.OnClick;
import mobileshop.edu.huatec.com.mobileshop.R;
import mobileshop.edu.huatec.com.mobileshop.activity.ChangePwdActivity;
import mobileshop.edu.huatec.com.mobileshop.activity.LoginActivity;
import mobileshop.edu.huatec.com.mobileshop.activity.MyAddressActivity;
import mobileshop.edu.huatec.com.mobileshop.activity.MyCollectActivity;
import mobileshop.edu.huatec.com.mobileshop.activity.MyOrderActivity;
import mobileshop.edu.huatec.com.mobileshop.common.BaseFragment;
import mobileshop.edu.huatec.com.mobileshop.utils.SystemConfig;

public class PersonalFragment extends BaseFragment {

    //已登录
    @BindView(R.id.personal_for_login)
    RelativeLayout personal_for_login;
    @BindView(R.id.user_img_view)
    ImageView user_img_view;
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.user_level)
    TextView user_level;

    //未登录
    @BindView(R.id.personal_for_not_login)
    RelativeLayout personal_for_not_login;

    //退出登录
    @BindView(R.id.person_logout_layout)
    RelativeLayout personal_logout_layout;

    @Override
    protected int gteContentViewId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        //初始化配置
        resetUI();
    }

    private void resetUI() {
        if(SystemConfig.isLogin()){
            //已登录，显示已登录的UI，隐藏未登录的UI
            personal_for_login.setVisibility(View.VISIBLE);
            personal_for_not_login.setVisibility(View.GONE);
            personal_logout_layout.setVisibility(View.VISIBLE);

            //显示已登录的信息
            //显示头像
            ImageLoader.getInstance().displayImage(SystemConfig.getLoginUserHead(),user_img_view);
            //显示用户名
            user_name.setText(SystemConfig.getLoginUserName());
            //显示邮箱
            user_level.setText(SystemConfig.getLoginUserEmail());
        }else{
            personal_for_login.setVisibility(View.GONE);
            personal_for_not_login.setVisibility(View.VISIBLE);
                personal_logout_layout.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.personal_login)
    void login(){
        Intent intent=new Intent(getActivity(), LoginActivity.class);
        startActivityForResult(intent,1000);
    }

    @OnClick(R.id.personal_my_order)
    void personal_my_order(){
        //我的订单
        if (SystemConfig.isLogin()){
            startActivity(new Intent(getActivity(), MyOrderActivity.class));
        }else{
            Intent intent=new Intent(getActivity(), LoginActivity.class);
            startActivityForResult(intent,1001);
        }
    }

    @OnClick(R.id.my_collect)
    void my_collect(){
        //我的收藏
        if (SystemConfig.isLogin()){
            startActivity(new Intent(getActivity(), MyCollectActivity.class));
        }else{
            Intent intent=new Intent(getActivity(), LoginActivity.class);
            startActivityForResult(intent,1002);
        }
    }

    @OnClick(R.id.my_address)
    void my_address(){
        //我的订单
        if (SystemConfig.isLogin()){
            startActivity(new Intent(getActivity(), MyAddressActivity.class));
        }else{
            Intent intent=new Intent(getActivity(), LoginActivity.class);
            startActivityForResult(intent,1003);
        }
    }

    @OnClick(R.id.my_account)
    void my_account(){
        //修改密码
        if (SystemConfig.isLogin()){
            startActivity(new Intent(getActivity(), ChangePwdActivity.class));
        }else{
            Intent intent=new Intent(getActivity(), LoginActivity.class);
            startActivityForResult(intent,1004);
        }
    }

    @OnClick(R.id.person_logout_layout)
    void logout(){
        //退出登录
        SystemConfig.logout();
        resetUI();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK){
            //重置UI
            resetUI();

            //打开登录之前想要进入的页面
            Intent intent=new Intent();
            if(requestCode==1000){

            }else if(requestCode==1001){
                intent.setClass(getActivity(),MyOrderActivity.class);
                startActivity(intent);
            }else if(requestCode==1002){
                intent.setClass(getActivity(),MyCollectActivity.class);
                startActivity(intent);
            }else if(requestCode==1003){
                intent.setClass(getActivity(),MyAddressActivity.class);
                startActivity(intent);
            }else if(requestCode==1004){
                intent.setClass(getActivity(),ChangePwdActivity.class);
                startActivity(intent);
            }

        }
    }
}
