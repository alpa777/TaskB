package com.all.learning.custom_view.upload.pojo;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import com.all.learning.base.Utils;


/**
 * Created by root on 23/9/17.
 */

public class LocalAttrs {
    public String path;
    public String displayName;
    public String height;
    public String width;
    public String mimeType;
    public String size;

    public static LocalAttrs cursorToFileAttrs(Context context, Uri uri) {
        String[] projection = new String[]{MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.HEIGHT,
                MediaStore.Images.Media.WIDTH,
                MediaStore.Images.Media.MIME_TYPE,
                MediaStore.Images.Media.SIZE
        };

        Cursor cursor = Utils.getCursorFromUri(context, uri, projection);

        LocalAttrs fileAttrs = new LocalAttrs();
        fileAttrs.path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        fileAttrs.displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
        fileAttrs.height = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.HEIGHT));
        fileAttrs.width = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.WIDTH));
        fileAttrs.mimeType = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.MIME_TYPE));
        fileAttrs.size = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.SIZE));

        return fileAttrs;
    }

    public Bitmap getBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ALPHA_8;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }
}
