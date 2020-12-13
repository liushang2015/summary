package com.liushang.transactional;

import com.liushang.config.TransactionalConfg;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

@Component
@Slf4j
@Aspect
public class ExtTransactionAop {

    @Autowired
    private TransactionalConfg transactionalConfg;

    @Around(value = "@annotation(com.liushang.transactional.LSTransactional)")
    public void around(ProceedingJoinPoint joinPoint) {
        TransactionStatus beign = null;
        try {
            // 目标方法insertMember2
            beign = transactionalConfg.beign();
            joinPoint.proceed();
            transactionalConfg.commit(beign);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            if (beign != null) {
                transactionalConfg.rollback(beign);
            }
        }
    }
}
