package ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.controller;

import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.model.Task;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.view.View;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.view.Container;

import org.apache.log4j.Logger;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ControllerTasks {

    private static final Logger log = Logger.getLogger(ControllerTasks.class);
    private final Scanner scanner;
    private final View view;
    private final AbstractTaskList list;

    public ControllerTasks(AbstractTaskList list, View view) {
        this.list = list;
        this.view = view;
        scanner = new Scanner(System.in);
    }

    public void addTask() {
        log.info("Start adding add action");
        String title = view.getTitle();
        if (view.getRepeatStatus()) {
            createRepeated(title);
        } else {
            createNotRepeated(title);
        }
    }

    private void createRepeated(String title) {
        log.info("Creating repeated task");
        Task task;
        LocalDateTime start = view.getStartTime();
        LocalDateTime end = view.getEndTime();
        int interval = view.getInterval();

        if (checkParams(start, end, interval)) {
            task = new Task(title, start, end, interval);
            task.setActive(true);

            list.add(task);
            Container.println(Container.SUCCESSFUL_ADD);
        } else {
            Container.println(Container.FAILURE_ADD);
        }
    }

    private boolean checkParams(LocalDateTime start, LocalDateTime end, int interval) {
        return (start.isBefore(end) || !start.equals(end))
                && start.plusSeconds(interval).isBefore(end);
    }

    private void createNotRepeated(String title) {
        log.info("Creating a non-repetitive task");
        Task task;
        LocalDateTime time = view.getTime();

        task = new Task(title, time);
        task.setActive(true);

        list.add(task);
        Container.println(Container.SUCCESSFUL_ADD);
    }

    public void editTask() {
        log.info("Execute edit action");
        int index = view.getIndex(list) - 1;
        if (index > -1) {
            Task task = list.getTask(index);
            while (true) {
                view.editMenu();

                int choice = scanner.nextInt();
                if (choice == 0) {
                    Container.println(Container.MAIN_MENU);
                    break;
                } else {
                    switch (choice) {
                        case 1:
                            editTaskTitle(task);
                            break;
                        case 2:
                            editTaskRepeatedStatus(task);
                            break;
                        case 3:
                            changeTaskActivityStatus(task);
                            break;
                        case 4:
                            changeTaskTimeOptions(task);
                            break;
                    }
                }
            }
        }
    }

    private void changeTaskTimeOptions(Task task) {
        log.info("Changing task time options");
        if (task.isRepeated()) {
            editRepeatedTime(task);
        } else {
            editNotRepeatedTime(task);
        }
    }

    private void editRepeatedTime(Task task) {
        log.info("Changing repeated task time");
        Container.println(">Enter new repeated task time-");
        while (true) {
            LocalDateTime start = view.getStartTime();
            LocalDateTime end = view.getEndTime();
            int interval = view.getInterval();

            if (checkParams(start, end, interval)) {
                task.setTime(start, end, interval);
                Container.println("Repeated task has successfully changed its time");
                break;
            } else {
                Container.println("Changing the time of the repeated task was a failure");
            }
        }
    }

    private void editNotRepeatedTime(Task task) {
        log.info("Changing not repeated task time");
        Container.println(">Enter new task time-");
        LocalDateTime time = view.getTime();
        task.setTime(time);
        Container.println("Not repeated task has successfully changed its time");
    }

    private void editTaskTitle(Task task) {
        log.info("Changing task title");
        Container.println(">Enter new task title: ");
        String title = view.getTitle();
        task.setTitle(title);
        Container.println("The task successfully changed the name");
    }

    private void editTaskRepeatedStatus(Task task) {
        log.info("Changing task repeated status");
        Container.println("The task has the following repetition status -> " + task.isRepeated());
        if (task.isRepeated()) {
            makeNotRepeated(task);
        } else {
            makeRepeated(task);
        }
    }

    private void makeNotRepeated(Task task) {
        log.info("Changing: repeated task -> not repeated task");
        task.setTime(view.getTime());
        task.setRepeat(false);
    }

    private void makeRepeated(Task task) {
        log.info("Changing: not repeated task -> repeated task");
        LocalDateTime start = view.getStartTime();
        LocalDateTime end = view.getEndTime();
        int interval = view.getInterval();

        if (checkParams(start, end, interval)) {
            task.setTime(start, end, interval);
            task.setRepeat(true);
            Container.println(Container.SUCCESSFUL_EDIT);
        } else {
            Container.println(Container.FAILURE_EDIT);
        }
    }

    private void changeTaskActivityStatus(Task task) {
        log.info("Changing task activity");
        Container.print(">Enter new task activity status: ");
        boolean activity = view.getActivity();
        task.setActive(activity);
        Container.println("The task successfully changed the activity status");
    }

    public void deleteTask() {
        log.info("Execute delete action");
        int index = view.getIndex(list) - 1;
        if (index > -1) {
            Task task = list.getTask(index);
            list.remove(task);
            Container.println("Task №" + (index + 1) + " was deleted");
        }
    }

    public void showTaskList() {
        log.info("Execute show task list action");
        view.showTaskList(list);
    }

    public void showCalendar() {
        log.info("Execute show task calendar action");
        view.showCalendar(list);
    }
}
