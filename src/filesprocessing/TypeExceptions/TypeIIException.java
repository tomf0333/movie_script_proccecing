package filesprocessing.TypeExceptions;

/**
 * the Exception that i raise in case a TypeIIException case happens (as defined in the ex description),
 * only saves the message needed for the Exception.
 */
public class TypeIIException extends Exception{

    private String msg = "ERROR: ";

    public TypeIIException(String problem){
        this.msg += problem;
    }

    public String getMsg() {
        return msg;
    }
}
