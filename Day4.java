import java.util.*;

public class Day4 extends AdventClass {

    public Day4(String filename) {
        super(filename);
    }

    public int ProcessCredentials() {

        int count = 0;

        ArrayList<String> currentEntry = new ArrayList<>(0);

        for(String str: this.values) {
            if (!str.equalsIgnoreCase("")) {
                currentEntry.add(str);
            }
            else {
                if (new Credential(currentEntry).isValidCredential()) { count++; }
                currentEntry = new ArrayList<String>(0); }
        }

        if (!currentEntry.isEmpty()) {
            if (new Credential(currentEntry).isValidCredential()) { count++; }
        }

        return count;
    }

    public String getResults() {
        return "Day4: " + "\n" +
                "R1: " + ProcessCredentials() + "\n" +
                "R2: " + "" + "\n";
    }

    private class Credential {

        ArrayList<String> batchFile;
        HashMap<String, String> fields;
        final HashSet<String> required = new HashSet<>(Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"));

        private Credential(ArrayList<String> batchFile) {
            this.fields = new HashMap<>();
            this.batchFile = batchFile;
            Process();
        }

        private void Process() {

            for(String line: this.batchFile) {

                line = line.replace("\n", "");
                String[] pairs = line.split(" ");

                for (int i = 0; i < pairs.length; i++) {

                    String[] key_and_value = pairs[i].split(":");
                    if(key_and_value.length > 1) {
                        this.fields.put(key_and_value[0], key_and_value[1]);
                    }
                    else {
                        this.fields.put(key_and_value[0], "");
                    }
                }
            }
        }

        private boolean isValidCredential() {
            return this.fields.keySet().containsAll(this.required);
        }

        private boolean isValidBirthYear(int byr) {
            return (byr >= 1920) && (byr <= 2002);
        }

        private boolean isValidIssueYear(int iyr) {
            return (iyr >= 2010) && (iyr <= 2020);
        }

        private boolean isValidExpirationYear(int eyr) {
            return (eyr >= 2020) && (eyr <= 2030);
        }

        private boolean isValidHeight(String hgt) {
            if (!hgt.endsWith("cm") && !hgt.endsWith("in")) {
                return false;
            }
            String numStr = hgt.substring(0, hgt.length()-3);
            double num = Integer.parseInt(numStr);
            
        }




    }
}
