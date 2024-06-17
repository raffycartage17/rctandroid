package com.racartech.library.rctandroid.time;

public class RCTmillisecondToTimeData {

    public final long DAYS;
    public final long HOURS;
    public final long MINUTES;
    public final long SECONDS;
    public final long MILLISECONDS;

    private static final long DAY_MS = 86400000;
    private static final long HOUR_MS = 3600000;
    private static final long MINUTE_MS = 60000;
    private static final long SECONDS_MS = 1000;

    public RCTmillisecondToTimeData(long milliseconds) {
        this.DAYS = milliseconds / DAY_MS;
        long remaining_ms = milliseconds % DAY_MS;

        this.HOURS = remaining_ms / HOUR_MS;
        remaining_ms = remaining_ms % HOUR_MS;

        this.MINUTES = remaining_ms / MINUTE_MS;
        remaining_ms = remaining_ms % MINUTE_MS;
        this.SECONDS = remaining_ms / SECONDS_MS;
        this.MILLISECONDS = remaining_ms % SECONDS_MS;
    }
}
