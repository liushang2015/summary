package com.liushang.serivce;

import com.liushang.entity.User1;
import com.liushang.mapper.User1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class User1ServiceImpl implements  User1Service {
    @Autowired
    public User1Mapper user1Mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(User1 user){
        user1Mapper.insert(user);
    }

    @Override
    public void addRequiredException(User1 user) {

    }

    @Override
    public void addSupports(User1 user) {

    }

    @Override
    public void addSupportsException(User1 user) {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNew(User1 user){
        user1Mapper.insert(user);
    }

    @Override
    public void addRequiresNewException(User1 user) {

    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void addNested(User1 user){
        user1Mapper.insert(user);
    }

    @Override
    public void addNestedException(User1 user) {

    }

    @Override
    public void addNotSupported(User1 user) {

    }

    @Override
    public void addNotSupportedException(User1 user) {

    }

    @Override
    public void addMandatory(User1 user) {

    }

    @Override
    public void addMandatoryException(User1 user) {

    }

    @Override
    public void addNever(User1 user) {

    }

    @Override
    public void addNeverException(User1 user) {

    }

}
