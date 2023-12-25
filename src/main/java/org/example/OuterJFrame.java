package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;

public class OuterJFrame extends JFrame{

    private OuterPanel outerPanel;
    private InnerPanel innerPanel;

    public OuterJFrame() {
        setTitle("日語五十音學習平台 (目前僅提供平假名)");
        outerPanel = new OuterPanel();
        add(outerPanel, BorderLayout.CENTER);
        innerPanel = new InnerPanel();
        add(innerPanel, BorderLayout.SOUTH);
        innerPanel.setPreferredSize(new Dimension(300,250));

        pack();
    }
}
