package ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks;

public class LinkedTaskList extends AbstractTaskList{

    private class ListElement {
        Task data;
        ListElement next;

        public ListElement(Task task) {
            this.data = task;
            next = null;
        }
    }

    static {
        type = ListTypes.types.LINKED;
    }

    private int size;
    private ListElement head;

    public LinkedTaskList() {
        size = 0;
        head = null;
    }

    public void add(Task task) {
        if (task == null) {
            throw new NullPointerException("немає значень task!");
        }

        ListElement temp = new ListElement(task);
        ListElement curTemp = head;

        if (size == 0) {
            head = temp;
        }
        else {
            while (curTemp.next != null) {
                curTemp = curTemp.next;
            }
            curTemp.next = temp;
        }
        size++;

    }

    public boolean remove(Task task) {

        if (task == null) {
            throw new NullPointerException("немає значень task!");
        }

        ListElement curTemp = head;

        if (size == 0) {
            return false;
        }

        if(curTemp.data.equals(task)){
            head = curTemp.next;
            size--;
            return true;
        }

        while(curTemp.next != null) {
            if(curTemp.next.data.equals(task)) {
                curTemp.next = curTemp.next.next;
                size--;
                return true;
            }

            curTemp = curTemp.next;
        }

        return false;
    }

    public int size() {
        return size;
    }

    public Task getTask(int num) {
        if (num < 0 || num > size) {
            throw new ArrayIndexOutOfBoundsException("Індекс не входить у масив!");
        }

        ListElement curTemp = head;
        int counter = 0;

        while(curTemp.next != null){

            if(num == counter){
                break;
            }
            counter++;
            curTemp = curTemp.next;
        }

        return curTemp.data;
    }


    public LinkedTaskList incoming(int from, int to) {

        if (from > to) {
            throw new IllegalArgumentException("Значення from повинно бути меншим за to!");
        }

        int nextTaskTime;
        LinkedTaskList returnArr = new LinkedTaskList();
        ListElement curTemp = head;

        while(curTemp.next != null){
            nextTaskTime = curTemp.data.nextTimeAfter(from);

            if(nextTaskTime != -1 && nextTaskTime < to) {
                returnArr.add(curTemp.data);
            }
            curTemp = curTemp.next;
        }

        return returnArr;
    }

}
