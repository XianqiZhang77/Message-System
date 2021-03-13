package com.ray.message.utils;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordUtilTest {

    @Test
    public void testEncrypt() {
        String encryptedString = PasswordUtil.encrypt("123456");
        assertEquals("701AEA5CD1EFCACBAABF07DC4B743923", encryptedString);
    }

}
