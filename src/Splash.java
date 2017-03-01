import de.javasoft.plaf.synthetica.SyntheticaBlueMoonLookAndFeel;
import java.awt.*;
import javax.swing.*;
import java.lang.*;
import javax.swing.UIManager;

public class Splash extends JWindow { 
  private int duration;
  public int status;
  public Splash(int d) {
    duration = d;
	  }

  // A simple little method to show a title screen in the center
  // of the screen for the amount of time given in the constructor
  public void showSplash() {
    JPanel content = (JPanel) getContentPane();
    content.setBackground(Color.white);

    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    setBounds((int)screen.getWidth()/2-120,(int)screen.getHeight()/2-160, 320,170);
	//setLocation(220, 170);

    // Build the splash screen

    JLabel label = new JLabel(new ImageIcon("Image//splash.gif"));
    JLabel sub = new JLabel("   सेल्स परचेस सिसस्टम   ");
    sub.setFont(new Font("Mangal",2,23));
   
	sub.setForeground(Color.RED);
    content.add(sub,BorderLayout.NORTH);
	content.add(label, BorderLayout.CENTER);
    content.setBorder(BorderFactory.createLineBorder(new Color(50,78, 120),5));
	

    // Display it
    setVisible(true);

    // Wait a little while, maybe while loading resources
    try {
      Thread.sleep(duration);
      sub.setForeground(Color.RED);
      sub.setText("     ॐकार ङिस्ट्रीबूयटर्स     ");
      Thread.sleep(duration);
    } catch (Exception e) {
    }

    setVisible(false);
  }

  public void showSplashAndExit() {
    showSplash();
	setVisible(false);
	User1 u=new User1();
    
  }

  public static void main(String[] args) {
	  try
		{
		     //UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");
			//UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
			//UIManager.setLookAndFeel("com.stefankrause.xplookandfeel.XPLookAndFeel");
			////UIManager.setLookAndFeel("de.hillenbrand.swing.plaf.threeD.ThreeDLookAndFeel");
			//UIManager.setLookAndFeel("se.diod.hippo.plaf.HippoLookAndFeel");
			//UIManager.setLookAndFeel("com.Trendy.swing.plaf.TrendyLookAndFeel");
			//UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
			// UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
			//UIManager.setLookAndFeel("net.infonode.gui.laf.InfoNodeLookAndFeel");
			//UIManager.setLookAndFeel("nextlf.plaf.NextLookAndFeel");
			//UIManager.setLookAndFeel(new SyntheticaStandardLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaOrangeMetallicLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaBlackMoonLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaMauveMetallicLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaGreenDreamLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaBlackStarLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaSilverMoonLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaBlueSteelLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaBlueIceLookAndFeel());
			UIManager.setLookAndFeel(new SyntheticaBlueMoonLookAndFeel());
			

		}
		catch(Exception e)
		{
			System.out.println("Success");
		}
    // Throw a nice little title page up on the screen first
    Splash s = new Splash(6000);
    // Normally, we'd call splash.showSplash() and get on with the program.
    // But, since this is only a test...
    s.showSplashAndExit();
	
		
  }
}  
 
 
