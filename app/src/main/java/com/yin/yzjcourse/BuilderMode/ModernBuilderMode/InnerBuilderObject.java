package com.yin.yzjcourse.BuilderMode.ModernBuilderMode;

/**
 * Created by think on 2017/7/24.
 */

public class InnerBuilderObject {
    private final String mName;
    private int mAge;
    private String mLocation;

    private InnerBuilderObject(Builder builder) {
        mName = builder.mName;
        mAge = builder.mAge;
        mLocation = builder.mLocation;
    }

    public static final class Builder {
        private final String mName;
        private int mAge;
        private String mLocation;

        public Builder(String mName) {
            this.mName = mName;
        }

        public Builder mAge(int val) {
            mAge = val;
            return this;
        }

        public Builder mLocation(String val) {
            mLocation = val;
            return this;
        }

        public InnerBuilderObject build() {
            return new InnerBuilderObject(this);
        }
    }
}
