package com.all.learning.my_bs;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

/**
 * Created by root on 7/11/17.
 */

public abstract class BaseUpload {
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public String packageName = "learn.android.developer";

    //public String packageName = "learn.android.developer";
    public abstract void fillData();

    public abstract void upload();

    public void config() {
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
    }

    public void exe() {
        config();
        fillData();
        upload();
    }
}
