import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        IO io = new IO();
        Matcher matcher = new Matcher();

        ArrayList<Rule> rules = io.loadRules(args[0]);
        ArrayList<String> memory = io.loadMemory(args[1]);

        printRules(rules, memory);
        for (Rule rule : rules) {
            matcher.match(rule, memory);
        }

    }

    private static void printRules(ArrayList<Rule> rules, ArrayList<String> memory) {
        for (Rule rule : rules) {
            System.out.println(rule.getName());
            for (Condition str : rule.getConditions()) {
                System.out.println(str.getCondition());
            }
            for (String str : rule.getActions()) {
                System.out.println(str);
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }

        for (String str : memory) {
            System.out.println(str);
        }
    }
}
