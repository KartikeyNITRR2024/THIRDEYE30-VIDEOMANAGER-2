package com.thirdeye3_2.video.manager.enums;

public enum Interval {
	ONE_MINUTE, TWO_MINUTES, FIVE_MINUTES, FIFTEEN_MINUTES, THIRTY_MINUTES,
    NINETY_MINUTES, ONE_HOUR, ONE_DAY, FIVE_DAYS, ONE_WEEK, ONE_MONTH, THREE_MONTHS;
	
	public String toYfinanceCode() {
        if (this == ONE_MINUTE) return "1m";
        else if (this == TWO_MINUTES) return "2m";
        else if (this == FIVE_MINUTES) return "5m";
        else if (this == FIFTEEN_MINUTES) return "15m";
        else if (this == THIRTY_MINUTES) return "30m";
        else if (this == NINETY_MINUTES) return "90m";
        else if (this == ONE_HOUR) return "1h";
        else if (this == ONE_DAY) return "1d";
        else if (this == FIVE_DAYS) return "5d";
        else if (this == ONE_WEEK) return "1wk";
        else if (this == ONE_MONTH) return "1mo";
        else if (this == THREE_MONTHS) return "3mo";
        else return "1m";
    }
}
