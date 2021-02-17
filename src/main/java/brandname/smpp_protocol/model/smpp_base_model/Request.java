package brandname.smpp_protocol.model.smpp_base_model;

public abstract class Request extends PDU {

    protected abstract Response createResponse();

    public Request(int commandId) {
        super(commandId);
    }

    protected Response getResponse(){
        Response response = createResponse();
        response.assignSequenceWithNextSequence();
        return response;
    }

    public boolean canBeResponse() {
        return true;
    }

    public boolean isRequest(){
        return true;
    }
}
