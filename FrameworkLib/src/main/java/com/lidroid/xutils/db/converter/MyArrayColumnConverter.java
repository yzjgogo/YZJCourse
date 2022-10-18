package com.lidroid.xutils.db.converter;

import android.database.Cursor;

import com.lidroid.xutils.db.sqlite.ColumnDbType;

/***
 * 增加xutils对数组存取的支持：<br>
 * 支持 int[],Integer[],String[],Double[],double[],Float[],float[] <br>
 *
 * @author zqs
 * @createTime 2014年7月5日 下午12:56:13
 */
public class MyArrayColumnConverter implements ColumnConverter<Object> {

    // 分隔符
    protected static final String SPILT_STR = (char) 1 + "";
    // 转换类型
    protected String returnType;

    public MyArrayColumnConverter(String returnType) {
        this.returnType = returnType;
    }

    @Override
    public Object getFieldValue(Cursor cursor, int index) {
        /**
         * 从数据库中取出的【数组】实际是【字符串+分隔符】 存取的时候统一按照了string+[分隔符]的方式进行存取。所以取数据时需要分类型取数据
         */
        String dbResult = cursor.isNull(index) ? null : cursor.getString(index);
        if (dbResult != null && dbResult.length() > 0) {
            String[] dbResultSplit = dbResult.split(MyArrayColumnConverter.SPILT_STR, -1);
            return this.dealData(dbResultSplit);
        }
        return dealData(new String[0]);
    }

    @Override
    public Object getFieldValue(String fieldStringValue) {

        if (fieldStringValue != null && fieldStringValue.length() > 0) {
            /**
             * 从数据库中取出的【数组】实际是【字符串+分隔符】
             * 存取的时候统一按照了string+[分隔符]的方式进行存取。所以取数据时需要分类型取数据
             */
            String[] dbResultSplit = fieldStringValue.split(MyArrayColumnConverter.SPILT_STR, -1);
            return this.dealData(dbResultSplit);
        }

        return null;
    }

    /** 将数组转成字符串存取在数据库中的主要处理流程 */
    @Override
    public String fieldValue2ColumnValue(Object fieldValue) {

        if ("int".equals(returnType)) {
            int[] value = (int[]) fieldValue;
            if (value != null) {
                // 定义分隔符
                String temp = "";
                for (int i = 0; i < value.length; i++) {
                    if (i == value.length - 1) {
                        temp += value[i] + "";
                    } else {
                        temp += value[i] + MyArrayColumnConverter.SPILT_STR;
                    }
                }
                return temp;
            }
        }

        if ("Integer".equals(returnType)) {
            Integer[] value = (Integer[]) fieldValue;
            if (value != null) {
                // 定义分隔符
                String temp = "";
                for (int i = 0; i < value.length; i++) {
                    if (i == value.length - 1) {
                        temp += value[i] + "";
                    } else {
                        temp += value[i] + MyArrayColumnConverter.SPILT_STR;
                    }
                }
                return temp;
            }
        }

        if ("String".equals(returnType)) {
            String[] value = (String[]) fieldValue;
            if (value != null) {
                // 定义分隔符
                String temp = "";
                for (int i = 0; i < value.length; i++) {
                    if (i == value.length - 1) {
                        temp += value[i] + "";
                    } else {
                        temp += value[i] + MyArrayColumnConverter.SPILT_STR;
                    }
                }
                return temp;
            }
        }

        if ("Long".equals(returnType)) {
            Long[] value = (Long[]) fieldValue;
            if (value != null) {
                // 定义分隔符
                String temp = "";
                for (int i = 0; i < value.length; i++) {
                    if (i == value.length - 1) {
                        temp += value[i] + "";
                    } else {
                        temp += value[i] + MyArrayColumnConverter.SPILT_STR;
                    }
                }
                return temp;
            }
        }

        if ("long".equals(returnType)) {
            long[] value = (long[]) fieldValue;
            if (value != null) {
                // 定义分隔符
                String temp = "";
                for (int i = 0; i < value.length; i++) {
                    if (i == value.length - 1) {
                        temp += value[i] + "";
                    } else {
                        temp += value[i] + MyArrayColumnConverter.SPILT_STR;
                    }
                }
                return temp;
            }
        }

        if ("Float".equals(returnType)) {
            Float[] value = (Float[]) fieldValue;
            if (value != null) {
                // 定义分隔符
                String temp = "";
                for (int i = 0; i < value.length; i++) {
                    if (i == value.length - 1) {
                        temp += value[i] + "";
                    } else {
                        temp += value[i] + MyArrayColumnConverter.SPILT_STR;
                    }
                }
                return temp;
            }
        }

        if ("float".equals(returnType)) {
            float[] value = (float[]) fieldValue;
            if (value != null) {
                // 定义分隔符
                String temp = "";
                for (int i = 0; i < value.length; i++) {
                    if (i == value.length - 1) {
                        temp += value[i] + "";
                    } else {
                        temp += value[i] + MyArrayColumnConverter.SPILT_STR;
                    }
                }
                return temp;
            }
        }

        if ("Double".equals(returnType)) {
            Double[] value = (Double[]) fieldValue;
            if (value != null) {
                // 定义分隔符
                String temp = "";
                for (int i = 0; i < value.length; i++) {
                    if (i == value.length - 1) {
                        temp += value[i] + "";
                    } else {
                        temp += value[i] + MyArrayColumnConverter.SPILT_STR;
                    }
                }
                return temp;
            }
        }

        if ("double".equals(returnType)) {
            double[] value = (double[]) fieldValue;
            if (value != null) {
                // 定义分隔符
                String temp = "";
                for (int i = 0; i < value.length; i++) {
                    if (i == value.length - 1) {
                        temp += value[i] + "";
                    } else {
                        temp += value[i] + MyArrayColumnConverter.SPILT_STR;
                    }
                }
                return temp;
            }
        }

        return null;

    }

