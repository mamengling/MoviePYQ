package com.movie.mling.movieapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.ClipboardManager;
import android.view.View;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.RequestListAdapter;
import com.movie.mling.movieapp.base.BaseFragment;
import com.movie.mling.movieapp.iactivityview.NoticeFragmentView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.MsgNoticeBean;
import com.movie.mling.movieapp.mould.UserInfoActivity;
import com.movie.mling.movieapp.presenter.NoticeFragmentPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.common.DensityUtil;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.CustomButtonPhoneDialog;
import com.movie.mling.movieapp.utils.widget.RecycleViewDivider;
import com.movie.mling.movieapp.utils.widget.TitleBar;
import com.movie.mling.movieapp.utils.widget.YRecycleview;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/16 11:07
 * $DESE$
 */
public class RequestFeagment extends BaseFragment implements NoticeFragmentView {
    private NoticeFragmentPresenter mNoticeFragmentPresenter;
    private YRecycleview yrecycle_view;
    private String url;
    private String keyType;
    private String keyid;
    private String keytypeDo;
    private RequestListAdapter mAdapter;
    private int intHandler = 101;
    private int intNumberPage = 0;

    public static RequestFeagment newInstance(String url, String keyType) {

        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("keyType", keyType);
        RequestFeagment fragment = new RequestFeagment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            mNoticeFragmentPresenter.getMsgList();
        }
    }
    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mNoticeFragmentPresenter = new NoticeFragmentPresenter(this);
        url = getArguments().getString("url");
        keyType = getArguments().getString("keyType");
        titleBar.setVisibility(View.GONE);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.fragment_request;
    }

    @Override
    protected void onCreateViewContent(View view) {
        yrecycle_view = view.findViewById(R.id.yrecycle_view);
    }

    @Override
    protected void fromNetGetData() {
        mNoticeFragmentPresenter.getMsgList();
    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    protected void setListener() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        yrecycle_view.setLayoutManager(layoutManager);
        //水平分割线
        yrecycle_view.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL, DensityUtil.dip2px(getActivity(), 5), getResources().getColor(R.color.content_view_bg)));
        mAdapter = new RequestListAdapter(getActivity());
        yrecycle_view.setAdapter(mAdapter);
        yrecycle_view.setRefreshAndLoadMoreListener(new YRecycleview.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                intHandler = 101;
                intNumberPage = 0;
                mNoticeFragmentPresenter.getMsgList();
            }

            @Override
            public void onLoadMore() {
                intHandler = 102;
                intNumberPage += 1;
                mNoticeFragmentPresenter.getMsgList();
            }
        });
        mAdapter.setOnItemOnclickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, final int position, int positionChild, int ClickType, String content) {
                keyid = content;
                switch (ClickType) {
                    case 1:
                        keytypeDo = "refuse";
                        mNoticeFragmentPresenter.getMsgDo();
                        break;
                    case 2:
                        // 从API11开始android推荐使用android.content.ClipboardManager
                        // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
                        ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                        // 将文本内容放到系统剪贴板里。
                        cm.setText(mAdapter.getList().get(position).getKeytext());
                        MDialog.getInstance(getActivity()).showToast("复制成功");
                        break;
                    case 3:
                        final CustomButtonPhoneDialog dialog = new CustomButtonPhoneDialog(getActivity());
                        dialog.setButtonListener(new CustomButtonPhoneDialog.OnButtonListener() {
                            @Override
                            public void onCancleButtonClick(CustomButtonPhoneDialog var1) {
                                dialog.cancel();
                            }

                            @Override
                            public void onCopyButtonClick(CustomButtonPhoneDialog var1) {
                                dialog.cancel();
                                // 从API11开始android推荐使用android.content.ClipboardManager
                                // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
                                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                                // 将文本内容放到系统剪贴板里。
                                cm.setText(mAdapter.getList().get(position).getKeytext());
                                MDialog.getInstance(getActivity()).showToast("复制成功");
                            }

                            @Override
                            public void onSendButtonClick(CustomButtonPhoneDialog var1) {
                                dialog.cancel();
                                Uri uri2 = Uri.parse("smsto:" + mAdapter.getList().get(position).getKeytext());
                                Intent intentMessage = new Intent(Intent.ACTION_VIEW, uri2);
                                startActivity(intentMessage);
                            }

                            @Override
                            public void onCallButtonClick(CustomButtonPhoneDialog var1) {
                                dialog.cancel();
                                Uri uri = Uri.parse("tel:" + mAdapter.getList().get(position).getKeytext());
                                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                                startActivity(intent);
                            }
                        });
                        break;
                    case 4:
                        keytypeDo = "adopt";
                        mNoticeFragmentPresenter.getMsgDo();
                        break;
                    case 5:
                        Map<String, String> map = new HashMap<>();
                        map.put("user_uid", mAdapter.getList().get(position).getUid());
                        ActivityAnim.intentActivity(getActivity(), UserInfoActivity.class, map);
                        break;
                }
            }
        });
    }

    @Override
    public RequestParams getParamentersDo() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_MSG_DO);
        params.put("token", SharePreferenceUtil.getString(getActivity(), UserConfig.USER_TOKEN, ""));
        params.put("keyid", keyid);
        params.put("keytype", keytypeDo);
        return params;
    }

    @Override
    public void excuteSuccessCallBack(MsgNoticeBean mCallBackVo) {
        switch (intHandler) {
            case 101:
                if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().getList() != null && mCallBackVo.getData().getList().size() > 0) {
                    mAdapter.onReference(mCallBackVo.getData().getList());
                }
                yrecycle_view.setReFreshComplete();
                break;
            case 102:
                if (mCallBackVo.getData().getList() != null && mCallBackVo.getData().getList().size() > 0) {
                    mAdapter.addOnReference(mCallBackVo.getData().getList());
                }
                yrecycle_view.setloadMoreComplete();
                break;
        }
    }

    @Override
    public void excuteSuccessDoCallBack(CallBackVo mCallBackVo) {
        intHandler = 101;
        mNoticeFragmentPresenter.getMsgList();
    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(url);
        params.put("token", SharePreferenceUtil.getString(getActivity(), UserConfig.USER_TOKEN, ""));
        params.put("keytype", keyType);
        params.put("page", intNumberPage + "");
        return params;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void closeProgress() {

    }

    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {
        switch (intHandler) {
            case 101:
                yrecycle_view.setReFreshComplete();
                break;
            case 102:
                yrecycle_view.setloadMoreComplete();
                break;
        }
        MDialog.getInstance(getActivity()).showToast(mCallBackVo.getMessage());
    }
}
