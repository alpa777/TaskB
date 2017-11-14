package com.all.learning.my_bs.dashboard;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.all.learning.R;
import com.all.learning.base.BaseActivity;
import com.all.learning.base.Utils;
import com.all.learning.databinding.ActivityAuploadBinding;

import java.io.IOException;


public class AUploadActivity extends BaseActivity {

    ActivityAuploadBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = (ActivityAuploadBinding) create(R.layout.activity_aupload);

        initToolbar(mBinding.toolbarLayout.toolbar, "Upload A");

        mBinding.btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUpload();
            }
        });
    }

    private void onUpload() {
        String res = null;
        try {
            res = Utils.loadJsonFromAsset("json/all.json", mContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (res == null) {
            Toast.makeText(mActivity, "fail", Toast.LENGTH_SHORT).show();
            return;
        }

        UploadA uploadA = new UploadA();
        uploadA.setDbJson(res);
        uploadA.exe();
        Toast.makeText(mActivity, "sending", Toast.LENGTH_SHORT).show();
    }
}
