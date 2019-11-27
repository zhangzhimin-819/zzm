package mobileshop.edu.huatec.com.mobileshop.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mobileshop.edu.huatec.com.mobileshop.R;
import mobileshop.edu.huatec.com.mobileshop.common.BaseFragment;

public class CartFragment extends BaseFragment {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cart_fragment);
//    }



    @Override
    protected int gteContentViewId() {
        return R.layout.fragment_cart;
    }
}
