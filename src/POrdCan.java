import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.text.*;


 class POrdCan extends JFrame  implements ActionListener 
{
   ImageIcon i4= new ImageIcon("Image//make.png");      
   ImageIcon i5= new ImageIcon("Image//back.png");      
	JLabel l1=new JLabel("Purchase Order No :");   
	JComboBox PONo =new JComboBox();
	
	JLabel l2=new JLabel("Order Cancel Date :");
	JTextField POcanDate =new JTextField();

	JLabel l3=new JLabel("Reason :");
	JTextField reason =new JTextField(22);
	JButton cmdSave=new JButton("OK",i4);
	JButton cmdCancel=new JButton(i5);

	JTextField  lb = new JTextField();

	static Connection con;
	static Statement st;
	static ResultSet rs;

	java.util.Date date = new java.util.Date();
	DateFormat df = DateFormat.getDateInstance(3);
	String  date1,mon,datex,year,yearx,currentDate;
	int d,d1;


	public POrdCan()
	{	
		super("Purchase Order Cancelation");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(PONo);
		c.add(l2);
		c.add(POcanDate);
		c.add(l3);
		c.add(reason);
		
		c.add(cmdSave);
		c.add(cmdCancel);

		c.add(lb);
		lb.setBounds(0,160,800,5);
		lb.setBackground(new Color(100,200,250));
		lb.setEditable(false);

		l1.setBounds(90,20,180,22);
		PONo.setBounds(260,20,120,22);
		l1.setFont(new java.awt.Font("Courier New", 1, 13));
		PONo.setFont(new java.awt.Font("Courier New", 1, 13));

		l2.setBounds(90,60,180,22);
		POcanDate.setBounds(260,60,120,22);
		POcanDate.setEditable(false);
		l2.setFont(new java.awt.Font("Courier New", 1, 13));
		POcanDate.setFont(new java.awt.Font("Courier New", 1, 13));

		l3.setBounds(90,120,120,22);
		reason .setBounds(260,120,230,22);
		l3.setFont(new java.awt.Font("Courier New", 1, 13));
		reason .setFont(new java.awt.Font("Courier New", 1, 13));
		
        cmdSave.setBounds(90,185,115,32);
		cmdCancel.setBounds(300,185,115,32);
		cmdCancel.setFont(new java.awt.Font("Courier New", 1, 12));
		cmdSave.setFont(new java.awt.Font("Courier New", 1, 12));
		
		cmdSave.addActionListener(this);
		cmdSave.setMnemonic('s');
		
		cmdCancel.addActionListener(this);
		cmdCancel.setMnemonic('o');

		date1=df.format(date);
		d=date1.indexOf('/');
		mon=date1.substring(0,d);
		d1=date1.lastIndexOf('/');
		datex=date1.substring(d+1,d1);
		yearx=date1.substring(d1+1);
		year="20"+yearx;
		currentDate=year+"-"+mon+"-"+datex;
		System.out.println("current date  "+currentDate);

		POcanDate.setText(currentDate);

		PONo.addItem("");
        setSize(550,260);
		setLocation(120,150);
		setResizable(false);
		setVisible(true);
		
		
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st.execute("use sales");
			rs=st.executeQuery("select POrder_No from POrder where POStatus=0   ");
			while(rs.next())
			{
				PONo.addItem(rs.getString(1));
			}
			System.out.println("connected");
		}
		catch(Exception e)
		{
			JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}

	}

	public void actionPerformed(ActionEvent e)
	{
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
				JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,se1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}

		if(e.getSource()==cmdSave)
		{
			//code for saving records
			boolean b=true;
			int no1=PONo.getSelectedIndex();
			String num1=(String)PONo.getSelectedItem();
			System.out.println(num1);
			String num2=(String)reason.getText();
			String OCdate=POcanDate.getText();
			if( num1.equals(""))
			{
				JOptionPane err=new JOptionPane();
				err.showMessageDialog(null,"Please select a Purchase Order.","Empty Field",JOptionPane.WARNING_MESSAGE);
				b=false;
			}
			 else 
			{
				 if(num2.equals(""))
				{
					JOptionPane err1=new JOptionPane();
					err1.showMessageDialog(null,"Please select a reason to cancel the Order.","Empty Field",JOptionPane.WARNING_MESSAGE);
					b=false;
				}
			}
			
			try
			{
				if(b==true)
				{

					String strSql="insert into POCancel values('"+num1+"','"+OCdate+"','"+num2+"' )  ";
					st.executeUpdate(strSql);
					System.out.println("inserted 1");
					String strSql1="UPDATE  POrder	SET   POStatus=2   WHERE  POrder_No='"+num1+"' ";
					st.executeUpdate(strSql1);
					System.out.println("updated " );
					setVisible(false);
				}
			}
			catch(SQLException se1)
			{
				JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,se1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}

public static void main(String [] agrs)
	{		
		POrdCan s=new POrdCan();
	}

}   //close of class User