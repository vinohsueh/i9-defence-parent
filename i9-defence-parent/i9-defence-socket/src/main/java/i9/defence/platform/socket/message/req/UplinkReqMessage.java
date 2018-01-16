package i9.defence.platform.socket.message.req;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import i9.defence.platform.socket.message.MessageDecodeConvert;
import i9.defence.platform.socket.util.EncryptUtils;

public class UplinkReqMessage implements MessageDecodeConvert {

    private String systemType;// 系统类型(十六进制)

    private String systemId;// 系统编号(十六进制)

    private byte source;// 来源使用情况

    private byte loop;// 回路

    private String deviceAddress;// 设备地址

    private byte unit;

    private char dataLen;

    private List<DataMessage> data = new ArrayList<DataMessage>();

    public String getSystemType() {
        return systemType;
    }

    public String getSystemId() {
        return systemId;
    }

    public byte getSource() {
        return source;
    }

    public byte getLoop() {
        return loop;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public byte getUnit() {
        return unit;
    }

    public char getDataLen() {
        return dataLen;
    }

    public List<DataMessage> getData() {
        return data;
    }

    @Override
    public void decode(ByteBuffer byteBuffer) {
        {
            byte[] dst = new byte[2];
            byteBuffer.get(dst);
            this.systemType = EncryptUtils.bytesToHexString(dst);
        }
        {
            byte[] dst = new byte[6];
            byteBuffer.get(dst);
            this.systemId = EncryptUtils.bytesToHexString(dst);
        }
        this.source = byteBuffer.get();
        this.loop = byteBuffer.get();
        {
            byte[] dst = new byte[4];
            byteBuffer.get(dst);
            this.deviceAddress = EncryptUtils.bytesToHexString(dst);
        }
        this.unit = byteBuffer.get();
        this.dataLen = byteBuffer.getChar();
        for (char c = 0; c < dataLen; c++) {
            DataMessage dataMessage = new DataMessage();
            dataMessage.decode(byteBuffer);
            this.data.add(dataMessage);
        }
    }
}
