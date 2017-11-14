package com.all.learning.my_bs.dashboard;

import com.all.learning.my_bs.BaseUpload;

import java.util.HashMap;

/**
 * Created by root on 8/11/17.
 */

public class UploadA extends BaseUpload {
    String dbJson;

    public void setDbJson(String dbJson) {
        this.dbJson = dbJson;
    }

    @Override
    public void fillData() {

    }

    @Override
    public void upload() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("json_string", dbJson);
        db.collection("root").
                document(packageName).
                set(map);

    }
}
