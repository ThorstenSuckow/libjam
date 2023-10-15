package libjam.util.event;

import java.util.Date;
import java.util.EventObject;

public class LogEvent extends EventObject {

    /**
     * Text of the event
     */
    private final String txt;

    /**
     * Date of the event.
     */
    private final Date when;

    /**
     * Constructs a LogEvent.
     *
     * @param source the object on which the Event initially occurred
     * @param txt the log message
     * @throws IllegalArgumentException if source is null
     */
    public LogEvent(final Object source, final String txt, final Date when) {
        super(source);

        this.txt = txt;
        this.when = when;
    }
    public LogEvent(final Object source, final String msg) {
        this(source, msg, new Date());
    }

    public String getText() {
        return txt;
    }

    public Date getDate() {
        return when;
    }
}
