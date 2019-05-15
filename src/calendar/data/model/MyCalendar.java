package calendar.data.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.GregorianCalendar;

public class MyCalendar {
    private final static MyCalendar instance = new MyCalendar();

    public static MyCalendar getInstance() {
        return instance;
    }

    // for adding/editing events
    LocalDate startDate;
    LocalDate endDate;
    LocalTime startTime;
    LocalTime endTime;
    public int event_day;
    public int event_month;
    public int event_year;
    public String event_subject;
    public int event_categorie_id;

    // for the year, month, week and day the user has open, is "viewing"
    public int viewing_day;
    public int viewing_week;
    public int viewing_month;
    public int viewing_year;

    // for the current calendar being worked on
    public String calendar_name;

    //public set this day as a start day for created calendar
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String calendar_start_date = sdf.format(new GregorianCalendar().getTime());

    private int calendar_start_year = LocalDate.now().getYear();
    private int calendar_start_month = LocalDate.now().getMonthValue();
    private int calendar_start_day = LocalDate.now().getDayOfMonth();
/*  private int calendar_start_year = Integer.parseInt(calendar_start_date.substring(0, 4));
    private int calendar_start_month = Integer.parseInt(calendar_start_date.substring(5, 7));
    private int calendar_start_day = Integer.parseInt(calendar_start_date.substring(8, 10));*/

    public String getCalendar_start_date() {
        return calendar_start_date;
    }

    public void setCalendar_start_date(String calendar_start_date) {
        this.calendar_start_date = calendar_start_date;
    }

    public int getCalendar_start_year() {
        return calendar_start_year;
    }

    public int getCalendar_start_month() {
        return calendar_start_month;
    }

    public int getCalendar_start_day() {
        return calendar_start_day;
    }

    public void setCalendar_start_year(int calendar_start_year) {
        this.calendar_start_year = calendar_start_year;
    }

    public enum Month {
        JANUARY,
        FEBRUARY,
        MARCH,
        APRIL,
        MAY,
        JUNE,
        JULY,
        AUGUST,
        SEPTEMBER,
        OCTOBER,
        NOVEMBER,
        DECEMBER
    }

    //Function that returns a month Index based on the given month name
    public int getMonthIndex(String month){
        switch (month)
        {
            case "JANUARY":
                return 0;
            case "FEBRUARY":
                return 1;
            case "MARCH":
                return 2;
            case "APRIL":
                return 3;
            case "MAY":
                return 4;
            case "JUNE":
                return 5;
            case "JULY":
                return 6;
            case "AUGUST":
                return 7;
            case "SEPTEMBER":
                return 8;
            case "OCTOBER":
                return 9;
            case "NOVEMBER":
                return 10;
            case "DECEMBER":
                return 11;
        }
        return 0;
    }
}
