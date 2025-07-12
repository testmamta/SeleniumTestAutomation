package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private static final ThreadLocal<Logger> threadLocalLogger = ThreadLocal.withInitial(() ->
            LogManager.getLogger("Thread-" + Thread.currentThread().getId()));

    public static Logger getLogger() {
        return threadLocalLogger.get();
    }
}
