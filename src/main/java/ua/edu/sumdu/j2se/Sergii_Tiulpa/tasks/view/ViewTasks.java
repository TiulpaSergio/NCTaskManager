package ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.view;


import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.model.Task;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.model.Tasks;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.view.Container;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.view.Time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;

public class ViewTasks {

    private static final Logger log = Logger.getLogger(ViewTasks.class);
    private final Scanner scanner;

    public ViewTasks(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showList(AbstractTaskList list) {
        log.info("Showing all task list");
        if (list.size() == 0) {
            Container.println(Container.EMPTY_LIST);
            log.info("List is empty");
        } else {
            getList(list);
        }
    }

    private void getList(AbstractTaskList list) {
        log.info("Getting not empty task list");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        StringBuilder repeated = new StringBuilder();
        StringBuilder notRepeated = new StringBuilder();
        int i = 1;
        for (Task t : list) {
            String title = t.getTitle();
            String time = t.getTime().format(formatter);
            String start = t.getStartTime().format(formatter);
            String end = t.getEndTime().format(formatter);
            String interval = Time.intervalFormatter(t.getRepeatInterval());
            String active = t.isActive() ? "active" : "inactive";

            if (t.isRepeated()) {
                log.info("Setting repeated task to general list");
                repeated.append(i++).append("\t")
                        .append(title).append("\t\t")
                        .append(start).append("\t")
                        .append(end).append("\t")
                        .append(interval).append("\t")
                        .append(active).append("\n");
            } else {
                log.info("Setting not repeated task to general list");
                notRepeated.append(i++).append("\t")
                        .append(title).append("\t\t")
                        .append(time).append("\t")
                        .append(active).append("\n");
            }
        }
        showRepeated(repeated);
        showNotRepeated(notRepeated);
    }

    private void showRepeated(StringBuilder repeated) {
        log.info("Printing repeated task list");
        Container.println(Container.HEADER_REPEATED);
        Container.print(repeated.toString());
    }

    private void showNotRepeated(StringBuilder notRepeated) {
        log.info("Printing not repeated task list");
        Container.println(Container.HEADER_NOT_REPEATED);
        Container.print(notRepeated.toString());
    }

    public void getCalendar(AbstractTaskList list) {
        log.info("Getting task calendar");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        while (true) {
            LocalDateTime start = getStartTime();
            LocalDateTime end = getEndTime();

            if (start.isBefore(end)) {
                SortedMap<LocalDateTime, Set<Task>> calendar =
                        Tasks.calendar(list, start, end);

                if (!calendar.isEmpty()) {
                    Container.println(Container.CALENDAR_HEADER);
                    for (Map.Entry<LocalDateTime, Set<Task>> entry : calendar.entrySet()) {
                        Container.printlnCalendarDate(entry.getKey().format(formatter));
                        for (Task t : entry.getValue()) {
                            Container.printlnCalendarTask(t.getTitle());
                        }
                        Container.println("");
                    }
                    Container.println(Container.CALENDAR_BOTTOM);
                } else {
                    log.info("Calendar is empty");
                    Container.println("Calendar is empty");
                }
                break;
            } else {
                log.info("Wrong date input, trying again");
                Container.println("Error. Enter start and end date correctly");
            }
        }
    }

    public String getTitle() {
        log.info("Getting task title");
        String title = "";
        Container.print("Input task title: ");
        while (title.isEmpty()) {
            title = scanner.nextLine();
            if (title.isEmpty()) {
                Container.print("Try again. Task title: ");
            }
        }
        return title;
    }

    public boolean getRepeatStatus() {
        log.info("Getting repeated status");
        String repeat = "";
        boolean repeatStatus = false;
        Container.print("Input task repeat status (true:false): ");
        while (repeat.isEmpty()
                || (!repeat.equalsIgnoreCase("true")
                && !repeat.equalsIgnoreCase("false"))) {
            repeat = scanner.nextLine();
            if (repeat.isEmpty() || (!repeat.equalsIgnoreCase("true")
                    && !repeat.equalsIgnoreCase("false"))) {
                Container.print("Try again. Repeat status (true:false): ");
            } else {
                repeatStatus = Boolean.parseBoolean(repeat.toLowerCase());
            }
        }
        return repeatStatus;
    }

    public LocalDateTime getStartTime() {
        log.info("Getting start time");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String date;
        LocalDateTime start;
        Container.print("Enter start date (dd-MM-yyyy HH:mm): ");
        while (true) {
            try {
                date = scanner.nextLine();
                start = LocalDateTime.parse(date, formatter);
                break;
            } catch (DateTimeParseException e) {
                Container.print("Error. Try input start date again (dd-MM-yyyy HH:mm): ");
                log.error(e);
            }
        }
        return start;
    }

    public LocalDateTime getEndTime() {
        log.info("Getting end time");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String date;
        LocalDateTime end;
        Container.print("Enter end date (dd-MM-yyyy HH:mm): ");
        while (true) {
            try {
                date = scanner.nextLine();
                end = LocalDateTime.parse(date, formatter);
                break;
            } catch (DateTimeParseException e) {
                Container.print("Error. Try input end date again (dd-MM-yyyy HH:mm): ");
                log.error(e);
            }
        }
        return end;
    }

    public int getInterval() {
        log.info("Getting interval");
        int interval;
        Container.print("Enter the task repetition interval in minutes: ");
        while (true) {
            interval = Integer.parseInt(scanner.nextLine());
            if (interval > 0) {
                return interval * 60;
            } else {
                Container.print("Try again. Enter interval in minutes: ");
            }
        }
    }

    public LocalDateTime getTime() {
        log.info("Getting time");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String date;
        LocalDateTime time;
        Container.print("Enter task date (dd-MM-yyyy HH:mm): ");
        while (true) {
            try {
                date = scanner.nextLine();
                time = LocalDateTime.parse(date, formatter);
                break;
            } catch (DateTimeParseException e) {
                Container.print("Error. Try input task date again (dd-MM-yyyy HH:mm): ");
                log.error(e);
            }
        }
        return time;
    }

    public int getTaskIndex(AbstractTaskList list) {
        log.info("Getting index for action");
        int id = 0;
        if (list.size() == 0) {
            Container.println(Container.EMPTY_LIST);
        } else {
            showList(list);
            Container.print("Enter the task id: ");
            while (true) {
                id = scanner.nextInt();
                scanner.nextLine();
                if (id > 0 && id <= list.size()) return id;
                Container.print("Try again. Enter task id: ");
            }
        }
        return id;
    }

    public boolean getActivity() {
        log.info("Getting task activity");
        String active = "";
        boolean activeStatus = false;
        Container.print("Input task activity status (true:false): ");
        while (active.isEmpty()
                || (!active.equalsIgnoreCase("true")
                && !active.equalsIgnoreCase("false"))) {
            active = scanner.nextLine();
            if (active.isEmpty() || (!active.equalsIgnoreCase("true")
                    && !active.equalsIgnoreCase("false"))) {
                Container.print("Try again. Activity status (true:false): ");
            } else {
                activeStatus = Boolean.parseBoolean(active.toLowerCase());
            }
        }
        return activeStatus;
    }

}