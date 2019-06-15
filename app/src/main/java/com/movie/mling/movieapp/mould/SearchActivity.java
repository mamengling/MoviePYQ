package com.movie.mling.movieapp.mould;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.HistoryAdapter;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.utils.common.ConstmOnItemOnclickListener;
import com.movie.mling.movieapp.utils.common.DensityUtil;
import com.movie.mling.movieapp.utils.common.ZxSharedPre;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.ClearEditText;
import com.movie.mling.movieapp.utils.widget.FullyLinearLayoutManager;
import com.movie.mling.movieapp.utils.widget.RecycleViewDivider;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/6 11:15
 * $DESE$
 */
public class SearchActivity extends BaseCompatActivity implements View.OnClickListener {
    private TextView tv_close;
    private ClearEditText title_bar1_edt;
    private List<String> listKey1s;
    private HistoryAdapter mAdapter;
    private String search_info;
    private RecyclerView recycler_view_history;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        titleBar.setVisibility(View.GONE);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreateViewContent(View view) {
        tv_close = view.findViewById(R.id.tv_close);
        title_bar1_edt = view.findViewById(R.id.title_bar1_edt);
        recycler_view_history = view.findViewById(R.id.recycler_view_history);
        view.findViewById(R.id.tv_history).setOnClickListener(this);
    }

    @Override
    protected void getExras() {

    }

    @Override
    protected void setListener() {
        setHistoryLayout();
        tv_close.setOnClickListener(this);
        FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view_history.setLayoutManager(layoutManager);
        //水平分割线
        recycler_view_history.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, DensityUtil.dip2px(this, 1), getResources().getColor(R.color.content_view_bg)));
        mAdapter = new HistoryAdapter(this);
        mAdapter.setOnItemOnclickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, String content) {
                Map<String,String> map=new HashMap<>();
                map.put("keywords",content);
                ActivityAnim.intentActivity(SearchActivity.this, SearchSuccessActivity.class, map);
            }
        });
        recycler_view_history.setAdapter(mAdapter);
        title_bar1_edt.setTextChangeAfter(new ClearEditText.OnTextChangeAfter() {
            @Override
            public void onText(String str) {
                search_info = str;
                if (TextUtils.isEmpty(search_info)) {
                    setHistoryLayout();
                } else {
                    List<String> list = ZxSharedPre.getInstance(mContext).getListInfo("searchHis");
                    if (!list.contains(search_info)) {
                        list.add(search_info);
                        ZxSharedPre.getInstance(mContext).setListInfo(list, "searchHis");
                    }
                }
            }
        });
        title_bar1_edt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {//EditorInfo.IME_ACTION_SEARCH、EditorInfo.IME_ACTION_SEND等分别对应EditText的imeOptions属性
                    if (!TextUtils.isEmpty(search_info)) {
                        Map<String,String> map=new HashMap<>();
                        map.put("keywords",search_info);
                        ActivityAnim.intentActivity(SearchActivity.this, SearchSuccessActivity.class, map);
                    }
                    return true;
                }
                return false;
            }
        });
        setHistoryLayout();

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
            case R.id.tv_close:
                ActivityAnim.endActivity(this);
                break;
            case R.id.tv_history:
                //保存搜索记录
                List<String> list = null;
                ZxSharedPre.getInstance(mContext).setListInfo(list, "searchHis");
                mAdapter.onReference(null);
                break;
        }
    }

    public void setHistoryLayout() {
        try {
            listKey1s = ZxSharedPre.getInstance(mContext).getListInfo("searchHis");
            //没有搜索历史不显示清空历史记录
            if (listKey1s != null && listKey1s.size() > 0) {
                mAdapter.onReference(listKey1s);
            } else {
            }
        } catch (Exception e) {

        }
    }
}
