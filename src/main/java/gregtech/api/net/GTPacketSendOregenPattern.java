package gregtech.api.net;

import net.minecraft.world.IBlockAccess;

import com.google.common.io.ByteArrayDataInput;

import gregtech.api.util.GTLog;
import gregtech.common.GTWorldgenerator;
import gregtech.common.GTWorldgenerator.OregenPattern;
import io.netty.buffer.ByteBuf;

public class GTPacketSendOregenPattern extends GTPacket {

    protected OregenPattern pattern = OregenPattern.AXISSYMMETRICAL;

    public GTPacketSendOregenPattern() {
        super();
    }

    public GTPacketSendOregenPattern(OregenPattern pattern) {
        super();
        this.pattern = pattern;
    }

    @Override
    public void encode(ByteBuf aOut) {
        aOut.writeInt(this.pattern.ordinal());
    }

    @Override
    public GTPacket decode(ByteArrayDataInput aData) {
        int ordinal = aData.readInt();
        // make sure we get valid data:
        if (ordinal >= 0 && ordinal < OregenPattern.values().length) {
            return new GTPacketSendOregenPattern(OregenPattern.values()[ordinal]);
        }
        // invalid data, default to AXISSYMMETRICAL:
        GTLog.err.printf(
            "Received invalid data! Received %d but value must be between 0 and %d! Default (0) will be used.%n",
            ordinal,
            OregenPattern.values().length - 1);
        return new GTPacketSendOregenPattern();
    }

    @Override
    public byte getPacketID() {
        return GTPacketTypes.SEND_OREGEN_PATTERN.id;
    }

    @Override
    public void process(IBlockAccess aWorld) {
        GTWorldgenerator.oregenPattern = this.pattern;
    }

}
