
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


class Ates{
   private int x;
   private int y;

    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
   
   
}

public class Oyun extends JPanel implements KeyListener,ActionListener {
    Timer timer=new Timer(1,this);
    
    Random random=new Random();
    
    
    
    
    private int gecen_sure=0;
    private int harcanan_ates=0;
    private int puan=0;
    
    private BufferedImage image;
    private ArrayList<Ates> atesler=new ArrayList<Ates>();
    
    private int atesdirY=1;
    private int topX1=600;
    private int topX2=0;
    
    
    private int uzayGemisiY=600;
    private int dirUzayY=30;
    
    private int topdirX1=2;
     private int topdirX2=10;
    
    private int uzayGemisiX=0;
    
    private int dirUzayX=20;
    
    String message="sarı topu vurarak puan kazanmaya çalış eğer kırmızı topa vurursan kaybedersin...";
    
    
    

    public Oyun(){
        
        JOptionPane.showMessageDialog(this, message);
        
        try {
            image=ImageIO.read(new FileImageInputStream(new File("arac.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        setBackground(Color.black);
        timer.start();
        
        
        
        
        
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        gecen_sure+=5;
        g.setColor(Color.red);
        g.fillOval(topX1, 0, 25, 25);
        g.setColor(Color.yellow);
        g.fillOval(topX2, 30, 25, 25);
        
        g.drawImage(image, uzayGemisiX, uzayGemisiY, image.getWidth() / 4 ,image.getHeight() / 4 ,this);
        
         g.setColor(Color.blue);
       
        for(Ates ates:atesler){
           g.fillRect(ates.getX(), ates.getY(), 10, 20);
        }
        
        for(Ates ates:atesler){
            if(ates.getY()<0){
                atesler.remove(ates);
            }
        }
        
         for(Ates ates:atesler){
            if(new Rectangle(ates.getX(),ates.getY() ,10 ,20).intersects(new Rectangle(topX2, 30, 10, 20))){
                puan++;
                            
            }
            
        }
         
        if(top1carpısmaKontrol()){
            timer.stop();
            String message="kaybettiniz..\n"+
                    "harcanan ateş : " + harcanan_ates +
                    "\n geçen süre : " + gecen_sure/1000.0+
                    "\n kazanılan puan : "+puan ;
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
            
        }
        
        
        
        
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
        
    }
    
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c=e.getKeyCode();
        if(c==KeyEvent.VK_LEFT){
           if(uzayGemisiX<=0){
               uzayGemisiX=0;
           } 
           else{
               uzayGemisiX -=dirUzayX;
           }
        
    }
        else if(c==KeyEvent.VK_RIGHT){
            if(uzayGemisiX>=720){
                uzayGemisiX=720;
            }
            else{
                uzayGemisiX +=dirUzayX;
            }
            
        }
        
        if(c==KeyEvent.VK_UP){
           if(uzayGemisiY<=0){
               uzayGemisiY=0;
           } 
           else{
               uzayGemisiY -=dirUzayY;
           }
        
    }
        if(c==KeyEvent.VK_DOWN){
            if(uzayGemisiY>=700){
                uzayGemisiY=700;
            }
            else{
                uzayGemisiY +=dirUzayY;
            }
        }
        else if(c==KeyEvent.VK_CONTROL){
            atesler.add(new Ates(uzayGemisiX+43,uzayGemisiY));
            
            harcanan_ates++;
            
        }
        
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
           
        topX1 +=topdirX1 ;
        
        if(topX1>=750){
            topdirX1= -topdirX1;
        }
       if(topX1<=0) {
           topdirX1= -topdirX1;
          
       }
       
       topX2 +=topdirX2 ;
        
        if(topX2>=750){
            topdirX2= -topdirX2;
        }
       if(topX2<=0) {
           topdirX2= -topdirX2;
          
       }
       
       for(Ates ates:atesler){
          ates.setY(ates.getY()-atesdirY);
          
        }
       
       
       repaint();
       
    }
    public boolean top1carpısmaKontrol(){
        for(Ates ates:atesler){
            if(new Rectangle(ates.getX(),ates.getY() ,10 ,20).intersects(new Rectangle(topX1, 0, 25, 25))){
                return true;
                            
            }
            
        }
        return false;
    }
    
    
    
    
    
    
    
}
