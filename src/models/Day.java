package models;

import exam6.Pacient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day {
    List<Pacient> pacients = new ArrayList<>();
    private String month;
    private int date;
    private int size;
    private boolean today;
    private List<Task> tasks = new ArrayList<>();

    public Day(String month, int date, boolean today) {
        this.month = month;
        this.date = date;
        this.today = today;
        this.pacients=fillPacients();
        this.size=fillPacients().size();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public boolean isToday() {
        return today;
    }

    public void setToday(boolean today) {
        this.today = today;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    private List<Pacient> fillPacients() {

        for (int i = 1; i < 5; i++) {
            pacients.add(new Pacient());
        }
        pacients.sort(Comparator.comparing(Pacient::getHour));
        return pacients;
    }

    public List<Pacient> getPacients() {
        return pacients;
    }

    public void setPacients(List<Pacient> pacients) {
        this.pacients = pacients;
    }
    public void addPacient(Pacient pacient)
    {
        pacients.add(pacient);
    }

}
