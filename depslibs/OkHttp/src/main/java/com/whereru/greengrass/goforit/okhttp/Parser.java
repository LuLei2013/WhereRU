package com.whereru.greengrass.goforit.okhttp;

/**
 * Created by lulei on 16/5/9.
 */
public interface Parser<T> {
    public T parse(Response response);
}
