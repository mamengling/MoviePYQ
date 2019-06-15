package com.movie.mling.movieapp.utils.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.movie.mling.movieapp.R;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2017/6/15 10:52
 * $DESE$
 */
public class TitleBar extends LinearLayout implements View.OnClickListener{
    public final static int clickBack = 1;//返回
    public final static int clickEdt = 2;
    public final static int clickSet = 3;
    public final static int click1Edt= 4;
    public final static int click1Edt1= 7;
    public final static int click1Left = 5;
    public final static int click1Right = 6;
    public final static int clickQuest = 8;
    public final int click1Clear = 7;
    public TitleBarClick titleBarClickImpl;
    private TextView not_data;
    private LinearLayout line_root;

    public enum TitleType {
        set, back, edt;
    }

    /**
     * 文字标题栏
     */
    private TextView viewTxtName, viewTxtBack;
    private TextView viewTxtSet, viewTxtEdt;
    /**
     * 搜索栏，UI控件
     */
    private TextView v1TvLeft,v1TvRight;
    private ImageView v1ImgClear;
    private EditText v1EdtSearch;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    /**
     * 初始化文字标题栏
     */
    private void initView() {
        setOrientation(LinearLayout.VERTICAL);
        View.inflate(getContext(), R.layout.common_title_bar, this);

        viewTxtName = (TextView) findViewById(R.id.title_bar_name);
        viewTxtBack = (TextView) findViewById(R.id.title_bar_back);
        viewTxtSet = (TextView) findViewById(R.id.title_bar_set);
        viewTxtEdt = (TextView) findViewById(R.id.title_bar_edt);
        not_data = (TextView) findViewById(R.id.not_data);
        line_root = (LinearLayout) findViewById(R.id.line_root);

        viewTxtBack.setOnClickListener(this);
        not_data.setOnClickListener(this);
        viewTxtSet.setOnClickListener(this);
        viewTxtEdt.setOnClickListener(this);
        viewTxtSet.setVisibility(View.GONE);
        viewTxtEdt.setVisibility(View.GONE);
    }
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.title_bar_back) {
            if (titleBarClickImpl != null) {
                titleBarClickImpl.titleOnClick(clickBack);
            }
        } else if (i == R.id.title_bar_set) {
            if (titleBarClickImpl != null) {
                titleBarClickImpl.titleOnClick(clickSet);
            }
        } else if (i == R.id.title_bar_edt) {
            if (getTitleBarClickImpl() != null) {
                titleBarClickImpl.titleOnClick(clickEdt);
            }
        } else if (i == R.id.title_bar1_left) {
            if (getTitleBarClickImpl() != null) {
                titleBarClickImpl.titleOnClick(click1Left);
            }
        }  else if (i == R.id.title_bar1_clear) {
            if (v1EdtSearch!=null){
                v1EdtSearch.setText("");
            }
        }else if (i == R.id.title_bar1_edt) {
            if (getTitleBarClickImpl() != null) {
                titleBarClickImpl.titleOnClick(click1Edt1);
            }
        } else if (i == R.id.title_bar1_right) {
            if (getTitleBarClickImpl() != null) {
                titleBarClickImpl.titleOnClick(click1Right);
            }
        }else if (i == R.id.not_data) {
            if (getTitleBarClickImpl() != null) {
                titleBarClickImpl.titleOnClick(clickQuest);
            }
        }
    }
    public TitleBarClick getTitleBarClickImpl() {
        return titleBarClickImpl;
    }

    public void setTitleBarClickImpl(TitleBarClick titleBarClickImpl) {
        this.titleBarClickImpl = titleBarClickImpl;
    }

    /**
     * 标题栏，点击事件监听接口
     */
    public interface TitleBarClick {
        /**
         * @param titleType 1:Back,2:edt,3:set,4:搜索，5位置，6搜索取消
         */
        void titleOnClick(int titleType);
    }
    /**
     * 设置原返回键图标，及文字 0不设置图标
     */
    public void setLeftView(int drwId,String name){
        setTextDrwName(viewTxtBack,drwId);
        viewTxtBack.setText(textDealWithStr(name));
    }
    /**
     * 设置原设置键图标，及文字 0不设置图标
     */
    public void setRightSet(int drwId,String name){
        setTextDrwNameRight(viewTxtSet,drwId);
        viewTxtSet.setText(textDealWithStr(name));
    }

    /**
     * 设置原设置键图标，及文字 0不设置图标
     */
    public void setRightSetColor(String name, int resId){
        viewTxtSet.setText(textDealWithStr(name));
        setTextDrwNameRight(viewTxtSet,0);
        viewTxtSet.setTextColor(getResources().getColor(resId));
    }
    /**
     * 设置原编辑键图标，及文字 0不设置图标
     */
    public void setRightEdit(int drwId,String name){
        setTextDrwNameRight(viewTxtEdt,drwId);
        viewTxtEdt.setText(textDealWithStr(name));
    }
    /**
     * 设置原编辑键图标，及文字 0不设置图标
     */
    public void setRightEdit(int drwId, String name, boolean isEn){
        setTextDrwNameRight(viewTxtEdt,drwId);
        viewTxtEdt.setText(textDealWithStr(name));
        viewTxtEdt.setEnabled(isEn);
        if (!isEn){
            viewTxtEdt.setTextColor(Color.GRAY);
        }else {
            viewTxtEdt.setTextColor(Color.BLACK);
        }
    }
    /**
     * 设置原编辑键图标，及文字 0不设置图标
     */
    public void setNotDate(String name){
        not_data.setText(name);
    }
    /**
     * 设置原编辑键图标，及文字 0不设置图标
     */
    public void setRightEditBlue(int drwId, String name, boolean isEn){
        setTextDrwNameRight(viewTxtEdt,drwId);
        viewTxtEdt.setText(textDealWithStr(name));
        viewTxtEdt.setEnabled(isEn);
        if (!isEn){
            viewTxtEdt.setTextColor(Color.GRAY);
        }else {
            viewTxtEdt.setTextColor(Color.parseColor("#5ac5b3"));
        }
    }
    private void setTextDrwName(TextView textView, int drawId){
        Drawable drawable=null;
        if (drawId!=0){
            drawable=getResources().getDrawable(drawId);
            drawable.setBounds( 0, 0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        }
        textView.setCompoundDrawables(drawable, null, null, null);
    }
    //设置右侧
    private void setTextDrwNameRight(TextView textView, int drawId){
        Drawable drawable=null;
        if (drawId==101){
            textView.setTextColor(Color.parseColor("#CE9278"));
        }else if(drawId!=101&&drawId!=0){
            drawable=getResources().getDrawable(drawId);
            drawable.setBounds( 0, 0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        }
        textView.setCompoundDrawables(null, null,drawable, null);
    }
    public void setTitleName(String name) {
        viewTxtName.setText(textDealWithStr(name));
    }
    public void setTitleName(String name, String color) {
        viewTxtName.setText(textDealWithStr(name));
        line_root.setBackgroundColor(Color.parseColor(color));
    }

    /**
     * 设置搜索标题栏，显示搜索栏
     *
     * @param clickEdt 是否可编辑
     */
    public void setSearchTitle(boolean clickEdt, boolean showRight, boolean showLeft) {
        View viewSearch = findViewById(R.id.title_search_layout);
        viewSearch.setVisibility(View.VISIBLE);
        v1EdtSearch = (EditText) findViewById(R.id.title_bar1_edt);
        v1ImgClear= (ImageView) findViewById(R.id.title_bar1_clear);
        v1ImgClear.setVisibility(View.GONE);
        v1ImgClear.setOnClickListener(this);
        v1TvRight = (TextView) findViewById(R.id.title_bar1_right);
        if (showRight) {
            v1TvRight.setVisibility(View.VISIBLE);
            v1TvRight.setOnClickListener(this);
        } else {
            v1TvRight.setVisibility(View.GONE);
        }

        v1TvLeft = (TextView) findViewById(R.id.title_bar1_left);
        if (showLeft) {
            v1TvLeft.setVisibility(View.VISIBLE);
            v1TvLeft.setOnClickListener(this);
        } else {
            v1TvLeft.setVisibility(View.GONE);
        }

        if (clickEdt) {
            v1EdtSearch.setEnabled(true);
        } else {
            v1EdtSearch.setEnabled(true);
            v1EdtSearch.setOnClickListener(this);
        }
    }
    /**
     * 设置搜索标题栏，显示搜索栏
     *
     * @param clickEdt 是否可编辑
     */
    public void setSearchTitle(boolean clickEdt, boolean showRight, boolean showLeft,boolean data) {
        View viewSearch = findViewById(R.id.title_search_layout);
        viewSearch.setVisibility(View.VISIBLE);
        v1EdtSearch = (EditText) findViewById(R.id.title_bar1_edt);
        v1ImgClear= (ImageView) findViewById(R.id.title_bar1_clear);
        v1ImgClear.setVisibility(View.GONE);
        v1ImgClear.setOnClickListener(this);
        v1TvRight = (TextView) findViewById(R.id.title_bar1_right);
        if (showRight) {
            v1TvRight.setVisibility(View.VISIBLE);
            v1TvRight.setOnClickListener(this);
        } else {
            v1TvRight.setVisibility(View.GONE);
        }

        v1TvLeft = (TextView) findViewById(R.id.title_bar1_left);
        if (showLeft) {
            v1TvLeft.setVisibility(View.VISIBLE);
            v1TvLeft.setOnClickListener(this);
        } else {
            v1TvLeft.setVisibility(View.GONE);
        }

        if (clickEdt) {
            v1EdtSearch.setEnabled(true);
        } else {
            v1EdtSearch.setEnabled(true);
            v1EdtSearch.setOnClickListener(this);
        }
        if (data) {
            not_data.setVisibility(VISIBLE);
            not_data.setEnabled(true);
        } else {
            not_data.setVisibility(GONE);
            not_data.setEnabled(true);
            not_data.setOnClickListener(this);
        }
    }
    /**
     * 设置输入框监听观察者
     * @param textWatcher
     */
    public void setSearchEdtView(TextWatcher textWatcher, String hint){
        if (v1EdtSearch!=null) {
            v1EdtSearch.addTextChangedListener(textWatcher);
//            v1EdtSearch.setHint(hint);
        }
    }

    /**
     * 选取搜搜内容
     * @return
     */
    public String getSearchTxt(){
        if (v1EdtSearch!=null) {
            return v1EdtSearch.getText().toString();
        }else{
            return "";
        }
    }
    /**
     * 设置搜索框内容是否可清除 图标
     * @param show
     */
    public void setSearchClear(boolean show){
        if (v1ImgClear!=null) {
            if (show){
                v1ImgClear.setVisibility(View.VISIBLE);
            }else{
                v1ImgClear.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置搜索显示内容
     * @param search
     */
    public void setSearchText(String search){
        if (v1EdtSearch!=null) {
            v1EdtSearch.setText(search);
        }
    }
    /**
     * 设置搜索标题栏，左侧定位按钮显示
     * @param name
     * @param drawableId
     */
    public void setSearchLeftTv(String name, int drawableId) {
        if (v1TvLeft == null) {
            v1TvLeft = (TextView) findViewById(R.id.title_bar1_left);
        }
        v1TvLeft.setVisibility(View.VISIBLE);
        setTextDrwName(v1TvLeft,drawableId);
        v1TvLeft.setText(textDealWithStr(name));
    }

    /**
     * 设置搜索标题栏，右侧定位按钮显示
     * @param name
     * @param drawableId 0不显示图标
     */
    public void setSearchRightTv(String name, int drawableId) {
        if (v1TvRight == null) {
            v1TvRight = (TextView) findViewById(R.id.title_bar1_right);
        }
        v1TvRight.setVisibility(View.VISIBLE);
        setTextDrwName(v1TvRight,drawableId);
        v1TvRight.setText(textDealWithStr(name));
    }
    /**
     * @param nameId
     */
    public void setTitleName(int nameId) {
        viewTxtName.setText(nameId);
    }

    public void setTitleBackName(String name) {
        viewTxtBack.setText(textDealWithStr(name));
    }

    public void setTitleEdtName(String name) {
        viewTxtEdt.setText(textDealWithStr(name));
    }
    //处理字符串 不是null
    private String textDealWithStr(String name){
        return TextUtils.isEmpty(name)?"":name;
    }

    /**
     * 普通文字标题栏
     * @param back 是否显示返回键
     * @param edt 是否显示编辑键
     * @param set 是否显示设置键
     */
    public void setShowIcon(boolean back, boolean edt, boolean set) {
        if (back) {
            viewTxtBack.setVisibility(View.VISIBLE);
        } else {
            viewTxtBack.setVisibility(View.GONE);
        }
        if (edt) {
            viewTxtEdt.setVisibility(View.VISIBLE);
        } else {
            viewTxtEdt.setVisibility(View.GONE);
        }
        if (set) {
            viewTxtSet.setVisibility(View.VISIBLE);
        } else {
            viewTxtSet.setVisibility(View.GONE);
        }
    }
    /**
     * 普通文字标题栏
     * @param back 是否显示返回键
     * @param edt 是否显示编辑键
     * @param set 是否显示设置键
     */
    public void setShowIcon(boolean back, boolean edt, boolean set,boolean data) {
        if (back) {
            viewTxtBack.setVisibility(View.VISIBLE);
        } else {
            viewTxtBack.setVisibility(View.GONE);
        }
        if (edt) {
            viewTxtEdt.setVisibility(View.VISIBLE);
        } else {
            viewTxtEdt.setVisibility(View.GONE);
        }
        if (set) {
            viewTxtSet.setVisibility(View.VISIBLE);
        } else {
            viewTxtSet.setVisibility(View.GONE);
        }
        if (data) {
            not_data.setVisibility(View.VISIBLE);
        } else {
            not_data.setVisibility(View.GONE);
        }
    }
}
