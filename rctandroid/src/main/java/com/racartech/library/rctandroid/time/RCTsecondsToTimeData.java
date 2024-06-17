package com.racartech.library.rctandroid.time;

public class RCTsecondsToTimeData {

    public final long DAYS;
    public final long HOURS;
    public final long MINUTES;
    public final long SECONDS;

    private static final long DAY_SECONDS = 86400;
    private static final long HOUR_SECONDS = 3600;
    private static final long MINUTE_SECONDS = 60;

    public RCTsecondsToTimeData(long seconds) {
        this.DAYS = seconds / DAY_SECONDS;
        long remainingSeconds = seconds % DAY_SECONDS;

        this.HOURS = remainingSeconds / HOUR_SECONDS;
        remainingSeconds = remainingSeconds % HOUR_SECONDS;

        this.MINUTES = remainingSeconds / MINUTE_SECONDS;
        this.SECONDS = remainingSeconds % MINUTE_SECONDS;
    }
}

