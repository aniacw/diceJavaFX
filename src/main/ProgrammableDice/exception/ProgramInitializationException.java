package main.ProgrammableDice.exception;

public class ProgramInitializationException extends Exception {
    public ProgramInitializationException() {
        super("Program not initialized");
    }

    public ProgramInitializationException(String programName, String triggerName) {
        super("Program not initialized: " + programName + ", trigger: " + triggerName);
    }

//    public ProgramInitializationException(String message, Throwable cause) {
//        super(message, cause);
//    }
//
//    public ProgramInitializationException(Throwable cause) {
//        super(cause);
//    }
}
