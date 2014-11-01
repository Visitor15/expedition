package forged.expedition.services;

/**
 * Created by nchampagne on 11/1/14.
 */
public class Events {

    public static enum RequestCommand {
        ACCEPT_RETURN_JSON_RESPONSE,
        ACCEPT_PARSE_JSON_RESPONSE,

    }

    public static enum RequestResponse {
        REQUEST_ACK,
        REQUEST_PROCESSING,
        REQUEST_UPDATE,
        REQUEST_FINISHED,
        REQUEST_ERROR,
        REQUEST_UNKNOWN
    }
}
