package com.all.learning.base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by root on 2/11/17.
 */

public class Utils {
    public static String APP_ID = "ca-app-pub-3373973442087367~5533399801";
    public static final int REQUEST_WRITE_STORAGE = 112;

    public static String saveJpeg(Activity activity, View view,
                                  View hideThisView, String fileName) {
        String jpegPath = null;
        if (activity == null) {
            return null;
        }
        boolean hasPermission = (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        } else {
            try {
                jpegPath = Environment.getExternalStorageDirectory() + "/" + fileName + ".jpeg";
                hideThisView.setVisibility(View.INVISIBLE);
                view.setDrawingCacheEnabled(true);
                view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                view.buildDrawingCache(true);
                Bitmap b = Bitmap.createBitmap(view.getDrawingCache());//loadBitmapFromView(view,view.getWidth(),view.getHeight());
                b.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(jpegPath));
            } catch (Exception e) {
                e.printStackTrace();
                Log.v("", "Error => " + e.getMessage());
            }
            hideThisView.setVisibility(View.VISIBLE);
        }
        Log.v("", "path => " + jpegPath);
        return jpegPath;
    }

    public static void shareImage(Activity activity, String loadedImage) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        //intent.putExtra(Intent.EXTRA_TEXT, "Hey view/download this image");
        Uri screenshotUri = Uri.parse("file://" + loadedImage);

        intent.putExtra(Intent.EXTRA_TEXT, "Download App Gujarat Election 2017 : Social Engineering. \n" + "https://goo.gl/sThP4b");
        intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
        intent.setType("image/*");
        activity.startActivity(Intent.createChooser(intent, "Share via..."));

    }

    public static <T extends RecyclerView.Adapter> void grid(Context context,
                                                             RecyclerView recyclerView, T t) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(35));
        recyclerView.setAdapter(t);
    }

    public static <T extends RecyclerView.Adapter> void grid(Context context,
                                                             RecyclerView recyclerView,
                                                             T t,
                                                             int colums,
                                                             int margin) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, colums);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(margin));
        recyclerView.setAdapter(t);
    }

    public static void shareImageOnly(Activity activity, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        //intent.putExtra(Intent.EXTRA_TEXT, "Hey view/download this image");

        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/*");
        activity.startActivity(Intent.createChooser(intent, "Share via..."));

    }

    public static <T extends RecyclerView.Adapter> void linearHorizontal
            (Context context, RecyclerView recyclerView, T t) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(20));
        recyclerView.setAdapter(t);
    }

    public static <T extends RecyclerView.Adapter> void linearHorizontal
            (Context context, RecyclerView recyclerView, T t, int margin) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(margin));
        recyclerView.setAdapter(t);
    }


    public static <T extends RecyclerView.Adapter> void linear
            (Context context, RecyclerView recyclerView, T t) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(20));
        recyclerView.setAdapter(t);
    }

    public static <T extends RecyclerView.Adapter> void linear
            (Context context, RecyclerView recyclerView, T t, int margin) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(margin));
        recyclerView.setAdapter(t);
    }


    public static Bitmap getBitmap(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ALPHA_8;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }


    public static String loadJsonFromAsset(String filename, Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();

        return new String(formArray);
    }

    public static Cursor getCursorFromUri(Context context, Uri uri, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri, projection,
                null, null, null);
        boolean isAvailable = cursor.moveToFirst();
        return isAvailable ? cursor : null;
    }

    //save image
    public static void imageDownload(Context ctx, String url, boolean shouldShare) {
        Picasso.with(ctx)
                .load(url)
                .into(getTarget(ctx, url, shouldShare));
        Toast.makeText(ctx, "Saved", Toast.LENGTH_SHORT).show();
    }  //target to save

    private static Target getTarget(final Context ctx, final String url, final boolean shouldShare) {
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        File path = Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES + "/Biology/");
                        File file = new File(path, "Materials_" + System.currentTimeMillis() + ".jpg");
                        try {
                            // Make sure the Pictures directory exists.
                            path.mkdirs();
                            file.createNewFile();
                            FileOutputStream ostream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
                            ostream.flush();
                            ostream.close();
                            if (shouldShare) {
                                Uri screenshotUri = Uri.fromFile(file);
                            } else {
                            }


                        } catch (IOException e) {
                            Log.e("IOException", e.getLocalizedMessage());
                        }
                    }
                }).start();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };
        return target;
    }

    public static boolean isOnline() {
        return true;
    }
}
