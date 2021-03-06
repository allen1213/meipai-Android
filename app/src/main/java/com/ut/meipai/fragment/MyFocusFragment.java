package com.ut.meipai.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ut.meipai.R;
import com.ut.meipai.adpter.MyFocusAdapter;
import com.ut.meipai.base.BaseFragment;
import com.ut.meipai.data.MyFocusData;
import com.ut.meipai.util.UTToast;
import com.ut.meipai.widget.RecyclerViewDivider;
import com.ut.meipai.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by 任和 on 2017/04/12 16:08
 * Function:  我的关注
 * Desc:
 */
public class MyFocusFragment extends BaseFragment {
    private static final long DELAY_MILLS = 1500;

    @BindView(R.id.pcf_refresh_focusFragment)
    PtrClassicFrameLayout mPtrClassicFrameLayout;
    @BindView(R.id.titleBar_focusFragment)
    TitleBar              mTitleBar;
    @BindView(R.id.rv_focus_focusFragment)
    RecyclerView          mRecyclerView;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        initTitleBar();
        initRefresh();
        initRecyclerView();
    }


    private void initTitleBar() {
        mTitleBar.setOnExtensionListener(new TitleBar.OnExtensionListener() {
            @Override
            public void onExtensionClick(View v) {
                UTToast.presentToast(getContext(), "找好友干啥？");
            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new RecyclerViewDivider(getContext(), LinearLayoutManager.HORIZONTAL,
                16, R.color.recyclerView_divide));
        mRecyclerView.setAdapter(new MyFocusAdapter(getContext(), MyFocusData.getSampleData()));
    }

    private void initRefresh() {
        mPtrClassicFrameLayout.disableWhenHorizontalMove(true);
        mPtrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                    }
                }, DELAY_MILLS);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new MyFocusAdapter(getContext(), MyFocusData.getSampleData()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_focus;
    }
}
