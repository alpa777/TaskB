package com.all.learning.my_bs.basic;

import android.support.annotation.NonNull;

import com.all.learning.my_bs.BaseUpload;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by root on 8/11/17.
 */

public class GetBasic extends BaseUpload {
    String dbJson;

    public void setDbJson(String dbJson) {
        this.dbJson = dbJson;
    }


    @Override
    public void fillData() {
    }

    @Override
    public void upload() {
        db.collection("root").
                document(packageName).
                collection("items").
                document("A1").
                get().
                addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot result = task.getResult();
                            try {
                                String o = (String) result.get("data");
                                EventBus.getDefault().post(new EventBasicJson(o));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }
}
