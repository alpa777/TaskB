package com.all.learning.custom_view.upload;

import com.all.learning.custom_view.upload.event.EventResultAttrs;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by root on 23/9/17.
 */

public class UplaodUtils {
    public static void publish(EventResultAttrs resultAttrs) {
        EventBus.getDefault().post(resultAttrs);
    }
}
