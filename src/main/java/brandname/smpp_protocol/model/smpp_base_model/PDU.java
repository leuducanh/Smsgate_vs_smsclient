package brandname.smpp_protocol.model.smpp_base_model;

import brandname.smpp_protocol.exceptions.NotEnoughByteInByteBufferException;
import brandname.smpp_protocol.exceptions.PDUException;
import brandname.smpp_protocol.exceptions.TerminatingZeroNotFoundException;
import brandname.smpp_protocol.model.ByteBuffer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PDU extends ByteData {


    public static final byte VALID_NONE = 0;
    public static final byte VALID_HEADER = 1;
    public static final byte VALID_HEADER_AND_BODY = 2;
    public static final byte VALID_HEADER_AND_BODY_AND_OPTIONAL_PARAM = 3;

    private static AtomicInteger sequenceNumberGlobal = new AtomicInteger(0);

    private PDUHeader header = null;

    private LinkedList<>

    @Override
    public void setData() throws NotEnoughByteInByteBufferException, TerminatingZeroNotFoundException, PDUException {
    }

    @Override
    public ByteBuffer getData() {
        return ;
    }
}
