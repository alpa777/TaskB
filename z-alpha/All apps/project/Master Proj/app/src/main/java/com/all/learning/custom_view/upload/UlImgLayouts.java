package com.all.learning.custom_view.upload;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.all.learning.R;
import com.all.learning.base.CommonWidget;
import com.all.learning.base.CommonAdapter;
import com.all.learning.base.OnItemBindListener;
import com.all.learning.base.Utils;
import com.all.learning.custom_view.upload.database.FbDatabase;
import com.all.learning.custom_view.upload.event.EventResultAttrs;
import com.all.learning.custom_view.upload.pojo.ResultAttrs;
import com.all.learning.custom_view.upload.pojo.TaskStatus;
import com.all.learning.databinding.AttchViewBinding;
import com.all.learning.databinding.RowAttachBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 23/9/17.
 */

public class UlImgLayouts extends LinearLayout {
    private int MAX_LIMIT = 4;
    Context context;
    AttchViewBinding mBinding;
    List<ResultAttrs> mList = new ArrayList<>();
    CommonAdapter commonAdapter;
    UploadManager uploadManager;

    public UlImgLayouts(Context context) {
        super(context);
        init(context);
    }


    public UlImgLayouts(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UlImgLayouts(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public void init(Context context) {
        this.context = context;
        uploadManager = new UploadManager(context);
        inflate();
        getLimit();
    }

    public List<String> getUrls() {
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            String downloadFileUrl = mList.get(i).fbAttrs.downloadFileUrl;
            urls.add(downloadFileUrl);
        }
        return urls;
    }

    private void getLimit() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        reference.child(FbDatabase.URL_LIMIT).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                Log.v("", "value => " + value + "");
                MAX_LIMIT = value;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void enableUpload(Activity activity) {
        if (mList.size() >= MAX_LIMIT) {
            Toast.makeText(activity, "Limit Over", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        uploadManager.enableUpload(activity);
    }

    public void stop() {
        EventBus.getDefault().unregister(this);
    }

    public void inflate() {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.attch_view, null, false);
        addView(mBinding.getRoot());
        setAdapter();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        uploadManager.startUpload(requestCode, resultCode, data);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void upload(EventResultAttrs eventResultAttrs) {
        if (eventResultAttrs.status == EventResultAttrs.ResultAttrsStatus.ADD) {
            mList.add(eventResultAttrs.resultAttrs);
            listIsModified();
        }
        if (eventResultAttrs.status == EventResultAttrs.ResultAttrsStatus.MODIFY) {
            mList.set(mList.indexOf(eventResultAttrs.resultAttrs), eventResultAttrs.resultAttrs);
            listItemIsModified(mList.indexOf(eventResultAttrs.resultAttrs));
        }
        if (eventResultAttrs.status == EventResultAttrs.ResultAttrsStatus.REMOVE) {
            mList.remove(eventResultAttrs.resultAttrs);
            listIsModified();
        }
    }

    private void listIsModified() {
        commonAdapter.notifyDataSetChanged();

        if (listModifyCallback != null) {
            listModifyCallback.onListModified(mList.size());
        }
    }

    private void listItemIsModified(int position) {
        commonAdapter.notifyItemChanged(position);
    }

    ListModifyCallback listModifyCallback;

    public void setListModifyCallback(ListModifyCallback listModifyCallback) {
        this.listModifyCallback = listModifyCallback;
    }

    public interface ListModifyCallback {
        void onListModified(int count);
    }

    public void setAdapter() {
        commonAdapter = new CommonAdapter<>(context, mList).setLayoutId(R.layout.row_attach);
        commonAdapter.setOnItemBindListener(new OnItemBindListener<ResultAttrs, RowAttachBinding>() {
            @Override
            public void onItemBind(final ResultAttrs resultAttrs, RowAttachBinding binding, int positition) {

                int deviceWidth = CommonWidget.getWidth(context);
                int item_width = deviceWidth - (deviceWidth / 100 * 65);
                binding.getRoot().setLayoutParams(
                        new ViewGroup.LayoutParams(
                                item_width
                                , ViewGroup.LayoutParams.WRAP_CONTENT)
                );
                binding.getRoot().requestFocus();
                binding.name.setText(resultAttrs.localAttrs.displayName);
                binding.size.setText("size : " +
                        (Integer.parseInt(resultAttrs.localAttrs.size) / 1000)
                        + " kb");
                binding.img.setImageBitmap(resultAttrs.localAttrs.getBitmap());

                binding.name.setVisibility(GONE);

                if (resultAttrs.fbAttrs.taskStatus.currentState ==
                        TaskStatus.TaskStatesEnum.SUCCESS) {
                    binding.pBar.setVisibility(View.GONE);
                    binding.txtRetry.setVisibility(View.GONE);
                    binding.lnrStatus.setBackgroundColor
                            (ContextCompat.getColor(context, R.color.green));
                    binding.txtIndex.setText("Complete");
                } else if (resultAttrs.fbAttrs.taskStatus.currentState ==
                        TaskStatus.TaskStatesEnum.PROGRESS) {
                    binding.pBar.setVisibility(View.VISIBLE);
                    binding.txtRetry.setVisibility(View.GONE);
                    binding.lnrStatus.setBackgroundColor
                            (ContextCompat.getColor(context,
                                    R.color.greenSelector));
                    binding.txtIndex.setText("" +
                            ((int) resultAttrs.fbAttrs.taskStatus.progress) +
                            " %");

                    if ((int) resultAttrs.fbAttrs.taskStatus.progress == 0) {
                        binding.txtIndex.setText("wait...");
                    }
                } else if (resultAttrs.fbAttrs.taskStatus.currentState ==
                        TaskStatus.TaskStatesEnum.FAIL) {
                    binding.pBar.setVisibility(View.GONE);
                    binding.txtRetry.setVisibility(View.VISIBLE);
                    binding.lnrStatus.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));
                    binding.txtIndex.setText("error");
                } else if (resultAttrs.fbAttrs.taskStatus.currentState ==
                        TaskStatus.TaskStatesEnum.RESET) {
                    binding.pBar.setVisibility(View.VISIBLE);
                    binding.txtRetry.setVisibility(View.GONE);
                    binding.lnrStatus.setBackgroundColor
                            (ContextCompat.getColor(context, R.color.greenSelector));
                    binding.txtIndex.setText("" +
                            ((int) resultAttrs.fbAttrs.taskStatus.progress) +
                            " %");

                    if ((int) resultAttrs.fbAttrs.taskStatus.progress == 0) {
                        binding.txtIndex.setText("wait...");
                    }
                }

                binding.txtRetry.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            uploadManager.rertyUpload(resultAttrs);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                binding.imgClose.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        uploadManager.remove(resultAttrs);
                    }
                });
            }

            @Override
            public void onItemClick(int position) {

                ArrayList<String> urls = new ArrayList<String>();
                for (int i = 0; i < mList.size(); i++) {
                    urls.add(mList.get(i).fbAttrs.downloadFileUrl);
                }
                //context.startActivity(FullImgActivity.prepareIntent(context, urls, position));
            }
        });
        Utils.linearHorizontal(context, mBinding.rcvAttch, commonAdapter, 12);
    }

}
