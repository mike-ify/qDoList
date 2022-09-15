package org.example.model;

import lombok.Data;

@Data
public class ToDoList {
    private static enum Status{
        DONE, INCOMPLETE
    }
    private String title;
    private String description;
    private Status status;

    public ToDoList(String title, String description) {
        this.title = title;
        this.description = description;
        setStatusIncomplete();
    }

    public ToDoList toggleStatus(){
        switch (status){
            case DONE:
                setStatusIncomplete();
                break;
            case INCOMPLETE:
                setStatusDone();
                break;
        }

        return this;
    }

    public ToDoList setStatusIncomplete(){
        status = Status.INCOMPLETE;
        return this;
    }

    public ToDoList setStatusDone(){
        status = Status.DONE;
        return this;
    }
}
