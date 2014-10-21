import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public final static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null)
            return false;
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE);
    }

    public final static boolean isSameHour(Date date1, Date date2) {
        if (date1 == null || date2 == null)
            return false;
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE)
                && cal1.get(Calendar.HOUR_OF_DAY) == cal2
                        .get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 比较两个时间戳是否为同一天
     * 
     * @param ts1
     *            单位ms
     * @param ts2
     *            单位ms
     * @return
     */
    public final static boolean isSameDay(long ts1, long ts2) {
        return isSameDay(new Date(ts1), new Date(ts2));
    }

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @SuppressWarnings("rawtypes")
    private static ThreadLocal dateThreadLocal = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat(DATE_FORMAT);
        }
    };

    public static DateFormat getDateFormat() {
        return (DateFormat) dateThreadLocal.get();
    }

    public static Date parseDate(String textDate) throws ParseException {
        return getDateFormat().parse(textDate);
    }

    public static String formatDate(Date date) {
        return getDateFormat().format(date);
    }

    public static String formatDate(long timeInMillis) {
        return getDateFormat().format(new Date(timeInMillis));
    }

    private static final String MIN_FORMAT = "yyyy-MM-dd HH:mm";

    @SuppressWarnings("rawtypes")
    private static ThreadLocal minThreadLocal = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat(MIN_FORMAT);
        }
    };

    public static DateFormat getMinFormat() {
        return (DateFormat) minThreadLocal.get();
    }

    public static Date parseMin(String textDate) throws ParseException {
        return getMinFormat().parse(textDate);
    }

    public static String formatMin(Date date) {
        return getMinFormat().format(date);
    }

    public static String formatMin(long timeInMillis) {
        return getMinFormat().format(new Date(timeInMillis));
    }
    
    private static final String HHMM_FORMAT = "HH:mm";

    @SuppressWarnings("rawtypes")
    private static ThreadLocal threadLocal = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new SimpleDateFormat(HHMM_FORMAT);
        }
    };

    public static DateFormat getHHmmFormat() {
        return (DateFormat) threadLocal.get();
    }

    public static Date parseHHmm(String textDate) throws ParseException {
        return getHHmmFormat().parse(textDate);
    }

    public static String formatHHmm(Date date) {
        return getHHmmFormat().format(date);
    }

    public static String formatHHmm(long timeInMillis) {
        return getHHmmFormat().format(new Date(timeInMillis));
    }

}
