package com.example.lan.n14dcpt040_gamegk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lan on 4/12/2018.
 */

public class Chesboard {
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private int[][] board;
    private int player;
    private Context context;
    private int bitmapWidth = 800;
    private int bitmapHeight = 800;
    private List<Line> Listline;
    private Negamax negamax;
    private int colQty = 8;
    private int rowQty = 8;
    private Bitmap tick;
    private int winer = 1;
    private Bitmap cross;


    public Chesboard(Context context, int bitmapWidth, int bitmapHeight, int colQty, int rowQty) {
        this.context = context;
        this.bitmapWidth = bitmapWidth;
        this.bitmapHeight = bitmapHeight;
        this.colQty = colQty;
        this.rowQty = rowQty;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getBitmapWidth() {
        return bitmapWidth;
    }

    public void setBitmapWidth(int bitmapWidth) {
        this.bitmapWidth = bitmapWidth;
    }

    public int getBitmapHeight() {
        return bitmapHeight;
    }

    public void setBitmapHeight(int bitmapHeight) {
        this.bitmapHeight = bitmapHeight;
    }

    public int getColQty() {
        return colQty;
    }

    public void setColQty(int colQty) {
        this.colQty = colQty;
    }

    public int getRowQty() {
        return rowQty;
    }

    public void setRowQty(int rowQty) {
        this.rowQty = rowQty;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public void init() {
        bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        Listline = new ArrayList<>();
        paint = new Paint();
        int strokeWidth = 2;
        paint.setStrokeWidth(strokeWidth);
        board = new int[rowQty][colQty];
        player = 0;
        int CellWidth = bitmapWidth / colQty;
        int CellHeight = bitmapHeight / rowQty;

//        int CellWidthBm = bitmapWidth / colQty;
//        int CellHeightBm = bitmapHeight / rowQty;

        for (int i = 0; i < rowQty; i++) {
            for (int j = 0; j < colQty; j++) {
                board[i][j] = -1;
            }
        }
        for (int i = 0; i <= colQty; i++) {
            Listline.add(new Line(CellWidth * i, 0, CellWidth * i, bitmapHeight));
        }
        for (int i = 0; i <= rowQty; i++) {
            Listline.add(new Line(0, i * CellHeight, bitmapWidth, i * CellHeight));
        }
        cross = BitmapFactory.decodeResource(context.getResources(), R.drawable.cross);// hình ảnh của dấu cross
        tick = BitmapFactory.decodeResource(context.getResources(), R.drawable.tick);// hình ảnh của dấu tick
    }

    public Bitmap drawBoard() {
        Line line;
        for (int i = 0; i < Listline.size(); i++) {
            line = Listline.get(i);
            canvas.drawLine(line.getX0(), line.getY0(), line.getXe(), line.getYe(), paint);
        }
        return this.bitmap;

    }

    public boolean onTouch(View view, MotionEvent motionEvent) {

        int cellWidth = view.getWidth() / colQty;
        int cellHeight = view.getHeight() / rowQty;
        int colIndex = (int) (motionEvent.getX() / cellWidth);
        int rowIndex = (int) (motionEvent.getY() / cellHeight);
        Log.i("", colIndex + "-" + rowIndex);
        if (board[rowIndex][colIndex] != -1) {
            return true;
        }


        board[rowIndex][colIndex] = player;
        onDrawBoard(colIndex, rowIndex, view);
        view.invalidate();
        player = (player + 1) % 2;
        return true;
    }


    private void onDrawBoard(int colIndex, int rowIndex, View view) {
        int cellwidth = bitmapWidth / colQty;
        int cellheight = bitmapHeight / rowQty;
        board[rowIndex][colIndex] = player;//gán nước đi
        if (player == 0) {
            canvas.drawBitmap(
                    cross,
                    new Rect(0, 0, cross.getWidth(), cross.getHeight()),
                    new Rect(colIndex * cellwidth, rowIndex * cellheight, (colIndex + 1) * cellwidth, (rowIndex + 1) * cellheight),
                    paint);
            // player = 1;
        } else {

            canvas.drawBitmap(
                    tick,
                    new Rect(0, 0, tick.getWidth(), tick.getHeight()),
                    new Rect(colIndex * cellwidth, rowIndex * cellheight, (colIndex + 1) * cellwidth, (rowIndex + 1) * cellheight),
                    paint);
        }
    }


    public boolean isgameover() {
        if (checkWin(0) || checkWin(2))
            return true;

        int count = 0;
        for (int i = 0; i < rowQty; i++) {
            for (int j = 0; j < colQty; j++) {
                if (board[i][j] == 1) {
                    count++;
                }
            }
        }
        if (count == 0) {
            return true;
        }
        return false;
    }

    private boolean checkWin(int player) {
        int rowIndex = 0;
        int colIndex = 0;
        //dọc
        int dem = 0;
        do{
            dem++;
            rowIndex++;
        }while (rowIndex < rowQty && board[rowIndex][colIndex] == player);
        rowIndex = rowIndex-1;
        while (rowIndex>=0 && board[rowIndex][colIndex] == player){
            dem++;
            rowIndex--;
        }
        if(dem == 1){
            winer = player;
            return true;
        }
//ngang

        dem= 0;
        do {
            dem++;
            colIndex++;
        } while (colIndex < colQty && board[rowIndex][colIndex] == player);
        colIndex = colIndex - 1;
        while (colIndex >= 0 && board[rowIndex][colIndex] == player) {
            dem++;
            colIndex--;
        }
        if (dem == 1) {
            winer = player;
            return true;
        }
//chéo

        dem = 0;

        do {
            dem++;
           rowIndex++;
            colIndex++;
        } while (colIndex < colQty && rowIndex < rowQty && board[rowIndex][colIndex] == player);
        rowIndex = rowIndex - 1;
        colIndex = colIndex - 1;
        while (colIndex >= 0 && rowIndex >= 0 && board[rowIndex][colIndex] == player) {
            dem++;
            colIndex--;
            rowIndex--;
        }
        if (dem == 1) {
            winer = player;
            return true;
        }

//chéo t2
        dem = 0;


        do {
            dem++;
            rowIndex--;
            colIndex++;
        } while (colIndex < colQty && rowIndex >= 0 && board[rowIndex][colIndex] == player);
        rowIndex = rowIndex + 1;
        colIndex = colIndex - 1;
        while (colIndex >= 0 && rowIndex < rowQty && board[rowIndex][colIndex] == player) {
            dem++;
            colIndex--;
            rowIndex++;
        }
        if (dem == 1) {
            winer = player;
            return true;
        }

        return false;

    }

    public int getCurrentDept() {
        int count = 0;
        for (int i = 0; i < rowQty; i++) {
            for (int j = 0; j < colQty; j++) {
                if (board[i][j] == -1) count++;
            }
        }
        return count;
    }

    public List<Move> getMove() {
        List<Move> move = new ArrayList<>();
        for (int i = 0; i < rowQty; i++) {
            for (int j = 0; j < colQty; j++) {
                if (board[i][j] == 1) {
                    move.add((new Move(i, j)));
                }
            }
        }
        return move;
    }

    public void makemove(Move move) {
        board[move.getIndexrow()][move.getIndexcol()] = player;
        player = (player + 1) % 2;

    }


    public int Evaluate(int player) {
        if (checkWin(player))
            return 1;
        if (checkWin((player + 1) % 2)) {
            return -1;
        }
        return 0;
    }
}
