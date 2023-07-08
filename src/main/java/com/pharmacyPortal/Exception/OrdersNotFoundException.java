package com.pharmacyPortal.Exception;

public class OrdersNotFoundException extends RuntimeException{
    public OrdersNotFoundException(String msg){
        super(msg);
    }
}
