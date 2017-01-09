import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import sun.audio.*;

public class gameOver extends JPanel{
    JButton startBtn = new JButton();
    JButton exitBtn = new JButton();
    static Image imageB;
    static int x=90, y=300, sX=352, sY=350;
    Image[] imageArray = new Image[20];
    Image i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20;
    Timer overTimer;
    int counter=0;
    boolean reverse = false;
    
    public gameOver(){

    	setLayout(null);
    	setBackground(Color.BLACK);
        
        i1 = new ImageIcon("g1.png").getImage();
        i2 = new ImageIcon("g2.png").getImage();
        i3 = new ImageIcon("g3.png").getImage();
        i4 = new ImageIcon("g4.png").getImage();
        i5 = new ImageIcon("g5.png").getImage();
        i6 = new ImageIcon("g6.png").getImage();
        i7 = new ImageIcon("g7.png").getImage();
        i8 = new ImageIcon("g8.png").getImage();
        i9 = new ImageIcon("g9.png").getImage();
        i10 = new ImageIcon("g10.png").getImage();
        i11 = new ImageIcon("g11.png").getImage();
        i12 = new ImageIcon("g12.png").getImage();
        i13 = new ImageIcon("g13.png").getImage();
        i14 = new ImageIcon("g14.png").getImage();
        i15 = new ImageIcon("g15.png").getImage();
        i16 = new ImageIcon("g16.png").getImage();
        i17 = new ImageIcon("g17.png").getImage();
        i18 = new ImageIcon("g18.png").getImage();
        i19 = new ImageIcon("g19.png").getImage();
        i20 = new ImageIcon("g20.png").getImage();
        
        imageArray[0] = i1;     imageArray[1] = i2;     imageArray[2] = i3;
        imageArray[3] = i4;     imageArray[4] = i5;     imageArray[5] = i6;
        imageArray[6] = i7;     imageArray[7] = i8;     imageArray[8] = i9;
        imageArray[9] = i10;    imageArray[10] = i11;   imageArray[11] = i12;
        imageArray[12] = i13;    imageArray[13] = i14;   imageArray[14] = i15;
        imageArray[15] = i16;    imageArray[16] = i17;   imageArray[17] = i18;
        imageArray[18] = i19;    imageArray[19] = i20;
        
        imageB = new ImageIcon("black.jpg").getImage();
	
    	startBtn.setBounds(95, 590, 80, 50);
    	startBtn.setBorderPainted(false);
        startBtn.setContentAreaFilled(false);
        startBtn.setFocusPainted(false); 
        startBtn.setOpaque(false);
        startBtn.addActionListener(new gListener());
        
        exitBtn.setBounds(350, 590, 80, 50);
        exitBtn.setBorderPainted(false);
        exitBtn.setContentAreaFilled(false);
        exitBtn.setFocusPainted(false); 
        exitBtn.setOpaque(false);
        exitBtn.addActionListener(new gListener());
        overTimer = new Timer(100, new OVER());
        overTimer.start();
    	add(startBtn);
    	add(exitBtn);	
    }
        
    protected void paintComponent(Graphics g) {
    	
    	Game.timer.stop();
    	Game.scoreTimer.stop();
    	
    	g.setColor(Color.WHITE);
    	g.setFont(new Font("Times New Roman", Font.BOLD, 30));
        g.drawImage(imageB, 0, 0, getWidth(), getHeight(), this);
        
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
           
        
        g.drawImage(imageArray[counter], x, y , sX, sY, this);
        g.drawString("Score: " + Integer.toString(Game.Score), 210, 430);
    }
    
    class gListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startBtn){
                Run.frame.remove(Run.Over);
                Run.frame.add(Run.game);
                for(int i = 0; i<3; i++){
                	Game.imageArray[i].setSpeed(4, 1);
                }
                Game.timer.start();
            	Game.scoreTimer.start();
                Run.frame.revalidate();
                Run.frame.repaint();
                startBtn.setFocusable(false);
                
                Game.Score=0;
                Game.Started=false;
                Game.level=0;
                Game.counter=0;
                Game.imageCounter=1;
                Fairy.roundRec1 = new RoundRectangle2D.Double(60, 918, 80, 30, 2, 2);
                Fairy.roundRec2 = new RoundRectangle2D.Double(150, 918, 80, 30, 2, 2);
                Fairy.roundRec3 = new RoundRectangle2D.Double(240, 918, 80, 30, 2, 2);
                Fairy.X=225;
                Fairy.Y=825;
                Fairy.Hp=3;
                
                
                //play game music
                Run.clip.stop();
                Run.Music = new File("game.wav");
                try{
                    Run.music();
                }catch (Exception ex){}
            }
            else if (e.getSource() == exitBtn){ 
                Run.frame.remove(Run.Over);
                Run.frame.add(Run.front);
                Run.frame.revalidate();
                Run.frame.repaint();
                exitBtn.setFocusable(false);
                
                //play homescreen music
                Run.clip.stop();
                Run.Music = new File("homescreen.wav");
                try{
                    Run.music();
                }catch (Exception ex){}
            }
        }
    }
    
    class OVER implements ActionListener{
        public void actionPerformed (ActionEvent e){
            Run.Over.repaint();
        }
    }
}