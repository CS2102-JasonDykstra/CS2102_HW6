import java.util.*;


public class ElectionData {
    private LinkedList<String> ballot = new LinkedList<String>();
    private HashMap<Integer, LinkedList<String>> votes = new HashMap<Integer, LinkedList<String>>();
    private Scanner keyboard = new Scanner(System.in);

    public ElectionData() {
        this.ballot.add("Gompei");
        this.ballot.add("Husky");
    }


    public LinkedList<String> getBallot() {
        return this.ballot;
    }


    public Scanner getKeyboard() {
        return this.keyboard;
    }


    public LinkedList<String> getLLVotes(Integer key) {
        return this.votes.get(key);
    }

    //processVote: method that takes three strings (for the first, second, and third choices, respectively) and returns void.
    //             This method stores a single voter's choices in your data structure.
    //             This throws UnknownCandidateException when a candidate's name isn't in the ballot.
    //             This throws DuplciateVoteException when a candidate's name appear twice in the input.
    public void processVote(String first, String second, String third) throws UnknownCandidateException, DuplicateVotesException {
        LinkedList<String> threeVotes = new LinkedList<String>();
        threeVotes.add(first);
        threeVotes.add(second);
        threeVotes.add(third);

        for (String s : threeVotes) {
            if (!(this.ballot.contains(s))) {
                throw new UnknownCandidateException(s);
            }
        }

        if ((first.equals(second)) || (first.equals(third))) {
            throw new DuplicateVotesException(first);
        } else if (second.equals(third)) {
            throw new DuplicateVotesException(second);
        }

        Integer newVoter = this.votes.values().size() * 27;
        LinkedList<String> votes = this.votes.put(newVoter, threeVotes);
    }

    //addCandidate: takes one string (for the name of a candidate) and adds the candidate to the ballot, returning void.
    //              If the named candidate was already on the ballot,
    //              throw a CandidateExistsException whose constructor takes the name as its only argument.
    //              This throws CandidateExistsException when the cadidate is already in the ballot.
    public void addCandidate(String name) throws CandidateExistsException {
        for (String s : this.ballot) {
            if (name.equals(s)) {
                throw new CandidateExistsException(name);
            }
        }
        this.ballot.add(name);
    }

    //findWinnerMostFirstVotes: the winner is the candidate with more than 50% of first place votes.
    //                          If no candidate receives more than 50% of the votes, return the string "Runoff required".
    public String findWinnerMostFirstVotes() {
        HashMap<String, Double> count = new HashMap<String, Double>();
        for (String s : this.ballot) {
            count.put(s, 0.0);
        }

        for (LinkedList<String> value : this.votes.values()) {
            String cand = value.get(0);
            Double numVotes = count.get(cand);
            count.put(cand, numVotes++);
        }

        for (String s : this.ballot) {
            if ((count.get(s) / this.votes.values().size()) > 0.5) {
                return s;
            }
        }
        return "Runoff required";
    }

    //findWinnerMostPoints: the winner is the candidate with the most points under the following formula:
    //                      three points for each first-place vote they received,
    //                      two points for each second-place vote they received,
    //                      and one point for each third-place vote they received.
    //                      If there is a tie between two or more candidates, please return the name of any one of the winners
    public String findWinnerMostPoints() {
        HashMap<String, Integer> count = new HashMap<String, Integer>();
        for (String s : this.ballot) {
            count.put(s, 0);
        }

        for (LinkedList<String> value : this.votes.values()) {
            for (int i = 0; i <= 2; i++) {
                String cand = value.get(i);
                Integer numVotes = count.get(cand);
                int points = 0;

                if (i == 0) {
                    points = 3;
                } else
                    if (i == 1) {
                    points = 2;
                } else
                    points = 1;

                count.put(cand, numVotes + points);
            }
        }

        String winner = "";
        int winnerPoints = 0;

        for (String s : this.ballot) {
            int candPoints = count.get(s);
            if (winnerPoints < candPoints) {
                winner = s;
                winnerPoints = candPoints;
            }
        }
        return winner;

    }
}