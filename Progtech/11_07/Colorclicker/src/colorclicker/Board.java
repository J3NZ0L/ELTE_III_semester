package colorclicker;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author pinter
 */
public class Board {

    private Field[][] board;
    private final int boardSize;
    private int steps;

    private ArrayList<Point> points;
    private Random random = new Random();
    private int clickNum = 0;
    private final int NUM_COLORED_FIELDS = 4;


    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.steps = 0 ;
        points = new ArrayList<>();
        board = new Field[this.boardSize][this.boardSize];
        for (int i = 0; i < this.boardSize; ++i) {
            for (int j = 0; j < this.boardSize; ++j) {
                board[i][j] = new Field();
                points.add(new Point(i, j));
            }
        }
        Collections.shuffle(points);
    }
    
    public boolean isOver() {
        if (steps*5==boardSize*boardSize) {
            return true;
        }
        return false;
    }
    
    public Field get(int x, int y) {
        return board[x][y];
    }
    
    public Field get(Point point) {
        int x = (int)point.getX();
        int y = (int)point.getY();
        return get(x, y);
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public void incSteps(){
        steps++;
    }

    public ArrayList<Point> step(int x,int y) {
        ArrayList<Point> res = new ArrayList<>();
        if (this.get(x, y).getColor() == null) {
            incSteps();
            Color color = new Color(random.nextInt(256),
                    random.nextInt(256), random.nextInt(256));
            this.get(x, y).setColor(color);
            this.get(x, y).setNumber(++clickNum);
            for (int i = 0; i < NUM_COLORED_FIELDS;) {
                Point point = points.removeLast();
                res.add(point);
                if (this.get(point).getColor() == null) {
                    this.get(point).setColor(color);
                    this.get(point).setNumber(clickNum);
                    i++;
                }
            }
        }
        return res;
    }
}
