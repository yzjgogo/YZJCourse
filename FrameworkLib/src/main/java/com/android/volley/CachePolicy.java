package com.android.volley;

/**
 * Created by zhaojh
 * Date: 2016/9/6
 * Time: 17:59
 */
public class CachePolicy {

    public static CachePolicy DefaultCachePolicy = new CachePolicy();
    public OverTimePolicy overTimeType = OverTimePolicy.DEFAULT;
    public InTimePolicy inTimePolicy = InTimePolicy.DEFAULT;
    private int cacheTime;

    private CachePolicy() {
    }

    public CachePolicy(InTimePolicy inTimeType, OverTimePolicy overTimeType, int timeSen) {
        this.overTimeType = overTimeType;
        this.inTimePolicy = inTimeType;
        this.cacheTime = timeSen * 1000;
    }

    public int getCacheTime() {
        return cacheTime;
    }

    public enum OverTimePolicy {
        /** 超时请求最新数据 */
        DEFAULT,
        /** 超时先返回缓存数据然后异步请求最新数据更新缓存 */
        CACHE,
        /** 超时先返回缓存数据然后请求结束再次返回最新数据 */
        CACHE_NET
    }

    public enum InTimePolicy {
        /** 未超时就返回缓存数据 */
        DEFAULT,
        /** 未超时就不反应 */
        NO_RESPONSE,
        /** 跳过缓存 */
        NO_CACHE
    }
}
