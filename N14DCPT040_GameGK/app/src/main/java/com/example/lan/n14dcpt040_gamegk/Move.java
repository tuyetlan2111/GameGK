package com.example.lan.n14dcpt040_gamegk;

/**
 * Created by Lan on 4/13/2018.
 */

public class Move {
    private int indexcol;
    private int indexrow;
    public Move(){

    }

    public Move(int indexcol, int indexrow) {
        this.indexcol = indexcol;
        this.indexrow = indexrow;
    }

    public int getIndexcol() {
        return indexcol;
    }

    public void setIndexcol(int indexcol) {
        this.indexcol = indexcol;
    }
    public int getIndexrow() {
        return indexrow;
    }

    public void setIndexrow(int indexrow) {
        this.indexrow = indexrow;
    }


}
