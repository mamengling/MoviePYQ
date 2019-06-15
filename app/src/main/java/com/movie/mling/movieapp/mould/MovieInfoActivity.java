package com.movie.mling.movieapp.mould;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.SuperKotlin.pictureviewer.ImagePagerActivity;
import com.SuperKotlin.pictureviewer.PictureConfig;
import com.loopj.android.http.RequestParams;
import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.adapter.ViewpagerAdapter;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.iactivityview.MovieInfoActivityView;
import com.movie.mling.movieapp.mode.bean.CallBackVo;
import com.movie.mling.movieapp.mode.bean.MovieInfoBean;
import com.movie.mling.movieapp.presenter.MovieInfoActivityPresenter;
import com.movie.mling.movieapp.utils.common.AppMethod;
import com.movie.mling.movieapp.utils.common.Constants;
import com.movie.mling.movieapp.utils.common.SharePreferenceUtil;
import com.movie.mling.movieapp.utils.common.UserConfig;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.dialogutils.MDialog;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.AlignTextView;
import com.movie.mling.movieapp.utils.widget.TitleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lib.util.open.rollviewpage.OnItemClickListener;
import lib.util.open.rollviewpage.RollPagerView;
import lib.util.open.rollviewpage.hintview.ColorPointHintView;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2018/3/9 10:45
 * $DESE$
 */
