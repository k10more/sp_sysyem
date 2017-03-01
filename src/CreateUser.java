//Create User Form
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.io.*;
import javax.swing.border.*;

public class CreateUser extends JFrame  implements ActionListener 
{
	
	 ImageIcon i5= new ImageIcon("Image//can.png");  
	 ImageIcon i6= new ImageIcon("Image//nike.png");
	ImageIcon i7=new ImageIcon("Image//user.png");
	JLabel l1=new JLabel("Select User Type :");   
	JComboBox cboUser =new JComboBox();

	JLabel l2=new JLabel("Password for new user:");
	JPasswordField txtPassword1=new JPasswordField();

	JLabel l22=new JLabel("Retype Password :");
	JPasswordField txtPassword2=new JPasswordField();

	JLabel l4=new JLabel("Username :");
	JTextField username=new JTextField();

	JButton cmdCreate=new JButton("Create",i6);
	JButton cmdCancel=new JButton("Cancel",i5);

	JLabel l32=new JLabel(i7);

	static Connection con;
	static Statement st;
	static ResultSet rs;

	String uname,pass1,pass2;
	int userType;

	public CreateUser()
	{	
		super("Create New User");
		Container c=getContentPane();
		c.setLayout(null);
		c.add(l32);
		c.add(l1);
		c.add(cboUser);
		c.add(l2);
		c.add(txtPassword1);
		c.add(l22);
		c.add(txtPassword2);
		c.add(cmdCreate);
		c.add(cmdCancel);
		c.add(l4);
		c.add(username);

		l32.setBounds(0,0,48,48);
		l1.setBounds(50,40,180,22);
		cboUser.setBounds(230,40,150,25);
		l1.setFont(new java.awt.Font("Courier New", 1, 13));
		cboUser.setFont(new java.awt.Font("Courier New", 1, 13));

		l4.setBounds(50,100,180,22);
		username.setBounds(230,100,160,25);
		l4.setFont(new java.awt.Font("Courier New", 1, 13));
		username.setFont(new java.awt.Font("Courier New", 1, 13));

		l2.setBounds(50,160,180,22);
		txtPassword1.setBounds(230,160,160,25);
		l2.setFont(new java.awt.Font("Courier New", 1, 13));
		txtPassword1.setFont(new java.awt.Font("Courier New", 1, 13));

		l22.setBounds(50,220,180,22);
		txtPassword2.setBounds(230,220,160,25);
		l22.setFont(new java.awt.Font("Courier New", 1, 13));
		txtPassword2.setFont(new java.awt.Font("Courier New", 1, 13));

		txtPassword1.setEchoChar('*');
		txtPassword2.setEchoChar('*');

		cmdCreate.setBounds(80,280,140,40);
		cmdCancel.setBounds(250,280,140,40);
		cmdCreate.setFont(new java.awt.Font("Courier New", 1, 13));
		cmdCancel.setFont(new java.awt.Font("Courier New", 1, 13));
		
		cmdCreate.addActionListener(this);
		cmdCreate.setMnemonic('c');
		getRootPane().setDefaultButton(cmdCreate);
		
		cmdCancel.addActionListener(this);
		cmdCancel.setMnemonic('n');

		cboUser.addItem("Administrator");
		cboUser.addItem("Purchase");
		cboUser.addItem("Sales");

		Border b=BorderFactory.createLineBorder(new Color(100,200,255));
		//oldPassword.setBorder(b);
		username.setBorder(b);
		txtPassword1.setBorder(b);
		txtPassword2.setBorder(b);
		
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st.execute("use sales");
			//rs=st.executeQuery("select * from sales.Login");
			System.out.println("connected");
		}
		catch(Exception e)
		{
			JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			//System.out.println("unable to connect");
		}
		
		setLocation(190,100);
		setSize(450,390);
		setVisible(true);
		setResizable(false);
	}

	public void actionPerformed(ActionEvent e)
	{
		// code for click on  ok button
		if(e.getSource()==cmdCreate)
		{
			try
			{
				userType=cboUser.getSelectedIndex();
				uname=(String)username.getText();
				pass1=new String(txtPassword1.getPassword());
				pass2=new String(txtPassword2.getPassword());
				String no="";
				boolean b=true;
				if( uname.equals(no)  ||  pass1.equals(no)  || pass2.equals(no))
				{
					JOptionPane err=new JOptionPane();
					err.showMessageDialog(null," Please fill all the fields to create a new user.","Empty Field",JOptionPane.ERROR_MESSAGE);
					b=false;
				}
				if(!(pass1.equals(pass2)))
				{
					JOptionPane err1=new JOptionPane();
					err1.showMessageDialog(null," Retype Passwords do not match.","Wrong Password",JOptionPane.ERROR_MESSAGE);
					txtPassword1.setText("");
					txtPassword2.setText("");
					txtPassword1.requestFocusInWindow();
					b=false;
				}
				if(b==true)
				{
					String strSql="insert  into  Login  values('"+uname+"','"+pass1+"',"+(userType+1)+")"  ;
					st.executeUpdate(strSql);
					JOptionPane user=new JOptionPane();
					user.showMessageDialog(null,"  User has been created successfully.","New User",JOptionPane.INFORMATION_MESSAGE);
					System.out.println("created");
					setVisible(false);
					st.close();
        			con.close();
				}
			}
		
			catch(Exception se)
			{
				JOptionPane e2=new JOptionPane();
				e2.showMessageDialog(null,se.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				//System.out.println("UNABLE TO CONNECT");
				//System.out.println(se.getMessage());
			}
	
		
		}
		if(e.getSource()==cmdCancel)
		{
			setVisible(false);
			try
			{
				st.close();
        		con.close();
			}
			catch(SQLException se1)
			{
				JOptionPane e5=new JOptionPane();
				e5.showMessageDialog(null,se1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				///System.out.println("UNABLE TO close");
				///System.out.println(se1.getMessage());
			}
		}
	}
	public static void main(String[] args) 
	{
		
		CreateUser cu = new CreateUser();
		System.out.println("Hello World!");
	}
}
