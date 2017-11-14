package com.all.learning.custom_view.upload.pojo;

/**
 * Created by root on 23/9/17.
 */

public class FbAttrs {


    public String downloadFileUrl;
    public String networkPath;
    public TaskStatus taskStatus = new TaskStatus();

    public FbAttrs(String networkPath) {
        this.networkPath = networkPath;
    }
}
