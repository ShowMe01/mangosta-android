package inaka.com.mangosta.models;

import android.net.Uri;

public class PendingImgMsg {

    public Uri uri;

    public String jid;

    public String iqId;

    public String messageId;

    public String imgUrl;

    public int status;

    public static final int INIT = 1;

    public static final int UPLOADED = 2;

    public static final int SENT = 3;

    public PendingImgMsg() {
        status = INIT;
    }

}
