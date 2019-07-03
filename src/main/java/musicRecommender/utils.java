package musicRecommender;

import com.google.common.io.Closeables;
import org.apache.mahout.common.iterator.FileLineIterator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class utils {

    public static List<Track> m_tracksDetails = new ArrayList<>();
    private static Map<String, Long> m_trackidNum2trackidStringMapping = new HashMap<>();
    private double base_score = 1;
    private double n = 0.3;


    public void initData(String filename) throws IOException {
        File file = new File(filename);
        FileLineIterator iterator = new FileLineIterator(file, false);
        String line = iterator.next();
        Long i = Long.valueOf(1);
        while (!line.isEmpty()) {
            String[] m = line.split(",");
            Track track = new Track(m[0], m[1], m[2], Integer.valueOf(m[3]));
            m_trackidNum2trackidStringMapping.put(m[0], i);
            System.out.println(i+++track.toString());
            m_tracksDetails.add(track);
            if (iterator.hasNext()) {
                line = iterator.next();
            } else {
                break;
            }
        }
        Closeables.close(iterator, true);
    }

    public void generatemffile(String filename) throws IOException {
        File file = new File(filename);
        FileLineIterator iterator = new FileLineIterator(file, false);
        String line = iterator.next();
        line = iterator.next();
        String str = "";
        long i = 0;
        while (!line.isEmpty()) {
            String[] m = line.split(",");
            int score = calcuScore(m[0]);
//            str += m[1]+','+m_trackidNum2trackidStringMapping.get(m[0])+','+score+"\n";
            str += m[1] + ',' + m[0] + ',' + score + "\n";
            if (iterator.hasNext()) {
                line = iterator.next();
            } else {
                break;
            }
            i++;
            if (i%10000==0) {
                System.out.println(i);
                FileOutputStream fos = new FileOutputStream("mfr.csv", true);//保存文件
                fos.write(str.getBytes());//写入文件
                str = "";
                fos.close();
            }
        }
        Closeables.close(iterator, true);

        FileOutputStream fos = new FileOutputStream("mfr.csv", true);//保存文件
        fos.write(str.getBytes());//写入文件
        fos.close();
    }

    public int calcuScore(String trackid) {
        int index = m_tracksDetails.indexOf(new Track(trackid));
        if (index == -1) {
            index = 0;
        }
        return (int)(base_score + n * m_tracksDetails.get(index).getFavoriates());
    }

    public static void main(String[] args) throws IOException {
        utils util = new utils();
        util.initData("m_t.csv");
//        util.generatemffile("m_f.csv");
        util.generatemffile("meta_favorites.csv");
    }

}
