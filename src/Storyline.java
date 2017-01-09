import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import javax.swing.text.*;

public class Storyline extends JPanel {
    
    static JPanel main = new JPanel(new BorderLayout());
    static JPanel main1 = new JPanel();
    static JPanel main2 = new JPanel(new BorderLayout());
    static JPanel main3 = new JPanel(new GridLayout(2,10));
    static JPanel chatPanel = new JPanel(new BorderLayout());
    static JPanel imagePanel = new JPanel(new GridBagLayout());
    static JLabel pictureLabel = new JLabel();
    static JLabel characterLabel = new JLabel();
    static JLabel NextButton = new JLabel();
    static JLabel PrevButton = new JLabel();
    static JTextPane chat = new JTextPane();
    
    static ImageIcon black = new ImageIcon("black.png");
    static ImageIcon nextTriangle = new ImageIcon("nextTriangle.png");
    static ImageIcon prevTriangle = new ImageIcon("prevTriangle.png");
    static ImageIcon fairyKing = new ImageIcon("kingdom.png");
    static ImageIcon background1 = new ImageIcon("darkcave.png");
    static ImageIcon background2 = new ImageIcon("darkwoods.png");
    static ImageIcon background3 = new ImageIcon("darkwoods2.png");
    static ImageIcon background4 = new ImageIcon("darkwoods3.png");
    static ImageIcon background5 = new ImageIcon("darkwoods4.png");
    static ImageIcon background6 = new ImageIcon("darkwoods5.png");
    static ImageIcon background7 = new ImageIcon("darkwoods6.png");
    static ImageIcon king = new ImageIcon("king4.png");
    static ImageIcon fairy1 = new ImageIcon("21.png");
    static ImageIcon fairy2 = new ImageIcon("19.png");
    static ImageIcon fairy3 = new ImageIcon("17.png");
    static ImageIcon fairy4 = new ImageIcon("middle.png");
    static ImageIcon fairy5 = new ImageIcon("right2.png");
    static ImageIcon witch1 = new ImageIcon("2.png");
    static ImageIcon witch2 = new ImageIcon("1.png");
    static ImageIcon witch3 = new ImageIcon("33.png");   
    static ImageIcon[] images1 = {black, black, fairyKing, king, background1, fairy2,black,black,background2, fairy1,background2,
        witch1,background3,fairy3,background4,fairy4,background4,witch2, background4, fairy4,background4, witch2,background4,fairy4, 
        background4,witch2,background4,fairy4,background5,witch3,background6,fairy1,background7,fairy5,black,black};
    static Font font = new Font("Chalkboard", Font.PLAIN, 20);
    static int counter = 0, count1 = 0, count2 = 1 , i = 0;;
    static String[] chatConvo = {"Once Upon A Time....", "The Fairy king, Oberon ordered her young and little daughter, "
                    + "Faye to bring back some fluffy candies from Fluffyland. "
                    + "After collecting her delicious candies, she made her way back home "
                    + "to the Great Fairy Kingdom, Faylinn... ", 
                    "The sun has set... light began to fade.... a while later it became dark... "
                    + "Faye became desperate as she ran through "
                    + "the woods searching for an exit as she fears the rumours of the Jealous Witch "
                    + "living in the woods, summoning terrifying and dangerous monsters that lurks inside the woods.. ", 
                    "*Amidst her journey.. the Jealous Witch suddenly appeared!*",
                    "Faye: Arghhh!!!",
                    "Jealous Witch : YOUNG LITTLE FAIRY! Thou shall die to my treacherous monsters!",
                    "Faye: Leave me alone! "
                    +"\n*Faye runs towards the witch and knocks into her*" 
                    + "\n*Jealous Witch falls down and her mask fell off*",
                    "Faye: SISTER DEAR?",
                    "Jealous Witch: YOUR SISTER IS LONG GONE!",
                    "Faye: What happened to you?!",
                    "Jealous Witch: IT IS NONE OF YOUR CONCERN!",
                    "Faye: I am your little sister! I love you! I miss you my dear sister! Please come home with me!",
                    "Jealous Witch: If you loved me, YOU WOULD HAVE NEVER LEFT ME HERE TO ROT TILL THIS DAY!",
                    "Faye: But you disappeared suddenly from the castle! Father was really sad and worried!",
                    "Jealous Witch: SPEAK NO MORE! It is time for you to meet your doom!",
                    "Faye: Noooooooooooooooooooo!",
                    "*Faye managed to escape the witch and desperately tries to find her way home*",
                    "*Thus, her escape began..*"};
    
