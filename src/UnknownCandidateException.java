public class UnknownCandidateException extends Exception {

    private String message;
    private String name;

    public UnknownCandidateException(String name) {
        this.message = name + " is not on ballot.";
        this.name = name;
    }


    public String getName() {
        return this.name;
    }


    public String getMessage() {
        return message;
    }
}
