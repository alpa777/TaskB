package com.all.learning.custom_view.upload.event;


import com.all.learning.custom_view.upload.pojo.ResultAttrs;

/**
 * Created by root on 23/9/17.
 */

public class EventResultAttrs {
    public ResultAttrs resultAttrs;
    public ResultAttrsStatus status;

    public enum ResultAttrsStatus {
        ADD, REMOVE, MODIFY
    }

    public EventResultAttrs(ResultAttrs resultAttrs, ResultAttrsStatus status) {
        this.resultAttrs = resultAttrs;
        this.status = status;
    }
}
