import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import sun.audio.*;

public class frontPage extends JPanel {
	
    JButton startBtn = new JButton();
    JButton storyBtn = new JButton();
    JButton exitBtn = new JButton();
    Image[] imageArray = new Image[20];
    Image i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20;
    Timer frontTimer;
    int counter=0;
    static int x=0, y=0, sX=540, sY=960;
    boolean reverse = false;

    public frontPage(){
    	//Run.frame.remove(Run.game);
        //Run.frame.add(Run.story);
        //Run.frame.revalidate();
        //Run.frame.repaint();
    	setLayout(null);
        setBackground(Color.WHITE);
        
    	i1 = new ImageIcon("f1.png").getImage();
        i2 = new ImageIcon("f2.png").getImage();
        i3 = new ImageIcon("f3.png").getImage();
        i4 = new ImageIcon("f4.png").getImage();
        i5 = new ImageIcon("f5.png").getImage();
        i6 = new ImageIcon("f6.png").getImage();
        i7 = new ImageIcon("f7.png").getImage();
        i8 = new ImageIcon("f8.png").getImage();
        i9 = new ImageIcon("f9.png").getImage();
        i10 = new ImageIcon("f10.png").getImage();
        i11 = new ImageIcon("f11.png").getImage();
        i12 = new ImageIcon("f12.png").getImage();
        i13 = new ImageIcon("f13.png").getImage();
        i14 = new ImageIcon("f14.png").getImage();
        i15 = new ImageIcon("f15.png").getImage();
        i16 = new ImageIcon("f16.png").getImage();
        i17 = new ImageIcon("f17.png").getImage();
        i18 = new ImageIcon("f18.png").getImage();
        i19 = new ImageIcon("f19.png").getImage();
        i20 = new ImageIcon("f20.png").getImage();
        
        imageArray[0] = i1;     imageArray[1] = i2;     imageArray[2] = i3;
        imageArray[3] = i4;     imageArray[4] = i5;     imageArray[5] = i6;
        imageArray[6] = i7;     imageArray[7] = i8;     imageArray[8] = i9;
        imageArray[9] = i10;    imageArray[10] = i11;   imageArray[11] = i12;
        imageArray[12] = i13;    imageArray[13] = i14;   imageArray[14] = i15;
        imageArray[15] = i16;    imageArray[16] = i17;   imageArray[17] = i18;
        imageArray[18] = i19;    imageArray[19] = i20;
        
    	startBtn.setBounds(70, 430, 80, 50);
    	startBtn.setBorderPainted(false);
    	startBtn.setContentAreaFilled(false);
    	startBtn.setFocusPainted(false); 
    	startBtn.setOpaque(false);
        startBtn.addActionListener(new frontListener());
        
        storyBtn.setBounds(70, 510, 80, 50);
        storyBtn.setBorderPainted(false);
        storyBtn.setContentAreaFilled(false);
        storyBtn.setFocusPainted(false); 
        storyBtn.setOpaque(false);
        storyBtn.addActionListener(new frontListener());
        
        exitBtn.setBounds(70, 580, 80, 50);
        exitBtn.setBorderPainted(false);
        exitBtn.setContentAreaFilled(false);
        exitBtn.setFocusPainted(false); 
        exitBtn.setOpaque(false);
        exitBtn.addActionListener(new frontListener());
      
        frontTimer = new Timer(100, new Front());
        frontTimer.start();
    	add(startBtn);
    	add(storyBtn);
    	add(exitBtn);
    }
          
    protected void paintComponent(Graphics g) {
        g.drawImage(gameOver.imageB, 0, 0, getWidth(), getHeight(), this);
        if(!reverse){
            counter++;
            
            if (counter==19) 
                reverse = true;
        }
        else{
            counter--;
            
            if (counter==0) 
                reverse = false;
        }
                      
        g.drawImage(imageArray[counter], x, y, sX, sY, this);
    }
    
    class frontListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startBtn){
            	for(int i = 0; i<3; i++){
                	Game.imageArray[i].setSpeed(4, 1);
                }
//                Run.starts = true;
//                Run.game = new Game();
                Run.frame.remove(Run.front);
                Run.frame.add(Run.game);
                Game.timer.start();
            	Game.scoreTimer.start();
                Run.frame.revalidate();
                Run.frame.repaint();
                
                //play game music 
                Run.clip.stop();
                Run.Music = new File("game.wav");
                try{
                    Run.music();
                }catch (Exception ex){}
                startBtn.setFocusable(false);
                Run.frame.setFocusable(true);
                Run.frame.requestFocusInWindow();
                Run.resetAll();
            }
            else if (e.getSource() == storyBtn){ 
                Run.frame.remove(Run.front);
                Run.frame.add(Run.story);
                Run.frame.revalidate();
                Run.frame.repaint();
                storyBtn.setFocusable(false);
                Run.story.setFocusable(true);
                Run.story.requestFocusInWindow();
                Run.resetAll();
                Storyline.NextStory();
                
                //play story music
                Run.clip.stop();
                Run.Music = new File("storyEnding.wav");
                try{
                    Run.music();
                }catch (Exception ex){}
            }
            else if (e.getSource() == exitBtn){ 
            
                    System.exit(0);
                    exitBtn.setFocusable(false);
               
            }
        }
    }
    
    class Front implements ActionListener{
        public void actionPerformed (ActionEvent e){
            Run.front.repaint();
        }
    }
}