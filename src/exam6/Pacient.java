package exam6;


import server.Generator;

import java.util.Random;


public class Pacient {
    private Generator generator=new Generator();
    private String FIO;
    private int type;
    private String hour;

    public Pacient() {
        this.FIO =Generator.makeName() ;
        this.type=generateRandomNumber();
        this.hour=generateRandomHourString();
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;

    }

    public Generator getGenerator() {
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public static String generateRandomHourString() {
        Random random = new Random();
        int randomHour = random.nextInt(9) + 9; // Generate random number between 9 and 17
        return String.format("%02d:00", randomHour);
    }
    public static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(2) + 1; // Generate random number between 1 and 2
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
