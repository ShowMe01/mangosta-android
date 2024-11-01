package inaka.com.mangosta.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;

public class FileUtils {

    public static String getFileName(ContentResolver contentResolver, Uri uri) {
        String[] projection = {MediaStore.Images.Media.DISPLAY_NAME};
        Cursor cursor = contentResolver.query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
            cursor.moveToFirst();
            String fileName = cursor.getString(column_index);
            cursor.close();
            return fileName;
        }
        return null;
    }

    public static long getFileSize(ContentResolver contentResolver, Uri uri) {
        String[] projection = {MediaStore.Images.Media.SIZE};
        Cursor cursor = contentResolver.query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE);
            cursor.moveToFirst();
            long fileSize = cursor.getLong(column_index);
            cursor.close();
            return fileSize;
        }
        return 0;
    }

    public static String getMimeType(ContentResolver contentResolver, Uri uri) {
        String fileName = getFileName(contentResolver, uri);
        String extension = MimeTypeMap.getFileExtensionFromUrl(fileName);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }

    public static String getRealPathFromUri(ContentResolver contentResolver, Uri uri) {
        String path = null;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = contentResolver.query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            path = cursor.getString(column_index);
            cursor.close();
        }
        return path;
    }

}
