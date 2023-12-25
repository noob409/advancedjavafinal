package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class OuterPanel extends JPanel {
    private String[] a = {"あ", "い", "う", "え", "お"};
    private String[] ka = {"か", "き", "く", "け", "こ"};
    private String[] sa = {"さ", "し", "す", "せ", "そ"};
    private String[] ta = {"た", "ち", "つ", "て", "と"};
    private String[] na = {"な", "に", "ぬ", "ね", "の"};
    private String[] ha = {"は", "ひ", "ふ", "へ", "ほ"};
    private String[] ma = {"ま", "み", "む", "め", "も"};
    private String[] ya = {"や", "ゆ", "よ"};
    private String[] ra = {"ら", "り", "る", "れ", "ろ"};
    private String[] wa = {"わ", "を"};
    private String[] h = {"ん"};
    private final JComboBox[] comboBoxes;
    private String romaji;
    private BufferedImage image;
    private final JButton playButton;
    private PlaySoundTest playSoundTest;
//    private InnerPanel innerPanel;
    public OuterPanel() {
        setLayout(new FlowLayout());

//        innerPanel = new InnerPanel();

        comboBoxes = new JComboBox[]{
                new JComboBox(a),
                new JComboBox(ka),
                new JComboBox(sa),
                new JComboBox(ta),
                new JComboBox(na),
                new JComboBox(ha),
                new JComboBox(ma),
                new JComboBox(ya),
                new JComboBox(ra),
                new JComboBox(wa),
                new JComboBox(h)
        };
        for (JComboBox comboBox : comboBoxes) {
            comboBox.addActionListener(new ComboListener());
            add(comboBox);
        }
        playButton = new JButton("播放鍵");
        add(playButton);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (romaji != "") {
                    playSoundTest.startPlayingSound("C:\\Users\\a4129\\OneDrive\\文件\\大三上" +
                            "\\Advanced Object-oriented programming\\B11009006_Final_v2" +
                            "\\B11009006_Final\\sounds\\" + romaji + ".wav");
                } else {
                }
            }
        });

    }
    private class ComboListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) throws RuntimeException {
            JComboBox source = (JComboBox) e.getSource();
            String selectedValue = (String) source.getSelectedItem();

            try {
                //  For Lenovo configuration
//                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/advancedforjavafinal","test","test");
                //  For Acer configuration
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/advancedforjavafinal","root01","root");

                PreparedStatement statement = conn.prepareStatement("SELECT * FROM japanese_kana where hiragana = '" + selectedValue + "'");
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    romaji = resultSet.getString("romaji");
                    try {
                        loadImage(romaji);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                conn.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public void loadImage(String fileName) throws IOException {
        //  For Acer configuration
        String filePath = "C:\\Users\\a4129\\OneDrive\\文件\\大三上" +
                "\\Advanced Object-oriented programming" +
                "\\B11009006_Final_v2\\B11009006_Final\\images\\" + fileName + ".png";
        System.out.println(fileName);
        image = ImageIO.read(new File(filePath));
        repaint();  //  Call paintComponent()
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int desiredWidth = 200; // Set your desired width
            int desiredHeight = 200; // Set your desired height

            int x = (getWidth() - desiredWidth) / 2;
            int y = (getHeight() - desiredHeight) / 2;

            // Draw the scaled image, 縮小比例
            g.drawImage(image.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH), x, y, this);
        }
    }
    // return image dimensions
//    @Override
//    public Dimension getPreferredSize() {
//        if (image != null) {
//            return new Dimension(image.getWidth(), image.getHeight());
//        } else {
//            return super.getPreferredSize();
//        }
//    }
}
