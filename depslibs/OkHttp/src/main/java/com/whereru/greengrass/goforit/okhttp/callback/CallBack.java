package com.whereru.greengrass.goforit.okhttp.callback;

import android.os.Handler;
import android.os.Message;

import com.whereru.greengrass.goforit.okhttp.UiHandler;
import com.whereru.greengrass.goforit.okhttp.parser.Parser;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 希望用模板方法模式重构,使得每种网络请求的返回处理消息类型可以在调用请求时自定义
 * Created by lulei on 16/5/12
 */
public class CallBack implements Callback {
    private static Handler handler = UiHandler.getInstance();

    private Parser mParser;

    public CallBack(Parser parser) {
        this.mParser = parser;
    }

    @Override

    public void onFailure(Call call, IOException e) {
        Message msg = new Message();
        msg.what = 0x00_001;
        msg.obj = mParser.parse(call, e);
        handler.sendMessage(msg);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        Message msg = new Message();
        msg.what = 0x00_001;
        msg.obj = mParser.parse(call, response);
        handler.sendMessage(msg);
    }
}
