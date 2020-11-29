package time;


import lombok.NonNull;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * DateTimeUtil
 *
 * @author zad
 * @create 2018-09-08 15:50
 */
public final class DateTimeUtil {

    private static final ConcurrentMap<String, DateTimeFormatter> FORMATTER_CACHE = new ConcurrentHashMap<>(16);

    private static final int PATTERN_CACHE_SIZE = 50;

    public static String DEFAULT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE = "yyyy-MM-dd";
    public static final String DEFAULT_TIME = "HH:mm:ss";

    public static final String SLASH_DATE_TIME = "yyyy/MM/dd HH:mm:ss";
    public static final String SLASH_DATE = "yyyy/MM/dd";

    private static final int ONE_YEAR_MONTHS = 12;

    private DateTimeUtil() {
        throw new AssertionError("Util禁止反射实例化");
    }

    //====================== convert =========================== //

    public static Date strToDate(@NonNull String dateTime, @NonNull String pattern) {
        return Date.from(getInstant(dateTime, pattern));
    }

    public static Date strToDate(@NonNull String dateTime) {
        return Date.from(getInstant(dateTime, DEFAULT_DATE_TIME));
    }

    public static String dateToStr(@NonNull Date dateTime, @NonNull String pattern) {
        return format(LocalDateTime.ofInstant(dateTime.toInstant(), ZoneId.systemDefault()), pattern);
    }

    public static String dateToStr(@NonNull Date dateTime) {
        return dateToStr(dateTime, DEFAULT_DATE_TIME);
    }


    public static LocalDateTime dateToLocalDateTime(@NonNull Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }


    public static LocalDate dateToLocalDate(@NonNull Date date) {
        LocalDateTime localDateTime = LocalDateTime
                .ofInstant(date.toInstant(), ZoneId.systemDefault());
        return localDateTime.toLocalDate();
    }


    public static LocalTime dateToLocalTime(@NonNull Date date) {
        LocalDateTime localDateTime = LocalDateTime
                .ofInstant(date.toInstant(), ZoneId.systemDefault());
        return localDateTime.toLocalTime();
    }


    public static Date localDateTimeToDate(@NonNull LocalDateTime localDateTime) {
        Instant instant = localDateTime
                .atZone(ZoneId.systemDefault())
                .toInstant();
        return Date.from(instant);
    }


    public static Date localDateToDate(@NonNull LocalDate localDate) {
        Instant instant = localDate
                .atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant();
        return Date.from(instant);
    }


    public static Date localTimeToDate(@NonNull LocalTime localTime) {
        Instant instant = LocalDateTime
                .of(LocalDate.now(), localTime)
                .atZone(ZoneId.systemDefault())
                .toInstant();
        return Date.from(instant);
    }


    // ========================= format ============================== //

