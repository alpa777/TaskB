package com.all.learning.custom_view.upload;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import com.all.learning.custom_view.upload.event.EventResultAttrs;
import com.all.learning.custom_view.upload.pojo.ResultAttrs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 23/9/17.
 */

public class UploadManager {
    int RC_PIC_FILE = 108;
    Context context;
    List<ResultAttrs> resultAttrses = new ArrayList<>();

    public UploadManager(Context context) {
        this.context = context;
    }

    public void enableUpload(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, RC_PIC_FILE);
    }

    public List<ResultAttrs> getResultAttrses() {
        return resultAttrses;
    }

    public void startUpload(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_PIC_FILE && resultCode == Activity.RESULT_OK) {
            Uri uriData = data.getData();
            ResultAttrs resultAttrs = ResultAttrs.createResultAttrs(context, uriData);
            resultAttrses.add(resultAttrs);
            UplaodUtils.publish(new EventResultAttrs(resultAttrs, EventResultAttrs.ResultAttrsStatus.ADD));
            uploadToSever(resultAttrs);
        }
    }

    public void remove(ResultAttrs resultAttrs) {
        resultAttrses.remove(resultAttrs);
        UplaodUtils.publish(new EventResultAttrs(resultAttrs, EventResultAttrs.ResultAttrsStatus.REMOVE));

    }


    public void rertyUpload(ResultAttrs resultAttrs) {
        uploadToSever(resultAttrs);
    }

    private void uploadToSever(ResultAttrs resultAttrs) {
        MyUploadTask fbFileUploader = new MyUploadTask(resultAttrs);
        try {
            fbFileUploader.exe();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}

