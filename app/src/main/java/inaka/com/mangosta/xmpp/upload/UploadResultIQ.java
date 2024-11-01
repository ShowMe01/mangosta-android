package inaka.com.mangosta.xmpp.upload;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.UnparsedIQ;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

public class UploadResultIQ extends IQ {

    public static final String ELEMENT = "slot";
    public static final String NAMESPACE = "urn:xmpp:http:upload:0";

    private String putUrl;
    private String getUrl;

    // 构造函数
    public UploadResultIQ() {
        super(ELEMENT, NAMESPACE);
    }

    public String getPutUrl() {
        return putUrl;
    }

    public String getGetUrl() {
        return getUrl;
    }

    @Override
    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder xml) {
        xml.rightAngleBracket();
        return xml;
    }

    // 自定义解析器，用于解析 <slot> 节点
    public static UploadResultIQ from(UnparsedIQ iq) throws XmlPullParserException, IOException {
        String iqXml = iq.getContent().toString();

        // 创建一个 XmlPullParser 实例
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

        factory.setNamespaceAware(true);

        XmlPullParser parser = factory.newPullParser();
        parser.setInput(new StringReader(iqXml));

        UploadResultIQ resultIQ = new UploadResultIQ();
        resultIQ.setStanzaId(iq.getStanzaId());
        int eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                String name = parser.getName();
                if (name.equals("put")) {
                    resultIQ.putUrl = parser.getAttributeValue(null, "url");
                } else if (name.equals("get")) {
                    resultIQ.getUrl = parser.getAttributeValue(null, "url");
                }
            }
            eventType = parser.next(); // 移动到下一个事件
        }

        return resultIQ;
    }
}
