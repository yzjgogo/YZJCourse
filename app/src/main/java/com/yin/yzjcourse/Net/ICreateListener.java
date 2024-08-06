package com.yin.yzjcourse.Net;



public interface ICreateListener<T> {
    void onSuccess(T entity);
    void onFailure(int code,String msg);
}
