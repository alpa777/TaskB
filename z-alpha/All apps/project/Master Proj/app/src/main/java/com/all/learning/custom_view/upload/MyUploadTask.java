package com.all.learning.custom_view.upload;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.all.learning.base.Utils;
import com.all.learning.custom_view.upload.database.FbDatabase;
import com.all.learning.custom_view.upload.event.EventResultAttrs;
import com.all.learning.custom_view.upload.pojo.ResultAttrs;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by root on 23/9/17.
 */

public class MyUploadTask {
    private ResultAttrs resultAttrs;
    EventResultAttrs resultAttrsEvent;
    FbDatabase fbDatabase;

    public MyUploadTask(ResultAttrs resultAttrs) {
        this.resultAttrs = resultAttrs;
        fbDatabase = new FbDatabase();
    }

    public void exe() throws FileNotFoundException {
        resultAttrs.setProgress(0);
        publish();
        if (!Utils.isOnline()) {
            resultAttrs.setProgress(-1);
            publish();
        }
        setListener(nw());
    }

    private UploadTask nw() throws FileNotFoundException {
        FirebaseStorage instance = FirebaseStorage.getInstance();
        StorageReference reference = instance.getReference();
        StorageReference child = reference.child(resultAttrs.fbAttrs.networkPath);
        UploadTask uploadTask = child.putStream
                (new FileInputStream(new File(resultAttrs.localAttrs.path)));
        return uploadTask;

    }

    private void setListener(UploadTask uploadTask) {
        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                if (progress > 0) {
                    resultAttrs.setProgress(progress);
                    publish();
                }
            }
        });
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                resultAttrs.setProgress(100);
                resultAttrs.fbAttrs.downloadFileUrl = downloadUrl.toString();
                publish();
                fbDatabase.nw(downloadUrl.toString());
            }
        });

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.v("", "Exception => " + e.getMessage());
                resultAttrs.setProgress(-1);
                publish();
            }
        });
    }


    private void publish() {
        if (resultAttrsEvent == null) {
            resultAttrsEvent = new EventResultAttrs(resultAttrs, EventResultAttrs.ResultAttrsStatus.MODIFY);
        }
        UplaodUtils.publish(resultAttrsEvent);
    }


}
