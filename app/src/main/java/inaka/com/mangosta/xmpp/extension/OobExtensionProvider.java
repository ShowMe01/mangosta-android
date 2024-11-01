package inaka.com.mangosta.xmpp.extension;

import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.xmlpull.v1.XmlPullParser;

public class OobExtensionProvider extends ExtensionElementProvider<OobExtension> {
    @Override
    public OobExtension parse(XmlPullParser parser, int initialDepth) throws Exception {
        String url = null;

        while (true) {
            int eventType = parser.next();
            if (eventType == XmlPullParser.START_TAG) {
                if ("url".equals(parser.getName())) {
                    url = parser.nextText();
                }
            } else if (eventType == XmlPullParser.END_TAG && "x".equals(parser.getName())) {
                break;
            }
        }

        return new OobExtension(url);
    }
}
