
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class OyunEkranı extends JFrame {
    
    private int mGenislik=800;
    private int mYukseklik=800;
    
    

    public OyunEkranı(String string) throws HeadlessException {
        super(string);
        SetDimension( mGenislik, mYukseklik);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(false);
        
        
        
        
        
    }
    
    public static void main(String[] args) {
        
        OyunEkranı ekran=new OyunEkranı("Uzay Oyunu");
        
        
        
         
        Oyun oyun=new Oyun();
        
        
        
        oyun.requestFocus();     //klavye işlemlerinin anlaşılması.
        oyun.addKeyListener(oyun); 
        
        oyun.setFocusable(true);
        oyun.setFocusTraversalKeysEnabled(false);
        
        ekran.add(oyun);
        
        ekran.setVisible(true);
        
        
    }
    public void SetDimension(int genislik,int yukseklik){   //başlatıldığı zaman ekranı tam ortaya getirmek için oluşturdum.
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        
        int posX=0;
        int posY=0;
        
        posX=(dim.width-mGenislik)/2;
        posY=(dim.height-mYukseklik)/2;
        
        setBounds(posX, posY, mGenislik, mYukseklik);
        
    }
    
    
    
    
    
}

