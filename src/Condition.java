import java.util.ArrayList;

public class Condition {
    private String condition;
    private ArrayList<String> matches;

    public Condition(String condition) {
        this.condition = condition;
        this.matches = new ArrayList<>();
    }

    public void addMatch(String match) {
        this.matches.add(match);
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public ArrayList<String> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<String> matches) {
        this.matches = matches;
    }
}
