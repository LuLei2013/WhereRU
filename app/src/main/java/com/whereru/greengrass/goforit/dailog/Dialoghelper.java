package com.whereru.greengrass.goforit.dailog;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;


/**
 * Created by didi on 16/5/22.
 */
public class DialogHelper {

    private final static int DEF_TIMEOUT = 20;

    private static DialogHelper mInstance;

    protected Handler mHandler = new Handler(Looper.myLooper());

    private ProgressDialog mProgressDialog = null;
    private LoadingDialog mLoadingDialog;

    private DialogHelper() {
    }

    public static synchronized DialogHelper getmInstance() {
        if (mInstance == null) {
            mInstance = new DialogHelper();
        }
        return mInstance;
    }


    public void showProgressDialog(Context context) {
        if (context == null) {
            return;
        }
        try {
            closeProgressDialog();
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.showDialog(false);
            mHandler.postDelayed(mHideProgressLoadingDialog, DEF_TIMEOUT * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeProgressDialog() {
        try {
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mHandler.removeCallbacks(mHideProgressLoadingDialog);
    }

    /**
     * 未防止dialog一直处于显示状态，定时器用于销毁dialog
     */
    private Runnable mHideProgressLoadingDialog = new Runnable() {
        public void run() {
            closeProgressDialog();
        }
    };


    /**
     * 未防止dialog一直处于显示状态，定时器用于销毁dialog
     */
    private Runnable mHideLoadingDialog = new Runnable() {
        public void run() {
            closeLoadingDialog();
        }
    };

    /**
     * 显示等待对话框
     *
     * @param isCancel 是否可取消
     * @param msg      展示msg
     */
    public void showLoadingDialog(Context context, boolean isCancel, int msg) {
        showLoadingDialog(context, isCancel, msg, false, 25);
    }

    /**
     * 显示等待对话框
     *
     * @param isCancel     是否可取消
     * @param msg          展示msg
     * @param checkTimeout 是否检查超时
     * @param timeout      超时时间：秒
     */
    public void showLoadingDialog(Context context, boolean isCancel, int msg, boolean checkTimeout, int timeout) {
        closeLoadingDialog();

        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(context);
        }

        if (msg > 0) {
            mLoadingDialog.showLoadingDialog(isCancel, context.getResources().getString(msg));
        } else {
            mLoadingDialog.showLoadingDialog(isCancel);
        }
        if (checkTimeout) {
            mHandler.postDelayed(mHideLoadingDialog, timeout * 1000);
        }
    }

    /**
     * 关闭等待对话框
     */
    public void closeLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.removeLoadingDialog();
            mLoadingDialog = null;
        }
        mHandler.removeCallbacks(mHideLoadingDialog);
    }
}
