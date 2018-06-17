package jp.co.nmatsumoto.main;

import com.orangesignal.csv.Csv;
import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.handlers.StringArrayListHandler;
import jp.co.nmatsumoto.framework.IGenerator;
import jp.co.nmatsumoto.framework.RecordEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static jp.co.nmatsumoto.framework.EntityMapper.convert;
import static jp.co.nmatsumoto.framework.EntityMapper.map;

/**
 * クラス生成ツールのメインクラス
 */
public class generatorMain extends JFrame implements ActionListener {

    /**
     * ラベル
     */
    JLabel label;

    /**
     * コンストラクタ
     *
     * @author n_matsumoto
     */
    generatorMain() {
        JButton button = new JButton("csvファイルを選択");
        button.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        label = new JLabel();

        JPanel labelPanel = new JPanel();
        labelPanel.add(label);

        getContentPane().add(labelPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
    }

    /**
     * generation メインメソッド
     *
     * @param args 引数無し
     * @author n_matsumoto
     */
    public static void main(String args[]) {
        generatorMain frame = new generatorMain();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 300, 150);
        frame.setTitle("クラス生成ツール");
        frame.setVisible(true);
    }

    /**
     * イベントハンドラ
     *
     * @param e イベント
     * @author n_matsumoto
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int selected = fileChooser.showOpenDialog(this);

        if (selected == JFileChooser.APPROVE_OPTION) {
            try {
                this.processManager(fileChooser.getSelectedFile());
                this.label.setText("クラス生成処理が完了しました。");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (selected == JFileChooser.CANCEL_OPTION) {
            label.setText("キャンセルされました");
        } else if (selected == JFileChooser.ERROR_OPTION) {
            label.setText("エラー又は取消しがありました");
        }
    }

    /**
     * プロセス管理マネージャ(ここで実際に処理を実行する)
     *
     * @param csv 読み込んだCSVファイル
     * @author n_matsumoto
     */
    private void processManager(File csv) throws IOException {
        // csvファイルの取得と変換
        CsvConfig config = new CsvConfig();
        config.setSkipLines(1);
        HashMap<String, List<RecordEntity>> list = null;
        list = convert(map(Csv.load(csv, config, new StringArrayListHandler())));

        // 生成
        for (IGenerator generator : ServiceLoader.load(IGenerator.class)) {
            generator.generate(list, csv.getParent());
        }
    }
}
