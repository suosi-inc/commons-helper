package cn.com.suosi.commons.helper.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 实现类似 PHP strtotime() 部分功能
 *
 * @author niuchaoqun
 */
public final class Strtotime {
    private static final List<Matcher> DT_MATCHERS;

    private static final List<Matcher> D_MATCHERS;

    public interface Matcher {
        LocalDateTime tryConvert(String input, Long time);
    }

    static  {
        D_MATCHERS = new LinkedList<>();
        D_MATCHERS.add(new DateFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        D_MATCHERS.add(new DateFormatMatcher(DateTimeFormatter.ofPattern("yyyy-M-d")));
        D_MATCHERS.add(new DateFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        D_MATCHERS.add(new DateFormatMatcher(DateTimeFormatter.ofPattern("yyyy/M/d")));
        D_MATCHERS.add(new DateFormatMatcher(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
        D_MATCHERS.add(new DateFormatMatcher(DateTimeFormatter.ofPattern("yyyy年M月d日")));
    }

    static {
        DT_MATCHERS = new LinkedList<>();

        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒")));

        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS")));

        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-M-d HH:m:s")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年M月d日 HH时m分s秒")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/M/d HH:m:s")));

        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));

        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd'T'HH:mm:ss'Z'")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd'T'HH:mm:ss")));

        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")));

        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd'T'HH:mm:ss.SSS")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd'T'HH:mm:ss.SSS'Z'")));

        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy-M-d HH:m")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy/M/d HH:m")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分")));
        DT_MATCHERS.add(new DateTimeFormatMatcher(DateTimeFormatter.ofPattern("yyyy年M月d日 HH时m分")));

        DT_MATCHERS.add(new Matcher() {
            private final Pattern days = Pattern.compile("[\\-\\+]?\\d+ day[s]?");
            @Override
            public LocalDateTime tryConvert(String input, Long time) {
                String exp = StringUtils.trim(input);
                if (StringUtils.isNotBlank(exp) && days.matcher(exp).matches()) {
                    int t = NumberUtils.toInt(exp.split(" ")[0]);
                    LocalDateTime ldt = LocalDateTime.now();
                    if (time != null) {
                        ldt = LocalDateTime.ofInstant(Instant.ofEpochSecond(Math.abs(time)), ZoneId.systemDefault());
                    }
                    return t != 0 ? ldt.plusDays(t) : ldt;
                }
                return null;
            }
        });

        DT_MATCHERS.add(new Matcher() {
            private final Pattern months = Pattern.compile("[\\-\\+]?\\d+ month[s]?");
            @Override
            public LocalDateTime tryConvert(String input, Long time) {
                String exp = StringUtils.trim(input);
                if (StringUtils.isNotBlank(exp) && months.matcher(exp).matches()) {
                    int t = NumberUtils.toInt(exp.split(" ")[0]);
                    LocalDateTime ldt = LocalDateTime.now();
                    if (time != null) {
                        ldt = LocalDateTime.ofInstant(Instant.ofEpochSecond(Math.abs(time)), ZoneId.systemDefault());
                    }
                    return t != 0 ? ldt.plusMonths(t) : ldt;
                }
                return null;
            }
        });

        DT_MATCHERS.add(new Matcher() {
            private final Pattern years = Pattern.compile("[\\-\\+]?\\d+ year[s]?");
            @Override
            public LocalDateTime tryConvert(String input, Long time) {
                String exp = StringUtils.trim(input);
                if (StringUtils.isNotBlank(exp) && years.matcher(exp).matches()) {
                    int t = NumberUtils.toInt(exp.split(" ")[0]);
                    LocalDateTime ldt = LocalDateTime.now();
                    if (time != null) {
                        ldt = LocalDateTime.ofInstant(Instant.ofEpochSecond(Math.abs(time)), ZoneId.systemDefault());
                    }

                    return t != 0 ? ldt.plusYears(t) : ldt;
                }
                return null;
            }
        });

    }

    private static class DateTimeFormatMatcher implements Matcher {

        private final DateTimeFormatter dateFormat;

        DateTimeFormatMatcher(DateTimeFormatter dateFormat) {
            this.dateFormat = dateFormat;
        }

        @Override
        public LocalDateTime tryConvert(String input, Long time) {
            try {
                return LocalDateTime.parse(input, this.dateFormat);
            } catch (DateTimeParseException ex) {
                return null;
            }
        }
    }

    private static class DateFormatMatcher implements Matcher {

        private final DateTimeFormatter dateFormat;

        DateFormatMatcher(DateTimeFormatter dateFormat) {
            this.dateFormat = dateFormat;
        }

        @Override
        public LocalDateTime tryConvert(String input, Long time) {
            try {
                LocalDate ld = LocalDate.parse(input, this.dateFormat);
                return LocalDateTime.of(ld, LocalTime.MIN);
            } catch (DateTimeParseException ex) {
                return null;
            }
        }
    }

    public static long parse(String input) {
        if (input != null) {
            LocalDateTime ldt;
            for (Matcher matcher : DT_MATCHERS) {
                ldt = matcher.tryConvert(input, null);
                if (ldt != null) {
                    return ldt.atZone(ZoneId.systemDefault()).toEpochSecond();
                }
            }

            for (Matcher matcher : D_MATCHERS) {
                ldt = matcher.tryConvert(input, null);
                if (ldt != null) {
                    return ldt.atZone(ZoneId.systemDefault()).toEpochSecond();
                }
            }
        }
        return 0L;
    }

    public static long parse(String input, Long time) {
        if (input != null) {
            LocalDateTime ldt;
            for (Matcher matcher : DT_MATCHERS) {
                ldt = matcher.tryConvert(input, time);

                if (ldt != null) {
                    return ldt.atZone(ZoneId.systemDefault()).toEpochSecond();
                }
            }

            for (Matcher matcher : D_MATCHERS) {
                ldt = matcher.tryConvert(input, time);
                if (ldt != null) {
                    return ldt.atZone(ZoneId.systemDefault()).toEpochSecond();
                }
            }
        }

        return 0L;
    }
}
