import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AsyncLogger {

    // 常用日志
    public final static String ERROR_LOG = "error";

    Map<String, Log> logs = new HashMap<String, Log>();

    public synchronized void register(String logName) {
        if (logs.get(logName) == null) {
            Log log = LogFactory.getLog(logName);
            logs.put(logName, log);
        }
    }

    private AsyncJob<LogItem> logger;

    public AsyncLogger() {
        register(ERROR_LOG);

        logger = new AsyncJob<LogItem>(new JobHandler<LogItem>() {
            public void handle(LogItem item) {
                switch (item.getLogLevel()) {
                case LogItem.INFO:
                    if (null == item.getError()) {
                        item.getLog().info(item.getObj());
                    } else {
                        item.getLog().info(item.getObj(), item.getError());
                    }
                    break;
                case LogItem.DEBUG:
                    if (null == item.getError()) {
                        item.getLog().debug(item.getObj());
                    } else {
                        item.getLog().debug(item.getObj(), item.getError());
                    }
                    break;
                case LogItem.TRACE:
                    if (null == item.getError()) {
                        item.getLog().trace(item.getObj());
                    } else {
                        item.getLog().trace(item.getObj(), item.getError());
                    }
                    break;
                case LogItem.WARN:
                    if (null == item.getError()) {
                        item.getLog().warn(item.getObj());
                    } else {
                        item.getLog().warn(item.getObj(), item.getError());
                    }
                    break;
                case LogItem.ERROR:
                    if (null == item.getError()) {
                        item.getLog().error(item.getObj());
                    } else {
                        item.getLog().error(item.getObj(), item.getError());
                    }
                    break;
                case LogItem.FATAL:
                    if (null == item.getError()) {
                        item.getLog().fatal(item.getObj());
                    } else {
                        item.getLog().fatal(item.getObj(), item.getError());
                    }
                    break;
                }
            }
        });

    }

    public void info(String logName, Object logContent) {
        info(logName, logContent, null);
    }

    public void info(String logName, Object logContent, Throwable t) {
        Log log = logs.get(logName);
        if (log == null || !log.isInfoEnabled())
            return;
        LogItem item = new LogItem(log, LogItem.INFO, logContent, t);
        logger.exec(item);
    }

    public void debug(String logName, Object logContent) {
        debug(logName, logContent, null);
    }

    public void debug(String logName, Object logContent, Throwable t) {
        Log log = logs.get(logName);
        if (log == null || !log.isDebugEnabled())
            return;
        LogItem item = new LogItem(log, LogItem.DEBUG, logContent, t);
        logger.exec(item);
    }

    public void trace(String logName, Object logContent) {
        trace(logName, logContent, null);
    }

    public void trace(String logName, Object logContent, Throwable t) {
        Log log = logs.get(logName);
        if (log == null || !log.isTraceEnabled())
            return;
        LogItem item = new LogItem(log, LogItem.TRACE, logContent, t);
        logger.exec(item);
    }

    public void warn(String logName, Object logContent) {
        warn(logName, logContent, null);
    }

    public void warn(String logName, Object logContent, Throwable t) {
        Log log = logs.get(logName);
        if (log == null || !log.isWarnEnabled())
            return;
        LogItem item = new LogItem(log, LogItem.WARN, logContent, t);
        logger.exec(item);
    }

    public void error(String logName, Object logContent) {
        error(logName, logContent, null);
    }

    public void error(String logName, Object logContent, Throwable t) {
        Log log = logs.get(logName);
        if (log == null || !log.isErrorEnabled())
            return;
        LogItem item = new LogItem(log, LogItem.ERROR, logContent, t);
        logger.exec(item);
    }

    public void fatal(String logName, Object logContent) {
        fatal(logName, logContent, null);
    }

    public void fatal(String logName, Object logContent, Throwable t) {
        Log log = logs.get(logName);
        if (log == null || !log.isFatalEnabled())
            return;
        LogItem item = new LogItem(log, LogItem.FATAL, logContent, t);
        logger.exec(item);
    }

    private class LogItem {
        private final static int INFO = 1;
        private final static int DEBUG = 2;
        private final static int TRACE = 3;
        private final static int WARN = 4;
        private final static int ERROR = 5;
        private final static int FATAL = 6;

        private Log log;
        private int logLevel;
        private Object obj;
        private Throwable error;

        private LogItem(Log log, int logLevel, Object obj, Throwable error) {
            this.log = log;
            this.logLevel = logLevel;
            this.obj = obj;
            this.error = error;
        }

        private Log getLog() {
            return log;
        }

        private int getLogLevel() {
            return logLevel;
        }

        private Object getObj() {
            return obj;
        }

        private Throwable getError() {
            return error;
        }
    }

    public void end() {
        logger.end();
    }

    private final static AsyncLogger _instance = new AsyncLogger();

    public final static AsyncLogger getInstance() {
        return _instance;
    }
}
