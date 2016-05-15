package com.whereru.greengrass.goforit.okhttp.parser;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lu lei on 16/5/9.
 * 负责将okHttp的请求结果{@link okhttp3.Response}(包括正常和错误两种情况)解析出来
 * 因为,这些类都是工具类,所以后面可以为其子类提供单例模式返回唯一的实例,
 * 从而避免重复的对象创建和回收,提高效率
 *
 * @param <T> 是将response中的body解析之后的结果对象类型
 */
public interface Parser<T> {
    Gson gson = new Gson();

    public T parse(Call call, Response response);

    public T parse(Call call, IOException e);
}
