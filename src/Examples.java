import org.junit.Test;

import static org.junit.Assert.*;

public class Examples {


    //set up a ballot and cast votes
    ElectionData setUp1() throws CandidateExistsException {
        ElectionData ED = new ElectionData();
        //put candidates on the ballot
        try {
            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");
        } catch (CandidateExistsException e) {
            e.getMessage();
        }

        //cast votes
        try {
            ED.processVote("husky", "gompei", "ziggy");
            ED.processVote("husky", "ziggy", "gompei");
            ED.processVote("gompei", "husky", "ziggy");
        } catch (UnknownCandidateException e) {
            e.getMessage();
            String answer = ED.getKeyboard().next();
            if (answer.toLowerCase().equals("y")) {
                ED.addCandidate(e.getName());
            }
        } catch (DuplicateVotesException e) {
            e.getMessage();
        }

        return (ED);
    }

    //tests for findWinnerMostFirstVotes
    @Test
    public void testMostFirstWinner() throws CandidateExistsException {
        assertEquals("husky", setUp1().findWinnerMostFirstVotes());
    }

    //tests for findWinnerMostPoints
    @Test
    public void testMostPoints() throws CandidateExistsException {
        assertEquals("husky", setUp1().findWinnerMostPoints());
    }

    //tests for Exceptions
    @Test(expected = CandidateExistsException.class)
    public void testCandidateExistEx() throws CandidateExistsException {
        this.setUp1().addCandidate("husky");
    }

    @Test(expected = DuplicateVotesException.class)
    public void testDuplicateVote1() throws DuplicateVotesException, CandidateExistsException, UnknownCandidateException {
        this.setUp1().processVote("husky", "husky", "gompei");
    }

    @Test(expected = DuplicateVotesException.class)
    public void testDuplicateVote2() throws DuplicateVotesException, CandidateExistsException, UnknownCandidateException {
        this.setUp1().processVote("gompei", "husky", "gompei");
    }

    @Test(expected = DuplicateVotesException.class)
    public void testDuplicateVote3() throws DuplicateVotesException, CandidateExistsException, UnknownCandidateException {
        this.setUp1().processVote("husky", "gompei", "gompei");
    }

    @Test(expected = UnknownCandidateException.class)
    public void testUnknownCandidate1() throws DuplicateVotesException, CandidateExistsException, UnknownCandidateException {
        this.setUp1().processVote("charles", "gompei", "gompei");
    }

    @Test(expected = UnknownCandidateException.class)
    public void testUnknownCandidate2() throws DuplicateVotesException, CandidateExistsException, UnknownCandidateException {
        this.setUp1().processVote("husky", "gompei", "charles");
    }

    //set up another ballot and cast votes
    ElectionData setUp2() throws CandidateExistsException {
        ElectionData ED = new ElectionData();
        //put candidates on the ballot
        try {
            ED.addCandidate("husky");
            ED.addCandidate("gompei");
            ED.addCandidate("ziggy");
            ED.addCandidate("prudence");
        } catch (CandidateExistsException e) {
            e.getMessage();
        }

        //cast votes
        try {
            ED.processVote("husky", "gompei", "ziggy");
            ED.processVote("husky", "gompei", "ziggy");
            ED.processVote("gompei", "husky", "prudence");
            ED.processVote("gompei", "husky", "ziggy");
        } catch (UnknownCandidateException e) {
            e.getMessage();
            String answer = ED.getKeyboard().next();
            if (answer.toLowerCase().equals("y")) {
                ED.addCandidate(e.getName());
            }
        } catch (DuplicateVotesException e) {
            e.getMessage();
        }

        return (ED);
    }

    //tests for findWinnerMostFirstVotes
    @Test
    public void testMostFirstWinner2() throws CandidateExistsException {
        assertEquals("Runoff required", setUp2().findWinnerMostFirstVotes());
    }

    //tests for findWinnerMostPoints
    @Test
    public void testMostPoints2() throws CandidateExistsException {
        assertEquals("husky", setUp2().findWinnerMostPoints());
    }

    //set up a third ballot and cast votes
    ElectionData setUp3() throws CandidateExistsException {
        ElectionData ED = new ElectionData();
        //put candidates on the ballot
        try {
            ED.addCandidate("husky");
            ED.addCandidate("gompei");
            ED.addCandidate("ziggy");
            ED.addCandidate("prudence");
        } catch (CandidateExistsException e) {
            e.getMessage();
        }

        //cast votes
        try {
            ED.processVote("prudence", "gompei", "ziggy");
            ED.processVote("husky", "gompei", "ziggy");
            ED.processVote("ziggy", "husky", "prudence");
            ED.processVote("gompei", "husky", "ziggy");
        } catch (UnknownCandidateException e) {
            e.getMessage();
            String answer = ED.getKeyboard().next();
            if (answer.toLowerCase().equals("y")) {
                ED.addCandidate(e.getName());
            }
        } catch (DuplicateVotesException e) {
            e.getMessage();
        }

        return (ED);
    }

    //tests for findWinnerMostFirstVotes
    @Test
    public void testMostFirstWinner3() throws CandidateExistsException {
        assertEquals("Runoff required", setUp2().findWinnerMostFirstVotes());
    }

}
