package jp.co.nmatsumoto.module.toEntity;

import jp.co.nmatsumoto.framework.RecordEntity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * エンティティ生成のロジッククラス
 */
public class CreateCode {

    /**
     * 入力テーブルのエンティティクラスを生成します
     *
     * @param entities テーブル情報
     * @param path     出力パス
     * @author n_matsumoto
     */
    public static void createInputEntity(List<RecordEntity> entities, String path) throws IOException {
        String fileName = entities.get(0).getInputTable() + "InputEntity.java";
        FileWriter fw = new FileWriter(path + "\\" + fileName, false);
        PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

        //ファイル書き込み
        pw.println("package jp.co.nmatsumoto.framework.entities;");
        pw.println();
        pw.println("import lombok.Data;");
        pw.println();
        pw.println("@Data");
        pw.println("public class " + entities.get(0).getInputTable() + "InputEntity {");
        for (RecordEntity entity : entities) {
            pw.println("/**");
            pw.println("* " + entity.getInputComment());
            pw.println("*/");
            pw.println(entity.getType() + " " + entity.getInputColumn() + ";");
            pw.println("");
        }
        pw.println("}");
        pw.close();
    }

    /**
     * 出力テーブルのエンティティクラスを生成します
     *
     * @param entities テーブル情報
     * @param path     出力パス
     * @author n_matsumoto
     */
    public static void createOutputEntity(List<RecordEntity> entities, String path) throws IOException {
        String fileName = entities.get(0).getOutputTable() + "OutputEntity.java";
        FileWriter fw = new FileWriter(path + "\\" + fileName, false);
        PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

        //ファイル書き込み
        pw.println("package jp.co.nmatsumoto.framework.entities;");
        pw.println();
        pw.println("import lombok.Data;");
        pw.println();
        pw.println("@Data");
        pw.println("public class " + entities.get(0).getOutputTable() + "OutputEntity {");
        for (RecordEntity entity : entities) {
            pw.println("/**");
            pw.println("* " + entity.getOutputComment());
            pw.println("*/");
            pw.println(entity.getType() + " " + entity.getOutputColumn() + ";");
            pw.println("");
        }
        pw.println("}");
        pw.close();
    }
}
