package i9.defence.platform.socket.message.resp;

import java.util.Calendar;
import java.util.Date;

import i9.defence.platform.socket.message.MessageEncodeConvert;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class HeartbeatRespMessage extends MessageEncodeConvert {

    @Override
    public ByteBuf encode() {
        ByteBuf byteBuf = Unpooled.buffer(7);
        byteBuf.writeByte((byte) 6);
        byteBuf.writeBytes(this.timestamp);
        return byteBuf;
    }
    
    private byte[] timestamp;

    public HeartbeatRespMessage() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int Y = calendar.get(Calendar.YEAR);
        int M = calendar.get(Calendar.MONTH);
        int D = calendar.get(Calendar.DAY_OF_MONTH);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int i = calendar.get(Calendar.MINUTE);
        int s = calendar.get(Calendar.SECOND);
        this.timestamp = new byte[] { (byte) Y, (byte) M, (byte) D, (byte) h, (byte) i, (byte) s };
    }
}
