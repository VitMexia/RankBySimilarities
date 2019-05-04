package Series;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyQuery {

    private MyFile queryFile;
    private int weight;
    private List<RankPair> ranking;

    public MyQuery(File qFile) throws FileNotFoundException {

        queryFile = new MyFile(qFile, null);
        ranking = new ArrayList<>();
    }

    public void setSimilarity(MyFile file){

        double wordocurrences = 0;

        for (String s:  queryFile.getTermsAndOcurrences().keySet()){

            wordocurrences += file.getWordOccurrences(s);
        }

        if(queryFile.getFileDimension() == 0 || file.getFileDimension() == 0)
            ranking.add(new RankPair(file.fileName, 0));
        else {

            double similarity =  wordocurrences / (queryFile.getFileDimension() * file.getFileDimension());

            if(similarity > ranking.get(0).similarity){
                ranking.add(new RankPair(file.fileName, similarity));
            }
            else{

                insertSorted(new RankPair(file.fileName,similarity), 0, ranking.size()-1);
            }
        }
    }

    private void insertSorted(RankPair rankPair, int left, int right) {

        int mid = left + (right - left)/2;
        if(rankPair.similarity > ranking.get(mid).similarity && rankPair.similarity <= ranking.get(mid-1).similarity)
            ranking.add(mid, rankPair);
        else if(rankPair.similarity > ranking.get(mid).similarity)
            insertSorted(rankPair, left, mid);
        else
            insertSorted(rankPair,mid, right);

    }

    public String getSimilarity(){

        String similaritiesToPrint = "";

        for (RankPair rp : ranking ) {

            similaritiesToPrint += rp.fileName + ", " + rp.similarity + ";\n";
        }

        return similaritiesToPrint;
    }

}
