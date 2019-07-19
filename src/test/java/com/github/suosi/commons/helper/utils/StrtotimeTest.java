package com.github.suosi.commons.helper.utils;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static com.github.suosi.commons.helper.Static.date;

public class StrtotimeTest {

    public static final String dtString = "2015-04-06 16:03:03";
    public static final String dtStringPlus = "2015-04-07 16:03:03";
    public static final String dtStringMinus = "2015-04-05 16:03:03";

    @Test
    public void test() {
        System.out.println(date("yyyy-MM-dd HH:mm:ss", Strtotime.parse("+1 minutes", Strtotime.parse("2015-04-06 16:03:03"))));
        System.out.println(date("yyyy-MM-dd HH:mm:ss", Strtotime.parse("-1 weeks", Strtotime.parse("2015-04-06 16:03:03"))));
        System.out.println(date("yyyy-MM-dd HH:mm:ss", Strtotime.parse("+2 year", Strtotime.parse("2015-04-06 16:03:03"))));
        System.out.println(date("yyyy-MM-dd HH:mm:ss", Strtotime.parse("+3 day", Strtotime.parse("2015-04-06 16:03:03"))));
        System.out.println(date("yyyy-MM-dd HH:mm:ss", Strtotime.parse("+4 month", Strtotime.parse("2015-04-06 16:03:03"))));
        System.out.println(date("yyyy-MM-dd HH:mm:ss", Strtotime.parse("+4 seconds", Strtotime.parse("2015-04-06 16:03:03"))));
    }

    @Test
    public void testParse1() {
        LocalDateTime ldt = LocalDateTime.parse(dtString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        long timeStamp = ldt.toEpochSecond(ZoneOffset.ofHours(8));
        System.out.println(timeStamp);
        System.out.println(Strtotime.parse("2019-6-22 9:35:36"));
        Assert.assertEquals(Strtotime.parse("2015-04-06T16:03:03Z"), timeStamp);
        Assert.assertEquals(Strtotime.parse("2015-04-06T16:03:03.123"), timeStamp);
        Assert.assertEquals(Strtotime.parse("2015-04-06T16:03:03"), timeStamp);
        Assert.assertEquals(Strtotime.parse("2015-04-06 16:03:03"), timeStamp);
        Assert.assertEquals(Strtotime.parse("2015-04-06 16:03"), timeStamp - 3);
        Assert.assertEquals(Strtotime.parse("2015-4-6 16:3:3"), timeStamp);
        Assert.assertEquals(Strtotime.parse("2015-4-06 16:03:3"), timeStamp);
        Assert.assertEquals(Strtotime.parse("2015-4-6 16:03:03"), timeStamp);
        Assert.assertEquals(Strtotime.parse("2015/04/06 16:03:03"), timeStamp);
        Assert.assertEquals(Strtotime.parse("2015年04月06日 16时03分03秒"), timeStamp);
        Assert.assertEquals(Strtotime.parse("2015年4月6日 16时03分3秒"), timeStamp);
        Assert.assertEquals(Strtotime.parse("2015年04月6日 16时03分3秒"), timeStamp);
        Assert.assertEquals(Strtotime.parse("2015年4月6日 16时03分"), timeStamp - 3);
    }

    @Test
    public void testParse2() {
        Assert.assertEquals(Strtotime.parse("+1 day", Strtotime.parse(dtString)), Strtotime.parse(dtStringPlus));
        Assert.assertEquals(Strtotime.parse("+1 days", Strtotime.parse(dtString)), Strtotime.parse(dtStringPlus));
        Assert.assertEquals(Strtotime.parse("-1 day", Strtotime.parse(dtString)), Strtotime.parse(dtStringMinus));
        Assert.assertEquals(Strtotime.parse("+24 hours", Strtotime.parse(dtString)), Strtotime.parse(dtStringPlus));
        Assert.assertEquals(Strtotime.parse("+24 hour", Strtotime.parse(dtString)), Strtotime.parse(dtStringPlus));
        Assert.assertEquals(Strtotime.parse("+1440 minutes", Strtotime.parse(dtString)), Strtotime.parse(dtStringPlus));
    }
}