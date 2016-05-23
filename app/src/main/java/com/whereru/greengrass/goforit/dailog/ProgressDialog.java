package com.whereru.greengrass.goforit.dailog;

import android.app.Dialog;
import android.content.Context;

import com.whereru.greengrass.goforit.R;


/**
 * Created by lulei on 16/5/22 .
 */
public class ProgressDialog extends Dialog {

    public ProgressDialog(Context context) {
        super(context, R.style.Dialog);
        initView();
    }

    /**
     * 初始化页面组件
     */
    private void initView() {
        super.setContentView(R.layout.layout_progress_dialog_content);
    }

    /**
     * 打开
     *
     * @param iscancel 是否可以取消
     * @return
     */
    public void showDialog(boolean iscancel) {
        try {
            this.setCancelable(iscancel);
            super.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
