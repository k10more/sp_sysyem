import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import javax.swing.border.*;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaMauveMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaGreenDreamLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSilverMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueSteelLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueIceLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueMoonLookAndFeel;

//import javax.swing.BorderFactory;

//declare class  for checking usernamr and password
 class User1 extends JFrame  implements ActionListener
{
	JPanel panel;
	//creating label and textbox for username
	ImageIcon i1= new ImageIcon("Image//key.png");      
	ImageIcon i2= new ImageIcon("Image//can.png");
	ImageIcon i3= new ImageIcon("Image//user.png"); 
	ImageIcon i4= new ImageIcon("Image//loading.gif");
	 
	JLabel l4=new JLabel(i3); 
	//JButton l4=new JButton(i3);
	JLabel l5=new JLabel(i4);
	JLabel l1=new JLabel("UserName :");   
	JTextField txtUser =new JTextField();
	
	//creating label and textbox for password
	JLabel l2=new JLabel("Password :");
	JPasswordField txtPassword=new JPasswordField();
	
	//creatingbuttons for checkingz
	
	JButton cmdOk=new JButton("Ok   ",i1);
	JButton cmdCancel=new JButton("Cancel",i2);

	String un,pwd;
	int login;
	String temp;
	static Connection con;
	static Statement st;
	static ResultSet rs;
	
