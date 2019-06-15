package com.movie.mling.movieapp.mould;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;

import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.LabelListAdapter;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.iactivityview.ChangeLabelActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.LabelBean;
import com.movie.mling.movieapp.presenter.ChangeLabelActivityPresenter;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.ArrayList;

/**
 * Created by MLing on 2018/5/14 0014.
 */

public class ChangeLabelActivity extends BaseCompatActivity implements ChangeLabelActivityView {
    private ChangeLabelActivityPresenter mChangeLabelActivityPresenter;
    private ListView list_label;
    private LabelListAdapter mAdapter;

    /**
     * view RadioButton
     */
    //性别
    private CheckBox rb_nan;
    private CheckBox rb_nv;
    //国籍
    private CheckBox rb_guonei;
    private CheckBox rb_gat;
    private CheckBox rb_guowai;
    //公司
    private CheckBox rb_you;
    private CheckBox rb_wu;
    /**
     * view EditText
     */
    //年龄
    private EditText tv_xiao_nianling;
    private EditText tv_da_nianling;
    //身高
    private EditText tv_di_shengao;
    private EditText tv_gao_shengao;
    //体重
    private EditText tv_di_tizhong;
    private EditText tv_gao_tizhong;
    private ArrayList<String> techangList = new ArrayList<>();
    private ArrayList<String> tagsList = new ArrayList<>();
    private String xingbie = "";
    private String guoji = "";
    private String gongsi = "";
    private String nianling_min;
    private String nianling_max;
    private String shengao_min;
    private String shengao_max;
    private String tizhong_max;
    private String tizhong_min;
    private String techang;
    private String tags;
    private int type;
    private int typeG;
    private int typeY;

