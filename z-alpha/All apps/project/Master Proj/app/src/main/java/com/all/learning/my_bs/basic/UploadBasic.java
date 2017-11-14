package com.all.learning.my_bs.basic;

import android.os.AsyncTask;

import com.all.learning.my_bs.BaseUpload;

import java.util.HashMap;

/**
 * Created by root on 8/11/17.
 */

public class UploadBasic extends BaseUpload {
    String dbJson;

    public void setDbJson(String dbJson) {
        this.dbJson = dbJson;
    }

    @Override
    public void fillData() {

    }

    @Override
    public void upload() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                HashMap<String, Object> map = new HashMap<>();
                map.put("data", dbJson);

                db.collection("root").
                        document(packageName).
                        collection("items").
                        document("A1").
                        set(map);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }.execute();


    }
}
