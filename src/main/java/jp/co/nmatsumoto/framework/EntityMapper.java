package jp.co.nmatsumoto.framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EntityMapper {
    /**
     * csvの内容をEntityに突っ込んで返す
     *
     * @param list 読み込んだcsvファイルのリスト
     * @return entities 行ごとのエンティティを持つリスト
     * @author n_matsumoto
     */
    public static List<RecordEntity> map(List<String[]> list) {
        List<RecordEntity> entities = new ArrayList<>();
        for (String[] record : list) {
            if (record.length >= 11) {
                RecordEntity entity = new RecordEntity();
                entity.setInputDB(checkNull(record[0]));
                entity.setInputSchema(checkNull(record[1]));
                entity.setInputTable(checkNull(record[2]));
                entity.setOutputDB(checkNull(record[3]));
                entity.setOutputSchema(checkNull(record[4]));
                entity.setOutputTable(checkNull(record[5]));
                entity.setInputColumn(checkNull(record[6]));
                entity.setInputComment(checkNull(record[7]));
                entity.setOutputColumn(checkNull(record[8]));
                entity.setOutputComment(checkNull(record[9]));
                entity.setType(checkNull(record[10]));
                entities.add(entity);
            }
        }
        return entities;
    }

    /**
     * csvの内容をグルーピングしてリストで返す
     *
     * @param entities 読み込んだcsvファイルのリスト
     * @return null テーブルごとにグルーピングしたHashMapリスト
     * @author n_matsumoto
     */
    public static HashMap<String, List<RecordEntity>> convert(List<RecordEntity> entities) {
        HashMap<String, List<RecordEntity>> tableMap = new HashMap<>();
        String target = null;
        for (RecordEntity entity : entities) {
            if (entity.getInputDB() == null || entity.getInputDB().length() != 0) {
                target = entity.getInputTable();
                tableMap.put(target, new ArrayList<>(Arrays.asList(entity)));
            } else {
                tableMap.get(target).add(entity);
            }
        }
        return tableMap;
    }

    /**
     * nullだったらnullを、nullじゃなかったら値を返す
     *
     * @param param 文字列
     * @return 文字列
     * @author n_matsumoto
     */
    private static String checkNull(String param) {
        if (param != null) {
            return param;
        } else {
            return null;
        }
    }
}
