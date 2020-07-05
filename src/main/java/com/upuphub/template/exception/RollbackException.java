package com.upuphub.template.exception;

/**
 * 数据回滚异常
 *
 * @author 王志立
 */
public class RollbackException extends RuntimeException {
    public RollbackException(String message) {
        super(message);
    }

    public RollbackException(Throwable e) {
        super(e);
    }
}
