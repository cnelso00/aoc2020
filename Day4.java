import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public int ProcessCredentialsComplete() {

        int count = 0;

        ArrayList<String> currentEntry = new ArrayList<>(0);

        for(String str: this.values) {
            if (!str.equalsIgnoreCase("")) {
                currentEntry.add(str);
            }
            else {
                if (new Credential(currentEntry).isValidCredentialComplete()) { count++; }
                currentEntry = new ArrayList<String>(0); }
        }

        if (!currentEntry.isEmpty()) {
            if (new Credential(currentEntry).isValidCredentialComplete()) { count++; }
        }

        return count;
    }

    public String getResults() {
        return "Day4: " + "\n" +
                "R1: " + ProcessCredentials() + "\n" +
                "R2: " + ProcessCredentialsComplete() + "\n";
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

        private boolean isValidCredentialComplete() {
            boolean isValid = this.fields.keySet().containsAll(this.required);

            if (isValid == false) { return isValid; }

            isValid = isValid && isValidBirthYear(fields.get("byr"));
            isValid = isValid && isValidIssueYear(fields.get("iyr"));
            isValid = isValid && isValidExpirationYear(fields.get("eyr"));
            isValid = isValid && isValidHeight(fields.get("hgt"));
            isValid = isValid && isValidHairColor(fields.get("hcl"));
            isValid = isValid && isValidEyeColor(fields.get("ecl"));
            isValid = isValid && isValidPID(fields.get("pid"));

            return isValid;

        }

        private boolean isValidBirthYear(String byr_str) {
            int byr = Integer.parseInt(byr_str);
            return (byr >= 1920) && (byr <= 2002);
        }

        private boolean isValidIssueYear(String iyr_str) {
            int iyr = Integer.parseInt(iyr_str);
            return (iyr >= 2010) && (iyr <= 2020);
        }

        private boolean isValidExpirationYear(String eyr_str) {
            int eyr = Integer.parseInt(eyr_str);
            return (eyr >= 2020) && (eyr <= 2030);
        }

        private boolean isValidHeight(String hgt) {

            boolean isValid = false;

            if (!hgt.endsWith("cm") && !hgt.endsWith("in")) {
                 return isValid;
            }
            String numStr = hgt.substring(0, hgt.length()-2);
            double num = Integer.parseInt(numStr);

            if (hgt.endsWith("cm") && 150 <= num && num <= 193) { isValid = true; }

            if (hgt.endsWith("in") && 59 <= num && num <= 76) { isValid = true; }

            return isValid;
        }

        private boolean isValidHairColor(String color) {

            Matcher matcher = Pattern.compile("^#[0-9a-f]{6}$").matcher(color);

            return matcher.matches();
        }

        private boolean isValidEyeColor(String color) {

            Matcher matcher = Pattern.compile("^(amb|blu|brn|gry|grn|hzl|oth)$").matcher(color);

            return matcher.matches();
        }

        private boolean isValidPID(String num) {

            Matcher matcher = Pattern.compile("^[0-9]{9}$").matcher(num);

            return matcher.matches();
        }




    }
}
