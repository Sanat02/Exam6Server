package models;

import java.util.ArrayList;
import java.util.List;

public class Day {

    private String month;
    private int date;
    private boolean today;
    private List<Task> tasks = new ArrayList<>();

    public Day(String month, int date, boolean today) {
        this.month = month;
        this.date = date;
        this.today = today;
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
}
