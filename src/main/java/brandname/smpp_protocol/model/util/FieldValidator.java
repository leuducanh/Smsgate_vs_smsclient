package brandname.smpp_protocol.model.util;

import brandname.smpp_protocol.exceptions.ValidateException;

public class FieldValidator extends Validator{
    public static void checkFieldStringLength(String fieldName,String src, int min, int max) throws ValidateException {
         boolean lengthIsFitIn = validateStringLength(src, min, max);
         if(!lengthIsFitIn) {
             throw new ValidateException(fieldName, src,"length not fit in min-max");
         }
    }

}