public class MovieInfoActivity extends BaseCompatActivity implements View.OnClickListener, MovieInfoActivityView {
    private MovieInfoActivityPresenter mMovieInfoActivityPresenter;
    private RollPagerView normal_view_pager;
    private ViewpagerAdapter mPagerAdapter;
    private TextView title_bar_back;
    private TextView title_bar_set;
    private TextView title_bar_edt;
    private TextView title_bar_add;
    private TextView title_bar_zan;
    private TextView title_bar_pao;
    private TextView tv_view_type;
    private TextView tv_movie_name;
    private TextView tv_add_beizhu;
    private TextView tv_beizhu_content;
    private LinearLayout line_content;
    private LinearLayout line_person;
    private String url;
    private String movieID;
    private ArrayList<String> items = new ArrayList<>();
    private String intentLng;
    private String intentLat;
    private String intentTitle;
    private String intentLid;
    private String intentCity;
    private String intentLname;
    private String intentMid;
    private String intentMtitle;
    private String intentleixing;
    private List<MovieInfoBean.DataBean.PhotoBean> photoList;
    private int movieFav;
    private int moviePao;
    private int movieDig;
    private String keyid;
    private String keytext;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        titleBar.setVisibility(View.GONE);
        mMovieInfoActivityPresenter = new MovieInfoActivityPresenter(this);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_movie_info;
    }

    @Override
    protected void onCreateViewContent(View view) {
        normal_view_pager = view.findViewById(R.id.normal_view_pager);
        tv_view_type = view.findViewById(R.id.tv_view_type);
        tv_movie_name = view.findViewById(R.id.tv_movie_name);
        line_content = view.findViewById(R.id.line_content);
        title_bar_back = view.findViewById(R.id.title_bar_back);
        title_bar_set = view.findViewById(R.id.title_bar_set);
        title_bar_edt = view.findViewById(R.id.title_bar_edt);
        title_bar_add = view.findViewById(R.id.title_bar_add);
        title_bar_zan = view.findViewById(R.id.title_bar_zan);
        title_bar_pao = view.findViewById(R.id.title_bar_pao);
        tv_add_beizhu = view.findViewById(R.id.tv_add_beizhu);
        tv_beizhu_content = view.findViewById(R.id.tv_beizhu_content);
        line_person = view.findViewById(R.id.line_person);
    }

    @Override
    protected void getExras() {
        movieID = getIntent().getStringExtra("movieID");
    }

    @Override
    protected void setListener() {
        title_bar_back.setOnClickListener(this);
        title_bar_set.setOnClickListener(this);
        title_bar_edt.setOnClickListener(this);
        title_bar_add.setOnClickListener(this);
        title_bar_zan.setOnClickListener(this);
        title_bar_pao.setOnClickListener(this);
        tv_add_beizhu.setOnClickListener(this);
        //轮播图 测试上传
        mPagerAdapter = new ViewpagerAdapter(this, items);
        normal_view_pager.setPlayDelay(3000);
        normal_view_pager.setAdapter(mPagerAdapter);
        normal_view_pager.setHintView(new ColorPointHintView(mContext, Color.parseColor("#5ac5b3"), Color.WHITE));
        normal_view_pager.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (TextUtils.equals("news", photoList.get(position).getFilmadtype())) {
                    Map<String, String> map = new HashMap<>();
                    map.put("loadUrl", photoList.get(position).getFilmadid());
                    map.put("title", tv_movie_name.getText().toString());
                    ActivityAnim.intentActivity(MovieInfoActivity.this, WebviewDefulitActivity.class, map);
                } else {
                    PictureConfig config = new PictureConfig.Builder()
                            .setListData(items)//图片数据List<String> list
                            .setPosition(position)//图片下标（从第position张图片开始浏览）
                            .setDownloadPath("pictureviewer")//图片下载文件夹地址
                            .setIsShowNumber(true)//是否显示数字下标
                            .needDownload(true)//是否支持图片下载
                            .setPlacrHolder(R.mipmap.mine_cer_icon)//占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
                            .build();
                    ImagePagerActivity.startActivity(MovieInfoActivity.this, config);
                }
            }
        });
    }

    @Override
    protected void fromNetGetData() {
        url = Constants.APP_USER_MOVIE_INFO;
        mMovieInfoActivityPresenter.getMovieInfo();
    }

    @Override
    protected void fromNotMsgReference() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                ActivityAnim.endActivity(this);
                break;
            case R.id.title_bar_set:
                if (TextUtils.isEmpty(intentLat) || TextUtils.isEmpty(intentLng)) {
                    MDialog.getInstance(this).showToast("没有设置位置信息");
                } else {
                    Intent map = new Intent(MovieInfoActivity.this, MapLocationActivity.class);
                    map.putExtra("intentLng", intentLng);
                    map.putExtra("intentLat", intentLat);
                    map.putExtra("intentTitle", intentTitle);
                    map.putExtra("intentLid", intentLid);
                    map.putExtra("intentCity", intentCity);
                    map.putExtra("intentLname", intentLname);
                    map.putExtra("intentMtitle", intentMtitle);
                    map.putExtra("intentMid", intentMid);
                    map.putExtra("intentleixing", intentleixing);
                    map.putExtra("intentFlag", 101);
                    ActivityAnim.intentActivity(this, map);
                }
                break;
            case R.id.title_bar_edt:
                if (!SharePreferenceUtil.getBoolean(MovieInfoActivity.this, UserConfig.SYS_IS_LOGIN, false)) {
                    //去登陆页
                    ActivityAnim.intentActivity(this, LoginActivity.class, null);
                } else {
                    url = Constants.APP_USER_COLLECT_MOVICE;
                    mMovieInfoActivityPresenter.getMovieFavInfo();
                }
                break;
            case R.id.title_bar_add:

                if (!SharePreferenceUtil.getBoolean(MovieInfoActivity.this, UserConfig.SYS_IS_LOGIN, false)) {
                    //去登陆页
                    ActivityAnim.intentActivity(this, LoginActivity.class, null);
                } else {
                    Map<String, String> map = new HashMap();
                    map.put("keyid", intentMid);
                    ActivityAnim.intentActivity(this, FeedBackActivity.class, map);
                }
                break;
            case R.id.title_bar_pao:
                if (!SharePreferenceUtil.getBoolean(MovieInfoActivity.this, UserConfig.SYS_IS_LOGIN, false)) {
                    //去登陆页
                    ActivityAnim.intentActivity(this, LoginActivity.class, null);
                } else {
                    url = Constants.APP_USER_SERVICE_PAO;
                    mMovieInfoActivityPresenter.getMoviePaoInfo();
                }
                break;
            case R.id.title_bar_zan:
                if (!SharePreferenceUtil.getBoolean(MovieInfoActivity.this, UserConfig.SYS_IS_LOGIN, false)) {
                    //去登陆页
                    ActivityAnim.intentActivity(this, LoginActivity.class, null);
                } else {
                    url = Constants.APP_USER_SERVICE_DIG;
                    mMovieInfoActivityPresenter.getMovieDigInfo();
                }
                break;
            case R.id.tv_add_beizhu:
                if (!SharePreferenceUtil.getBoolean(MovieInfoActivity.this, UserConfig.SYS_IS_LOGIN, false)) {
                    //去登陆页
                    ActivityAnim.intentActivity(this, LoginActivity.class, null);
                } else {
                    Map<String, String> map = new HashMap<>();
                    map.put("keyid", keyid);
                    map.put("keytext", keytext);
                    ActivityAnim.intentActivity(this, InputUserMarkActivity.class, map);
                }

                break;
        }
    }

    @Override
    public RequestParams getParamenters() {
        RequestParams params = AppMethod.getMapParams(url);
        params.put("token", SharePreferenceUtil.getString(this, UserConfig.USER_TOKEN, ""));
        params.put("id", movieID);
        params.put("fid", movieID);
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
    public void excuteSuccessCallBack(MovieInfoBean mCallBackVo) {
        if (mCallBackVo != null && mCallBackVo.getData() != null) {
            if (mCallBackVo.getData().getPhoto() != null && mCallBackVo.getData().getPhoto().size() > 0) {
                photoList = mCallBackVo.getData().getPhoto();
                items.clear();
                for (int i = 0; i < mCallBackVo.getData().getPhoto().size(); i++) {
                    items.add(mCallBackVo.getData().getPhoto().get(i).getTitlepic());
                }
                LogUtil.i("items", items.size() + "");
                mPagerAdapter.setImgs(items);
                normal_view_pager.setPlayDelay(3000);
                normal_view_pager.setAdapter(mPagerAdapter);
            }
            keyid = mCallBackVo.getData().getId();
            keytext = mCallBackVo.getData().getUser_mark();
            intentLng = mCallBackVo.getData().getLng();
            intentLat = mCallBackVo.getData().getLat();
            intentTitle = mCallBackVo.getData().getTitle();
            intentLid = mCallBackVo.getData().getLid();
            intentCity = mCallBackVo.getData().getCity();
            intentLname = mCallBackVo.getData().getLname();
            intentMtitle = mCallBackVo.getData().getTitle();
            intentMid = mCallBackVo.getData().getId();
            intentleixing = mCallBackVo.getData().getLeixing_text();
            tv_view_type.setText(mCallBackVo.getData().getLeixing_text());
            tv_movie_name.setText(mCallBackVo.getData().getTitle());
            line_content.removeAllViews();
            movieFav = mCallBackVo.getData().getFav();
            movieDig = mCallBackVo.getData().getDig();
            moviePao = mCallBackVo.getData().getPao();
            if (movieFav == 1) {
                setView(title_bar_edt, R.mipmap.icon_new_like_full);
            } else {
                setView(title_bar_edt, R.mipmap.icon_new_like);
            }

            if (movieDig == 1) {
                setView(title_bar_zan, R.mipmap.icon_movie_dig_sel);
            } else {
                setView(title_bar_zan, R.mipmap.icon_movie_dig_unsel);
            }

            if (moviePao == 1) {
                setView(title_bar_pao, R.mipmap.icon_mar_pao_sel);
            } else {
                setView(title_bar_pao, R.mipmap.icon_mark_pao_unsel);
            }
            if (!TextUtils.isEmpty(mCallBackVo.getData().getUser_mark())) {
                tv_beizhu_content.setText(mCallBackVo.getData().getUser_mark());
            }
            for (int i = 0; i < mCallBackVo.getData().getExt().size(); i++) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_plus_text_view_, null);
                AlignTextView tv_ticai = view.findViewById(R.id.tv_ticai);
                TextView tv_ticai_name = view.findViewById(R.id.tv_ticai_name);
                tv_ticai.setAlingText(mCallBackVo.getData().getExt().get(i).get(0) + "：");
                tv_ticai_name.setText(mCallBackVo.getData().getExt().get(i).get(1));
                line_content.addView(view);
            }
            line_person.removeAllViews();
            for (int i = 0; i < mCallBackVo.getData().getUsers().size(); i++) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_user_text_view_, null);
                TextView tv_person = view.findViewById(R.id.tv_person);
                TextView tv_content = view.findViewById(R.id.tv_content);
                tv_person.setText(mCallBackVo.getData().getUsers().get(i).getTitle());
                tv_content.setText(mCallBackVo.getData().getUsers().get(i).getRemark());
                line_person.addView(view);
            }
        }
    }

    @Override
    public void excuteSuccessFavCallBack(MovieInfoBean mCallBackVo) {
        if (movieFav == 1) {
            setView(title_bar_edt, R.mipmap.icon_new_like);
            movieFav = 0;
        } else {
            setView(title_bar_edt, R.mipmap.icon_new_like_full);
            movieFav = 1;
        }
    }

    @Override
    public void excuteSuccessDigCallBack(MovieInfoBean mCallBackVo) {
        if (movieDig == 1) {
            setView(title_bar_zan, R.mipmap.icon_movie_dig_unsel);
            movieDig = 0;
        } else {
            setView(title_bar_zan, R.mipmap.icon_movie_dig_sel);
            movieDig = 1;
        }
    }

    @Override
    public void excuteSuccessPaoCallBack(MovieInfoBean mCallBackVo) {
        if (moviePao == 1) {
            setView(title_bar_pao, R.mipmap.icon_mark_pao_unsel);
            moviePao = 0;
        } else {
            setView(title_bar_pao, R.mipmap.icon_mar_pao_sel);
            moviePao = 1;
        }
    }


    public void setView(TextView textView, int drwId) {
        setTextDrwName(textView, drwId);
    }


    private void setTextDrwName(TextView textView, int drawId) {
        Drawable drawable = null;
        if (drawId != 0) {
            drawable = getResources().getDrawable(drawId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        textView.setCompoundDrawables(null, null, drawable, null);
    }
}
