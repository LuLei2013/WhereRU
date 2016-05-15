package com.whereru.greengrass.goforit.okhttp.parser;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lule on 16/5/12.
 * {@link com.whereru.greengrass.goforit.okhttp.parser.Parser} 的子类,
 * 负责从返回的{@link okhttp3.Response}中,就返回值解析成一个有类型的对象
 *
 * @param <T> 指定了返回结果的类型
 */
public class ObjectParser<T> implements Parser<T> {
    private Class<T> mClass = null;

    public ObjectParser(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class can't be null");
        }
        this.mClass = clazz;
    }

    @Override
    public T parse(Call call, Response response) {
        try {
            String str = response.body().string();
            T t = gson.fromJson(str, mClass);
            return t;
        } catch (IOException e) {
        }
        return null;
    }

    @Override
    public T parse(Call call, IOException e) {
        return null;
    }
}
