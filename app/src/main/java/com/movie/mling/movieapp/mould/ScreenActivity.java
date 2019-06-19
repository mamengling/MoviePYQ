package com.movie.mling.movieapp.mould;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.ScreenAdapter;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.iactivityview.ScreenActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.ScreenBean;
import com.movie.mling.movieapp.presenter.ScreenActivityPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

public class ScreenActivity extends BaseCompatActivity implements View.OnClickListener, ScreenActivityView {
    private ScreenActivityPresenter mPresenter;
    private RecyclerView recycler_view;
    private TextView title_bar_back;
    private TextView title_bar_right;
    private ScreenAdapter mAdapter;
    private ScreenBean CallBackVo;
    private ScreenBean.labelBean top;
    private List<ScreenBean.labelBean> mListNan;
    private List<ScreenBean.labelBean> mListNv;
    private List<ScreenBean.labelBean> mList;
    private ArrayList<ScreenBean.labelBean> mListTop;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mPresenter = new ScreenActivityPresenter(this);
        titleBar.setVisibility(View.GONE);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_sreen;
    }

    @Override
    protected void onCreateViewContent(View view) {
        recycler_view = view.findViewById(R.id.recycler_view);
        title_bar_back = view.findViewById(R.id.title_bar_back);
        title_bar_right = view.findViewById(R.id.title_bar_right);
    }

    @Override
    protected void getExras() {
    }

    @Override
    protected void setListener() {
        top = new ScreenBean.labelBean();
        top.setViewType(102);
        top.setSizeView(4);

        title_bar_back.setOnClickListener(this);
        title_bar_right.setOnClickListener(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mAdapter.getList().get(position).getSizeView();
            }
        });
        recycler_view.setLayoutManager(layoutManager);
        mAdapter = new ScreenAdapter(this);
        recycler_view.setAdapter(mAdapter);
        mAdapter.setOnItemOnclickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, String content) {
                switch (ClickType) {
                    case 1:
                        mAdapter.getList().removeAll(mListNv);
                        mAdapter.getList().addAll(mListNan);
                        mAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        mAdapter.getList().removeAll(mListNan);
                        mAdapter.getList().addAll(mListNv);
                        mAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
        mPresenter.getLableList();
    }

    @Override
    protected void fromNetGetData() {

    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                break;
            case R.id.title_bar_right:
                break;
        }
    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(Constants.APP_USER_SERVICE_SEARCH_STARCATE);
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

    }

    @Override
    public void excuteSuccessCallBack(ScreenBean userVo) {
        CallBackVo = userVo;
        mList = new ArrayList<>();
        if (CallBackVo.getData() != null && CallBackVo.getData().getList() != null) {
            if (CallBackVo.getData().getList().getTechang() != null && CallBackVo.getData().getList().getTechang().size() > 0) {

                mListTop = new ArrayList<>();
                mListTop.add(top);

                ScreenBean.labelBean titleTchang = new ScreenBean.labelBean();
                titleTchang.setViewType(101);
                titleTchang.setSizeView(4);
                titleTchang.setName("特长");
                mListTop.add(titleTchang);
                for (int i = 0; i < CallBackVo.getData().getList().getTechang().size(); i++) {
                    CallBackVo.getData().getList().getTechang().get(i).setSizeView(1);
                    mListTop.add(CallBackVo.getData().getList().getTechang().get(i));
                }

                ScreenBean.labelBean titleFge = new ScreenBean.labelBean();
                titleFge.setViewType(101);
                titleFge.setSizeView(4);
                titleFge.setName("风格");
                mListTop.add(titleFge);
                for (int i = 0; i < CallBackVo.getData().getList().getFengge().size(); i++) {
                    CallBackVo.getData().getList().getFengge().get(i).setSizeView(1);
                    mListTop.add(CallBackVo.getData().getList().getFengge().get(i));
                }

                ScreenBean.labelBean titleType = new ScreenBean.labelBean();
                titleType.setViewType(101);
                titleType.setSizeView(4);
                titleType.setName("角色类型");
                mListTop.add(titleType);

                for (int i = 0; i < CallBackVo.getData().getList().getLeixing().size(); i++) {
                    CallBackVo.getData().getList().getLeixing().get(i).setSizeView(1);
                    mListTop.add(CallBackVo.getData().getList().getLeixing().get(i));
                }

                ScreenBean.labelBean shencai_male = new ScreenBean.labelBean();
                shencai_male.setViewType(101);
                shencai_male.setSizeView(4);
                shencai_male.setName("身材");
                mListTop.add(shencai_male);

                /**
                 * 男
                 */
                mListNan = new ArrayList<>();
                for (int i = 0; i < CallBackVo.getData().getList().getShencai_male().size(); i++) {
                    CallBackVo.getData().getList().getShencai_male().get(i).setSizeView(1);
                    mListNan.add(CallBackVo.getData().getList().getShencai_male().get(i));
                }


                ScreenBean.labelBean shijue_male = new ScreenBean.labelBean();
                shijue_male.setViewType(101);
                shijue_male.setSizeView(4);
                shijue_male.setName("视觉感");
                mListNan.add(shijue_male);


                for (int i = 0; i < CallBackVo.getData().getList().getShijue_male().size(); i++) {
                    CallBackVo.getData().getList().getShijue_male().get(i).setSizeView(1);
                    mListNan.add(CallBackVo.getData().getList().getShijue_male().get(i));
                }


                ScreenBean.labelBean dingwei_male = new ScreenBean.labelBean();
                dingwei_male.setViewType(101);
                dingwei_male.setSizeView(4);
                dingwei_male.setName("角色定位");
                mListNan.add(dingwei_male);

                for (int i = 0; i < CallBackVo.getData().getList().getDingwei_male().size(); i++) {
                    CallBackVo.getData().getList().getDingwei_male().get(i).setSizeView(1);
                    mListNan.add(CallBackVo.getData().getList().getDingwei_male().get(i));
                }


                /**
                 * 女
                 */
                mListNv = new ArrayList<>();
                for (int i = 0; i < CallBackVo.getData().getList().getShencai_female().size(); i++) {
                    CallBackVo.getData().getList().getShencai_female().get(i).setSizeView(1);
                    mListNv.add(CallBackVo.getData().getList().getShencai_female().get(i));
                }

                ScreenBean.labelBean shijue_female = new ScreenBean.labelBean();
                shijue_female.setViewType(101);
                shijue_female.setSizeView(4);
                shijue_female.setName("视觉感");
                mListNv.add(shijue_female);
                for (int i = 0; i < CallBackVo.getData().getList().getShijue_female().size(); i++) {
                    CallBackVo.getData().getList().getShijue_female().get(i).setSizeView(1);
                    mListNv.add(CallBackVo.getData().getList().getShijue_female().get(i));
                }

                ScreenBean.labelBean dingwei_female = new ScreenBean.labelBean();
                dingwei_female.setViewType(101);
                dingwei_female.setSizeView(4);
                dingwei_female.setName("角色定位");
                mListNv.add(dingwei_female);
                for (int i = 0; i < CallBackVo.getData().getList().getDingwei_female().size(); i++) {
                    CallBackVo.getData().getList().getDingwei_female().get(i).setSizeView(1);
                    mListNv.add(CallBackVo.getData().getList().getDingwei_female().get(i));
                }


                /**
                 * 处理男角色数据
                 */
                mList.addAll(mListTop);
                mList.addAll(mListNan);
            }
        }
        mAdapter.onReference(mList);
    }


    /**
     * 展示男
     */
    private void showNan() {
        List<ScreenBean.labelBean> mList = new ArrayList<>();
        if (CallBackVo.getData() != null && CallBackVo.getData().getList() != null) {
            if (CallBackVo.getData().getList().getTechang() != null && CallBackVo.getData().getList().getTechang().size() > 0) {
                mList.add(top);
                ScreenBean.labelBean titleTchang = new ScreenBean.labelBean();
                titleTchang.setViewType(101);
                titleTchang.setSizeView(4);
                titleTchang.setName("特长");
                mList.add(titleTchang);
                for (int i = 0; i < CallBackVo.getData().getList().getTechang().size(); i++) {
                    CallBackVo.getData().getList().getTechang().get(i).setSizeView(1);
                    mList.add(CallBackVo.getData().getList().getTechang().get(i));
                }


                ScreenBean.labelBean titleFge = new ScreenBean.labelBean();
                titleFge.setViewType(101);
                titleFge.setSizeView(4);
                titleFge.setName("风格");
                mList.add(titleFge);
                for (int i = 0; i < CallBackVo.getData().getList().getFengge().size(); i++) {
                    CallBackVo.getData().getList().getFengge().get(i).setSizeView(1);
                    mList.add(CallBackVo.getData().getList().getFengge().get(i));
                }

                ScreenBean.labelBean titleType = new ScreenBean.labelBean();
                titleType.setViewType(101);
                titleType.setSizeView(4);
                titleType.setName("角色类型");
                mList.add(titleType);
                for (int i = 0; i < CallBackVo.getData().getList().getLeixing().size(); i++) {
                    CallBackVo.getData().getList().getLeixing().get(i).setSizeView(1);
                    mList.add(CallBackVo.getData().getList().getLeixing().get(i));
                }


                ScreenBean.labelBean shencai_male = new ScreenBean.labelBean();
                shencai_male.setViewType(101);
                shencai_male.setSizeView(4);
                shencai_male.setName("身材");
                mList.add(shencai_male);
                for (int i = 0; i < CallBackVo.getData().getList().getShencai_male().size(); i++) {
                    CallBackVo.getData().getList().getShencai_male().get(i).setSizeView(1);
                    mList.add(CallBackVo.getData().getList().getShencai_male().get(i));
                }


                ScreenBean.labelBean shijue_male = new ScreenBean.labelBean();
                shijue_male.setViewType(101);
                shijue_male.setSizeView(4);
                shijue_male.setName("视觉感");
                mList.add(shijue_male);
                for (int i = 0; i < CallBackVo.getData().getList().getShijue_male().size(); i++) {
                    CallBackVo.getData().getList().getShijue_male().get(i).setSizeView(1);
                    mList.add(CallBackVo.getData().getList().getShijue_male().get(i));
                }

                ScreenBean.labelBean dingwei_male = new ScreenBean.labelBean();
                dingwei_male.setViewType(101);
                dingwei_male.setSizeView(4);
                dingwei_male.setName("角色定位");
                mList.add(dingwei_male);
                for (int i = 0; i < CallBackVo.getData().getList().getDingwei_male().size(); i++) {
                    CallBackVo.getData().getList().getDingwei_male().get(i).setSizeView(1);
                    mList.add(CallBackVo.getData().getList().getDingwei_male().get(i));
                }
            }
        }
        mAdapter.onReference(mList);
    }

    /**
     * 展示女
     */
    private void showNv() {
        List<ScreenBean.labelBean> mList = new ArrayList<>();
        mList.add(top);
        if (CallBackVo.getData() != null && CallBackVo.getData().getList() != null) {
            if (CallBackVo.getData().getList().getTechang() != null && CallBackVo.getData().getList().getTechang().size() > 0) {
                ScreenBean.labelBean titleTchang = new ScreenBean.labelBean();
                titleTchang.setViewType(101);
                titleTchang.setSizeView(4);
                titleTchang.setName("特长");
                mList.add(titleTchang);
                for (int i = 0; i < CallBackVo.getData().getList().getTechang().size(); i++) {
                    CallBackVo.getData().getList().getTechang().get(i).setSizeView(1);
                    mList.add(CallBackVo.getData().getList().getTechang().get(i));
                }


                ScreenBean.labelBean titleFge = new ScreenBean.labelBean();
                titleFge.setViewType(101);
                titleFge.setSizeView(4);
                titleFge.setName("风格");
                mList.add(titleFge);
                for (int i = 0; i < CallBackVo.getData().getList().getFengge().size(); i++) {
                    CallBackVo.getData().getList().getFengge().get(i).setSizeView(1);
                    mList.add(CallBackVo.getData().getList().getFengge().get(i));
                }

                ScreenBean.labelBean titleType = new ScreenBean.labelBean();
                titleType.setViewType(101);
                titleType.setSizeView(4);
                titleType.setName("角色类型");
                mList.add(titleType);
                for (int i = 0; i < CallBackVo.getData().getList().getLeixing().size(); i++) {
                    CallBackVo.getData().getList().getLeixing().get(i).setSizeView(1);
                    mList.add(CallBackVo.getData().getList().getLeixing().get(i));
                }


                ScreenBean.labelBean shencai_female = new ScreenBean.labelBean();
                shencai_female.setViewType(101);
                shencai_female.setSizeView(4);
                shencai_female.setName("身材");
                mList.add(shencai_female);
                for (int i = 0; i < CallBackVo.getData().getList().getShencai_female().size(); i++) {
                    CallBackVo.getData().getList().getShencai_female().get(i).setSizeView(1);
                    mList.add(CallBackVo.getData().getList().getShencai_female().get(i));
                }

                ScreenBean.labelBean shijue_female = new ScreenBean.labelBean();
                shijue_female.setViewType(101);
                shijue_female.setSizeView(4);
                shijue_female.setName("视觉感");
                mList.add(shijue_female);
                for (int i = 0; i < CallBackVo.getData().getList().getShijue_female().size(); i++) {
                    CallBackVo.getData().getList().getShijue_female().get(i).setSizeView(1);
                    mList.add(CallBackVo.getData().getList().getShijue_female().get(i));
                }

                ScreenBean.labelBean dingwei_female = new ScreenBean.labelBean();
                dingwei_female.setViewType(101);
                dingwei_female.setSizeView(4);
                dingwei_female.setName("角色定位");
                mList.add(dingwei_female);
                for (int i = 0; i < CallBackVo.getData().getList().getDingwei_female().size(); i++) {
                    CallBackVo.getData().getList().getDingwei_female().get(i).setSizeView(1);
                    mList.add(CallBackVo.getData().getList().getDingwei_female().get(i));
                }
            }
        }

        mAdapter.onReference(mList);
    }
}
