package com.example.moviemotionserver;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {
    private static final String[] joursSemaine = new DateFormatSymbols(Locale.FRENCH).getShortWeekdays();
    private static final String[] mois = new DateFormatSymbols(Locale.FRENCH).getShortMonths();

    public static String formatDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return joursSemaine[dayOfWeek];
    }

    public static String formatMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        return mois[month];
    }

    public static String formatDateWithDayAndMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        return "jour: '" + joursSemaine[dayOfWeek] + "', jourChiffre: " + dayOfMonth + ", mois: '" + mois[month] + "'";
    }

    public static String parseDate(String dateString) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEE, d MMM yyyy", Locale.FRENCH);

        Date date = inputFormat.parse(dateString);
        return outputFormat.format(date);
    }

}
