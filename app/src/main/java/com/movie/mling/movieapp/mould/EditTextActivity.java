package com.movie.mling.movieapp.mould;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.movie.mling.movieapp.R;
import com.movie.mling.movieapp.base.BaseCompatActivity;
import com.movie.mling.movieapp.utils.common.log.LogUtil;
import com.movie.mling.movieapp.utils.widget.ActivityAnim;
import com.movie.mling.movieapp.utils.widget.TitleBar;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2017/8/10 16:52
 * $DESE$
 */
public class EditTextActivity extends BaseCompatActivity implements View.OnClickListener {
    private TextInputLayout editText;
    private EditText editTextt;
    private String titleName = "请输入";
    private String titleEditText;
    private String workContent = "填写详细、清晰的职位描述，有助于您更准确的展开招聘需求（不能填写QQ、微信、电话等联系方式，以及特殊符号）\n\n例如：\n1.工作内容...\n2.任务要求...\n3.特别说明...";
    private int titleContentNumber;
    private TitleBar mTitleBar;

    @Override
    protected void titleBarSet(TitleBar titleBar) {
        mTitleBar = titleBar;
        titleBar.setTitleName("请输入内容");
        titleBar.setShowIcon(true, true, false);
        titleBar.setTitleBarClickImpl(new TitleBar.TitleBarClick() {
            @Override
            public void titleOnClick(int titleType) {
                switch (titleType) {
                    case TitleBar.clickBack:
                        ActivityAnim.endActivity(EditTextActivity.this);
                        break;
                    case TitleBar.clickEdt:
                        titleEditText = editText.getEditText().getText().toString().trim();
                        LogUtil.i("titletitle", "102" + titleEditText);
                        Intent intent = new Intent();
                        intent.putExtra("editTextContent", titleEditText);
                        setResult(RESULT_OK, intent);
                        finish();
                        break;
                }
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_edittext;
    }

    @Override
    protected void onCreateViewContent(View view) {
        editText = (TextInputLayout) view.findViewById(R.id.editText);
        editTextt = (EditText) view.findViewById(R.id.editTextt);
    }

    @Override
    protected void getExras() {
        titleName = getIntent().getStringExtra("titleName");
        workContent = getIntent().getStringExtra("titleContent");
        titleContentNumber = getIntent().getIntExtra("titleContentNumber", 15);
        titleEditText = getIntent().getStringExtra("titleEditText");
        LogUtil.i(titleName, titleEditText + "----------" + workContent);
    }

    @Override
    protected void setListener() {
        if (titleContentNumber > 15) {
//            editText.setCounterMaxLength(titleContentNumber);
            editText.setCounterEnabled(false);
            editTextt.setLines(15);
            editTextt.setMaxLines(15);
        } else {
            editText.setCounterEnabled(false);
            editTextt.setLines(6);
        }
        mTitleBar.setTitleName(titleName);
        if (TextUtils.isEmpty(titleEditText) || TextUtils.equals(titleEditText, "请填写") || TextUtils.equals(titleEditText, "选填")) {
            editTextt.setHint(workContent);
            mTitleBar.setRightEditBlue(0, "确定", false);
        } else {
            editText.getEditText().setText(titleEditText);
            mTitleBar.setRightEditBlue(0, "确定", true);
        }
        editTextt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                titleEditText = editText.getEditText().getText().toString().trim();
                if (!TextUtils.isEmpty(titleEditText)) {
                    mTitleBar.setRightEditBlue(0, "确定", true);
                } else {
                    editTextt.setHint(workContent);
                    mTitleBar.setRightEditBlue(0, "确定", false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

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
            case R.id.title_bar_back:
                ActivityAnim.endActivity(this);
                break;
            case R.id.title_bar_edt:
                titleEditText = editText.getEditText().getText().toString().trim();
                Intent intent = new Intent();
                intent.putExtra("editTextContent", titleEditText);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
