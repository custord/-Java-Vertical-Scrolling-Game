import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
public class WinEnding extends JPanel {
    static WinEnding frame; 
    JButton startBtn = new JButton();
    JButton exitBtn = new JButton();
    Image[] imageF = new Image[3];
    Image[] imageK = new Image[3];
    Image image, f1, f2, f3, k1, k2, k3;
    Timer winTimer;
    static int counter=0;
    boolean reverse = false;

    public WinEnding(){
    	
        winTimer = new Timer(250, new TimerListener());
        setLayout(null);

        image = new ImageIcon("happyEnding.jpg").getImage();
        f1 = new ImageIcon("rightUp.png").getImage();
        f2 = new ImageIcon("rightMiddle.png").getImage();
        f3 = new ImageIcon("rightDown.png").getImage();
        k1 = new ImageIcon("king1.png").getImage();
        k2 = new ImageIcon("king2.png").getImage();
        k3 = new ImageIcon("king3.png").getImage();

        imageF[0] = f1;
        imageF[1] = f2;
        imageF[2] = f3;
        imageK[0] = k1;
        imageK[1] = k2;
        imageK[2] = k3;


        startBtn.setBounds(90, 860, 80, 50);
        startBtn.setBorderPainted(false);
        startBtn.setContentAreaFilled(false);
        startBtn.setFocusPainted(false); 
        startBtn.setOpaque(false);
        startBtn.addActionListener(new endListener());


        exitBtn.setBounds(365, 860, 80, 50);
        exitBtn.setBorderPainted(false);
        exitBtn.setContentAreaFilled(false);
        exitBtn.setFocusPainted(false); 
        exitBtn.setOpaque(false);
        exitBtn.addActionListener(new endListener());

        add(startBtn);
        add(exitBtn);
        winTimer.start();
    }
    
    public void paintComponent(Graphics g) {
    	Game.timer.stop();
    	Game.scoreTimer.stop();
        g.drawImage(gameOver.imageB, 0, 0, getWidth(), getHeight(), this);
        if(!reverse){
            counter++;
            if (counter==2) 
                reverse = true;
        }
        else{
            counter--;
            
            if (counter==0) 
                reverse = false;
        }
        g.drawImage(image, 0, 0, 540, 960, this);
        g.drawImage(imageF[counter], 180, 570, this);
        g.drawImage(imageK[counter], 320, 540, this);
    }
 
    
    class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Run.win.repaint();
        }
    }
    class endListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startBtn){
                Run.frame.remove(Run.win);
                Run.frame.add(Run.front);
                Run.frame.revalidate();
                Run.frame.repaint();
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
                Run.frame.remove(Run.win);
                Run.frame.add(Run.front);
                Run.frame.revalidate();
                Run.frame.repaint();
                
                //play homescreen music
                Run.clip.stop();
                Run.Music = new File("homescreen.wav");
                try{
                    Run.music();
                }catch (Exception ex){}
            }
        }
    }
}

    

