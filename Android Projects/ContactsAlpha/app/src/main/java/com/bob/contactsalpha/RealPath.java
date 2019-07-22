package com.bob.contactsalpha;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.text.TextUtils;
import android.util.Log;

import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import static android.content.ContentResolver.SCHEME_CONTENT;
import static android.content.ContentResolver.SCHEME_FILE;
import static android.content.ContentValues.TAG;

public class RealPath
{
    //function for when calling without specifying google images, will be updated as it is not functioning on recent api 28
    @SuppressLint("NewApi")
    public static String getRealPathFromURI(Context context, Uri uriPhoto)
    {
    
        /**
         * This is useful when an image is available in sdcard physically.
         *
         * @param uriPhoto
         * @return
         */
        
        if (uriPhoto == null)
            return null;
    
        if (SCHEME_FILE.equals(uriPhoto.getScheme())) {
            return uriPhoto.getPath();
        } else if (SCHEME_CONTENT.equals(uriPhoto.getScheme())) {
            final String[] filePathColumn = {MediaStore.MediaColumns.DATA,
                    MediaStore.MediaColumns.DISPLAY_NAME};
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(uriPhoto, filePathColumn, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    final int columnIndex = (uriPhoto.toString()
                            .startsWith("content://com.google.android.gallery3d")) ? cursor
                            .getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME)
                            : cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
                
                    // Picasa images on API 13+
                    if (columnIndex != -1) {
                        String filePath = cursor.getString(columnIndex);
                        if (!TextUtils.isEmpty(filePath)) {
                            return filePath;
                        }
                    }
                }
            } catch (IllegalArgumentException e) {
                // Nothing we can do
                Log.d(TAG, "IllegalArgumentException");
                e.printStackTrace();
            } catch (SecurityException ignored) {
                Log.d(TAG, "SecurityException");
                // Nothing we can do
                ignored.printStackTrace();
            } finally {
                if (cursor != null)
                    cursor.close();
            }
        }
        return null;
    }
    
        /**
         * This is useful when an image is not available in sdcard physically but it displays into photos application via google drive(Google Photos) and also for if image is available in sdcard physically.
         *
         * @param uriPhoto
         * @return
         */

        public static String getPathFromGooglePhotosUri(Context context, Uri uriPhoto)
        {
            //functional when acquiring information using google photos but...not all devices have google photos
            //will be updated in later builds
            
            if (uriPhoto == null)
                return null;
    
            FileInputStream input = null;
            FileOutputStream output = null;
            try
            {
                ParcelFileDescriptor pfd = context.getContentResolver().openFileDescriptor(uriPhoto, "r");
                FileDescriptor fd = pfd.getFileDescriptor();
                input = new FileInputStream(fd);
        
                String tempFilename = getTempFilename(context);
                output = new FileOutputStream(tempFilename);
        
                int read;
                byte[] bytes = new byte[4096];
                while ((read = input.read(bytes)) != -1)
                {
                    output.write(bytes, 0, read);
                }
                return tempFilename;
            } catch (IOException ignored)
            {
                // Nothing we can do
            } finally
            {
                closeSilently(input);
                closeSilently(output);
            }
            return null;
        }
    
    public static void closeSilently(Closeable c)
    {
        if (c == null)
            return;
        try {
            c.close();
        } catch (Throwable t) {
            // Do nothing
        }
    }
    
    private static String getTempFilename(Context context) throws IOException
    {
        File outputDir = context.getCacheDir();
        File outputFile = File.createTempFile("image", "tmp", outputDir);
        return outputFile.getAbsolutePath();
    }
    
    private void copyFile(File sourceFile, File destFile) throws IOException
    {
        if (!sourceFile.exists()) {
            return;
        }
        
        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null)
        {
            source.close();
        }
        if (destination != null)
        {
            destination.close();
        }
    }
}

