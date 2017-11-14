package com.all.learning.custom_view.upload.pojo;

import android.content.Context;
import android.net.Uri;

/**
 * Created by root on 23/9/17.
 */

public class ResultAttrs  {
    public LocalAttrs localAttrs;
    public FbAttrs fbAttrs;

    public ResultAttrs(LocalAttrs cursorAttrs, FbAttrs fbAttrs) {
        this.localAttrs = cursorAttrs;
        this.fbAttrs = fbAttrs;
    }

    public void setProgress(double progress) {
        this.fbAttrs.taskStatus.progress = progress;
        if (progress == 0) {
            this.fbAttrs.taskStatus.currentState = TaskStatus.TaskStatesEnum.RESET;
        }
        if (progress > 0 && progress < 100) {
            this.fbAttrs.taskStatus.currentState = TaskStatus.TaskStatesEnum.PROGRESS;
        }
        if (progress == 100) {
            this.fbAttrs.taskStatus.currentState = TaskStatus.TaskStatesEnum.SUCCESS;
        }
        if (progress == -1) {
            this.fbAttrs.taskStatus.currentState = TaskStatus.TaskStatesEnum.FAIL;
        }
    }

    public static ResultAttrs createResultAttrs(Context context, Uri uri) {
        LocalAttrs cursorAttrs = LocalAttrs.cursorToFileAttrs(context, uri);
        ResultAttrs resultAttrs = new ResultAttrs(cursorAttrs, new FbAttrs("biology/img/"));
        return resultAttrs;
    }


}
