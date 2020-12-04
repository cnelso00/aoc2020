import java.util.ArrayList;
import java.util.HashSet;

public class Day1 {

    static int goal = 2020;
    public static HashSet<Integer> values;

    public static void main(String[] args) {

        ArrayList<Integer> input = new GetInput("day1_input.txt").asIntegerList();
        values = new HashSet<>();
        System.out.println("Test 1: " + get2020Product1(input));
        System.out.println("Test 2: " + get2020Product2(input));

    }

    public static int getSummingNumber(int num) {
        return goal - num;
    }

    public static int get2020Product1(ArrayList<Integer> input) {
        int product = -1;

        for (Integer num : input) {
            int summingNumber = getSummingNumber(num);
            if (values.contains(summingNumber)) {
                product = summingNumber * num;
            }
            else {
                values.add(num);
            }
        }

        return product;
    }

    public static int get2020Product2(ArrayList<Integer> input) {
        int product = -1;
        HashSet<Integer> subtraction_values = new HashSet<>();
        for (Integer first_num : input) {
            int difference = goal - first_num;

            for (Integer second_num : input) {
                if (second_num < difference) {
                    int summing_number = difference - second_num;
                    if (subtraction_values.contains(summing_number)) {
                        product = first_num * second_num * summing_number;
                    }
                    else { subtraction_values.add(second_num); }
                }
            }
        }

        return product;
    }
}
