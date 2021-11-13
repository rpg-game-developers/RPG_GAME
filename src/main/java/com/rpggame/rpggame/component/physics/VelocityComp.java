package com.rpggame.rpggame.component.physics;

import com.rpggame.rpggame.component.Component;

public class VelocityComp implements Component {
    double x;
    double y;

    public VelocityComp() {
    }

    public VelocityComp(double x, double y) {
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
