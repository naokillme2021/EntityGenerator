package jp.co.nmatsumoto.module.toEntity;

import jp.co.nmatsumoto.framework.IGenerator;
import jp.co.nmatsumoto.framework.RecordEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static jp.co.nmatsumoto.module.toEntity.CreateCode.createInputEntity;
import static jp.co.nmatsumoto.module.toEntity.CreateCode.createOutputEntity;

/**
 * エンティティ生成クラス
 */
public class GenerateToEntity implements IGenerator {

    /**
     * エンティティ生成ツール
     *
     * @param map  テーブル定義情報
     * @param path 出力先パス
     * @author n_matsumoto
     */
    @Override
    public void generate(HashMap<String, List<RecordEntity>> map, String path) {
        for (String key : map.keySet()) {
            List<RecordEntity> entities = map.get(key);
            try {
                // エンティティクラスファイルの生成
                createInputEntity(entities, path);
                createOutputEntity(entities, path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
