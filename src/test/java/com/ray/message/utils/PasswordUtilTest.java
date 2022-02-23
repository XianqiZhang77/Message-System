package com.ray.message.utils;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordUtilTest {

    @Test
    public void testEncrypt() {
        String encryptedString = PasswordUtil.encrypt("123456");
        assertEquals("A78DE68013AE19C006D05EEB4EA84F39", encryptedString);
    }

}
