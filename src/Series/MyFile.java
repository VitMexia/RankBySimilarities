package Series;


import javafx.scene.paint.Stop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class MyFile {

    private Map<String, Integer> termsAndOcurrences;
    public final String fileName;

    private double fileDimension;

    public MyFile(File file, StopWords stopWords)throws FileNotFoundException {

        Scanner sc = new Scanner(new FileReader(file));

        fileName = file.getName();

        termsAndOcurrences = new HashMap<>();

        while(sc.hasNext()){
            String temp = sc.next();

            temp.replaceAll("[.,:?!;(){}]","");

            if(stopWords == null || !stopWords.isStopWord(temp))
                addWord(temp);
        }

        setFileDimension();
    }

    private void setFileDimension() {

        Collection<Integer> temp = termsAndOcurrences.values();

        for (Integer i: temp) {
            fileDimension += i*i;
        }

        fileDimension = Math.sqrt(fileDimension);

    }

    public double getFileDimension(){
        return fileDimension;
    }

    private void addWord(String newWord){

        if(termsAndOcurrences.containsKey(newWord))
            termsAndOcurrences.put(newWord, termsAndOcurrences.get(newWord) + 1);
        else
            termsAndOcurrences.put(newWord, 1);

    }

    public Map<String, Integer> getTermsAndOcurrences(){
        return termsAndOcurrences;
    }

    public int getWordOccurrences(String word){

        Integer i = termsAndOcurrences.get(word);
        return i == null ? 0 : i;

    }
}
