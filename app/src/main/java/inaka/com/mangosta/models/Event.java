package inaka.com.mangosta.models;

import org.jxmpp.jid.Jid;

import de.greenrobot.event.EventBus;

public class Event {

    public enum Type {
        ROOMS_LOADED,
        STICKER_SENT,
        GO_BACK_FROM_CHAT,
        CONTACTS_CHANGED,
        BLOG_POST_CREATED,
        PRESENCE_RECEIVED,
        PRESENCE_SUBSCRIPTION_REQUEST,
        REFRESH_UNREAD_MESSAGES_COUNT,
        ROSTER_CHANGED,
        IMAGE_UPLOADED,
    }

    private Type mType;
    private String mImageName;
    private Jid mJidSender;
    private Object mData;

    public Event() {

    }

    public Event(Type type) {
        this.mType = type;
    }

    public Event(Type type, Object data) {
        this.mType = type;
        this.mData = data;
    }

    public Event(Type type, String imageName) {
        this.mType = type;
        this.mImageName = imageName;
    }

    public Event(Type type, Jid sender) {
        this.mType = type;
        this.mJidSender = sender;
    }

    public Type getType() {
        return mType;
    }

    public String getImageName() {
        return mImageName;
    }

    public Jid getJidSender() {
        return mJidSender;
    }

    public Object getData() {
        return mData;
    }

    public void post() {
        EventBus.getDefault().post(this);
    }

}
