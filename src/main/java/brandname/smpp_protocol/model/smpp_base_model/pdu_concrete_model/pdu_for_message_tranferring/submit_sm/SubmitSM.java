package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_message_tranferring.submit_sm;

import brandname.smpp_protocol.exceptions.*;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.Request;
import brandname.smpp_protocol.model.smpp_base_model.Response;
import brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.Address;
import brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_message_tranferring.*;
import brandname.smpp_protocol.model.smpp_base_model.tlv_concrete_model.TLVByte;
import brandname.smpp_protocol.model.smpp_base_model.tlv_concrete_model.TLVShort;

import java.io.UnsupportedEncodingException;

import static brandname.smpp_protocol.model.util.Constants.CommandId.SUBMIT_SM;
import static brandname.smpp_protocol.model.util.Constants.*;
import static brandname.smpp_protocol.model.util.Constants.DefaultPDUField.*;
import static brandname.smpp_protocol.model.util.Constants.TLVTag.*;

public class SubmitSM extends Request {

    private String service_type = null;
    private Address source_address = new Address();
    private Address dest_address = new Address();
    private Integer esm_class = ZERO_IN_HEX;
    private Integer protocol_id = (Integer) DEFAULT_NULL_FIELD;
    private Integer priority_flag = ZERO_IN_HEX;

//    “YYMMDDhhmmsstnnp” where
//‘YY’ last two digits of the year (00-99)
//‘MM’ month (01-12)
//‘DD’ day (01-31)
//‘hh’ hour (00-23)
//‘mm’ minute (00-59)
//‘ss’ second (00-59)
//‘t’ tenths of second (0-9)
//‘nn’ Time difference in quarter hours between local
//    time (as expressed in the first 13 octets) and UTC
//(Universal Time Constant) time (00-48).
//            ‘p’ - “+” Local time is in quarter hours advanced in relation
//    to UTC time.
// “-” Local time is in quarter hours retarded in relation
//    to UTC time.
// “R” Local time is relative to the current SMSC time.
    private String schedule_delivery_time = DEFAULT_EMPTY_FIELD;
    private String validity_period = DEFAULT_EMPTY_FIELD;

//    x x x x x x 0 0 No SMSC Delivery Receipt requested (default)
//    x x x x x x 0 1 SMSC Delivery Receipt requested where final delivery
//    outcome is delivery success or failure
//    x x x x x x 1 0 SMSC Delivery Receipt requested where the final
//    delivery outcome is delivery failure
//    x x x x x x 1 1 reserved
//    SME originated Acknowledgement (bits 3 and 2)
//    x x x x 0 0 x x No recipient SME acknowledgment requested (default)
//    x x x x 0 1 x x SME Delivery Acknowledgement requested
//    x x x x 1 0 x x SME Manual/User Acknowledgment requested
//    x x x x 1 1 x x Both Delivery and Manual/User Acknowledgment requested
//    Intermediate Notification (bit 5)
//    x x x 0 x x x x No Intermediate notification requested (default)
//    x x x 1 x x x x Intermediate notification requested **

    private Integer registered_delivery = (Integer) DEFAULT_NULL_FIELD;
    // chỉ ra rằng tin nhắn thay thế cho một tin đã tồn tại.
    private Integer replace_if_present_flag = (Integer) DEFAULT_NULL_FIELD;
    private Integer data_coding = (Integer) DEFAULT_NULL_FIELD;
    // dùng các msg có sẵn trên smsc , nếu không set là null
    private Integer sm_default_msg_id = (Integer) DEFAULT_NULL_FIELD;
    private ShortMessage shortMessage = new ShortMessage();

    private TLVShort user_message_reference = new TLVShort(USER_MESSAGE_REFERENCE);
    private TLVShort source_port = new TLVShort(SOURCE_PORT);
    private TLVByte dest_addr_subunit = new TLVByte(DEST_ADDR_SUBUNIT);
    private TLVShort destination_port = new TLVShort(DESTINATION_PORT);
    private TLVByte dest_addr_subunit = new TLVByte(DEST_ADDR_SUBUNIT);





    public SubmitSM() {
        super(SUBMIT_SM);
    }

    @Override
    protected Response createResponse() {
        return null;
    }

    @Override
    protected void setBody(ByteBuffer byteBuffer) throws TerminatingZeroNotFoundException, NotEnoughByteInByteBufferException, ValidateException, UnsupportedEncodingException, PDUException, InvalidPDUException {

    }
}
