package Series;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StopWords {

    private List<String> stopWords;
    private int biggestStopWordSize;

    public StopWords(File stopWordsFile) throws FileNotFoundException {

        Scanner sc = new Scanner(new FileReader(stopWordsFile));

        stopWords = new ArrayList<>();

        while(sc.hasNext()){
            String temp = sc.next();

            stopWords.add(temp);

            if(temp.length() > biggestStopWordSize)
                biggestStopWordSize = temp.length();
        }

    }

    public boolean isStopWord(String word){

        if(word.length() > biggestStopWordSize)
            return false;
        else{

            for (String s: stopWords) {
                if(s.equals(word))
                    return true;
            }
            return false;
        }

    }
}
