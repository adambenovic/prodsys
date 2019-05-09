import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matcher {
    public void match(ArrayList<Rule> rules, ArrayList<String > memory) {
        ArrayList<Rule> currentRules = new ArrayList<>();
        ArrayList<Rule> currentTempRules = new ArrayList<>();

        for (Rule rule : rules) {
            currentRules.clear();
            currentRules.add(rule);
            int count = 0;
            while (count < rule.getConditions().size()) {
                currentTempRules.clear();

                for (Rule currentRule : currentRules) {
                    String condition = currentRule.getConditions().get(count);

                    if (condition.contains("<>")) {
                        if (this.checkEqual(condition)) {
                            currentTempRules.add(currentRule);
                        }
                        continue;
                    }

                    for (String fact : memory) {
                        if (this.checkVariables(condition, fact)) {
                            currentTempRules.add(this.updateRule(condition, fact, currentRule));
                        }
                    }

                }
                currentRules.clear();
                currentRules.addAll(currentTempRules);
                count++;
            }
            for (Rule currentRule : currentRules) {
                for (String action : currentRule.getActions()) {
                    if (action.split("\\s+", 2)[0].equals(Enum.REMOVE)) {
                        memory.remove(action.split("\\s+", 2)[1]);
                    }
                    else if (action.split("\\s+", 2)[0].equals(Enum.PRINT)) {
                        System.out.println(action.split("\\s+", 2)[1]);
                    }
                    else if (!memory.contains(action.split("\\s+", 2)[1])) {
                        memory.add(action.split("\\s+", 2)[1]);
                    }
                }
            }

        }
    }

    public Rule updateRule(String condition, String fact, Rule currentRule) {
        List<String> con = Arrays.asList(condition.split("\\s+"));
        List<String> facts = Arrays.asList(fact.split("\\s+"));
        String variable, name;
        Rule updatedRule = new Rule(currentRule);

        for (int i = 0; i < con.size(); i++) {
            if (con.get(i).startsWith("?")) {
                variable = "\\" + con.get(i);
                name = facts.get(i);

                for (int j = 0; j < currentRule.getConditions().size(); j++) {
                    updatedRule.getConditions().set(j, updatedRule.getConditions().get(j).replaceAll(variable, name));
                }
                for (int j = 0; j < currentRule.getActions().size(); j++) {
                    updatedRule.getActions().set(j, updatedRule.getActions().get(j).replaceAll(variable, name));
                }
            }
        }

        return updatedRule;
    }

    public boolean checkVariables(String condition, String fact) {
        List<String> conditionTokens = Arrays.asList(condition.split("\\s+"));
        List<String> memoryTokens = Arrays.asList(fact.split("\\s+"));

        for (int i = 0; i < conditionTokens.size(); i++) {
            if (!conditionTokens.get(i).equals(memoryTokens.get(i)) && !conditionTokens.get(i).startsWith("?"))
                return false;
        }

        return true;
    }

    public boolean checkEqual(String condition) {
        String[] names = condition.split("\\s+");

        if (names[1].equals(names[2])) {
            return false;
        }

        return true;
    }
}
