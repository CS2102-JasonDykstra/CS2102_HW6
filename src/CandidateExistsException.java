
public class CandidateExistsException extends Exception {

    private String message;
    private String name;

    public CandidateExistsException(String name) {
        this.message = "Candidate already exists in the ballot.";
        this.name = name;
    }


    public String getMessage() {
        return message;
    }
}
