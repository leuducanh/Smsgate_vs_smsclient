package brandname.smpp_protocol.model.smpp_base_model;

import brandname.smpp_protocol.exceptions.NotEnoughByteInByteBufferException;
import brandname.smpp_protocol.exceptions.PDUException;
import brandname.smpp_protocol.exceptions.TerminatingZeroNotFoundException;
import brandname.smpp_protocol.model.ByteBuffer;

public class PDUHeader extends ByteData{
    private int commandLength = 0;
    private int commandId = 0;
    private int commandStatus = 0;
    private int sequenceNumber = 1;


    public ByteBuffer getData()
    {
        ByteBuffer buffer = new ByteBuffer();
        buffer.appendInt(getCommandLength());
        buffer.appendInt(getCommandId());
        buffer.appendInt(getCommandStatus());
        buffer.appendInt(getSequenceNumber());
        return buffer;
    }

    public void setData(ByteBuffer buffer)
            throws NotEnoughByteInByteBufferException
    {
        commandLength = buffer.removeInt();
        commandId = buffer.removeInt();
        commandStatus = buffer.removeInt();
        sequenceNumber = buffer.removeInt();
    }


    public int getCommandLength()
    {
        return commandLength;
    }

    public int getCommandId()
    {
        return commandId;
    }

    public int getCommandStatus()
    {
        return commandStatus;
    }

    public int getSequenceNumber()
    {
        return sequenceNumber;
    }

    public void setCommandLength(int cmdLen)
    {
        commandLength = cmdLen;
    }

    public void setCommandId(int cmdId)
    {
        commandId = cmdId;
    }

    public void setCommandStatus(int cmdStatus)
    {
        commandStatus = cmdStatus;
    }

    public void setSequenceNumber(int seqNr)
    {
        sequenceNumber = seqNr;
    }
}
