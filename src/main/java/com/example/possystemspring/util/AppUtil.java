package com.example.possystemspring.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String generateItemId(){
        return "NOTE-"+UUID.randomUUID();
    }
    public static String generateCusId(){
        return "USER-"+UUID.randomUUID();
    }
    public static String generateOrderId() {
        return "Or-"+ UUID.randomUUID();
    }
    public static String generateOrderDetailId() {
        return "OD-"+ UUID.randomUUID();
    }
}
