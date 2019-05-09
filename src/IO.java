import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

class IO {
    public ArrayList<Rule> loadRules(String filename) {
        ArrayList<Rule> rules = new ArrayList<>();
        String line = "empty";
        Rule newRule;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            try {
                while(line != null)
                {
                    line = reader.readLine();
                    newRule = new Rule(line.replace(Enum.NAME, ""));

                    line = reader.readLine();
                    newRule.setConditions(this.splitLine(line.replace(Enum.IF, "")));

                   line = reader.readLine();
                   newRule.setActions(this.splitLine(line.replace(Enum.THEN, "")));

                   rules.add(newRule);

                    line = reader.readLine();
                }
            }
            catch (IOException e)
            {
                System.out.println( "Unable to read " + filename + ".");
                System.exit(11);
            }
        }
        catch ( FileNotFoundException e)
        {
            System.out.println( "File " + filename + " not found." );
            System.exit(11);
        }


        return rules;
    }

    public ArrayList<String> loadMemory(String filename) {
        ArrayList<String> memory = new ArrayList<>();
        String line;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            try {
                line = reader.readLine();

                while(line != null)
                {
                    memory.add(line);
                    line = reader.readLine();

                }
            }
            catch (IOException e)
            {
                System.out.println( "Unable to read " + filename + ".");
                System.exit(11);
            }
        }
        catch ( FileNotFoundException e)
        {
            System.out.println( "File " + filename + " not found." );
            System.exit(11);
        }


        return memory;
    }

    private ArrayList<String> splitLine(String line) {
        String[] data;
        Pattern pattern = Pattern.compile("\\s*,\\s*");
        data = pattern.split(line);

        return new ArrayList<>(Arrays.asList(data));
    }
}
