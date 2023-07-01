package exam6;

import com.sun.net.httpserver.HttpExchange;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import models.Day;
import models.Month;
import server.BasicServer;

import server.ContentType;
import server.ResponseCodes;
import server.Utils;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.util.Map;

import static server.Utils.parseUrlEncoded;


public class Lesson44Server extends BasicServer {

    private final static Configuration freemarker = initFreeMarker();
    private Month month = new Month();
    private Day day = null;


    public Lesson44Server(String host, int port) throws IOException {
        super(host, port);
        registerGet("/schedule", this::getSchedule);
        registerGet("/addTask", this::addTask);
        registerGet("/deleteTask", this::deleteTask);
        registerPost("/add", this::handleRegisterPost);
        registerGet("/add", this::addHandler);


    }

    private void addHandler(HttpExchange exchange) {
        Path path = makeFilePath("add.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    private void deleteTask(HttpExchange exchange) {
        String queryParams = getQueryParams(exchange);
        Map<String, String> params = Utils.parseUrlEncoded(queryParams, "&");
        String name = params.getOrDefault("FIO", "null");
        System.out.println(name);
        for (int i = 0; i < day.getPacients().size(); i++) {
            if (day.getPacients().get(i).getFIO().equals(name)) {
                day.getPacients().remove(i);
                day.setSize(day.getPacients().size());
                break;

            }
        }
        Path path = makeFilePath("deleted.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);

    }


    private void handleRegisterPost(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String, String> parsed = parseUrlEncoded(raw, "&");
        String name = parsed.get("name");
        String description = parsed.get("description");
        String priority = parsed.get("priority");
        if (hasNumber(name) == 1 || isValid(priority) == 1) {
            Path path = makeFilePath("invalid.html");
            sendFile(exchange, path, ContentType.TEXT_HTML);
        } else {
            Pacient newPacient = new Pacient();
            newPacient.setFIO(name);
            newPacient.setDescription(description);
            newPacient.setType(Integer.parseInt(priority));
            for (int i = 0; i < month.getDays().size(); i++) {
                if (month.getDays().get(i).getDate() == day.getDate()) {
                    month.getDays().get(i).addPacient(newPacient);
                }
            }
            redirect303(exchange, "/schedule");

        }

    }

    private void addTask(HttpExchange exchange) {
        String queryParams = getQueryParams(exchange);
        Map<String, String> params = parseUrlEncoded(queryParams, "&");
        String date = params.getOrDefault("day", "null");
        int dateInt = Integer.parseInt(date);

        for (int i = 0; i < month.getDays().size(); i++) {
            if (month.getDays().get(i).getDate() == dateInt) {
                day = month.getDays().get(i);
            }
        }

        renderTemplate(exchange, "createTask.html", day);
    }

    private void getSchedule(HttpExchange exchange) {

        renderTemplate(exchange, "schedule.html", month);

    }

    private static Configuration initFreeMarker() {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
            cfg.setDirectoryForTemplateLoading(new File("data"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setFallbackOnNullLoopVariable(false);
            return cfg;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    protected void renderTemplate(HttpExchange exchange, String templateFile, Object dataModel) {
        try {
            Template temp = freemarker.getTemplate(templateFile);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            try (OutputStreamWriter writer = new OutputStreamWriter(stream)) {

                temp.process(dataModel, writer);
                writer.flush();
                var data = stream.toByteArray();

                sendByteData(exchange, ResponseCodes.OK, server.ContentType.TEXT_HTML, data);
            }
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public static int hasNumber(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                return 1;
            }
        }
        return 0;
    }

    public static int isValid(String input) {
        if (input.equals("1") || input.equals("2")) {
            return 0;
        }
        return 1;
    }


}
