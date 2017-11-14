package com.all.learning.custom_view.upload.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 23/9/17.
 */

public class FbDatabase {
    public static final String URL_MATERIAL = "biology/Material/";
    public static final String URL_LIMIT = "biology/limit/";

    public void nw(String url) {
        final FirebaseDatabase database
                = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();

        HashMap<String, Object> map = new HashMap<>();
        map.put("url", "" + url);
        map.put("isVerified", false);

        String key = reference.child(URL_MATERIAL).push().getKey();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(URL_MATERIAL + key, map);

        reference.updateChildren(childUpdates);
    }


    public static void putLimit() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(URL_LIMIT, 5);

        reference.updateChildren(childUpdates);
    }


}