    /**
     * @param titleBar 设置标题栏
     */
    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mChangeLabelActivityPresenter = new ChangeLabelActivityPresenter(this);
        titleBar.setTitleName("筛选");
        titleBar.setRightEdit(0, "确定");
        titleBar.setShowIcon(true, true, false);
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(ChangeLabelActivity.this);
                        break;
                    case TitleBar.clickEdt:
                        changeSuccess();
                        break;
                }
            }
        });
    }


    /**
     * @return 设置页面布局
     */
    @Override
    protected int onCreateViewId() {
        return R.layout.activity_change_label;
    }

    /**
     * 设置页面布局实例化
     *
     * @param view
     */
    @Override
    protected void onCreateViewContent(View view) {
        list_label = view.findViewById(R.id.list_label);
        rb_nan = view.findViewById(R.id.rb_nan);
        rb_nv = view.findViewById(R.id.rb_nv);
        rb_guonei = view.findViewById(R.id.rb_guonei);
        rb_gat = view.findViewById(R.id.rb_gat);
        rb_guowai = view.findViewById(R.id.rb_guowai);
        rb_you = view.findViewById(R.id.rb_you);
        rb_wu = view.findViewById(R.id.rb_wu);
        tv_xiao_nianling = view.findViewById(R.id.tv_xiao_nianling);
        tv_da_nianling = view.findViewById(R.id.tv_da_nianling);
        tv_di_shengao = view.findViewById(R.id.tv_di_shengao);
        tv_gao_shengao = view.findViewById(R.id.tv_gao_shengao);
        tv_di_tizhong = view.findViewById(R.id.tv_di_tizhong);
        tv_gao_tizhong = view.findViewById(R.id.tv_gao_tizhong);
    }

    private void changeSuccess() {
        if (rb_nan.isChecked()) {
            xingbie = "男";
        }
        if (rb_nv.isChecked()) {
            xingbie = "女";
        }
        if (rb_guonei.isChecked()) {
            guoji = "国内";
        }
        if (rb_gat.isChecked()) {
            guoji = "港台";
        }
        if (rb_guowai.isChecked()) {
            guoji = "海外";
        }
        if (rb_you.isChecked()) {
            gongsi = "有";
        }
        if (rb_wu.isChecked()) {
            gongsi = "无";
        }
        if (!TextUtils.isEmpty(tv_xiao_nianling.getText().toString())) {
            nianling_min = tv_xiao_nianling.getText().toString();
        } else {
            nianling_min = "";
        }
        if (!TextUtils.isEmpty(tv_da_nianling.getText().toString())) {
            nianling_max = tv_da_nianling.getText().toString();
        } else {
            nianling_max = "";
        }
        if (!TextUtils.isEmpty(tv_di_shengao.getText().toString())) {
            shengao_min = tv_di_shengao.getText().toString();
        } else {
            shengao_min = "";
        }
        if (!TextUtils.isEmpty(tv_gao_shengao.getText().toString())) {
            shengao_max = tv_gao_shengao.getText().toString();
        } else {
            shengao_max = "";
        }
        if (!TextUtils.isEmpty(tv_di_tizhong.getText().toString())) {
            tizhong_min = tv_di_tizhong.getText().toString();
        } else {
            tizhong_min = "";
        }
        if (!TextUtils.isEmpty(tv_gao_tizhong.getText().toString())) {
            tizhong_max = tv_gao_tizhong.getText().toString();
        } else {
            tizhong_max = "";
        }
        if (techangList.size() > 0) {
            techang = techangList.toString();
            techang = techang.substring(1, techang.length() - 1);
            LogUtil.i("Onclick", "Ltitle=" + techang);
        } else {
            techang = "";
        }
        if (tagsList.size() > 0) {
            tags = tagsList.toString();
            tags = tags.substring(1, tags.length() - 1);
            LogUtil.i("Onclick", "tags=" + tags);
        } else {
            tags = "";
        }
        Intent intent = new Intent(this, SearchActorActivity.class);
        intent.putExtra("xingbie", xingbie);
        intent.putExtra("guoji", guoji);
        intent.putExtra("gongsi", gongsi);
        intent.putExtra("nianling_min", nianling_min);
        intent.putExtra("nianling_max", nianling_max);
        intent.putExtra("shengao_min", shengao_min);
        intent.putExtra("shengao_max", shengao_max);
        intent.putExtra("tizhong_min", tizhong_min);
        intent.putExtra("tizhong_max", tizhong_max);
        intent.putExtra("techang", techang);
        intent.putExtra("tags", tags);
        ActivityAnim.intentActivity(this, intent);
    }

    /**
     * 本地传参
     */
    @Override
    protected void getExras() {

    }

    /**
     * 监听事件
     */
    @Override
    protected void setListener() {
        //三个CheckBox使用同一个监听
        TypeCheckBoxListener typeCheckBoxListener = new TypeCheckBoxListener();
        rb_nan.setOnCheckedChangeListener(typeCheckBoxListener);
        rb_nv.setOnCheckedChangeListener(typeCheckBoxListener);
        TypeCheckBoxListenerG typeCheckBoxListenerg = new TypeCheckBoxListenerG();
        rb_guonei.setOnCheckedChangeListener(typeCheckBoxListenerg);
        rb_gat.setOnCheckedChangeListener(typeCheckBoxListenerg);
        rb_guowai.setOnCheckedChangeListener(typeCheckBoxListenerg);
        TypeCheckBoxListenerGS typeCheckBoxListenergs = new TypeCheckBoxListenerGS();
        rb_you.setOnCheckedChangeListener(typeCheckBoxListenergs);
        rb_wu.setOnCheckedChangeListener(typeCheckBoxListenergs);
        mChangeLabelActivityPresenter.getJsonString(this, "label.json");

    }

    /**
     * 获取数据方法，之后，View赋值
     */
    @Override
    protected void fromNetGetData() {
//        mChangeLabelActivityPresenter.getJsonString(this, "label.json");
    }

    /**
     * 获取数据方法，之后，View赋值
     */
    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    public void excuteSuccessCallBack(LabelBean userVo) {
        mAdapter = new LabelListAdapter(userVo.getMsgdate(), this);
        list_label.setAdapter(mAdapter);
        techangList.clear();
        tagsList.clear();
        mAdapter.setOnItemOnclickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, String content) {
                LogUtil.i("Onclick", "ClickType=" + ClickType + ",Ltitle=" + content);
                if (position == 0) {
                    if (ClickType == 0) {
                        techangList.add(content);
                    } else if (ClickType == 1) {
                        for (int i = 0; i < techangList.size(); i++) {
                            if (TextUtils.equals(content, techangList.get(i))) {
                                techangList.remove(content);
                            }
                        }
                    }
                    LogUtil.i("Onclick", "ClickType=" + ClickType + ",techangList=" + techangList.toString());
                }
                if (position == 1) {
                    if (ClickType == 0) {
                        tagsList.add(content);
                    } else if (ClickType == 1) {
                        for (int i = 0; i < tagsList.size(); i++) {
                            if (TextUtils.equals(content, tagsList.get(i))) {
                                tagsList.remove(content);
                            }
                        }
                    }
                    LogUtil.i("Onclick", "ClickType=" + ClickType + ",tagsList=" + tagsList.toString());
                }
            }
        });
    }

    /**
     * 获取参数
     *
     * @return
     */
    @Override
    public RequestParams getParamenters() {
        return null;
    }

    /**
     * 显示操作进度
     */
    @Override
    public void showProgress() {

    }

    /**
     * 关闭进度
     */
    @Override
    public void closeProgress() {

    }

    /**
     * 失败回调
     *
     * @param mCallBackVo
     */
    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {

    }



    /**
            * 将一组CheckBox设置同一个监听器对象
     * 在监听器中监听发生状态改变的CheckBox，经过一系列判断
     * 通过对静态全局变量赋值的形式将用户的最终选择传递出去
     *
             * 注意：由于要模拟RadioGroup+RadioButton的效果，所以要做到以下两点：
            * 1. 一组按钮只能同时选中一个
     * 2. 点击已选中的按钮要能够取消选中
     *
             * 实现要点：每次触发监听时检查是否有按钮被选中，如果没有，直接赋值然后return。如果有，判断该次触发是选中还是取消选中
     * 如果是取消选中，不执行任何逻辑。如果是选中，则赋值出去，然后将其他的按钮置false。这个时候之前选中的按钮由于被置false，
            * 状态发生改变，所以会再触发一次监听。但由于其状态是false，所以不会执行任何逻辑。这点很重要，所以前面赋值出去前一定要先检查状态
     * 如果为false，就不要再对赋值进行任何改变了。
            *
            */
    private class TypeCheckBoxListener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!rb_nan.isChecked()&&!rb_nv.isChecked()){
                type = 0;
                return;
            }
            switch (buttonView.getId()){
                case R.id.rb_nan:
                    if (isChecked) {
                        type = R.id.rb_nan;
                        rb_nv.setChecked(false);
                    }
                    break;
                case R.id.rb_nv:
                    if (isChecked) {
                        type = R.id.rb_nv;
                        rb_nan.setChecked(false);
                    }
                    break;
            }
        }
    }


    private class TypeCheckBoxListenerG implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!rb_guonei.isChecked()&&!rb_gat.isChecked()&&!rb_guowai.isChecked()){
                typeG = 0;
                return;
            }
            switch (buttonView.getId()){
                case R.id.rb_guonei:
                    if (isChecked) {
                        typeG = R.id.rb_guonei;
                        rb_gat.setChecked(false);
                        rb_guowai.setChecked(false);
                    }
                    break;
                case R.id.rb_gat:
                    if (isChecked) {
                        typeG = R.id.rb_gat;
                        rb_guonei.setChecked(false);
                        rb_guowai.setChecked(false);
                    }
                    break;
                case R.id.rb_guowai:
                    if (isChecked) {
                        typeG = R.id.rb_guowai;
                        rb_guonei.setChecked(false);
                        rb_gat.setChecked(false);
                    }
                    break;
            }
        }
    }
    private class TypeCheckBoxListenerGS implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!rb_you.isChecked()&&!rb_wu.isChecked()){
                typeY = 0;
                return;
            }
            switch (buttonView.getId()){
                case R.id.rb_you:
                    if (isChecked) {
                        typeY = R.id.rb_you;
                        rb_wu.setChecked(false);
                    }
                    break;
                case R.id.rb_wu:
                    if (isChecked) {
                        typeY = R.id.rb_wu;
                        rb_you.setChecked(false);
                    }
                    break;
            }
        }
    }
}