	User1()
	{	
		super("Password Checking");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(txtUser);
		c.add(l2);
		c.add(txtPassword);
		c.add(cmdOk);
		c.add(cmdCancel);
		c.add(l4);
		c.add(l5);
		Border border=BorderFactory.createLineBorder(new Color(100,200,255));
		l5.setBounds(240,190,50,10);
		l4.setBounds(0,0,40,40);
		l1.setBounds(40,35,100,25);
		txtUser.setBounds(140,35,170,25);
		txtUser.setBorder(border);
		l5.setVisible(false);
		l2.setBounds(40,90,100,25);
		txtPassword.setBounds(140,90,170,25);
		txtPassword.setBorder(border);

		l1.setFont(new java.awt.Font("Courier New", 1, 15));
		l2.setFont(new java.awt.Font("Courier New", 1, 15));
		txtUser.setFont(new java.awt.Font("Courier New", 1, 13));
		txtPassword.setFont(new java.awt.Font("Courier New", 1, 12));
	
		cmdOk.setBounds(50,150,105,35);
		cmdCancel.setBounds(190,150,105,35);
		//cmdOk.addMouseListener(this);
		cmdOk.addActionListener(this);
		cmdOk.setMnemonic('o');
		
		
		cmdCancel.addActionListener(this);
		cmdCancel.setMnemonic('c');

		cmdOk.setFont(new java.awt.Font("Courier New", 1, 14));
		cmdCancel.setFont(new java.awt.Font("Courier New", 1, 14));

		getRootPane().setDefaultButton(cmdOk);
		txtPassword.setEchoChar('$');

		//l4.setBounds(330,40,80,54);

		setSize(350,240);
		 Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int)screen.getWidth()/2-120,(int)screen.getHeight()/2-160);
		setResizable(false);
		setVisible(true);

		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//JFrame.setDefaultLookAndFeelDecorated(true);
		//setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 6));

		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=st.executeQuery("select * from sales.Login");
			System.out.println("connected");
			
			
		}
		catch(Exception e)
		{
			JOptionPane e3=new JOptionPane();
			e3.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			//System.out.println("unable to connect");
		}
	}

	//methods for action events
	public void actionPerformed(ActionEvent e)
{
		if(e.getSource()==cmdCancel)
		{
			System.exit(0);
			try
			{
				st.close();
        		con.close();
			}
			catch(SQLException se1)
			{
				JOptionPane e3=new JOptionPane();
				e3.showMessageDialog(null,se1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				//System.out.println("UNABLE TO close");
				//System.out.println(se1.getMessage());
			}
		}
		if(e.getSource()==cmdOk)
		{
			
			boolean b=false;
			try
			{
				
				un=(String)txtUser.getText();
				pwd=new String(txtPassword.getPassword());
				
				System.out.println("Hello dude");

				String no="";
				if( un.equals(no)  ||  pwd.equals(no))
				{
					l5.setVisible(false);
					JOptionPane err=new JOptionPane();
					err.showMessageDialog(null,"Please,Enter valid Username and Password","Error",JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					while(rs.next())
					{
						System.out.println("Hello");
						if (un.equals(rs.getString(1)) && pwd.equals(rs.getString(2)))
						{
							//JOptionPane.showMessageDialog(null, " ", " Welcome-"+un, JOptionPane.INFORMATION_MESSAGE, i4);
                                                        b=true;
							break;
						}
					}
					if(b==true)
					{
					System.out.println("Valid login");
					rs=st.executeQuery("select LDept_No , Dept_Name  from sales.Login , sales.Department   where Username='"+un+"'  and  Password='"+pwd+"'  and  Department.Dept_No=Login.LDept_No ");
					rs.next();
					login=rs.getInt(1);
					temp=rs.getString(2);
					MainForm m = new MainForm(login,un);
					setVisible(false);
					try
					{
						st.close();
        				con.close();
						System.out.println(" close");
					}
					catch(SQLException se1)
					{
						System.out.println("UNABLE TO close");
						System.out.println(se1.getMessage());
					}
					System.out.println("Dept_No=" +login+ "\t Dept_Name=" +temp);
					}
					else
					{
						try
						{
						JOptionPane err1=new JOptionPane();
						err1.showMessageDialog(null,"  You have entered wrong Username or Password.  \n  Please check for capitalization of character","Wrong Password",JOptionPane.ERROR_MESSAGE);
						System.out.println("wrong login");
						
						
						txtPassword.setText("");
						//txtUser.setFocusable(true);
						txtUser.setText("");
						txtUser.requestFocusInWindow();
						rs.beforeFirst();
						
						}
						catch(Exception se4)
						{
							JOptionPane e1=new JOptionPane();
							e1.showMessageDialog(null,se4.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
							//System.out.println("UNABLE TO before first");
							//System.out.println(se2.getMessage());
						}
				
					}
				}
			}
			catch(SQLException see)
			{
				JOptionPane e2=new JOptionPane();
				e2.showMessageDialog(null,see.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				//System.out.println("UNABLE TO run while");
				//System.out.println(see.getMessage());
			}
			
		}
	}

	public static void main(String [] agrs)
	{
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		try
		{
			
			//UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");
			//UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
			//UIManager.setLookAndFeel("com.stefankrause.xplookandfeel.XPLookAndFeel");
			//UIManager.setLookAndFeel("org.fife.plaf.Office2003.Office2003LookAndFeel");
			//UIManager.setLookAndFeel("de.hillenbrand.swing.plaf.threeD.ThreeDLookAndFeel");
			//UIManager.setLookAndFeel("se.diod.hippo.plaf.HippoLookAndFeel");
			//UIManager.setLookAndFeel("com.Trendy.swing.plaf.TrendyLookAndFeel");
			//UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
			// UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
			//UIManager.setLookAndFeel("net.infonode.gui.laf.InfoNodeLookAndFeel");
			//UIManager.setLookAndFeel("nextlf.plaf.NextLookAndFeel");
			//UIManager.setLookAndFeel(new SyntheticaStandardLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaOrangeMetallicLookAndFeel());
			UIManager.setLookAndFeel(new SyntheticaBlackMoonLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaMauveMetallicLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaGreenDreamLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaBlackStarLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaSilverMoonLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaBlueSteelLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaBlueIceLookAndFeel());
			//UIManager.setLookAndFeel(new SyntheticaBlueMoonLookAndFeel());
			
			
	     
		}
		catch(Exception e)
		{
			System.out.println("unable");
		}
		User1 u=new User1();
	}

}   //close of class User