    public Storyline(){
        
        setLayout(new FlowLayout());
        chatPanel.setPreferredSize(new Dimension(540,200));
        main1.setPreferredSize(new Dimension(540,200));
        main2.setPreferredSize(new Dimension(540,560));
        main3.setPreferredSize(new Dimension(540,200));
        imagePanel.setPreferredSize(new Dimension(120,100));
        imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        imagePanel.setBackground(Color.BLACK);
        chat.setPreferredSize(new Dimension(420,100));
        chat.setBackground(Color.BLACK);
        chat.setForeground(Color.WHITE);
        chat.setEditable(false);
        chat.setFont(font);
        PrevButton.setIcon(prevTriangle);
        NextButton.setIcon(nextTriangle);
        for (int i=0; i<21; i++){
            if (i==20)
                main3.add(PrevButton);
            else
                main3.add(new JLabel(""));
        }
        main3.add(NextButton);

        chat.setEditorKit(new MyEditorKit());
        SimpleAttributeSet attrs=new SimpleAttributeSet();
        StyleConstants.setAlignment(attrs,StyleConstants.ALIGN_CENTER);
        StyledDocument doc=(StyledDocument)chat.getDocument();
        doc.setParagraphAttributes(0,doc.getLength()-1,attrs,false);
   
         
        main1.setBackground(Color.BLACK);
        main2.setBackground(Color.BLACK);
        main3.setBackground(Color.BLACK);

        add(main);
        main.add(main1, BorderLayout.NORTH);
        main.add(main2, BorderLayout.CENTER);
        main.add(main3, BorderLayout.SOUTH);
        main2.add(chatPanel, BorderLayout.SOUTH);
        main2.add(pictureLabel, BorderLayout.CENTER);
        chatPanel.add(imagePanel);
        chatPanel.add(chat, BorderLayout.EAST);
        imagePanel.add(characterLabel);
        
        //listener
        NextButton.addMouseListener(new Mouse());
        PrevButton.addMouseListener(new Mouse());
        this.addKeyListener(new Story());
    }
    //next story
    public static void NextStory(){
        updateGame();
        if(i==18){
            Run.frame.remove(Run.story);
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
        else{
            pictureLabel.setIcon(images1[count1]);
            characterLabel.setIcon(images1[count2]);
            chat.setText(chatConvo[i]);
            count1+=2;
            count2+=2;
            i++;
            
            if(i==4 && i==18){
                main2.setBackground(Color.BLACK);
            }
        }
    }
    //previous story
    public void PrevStory(){
        
        if (i>0){
            i--;
            count1-=2;
            count2-=2; 

            if(i==4 && i==18)
               main2.setBackground(Color.BLACK);

            pictureLabel.setIcon(images1[count1]);
            characterLabel.setIcon(images1[count2]);
            chat.setText(chatConvo[i]);
        }

    }
    //mouse listener
    class Mouse implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1){
                if (e.getSource() == NextButton)
                    NextStory();
            }
            if (e.getButton() == MouseEvent.BUTTON1)
                if (e.getSource() == PrevButton)
                    PrevStory();
        }

        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }
    
    class Story implements KeyListener{
        @Override
        public void keyPressed(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                NextStory();
                //Run.fairyObject.setFocusable(false);
                Run.front.setFocusable(false);
                Run.story.setFocusable(true);
                Run.story.requestFocusInWindow();
            }
            if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                PrevStory();
                 Run.front.setFocusable(false);
                 Run.story.setFocusable(true);
                 Run.story.requestFocusInWindow();
            }
        }
        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyReleased(KeyEvent e) {}
    };
    class MyEditorKit extends StyledEditorKit {
    public ViewFactory getViewFactory() {
        return new StyledViewFactory();
    }
    class StyledViewFactory implements ViewFactory {

        public View create(Element elem) {
            String kind = elem.getName();
            if (kind != null) {
                if (kind.equals(AbstractDocument.ContentElementName)) {

                    return new LabelView(elem);
                } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                    return new ParagraphView(elem);
                } else if (kind.equals(AbstractDocument.SectionElementName)) {

                    return new CenteredBoxView(elem, View.Y_AXIS);
                } else if (kind.equals(StyleConstants.ComponentElementName)) {
                    return new ComponentView(elem);
                } else if (kind.equals(StyleConstants.IconElementName)) {

                    return new IconView(elem);
                }
            }
 
            return new LabelView(elem);
        }

    }
}
    class CenteredBoxView extends BoxView {
    public CenteredBoxView(Element elem, int axis) {

        super(elem,axis);
    }
        protected void layoutMajorAxis(int targetSpan, int axis, int[] offsets, int[] spans) {

            super.layoutMajorAxis(targetSpan,axis,offsets,spans);
            int textBlockHeight = 0;
            int offset = 0;

            for (int i = 0; i < spans.length; i++) {

                textBlockHeight = spans[i];
            }
            offset = (targetSpan - textBlockHeight) / 2;
            for (int i = 0; i < offsets.length; i++) {
                offsets[i] += offset;
            }
        }
    }
    
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    
    public static void updateGame(){
        Run.game.Score=0;
        Run.game.Started=false;
        Run.game.level=0;
        Run.game.counter=0;
        Run.game.imageCounter=1;
        Run.fairyObject.roundRec1 = new RoundRectangle2D.Double(60, 918, 80, 30, 2, 2);
        Run.fairyObject.roundRec2 = new RoundRectangle2D.Double(150, 918, 80, 30, 2, 2);
        Run.fairyObject.roundRec3 = new RoundRectangle2D.Double(240, 918, 80, 30, 2, 2);
        Run.fairyObject.X=225;
        Run.fairyObject.Y=825;
        Run.fairyObject.Hp=3;
    }
}