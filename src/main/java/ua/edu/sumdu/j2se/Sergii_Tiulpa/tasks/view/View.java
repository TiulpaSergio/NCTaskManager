package ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.view;

import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.view.ViewTasks;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.view.Container;

import java.time.LocalDateTime;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class View {

    private static final Logger log = Logger.getLogger(View.class);
    private final ViewTasks taskView;
    private final Scanner scanner;

    public View() {
        scanner = new Scanner(System.in);
        taskView = new ViewTasks(scanner);
    }

    public void showMenu() {
        log.info("Displaying the main user menu");
        Container.println("==============Task Manager==============");
        Container.println("# 1) Add task");
        Container.println("# 2) Edit task");
        Container.println("# 3) Delete task");
        Container.println("# 4) Show tasks' list");
        Container.println("# 5) Show tasks' calendar");
        Container.println("# 0) Exit program");
        Container.println("========================================");
        Container.print("@ Your choice: ");
    }

    public void editMenu() {
        log.info("Displaying the user editing menu");
        Container.println("==============Edit Menu==============");
        Container.println("# 1) Task title");
        Container.println("# 2) Task repeated status");
        Container.println("# 3) Task active");
        Container.println("# 4) Task time");
        Container.println("# 0) To main menu");
        Container.println("========================================");
        Container.print("@ Your choice: ");
    }

    public void showTaskList(AbstractTaskList list) {
        log.info("Showing tasks' list");
        taskView.showList(list);
    }

    public void showCalendar(AbstractTaskList list) {
        log.info("Showing tasks' calendar");
        taskView.getCalendar(list);
    }

    public boolean checkUserChoice() {
        log.info("Checking user answer");
        return userAnswer().equalsIgnoreCase("yes");
    }

    private String userAnswer() {
        log.info("Parsing the user response");
        String answer = scanner.nextLine();
        while (!answer.equalsIgnoreCase("yes")
                && !answer.equalsIgnoreCase("no")) {
            Container.print("You should select #Yes# or #No#: ");
            answer = scanner.nextLine();
        }
        return answer;
    }

    public String getTitle() {
        log.info("Getting task title");
        return taskView.getTitle();
    }

    public boolean getRepeatStatus() {
        log.info("Getting repeated status");
        return taskView.getRepeatStatus();
    }

    public LocalDateTime getStartTime() {
        log.info("Getting start time");
        return taskView.getStartTime();
    }

    public LocalDateTime getEndTime() {
        log.info("Getting end time");
        return taskView.getEndTime();
    }

    public int getInterval() {
        log.info("Getting task interval");
        return taskView.getInterval();
    }

    public LocalDateTime getTime() {
        log.info("Getting time");
        return taskView.getTime();
    }

    public int getIndex(AbstractTaskList list) {
        log.info("Getting task index for action");
        return taskView.getTaskIndex(list);
    }

    public boolean getActivity() {
        log.info("Getting task activity");
        return taskView.getActivity();
    }

}