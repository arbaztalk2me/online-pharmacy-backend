package com.pharmacyPortal.Exception;

public class MedicineNotFoundException extends RuntimeException{
    public MedicineNotFoundException(String msg){
        super(msg);
    }
}
