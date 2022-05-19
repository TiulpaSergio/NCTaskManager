package ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks;

public abstract class AbstractTaskList {

    protected int size;
    protected static ListTypes.types type;

    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index);
    public AbstractTaskList incoming(int from, int to) {
        if(from > to) {
            throw new IllegalArgumentException("Invalid interval!");
        }

        int nextTaskTime;
        Task currentTask;
        AbstractTaskList returnArr = TaskListFactory.createTaskList(type);

        for(int index = 0; index < size; index++) {
            currentTask = getTask(index);
            nextTaskTime = currentTask.nextTimeAfter(from);

            if(nextTaskTime != -1 && nextTaskTime < to) {
                returnArr.add(currentTask);
            }
        }

        return returnArr;

    }

}
