import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

public class Fairy extends JPanel {
    static Shape roundRec1 = new RoundRectangle2D.Double(60, 918, 80, 30, 2, 2);
    static Shape roundRec2 = new RoundRectangle2D.Double(150, 918, 80, 30, 2, 2);
    static Shape roundRec3 = new RoundRectangle2D.Double(240, 918, 80, 30, 2, 2);
    static int X=225, Y=825;
    Image picture;
    int[] xArray= {9, 117, 225, 333, 441};
    static int Hp=3;

    public Fairy(Image picture){
        this.picture = picture;
    }
    public void setImage(Image picture){
        this.picture = picture;
    }

    public int getX() { return X; }
    public int getY() { return Y; }
    public int getHp() { return Hp; }
    
    public void moveLeft(){
        if (X != 9)
            X-=108;
    }
    public void moveRight(){
        if (X != 441)
            X+=108;
    }
    public void moveUp(){
        if (Y != 69)
            Y-=108;
    }
    public void moveDown(){
        if (Y<800)
            Y+=108;
    }
    
    public void decreaseHp(){
        Hp--;
    }
    public void increaseHp(){
        if (Hp!=3)
            Hp++;
    }
    
    public void paint(Graphics g){
        g.drawImage(picture, X, Y, 75, 68, this); 
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(255,51,51));
        g2d.fill(roundRec1); //hp bar 1
        g2d.fill(roundRec2); //hp bar 2
        g2d.fill(roundRec3); //hp bar 3
    }
}