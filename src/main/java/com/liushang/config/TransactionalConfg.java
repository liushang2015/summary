package com.liushang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * @ClassName DatasouseConfg
 * @Author
 * @Version V1.0
 **/
@Component
public class TransactionalConfg {

    /**
     * 获取事务管理器
     */
    @Autowired
    private    DataSourceTransactionManager dataSourceTransactionManager;

    // begin 开启事务 commit 提交事务 回滚事务、
    public TransactionStatus beign() {
        // 使用默认的传播行为
        return dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
    }

    /**
     * 提交事务
     *
     * @param transactionStatus
     */
    public void commit(TransactionStatus transactionStatus) {
        dataSourceTransactionManager.commit(transactionStatus);
//        dataSourceTransactionManager.rollback();
    }

    /*
    回滚事务
     */
    public void rollback(TransactionStatus transactionStatus) {
        dataSourceTransactionManager.rollback(transactionStatus);
    }

}
