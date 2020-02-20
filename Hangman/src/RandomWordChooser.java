import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
public class RandomWordChooser {
    public static String chooseWord(String fileName){
        ArrayList<String> words = new ArrayList<>();
        try
        {
            URL path = RandomWordChooser.class.getResource(fileName);
            File foo = new File(path.getFile());
            Scanner read = new Scanner(foo);
            while(read.hasNextLine())
            {
                words.add(read.nextLine());
            }
        }
        catch (Exception e){}
        int rand = (int)(Math.random() * words.size());
        return words.get(rand);
    }
}
