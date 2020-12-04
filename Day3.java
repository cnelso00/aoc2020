import java.lang.reflect.Array;
import java.util.ArrayList;

public class Day3 extends AdventClass {

    int[] x_mov;
    int[] y_mov;

    public Day3(String filename, int[] x_mov, int[] y_mov) {
        super(filename);
        this.x_mov = x_mov;
        this.y_mov = y_mov;
    }

    public String getResults() {
        int[] one_x = {1};
        int[] three_y = {3};

        /**
        * I wanted line 24's function call to look like:
        * getTreesEncountered(this.values, {1}, {3})
        */
        return "Day3: " + "\n" +
                "R1: " + getTreesEncountered(this.values, one_x, three_y) + "\n" +
                "R2: " + getTreesEncountered(this.values, this.x_mov, this.y_mov) + "\n";
    }

    private double getTreesEncountered(ArrayList<String> map, int x_move[], int y_mov[]) {
        double product = 1;
        for (int i = 0; i < x_move.length; i++) {
            Crawler crawler = new Crawler(map, x_move[i], y_mov[i]);
            product = product * crawler.TreesEncountered();
        }
        return product;
    }
    private class Crawler {
        int x_loc;
        int y_loc;
        int x_mov;
        int y_mov;
        ArrayList<String> map;

        final Character TREE = '#';

        private Crawler() {
            this.x_loc = 0;
            this.y_loc = 0;
            this.map = null;
        }

        private Crawler(ArrayList<String> map, int x_mov, int y_mov) {
            this();
            this.map = map;
            this.x_mov = x_mov;
            this.y_mov = y_mov;
        }

        private int Crawl(int x_loc, int y_loc) {

            if (x_loc >= map.size()) { return 0; }
            String row_value = map.get(x_loc);

            if (y_loc >= row_value.length()) { y_loc = y_loc % (row_value.length()); }

            int treeExists = 0;
            if(TREE.equals(row_value.charAt(y_loc))) {
                treeExists = 1;
            }

            return Crawl(x_loc+x_mov, y_loc+y_mov) + treeExists;
        }

        private int TreesEncountered() {
            return Crawl(this.x_loc, this.y_loc);
        }



    }
}
