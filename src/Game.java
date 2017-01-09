import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import sun.audio.*;

public class Game extends JPanel {
    
    static Obstacle[] imageArray = new Obstacle[3];
    static Background background = new Background();
    static scoreTimeCounter sTime;
    static Timer timer, scoreTimer;
    static Time myTimer;

    static int counter=0, Score=0, level=1, imageCounter=1;
    static int[] marksCounter = {50, 100, 150, 200, 300};
    static boolean Started = false;
    static Image[] storeImage;
    static Image EmptyImage, image1, image2, image3, mainBackground, icon1, icon2, icon3, icon4;
    static ImageIcon[] bgArray = new ImageIcon[5];
    static ImageIcon imageIcon1, imageIcon2, imageIcon3,
                 first, second, third, 
                 up, middle, down, 
                 leftUp, leftMiddle, leftDown,
                 rightUp, rightMiddle, rightDown, 
                 bg1, bg2, bg3, bg4, bg5, pumpkinIcon, fairyIcon, loveIcon, bombIcon, emp;
    
    public Game() {
        //background images
        bg1 = new ImageIcon("b1.png");
        bg2 = new ImageIcon("b2.png");
        bg3 = new ImageIcon("b3.png");
        bg4 = new ImageIcon("b4.png");
        bg5 = new ImageIcon("b5.png");
        
                
        //background images array
        bgArray[0] = bg1;
        bgArray[1] = bg2;
        bgArray[2] = bg3;
        bgArray[3] = bg4;
        bgArray[4] = bg5;

        //icon images
        imageIcon1 = new ImageIcon("boom.png");
        imageIcon2 = new ImageIcon("pumpkin.png");
        imageIcon3 = new ImageIcon("love.png");
        pumpkinIcon = new ImageIcon("pumpkinIcon.png");
        fairyIcon = new ImageIcon("fairyIcon.png");  
        loveIcon = new ImageIcon("LoveIcon.png");
        bombIcon = new ImageIcon("BombIcon.png");
        emp = new ImageIcon("nothing.png");
        icon1 = pumpkinIcon.getImage();
        icon2 = fairyIcon.getImage();
        icon3 = bombIcon.getImage();
        icon4 = loveIcon.getImage();
        
        //fairy posture
        first = new ImageIcon("up.png");
        second = new ImageIcon("middle.png");
        third = new ImageIcon("down.png");
        up = new ImageIcon("up.png");
        middle = new ImageIcon("middle.png");
        down = new ImageIcon("down.png");
        leftUp = new ImageIcon("leftUp.png");
        leftMiddle = new ImageIcon("leftMiddle.png");
        leftDown = new ImageIcon("leftDown.png");
        rightUp = new ImageIcon("rightUp.png");
        rightMiddle = new ImageIcon("rightMiddle.png");
        rightDown = new ImageIcon("rightDown.png");


        mainBackground = bg1.getImage();
        image1 = imageIcon1.getImage();
        image2 = imageIcon2.getImage();
        image3 = imageIcon3.getImage();

        Image fairy = first.getImage();
        EmptyImage = emp.getImage();
        storeImage = new Image[]{image1, image1, image1, image1, image1, 
                                 image1, image2, image2, image2, image3};
        
        //obstacle
        imageArray[0] = new Obstacle(image2, 2);
        imageArray[1] = new Obstacle(image1, 1);
        imageArray[2] = new Obstacle(image3, 3);
        
        //Timers
        myTimer = new Time(1);
        sTime = new scoreTimeCounter(); //score timer
        timer = new Timer(200, myTimer);
        scoreTimer = new Timer(5, sTime);  //0.005 ms
        timer.start();
        scoreTimer.start();
    }
    public void paint(Graphics graphics) {
        graphics.drawImage(gameOver.imageB, 0, 0, getWidth(), getHeight(), this);
        //draw white background
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 540, 960);
        
        //paint background
        background.paint(graphics);
        
