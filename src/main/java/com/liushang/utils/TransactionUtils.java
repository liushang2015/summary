package com.liushang.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * @ClassName TransactionUtils
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
@Component
public class TransactionUtils {
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    /**
     * begin事务
     *
     * @return
     */
    public TransactionStatus begin() {
        TransactionStatus transaction = dataSourceTransactionManager.
                getTransaction(new DefaultTransactionAttribute());
        return transaction;
    }

    /**
     * 提交事务
     */
    public void commit(TransactionStatus transaction) {
        dataSourceTransactionManager.commit(transaction);
    }

    /**
     * 回滚事务
     *
     * @param transaction
     */
    public void rollback(TransactionStatus transaction) {
        dataSourceTransactionManager.rollback(transaction);
    }
    /**
     *
     * 事务开启
     */

}
