package com.whereru.greengrass.goforit.dailog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.whereru.greengrass.goforit.R;


/**
 * Created by lulei on 16/5/22.
 */
public class LoadingDialog {

    private Context mContext;
    /**
     * loading对话框是否正在显示
     */
    private boolean mIsLoading = false;
    private ProgressDialog waitDialog = null;

    public LoadingDialog(Context context) {
        mContext = context;
    }

    /**
     * 正在加载对话框
     *
     * @param isCanCancel 是否可以取消对话框
     */
    public void showLoadingDialog(boolean isCanCancel) {
        showLoadingDialog(isCanCancel, mContext.getString(R.string.wait_net));
    }

    /**
     * 正在加载对话框,自定义提示语
     *
     * @param isCanCancel 是否可以取消对话框
     * @param msg         自定义提示语
     */
    public void showLoadingDialog(boolean isCanCancel, String msg) {
        try {
            if (waitDialog != null) {
                waitDialog.dismiss();
                waitDialog = null;
            }
            boolean isChangedTypface = false;
            try {
                waitDialog = ProgressDialog.show(mContext, null, msg, true, false);
                isChangedTypface = waitDialog != null;
            } catch (Exception e) {
            }
            if (!isChangedTypface) {
                waitDialog = new ProgressDialog(mContext);
                waitDialog.setMessage(msg);
                waitDialog.setCancelable(isCanCancel);
                waitDialog.setOnCancelListener(mCancelListener);
                waitDialog.show();
            }
            mIsLoading = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeLoadingDialog() {
        try {
            if (waitDialog != null) {
                waitDialog.dismiss();
                waitDialog = null;
            }
        } catch (Exception e) {
        }
        waitDialog = null;
        mIsLoading = false;
    }

    /**
     * loading对话框是否正在显示
     */
    public boolean isLoading() {
        return mIsLoading;
    }

    /**
     * loading对话框取消的监听
     */
    private DialogInterface.OnCancelListener mCancelListener = new DialogInterface.OnCancelListener() {

        @Override
        public void onCancel(DialogInterface dialog) {
            mIsLoading = false;
        }
    };


}
