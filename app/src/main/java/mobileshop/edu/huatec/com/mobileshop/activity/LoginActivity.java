package mobileshop.edu.huatec.com.mobileshop.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import mobileshop.edu.huatec.com.mobileshop.R;
import mobileshop.edu.huatec.com.mobileshop.common.BaseActivity;
import mobileshop.edu.huatec.com.mobileshop.http.ProgressDialogSubscriber;
import mobileshop.edu.huatec.com.mobileshop.http.entity.MemberEntity;
import mobileshop.edu.huatec.com.mobileshop.http.presenter.MemberPresenter;
import mobileshop.edu.huatec.com.mobileshop.utils.SystemConfig;

public class LoginActivity extends BaseActivity{

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @Override
    public int getContentViewId(){return R.layout.activity_login;}
    @OnClick(R.id.bt_login)
    void login(){
        String userName=etUsername.getText().toString().trim();
        String pwd= et_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(userName)){
            toastShort("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(userName)){
            toastShort("请输入密码");
            return;
        }
        MemberPresenter.login2(new ProgressDialogSubscriber<MemberEntity>(this) {
            @Override
            public void onNext(MemberEntity memberEntity) {
                //保存登录状态
                SystemConfig.setLogin(true);
                //弹出登录成功提示
                toastShort("登录成功");
                //保存登录账户的信息
                SystemConfig.setLoginUserName(memberEntity.uname);
                SystemConfig.setLoginUserEmail(memberEntity.email);
                SystemConfig.setLoginUserHead(memberEntity.image);
                //返回数据，只有调用了setResult,在调用的地方会回调onActivityResult方法
                setResult(RESULT_OK);
                finish();
            }
        },userName,pwd);

    }

//    private static final String TAG="LoginActivity";
//    private EditText et_username;
//    private EditText et_pwd;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        initView();
//    }
//
//    private void initView() {
//        findViewById(R.id.iv_back).setOnClickListener(this);
//        findViewById(R.id.bt_login).setOnClickListener(this);
//
//        et_username=findViewById(R.id.et_username);
//        et_pwd=findViewById(R.id.et_pwd);
//        String user_name=getIntent().getStringExtra("user_name");
//        Log.d(TAG,"user_name="+user_name);
//        et_username.setText(user_name);
//    }
//
//    @Override
//    public int getContentViewId() {
//        return 0;
//    }

//    @Override
//    public void onClick(View v) {
//        switch(v.getId()){
//            case R.id.iv_back:
//                finish();
//                break;
//            case R.id.bt_login:
//                String user_name=et_username.getText().toString();
//                String pwd=et_pwd.getText().toString();
//
//                if(TextUtils.isEmpty(user_name)){
//                    Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
//                return;
//                }
//                if(TextUtils.isEmpty(pwd)){
//                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                new AlertDialog.Builder(this)
//                        .setTitle("提示")
//                        .setMessage("用户名"+user_name+"密码："+pwd)
//                        .show();
//                break;
//        }
//    }
}
