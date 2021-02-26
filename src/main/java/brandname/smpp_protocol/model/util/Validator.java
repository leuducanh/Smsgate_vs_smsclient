package brandname.smpp_protocol.model.util;

import brandname.smpp_protocol.exceptions.ValidateException;

public class Validator {

    protected static boolean validateLength(int length, int min, int max){
        if( min > length || max < length) {
            return false;
        }
        return true;
    }

}
