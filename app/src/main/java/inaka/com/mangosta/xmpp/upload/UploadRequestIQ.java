package inaka.com.mangosta.xmpp.upload;

import org.jivesoftware.smack.packet.IQ;

public class UploadRequestIQ extends IQ {
    private final String fileName;
    private final int fileSize;
    private final String contentType;

    public UploadRequestIQ(String fileName, int fileSize, String contentType) {
        super("request", "urn:xmpp:http:upload:0");
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.contentType = contentType;
    }

    @Override
    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder xml) {
        xml.attribute("filename", fileName);
        xml.attribute("size", fileSize);
        xml.attribute("content-type", contentType);
        xml.rightAngleBracket();
        return xml;
    }
}

