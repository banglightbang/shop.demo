package com.tv.bang.orders.command.order;

public class OrderAlreadyCheckedOutException extends Exception {

    public OrderAlreadyCheckedOutException(String msg) {
        super(msg);
    }
}
