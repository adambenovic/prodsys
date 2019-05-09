import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        IO io = new IO();
        Matcher matcher = new Matcher();

        ArrayList<Rule> rules = io.loadRules(args[0]);
        ArrayList<String> memory = io.loadMemory(args[1]);

        System.out.println("Messages:");

        matcher.match(rules, memory);

        System.out.println();
        System.out.println("Memory:");
        for (String mem:memory) {
            System.out.println(mem);
        }
    }

}
