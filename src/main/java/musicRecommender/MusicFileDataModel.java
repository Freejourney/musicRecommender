package musicRecommender;

import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MusicFileDataModel extends FileDataModel {

    public static Map<String,Long> userNameAndIDMapping = new HashMap<>();
    public static Map<Long,String> userIDAndNameMapping = new HashMap<>();
    public static long userID = 0;

    public static Map<String,Long> itemNameAndIDMapping = new HashMap<>();
    public static Map<Long,String> itemIDAndNameMapping = new HashMap<>();
    public static long itemID = 0;

    public MusicFileDataModel(File dataFile) throws IOException {
        super(dataFile);
    }

    public MusicFileDataModel(File dataFile, String delimiterRegex) throws IOException {
        super(dataFile, delimiterRegex);
    }

    public MusicFileDataModel(File dataFile, boolean transpose, long minReloadIntervalMS) throws IOException {
        super(dataFile, transpose, minReloadIntervalMS);
    }

    public MusicFileDataModel(File dataFile, boolean transpose, long minReloadIntervalMS, String delimiterRegex) throws IOException {
        super(dataFile, transpose, minReloadIntervalMS, delimiterRegex);
    }


    @Override
    protected long readUserIDFromString(String value) {
        value = value.trim();
        if (userNameAndIDMapping.containsKey(value)) {
            return userNameAndIDMapping.get(value);
        }

        userNameAndIDMapping.put(value, userID);
        userIDAndNameMapping.put(userID, value);
//        System.out.println("userID: "+userID+"value: "+value);
        userID++;
        return (userID -1);
    }

    @Override
    protected long readItemIDFromString(String value) {
        value = value.trim();
        if (itemNameAndIDMapping.containsKey(value)) {
            return itemNameAndIDMapping.get(value);
        }

        itemNameAndIDMapping.put(value, itemID);
        itemIDAndNameMapping.put(itemID, value);
        itemID++;
        return (itemID-1);
    }
}
