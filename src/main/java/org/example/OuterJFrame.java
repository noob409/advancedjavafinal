package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

public class OuterJFrame extends JFrame{
    public OuterJFrame() {
        setTitle("日語五十音學習平台");
        OuterPanel outerPanel = new OuterPanel();
        add(outerPanel, BorderLayout.CENTER);

        InnerPanel innerPanel = new InnerPanel();
        add(innerPanel, BorderLayout.SOUTH);
        innerPanel.setPreferredSize(new Dimension(300,300));

        pack();
    }
}
