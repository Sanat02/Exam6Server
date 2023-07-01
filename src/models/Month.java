package models;

import exam6.Pacient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Month {

    private List<Day> days = new ArrayList<>();
    private Pacient pacient=new Pacient();
    private List<Month> listP=new ArrayList<>();
    LocalDate currentDate = LocalDate.now();
    int dayOfMonth = currentDate.getDayOfMonth();
    private int size=5;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Month() {
        fillMonth();
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }



    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public Pacient getPacient() {
        return pacient;
    }


    private void fillMonth() {

        for (int i = 1; i < 31; i++) {

            LocalDateTime dateTime = LocalDateTime.now();

            int dayOfMonth = dateTime.getDayOfMonth();

            Day day = new Day("July", i, i == dayOfMonth);

            days.add(day);


        }
    }




}
