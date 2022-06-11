package ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.model.Task;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.view.Container;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class Dates {

    private static final Logger log = Logger.getLogger(Dates.class);
    private static Dates instance;

    public static Dates getInstance() {
        if (instance == null) {
            instance = new Dates();
        }
        return instance;
    }


    public void notify(SortedMap<LocalDateTime, Set<Task>> calendar) {
        log.info("Execute notify command");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        Container.println("\n~Task Manager: @You have upcoming tasks@");
        Container.println("~Tasks in the near future~");
        Container.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        for (Map.Entry<LocalDateTime, Set<Task>> entry : calendar.entrySet()) {
            log.info("Getting calendar date for notify");
            String date = entry.getKey().format(formatter);
            Container.notifyCalendarDate(date);
            for (Task t : entry.getValue()) {
                log.info("Getting calendar tasks by date: " + date);
                Container.notifyCalendarTask(t.getTitle());
            }
            Container.println("");
        }
        Container.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        log.info("Notify command is completed");
    }
}