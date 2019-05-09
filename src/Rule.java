import java.util.ArrayList;

public class Rule {
    private String name;
    private ArrayList<String> conditions;
    private ArrayList<String> actions;

    public Rule(String name) {
        this.name = name;
    }

    public Rule(Rule rule) {
        this.name = rule.name;
        this.conditions = new ArrayList<>(rule.getConditions());
        this.actions = new ArrayList<>(rule.getActions());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<String> conditions) {
        this.conditions = conditions;
    }

    public ArrayList<String> getActions() {
        return actions;
    }

    public void setActions(ArrayList<String> actions) {
        this.actions = actions;
    }
}
