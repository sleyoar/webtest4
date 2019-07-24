package com.util;

import java.util.UUID;

public class CodeUtil {
    public static String generateUniqueCode() {
        //生成唯一的激活码
        return UUID.randomUUID().toString().replace("-", "");
    }
}
