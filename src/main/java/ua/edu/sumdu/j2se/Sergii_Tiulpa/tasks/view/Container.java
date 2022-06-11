package ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.view;

public class Container {
    public static String HEADER_REPEATED = "#\tTitle\t\t\tStart\t\t\t\tEnd\t\t\t\tInterval\t\tActive";

    public static String HEADER_NOT_REPEATED = "#\tTitle\t\t\tTime\t\t\tActive";

    public static String CALENDAR_HEADER = "#========\t\tCALENDAR\t\t========#";

    public static String CALENDAR_BOTTOM = "#\t========\t\tEND\t\t========\t#";

    public static String SUCCESSFUL_ADD = "Addition the task was successful";

    public static String FAILURE_ADD = "Addition the task was failure";

    public static String SUCCESSFUL_EDIT = "Task editing was successful";

    public static String FAILURE_EDIT = "Task editing was failure";

    public static String EMPTY_LIST = "The task list is empty. " +
            "You can fill it by adding at least one task.";

    public static String MAIN_MENU = "Returning to main menu";

    public static void println(String message) {
        System.out.println(message);
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void printlnCalendarDate(String date) {
        println("@\t======\t" + date + "\t======\t@");
    }

    public static void printlnCalendarTask(String title) {
        println("\t\t@\t~~~\t" + title + "\t~~~\t@");
    }

    public static void notifyCalendarDate(String date) {
        println("%\t!!!!!!\t" + date + "\t!!!!!!\t%");
    }

    public static void notifyCalendarTask(String title) {
        println("\t\t%\t&&&\t" + title + "\t&&&\t%");
    }
}
