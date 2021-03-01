package brandname.smpp_protocol.model.util;

import brandname.smpp_protocol.model.smpp_base_model.tlv_concrete_model.TLVShort;

public class Constants {


    public static int SMPP_INTERFACE = 0x34;

    public static final int PDU_HEADER_SIZE = 4 * 4; // 4 * int -> 16 byte
    public static final int TLV_TAG_FIELD_SIZE = 2; // 2byte
    public static final int TLV_LENGTH_FIELD_SIZE = 2; // 2byte

    public static class CommandId {

        public static final int ENQUIRE_LINK = 0x00000015;
        public static final int ENQUIRE_LINK_RESP = 0x80000015;

        public static final int BIND_RECEIVER = 0x00000001;
        public static final int BIND_RECEIVER_RESP = 0x80000001;

        public static final int BIND_TRANSMITTER = 0x00000002;
        public static final int BIND_TRANSMITTER_RESP = 0x80000002;

        public static final int BIND_TRANSCEIVER = 0x00000009;
        public static final int BIND_TRANSCEIVER_RESP = 0x80000009;

        public static final int SUBMIT_SM = 0x00000004;
        public static final int SUBMIT_SM_RESP = 0x80000004;
    }

    public static class DefaultPDUField {
        public static final String DEFAULT_EMPTY_FIELD = "";
        public static int ZERO_IN_HEX = 0x00000000;
        public static byte ZERO_IN_HEX_BYTE = 0x00000000;
        public static Object DEFAULT_NULL_FIELD = null;
    }

    public static class TLVTag {
        public static final short USER_MESSAGE_REFERENCE = 0x0204;
        public static final short SOURCE_PORT = 0x020A;
        public static final short SOURCE_ADDR_SUBUNIT = 0x000D;
        public static final short DESTINATION_PORT = 0x020B;
        public static final short DEST_ADDR_SUBUNIT = 0x0005;
        public static final short SAR_MSG_REF_NUM = 0x020C;
        public static final short SAR_TOTAL_SEGMENTS = 0x020E;
        public static final short SAR_SEGMENT_SEQNUM = 0x020F;
        public static final short MORE_MESSAGES_TO_SEND = 0x0426;
        public static final short PAYLOAD_TYPE = 0x0019;
        public static final short MESSAGE_PAYLOAD = 0x0424;
        public static final short PRIVACY_INDICATOR = 0x0201;
        public static final short CALLBACK_NUM = 0x0381;
        public static final short CALLBACK_NUM_PRES_IND = 0x0302;
        public static final short CALLBACK_NUM_ATAG = 0x0303;
        public static final short SOURCE_SUBADDRESS = 0x0202;
        public static final short DEST_SUBADDRESS = 0x0203;
        public static final short USER_RESPONSE_CODE = 0x0205;
        public static final short DISPLAY_TIME = 0x1201;
        public static final short SMS_SIGNAL = 0x1203;
        public static final short MS_VALIDITY = 0x1204;
        public static final short MS_MSG_WAIT_FACILITIES = 0x0030;
        public static final short NUMBER_OF_MESSAGES = 0x0304;
        public static final short ALERT_ON_MSG_DELIVERY = 0x130C;

        public static final short LANGUAGE_INDICATOR = 0x020D;
        public static final short ITS_REPLY_TYPE = 0x1380;
        public static final short ITS_SESSION_INFO = 0x1383;
        public static final short USSD_SERVICE_OP = 0x0501;

    }

    public static class DefaultTVLField {
        // default in document
        public static final short sc_interface_version = 0x0210; // Generic
    }

    public static class TonData {
        public static final byte Unknown = 0x00;
        public static final byte International = 0x01;
        public static final byte National = 0x02;
        public static final byte NetworkSpecific = 0x03;
        public static final byte SubscriberNumber = 0x04;
        public static final byte Alphanumeric = 0x05;
        public static final byte Abbreviated = 0x06;
    }

    public static class NpiData {
        public static final byte Unknown = 0x00;
        public static final byte ISDN = 0x01;
        public static final byte Data = 0x02;
        public static final byte Telex = 0x03;
        public static final byte LandMobile = 0x04;
        public static final byte National = 0x05;
        public static final byte Private = 0x06;
        public static final byte ERMES = 0x07;
        public static final byte Internet = 0x08;
        public static final byte WAPClientId = 0x09;
    }
}