        for( int i=0;i<3;i++){
            imageArray[i].paint(graphics);
                    
            if(Score == marksCounter[imageCounter-1]){
                //Score to win            
                if (Score == 300){
                    Run.frame.remove(Run.game);
                    Run.frame.add(Run.win);
                    Run.frame.revalidate();
                    Run.frame.repaint();

                    //play game music 
                    Run.clip.stop();
                    Run.Music = new File("homescreen.wav");
                    try{
                        Run.music();
                    }catch (Exception ex){}
                    Run.frame.setFocusable(true);
                    Run.resetAll();
                }
                
                mainBackground = bgArray[imageCounter].getImage();
                level++;
                imageArray[i].setSpeed(imageArray[i].getMaxSpeed()+1, imageArray[i].getMinSpeed()+1);
                
                if (imageCounter!=5)
                    imageCounter++;
            }
            
            Run.fairyObject.paint(graphics);
        }
    }   
    public void update() { for (Obstacle aImageArray : imageArray) aImageArray.update(); }
    class Time implements ActionListener{
        int direction;
        
        Time(int direction){
            this.direction = direction;
        }
        
        void setDirection(int direction){
            this.direction = direction;
            if (direction==1){
                first=up;
                second=middle;
                third=down;
            }
            else if (direction==2){
                first=leftUp;
                second=leftMiddle;
                third=leftDown;
            }
            else {        
                first=rightUp;
                second=rightMiddle;
                third=rightDown;
            }
        }
        
        public void actionPerformed(ActionEvent e) {
                counter++;
                if (counter==2){
                    Run.imageFairy = first.getImage();
                }
                else if (counter==4){
                    Run.imageFairy = third.getImage();
                    counter=0;
                }
                else
                    Run.imageFairy = second.getImage(); 
                
                Run.fairyObject.setImage(Run.imageFairy);       
        }
    }
    class scoreTimeCounter implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            
            for( int i=0;i<3;i++){
                if  (     (  Run.fairyObject.getX() == imageArray[i].getX()   ) 
                      &&  ( (Run.fairyObject.getY()-imageArray[i].getY()) <10 )
                      &&  ( (Run.fairyObject.getY()-imageArray[i].getY()) >0  )   
                    )
                {
                    switch(imageArray[i].getImage()){
                        case 1:
                            imageArray[i].setImage(EmptyImage);
                            Run.fairyObject.decreaseHp();

                            //hp bar action
                            if(Run.fairyObject.getHp()==2)
                                Fairy.roundRec3 = new RoundRectangle2D.Double(0, 0, 0, 0, 0, 0);
                            if(Run.fairyObject.getHp()==1)
                                Fairy.roundRec2 = new RoundRectangle2D.Double(0, 0, 0, 0, 0, 0);
                            if(Run.fairyObject.getHp()==0){
                                Fairy.roundRec1 = new RoundRectangle2D.Double(0, 0, 0, 0, 0, 0);
                                Run.frame.remove(Run.game);
                                Run.frame.add(Run.Over);
                                Run.frame.revalidate();
                                Run.frame.repaint();
                                Run.clip.stop();
                                Run.Music = new File("storyEnding.wav");
                                try{
                                    Run.music();
                                }catch (Exception ex){}
                            } 

                            break;

                        case 2:
                            imageArray[i].setImage(EmptyImage);
                            Score+=25;
                            break;

                        case 3:
                            imageArray[i].setImage(EmptyImage);

                            if(Run.fairyObject.getHp()==2)
                                Fairy.roundRec3 = new RoundRectangle2D.Double(240, 918, 80, 30, 2, 2);
                            if(Run.fairyObject.getHp()==1)
                                Fairy.roundRec2 = new RoundRectangle2D.Double(150, 918, 80, 30, 2, 2);

                            //hp bar action
                            if(Run.fairyObject.getHp()!=3){
                                Run.fairyObject.increaseHp();
                            } 
                            break;

                    }
                }
            } 
        }
    }
}