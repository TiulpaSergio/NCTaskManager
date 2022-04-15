package ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks;

public class Main {

	public static void main(String[] args)
	{
		Task task = new Task("Task title", 42);
		task.setTime(42);
		System.out.println(task.isRepeated());

		Task task2 = new Task("Task title", 5, 25, 3);
		task.setTime(5, 25, 3);
		System.out.println(task.isRepeated());
	}
}