    public static Date getSpecificDateTime(int year, int monthOfYear, int dayOfMonth, int hourOfDay,
                                           int minuteOfHour, int secondOfMinute) {
        LocalDateTime localDateTime = LocalDateTime.of(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date getDay(@NonNull Date date) {
        return localDateToDate(dateToLocalDate(date));
    }

    public static Date withDate(@NonNull Date date, int year, int monthOfYear, int dayOfMonth) {
        LocalDateTime localDateTime = dateToLocalDateTime(date).withYear(year).withMonth(monthOfYear).withDayOfMonth(dayOfMonth);
        return localDateTimeToDate(localDateTime);
    }

    public static Date withTime(@NonNull Date date, int hourOfDay, int minuteOfHour, int secondsOfMinute, int millisOfSecond) {
        LocalDateTime localDateTime = dateToLocalDateTime(date).withHour(hourOfDay).withMinute(minuteOfHour).withSecond(secondsOfMinute).withNano(millisOfSecond);
        return localDateTimeToDate(localDateTime);
    }

    public static Date withYear(@NonNull Date date, int year) {
        LocalDateTime localDateTime = dateToLocalDateTime(date).withYear(year);
        return localDateTimeToDate(localDateTime);
    }

    public static Date withMonthOfYear(@NonNull Date date, int monthOfYear) {
        LocalDateTime localDateTime = dateToLocalDateTime(date).withMonth(monthOfYear);
        return localDateTimeToDate(localDateTime);
    }

    public static Date withDayOfYear(@NonNull Date date, int dayOfYear) {
        LocalDateTime localDateTime = dateToLocalDateTime(date).withDayOfYear(dayOfYear);
        return localDateTimeToDate(localDateTime);
    }

    public static Date withDayOfMonth(@NonNull Date date, int dayOfMonth) {
        LocalDateTime localDateTime = dateToLocalDateTime(date).withDayOfMonth(dayOfMonth);
        return localDateTimeToDate(localDateTime);
    }

    public static Date withDayOfWeek(@NonNull Date date, int dayOfWeek) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        int day = localDateTime.getDayOfWeek().getValue();
        return localDateTimeToDate(localDateTime.plusDays(dayOfWeek - day));
    }

    public static Date withHourOfDay(@NonNull Date date, int hour) {
        LocalDateTime localDateTime = dateToLocalDateTime(date).withHour(hour);
        return localDateTimeToDate(localDateTime);
    }

    public static Date withMinuteOfHour(@NonNull Date date, int minute) {
        LocalDateTime localDateTime = dateToLocalDateTime(date).withMinute(minute);
        return localDateTimeToDate(localDateTime);
    }

    public static Date withSecondOfMinute(@NonNull Date date, int second) {
        LocalDateTime localDateTime = dateToLocalDateTime(date).withSecond(second);
        return localDateTimeToDate(localDateTime);
    }


    public static Date withMinuteAndSecond(@NonNull Date date, int minute, int second) {
        LocalDateTime localDateTime = dateToLocalDateTime(date).withMinute(minute).withSecond(second);
        return localDateTimeToDate(localDateTime);
    }


    // ========================= calculate =========================== //

    public static Date plusYears(@NonNull Date date, int years) {
        LocalDateTime localDateTime = dateToLocalDateTime(date).plusYears(years);
        return localDateTimeToDate(localDateTime);
    }

    public static Date minusYears(@NonNull Date date, int years) {
        return plusYears(date, -years);
    }

    public static Date plusMonths(@NonNull Date date, int months) {
        LocalDateTime localDateTime = dateToLocalDateTime(date).plusMonths(months);
        return localDateTimeToDate(localDateTime);
    }

    public static Date minusMonths(@NonNull Date date, int months) {
        return plusMonths(date, -months);
    }

    public static Date plusWeeks(@NonNull Date date, int weeks) {
        LocalDateTime localDateTime = dateToLocalDateTime(date).plusWeeks(weeks);
        return localDateTimeToDate(localDateTime);
    }

    public static Date minusWeeks(@NonNull Date date, int weeks) {
        return plusWeeks(date, -weeks);
    }

    public static Date plusDays(@NonNull Date date, int days) {
        LocalDateTime localDateTime = dateToLocalDateTime(date).plusDays(days);
        return localDateTimeToDate(localDateTime);
    }

    public static Date minusDays(@NonNull Date date, int days) {
        return plusDays(date, -days);
    }

    public static Date plusHours(@NonNull Date date, int hours) {
        LocalDateTime localDateTime = dateToLocalDateTime(date).plusHours(hours);
        return localDateTimeToDate(localDateTime);
    }

    public static Date minusHours(@NonNull Date date, int hours) {
        return plusHours(date, -hours);
    }

    public static Date plusMinutes(@NonNull Date date, int minutes) {
        LocalDateTime localDateTime = dateToLocalDateTime(date).plusMinutes(minutes);
        return localDateTimeToDate(localDateTime);
    }

    public static Date minusMinutes(@NonNull Date date, int minutes) {
        return plusMinutes(date, -minutes);
    }

    public static Date plusSeconds(@NonNull Date date, int seconds) {
        LocalDateTime localDateTime = dateToLocalDateTime(date).plusSeconds(seconds);
        return localDateTimeToDate(localDateTime);
    }

    public static Date minusSeconds(@NonNull Date date, int seconds) {
        return plusSeconds(date, -seconds);
    }

    public static Date plusTime(@NonNull Date date, int years, int months, int days, int hours, int minutes, int seconds, int millis) {
        LocalDateTime localDateTime = dateToLocalDateTime(date)
                .plusYears(years).plusMonths(months).plusDays(days)
                .plusHours(hours).plusMinutes(minutes).plusSeconds(seconds);
        return localDateTimeToDate(localDateTime);
    }

    public static Date minusTime(@NonNull Date date, int years, int months, int days, int hours, int minutes, int seconds, int millis) {
        return plusTime(date, -years, -months, -days, -hours, -minutes, -seconds, -millis);
    }

    public static int getDayOfYear(@NonNull Date date) {
        return dateToLocalDateTime(date).getDayOfYear();
    }

    public static int getDayOfMonth(@NonNull Date date) {
        return dateToLocalDateTime(date).getDayOfMonth();
    }

    public static int getDayOfWeek(@NonNull Date date) {
        return dateToLocalDateTime(date).getDayOfWeek().getValue();
    }

    public static int getYear(@NonNull Date date) {
        return dateToLocalDateTime(date).getYear();
    }

    public static int getMonthOfYear(@NonNull Date date) {
        return dateToLocalDateTime(date).getMonthValue();
    }

    public static int getHourOfDay(@NonNull Date date) {
        return dateToLocalDateTime(date).getHour();
    }

    public static int getMinuteOfDay(@NonNull Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime.getHour() * (int) TimeUnit.HOURS.toMinutes(1) + localDateTime.getMinute();
    }

    public static int getMinuteOfHour(@NonNull Date date) {
        return dateToLocalDateTime(date).getMinute();
    }

    public static int getSecondOfDay(@NonNull Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        int res = localDateTime.getHour() * (int) TimeUnit.HOURS.toSeconds(1)
                + localDateTime.getMinute() * (int) TimeUnit.MINUTES.toSeconds(1)
                + localDateTime.getSecond();
        return res;
    }

    public static int getSecondOfMinute(@NonNull Date date) {
        return dateToLocalDateTime(date).getSecond();
    }


    // ========================= compare ============================= //

    public static Date max(@NonNull Date date1, @NonNull Date date2) {
        return getMaxOrMinDate(date1, date2, true);
    }


    public static Date min(@NonNull Date date1, @NonNull Date date2) {
        return getMaxOrMinDate(date1, date2, false);
    }

    private static Date getMaxOrMinDate(@NonNull Date date1, @NonNull Date date2, boolean max) {
        if (max) {
            return date1.compareTo(date2) >= 0 ? date1 : date2;
        } else {
            return date1.compareTo(date2) >= 0 ? date2 : date1;
        }
    }


    public static boolean isBetween(@NonNull Date date, @NonNull Date begin, @NonNull Date end) {
        return date.after(begin) && end.after(date);
    }

    public static int getDifferentYears(@NonNull Date minuend, @NonNull Date subtrahend) {
        LocalDateTime minuendLocalDateTime = dateToLocalDateTime(minuend);
        LocalDateTime subtrahendLocalDateTime = dateToLocalDateTime(subtrahend);
        return minuendLocalDateTime.getYear() - subtrahendLocalDateTime.getYear();
    }

    public static int getDifferentMonths(@NonNull Date minuend, @NonNull Date subtrahend) {
        LocalDate minuendLocalDate = dateToLocalDate(minuend);
        LocalDate subtrahendLocalDate = dateToLocalDate(subtrahend);
        Period period = getPeriod(minuendLocalDate, subtrahendLocalDate);
        return period.getMonths() + period.getYears() * ONE_YEAR_MONTHS;
    }


    public static int getDifferentWeeks(@NonNull Date minuend, @NonNull Date subtrahend) {
        return (int) ((minuend.getTime() - subtrahend.getTime()) / TimeUnit.DAYS.toMillis(7));
    }


    public static int getDifferentDays(@NonNull Date minuend, @NonNull Date subtrahend) {
        return (int) ((minuend.getTime() - subtrahend.getTime()) / TimeUnit.DAYS.toMillis(1));
    }


    public static int getDifferentHours(@NonNull Date minuend, @NonNull Date subtrahend) {
        return (int) ((minuend.getTime() - subtrahend.getTime()) / TimeUnit.HOURS.toMillis(1));
    }


    public static long getDifferentMinutes(@NonNull Date minuend, @NonNull Date subtrahend) {
        return (minuend.getTime() - subtrahend.getTime()) / TimeUnit.MINUTES.toMillis(1);
    }


    public static long getDifferentSeconds(@NonNull Date minuend, @NonNull Date subtrahend) {
        return (minuend.getTime() - subtrahend.getTime()) / TimeUnit.SECONDS.toMillis(1);
    }

    private static Duration getDuration(LocalDateTime minuend, LocalDateTime subtrahend) {
        return Duration.between(minuend, subtrahend);
    }

    private static Period getPeriod(LocalDate minuend, LocalDate subtrahend) {
        return Period.between(minuend, subtrahend);
    }


    private static String format(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = getFormatter(pattern);
        return localDateTime.format(formatter);
    }

    private static DateTimeFormatter getFormatter(String pattern) {
        if (Objects.isNull(pattern)) {
            throw new IllegalArgumentException("Invalid pattern specification");
        }
        DateTimeFormatter formatter = FORMATTER_CACHE.get(pattern);
        if (formatter == null) {
            if (FORMATTER_CACHE.size() < PATTERN_CACHE_SIZE) {
                formatter = DateTimeFormatter.ofPattern(pattern);
                DateTimeFormatter oldFormatter = FORMATTER_CACHE.putIfAbsent(pattern, formatter);
                if (oldFormatter != null) {
                    formatter = oldFormatter;
                }
            }
        }

        return formatter;
    }

    private static Instant getInstant(String time, String pattern) {
        return LocalDateTime
                .parse(time, getFormatter(pattern))
                .atZone(ZoneId.systemDefault())
                .toInstant();
    }

}
