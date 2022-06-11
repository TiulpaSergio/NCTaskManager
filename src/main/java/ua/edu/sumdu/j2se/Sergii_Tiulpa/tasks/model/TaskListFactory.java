package ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.model;

import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.model.LinkedTaskList;
import ua.edu.sumdu.j2se.Sergii_Tiulpa.tasks.model.ListTypes;

public class TaskListFactory {

    static public AbstractTaskList createTaskList(ListTypes.types type) {
        if(type == ListTypes.types.ARRAY) {
            return new ArrayTaskList();
        }
        else {
            return new LinkedTaskList();
        }
    }

}
