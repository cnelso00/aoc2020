import java.util.ArrayList;

public class AdventClass {

        public ArrayList<String> values;

        public AdventClass() { this.values = null; }

        public AdventClass(String filename) { this.values = new GetInput(filename).asStringList(); }

}
