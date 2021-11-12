package com.rpggame.rpggame.component;

public class PositionComponent implements Component {
    private double x;
    private double y;

    public PositionComponent() {
    }

    public PositionComponent(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
