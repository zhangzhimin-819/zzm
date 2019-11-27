package mobileshop.edu.huatec.com.mobileshop.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.ButterKnife;
import mobileshop.edu.huatec.com.mobileshop.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        initView();
        initData();
    }
    protected void initView(){

    }
    protected void initData(){

    }

    public abstract int getContentViewId();

    public void toastShort(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    public void toastLong(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