    @Override
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.TEXT;
    }

    @SuppressWarnings("unchecked")
    private Object dealData(String[] dbResultSplit) {

        // 读取的是int
        if ("int".equals(returnType)) {
            int[] result = new int[dbResultSplit.length];
            for (int i = 0; i < dbResultSplit.length; i++) {
                result[i] = Integer.parseInt(dbResultSplit[i]);
            }
            return (Object) result;
        }

        // 读取的是Integer
        if ("Integer".equals(returnType)) {
            Integer[] result = new Integer[dbResultSplit.length];
            for (int i = 0; i < dbResultSplit.length; i++) {
                result[i] = Integer.parseInt(dbResultSplit[i]);
            }
            return (Object) result;
        }

        // 读取的是String
        if ("String".equals(returnType)) {
            return (Object) dbResultSplit;
        }

        // 读取的是Long
        if ("Long".equals(returnType)) {
            Long[] result = new Long[dbResultSplit.length];
            for (int i = 0; i < dbResultSplit.length; i++) {
                result[i] = Long.parseLong(dbResultSplit[i]);
            }
            return (Object) result;
        }

        // 读取的是long
        if ("long".equals(returnType)) {
            long[] result = new long[dbResultSplit.length];
            for (int i = 0; i < dbResultSplit.length; i++) {
                result[i] = Long.parseLong(dbResultSplit[i]);
            }
            return (Object) result;
        }

        // 读取Float
        if ("Float".equals(returnType)) {
            Float[] result = new Float[dbResultSplit.length];
            for (int i = 0; i < dbResultSplit.length; i++) {
                result[i] = Float.parseFloat(dbResultSplit[i]);
            }
            return (Object) result;

        }

        // 读取float
        if ("float".equals(returnType)) {
            float[] result = new float[dbResultSplit.length];
            for (int i = 0; i < dbResultSplit.length; i++) {
                result[i] = Float.parseFloat(dbResultSplit[i]);
            }
            return (Object) result;
        }

        // 读取Double
        if ("Double".equals(returnType)) {
            Double[] result = new Double[dbResultSplit.length];
            for (int i = 0; i < dbResultSplit.length; i++) {
                result[i] = Double.parseDouble(dbResultSplit[i]);
            }
            return (Object) result;
        }

        // 读取double
        if ("double".equals(returnType)) {
            double[] result = new double[dbResultSplit.length];
            for (int i = 0; i < dbResultSplit.length; i++) {
                result[i] = Double.parseDouble(dbResultSplit[i]);
            }
            return (Object) result;
        }
        return null;
    }
}
