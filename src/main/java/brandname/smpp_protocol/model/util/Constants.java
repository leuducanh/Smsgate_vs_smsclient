package brandname.smpp_protocol.model.util;

public class Constants {

    public static int SMPP_INTERFACE = 0x34;

    public static final int PDU_HEADER_SIZE = 4*4; // 4 * int -> 16 byte
    public static final int TLV_TAG_FIELD_SIZE = 2; // 2byte
    public static final int TLV_LENGTH_FIELD_SIZE = 2; // 2byte

    public static class CommandId {
        public static final int BIND_RECEIVER = 0x00000001;
        public static final int BIND_RECEIVER_RESP = 0x80000001;

        public static final int BIND_TRANSMITTER = 0x00000002;
        public static final int BIND_TRANSMITTER_RESP = 0x80000002;
    }

    public static class DefaultPDUField {
        public static final String DEFAULT_EMPTY_FIELD = "";
    }

    public static class DefaultTVLField {
        public static final short sc_interface_version = 0x0210;
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
        public static final byte National  = 0x05;
        public static final byte Private = 0x06;
        public static final byte ERMES = 0x07;
        public static final byte Internet = 0x08;
        public static final byte WAPClientId = 0x09;
    }
}
