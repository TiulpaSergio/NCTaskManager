package ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks;

public class ArrayTaskList {
    private int num;
    private Task [] tasks;
    private int size;

    public ArrayTaskList(){
        num = 1;
        tasks = new Task[num];
        size = 0;
    }

    public void add(Task task)
    {
        if(task == null){
            throw new NullPointerException("немає значень task!");
        }

        tasks[size] = task;
        size++;

        if(size == num)
        {
            num += 5;
            Task [] tasks2 = new Task[num];
            System.arraycopy(tasks, 0, tasks2, 0, tasks.length);
            tasks = tasks2;
        }

    }

    public boolean remove(Task task)
    {
        boolean exist = false;

        for(int i = 0; i < size; i++)
        {
            if(task.equals(tasks[i]) && !exist)
            {
                tasks[i] = null;
                exist = true;
            }
            if(exist)
            {
                tasks[i] = tasks[i+1];
                tasks[i+1] = null;
            }
        }

        if(exist)
        {
            size--;
        }

        if(size < num - 5)
        {
            num -= 5;
            Task [] tasks2 = new Task[num];

            System.arraycopy(tasks, 0, tasks2, 0, tasks.length);
            tasks = tasks2;
        }

        return exist;
    }

    public int size()
    {
        return size;
    }

    public Task getTask(int index)
    {
        if(index < 0 || index > size){
            throw new ArrayIndexOutOfBoundsException("Індекс не входить у масив!");
        }
        return tasks[index];
    }


    public ArrayTaskList incoming(int from, int to){

        if(from > to) {
            throw new IllegalArgumentException("Значення from повинно бути меншим за to!");
        }

        int nextTaskTime;
        ArrayTaskList returnArr = new ArrayTaskList();

        for(short index = 0; index < size; index++) {
            nextTaskTime = tasks[index].nextTimeAfter(from);

            if(nextTaskTime != -1 && nextTaskTime < to) {
                returnArr.add(tasks[index]);
            }
        }

        return returnArr;
    }
}
