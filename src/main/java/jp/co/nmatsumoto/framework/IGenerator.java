package jp.co.nmatsumoto.framework;

import java.util.HashMap;
import java.util.List;

/**
 * ソース生成クラスが継承するSPI
 */
public interface IGenerator {
    void generate(HashMap<String, List<RecordEntity>> map, String path);
}
