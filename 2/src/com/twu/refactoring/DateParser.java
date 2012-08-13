package com.twu.refactoring;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class DateParser {

    private static final String OUT_OF_BOUND_MESSAGE_TEMPLATE = "%s string is less than %d characters";
    private static final String INVALID_INTEGER_MESSAGE_TEMPLATE = "%s is not an integer";
    private static final String OUT_RANGE_MESSAGE_TEMPLATE = "%s cannot be less than %d or more than %d";

    private final String dateAndTimeString;

    private final FieldDefinition YEAR = new FieldDefinition("Year", 0, 4, 2000, 2012);
    private final FieldDefinition MONTH = new FieldDefinition("Month", 5, 7, 1, 12);
    private final FieldDefinition DATE = new FieldDefinition("Date", 8, 10, 1, 31);
    private final FieldDefinition HOUR = new FieldDefinition("Hour", 11, 13, 0, 23);
    private final FieldDefinition MINUTE = new FieldDefinition("Minute", 14, 16, 0, 59);

    /**
     * Takes a date in ISO 8601 format and returns a date
     *
     * @param dateAndTimeString - should be in format ISO 8601 format
     *                          examples -
     *                          2012-06-17 is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17TZ is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17T15:00Z is 17th June 2012 - 15:00 in UTC TimeZone
     */
    public DateParser(String dateAndTimeString) {
        this.dateAndTimeString = dateAndTimeString;
    }

    public Date parse() {
        int hour, minute;

        int year = parseField(YEAR);
        int month = parseField(MONTH);
        int date = parseField(DATE);

        if (isNoTimeProvided()) {
            hour = 0;
            minute = 0;
        } else {
            hour = parseField(HOUR);
            minute = parseField(MINUTE);
        }

        return createDate(hour, minute, year, month, date);
    }

    private Date createDate(int hour, int minute, int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(year, month - 1, date, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private boolean isNoTimeProvided() {
        return dateAndTimeString.substring(11, 12).equals("Z");
    }

    private int parseField(FieldDefinition field){
        int value;
        int stringLength = field.endIndex - field.startIndex;
        try {
            String stringToParse = getStringToParse(field);
            value = Integer.parseInt(stringToParse);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format(OUT_OF_BOUND_MESSAGE_TEMPLATE, field.name, stringLength));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format(INVALID_INTEGER_MESSAGE_TEMPLATE, field.name));
        }
        if (value < field.lowerBound || value > field.upperBound)
            throw new IllegalArgumentException(String.format(OUT_RANGE_MESSAGE_TEMPLATE,field.name, field.lowerBound, field.upperBound));
        return value;
    }

    private String getStringToParse(FieldDefinition field){
        return dateAndTimeString.substring(field.startIndex, field.endIndex);
    }
}
