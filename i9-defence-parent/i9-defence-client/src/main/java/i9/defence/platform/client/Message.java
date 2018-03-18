package i9.defence.platform.client;

import i9.defence.platform.netty.libraries.req.DataMessage;
import i9.defence.platform.netty.libraries.req.UpStreamReqMessage;

public class Message {

    public UpStreamReqMessage makeUpStreamReqMessage() {
        UpStreamReqMessage upStreamReqMessage = new UpStreamReqMessage();
        return upStreamReqMessage;
    }
    
    public DataMessage makeDataMessage1() {
        DataMessage dataMessage = new DataMessage();
        return dataMessage;
    }
}
