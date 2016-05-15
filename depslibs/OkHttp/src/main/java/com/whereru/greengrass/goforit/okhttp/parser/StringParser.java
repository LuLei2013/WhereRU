package com.whereru.greengrass.goforit.okhttp.parser;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lule on 16/5/12.
 * {@link com.whereru.greengrass.goforit.okhttp.parser.Parser} 的子类,
 * 负责从返回的{@link okhttp3.Response}中,就返回值解析成一个字符串
 */
public class StringParser implements Parser<String> {

    @Override
    public String parse(Call call, Response response) {
        try {
            return response.body().string();
        } catch (IOException e) {
            if (e != null) {
                return e.toString();
            }
        }
        return "服务器请求失败1";
    }

    @Override
    public String parse(Call call, IOException e) {
        if (e == null) {
            return "服务器请求失败2";
        }
        return e.toString();
    }
}
