package ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks;

public class Task {
    private String title;
    private int time, start, end, interval;
    private boolean active, repeat;

    public Task(String title, int time) {
        if(time < 0){
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.time = time;
        repeat = false;
        active = false;
    }

    public Task(String title, int start, int end, int interval) {
        if (interval <= 0 || start < 0 || end < 0) {
            throw new IllegalArgumentException();
        }

        this.title = title;
        setTime(start, end, interval);
        repeat = true;
        active = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTime() {
        if (repeat) {
            return start;
        }
        return time;
    }

    public void setTime(int time) {
        this.time = time;
        repeat = false;
    }

    public int getStartTime() {
        if (repeat) {
            return start;
        }
        return time;
    }

    public int getEndTime() {
        if (repeat) {
            return end;
        }
        return time;
    }

    public int getRepeatInterval() {
        if (repeat) {
            return interval;
        }
        return 0;
    }

    public void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;

        repeat = true;

    }

    public boolean isRepeated() {
        return this.repeat;
    }

    public int nextTimeAfter(int current) {
        if (!active) {
            return -1;
        }

        else {
            if (!repeat) {
                if (time <= current) {
                    return -1;
                }

                else {
                    return time;
                }

            }

            else {
                int promizh = start;

                while (promizh <= current) {
                    promizh += interval;

                    if (promizh > end) {
                        return -1;
                    }
                }

                return promizh;
            }

        }
    }
}
