package mobileshop.edu.huatec.com.mobileshop.fragment;

import android.graphics.Bitmap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.OnClick;
import mobileshop.edu.huatec.com.mobileshop.R;
import mobileshop.edu.huatec.com.mobileshop.common.BaseFragment;
import mobileshop.edu.huatec.com.mobileshop.view.MyWebView;

public class HomeFragment extends BaseFragment {

    private static final String TAG="HomeFragment";

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.webView)
    MyWebView mWebView;

    @OnClick(R.id.home_title_search)
    void search(){
        toastShort("开发中....");
    }

    @Override
    protected int gteContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        //初始化WebView
        initWebView();

        initSwipeRefreshLayout();

    }


    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,android.R.color.holo_green_light,
                android.R.color.holo_orange_light,android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mWebView.reload();
            }
        });
    }

    private void initWebView() {
        //设置无垂直方向的scrollbar
        mWebView.setVerticalFadingEdgeEnabled(false);
        //设置无水平方向的scrollbar
        mWebView.setHorizontalScrollBarEnabled(false);

        WebSettings settings=mWebView.getSettings();
        settings.setJavaScriptEnabled(true);//支持js脚本
        settings.setSupportZoom(false);//支持缩放
        settings.setBuiltInZoomControls(false);

        mWebView.setOnCustomScrollChanged(new MyWebView.IWebViewScroll(){

            @Override
            public void onTop() {
                mSwipeRefreshLayout.setEnabled(true);
            }

            @Override
            public void notOnTop() {
                mSwipeRefreshLayout.setEnabled(false);
            }
        });

        //点击后退按钮，让WebView后退
        mWebView.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction()==KeyEvent.ACTION_DOWN){
                    if (keyCode==KeyEvent.KEYCODE_BACK && mWebView.canGoBack()){
                        mWebView.goBack();
                        return true;
                    }
                }
                return true;
            }
        });
        mWebView.setWebViewClient(new WebViewClient(){
            //当点击链接时，希望覆盖而不是打开浏览器窗口


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mSwipeRefreshLayout.setRefreshing(true);
                Log.e(TAG,"onPageStarted");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mSwipeRefreshLayout.setRefreshing(false);
                Log.e(TAG,"onPageFinished");
            }
        });
        //加载ur
        mWebView.loadUrl("https://www.apple.com.cn/cn-k12/shop");
    }
}
