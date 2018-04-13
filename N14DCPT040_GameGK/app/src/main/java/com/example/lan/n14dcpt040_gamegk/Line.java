package com.example.lan.n14dcpt040_gamegk;

/**
 * Created by Lan on 4/13/2018.
 */

public class Line {
    private int x0,y0,xe,ye;

    public Line(int x0, int y0, int xe, int ye) {
        this.x0 = x0;
        this.y0 = y0;
        this.xe = xe;
        this.ye = ye;
    }

    public int getX0() {
        return x0;
    }

    public void setX0(int x0) {
        this.x0 = x0;
    }

    public int getY0() {
        return y0;
    }

    public void setY0(int y0) {
        this.y0 = y0;
    }

    public int getXe() {
        return xe;
    }

    public void setXe(int xe) {
        this.xe = xe;
    }

    public int getYe() {
        return ye;
    }

    public void setYe(int ye) {
        this.ye = ye;
    }
}
