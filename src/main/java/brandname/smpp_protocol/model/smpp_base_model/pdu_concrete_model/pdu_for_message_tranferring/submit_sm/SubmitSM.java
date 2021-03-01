package brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_message_tranferring.submit_sm;

import brandname.smpp_protocol.exceptions.*;
import brandname.smpp_protocol.model.ByteBuffer;
import brandname.smpp_protocol.model.smpp_base_model.Request;
import brandname.smpp_protocol.model.smpp_base_model.Response;
import brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.Address;
import brandname.smpp_protocol.model.smpp_base_model.pdu_concrete_model.pdu_for_message_tranferring.*;
import brandname.smpp_protocol.model.smpp_base_model.tlv_concrete_model.*;

import java.io.UnsupportedEncodingException;

import static brandname.smpp_protocol.model.util.Constants.CommandId.SUBMIT_SM;
import static brandname.smpp_protocol.model.util.Constants.*;
import static brandname.smpp_protocol.model.util.Constants.DefaultPDUField.*;
import static brandname.smpp_protocol.model.util.Constants.TLVTag.*;

public class SubmitSM extends Request {

    private String service_type = null;
    private Address source_address = new Address();
    private Address dest_address = new Address();
    private byte esm_class = ZERO_IN_HEX_BYTE;
    private byte protocol_id = ZERO_IN_HEX_BYTE;
    private byte priority_flag = ZERO_IN_HEX_BYTE;

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

    private byte registered_delivery = ZERO_IN_HEX_BYTE;
    // chỉ ra rằng tin nhắn thay thế cho một tin đã tồn tại.
    private byte replace_if_present_flag = ZERO_IN_HEX_BYTE;
    private byte data_coding = ZERO_IN_HEX_BYTE;
    // dùng các msg có sẵn trên smsc , nếu không set là null
    private byte sm_default_msg_id = ZERO_IN_HEX_BYTE;
    private ShortMessage shortMessage = new ShortMessage();

    private TLVShort user_message_reference = new TLVShort(USER_MESSAGE_REFERENCE);
    private TLVShort source_port = new TLVShort(SOURCE_PORT);
    private TLVByte source_addr_subunit = new TLVByte(SOURCE_ADDR_SUBUNIT);
    private TLVShort destination_port = new TLVShort(DESTINATION_PORT);
    private TLVByte dest_addr_subunit = new TLVByte(DEST_ADDR_SUBUNIT);
    private TLVShort sar_msg_ref_num = new TLVShort(SAR_MSG_REF_NUM);
    private TLVByte sar_total_segments = new TLVByte(SAR_TOTAL_SEGMENTS);
    private TLVByte sar_segment_seqnum = new TLVByte(SAR_SEGMENT_SEQNUM);
    private TLVByte more_messages_to_send = new TLVByte(MORE_MESSAGES_TO_SEND);
    private TLVByte payload_type = new TLVByte(PAYLOAD_TYPE);
    private TLVOctets message_payload = new TLVOctets(MESSAGE_PAYLOAD);
    private TLVByte privacy_indicator = new TLVByte(PRIVACY_INDICATOR);
    private TLVOctets  callback_num = new TLVOctets(CALLBACK_NUM);
    private TLVByte callback_num_pres_ind = new TLVByte(CALLBACK_NUM_PRES_IND);
    private TLVOctets callback_num_atag = new TLVOctets(CALLBACK_NUM_ATAG);
    private TLVOctets source_subaddress = new TLVOctets(SOURCE_SUBADDRESS);
    private TLVOctets dest_subaddress = new TLVOctets(DEST_SUBADDRESS);
    private TLVByte user_response_code = new TLVByte(USER_RESPONSE_CODE);
    private TLVByte display_time = new TLVByte(DISPLAY_TIME);
    private TLVShort sms_signal = new TLVShort(SMS_SIGNAL);
    private TLVByte ms_validity = new TLVByte(MS_VALIDITY);
    private TLVByte ms_msg_wait_facilities = new TLVByte(MS_MSG_WAIT_FACILITIES);
    private TLVByte number_of_messages = new TLVByte(NUMBER_OF_MESSAGES);
    private TLVEmpty alert_on_msg_delivery = new TLVEmpty(ALERT_ON_MSG_DELIVERY);
    private TLVByte language_indicator = new TLVByte(LANGUAGE_INDICATOR);
    private TLVByte its_reply_type = new TLVByte(ITS_REPLY_TYPE);
    private TLVShort its_session_info = new TLVShort(ITS_SESSION_INFO);
    private TLVByte ussd_service_op = new TLVByte(USSD_SERVICE_OP);

    public SubmitSM() {
        super(SUBMIT_SM);

        defaultTlvOfThisPdu.add(user_message_reference);
        defaultTlvOfThisPdu.add(source_port);
        defaultTlvOfThisPdu.add(source_addr_subunit);
        defaultTlvOfThisPdu.add(destination_port);
        defaultTlvOfThisPdu.add(dest_addr_subunit);
        defaultTlvOfThisPdu.add(sar_msg_ref_num);
        defaultTlvOfThisPdu.add(sar_total_segments);
        defaultTlvOfThisPdu.add(sar_segment_seqnum);
        defaultTlvOfThisPdu.add(more_messages_to_send);
        defaultTlvOfThisPdu.add(payload_type);
        defaultTlvOfThisPdu.add(message_payload);
        defaultTlvOfThisPdu.add(privacy_indicator);
        defaultTlvOfThisPdu.add(callback_num);
        defaultTlvOfThisPdu.add(callback_num_pres_ind);
        defaultTlvOfThisPdu.add(callback_num_atag);
        defaultTlvOfThisPdu.add(source_subaddress);
        defaultTlvOfThisPdu.add(dest_subaddress);
        defaultTlvOfThisPdu.add(user_response_code);
        defaultTlvOfThisPdu.add(display_time);
        defaultTlvOfThisPdu.add(sms_signal);
        defaultTlvOfThisPdu.add(ms_validity);
        defaultTlvOfThisPdu.add(ms_msg_wait_facilities);
        defaultTlvOfThisPdu.add(number_of_messages);
        defaultTlvOfThisPdu.add(alert_on_msg_delivery);
        defaultTlvOfThisPdu.add(language_indicator);
        defaultTlvOfThisPdu.add(its_reply_type);
        defaultTlvOfThisPdu.add(its_session_info);
        defaultTlvOfThisPdu.add(ussd_service_op);
    }

    @Override
    protected Response createResponse() {
        return null;
    }

    @Override
    protected void setBody(ByteBuffer byteBuffer) throws TerminatingZeroNotFoundException, NotEnoughByteInByteBufferException, ValidateException, UnsupportedEncodingException, PDUException, InvalidPDUException {
        service_type = byteBuffer.removeCString();
        source_address.setData(byteBuffer);
        dest_address.setData(byteBuffer);
        esm_class = byteBuffer.removeByte();
        protocol_id = byteBuffer.removeByte();
        priority_flag = byteBuffer.removeByte();
        schedule_delivery_time = byteBuffer.removeCString();
        validity_period = byteBuffer.removeCString();
        registered_delivery = byteBuffer.removeByte();
        replace_if_present_flag = byteBuffer.removeByte();
        data_coding = byteBuffer.removeByte();
        sm_default_msg_id = byteBuffer.removeByte();
        shortMessage.setData(byteBuffer);
    }
}
