package calendar.data.model;
import java.time.Month;

public class Model {

    private final static Model instance = new Model();

    public static Model getInstance() {
        return instance;
    }


    // for the year and month the user has open, is "viewing"
    public int viewing_year;
    public Month viewing_month;
    public int viewing_week;
    public int viewing_day;

    public int getMonthIndex(Month month){
        switch (month){
            case JANUARY:   return 1;
            case FEBRUARY:  return 2;
            case MARCH:     return 3;
            case APRIL:     return 4;
            case MAY:       return 5;
            case JUNE:      return 6;
            case JULY:      return 7;
            case AUGUST:    return 8;
            case SEPTEMBER: return 9;
            case OCTOBER:   return 10;
            case NOVEMBER:  return 11;
            case DECEMBER:  return 12;
        }   return 0;
    }

    public Month getMonthName(int month){
        switch (month) {
            case 1:  return Month.JANUARY;
            case 2:  return Month.FEBRUARY;
            case 3:  return Month.MARCH;
            case 4:  return Month.APRIL;
            case 5:  return Month.MAY;
            case 6:  return Month.JUNE;
            case 7:  return Month.JULY;
            case 8:  return Month.AUGUST;
            case 9:  return Month.SEPTEMBER;
            case 10: return Month.OCTOBER;
            case 11: return Month.NOVEMBER;
            case 12: return Month.DECEMBER;
        }   return null;
    }
}
