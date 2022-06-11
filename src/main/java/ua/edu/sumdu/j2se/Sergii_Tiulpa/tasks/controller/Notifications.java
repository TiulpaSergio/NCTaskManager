package ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.controller;

import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.model.Task;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.model.Tasks;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.view.Container;

import org.apache.log4j.Logger;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class Notifications extends Thread {

    private static final Logger log = Logger.getLogger(Notifications.class);
    private static final long TIME = 300_000;
    private static final long MINUTES = 60;
    private final AbstractTaskList atl;
    private final Dates notification;

    public Notifications(AbstractTaskList list) {
        atl = list;
        notification = Dates.getInstance();
    }

    @Override
    public void run() {
        SortedMap<LocalDateTime, Set<Task>> calendar;
        while (true) {
            calendar = Tasks.calendar(atl,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(MINUTES));
            if (!calendar.isEmpty()) {
                notification.notify(calendar);
            }
            try {
                sleep(TIME);
            } catch (InterruptedException e) {
                log.error(e);
                Container.println(e.getMessage());
            }
        }
    }
}