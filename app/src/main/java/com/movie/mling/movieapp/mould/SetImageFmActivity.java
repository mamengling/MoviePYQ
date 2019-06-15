package com.movie.mling.movieapp.mould;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.ChangeFmAdapter;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.mode.bean.ImageInfoBean;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/2/28 16:49
 * $DESE$
 */
public class SetImageFmActivity extends BaseCompatActivity implements View.OnClickListener {
    private TitleBar mTitleBar;
    private GridView gridView;
    private Button radiobutton3;
    private Button radiobutton4;
    private ChangeFmAdapter mAdapter;
    private List<ImageInfoBean.DataBean> mList = new ArrayList<>();
    private ArrayList<ImageInfoBean.DataBean> mListItem;
    private String picID;
    private String picUrl;
    private boolean flag;


    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mTitleBar = titleBar;
        titleBar.setTitleName("相册");
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(SetImageFmActivity.this);
                        break;
                }
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_set_image_fm;
    }

    @Override
    protected void onCreateViewContent(View view) {
        radiobutton3 = (Button) view.findViewById(R.id.radiobutton3);
        radiobutton4 = (Button) view.findViewById(R.id.radiobutton4);
        gridView = (GridView) view.findViewById(R.id.grid_view);
    }

    @Override
    protected void getExras() {
        mListItem = getIntent().getParcelableArrayListExtra("imagelist");
        flag = getIntent().getBooleanExtra("flag", false);
    }

    @Override
    protected void setListener() {
        radiobutton3.setOnClickListener(this);
        radiobutton4.setOnClickListener(this);
        if (flag) {
            radiobutton3.setVisibility(View.VISIBLE);
            radiobutton4.setVisibility(View.GONE);
        } else {
            radiobutton3.setVisibility(View.GONE);
            radiobutton4.setVisibility(View.VISIBLE);
        }
        if (mListItem != null && mListItem.size() > 0) {
            mList.clear();
            mList.addAll(mListItem);
        }
        mAdapter = new ChangeFmAdapter(mList, this);
        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.changeState(position);
                picID = mList.get(position).getId();
                picUrl = mList.get(position).getTitlepic();

            }
        });


    }

    @Override
    protected void fromNetGetData() {

    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radiobutton3:
                Intent intent = new Intent();
                intent.putExtra("picID", picID);
                intent.putExtra("picUrl", picUrl);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.radiobutton4:
                Intent intentD = new Intent();
                intentD.putExtra("picID", picID);
                intentD.putExtra("picUrl", picUrl);
                setResult(RESULT_OK, intentD);
                finish();
                break;
        }
    }
}
