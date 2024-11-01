package inaka.com.mangosta.xmpp.extension;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;

public class OobExtension implements ExtensionElement {
    public static final String ELEMENT = "x";
    public static final String NAMESPACE = "jabber:x:oob";

    private final String url;

    public OobExtension(String url) {
        this.url = url;
    }

    public static OobExtension fromUrl(String url) {
        return new OobExtension(url);
    }

    public static OobExtension fromMessage(Message message) {
        return message.getExtension(OobExtension.ELEMENT, OobExtension.NAMESPACE);
    }


    @Override
    public String getNamespace() {
        return NAMESPACE;
    }

    @Override
    public String getElementName() {
        return ELEMENT;
    }

    @Override
    public CharSequence toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(getElementName()).append(" xmlns='").append(getNamespace()).append("'>");
        sb.append("<url>").append(url).append("</url>");
        sb.append("</").append(getElementName()).append(">");
        return sb.toString();
    }

    public String getUrl() {
        return url;
    }


}
