import exam6.Lesson44Server;
import models.Month;


import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        try {
            new Lesson44Server("localhost", 8187).start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




}
