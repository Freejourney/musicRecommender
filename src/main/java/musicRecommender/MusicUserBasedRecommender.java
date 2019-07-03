package musicRecommender;


import com.google.common.io.Closeables;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.iterator.FileLineIterator;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static musicRecommender.utils.m_tracksDetails;

public class MusicUserBasedRecommender {

    public static void main(String[] args) throws IOException, TasteException {
        utils util = new utils();
        util.initData("m_t.csv");
        File file = new File("mfr.csv");
        MusicFileDataModel model = new MusicFileDataModel(file);

//        ItemSimilarity similarity = new UncenteredCosineSimilarity(model);
        //皮尔逊相似度
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
        Recommender cachingRecommender = new CachingRecommender(recommender);

        for (long userID = 0; userID <= model.userID; userID++) {
            String userName = model.userIDAndNameMapping.get(userID);
            List<RecommendedItem> recommendations = cachingRecommender.recommend(userID, 5);
            System.out.print("为用户 " + userName + " 推荐音乐:");

            for (RecommendedItem recommendation : recommendations) {
                System.out.print(recommendation.getItemID() + ":" + model.itemIDAndNameMapping.get(recommendation.getItemID()) +
                        m_tracksDetails.get(m_tracksDetails.indexOf(new Track(model.itemIDAndNameMapping.get(recommendation.getItemID())))).toString() + " | ");
            }
            System.out.println();
        }
    }

}
