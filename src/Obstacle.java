import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Obstacle extends JPanel {
    Font font = new Font("Times New Roman", Font.PLAIN , 30);
    Font font2 = new Font("Times New Roman", Font.PLAIN , 20);
    int X, Y = 50, typeImage;
    int[] xArray= {9, 117, 225, 333, 441, 3000};
    int randomIndex = new Random().nextInt(xArray.length);
    int randomImage = 0, randomSpeed = 7, speedMax=4, speedMin=1;
    Image picture;

    public Obstacle(Image picture, int typeImage){
        this.typeImage = typeImage;
        this.picture = picture;
        generateRandomXLocation();
        randomSpeed = (int)(Math.random() * speedMax + speedMin);
    }

    public void setSpeed(int max, int min){
        speedMax = max;
        speedMin = min;
    } 
    public void setImage(Image emptyPic){ 
        picture = emptyPic; 
        typeImage=-1;
    }
    
    public int getX() { return X; }
    public int getY() { return Y; }
    public int getMaxSpeed() { return speedMax; }  
    public int getMinSpeed() { return speedMin; } 
    public int getImage(){ return typeImage; }
   
    public void generateRandomXLocation(){
        randomIndex = new Random().nextInt(xArray.length);
        X = xArray[randomIndex];
    }
    
    public void paint(Graphics g){
        g.setFont(font);
        g.setColor(Color.BLACK);

        String score = Integer.toString(Game.Score);
        g.drawString("   Score: " + score + "          Level " + Game.level, 15, 35);
        g.drawString(": + 25", 420, 35); 
        g.setFont(font2);
        g.drawString(" + Hp ", 370, 940);
        g.drawString(" - Hp ", 460, 940);
                      
        g.drawImage(picture, X, Y, 75, 68, this); //obstacle
        g.drawImage(Game.icon1, 380, 10, 30, 30, this); //pumpkin icon
        g.drawImage(Game.icon2, 18, 918, 30, 30, this); //fairy icon
        g.drawImage(Game.icon4, 340, 918, 30, 30, this); //bomb icon
        g.drawImage(Game.icon3, 430, 918, 30, 30, this); //love icon
    }

    public void update(){
        //changes the obstacle xLocation if the created obstacle reaches the bottom of the screen
        if(Y >= 850){
            generateRandomXLocation();
            Y = 50;
            randomSpeed = (int)(Math.random() * speedMax + speedMin);
            randomImage = (int)(Math.random() * 10 + 0);
            
            if (randomImage <6)
                typeImage = 1;
            else if ( (randomImage >5) && (randomImage <9) )
                typeImage = 2;
            else if (randomImage == 9)
                typeImage = 3;
            
            picture = Game.storeImage[randomImage];
        }

        //moves the obstacle down if the obstacle is inside the window
        if(Y <= 850)
            Y += randomSpeed;    
    }
}

class Background extends JPanel{
    static int Ox = 0;
    public void paint(Graphics g){
        g.drawImage(Game.mainBackground, Ox, 45, 540, 870, this);
    }
}