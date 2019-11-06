package com.github.suosi.commons.helper;

import org.junit.Assert;
import org.junit.Test;


public class StaticTest {
    @Test
    public void testTime() {
        System.out.println("time:" + Static.time());
    }

    @Test
    public void testMicrotime() {
        System.out.println("microtime:" + Static.microtime());
    }

    @Test
    public void testMaxMemory() {
        System.out.println("totalMemory:" + Static.maxMemory());
    }

    @Test
    public void testTotalMemory() {
        System.out.println("totalMemory:" + Static.totalMemory("M"));
    }

    @Test
    public void testUsedMemory() {
        System.out.println("usedMemory:" + Static.usedMemory("M"));
    }

    @Test
    public void testDate() {
        System.out.println("date:" + Static.date());
        System.out.println("date:" + Static.date(1573025736L));
    }

    @Test
    public void testMd5() {
        Assert.assertEquals(Static.md5("123456"), "e10adc3949ba59abbe56e057f20f883e");

        System.out.println("md5:" + Static.md5("123456"));
    }

    @Test
    public void testSha256() {
        Assert.assertEquals(Static.sha256("123456"), "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92");

        System.out.println("sha1:" + Static.sha256("123456"));
    }

    @Test
    public void testBase64() {
        Assert.assertEquals(Static.base64Decode(Static.base64Encode("img/logo.png")), "img/logo.png");

        System.out.println("base64:" + Static.base64Decode(Static.base64Encode("img/logo.png")));
    }

    @Test
    public void testBase64Safe() {
        Assert.assertEquals(Static.base64Decode(Static.base64SafeEncode("img/logo.png")), "img/logo.png");

        System.out.println("base64Safe:" + Static.base64Decode(Static.base64SafeEncode("img/logo.png")));
    }

    @Test
    public void testBase64Encode() {
        Assert.assertEquals(Static.base64Encode("img/logo.png"), "aW1nL2xvZ28ucG5n");

        System.out.println("base64Encode:" + Static.base64Encode("img/logo.png"));
    }
}
