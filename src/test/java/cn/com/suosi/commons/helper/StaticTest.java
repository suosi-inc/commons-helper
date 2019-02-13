package cn.com.suosi.commons.helper;

import org.junit.Assert;
import org.junit.Test;

import static cn.com.suosi.commons.helper.Static.*;


public class StaticTest {
    @Test
    public void testTime() {
        System.out.println("time:" + time());
    }

    @Test
    public void testMicrotime() {
        System.out.println("microtime:" + microtime());
    }

    @Test
    public void testMaxMemory() {
        System.out.println("totalMemory:" + maxMemory());
    }

    @Test
    public void testTotalMemory() {
        System.out.println("totalMemory:" +  totalMemory("M"));
    }

    @Test
    public void testUsedMemory() {
        System.out.println("usedMemory:" + usedMemory("M"));
    }

    @Test
    public void testDate() {
        System.out.println("date:" + date());
    }

    @Test
    public void testMd5() {
        Assert.assertEquals(md5("123456"), "e10adc3949ba59abbe56e057f20f883e");

        System.out.println("md5:" + md5("123456"));
    }

    @Test
    public void testSha256() {
        Assert.assertEquals(sha256("123456"), "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92");

        System.out.println("sha1:" + sha256("123456"));
    }

    @Test
    public void testBase64() {
        Assert.assertEquals(base64Decode(base64Encode("img/logo.png")), "img/logo.png");

        System.out.println("base64:" + base64Decode(base64Encode("img/logo.png")));
    }

    @Test
    public void testBase64Safe() {
        Assert.assertEquals(base64Decode(base64SafeEncode("img/logo.png")), "img/logo.png");

        System.out.println("base64Safe:" + base64Decode(base64SafeEncode("img/logo.png")));
    }

    @Test
    public void testBase64Encode() {
        Assert.assertEquals(base64Encode("img/logo.png"), "aW1nL2xvZ28ucG5n");

        System.out.println("base64Encode:" + base64Encode("img/logo.png"));
    }
}