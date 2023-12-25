package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class InnerPanel extends JPanel {
    private BufferedImage drawingImage;
    private ArrayList<Point> currentShape = new ArrayList<>();
    private Color selectedColor = Color.BLACK;
    private float strokeWidth = 5.0f;
    private ArrayList<DrawInfo> shapesList = new ArrayList<>();
    private final JButton clearButton;

    public InnerPanel() {
        drawingImage = new BufferedImage(getPreferredSize().width, getPreferredSize().height,
                BufferedImage.TYPE_INT_ARGB);
        setOpaque(false); // Make the panel transparent so that the loaded image can be seen beneath

        // Set up mouse listeners for drawing
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentShape = new ArrayList<>();
                currentShape.add(e.getPoint());
                repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                currentShape.add(e.getPoint());
                shapesList.add(new DrawInfo(selectedColor, strokeWidth, currentShape));
                repaint();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentShape.add(e.getPoint());
                repaint();
            }
        });

        clearButton = new JButton("清除鍵");
        add(clearButton);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearShapes();
                repaint();
            }
        });
        JButton colorButton = new JButton("Choose Color");
        add(colorButton);
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(InnerPanel.this, "Choose Color", null);
                if (selectedColor != null) {
                    setSelectedColor(selectedColor);
                }
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        if (drawingImage != null) {
            // Draw the stored image
            g2d.drawImage(drawingImage, 0, 0, this);
        }

        // Draw all stored lines
        for (DrawInfo shape : shapesList) {
            g2d.setColor(shape.getLineColor());
            g2d.setStroke(new BasicStroke(shape.getLineStroke()));

            for (int i = 0; i < shape.getTraceLine().size() - 1; i++) {
                Point startPoint = shape.getTraceLine().get(i);
                Point endPoint = shape.getTraceLine().get(i + 1);
                g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            }
        }

        // Draw the current line
        if (!currentShape.isEmpty()) {
            g2d.setColor(selectedColor);
            g2d.setStroke(new BasicStroke(strokeWidth));

            Point prevPoint = currentShape.get(0);
            for (Point point : currentShape) {
                g2d.drawLine(prevPoint.x, prevPoint.y, point.x, point.y);
                prevPoint = point;
            }
        }
    }
    public void clearShapes() {
        if (!currentShape.isEmpty()) {
            currentShape.clear();  // Clear the current line
        }
        shapesList.clear();  // Clear all stored shapes
    }
    public void setSelectedColor(Color color) {
        selectedColor = color;
    }
}
