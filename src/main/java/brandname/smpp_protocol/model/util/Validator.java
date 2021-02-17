package brandname.smpp_protocol.model.util;

import brandname.smpp_protocol.exceptions.ValidateException;

public class Validator {

    public static boolean validateStringLength(String src, int min, int max){
        if(src == null || min > src.length() || max < src.length()) {
            return false;
        }
        return true;
    }

}
