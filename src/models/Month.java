package models;

import exam6.Pacient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Month {

    private List<Day> days = new ArrayList<>();
    private Pacient pacient=new Pacient();
    private List<List<Pacient>> listP=new ArrayList<>();
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
        fillPacients2();
        pacient.setFIO("Gulya");

    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public List<List<Pacient>> getListP() {
        return listP;
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
    private List<Pacient> fillPacients() {
       List<Pacient> pacients = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            pacients.add(new Pacient());
        }
        return pacients;
    }
    private void fillPacients2() {
        for (int i = 1; i < 31; i++) {
            listP.add(fillPacients());
        }
    }


}
