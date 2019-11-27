package mobileshop.edu.huatec.com.mobileshop.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;




import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mobileshop.edu.huatec.com.mobileshop.R;
import mobileshop.edu.huatec.com.mobileshop.activity.GoodsListActivity;
import mobileshop.edu.huatec.com.mobileshop.adapter.CategoryLeftAdapter;
import mobileshop.edu.huatec.com.mobileshop.adapter.CategoryRightAdapter;
import mobileshop.edu.huatec.com.mobileshop.common.BaseFragment;
import mobileshop.edu.huatec.com.mobileshop.http.ProgressDialogSubscriber;
import mobileshop.edu.huatec.com.mobileshop.http.entity.CategoryEntity;
import mobileshop.edu.huatec.com.mobileshop.http.presenter.CategoryPresenter;


public class CategoryFragment extends BaseFragment {
    @BindView(R.id.rv_left)
    RecyclerView rv_left;
    @BindView(R.id.rv_right)
    RecyclerView rv_right;

    private List<CategoryEntity> leftData,rightData;
    private CategoryRightAdapter rightAdapter;
    private CategoryLeftAdapter leftAdapter;
    @Override
    protected void initData() {
        super.initData();
        //获取一级列表
        CategoryPresenter.getTopList(new ProgressDialogSubscriber<List<CategoryEntity>>(getActivity()) {
            @Override
            public void onNext(List<CategoryEntity> categoryEntities) {
                leftData.clear();
                leftData.addAll(categoryEntities);
                //刷新列表
                leftAdapter.notifyDataSetChanged();
                //左边选中的位置 刷新UI
                leftAdapter.setSelect(0);
                //加载二级列表
                loadSecondList(0);
            }
        });
    }
    private void loadSecondList(int pos){
        if(leftData==null||leftData.size()==0){
            return;
        }
        CategoryEntity entity = leftData.get(pos);
        CategoryPresenter.getSecondList(new ProgressDialogSubscriber<List<CategoryEntity>>(getActivity()) {
            @Override
            public void onNext(List<CategoryEntity> categoryEntities) {
                rightData.clear();
                rightData.addAll(categoryEntities);
                rightAdapter.notifyDataSetChanged();
            }
        },entity.getCat_id());
    }
    @Override
    protected int gteContentViewId() {
       return R.layout.fragment_category;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        leftData=new ArrayList<>();
        rightData=new ArrayList<>();
        LinearLayoutManager leftManager=new LinearLayoutManager(getActivity());
        leftManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_left.setLayoutManager(leftManager);
        GridLayoutManager rightManager=new GridLayoutManager(getActivity(),3,LinearLayoutManager.VERTICAL,false);
        rv_right.setLayoutManager(rightManager);
        leftAdapter=new CategoryLeftAdapter(getActivity(),leftData);
        leftAdapter.setOnItemClickListener(new CategoryLeftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, CategoryEntity entity) {
                leftAdapter.setSelect(position);
                loadSecondList(position);
            }
        });
        rightAdapter=new CategoryRightAdapter(getActivity(),rightData);
        rightAdapter.setOnItemClickListener(new CategoryRightAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, CategoryEntity entity) {
                Intent intent=new Intent(getActivity(), GoodsListActivity.class);
                intent.putExtra("cat_id",entity.getCat_id());
                startActivity(intent);
            }
        });
        rv_right.setAdapter(rightAdapter);
        rv_left.setAdapter(leftAdapter);
    }
    @OnClick(R.id.ll_search)
    void search(){
        toastShort("开发中");
    }
}
