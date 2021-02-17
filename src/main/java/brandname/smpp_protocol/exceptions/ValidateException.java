package brandname.smpp_protocol.exceptions;

public class ValidateException extends SmppException{

    private String fieldName;
    private String value;
    private String whyError;
    private String stepError;

    public ValidateException(String fieldName, String value, String whyError) {
        super(fieldName + " " + value + " " + whyError);

        this.fieldName = fieldName;
        this.value = value;
        this.whyError = whyError;
    }

    public void setStepError(String stepError) {
        this.stepError = stepError;
    }
}
