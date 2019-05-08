import java.util.ArrayList;

public class Matcher {
    public void match(Rule rule, ArrayList<String > memory) {
        int type = 1;
        for (Condition condition : rule.getConditions()) {
            type = getType(condition, memory);
            if (type == Enum.NONE)
                break;
        }

        if (type > 0) {

        }
    }

    private int getType(Condition condition, ArrayList<String> memory) {
        if (condition.getCondition().contains("<>"))
            return Enum.SPECIAL;
        if (condition.getCondition().contains("?"))
            return matchVariables(condition, memory);

        for (String mem : memory) {
            if (mem.equals(condition.getCondition()))
                return Enum.EXACT;
        }

        return Enum.NONE;
    }

    private int matchVariables(Condition condition, ArrayList<String> memory) {
        for (String mem : memory) {
            String[] splitCondition = condition.getCondition().split(" ");
            String[] splitMemory = mem.split(" ");
            if (splitCondition.length != splitMemory.length)
                return Enum.NONE;

            for (int i = 0; i < splitCondition.length; i++) {
                if (!splitCondition[i].equals(splitMemory[i])) {
                    if (splitCondition[i].startsWith("?")) {
                        condition.addMatch(splitCondition[i] + "=" + splitMemory[i]);
                    } else
                        return Enum.NONE;
                }
            }
        }

        return Enum.VARIABLE;
    }
}
