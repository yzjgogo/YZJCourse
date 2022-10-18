package com.lidroid.xutils.db;

import android.content.Context;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.DbUtils.DbUpgradeListener;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.db.table.KeyValue;
import com.lidroid.xutils.exception.DbException;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao基类
 *
 * @param <T>
 * @author ylf
 * @createTime 2014年5月25日 上午11:36:01
 */
public abstract class DAOImpl<T extends Object> implements DAOInterface<T> {

    public static String[] nullStrs = new String[0];

    protected Context mContext;
    protected Class<T> classT;
    protected Object entity;
    /** 完整的db路径（包含db文件名） */
    protected String dbFullPath;
    /** db文件的路径 */
    protected String sdCardPath;
    /** db文件名 */
    protected String dbName;

    /** 是否支持事务 param addd by kuku */
    private boolean allowTransaction = false; // 数据量大的时候请开启

    /**
     * 默认Db
     *
     * @param mContext
     * @param entity
     * @author ylf
     * @createTime 2014年5月25日 上午11:35:46
     */
    public DAOImpl(Context mContext, Class<T> classT) {
        this(mContext, classT, "", "", "");
    }

    public DAOImpl(Context mContext, Class<T> classT, String dbFullPath) {
        this(mContext, classT, dbFullPath, "", "");
    }

    public DAOImpl(Context mContext, Class<T> classT, String sdCardPath, String dbName) {
        this(mContext, classT, "", sdCardPath, dbName);
    }

    /**
     * sd卡上db，不存在将自动创建
     *
     * @param mContext
     * @param classT
     * @param dbFullPath dbFullPath可以是[仅文件名]或者[完整file的路径]，如果为空则sdCardPath+dbName构成的路径才会生效
     * @param sdCardPath sd卡文件路径
     * @param dbName db的文件名
     */
    private DAOImpl(Context mContext, Class<T> classT, String dbFullPath, String sdCardPath, String dbName) {
        this.mContext = mContext;
        this.classT = classT;
        try {
            Constructor<?> constructor = classT.getConstructor();
            this.entity = constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.dbFullPath = dbFullPath;
        this.sdCardPath = sdCardPath;
        this.dbName = dbName;
    }

    @Override
    public List<T> findAll() throws DbException {
        return getDbUtils().findAll(classT);
    }

    @Override
    public List<T> findAll(Selector selector) throws DbException {
        return getDbUtils().findAll(selector);
    }

    @Override
    public T findById(Object idValue) throws DbException {
        return getDbUtils().findById(classT, idValue);
    }

    @Override
    public T findFirst(Selector selector) throws DbException {
        return getDbUtils().findFirst(selector);
    }

    @Override
    public T findFirst() throws DbException {
        return getDbUtils().findFirst(classT);
    }

    @Override
    public void save(Object entity) throws DbException {
        getDbUtils().save(entity);
    }

    @Override
    public void saveOrUpdate(Object entity) throws DbException {
        getDbUtils().saveOrUpdate(entity);

    }

    @Override
    public void saveOrUpdateAll(List<T> list) throws DbException {
        getDbUtils().saveOrUpdateAll(list);
    }

    @Override
    public void saveBindingId(Object entity) throws DbException {
        getDbUtils().saveBindingId(entity);
    }

    @Override
    public void saveAll(List<T> entitys) throws DbException {
        getDbUtils().saveAll(entitys);
    }

    @Override
    public void delete(Object entity) throws DbException {
        getDbUtils().delete(entity);
    }

    @Override
    public void deleteAll() throws DbException {
        getDbUtils().deleteAll(classT);
    }

    public void deleteAll(List<T> entities) throws DbException {
        getDbUtils().deleteAll(entities);
    }

    @Override
    public void delete(WhereBuilder whereBuilder) throws DbException {
        getDbUtils().delete(classT, whereBuilder);
    }

    @Override
    public void update(Object entity) throws DbException {
        update(entity, nullStrs);
    }

    @Override
    public void update(Object entity, String... updateColumnNames) throws DbException {
        getDbUtils().update(entity, updateColumnNames);
    }

    @Override
    public void updateAll(List<?> entities) throws DbException {
        updateAll(entities, nullStrs);
    }

    @Override
    public void updateAll(List<?> entities, String... updateColumnNames) throws DbException {
        getDbUtils().updateAll(entities, updateColumnNames);
    }

    @Override
    public void update(WhereBuilder whereBuilder, List<KeyValue> updateKeyvalues) throws DbException {
        getDbUtils().update(entity, whereBuilder, updateKeyvalues);

    }

    @Override
    public void update(WhereBuilder whereBuilder, KeyValue updateKeyvalue) throws DbException {
        List<KeyValue> list = new ArrayList<KeyValue>();
        list.add(updateKeyvalue);
        getDbUtils().update(entity, whereBuilder, list);
    }

    public long count(Selector selector) throws DbException {
        return getDbUtils().count(selector);
    }

    public long count(Class<?> entityType) throws DbException {
        return count(Selector.from(entityType));
    }

    public void realseDB() {
        getDbUtils().close();
    }

    private DbUtils getDbUtils() {
        DbUtils dbUtils = null;
        if (this.dbFullPath != null && !this.dbFullPath.equals("")) {
            dbUtils = DbUtils.create(mContext, dbFullPath, getVersion(), new DbUpgradeListener() {

                @Override
                public void onUpgrade(DbUtils db, int oldVersion, int newVersion) {
                    onUpdate(db, oldVersion, newVersion);
                }

            });
        } else if (sdCardPath != null && !sdCardPath.equals("") && dbName != null && !dbName.equals("")) {
            dbUtils = DbUtils.create(mContext, sdCardPath, dbName, getVersion(), new DbUpgradeListener() {

                @Override
                public void onUpgrade(DbUtils db, int oldVersion, int newVersion) {
                    onUpdate(db, oldVersion, newVersion);
                }

            });
        } else {
            dbUtils = DbUtils.create(mContext, "xUtils.db", getVersion(), new DbUpgradeListener() {

                @Override
                public void onUpgrade(DbUtils db, int oldVersion, int newVersion) {
                    onUpdate(db, oldVersion, newVersion);
                }

            });
        }
        dbUtils.configAllowTransaction(allowTransaction);
        return dbUtils;
    }

    /**
     * 设置事务的开关。默认是关闭状态！
     *
     * @param configAllowTransaction
     * @author zqs
     * @createTime 2014年8月30日 下午1:26:08
     */
    public void setConfigAllowTransaction(boolean configAllowTransaction) {
        this.allowTransaction = configAllowTransaction;
    }

    public abstract void onUpdate(DbUtils db, int oldVersion, int newVersion);

    public abstract int getVersion();

}
