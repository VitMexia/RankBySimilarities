package Series;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class MyFile {

    private Map<String, Integer> termsAndOcurrences;

    private double fileDimension;

    public MyFile(File file, StopWords stopWords)throws FileNotFoundException {

        Scanner sc = new Scanner(new FileReader(file));

        termsAndOcurrences = new HashMap<>();

        while(sc.hasNext()){
            String temp = sc.next();

            temp.replaceAll("[.,:?!;(){}]","");

            if(!stopWords.isStopWord(temp))
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
}
