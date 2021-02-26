package brandname.smpp_protocol.model.util;

public class DataTransformer {

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder hexStringBuilder = new StringBuilder();
        for(byte b : bytes) {
            int byteToInt = b;
            hexStringBuilder.append(Integer.toHexString(byteToInt));
            hexStringBuilder.append(" ");
        }
        return hexStringBuilder.toString();
    }

}
