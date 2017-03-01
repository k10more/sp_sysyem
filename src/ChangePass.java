import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.io.*;
import javax.swing.border.*;

public class ChangePass extends JFrame  implements ActionListener 
{
    ImageIcon i6= new ImageIcon("Image//can.png"); 
	ImageIcon i5= new ImageIcon("Image//save.png");
	ImageIcon i7= new ImageIcon("Image//user.png");
	
	JLabel l7=new JLabel(i7);   

	JLabel l1=new JLabel("Username :");   
	JTextField username=new JTextField();

	JLabel l2=new JLabel("New Password :");
	JPasswordField txtPassword1=new JPasswordField();

	JLabel l22=new JLabel("Retype Password :");
	JPasswordField txtPassword2=new JPasswordField();

	JLabel l3=new JLabel("Old Password :");
	JPasswordField oldPassword=new JPasswordField();

	JButton cmdChange=new JButton("Change",i5);
	JButton cmdCancel=new JButton("Cancel",i6);

	static Connection con;
	static Statement st;
	static Statement st1;
	static ResultSet rs;

	String uname,oldPass,pass1,pass2;

	public ChangePass()
	{	
		super("Change Password");
		Container c=getContentPane();
		c.setLayout(null);
		c.add(l7);
		c.add(l1);
		c.add(username);
		c.add(l2);
		c.add(txtPassword1);
		c.add(l22);
		c.add(txtPassword2);
		c.add(l3);
		c.add(oldPassword);
		c.add(cmdChange);
		c.add(cmdCancel);
		
		l7.setBounds(0,0,48,48);
		l3.setBounds(60,100,150,25);
		oldPassword.setBounds(208,100,150,25);
		l3.setFont(new java.awt.Font("Courier New", 1, 13));
		oldPassword.setFont(new java.awt.Font("Courier New", 1, 13));

		l1.setBounds(60,40,150,25);
		username.setBounds(208,40,150,25);
		l1.setFont(new java.awt.Font("Courier New", 1, 13));
		username.setFont(new java.awt.Font("Courier New", 1, 13));

		l2.setBounds(60,160,150,25);
		txtPassword1.setBounds(208,160,150,25);
		l2.setFont(new java.awt.Font("Courier New", 1, 13));
		txtPassword1.setFont(new java.awt.Font("Courier New", 1, 13));

		l22.setBounds(60,220,150,25);
		txtPassword2.setBounds(208,220,150,25);
		l22.setFont(new java.awt.Font("Courier New", 1, 13));
		txtPassword2.setFont(new java.awt.Font("Courier New", 1, 13));


		txtPassword1.setEchoChar('$');
		txtPassword2.setEchoChar('$');
		oldPassword.setEchoChar('$');

		cmdChange.setBounds(80,280,140,40);
		cmdCancel.setBounds(250,280,140,40);
		cmdChange.setFont(new java.awt.Font("Courier New", 1, 13));
		cmdCancel.setFont(new java.awt.Font("Courier New", 1, 13));
		
		cmdChange.addActionListener(this);
		cmdChange.setMnemonic('c');
		getRootPane().setDefaultButton(cmdChange);
		
		cmdCancel.addActionListener(this);
		cmdCancel.setMnemonic('n');

		Border b=BorderFactory.createLineBorder(new Color(100,200,255));
		oldPassword.setBorder(b);
		username.setBorder(b);
		txtPassword1.setBorder(b);
		txtPassword2.setBorder(b);

		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");
			st1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st1.execute("use sales");
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st.execute("use sales");
			rs=st.executeQuery("select * from Login");
			System.out.println("connected");
		}
		catch(Exception e)
		{
			JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			//System.out.println("unable to connect");
		}
		
		setLocation(170,70);
		setSize(450,400);
		setVisible(true);
		setResizable(false);
	}

	public void actionPerformed(ActionEvent e)
	{
		// code for click on  ok button
		if(e.getSource()==cmdChange)
		{
			try
			{
				uname=(String)username.getText();
				oldPass=new String(oldPassword.getPassword());
				pass1=new String(txtPassword1.getPassword());
				pass2=new String(txtPassword2.getPassword());
				String no="";
				boolean b=true;
				boolean c=false;

				if( uname.equals(no)  ||  oldPass.equals(no)||  pass1.equals(no)  || pass2.equals(no))
				{
					JOptionPane err=new JOptionPane();
					err.showMessageDialog(null," Please fill all fields to change the password.","Empty Fields",JOptionPane.ERROR_MESSAGE);
					b=false;
				}
				rs.beforeFirst();
				while(rs.next())
				{
					if(uname.equals(rs.getString(1))  &&  oldPass.equals(rs.getString(2)))
					{
						c=true;
					}
					/*
					else
					{
						c=false;
						JOptionPane err2=new JOptionPane();
						err2.showMessageDialog(null," you enter Wrong Username or Old Password.","wrong Data",JOptionPane.ERROR_MESSAGE);
						break;
					}
					*/
				}	
				if(c==false)
				{
					JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," you have entered Wrong Username or Old Password.","wrong Data",JOptionPane.ERROR_MESSAGE);
					username.setText("");
					oldPassword.setText("");
					username.requestFocusInWindow();
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
				
				
				
				if(b==true && c==true)
				{
					System.out.println("ok");
					String strSql=" update  Login  set Password='"+pass1+"'  where  Username='"+uname+"' and  Password='"+oldPass+"'  "  ;
					st1.executeUpdate(strSql);
					System.out.println("changed");
					setVisible(false);
					try
					{
						st.close();
        				con.close();
					}
					catch(SQLException se1)
					{
						JOptionPane e2=new JOptionPane();
						e2.showMessageDialog(null,se1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
						//System.out.println("UNABLE TO close");
						//System.out.println(se1.getMessage());
					}
				}
			}
		
			catch(Exception se)
			{
				JOptionPane e4=new JOptionPane();
				e4.showMessageDialog(null,se.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				//System.out.println("UNABLE TO change");
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
	
		ChangePass cp = new ChangePass();
		System.out.println("Hello World!");
	}
}
