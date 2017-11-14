package com.all.learning.custom_view.upload.event;

import android.net.Uri;
import android.widget.EditText;

/**
 * Created by root on 22/8/17.
 */

public class EventValidation {
    public String text;
    public EditText editText;
    public Uri uri;
    public boolean shouldShare;

    public EventValidation(Uri uri, boolean shouldShare) {
        this.uri = uri;
        this.shouldShare = shouldShare;
    }

    public EventValidation(String text) {
        this.text = text;
        this.shouldShare = false;
    }

    public EventValidation(String text, EditText editText) {
        this.text = text;
        this.editText = editText;
        isValid();
    }


    public boolean isValid() {
        return text != null && text.trim().length() > 3;
    }
}
