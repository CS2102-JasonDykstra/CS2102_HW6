public class DuplicateVotesException extends Exception {

    private String message;
    private String name;

    public DuplicateVotesException (String name) {
        this.message = name + " entered multiple times.";
        this.name = name;
    }


    public String getMessage() {
        return message;
    }
}
