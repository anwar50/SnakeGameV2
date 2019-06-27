package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    List<Point> snakePoints;
    int xDir;
    int yDir;
    boolean isMoving;
        //snake increasing!
    boolean elongate;
    final int STARTSIZE = 20;
    final int STARTX = 150;
    final int STARTY = 150;

    public Snake() {
        snakePoints = new ArrayList<Point>();
        xDir = 0;
        yDir = 0;
        isMoving = false;
        elongate = false;
        snakePoints.add(new Point(STARTX, STARTY));

        for(int i = 1; i < STARTSIZE; i++)
        {
            snakePoints.add(new Point(STARTX-i*4, STARTY));

        }
    }
    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        for (Point p : snakePoints) {
            g.fillRect(p.getX(), p.getY(), 5, 5);
        }

    }
    public boolean isMoving()
    {
        return isMoving;
    }
    public void setMoving(boolean moving)
    {
        this.isMoving = moving;
    }
    public void move()
    {
        if(isMoving)
        {   //snake 1 moving!
            Point temp = snakePoints.get(0);
            Point last = snakePoints.get(snakePoints.size()-1);
            Point newstart = new Point(temp.getX() + xDir*4, temp.getY()+ yDir * 4);
            for(int i = snakePoints.size()-1; i >= 1; i--)
            {
                snakePoints.set(i, snakePoints.get(i-1));
            }
            snakePoints.set(0, newstart);

            if(elongate)
            {
                snakePoints.add(last);
                elongate = false;
            }
        }

    }
    //track if the snake has collided with itself
    public boolean snakeCollision()
    {
        int x = this.getX();
        int y = this.getY();
        for(int i = 1; i < snakePoints.size(); i++)
        {
            if(snakePoints.get(i).getX() == x && snakePoints.get(i).getY() == y) {
                return true;
            }
        }
        return false;
    }
    public int getxDir() {
        return xDir;
    }
    public int getyDir() {
        return yDir;
    }
    public void setxDir(int x) {
        this.xDir = x;
    }

    public void setyDir(int yDir) {
        this.yDir = yDir;
    }

    //head of the snake!
    public int getX() {
        return snakePoints.get(0).getX();
    }

    public int getY() {
        return snakePoints.get(0).getY();
    }
    public void setElongate(boolean elongate)
    {
        this.elongate = elongate;
    }
}
