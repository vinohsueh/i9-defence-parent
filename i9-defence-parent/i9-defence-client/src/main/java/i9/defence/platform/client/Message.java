package i9.defence.platform.client;

import i9.defence.platform.netty.libraries.DataEnum;
import i9.defence.platform.netty.libraries.DataParseUtil;
import i9.defence.platform.netty.libraries.EncryptUtils;
import i9.defence.platform.netty.libraries.req.DataMessage;
import i9.defence.platform.netty.libraries.req.UpStreamReqMessage;

public class Message {

    public UpStreamReqMessage makeUpStreamReqMessage() {
        UpStreamReqMessage upStreamReqMessage = new UpStreamReqMessage();
        upStreamReqMessage.systemType = DataParseUtil.parseSignedShort(EncryptUtils.hexStringToBytes("0002"));
        upStreamReqMessage.systemId = "000000000001";
        upStreamReqMessage.source = (byte) 0;
        upStreamReqMessage.loop = (byte) 0;
        upStreamReqMessage.deviceAddress = "00000001";
        upStreamReqMessage.unit = 8;
        int i = 0;
        while (i <= 3) {
            upStreamReqMessage.dataList.add(this.makeDataMessage1(i));
            i ++;
        }
        while (i <= 7) {
            upStreamReqMessage.dataList.add(this.makeDataMessage2(i));
            i ++;
        }
        for (DataMessage dataMessage : upStreamReqMessage.dataList) {
            upStreamReqMessage.dataLen += dataMessage.getByteArray().length;
        }
        for (DataMessage dataMessage : upStreamReqMessage.dataList) {
            dataMessage.data = DataParseUtil.reverse(dataMessage.data);
        }
        return upStreamReqMessage;
    }
    
    public DataMessage makeDataMessage1(int channelId) {
        DataMessage dataMessage = new DataMessage();
        dataMessage.channelId = (byte) channelId;
        dataMessage.source = (byte) 0;
        dataMessage.type = (byte) DataEnum.T_ENUM.value;
        dataMessage.data = EncryptUtils.hexStringToBytes("00000000");
        dataMessage.len = (byte) dataMessage.data.length;
        dataMessage.Y = (byte) -1;
        dataMessage.M = (byte) -1;
        dataMessage.D = (byte) -1;
        dataMessage.H = (byte) -1;
        dataMessage.m = (byte) -1;
        dataMessage.S = (byte) -1;
        return dataMessage;
    }
    
    public DataMessage makeDataMessage2(int channelId) {
        DataMessage dataMessage = new DataMessage();
        dataMessage.channelId = (byte) channelId;
        dataMessage.source = (byte) 0;
        dataMessage.type = (byte) DataEnum.T_FLOAT.value;
//        int r = new Random().nextInt(100);
//        dataMessage.data = EncryptUtils.shortToByte((short) r);
        dataMessage.data = EncryptUtils.floatToByte(100.05f);
        
        dataMessage.len = (byte) dataMessage.data.length;
        dataMessage.Y = (byte) -1;
        dataMessage.M = (byte) -1;
        dataMessage.D = (byte) -1;
        dataMessage.H = (byte) -1;
        dataMessage.m = (byte) -1;
        dataMessage.S = (byte) -1;
        return dataMessage;
    }
}
