package jp.co.nmatsumoto.framework;

import lombok.Data;

@Data
public class RecordEntity {

    /**
     * 入力DB
     */
    private String inputDB;

    /**
     * 入力スキーマ
     */
    private String inputSchema;

    /**
     * 入力テーブル
     */
    private String inputTable;

    /**
     * 出力DB
     */
    private String outputDB;

    /**
     * 出力スキーマ
     */
    private String outputSchema;

    /**
     * 出力テーブル
     */
    private String outputTable;

    /**
     * 入力カラム
     */
    private String inputColumn;

    /**
     * 入力カラムコメント
     */
    private String inputComment;

    /**
     * 出力カラム
     */
    private String outputColumn;

    /**
     * 出力カラムコメント
     */
    private String outputComment;

    /**
     * 型
     */
    private String type;
}
