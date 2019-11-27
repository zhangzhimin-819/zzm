package mobileshop.edu.huatec.com.mobileshop.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import mobileshop.edu.huatec.com.mobileshop.R;
import mobileshop.edu.huatec.com.mobileshop.common.BaseActivity;

public class SplashActivity extends BaseActivity {

    ImageView splash_loading_item;
    @Override
    public int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        super.initView();

        splash_loading_item=findViewById(R.id.splash_loading_item);
        //使用布局文件的形式创建，使用AnimationUtils.loadingAnimation导入动画
        Animation translate= AnimationUtils.loadAnimation(this,R.anim.splash_loading);

        //给动画设置监听器，监听动画结束，动画结束之后打开AdActivity
        translate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //这里添加了一个activity转场动画，就是activity切换的动画
                startActivity(new Intent(SplashActivity.this,AdActivity.class));
                overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
                finish();
            }
        });
        //启动动画
        splash_loading_item.setAnimation(translate);
    }
}
