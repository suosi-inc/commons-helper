package com.github.suosi.commons.helper;

import com.github.suosi.commons.helper.utils.Strtotime;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 *
 *
 * @author niuchaoqun
 */
public class Static {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 返回当前 Unix 时间戳（毫秒）
     *
     * @return
     */
    public static long microtime() {
        return System.currentTimeMillis();
    }

    /**
     * 返回当前 Unix 时间戳（秒）
     *
     * @return
     */
    public static long time() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 返回系统空闲内存
     *
     * @return
     */
    public static long freeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * 返回系统最大内存
     *
     * @return
     */
    public static long maxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    /**
     * 返回系统当前使用内存
     *
     * @return
     */
    public static long usedMemory() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    /**
     * 返回系统当前占用内存
     *
     * @return
     */
    public static long totalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    /**
     * 格式化返回系统当前空闲内存
     *
     * @param format
     * @return
     */
    public static long freeMemory(final String format) {
        long memory = Runtime.getRuntime().freeMemory();
        return format != null ? sizeFormat(memory, format) : memory;

    }

    /**
     * 格式化返回系统使用空闲内存
     *
     * @param format
     * @return
     */
    public static long usedMemory(final String format) {
        long memory = totalMemory() - freeMemory();
        return format != null ? sizeFormat(memory, format) : memory;
    }

    /**
     * 格式化返回系统当前最大内存
     *
     * @param format
     * @return
     */
    public static long maxMemory(final String format) {
        long memory = Runtime.getRuntime().maxMemory();
        return format != null ? sizeFormat(memory, format) : memory;
    }

    /**
     * 格式化返回系统当前占用内存
     *
     * @param format
     * @return
     */
    public static long totalMemory(final String format) {
        long memory = Runtime.getRuntime().totalMemory();
        return format != null ? sizeFormat(memory, format) : memory;
    }

    /**
     * 格式化转换大小
     *
     * @param bytes
     * @param suffix
     * @return
     */
    public static long sizeFormat(long bytes, final String suffix) {
        if (bytes > 0) {
            switch (suffix.toUpperCase()) {
                case "K" :
                    return (bytes / 1024);
                case "M" :
                    return (bytes / 1024 / 1024);
                case "G" :
                    return (bytes / 1024 / 1024 / 1024);
                case "T" :
                    return (bytes / 1024 / 1024 / 1024 / 1024);
                default :
                    return bytes;
            }
        }
        return 0;
    }

    /**
     * 根据格式化参数返回时间字符串
     *
     * @return
     */
    public static String date() {
        return date(DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 根据格式化参数返回时间字符串
     *
     * @return
     */
    public static String date(final long time) {
        return date(DEFAULT_DATETIME_FORMAT, time);
    }

    /**
     * 根据格式化参数返回时间字符串
     *
     * @param pattern
     * @return
     */
    public static String date(final String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 根据格式化参数，时间戳参数，返回时间字符串
     *
     * @param pattern
     * @param time
     * @return
     */
    public static String date(final String pattern, final long time) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(Math.abs(time)), ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * MD5
     *
     * @param data
     * @return
     */
    public static String md5(final String data) {
        return DigestUtils.md5Hex(data);
    }

    /**
     * MD5
     *
     * @param data
     * @return
     */
    public static String md5(final byte[] data) {
        return DigestUtils.md5Hex(data);
    }

    /**
     * Sha1
     *
     * @param data
     * @return
     */
    public static String sha1(final String data) {
        return DigestUtils.sha1Hex(data);
    }

    /**
     * Sha1
     *
     * @param data
     * @return
     */
    public static String sha1(final byte[] data) {
        return DigestUtils.sha1Hex(data);
    }

    /**
     * Sha256
     *
     * @param data
     * @return
     */
    public static String sha256(final String data) {
        return DigestUtils.sha256Hex(data);
    }

    /**
     * Sha256
     *
     * @param data
     * @return
     */
    public static String sha256(final byte[] data) {
        return DigestUtils.sha256Hex(data);
    }

    /**
     * base64Encode
     *
     * @param data
     * @return
     */
    public static String base64Encode(final String data) {
        return Base64.encodeBase64String(data.getBytes());
    }

    /**
     * base64Encode
     *
     * @param data
     * @return
     */
    public static String base64Encode(final byte[] data) {
        return Base64.encodeBase64String(data);
    }

    /**
     * base64Decode
     *
     * @param data
     * @return
     */
    public static String base64Decode(final String data) {
        return new String(Base64.decodeBase64(data));
    }

    /**
     * base64Decode
     *
     * @param data
     * @return
     */
    public static String base64Decode(final byte[] data) {
        return new String(Base64.decodeBase64(data));
    }

    /**
     * base64SafeEncode
     *
     * @param data
     * @return
     */
    public static String base64SafeEncode(final String data) {
        return Base64.encodeBase64URLSafeString(data.getBytes());
    }

    /**
     * base64SafeEncode
     *
     * @param data
     * @return
     */
    public static String base64SafeEncode(final byte[] data) {
        return Base64.encodeBase64URLSafeString(data);
    }

    /**
     * 返回当前默认时间戳
     *
     * @return
     */
    public static long strtotime() {
        return Instant.now().getEpochSecond();
    }

    /**
     * 通用格式日期时间字符串自动转换时间戳
     *
     * @param input
     * @return
     */
    public static long strtotime(final String input) {
        return Strtotime.parse(input);
    }

    /**
     * 以提供的时间戳为基准，返回表达式计算后的时间戳
     *
     * @param input
     * @param unixStamp
     * @return
     */
    public static long strtotime(final String input, final Long unixStamp) {
        return Strtotime.parse(input, unixStamp);
    }
}
