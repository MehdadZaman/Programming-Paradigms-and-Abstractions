import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public  class WordCounter {

    public static HashMap<String, HashMap<String, Integer>> mainHashMap = new HashMap<>();

    // The following are the ONLY variables we will modify for grading.
    // The rest of your code must run with no changes.
    public static final Path FOLDER_OF_TEXT_FILES  = Paths.get("txtFiles");  // path to the folder where input text files are located
    public static final Path WORD_COUNT_TABLE_FILE = Paths.get("file-table"); // path to the output plain-text (.txt) file
    public static final int  NUMBER_OF_THREADS     = 1;                // max. number of threads to spawn

    public static void main(String... args) {
        //your implementation of how to run the WordCounter as a stand-alone multi-threaded program
        long start = System.currentTimeMillis();
        listFilesForFolder();
        long end = System.currentTimeMillis();
        makeFile();
        System.out.println(end - start + " ms");
    }
    public static void listFilesForFolder()
    {
        ArrayList<Thread> allThreads = new ArrayList<>();
        ArrayList<WordCounterThread> allRunnables = new ArrayList<>();
        File[] directoryOfFiles = FOLDER_OF_TEXT_FILES.toFile().listFiles();
        int numOfThreads = NUMBER_OF_THREADS;
        if(numOfThreads <= 0)
        {
            for(int i = 0; i < directoryOfFiles.length; i++)
            {
                parseFile(directoryOfFiles[i].getName());
            }
        }
        else
        {
            int numOfRunnables = 0;
            while((numOfRunnables < directoryOfFiles.length) && (numOfRunnables < numOfThreads))
            {
                WordCounterThread tempRunnable = new WordCounterThread();
                allRunnables.add(tempRunnable);
                numOfRunnables++;
            }

            for(int i = 0; i < directoryOfFiles.length; i++)
            {
                allRunnables.get(i % numOfRunnables).files.add(directoryOfFiles[i]);
            }
            for(int i = 0; i < allRunnables.size(); i++)
            {
                Thread tempThread = new Thread(allRunnables.get(i));
                allThreads.add(tempThread);
                tempThread.start();
            }
        }
        for(int j = 0; j < allThreads.size(); j++)
        {
            try {
                allThreads.get(j).join();
            }
            catch (InterruptedException ie) {}
        }
    }
    public static void parseFile(String filename)
    {
        ArrayList<String> txtStrings = new ArrayList<>();
        HashMap<String, Integer> wordsAndCount = new HashMap<>();
        try
        {
            File file = new File(FOLDER_OF_TEXT_FILES + "//" + filename);
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNextLine())
            {
                String[] wordsArray = fileReader.nextLine().split("[^a-zA-Z]");
                for(int i = 0; i < wordsArray.length; i++)
                {
                    if((wordsArray[i].toLowerCase() != "") && (wordsArray[i].length() != 0)) {
                        txtStrings.add(wordsArray[i].toLowerCase());
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        for(int i = 0; i < txtStrings.size(); i++)
        {
            if(wordsAndCount.containsKey(txtStrings.get(i)))
            {
                wordsAndCount.replace(txtStrings.get(i), wordsAndCount.get(txtStrings.get(i)), wordsAndCount.get(txtStrings.get(i)) + 1);
            }
            else
            {
                wordsAndCount.put(txtStrings.get(i), 1);
            }
        }
        WordCounter.mainHashMap.put(filename, wordsAndCount);
    }
    public static void makeFile() {
        ArrayList<String> correspondingFileNames = new ArrayList<>();
        ArrayList<HashMap<String, Integer>> correspondingHashTable = new ArrayList<>();
        HashSet<String> allWords = new HashSet<>();
        for (Map.Entry entry : mainHashMap.entrySet()) {
            correspondingFileNames.add((String)(entry.getKey()));
            correspondingHashTable.add((HashMap<String, Integer>)(entry.getValue()));
            HashMap<String, Integer> tempFileHash = mainHashMap.get(entry.getKey());

            for (Map.Entry entryFile : tempFileHash.entrySet()) {
                allWords.add((String)(entryFile.getKey()));
            }
        }
        ArrayList<String> wordArrayList = new ArrayList<>();
        for(String word : allWords){
            wordArrayList.add(word);
        }
        Collections.sort(wordArrayList);
        int[][] correspondingWordCounts = new int[wordArrayList.size()][correspondingFileNames.size() + 1];
        for(int i = 0; i < correspondingHashTable.size(); i++)
        {
            for(int j = 0; j < wordArrayList.size(); j++)
            {
                if(correspondingHashTable.get(i).containsKey(wordArrayList.get(j))) {
                    correspondingWordCounts[j][i] = correspondingHashTable.get(i).get(wordArrayList.get(j));
                }
                else
                {
                    correspondingWordCounts[j][i] = 0;
                }
            }
        }
        for(int i = 0; i < wordArrayList.size(); i++)
        {
            int columnSum = 0;
            for(int j = 0; j < correspondingHashTable.size() + 1; j++)
            {
                if(j != correspondingHashTable.size()) {
                    columnSum += correspondingWordCounts[i][j];
                }
                else
                {
                    correspondingWordCounts[i][j] = columnSum;
                }
            }
        }
        int longestWordLength = 0;
        for(int i = 0; i < wordArrayList.size(); i++)
        {
            if(wordArrayList.get(i).length() > longestWordLength)
            {
                longestWordLength = wordArrayList.get(i).length();
            }
        }
        longestWordLength++;
        int longestFileNameLength = 0;
        for(int i = 0; i < correspondingFileNames.size(); i++)
        {
            if(correspondingFileNames.get(i).length() > longestFileNameLength)
            {
                longestFileNameLength = correspondingFileNames.get(i).length();
            }
        }
        longestFileNameLength += 4;
        try
        {
            FileWriter fileWriter = new FileWriter(WORD_COUNT_TABLE_FILE + "//word_count_table.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.printf("%-" + longestWordLength + "s", " ");
            for(int i = 0; i < correspondingFileNames.size(); i++)
            {
                printWriter.printf("%-" + longestFileNameLength + "s",  correspondingFileNames.get(i));
            }
            printWriter.printf("%-" + longestFileNameLength + "s",  "total");
            printWriter.println();
            for(int i = 0; i < wordArrayList.size(); i++)
            {
                printWriter.printf("%-" + longestWordLength + "s",  wordArrayList.get(i));

                for(int j = 0; j < correspondingHashTable.size() + 1; j++)
                {
                    printWriter.printf("%-" + longestFileNameLength + "d",  correspondingWordCounts[i][j]);
                }
                printWriter.println();
            }
            printWriter.close();
            fileWriter.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}