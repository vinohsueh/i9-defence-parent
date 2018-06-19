package i9.defence.platform.socket.context;


public class DeviceAttribute implements java.io.Serializable {

    private static final long serialVersionUID = -7424171401590678894L;

    public String systemId;// 系统编号(十六进制)

    public byte loop;// 回路

    public String deviceAddress;// 设备地址

    public String getSystemId() {
        return systemId;
    }

    public byte getLoop() {
        return loop;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public DeviceAttribute(String systemId, byte loop, String deviceAddress) {
        this.systemId = systemId;
        this.loop = loop;
        this.deviceAddress = deviceAddress;
    }

    public DeviceAttribute() {
    }
}
