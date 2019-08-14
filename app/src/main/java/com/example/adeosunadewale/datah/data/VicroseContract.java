package com.example.adeosunadewale.datah.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ADEOSUN ADEWALE on 10/27/2017.
 */

public final class VicroseContract {

    public static final String CONTENT_AUTHORITY = "com.example.adeosunadewale.datah";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_VICROSE = "DATA";

    private VicroseContract(){}
    public static final class VicEntry implements BaseColumns{
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_VICROSE);

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + PATH_VICROSE;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + PATH_VICROSE;
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "NAME";
        public final static String TABLE_NAME = "DATA";
        public final static String COLUMN_SI = "SI";
        public final static String COLUMN_GL = "GL";
        public final static String COLUMN_BK = "BK";
        public final static String COLUMN_FB = "FB";
        public final static String COLUMN_Wst = "Wst";
        public final static String COLUMN_Bst = "Bst";
        public final static String COLUMN_UndBst = "UndBst";
        public final static String COLUMN_lb = "Lb";
        public final static String COLUMN_Sw = "Sw";
        public final static String COLUMN_Slv = "Slv";
        public final static String COLUMN_Knel = "Knel";
        public final static String COLUMN_Phone = "Phone";
    }
}
