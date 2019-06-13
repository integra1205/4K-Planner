package WeekTests;

import calendar.data.model.MyCalendar;
import org.junit.Test;
import java.time.LocalDate;
import java.util.Arrays;

public class WeekViewTest1 {

    public boolean isItEquals(LocalDate[] expected, LocalDate[] actual) {
        if (expected.length != actual.length) {
            System.out.println("сравниваются разные периоды");
            return false;
        }

        for (int i = 0; i < expected.length; i++) {
            if (expected[i].isEqual(actual[i]) == false) {
                System.out.println("дни недели не совпадают");
                return false;
            }
        }
        System.out.println("дни недели совпадают");
        return true;
    }

    @Test
    public void getAllDaysOfSelectedWeek() {
        LocalDate selectedDate = LocalDate.of(2019, 6, 10);

        MyCalendar.getInstance().viewing_month = selectedDate.getMonthValue();
        MyCalendar.getInstance().viewing_year = selectedDate.getYear();
        MyCalendar.getInstance().viewing_day_of_month = selectedDate.getDayOfMonth();

        LocalDate[] actual = new LocalDate[7];
        for (int i=0; i<actual.length; i++) {
            actual[i] = LocalDate.of(2019, 6, 10 + i);
        }

        LocalDate[] expected = MyCalendar.getInstance().getAllDaysOfSelectedWeek();
//        assert isItEquals(expected,actual);
        assert Arrays.equals(expected, actual);
    }
}


