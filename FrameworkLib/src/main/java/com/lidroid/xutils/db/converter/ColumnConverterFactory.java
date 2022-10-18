package com.lidroid.xutils.db.converter;

import com.lidroid.xutils.db.sqlite.ColumnDbType;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: wyouflf
 * Date: 13-11-4
 * Time: 下午10:27
 */
public class ColumnConverterFactory {

    private static final ConcurrentHashMap<String, ColumnConverter> columnType_columnConverter_map;

    static {
        columnType_columnConverter_map = new ConcurrentHashMap<String, ColumnConverter>();

        BooleanColumnConverter booleanColumnConverter = new BooleanColumnConverter();
        columnType_columnConverter_map.put(boolean.class.getName(), booleanColumnConverter);
        columnType_columnConverter_map.put(Boolean.class.getName(), booleanColumnConverter);

        ByteArrayColumnConverter byteArrayColumnConverter = new ByteArrayColumnConverter();
        columnType_columnConverter_map.put(byte[].class.getName(), byteArrayColumnConverter);

        ByteColumnConverter byteColumnConverter = new ByteColumnConverter();
        columnType_columnConverter_map.put(byte.class.getName(), byteColumnConverter);
        columnType_columnConverter_map.put(Byte.class.getName(), byteColumnConverter);

        CharColumnConverter charColumnConverter = new CharColumnConverter();
        columnType_columnConverter_map.put(char.class.getName(), charColumnConverter);
        columnType_columnConverter_map.put(Character.class.getName(), charColumnConverter);

        DateColumnConverter dateColumnConverter = new DateColumnConverter();
        columnType_columnConverter_map.put(Date.class.getName(), dateColumnConverter);

        DoubleColumnConverter doubleColumnConverter = new DoubleColumnConverter();
        columnType_columnConverter_map.put(double.class.getName(), doubleColumnConverter);
        columnType_columnConverter_map.put(Double.class.getName(), doubleColumnConverter);

        FloatColumnConverter floatColumnConverter = new FloatColumnConverter();
        columnType_columnConverter_map.put(float.class.getName(), floatColumnConverter);
        columnType_columnConverter_map.put(Float.class.getName(), floatColumnConverter);

        IntegerColumnConverter integerColumnConverter = new IntegerColumnConverter();
        columnType_columnConverter_map.put(int.class.getName(), integerColumnConverter);
        columnType_columnConverter_map.put(Integer.class.getName(), integerColumnConverter);

        LongColumnConverter longColumnConverter = new LongColumnConverter();
        columnType_columnConverter_map.put(long.class.getName(), longColumnConverter);
        columnType_columnConverter_map.put(Long.class.getName(), longColumnConverter);

        ShortColumnConverter shortColumnConverter = new ShortColumnConverter();
        columnType_columnConverter_map.put(short.class.getName(), shortColumnConverter);
        columnType_columnConverter_map.put(Short.class.getName(), shortColumnConverter);

        SqlDateColumnConverter sqlDateColumnConverter = new SqlDateColumnConverter();
        columnType_columnConverter_map.put(java.sql.Date.class.getName(), sqlDateColumnConverter);

        StringColumnConverter stringColumnConverter = new StringColumnConverter();
        columnType_columnConverter_map.put(String.class.getName(), stringColumnConverter);

        //自定义Integer数组类型存取
        MyArrayColumnConverter arrayColumnConverter = new MyArrayColumnConverter("Integer");
        columnType_columnConverter_map.put(Integer[].class.getName(), arrayColumnConverter);
        //自定义int数组类型存取
        arrayColumnConverter = new MyArrayColumnConverter("int");
        columnType_columnConverter_map.put(int[].class.getName(), arrayColumnConverter);

        //自定义string数组类型存取
        arrayColumnConverter = new MyArrayColumnConverter("String");
        columnType_columnConverter_map.put(String[].class.getName(), arrayColumnConverter);

        //自定义Long数组类型存取
        arrayColumnConverter = new MyArrayColumnConverter("Long");
        columnType_columnConverter_map.put(Long[].class.getName(), arrayColumnConverter);

        //自定义long数组类型存取
        arrayColumnConverter = new MyArrayColumnConverter("long");
        columnType_columnConverter_map.put(long[].class.getName(), arrayColumnConverter);

        //自定义Float数组类型存取
        arrayColumnConverter = new MyArrayColumnConverter("Float");
        columnType_columnConverter_map.put(Float[].class.getName(), arrayColumnConverter);

        //自定义float数组类型存取
        arrayColumnConverter = new MyArrayColumnConverter("float");
        columnType_columnConverter_map.put(float[].class.getName(), arrayColumnConverter);

        //自定义Double数组类型存取
        arrayColumnConverter = new MyArrayColumnConverter("Double");
        columnType_columnConverter_map.put(Double[].class.getName(), arrayColumnConverter);

        //自定义double数组类型存取
        arrayColumnConverter = new MyArrayColumnConverter("double");
        columnType_columnConverter_map.put(double[].class.getName(), arrayColumnConverter);

        // 自定义Object类型数据的存取
        ObjectColumnConverter objectColumnConverter = new ObjectColumnConverter();
        columnType_columnConverter_map.put(Object.class.getName(), objectColumnConverter);
    }

    private ColumnConverterFactory() {
    }

    public static ColumnConverter getColumnConverter(Class columnType) {
        if (columnType_columnConverter_map.containsKey(columnType.getName())) {
            return columnType_columnConverter_map.get(columnType.getName());
        } else if (ColumnConverter.class.isAssignableFrom(columnType)) {
            try {
                ColumnConverter columnConverter = (ColumnConverter) columnType.newInstance();
                if (columnConverter != null) {
                    columnType_columnConverter_map.put(columnType.getName(), columnConverter);
                }
                return columnConverter;
            } catch (Throwable e) {
            }
        }
        return null;
    }

    public static ColumnDbType getDbColumnType(Class columnType) {
        ColumnConverter converter = getColumnConverter(columnType);
        if (converter != null) {
            return converter.getColumnDbType();
        }
        return ColumnDbType.TEXT;
    }

    public static void registerColumnConverter(Class columnType, ColumnConverter columnConverter) {
        columnType_columnConverter_map.put(columnType.getName(), columnConverter);
    }

    public static boolean isSupportColumnConverter(Class columnType) {
        if (columnType_columnConverter_map.containsKey(columnType.getName())) {
            return true;
        } else if (ColumnConverter.class.isAssignableFrom(columnType)) {
            try {
                ColumnConverter columnConverter = (ColumnConverter) columnType.newInstance();
                if (columnConverter != null) {
                    columnType_columnConverter_map.put(columnType.getName(), columnConverter);
                }
                return columnConverter == null;
            } catch (Throwable e) {
            }
        }
        return false;
    }
}
