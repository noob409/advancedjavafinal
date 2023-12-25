package org.example;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class MysqlConnection {

    private Connection conn;
    public MysqlConnection(List<JapaneseType> japaneseTypeList) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find the class");
        }
        try {
            //  For Lenovo configuration
//            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/advancedforjavafinal","test","test");
            //  For Acer configuration
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/advancedforjavafinal","root01","root");

            PreparedStatement statDelete = conn.prepareStatement("DELETE FROM japanese_kana");
            statDelete.executeUpdate();

            PreparedStatement statAdd = conn.prepareStatement("INSERT INTO japanese_kana VALUES (?,?,?)");
            for (JapaneseType japaneseType: japaneseTypeList) {
                statAdd.setString(1, japaneseType.getRomaji());
                statAdd.setString(2, japaneseType.getKatakana());
                statAdd.setString(3, japaneseType.getHiragana());
                statAdd.executeUpdate();
            }

            PreparedStatement statAll = conn.prepareStatement("Select * From japanese_kana");
            ResultSet resultSet = statAll.executeQuery();

            while (resultSet.next()) {
                String romaji = resultSet.getString("romaji");
                String katakana = resultSet.getString("katakana");
                String hiragana = resultSet.getString("hiragana");
                System.out.println("================================");
                System.out.print(romaji + "\t");
                System.out.print(katakana + "\t");
                System.out.print(hiragana + "\n");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
