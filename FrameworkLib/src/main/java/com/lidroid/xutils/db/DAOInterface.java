package com.lidroid.xutils.db;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.db.table.KeyValue;
import com.lidroid.xutils.exception.DbException;

import java.util.List;

/**
 * Dao接口
 *
 * @param <T>
 * @author ylf
 * @createTime 2014年5月25日 上午11:36:01
 */
public interface DAOInterface<T extends Object> {

    public List<T> findAll() throws DbException;

    public List<T> findAll(Selector selector) throws DbException;

    public T findById(Object idValue) throws DbException;

    public T findFirst(Selector selector) throws DbException;

    public T findFirst() throws DbException;

    public void save(Object entity) throws DbException;

    public void saveOrUpdate(Object entity) throws DbException;

    public void saveOrUpdateAll(List<T> entity) throws DbException;

    public void saveBindingId(Object entity) throws DbException;

    public void saveAll(List<T> entitys) throws DbException;

    public void delete(Object entity) throws DbException;

    public void deleteAll() throws DbException;

    public void delete(WhereBuilder whereBuilder) throws DbException;

    public void update(Object entity) throws DbException;

    public void update(Object entity, String... updateColumnNames) throws DbException;

    public void update(WhereBuilder whereBuilder, List<KeyValue> updateKeyvalues) throws DbException;

    public void update(WhereBuilder whereBuilder, KeyValue updateKeyvalue) throws DbException;

    public void updateAll(List<?> entities) throws DbException;

    public void updateAll(List<?> entities, String... updateColumnNames) throws DbException;

    // public void updateAll(List<?> entities, WhereBuilder whereBuilder,
    // String... updateColumnNames) throws DbException;
}
