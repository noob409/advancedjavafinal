package org.example;

import java.awt.*;
import java.util.ArrayList;

public class DrawInfo {
    private Color color;
    private float stroke;
    private ArrayList<Point> traceLine;

    public DrawInfo(Color color, float stroke, ArrayList<Point> traceLine) {
        this.color = color;
        this.stroke = stroke;
        this.traceLine = traceLine;
    }

    public Color getLineColor(){
        return color;
    }
    public float getLineStroke(){
        return stroke;
    }
    public ArrayList<Point> getTraceLine(){
        return traceLine;
    }
}
