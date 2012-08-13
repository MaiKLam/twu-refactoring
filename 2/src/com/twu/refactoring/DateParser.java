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

        int year = parseYear();
        int month = parseMonth();
        int date = parseDate();

        if (isNoTimeProvided()) {
            hour = 0;
            minute = 0;
        } else {
            hour = parseHour();
            minute = parseMinute();
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


    private int parseMinute() {
        int startPosition = 14;
        int endPosition = 16;
        int minValue = 0;
        int maxValue = 59;
        HashMap<String, Integer> minuteDetails = generateDetailsMap(startPosition, endPosition, minValue, maxValue);
        return parseValueString("Minute", minuteDetails);
    }

    private int parseHour() {
        int startPosition = 11;
        int endPosition = 13;
        int minValue = 0;
        int maxValue = 23;
        HashMap<String, Integer> hourDetails = generateDetailsMap(startPosition, endPosition, minValue, maxValue);
        return parseValueString("Hour", hourDetails);
    }

    private int parseDate() {
        int startPosition = 8;
        int endPosition = 10;
        int minValue = 1;
        int maxValue = 31;
        HashMap<String, Integer> dateDetails = generateDetailsMap(startPosition, endPosition, minValue, maxValue);
        return parseValueString("Date", dateDetails);
    }

    private int parseMonth() {
        int startPosition = 5;
        int endPosition = 7;
        int minValue = 1;
        int maxValue = 12;
        HashMap<String, Integer> monthDetails = generateDetailsMap(startPosition, endPosition, minValue, maxValue);
        return parseValueString("Month", monthDetails);
    }

    private int parseYear() {
        int startPosition = 0;
        int endPosition = 4;
        int minValue = 2000;
        int maxValue = 2012;
        HashMap<String, Integer> yearDetails = generateDetailsMap(startPosition, endPosition, minValue, maxValue);
        return parseValueString("Year", yearDetails);
    }

    private HashMap<String, Integer> generateDetailsMap(int startPosition, int endPosition, int minValue, int maxValue) {
        HashMap<String,Integer> details = new HashMap<String, Integer>();
        details.put("startPosition", startPosition);
        details.put("endPosition", endPosition);
        details.put("minValue", minValue);
        details.put("maxValue", maxValue);
        return details;
    }

    private int parseValueString(String valueName, HashMap<String, Integer> details){
        int value;
        int stringLength = details.get("endPosition") - details.get("startPosition");
        try {
            String stringToParse = dateAndTimeString.substring(details.get("startPosition"), details.get("endPosition"));
            value = Integer.parseInt(stringToParse);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format(OUT_OF_BOUND_MESSAGE_TEMPLATE, valueName, stringLength));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format(INVALID_INTEGER_MESSAGE_TEMPLATE, valueName));
        }
        checkBounds(valueName, details, value);
        return value;
    }

    private void checkBounds(String valueName, HashMap<String, Integer> details, int value) {
        if (value < details.get("minValue") || value > details.get("maxValue"))
            throw new IllegalArgumentException(String.format(OUT_RANGE_MESSAGE_TEMPLATE,valueName, details.get("minValue"),details.get("maxValue")));
    }
}
