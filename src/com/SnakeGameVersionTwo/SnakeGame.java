package com.company;

import java.applet.Applet;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame extends Applet implements Runnable, KeyListener {
    Graphics gfx;
    Image img;
    Thread thread;
    Snake snake;
    boolean gameOver;
    Token token;
    Token token2;
    public void init()
    {
        this.resize(400, 400);
        img = createImage(400, 400);
        gfx =  img.getGraphics();
        gameOver = false;
        this.addKeyListener(this);
        snake = new Snake();
        token = new Token(snake);
        token2 = new Token(snake);
        thread = new Thread(this);
        thread.start();
    }
    public void paint(Graphics g)
    {
        gfx.setColor(Color.blue);
        gfx.fillRect(0, 0, 400, 400);
        if(!gameOver) {
            snake.draw(gfx);
            token.draw(gfx);
            token2.draw(gfx);
        }
        else
        {
            gfx.setColor(Color.RED);
            gfx.drawString("The game is officially over", 180, 150);
            gfx.drawString("Final Score: " + token.getScore(), 180, 170);
        }
        g.drawImage(img, 0, 0, null);
    }
    public void update(Graphics g)
    {
        paint(g);
    }
    public void repaint(Graphics g)
    {
        paint(g);
    }

    public void run()
    {
        for(;;)
        {
            if(!gameOver)
            {
                snake.move();
                this.checkGameOver();
                token.snakeCollision();
                token2.snakeCollision();
            }
            this.repaint();
            try{
                Thread.sleep(40);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    public void checkGameOver()
    {
        //if its gone off the screen, though its 400 its three less
        if(snake.getX() < 0 || snake.getX() > 396)
        {
            gameOver = true;
        }
        if(snake.getY() < 0 || snake.getY() > 396)
        {
            gameOver = true;
        }
        if (snake.snakeCollision())
        {
            gameOver = true;
        }
    }

    public void keyPressed(KeyEvent e)
    {
        if(!snake.isMoving())
        {
            if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_DOWN)
            {
                snake.setMoving(true);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            if(snake.getyDir() != 1)
            {
                snake.setyDir(-1);
                snake.setxDir(0);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            if(snake.getyDir() != -1)
            {
                snake.setyDir(1);
                snake.setxDir(0);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            if(snake.getxDir() != 1)
            {
                snake.setxDir(-1);
                snake.setyDir(0);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if(snake.getxDir() != -1)
            {
                snake.setxDir(1);
                snake.setyDir(0);
            }
        }
    }
    public void keyReleased(KeyEvent e)
    {

    }
    public void keyTyped(KeyEvent e)
    {

    }
}
