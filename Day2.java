import java.util.ArrayList;

public class Day2 {

    private ArrayList<String> values;

    public Day2(String filename) {
        this.values = new GetInput(filename).asStringList();
    }

    private int getValidEntryCount(ArrayList<String> values) {
        int count = 0;

        for (String s: values) {
            Entry entry = new Entry(s);
            if (entry.isValidByCount()) { count++; }
        }

        return count;
    }

    private int getValidEntryByIndex(ArrayList<String> values)  {
        int count = 0;

        for (String s: values) {
            Entry entry = new Entry(s);
            if (entry.isValidByIndex()) { count++; }
        }

        return count;
    }

    public String getDay2Results() {
        return "Day2: " + "\n" +
                "R1: " + getValidEntryCount(this.values) + "\n" +
                "R2: " + getValidEntryByIndex(this.values ) + "\n";
    }

    private class Entry {

        int lowest_count;
        int highest_count;
        Character letter;
        String password;

        private Entry(String entry) {
            String[] list = entry.split(":");
            String[] protocol = list[0].split(" ");
            String[] counts = protocol[0].split("-");
            this.lowest_count = Integer.parseInt(counts[0]);
            this.highest_count = Integer.parseInt(counts[1]);
            this.letter = protocol[1].charAt(0);

            this.password = list[1];

        }

        private boolean isValidByCount() {
            int count = 0;

            for (Character c : this.password.toCharArray()) {
                if (c.equals(letter)) {
                    count++;
                }
            }

            return (lowest_count <= count && count <= highest_count);
        }

        private boolean isValidByIndex() {
            boolean first_index_value = this.letter.equals(password.charAt(lowest_count));
            boolean second_index_value = this.letter.equals(password.charAt(highest_count));
            return first_index_value ^ second_index_value;
        }

    }

}
