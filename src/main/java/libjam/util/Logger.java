package libjam.util;

import libjam.util.event.LogEvent;
import libjam.util.event.LogListener;

import java.util.ArrayList;
import java.util.List;

class LogImpl {
    private List<LogListener> logListener = new ArrayList<>();

    public void log(String str) {

        for (LogListener listener: logListener) {
            listener.onLogEvent(new LogEvent(this, str));
        }

    }

    public void addLogListener(final LogListener listener) {
        logListener.add(listener);
    }
}

final public class Logger {

    private Logger(){
    }

    /**
     * LogImpl used by this class.
     */
    private static LogImpl loggerInst = new LogImpl();

    public static void addLogListener(final LogListener listener) {
        loggerInst.addLogListener(listener);
    }

    public static void log(final String str) {
        loggerInst.log(str);
    }

    public static void log(final int i) {
        loggerInst.log(Integer.valueOf(i).toString());
    }

    public static void log(final double d) {
        loggerInst.log(Double.valueOf(d).toString());
    }

}
