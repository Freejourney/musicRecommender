package musicRecommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.precompute.FileSimilarItemsWriter;
import org.apache.mahout.cf.taste.impl.similarity.precompute.MultithreadedBatchItemSimilarities;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.precompute.BatchItemSimilarities;

import java.io.File;
import java.io.IOException;

public class MusicItemBasedRecommender {

    public static void main(String[] args) throws IOException, TasteException {
        File file = new File("mfr.csv");
        MusicFileDataModel model = new MusicFileDataModel(file);

//        ItemSimilarity similarity = new UncenteredCosineSimilarity(model);
        //皮尔逊相似度
        ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
        ItemBasedRecommender recommender = new GenericItemBasedRecommender(model, similarity);

        //多线程批处理
        BatchItemSimilarities batch = new MultithreadedBatchItemSimilarities(recommender, 5);
        int numSimilarities = batch.computeItemSimilarities(Runtime.getRuntime().availableProcessors(), 1, new FileSimilarItemsWriter(new File( "item_result.csv")));

        System.out.println("Computed " + numSimilarities + " similarities for " + model.getNumItems() + " items " + "and saved them to file " + "item_result.csv");
    }
}
