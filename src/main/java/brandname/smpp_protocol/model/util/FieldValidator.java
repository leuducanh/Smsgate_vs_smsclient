package brandname.smpp_protocol.model.util;

import brandname.smpp_protocol.exceptions.ValidateException;

public class FieldValidator extends Validator {
    public static void checkFieldStringLength(String fieldName, String src, int min, int max) throws ValidateException {
        int length = src == null ? 0 : src.length();
        boolean lengthIsFitIn = validateLength(length, min, max);
        if (!lengthIsFitIn) {
            throw new ValidateException(fieldName, src, "length not fit in min-max");
        }
    }

    public static void checkFieldBytesLength(String fieldName, byte[] src, int min, int max) throws ValidateException {
        int length = src.length;
        boolean lengthIsFitIn = validateLength(length, min, max);
        if (!lengthIsFitIn) {
            throw new ValidateException(fieldName, DataTransformer.bytesToHexString(src), "length not fit in min-max");
        }
    }
}
