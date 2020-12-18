package com.liushang.serivce;

import com.liushang.entity.User1;

public interface User1Service {
    /**
     * 1、PROPAGATION_REQUIRED
     */
    public void addRequired(User1 user);
    public void addRequiredException(User1 user);

    public void addSupports(User1 user);
    public void addSupportsException(User1 user);

    public void addRequiresNew(User1 user);
    public void addRequiresNewException(User1 user);


    public void addNested(User1 user);
    public void addNestedException(User1 user);

    public void addNotSupported(User1 user);
    public void addNotSupportedException(User1 user);

    public void addMandatory(User1 user);
    public void addMandatoryException(User1 user);

    public void addNever(User1 user);
    public void addNeverException(User1 user);

}
