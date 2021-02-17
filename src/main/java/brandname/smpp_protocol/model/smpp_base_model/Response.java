package brandname.smpp_protocol.model.smpp_base_model;

public abstract class Response extends PDU{

    public Response(int commandId) {
        super(commandId);
    }

    public boolean canBeResponse() {
        return true;
    }

    public boolean isRequest(){
        return true;
    }
}
