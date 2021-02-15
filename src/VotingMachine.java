import java.util.LinkedList;

public class VotingMachine {
    private ElectionData election;

    public VotingMachine(ElectionData election){
        this.election = election;
    }

    //printBallot: print the names of candidates in the ballot
    public void printBallot() {
        System.out.println("The candidates are ");
        for (String s : this.election.getBallot()) {
            System.out.println(s);
        }
    }

    //screen: print a screen and ask the user who they want to vote for and process the information.
    public void screen() {
        this.printBallot();
        System.out.println("Who is your first vote?");
        String candidate1 = this.election.getKeyboard().next();

        System.out.println("Who is your second vote?");
        String candidate2 = this.election.getKeyboard().next();

        System.out.println("Who is your third vote?");
        String candidate3 = this.election.getKeyboard().next();

        try {
            this.election.processVote(candidate1, candidate2, candidate3);
            System.out.println("You voted for " + candidate1 + ", " + candidate2 + ", and " + candidate3 + ".");
        } catch (UnknownCandidateException e){
            e.getMessage();
            System.out.println("Would you like to add " + e.getName() + "?");
            System.out.println("If yes, enter \"y\".");
            String answer = this.election.getKeyboard().next();
            if (answer.toLowerCase().equals("y")){
                this.addWriteIn(e.getName());
            }
            this.screen();
        } catch (DuplicateVotesException e){
            e.getMessage();
            System.out.println("You cannot vote for the same candidate more than once.");
            this.screen();
        }
    }


    //addWriteIn: Calls the addCandidate() method in ElectionData.
    //            If addCandidate() throws a CandidateExistsException, tell the user that the candidate already exists.
    //            Otherwise, inform the user that the candidate was added successfully.
    public String addWriteIn(String name){
        try {
            this.election.addCandidate(name);
            return name + " was successfully added to the ballot!";
        } catch (CandidateExistsException e){
            return e.getMessage();
        }
    }


}
