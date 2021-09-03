package com.yin.yzjcourse.Net;

import java.io.Serializable;

public class RspEntity implements Serializable {
    public String accessKeyId;
    public String secretAccessKey;
    public String securityToken;
    public String StatusCode;
    public String expiresAt;

    @Override
    public String toString() {
        return "RspEntity{" +
                "accessKeyId='" + accessKeyId + '\'' +
                ", secretAccessKey='" + secretAccessKey + '\'' +
                ", securityToken='" + securityToken + '\'' +
                ", StatusCode='" + StatusCode + '\'' +
                ", expiresAt='" + expiresAt + '\'' +
                '}';
    }
}
