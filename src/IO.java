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
                    newRule.setConditions(this.splitConditions(line.replace(Enum.IF, "")));

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
        String line = "empty";

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

    private ArrayList<Condition> splitConditions(String line) {
        ArrayList<String> textConditions = splitLine(line);
        ArrayList<Condition> conditions = new ArrayList<>();

        for (String str : textConditions) {
            conditions.add(new Condition(str));
        }

        return conditions;
    }

    private ArrayList<String> splitLine(String line) {
        String[] data;
        Pattern pattern = Pattern.compile(",");
        data = pattern.split(line);

        return new ArrayList<>(Arrays.asList(data));
    }

//    public void writeToFile(ArrayList<Solution> solutions, String filename) {
//        if(solutions.isEmpty()) {
//            System.out.println("No solutions found.");
//            System.exit(Enum.EXIT_SOLUTION_NONE);
//        }
//
//        BufferedWriter writer = null;
//
//        try {
//            writer = new BufferedWriter(new FileWriter(filename));
//        }
//        catch (IOException e)
//        {
//            System.out.println( "Unable to open " + filename + " for writing.");
//            System.exit(Enum.EXIT_WRITE_EXCEPTION);
//        }
//
//        int i = 1;
//        for (Solution solution: solutions) {
//            try {
//                writer.write("Solution no." + i +":\n");
//                ArrayList<String> lines = mapToLines(solution.getMap(), solution.getHeight(), solution.getWidth());
//                for (String line: lines)
//                    writer.write(line + '\n');
//
//                writer.write("Run time = " + solution.getRunTime() + "ms\n\n");
//            } catch (IOException e)
//            {
//                System.out.println( "Unable to write to " + filename + ".");
//                System.exit(Enum.EXIT_WRITE_EXCEPTION);
//            }
//        }
//
//        try {
//            writer.close();
//        } catch (IOException e) {
//            System.out.println( "Unable to close " + filename + ".");
//            System.exit(Enum.EXIT_FILE_CLOSE);
//        }
//    }
}
