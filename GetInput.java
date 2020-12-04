import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GetInput {

    public String fileName;
    private File file;

    public GetInput(String fileName) {
        this.fileName = "./src/resources/" + fileName;
        this.file = new File(this.fileName);
    }

    public ArrayList<String> asStringList() {
        ArrayList<String> returnList = new ArrayList<String>();

        try {
            Scanner fileReader = new Scanner(this.file);

            while(fileReader.hasNextLine()) {
                returnList.add(fileReader.nextLine());
            }

            fileReader.close();
        } catch(FileNotFoundException e) { e.printStackTrace(); }
        return returnList;
    }

    public ArrayList<Integer> asIntegerList() {
        ArrayList<Integer> returnList = new ArrayList<Integer>();

        try {
            Scanner fileReader = new Scanner(this.file);

            while(fileReader.hasNextLine()) {
                String cleaned = fileReader.nextLine().replace("\n", "");
                returnList.add(Integer.parseInt(cleaned));
            }

            fileReader.close();
        } catch(FileNotFoundException e) { e.printStackTrace(); }
        return returnList;
    }

}
