package com.all.learning.customers.dashboard;

import android.support.annotation.NonNull;

import com.all.learning.my_bs.BaseUpload;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by root on 8/11/17.
 */

public class GetDashboard extends BaseUpload {
    String dbJson;

    public void setDbJson(String dbJson) {
        this.dbJson = dbJson;
    }


    @Override
    public void fillData() {
    }

    @Override
    public void upload() {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("JSON_A", dbJson);
        db.collection("root").
                document(packageName).
                get().
                addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot result = task.getResult();
                            try {
                                String o = (String) result.get("json_string");
                                EventBus.getDefault().post(new EventDashboardJson(o));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }
}
