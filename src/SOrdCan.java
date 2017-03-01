import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.text.*;


 class SOrdCan extends JFrame  implements ActionListener 
{
	ImageIcon i4= new ImageIcon("Image//make.png");      
	ImageIcon i5= new ImageIcon("Image//back.png");      

	
	
	JLabel l1=new JLabel("Sales Order No :");   
	JComboBox SoNo =new JComboBox();
	
	JLabel l2=new JLabel("Order Cancel Date :");
	JTextField SOcanDate =new JTextField();

	JLabel l3=new JLabel("Reason:");
	JTextField reason =new JTextField(20);

	JButton cmdSave=new JButton("OK",i4);
	JButton cmdCancel=new JButton("Cancel",i5);
	//JButton print=new JButton("Print");

	JTextField  lb = new JTextField();

	java.util.Date date = new java.util.Date();
	DateFormat df = DateFormat.getDateInstance(3);
	String  date1,mon,datex,year,yearx,currentDate;
	int d,d1;

	static Connection con;
	static Statement st;
	static ResultSet rs;

	public SOrdCan()
	{	
		super("Sales Order Cancelation");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(SoNo);
		c.add(l2);
		c.add(SOcanDate);
		c.add(l3);
		c.add(reason);
		
		//c.add(print);
		c.add(cmdSave);
		c.add(cmdCancel);

		c.add(lb);
		lb.setBounds(0,160,800,5);
		lb.setBackground(new Color(100,200,250));
		lb.setEditable(false);

		date1=df.format(date);
		d=date1.indexOf('/');
		mon=date1.substring(0,d);
		d1=date1.lastIndexOf('/');
		datex=date1.substring(d+1,d1);
		yearx=date1.substring(d1+1);
		year="20"+yearx;
		currentDate=year+"-"+mon+"-"+datex;
		System.out.println("current date  "+currentDate);

		SOcanDate.setText(currentDate);

		


		l1.setBounds(90,20,180,22);
		 SoNo.setBounds(260,20,120,22);
		 l1.setFont(new java.awt.Font("Courier New", 1, 13));
		SoNo .setFont(new java.awt.Font("Courier New", 1, 13));

		l2.setBounds(90,60,180,22);
		SOcanDate.setBounds(260,60,120,22);
		SOcanDate.setEditable(false);
		l2.setFont(new java.awt.Font("Courier New", 1, 13));
		SOcanDate .setFont(new java.awt.Font("Courier New", 1, 13));

		l3.setBounds(90,120,120,22);
		reason .setBounds(260,120,230,22);
		l3.setFont(new java.awt.Font("Courier New", 1, 13));
		reason .setFont(new java.awt.Font("Courier New", 1, 13));

		SoNo.addItem("");

		/*reason.addItem("");
		reason.addItem("Late Delivery");
		reason.addItem("Extra  Stock");
		reason.addItem("Less Demand");*/
		
        cmdSave.setBounds(90,185,130,32);
		//print.setBounds(200,220,95,32);
		cmdCancel.setBounds(300,185,130,32);
		cmdSave.setFont(new java.awt.Font("Courier New", 1, 13));
		cmdCancel .setFont(new java.awt.Font("Courier New", 1, 13));
		
		cmdSave.addActionListener(this);
		cmdSave.setMnemonic('s');
		
		cmdCancel.addActionListener(this);
		cmdCancel.setMnemonic('o');

		//print.addActionListener(this);
		//print.setMnemonic('p');

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
			rs=st.executeQuery("select SOrder_No from SOrder where SOStatus=0   ");

			while(rs.next())
			{
				SoNo.addItem(rs.getString(1));
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
			int no1=SoNo.getSelectedIndex();
			String num1=(String)SoNo.getSelectedItem();
			System.out.println(num1);
			//int no2=reason.getSelectedIndex();
			String num2=(String)reason.getText();
			String OCdate=SOcanDate.getText();
			if(  num1.equals(""))
			{
				JOptionPane err=new JOptionPane();
				err.showMessageDialog(null,"Please select a Sales Order.","Empty Field",JOptionPane.WARNING_MESSAGE);
				b=false;
			}
			 else 
			{
				 if( num2.equals(""))
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

					String strSql="insert into SOCancel values('"+num1+"','"+OCdate+"','"+num2+"' )  ";
					st.executeUpdate(strSql);
					System.out.println("inserted 1");

					String strSql1="UPDATE  SOrder	SET   SOStatus=2   WHERE  SOrder_No='"+num1+"' ";
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
		
		SOrdCan s=new SOrdCan();
	}

}   //close of class User