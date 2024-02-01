package it.marcosoft.ticketwave.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    private static final String ITALIAN_DATE_FORMAT = "dd/MM/yyyy";
    private static final String ISO_8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static String convertItalianToISO8601(String italianDateString) {
        try {
            // Create a SimpleDateFormat object for parsing the Italian date format
            SimpleDateFormat italianDateFormat = new SimpleDateFormat(ITALIAN_DATE_FORMAT, Locale.ITALY);

            // Parse the Italian date string to obtain a Date object
            Date italianDate = italianDateFormat.parse(italianDateString);

            // Create a SimpleDateFormat object for formatting in ISO 8601 date-time format
            SimpleDateFormat iso8601DateFormat = new SimpleDateFormat(ISO_8601_DATE_FORMAT, Locale.US);

            // Format the Date object to obtain the desired date-time format
            return iso8601DateFormat.format(italianDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Return null for invalid date strings
        }
    }

    // You can add more utility methods as needed
}
