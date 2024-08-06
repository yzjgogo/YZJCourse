package com.yin.yzjcourse.Net;

import java.io.Serializable;

public class UploadAvatarGetUrlEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public String upload_url;
    public String file_url;

    @Override
    public String toString() {
        return "UploadAvatarGetUrlEntity{" +
                "upload_url='" + upload_url + '\'' +
                ", file_url='" + file_url + '\'' +
                '}';
    }
}
