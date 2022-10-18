package com.lidroid.xutils.db.converter;

import android.database.Cursor;

import com.lidroid.xutils.db.sqlite.ColumnDbType;

/**
 * 某些需求下需要将字段设置为object。 object字段一律按照toString存储
 *
 * @author zqs
 * @createTime 2016年4月20日 下午4:18:32
 */
public class ObjectColumnConverter implements ColumnConverter<Object> {
    @Override
    public Object getFieldValue(final Cursor cursor, int index) {
        return cursor.isNull(index) ? null : cursor.getString(index);
    }

    @Override
    public String getFieldValue(String fieldStringValue) {
        return fieldStringValue;
    }

    @Override
    public Object fieldValue2ColumnValue(Object fieldValue) {
        return fieldValue;
    }

    @Override
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.TEXT;
    }
}
