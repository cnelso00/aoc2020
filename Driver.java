
public class Driver {

    public static void main(String[] args) {

        Day4 day4 = new Day4("day4_input.txt");
        System.out.println(day4.getResults());

        /**
         * Day 3 Move Values for each slope
         */
        int[] day3_x = {1,1,1,1,2};
        int[] day3_y = {1,3,5,7,1};
        Day3 day3 = new Day3("day3_input.txt", day3_x, day3_y);
        System.out.println(day3.getResults());

        Day2 day2 = new Day2("day2_input.txt");
        System.out.println(day2.getDay2Results());

    }
}
