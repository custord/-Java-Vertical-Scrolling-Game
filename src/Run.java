import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import sun.audio.*;

public class Run implements ActionListener{
    static JFrame frame; 
    static Fairy fairyObject;
    static Image imageFairy;
    static frontPage front;
    static Game game;
    static WinEnding win;
    static gameOver Over;
    static Storyline story;
    static File Music= new File("homescreen.wav"); 
    static Clip clip; 
    static boolean starts = false;
    
    public static void main(String[] args) throws Exception{
        front = new frontPage();
        Over = new gameOver();
        imageFairy = new ImageIcon("up.png").getImage();
        fairyObject = new Fairy(imageFairy);
        story = new Storyline();
        game = new Game();
        win = new WinEnding();
        frame = new JFrame();
        frame.setSize(new Dimension(540, 1000));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("FlyMeHome");
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(keylistener);
        frame.addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent arg0) {
                frame__windowStateChanged(arg0);
            }
        });
        frame.add(front);
        frame.setVisible(true);
        //background music
        music();

        while (true) {
            game.update();
            game.repaint();
            Thread.sleep(5); //program sleep 0.005 second
        }
    }   
    public static void frame__windowStateChanged(WindowEvent e){
        // minimized
        if ((e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED){
            System.out.println("min");
        }
        // maximized
        else if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH){
            gameOver.x=700;
            gameOver.y=280;
            gameOver.sX=500;
            gameOver.sY=400;
            front.x=700;
            front.y=0;
            front.sY = 1001;        
        }
    }
    static KeyListener keylistener = new KeyListener(){ 
        @Override
        public void keyPressed(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_UP) {
                Run.fairyObject.moveUp();
            }
            if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                Run.fairyObject.moveDown();
            }
            if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                Run.fairyObject.moveLeft();
                Game.myTimer.setDirection(2);
            }
            if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                Run.fairyObject.moveRight();
                Game.myTimer.setDirection(3);}
            }

        @Override
        public void keyReleased(KeyEvent e) {
            Game.myTimer.setDirection(1);
        }

        @Override
        public void keyTyped(KeyEvent e) {}
    };

    @Override
    public void actionPerformed(ActionEvent e) {}
    public static void music() throws Exception{
        clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(Music));
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }
    
    public static void resetAll(){
        game.Score=0;
        game.Started=false;
        game.level=0;
        game.counter=0;
        game.imageCounter=1;
        fairyObject.roundRec1 = new RoundRectangle2D.Double(60, 918, 80, 30, 2, 2);
        fairyObject.roundRec2 = new RoundRectangle2D.Double(150, 918, 80, 30, 2, 2);
        fairyObject.roundRec3 = new RoundRectangle2D.Double(240, 918, 80, 30, 2, 2);
        fairyObject.X=225;
        fairyObject.Y=825;
        fairyObject.Hp=3;
        story.counter = 0;
        story.count1 = 0;
        story.count2 = 1;
        story.i = 0;
        win.counter=0;
    }
}