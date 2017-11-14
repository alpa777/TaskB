package com.all.learning.custom_view.upload.pojo;

/**
 * Created by root on 23/9/17.
 */

public class TaskStatus {
    public enum TaskStatesEnum {
        RESET, PROGRESS, FAIL, SUCCESS,
    }

    public double progress;

    public TaskStatesEnum currentState;
}
