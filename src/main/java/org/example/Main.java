package org.example;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        JapaneseJsonCatch japaneseJsonCatch = new JapaneseJsonCatch();
        List<JapaneseType> japaneseTypeList = japaneseJsonCatch.getJapaneseList();

        new MysqlConnection(japaneseTypeList);

        OuterJFrame middleJFrame = new OuterJFrame();
        middleJFrame.setDefaultCloseOperation(middleJFrame.EXIT_ON_CLOSE);
        middleJFrame.setSize(700,700);
        middleJFrame.setVisible(true);
    }
}