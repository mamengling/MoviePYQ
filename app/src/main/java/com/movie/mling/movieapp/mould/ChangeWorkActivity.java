package com.movie.mling.movieapp.mould;

import android.content.Intent;
import android.view.View;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.StringTagAdapter;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.iactivityview.ChangeWorkActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.WorkBean;
import com.movie.mling.movieapp.presenter.ChangeWorkActivityPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import zhouyou.flexbox.interfaces.OnFlexboxSubscribeListener;
import zhouyou.flexbox.widget.TagFlowLayout;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/15 13:12
 * $DESE$
 */
public class ChangeWorkActivity extends BaseCompatActivity implements ChangeWorkActivityView {
    private ChangeWorkActivityPresenter mChangeWorkActivityPresenter;
    private StringTagAdapter adapter;
    private TagFlowLayout flowLayout;

    private List<String> sourceData = new ArrayList<>();
    private List<String> sourceDataItem;
    private List<String> selectItems = new ArrayList<>();
    private WorkBean mWorkBean;
    private String contentWork = "";

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mChangeWorkActivityPresenter = new ChangeWorkActivityPresenter(this);
        titleBar.setTitleName("修改职业");
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(ChangeWorkActivity.this);
                        break;
                }
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_change_work;
    }

    @Override
    protected void onCreateViewContent(View view) {
        flowLayout = view.findViewById(R.id.flow_layout);
        view.findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contentWork.length() > 1) {
                    String str = contentWork.substring(0, contentWork.length() - 1);
                    Intent intent = new Intent();
                    intent.putExtra("textContent", str);
                    setResult(RESULT_OK, intent);
                    LogUtil.i("textContent",str);
                    finish();
                }else {
                    contentWork = "";
                    for (int i = 0; i < selectItems.size(); i++) {
                        contentWork += selectItems.get(i) + ",";
                    }
                    String str = contentWork.substring(0, contentWork.length() - 1);
                    Intent intent = new Intent();
                    intent.putExtra("textContent", str);
                    setResult(RESULT_OK, intent);
                    LogUtil.i("textContent",str);
                    finish();
                }
            }
        });
    }

    @Override
    protected void getExras() {
        ArrayList<String> intentList = getIntent().getStringArrayListExtra("intentList");
        if (intentList != null && intentList.size() > 0) {
            selectItems.clear();
            selectItems.addAll(intentList);
        }
    }

    @Override
    protected void setListener() {
        adapter = new StringTagAdapter(this, sourceData, selectItems);
        adapter.setOnSubscribeListener(new OnFlexboxSubscribeListener<String>() {
            @Override
            public void onSubscribe(List<String> selectedItem) {
                contentWork = "";
                for (int i = 0; i < selectedItem.size(); i++) {
                    contentWork += selectedItem.get(i) + ",";
                }
//                btnCount.setText("已选择" + selectedItem.size() + "个");
            }
        });
        flowLayout.setAdapter(adapter);
    }

    @Override
    protected void fromNetGetData() {
        mChangeWorkActivityPresenter.getUserWorkList();
    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_CATE);
        params.put("token", SharePreferenceUtil.getString(this, UserConfig.USER_TOKEN, ""));
        String timestamp = AppMethod.getSecondTimestampTwo();
        params.put("timestamp", timestamp);
        return params;
    }

    @Override
    public void showProgress() {
        MDialog.getInstance(this).showProgressDialog();
    }

    @Override
    public void closeProgress() {
        MDialog.getInstance(this).closeProgressDialog();
    }

    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {

    }

    @Override
    public void excuteSuccessCallBack(WorkBean userVo) {
        if (userVo != null && userVo.getData() != null && userVo.getData().size() > 0) {
            mWorkBean = userVo;
            sourceData.clear();
            sourceDataItem = new ArrayList<>();
            for (int i = 0; i < userVo.getData().size(); i++) {
                sourceDataItem.add(userVo.getData().get(i).getCname());
            }
            sourceData.addAll(sourceDataItem);
            adapter.notifyDataSetChanged();
        }
    }
}
