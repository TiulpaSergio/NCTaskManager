package ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.controller;

import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.controller.Passwords;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.controller.ControllerTasks;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.controller.Notifications;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.view.View;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.view.Container;
import org.apache.log4j.Logger;
import java.util.Scanner;

public class Controller {

    private static final Logger log = Logger.getLogger(Controller.class);
    private static final String QUESTION = "Are you confident in your choice? (Yes:No) ";
    private final ControllerTasks controller;
    private final Passwords securityController;
    private final View view;
    private final Scanner scanner;

    public Controller(AbstractTaskList list, View view) {
        this.view = view;
        controller = new ControllerTasks(list, view);
        securityController = new Passwords();
        scanner = new Scanner(System.in);

        Notifications manager = new Notifications(list);
        manager.setDaemon(true);
        manager.start();
    }

    public void execute() {
        log.info("Password verification");
        if (securityController.checkPassword()) {

            log.info("Executing main controller command");
            while (true) {
                view.showMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 0) {
                    Container.print("Do you want to finish? (Yes:No): ");
                    if (view.checkUserChoice()) {
                        Container.println("Program was finished");
                        log.info("The Task Manager application is getting ready to end");
                        break;
                    }
                } else {
                    switch (choice) {
                        case 1:
                            Container.print(QUESTION);
                            if (view.checkUserChoice()) controller.addTask();
                            break;
                        case 2:
                            Container.print(QUESTION);
                            if (view.checkUserChoice()) controller.editTask();
                            break;
                        case 3:
                            Container.print(QUESTION);
                            if (view.checkUserChoice()) controller.deleteTask();
                            break;
                        case 4:
                            controller.showTaskList();
                            break;
                        case 5:
                            controller.showCalendar();
                            break;
                    }
                }
            }
        }
    }
}
