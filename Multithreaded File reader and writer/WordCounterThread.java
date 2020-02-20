import java.io.File;
import java.util.ArrayList;

public class WordCounterThread implements Runnable{

    public ArrayList<File> files = new ArrayList<>();

    public WordCounterThread(){}

    @Override
    public void run() {
        for(int i = 0; i < files.size(); i++)
        {
            WordCounter.parseFile(files.get(i).getName());
        }
    }

}
