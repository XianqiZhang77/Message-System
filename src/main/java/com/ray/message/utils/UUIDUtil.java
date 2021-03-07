package com.ray.message.utils;

import java.util.UUID;

public class UUIDUtil {

    public static String getToken() {
        return UUID.randomUUID().toString();
    }
}
