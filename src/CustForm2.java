// Program for display customer table form

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.lang.String.*;
import javax.swing.border.*;
import javax.swing.Timer;
import javax.swing.table.*;


public //declare class  for display customer information
 class CustForm2 extends JFrame implements ActionListener,MouseListener
{
    ImageIcon i1= new ImageIcon("Image//new.png");
	ImageIcon i2= new ImageIcon("Image//back.png");
	ImageIcon i3= new ImageIcon("Image//Recycle.png");
	ImageIcon i4= new ImageIcon("Image//filesave.png");
	int flag=1;

	// Timer timer = new Timer(500);

	JLabel l1=new JLabel("Customer Information ");
	JButton  add=new JButton("Add",i1);
	JButton  update=new JButton("Update",i4);
	JButton  delete=new JButton("Delete",i3);

	JLabel up=new JLabel(" Updating :");
	ImageIcon i5= new ImageIcon("Image//up1.gif");
	JLabel upimg=new JLabel(i5);
	
	JButton  quit=new JButton(i2);

	JTextField  lb = new JTextField();

	String[] column={"Customer Id","Customer Name","Delivery Address","Contact No"};
	String[][] row= new String[40][5];
	JTable tbl = new JTable( row,column);
	JScrollPane table=new JScrollPane(tbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	JTextField custid=new JTextField();
	JTextField custnm=new JTextField();

	


	static Connection con;
	static Statement st;
	static ResultSet rs;
	static Statement st1;
	static ResultSet rs1;
	

	int custCount=0;  //counter for count number of rows in customer table
	String s1,s2,s3,s4,s5,t="";
	int s6;

	int i=0;

	

	
	public CustForm2()
	{	
		super("Customer Information Table");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
 
		c.add(add);
		c.add(update);
		c.add(delete);
		c.add(quit);

		c.add(up);
		c.add(upimg);

		

		Border b=BorderFactory.createLineBorder(new Color(100,200,255));

		c.add(table);
		table.setBounds(7,60,780,380);
		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbl.setRowHeight(25);
		tbl.setFont(new java.awt.Font("Courier New", 1, 12));
		tbl.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(custid));
		tbl.getColumnModel().getColumn(1).setCellEditor( new DefaultCellEditor(custnm));
		tbl.getColumnModel().getColumn(2).setCellEditor( new DefaultCellEditor(custnm));
		tbl.getColumnModel().getColumn(3).setCellEditor( new DefaultCellEditor(custnm));

		TableColumn column = null;
		column = tbl.getColumnModel().getColumn(0);
		column.setPreferredWidth(80);
		column = tbl.getColumnModel().getColumn(1);
		column.setPreferredWidth(155);
		column = tbl.getColumnModel().getColumn(2);
		column.setPreferredWidth(435);
		column = tbl.getColumnModel().getColumn(3);
		column.setPreferredWidth(90);

		custid.setEditable(false);

		c.add(lb);
		lb.setBounds(0,450,800,5);
		lb.setBackground(new Color(100,200,250));
		lb.setEditable(false);

		l1.setBounds(270,20,270,27);
		l1.setFont(new java.awt.Font("papyrus", 1, 25));
		l1.setForeground(new Color(200,0,0));

		add.setBounds(100,463,145,40);
		update.setBounds(250,463,145,40);
		delete.setBounds(400,463,145,40);
		quit.setBounds(600,463,145,40);			

		add.setFont(new java.awt.Font("Courier New", 1, 13));
		update.setFont(new java.awt.Font("Courier New", 1, 13));
		delete.setFont(new java.awt.Font("Courier New", 1, 13));
		quit.setFont(new java.awt.Font("Courier New", 1, 13));
		
		add.addActionListener(this);
		add.setMnemonic('A');

		update.addActionListener(this);
		update.setMnemonic('u');

		delete.addActionListener(this);
		delete.setMnemonic('D');
		delete.setEnabled(false);
		
		quit.addActionListener(this);
		quit.setMnemonic('b');

		tbl.addMouseListener(this);

		up.setBounds(60,390,120,25);
		up.setFont(new java.awt.Font("Courier New", 1, 15));
		up.setVisible(false);

		upimg.setBounds(40,400,150,120);
		upimg.setVisible(false);
		
		setSize(800,560);
		setVisible(true);
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");
			st1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st1.execute("use sales");
			rs1=st1.executeQuery("select Cust_Id, Cust_Status from Customer");
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st.execute("use sales");
			rs=st.executeQuery("select * from Customer");
			System.out.println("connected");
			
		}
		catch(Exception e)
		{
			JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			//System.out.println("unable to connect  "+e.getMessage());
		}

		try
		{
			while(rs.next())
				{
				//tb1.setValueAt(Fieldname,col,row);
					tbl.setValueAt(rs.getString(1),i,0);
					tbl.setValueAt(rs.getString(2),i,1);
					tbl.setValueAt(rs.getString(4),i,2);
					tbl.setValueAt(rs.getString(5),i,3);
					
					custCount++;
					i++;
				}
			
		}
		catch(Exception se1)
		{
			JOptionPane e2=new JOptionPane();
			e2.showMessageDialog(null,se1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			//System.out.println("unable to add  "+e1.getMessage());
		}

		
	}

	
	public void mouseClicked(MouseEvent me)
	{
		if(me.getSource()==tbl)
		{
			//delete.setEnabled(true); for delete button to be enabled
			int rowNo;
			rowNo=tbl.getSelectedRow();
			String str=(String )tbl.getValueAt(rowNo,0);
			try
			{
				if(rowNo<custCount)
				{
					rs1=st1.executeQuery("select Cust_Id, Cust_Status from Customer");
				    rs1.absolute(rowNo+1);
				//while(rs1.next())
				//{
					//if(rs1.getString(1).equals(str))
						//break;
				//}
				if(rs1.getInt(2)==1)
				{
					delete.setEnabled(false);
					update.setEnabled(false);
				}

				else
				{
					delete.setEnabled(true);
					update.setEnabled(true);
				}
				}
				else
					delete.setEnabled(false);
				rs1.beforeFirst();
			}
			catch(Exception se2)
			{
				JOptionPane e3=new JOptionPane();
				e3.showMessageDialog(null,se2.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				//System.out.println("unable to mouse click  "+e2.getMessage());
			}
		}
		
	} //End of mouseClicked().

	public void mouseEntered(MouseEvent me)
	{
		
	}

	public void mouseExited(MouseEvent me)
	{
		
	}


	public void mousePressed(MouseEvent me)
	{
				
	}

	public void mouseReleased(MouseEvent me)
	{
		
	}

	public void actionPerformed(ActionEvent e)
	{
		//code for exit button
		if(e.getSource()==quit)
		{
			setVisible(false);
			try
			{
				st.close();
        		con.close();
			}
			catch(SQLException se3)
			{
				JOptionPane e4=new JOptionPane();
				e4.showMessageDialog(null,se3.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				//System.out.println("UNABLE TO close  ");
				//System.out.println(se1.getMessage());
			}
		}

		

		if(e.getSource()==update)
		{
			//tbl.clearSelection();
			custnm.setEditable(false);
			boolean b=true;
			
			
			
			
			for(int x=0;x<custCount;x++)
			{
			s1=(String)tbl.getValueAt(x,0);
			s2=(String)tbl.getValueAt(x,1);
			//s3=(String)tbl.getValueAt(x,2);
			s4=(String)tbl.getValueAt(x,2);
			s5=(String)tbl.getValueAt(x,3);
			String no="";
			int spaCnt=0;
			
			
			if( s2.equals(no) || s4.equals(no) || s5.equals(no) )
			{
				JOptionPane err=new JOptionPane();
				err.showMessageDialog(null,"To update Customer record you must enter all values in empty field","Record Insert Error",JOptionPane.ERROR_MESSAGE);
				flag=1;
				b=false;

			}
			//Validations 
			for(int m=0;m<s2.length();m++)
			{
				s2=s2.toUpperCase();
				if(s2.charAt(m)==32)
				{
					spaCnt++;
				}
				if((s2.charAt(m)<65  || s2.charAt(m)>90) &&  s2.charAt(m)!=32  )
				{                         
					JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," Please enter a valid name for Customer Id '"+s1+"'","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					b=false;
					flag=1;
					break;
				}
				else if(spaCnt>4)
				{
					JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," Please enter a valid name for Customer Id '"+s1+"' ","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					b=false;
					flag=1;
					break;
				}
			}
			for(int m=0;m<s5.length();m++)
			{
				
				//if((s5.charAt(m)<48  || s5.charAt(m)>57) || s5.length()>14)
				if((s5.charAt(m)<48  || s5.charAt(m)>57)||s5.length()<=7||s5.length()>12)
				{
					if(s5.length()<=7||s5.length()>12)
					{
						JOptionPane err211=new JOptionPane();
						err211.showMessageDialog(null," You must enter the more than 8 No and Less than 12 No.","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
						b=false;
						flag=1;
						break;
					}
					if(s5.charAt(m)<48  || s5.charAt(m)>57)
					{
					JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," You must enter numeric value Contact No.","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					b=false;
					flag=1;
					break;
					}
				}
			}

			if(b==true)
			{
				//s3=s3.toUpperCase();
				s4=s4.toUpperCase();
				
				
				try
				{
					//String s6=s5;
					
					String strSql="UPDATE  Customer	SET   Cust_Name='"+s2+"', Off_Add='"+s3+"',Del_Add='"+s4+"',Cont_No='"+s5+"'   WHERE  Cust_Id='"+s1+"'  and Cust_Status=0 ";
					st.executeUpdate(strSql);
					flag=0;
					System.out.println("updated");					
				}
				catch(Exception se1)
				{
					JOptionPane err3=new JOptionPane();
					err3.showMessageDialog(null,se1.getMessage(),"Record Insert Error",JOptionPane.ERROR_MESSAGE);
					System.out.println("unable update  ");
				}
			}
			
			}//end for
			
			if(flag==0)
			{
			JOptionPane opt123=new JOptionPane();
			opt123.showMessageDialog(null," Records Updated Sucessfully","Update",JOptionPane.INFORMATION_MESSAGE);
			
			}
			System.out.println("ok");
			custnm.setEditable(true);
			
		}

		if(e.getSource()==add)
		{
			setVisible(false);
			
			try
			{
				st.close();
        		con.close();
			}
			catch(SQLException se4)
			{
				JOptionPane e5=new JOptionPane();
				e5.showMessageDialog(null,se4.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			CustAddForm2 caf= new CustAddForm2("c"+(custCount+1));
		}
		
		

		if(e.getSource()==delete)
		{
			int x=1,rowNo;
			rowNo=tbl.getSelectedRow();
			s1=(String)tbl.getValueAt(rowNo,0);
			System.out.println(rowNo+ "   "+s1);

			if(rowNo==-1 ||  rowNo>custCount-1)
			{
				JOptionPane err3=new JOptionPane();
				err3.showMessageDialog(null," No record is selected.","No Selection",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
			try
			{
				JOptionPane cof=new JOptionPane();
				if(cof.showConfirmDialog(null,"Do you want to remove selected record ?","Delete Confirmation ",JOptionPane.YES_NO_OPTION)==cof.YES_OPTION)
				{
				String strSql1="UPDATE  Customer SET Cust_Status="+x+"  WHERE  Cust_Id='"+s1+"'";
				st.executeUpdate(strSql1);
				delete.setEnabled(false);
				rs=st.executeQuery("select  *  from  Customer ");
				System.out.println("deleted");
				}
			}
			catch(Exception se5)
			{
				JOptionPane e6=new JOptionPane();
				e6.showMessageDialog(null,se5.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				//System.out.println("unable delete  "+se9.getMessage());
			}
			}
			
		}
	}

	public static void main(String [] agrs)
	{
		 CustForm2 p=new  CustForm2();
	}
} 


class CustAddForm2 extends JFrame  implements ActionListener
{
   ImageIcon i4= new ImageIcon("Image//filesave.png");      
   ImageIcon i5= new ImageIcon("Image//back.png");      


	JLabel l7=new JLabel("Add New Customer ");
	
	JLabel l1=new JLabel("Customer ID     :");
	JTextField custId=new JTextField();

	JLabel l2=new JLabel("Customer Name   :");
	JTextField custName=new JTextField();

	JLabel l3=new JLabel("Office Address :");
	JTextField offAdd=new JTextField();

	JLabel l4=new JLabel("Delivery Address :");
	JTextField delAdd=new JTextField();

	JLabel l6=new JLabel("Contact Number :");
	JTextField contactNo=new JTextField();

	JButton  save=new JButton("Save",i4);
	JButton  cancel=new JButton(i5);

	JTextField  lb = new JTextField();

	static Connection con;
	static Statement st;
	static ResultSet rs;

	String s1,s2,s3,s4,s5;
	int s6;

	
	CustAddForm2(String count)
	{	
		super("Add New Customer ");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(custName);
		//c.add(offAdd);
		c.add(delAdd);
		c.add(contactNo);
		c.add(custId);

		c.add(l1);
		c.add(l2);
		//c.add(l3);
		c.add(l4);
		c.add(l6);
		
		l7.setBounds(270,20,270,27);
		l7.setFont(new java.awt.Font("papyrus", 1, 25));
		l7.setForeground(new Color(200,0,0));
		c.add(l7);
		
		c.add(save);
		c.add(cancel);

		Border b=BorderFactory.createLineBorder(new Color(100,200,255));

		c.add(lb);
		lb.setBounds(0,250,800,5);
		lb.setBackground(new Color(100,200,250));
		lb.setEditable(false);

		
		//custName.requestFocusInWindow();
		//custName.requestFocus();

		l1.setBounds(10,50,140,25);
		custId.setBounds(160,50,100,25);
		custId.setText(count);
		custId.setEditable(false);
		l1.setFont(new java.awt.Font("Courier New", 1, 13));
		custId.setFont(new java.awt.Font("Courier New", 1, 13));
		custId.setBorder(b);
		
		l2.setBounds(10,90,140,25);
		custName.setBounds(160,90,250,25);
		l2.setFont(new java.awt.Font("Courier New", 1, 13));
		custName.setFont(new java.awt.Font("Courier New", 1, 13));
		custName.setBorder(b);

		
		
		l4.setBounds(10,150,150,25);
		delAdd.setBounds(160,150,630,30);
		l4.setFont(new java.awt.Font("Courier New", 1, 13));
		delAdd.setFont(new java.awt.Font("Courier New", 1, 13));
		delAdd.setBorder(b);
		
		l6.setBounds(10,220,150,25);
		contactNo.setBounds(160,220,180,25);
		l6.setFont(new java.awt.Font("Courier New", 1, 13));
		contactNo.setFont(new java.awt.Font("Courier New", 1, 13));
		contactNo.setBorder(b);

		save.setBounds(200,270,115,40);
		cancel.setBounds(520,270,115,40);
	
		save.setFont(new java.awt.Font("Courier New", 1, 13));
		cancel.setFont(new java.awt.Font("Courier New", 1, 13));

		save.addActionListener(this);
		cancel.addActionListener(this);

		

		save.setMnemonic('S');
		getRootPane().setDefaultButton(save);
		cancel.setMnemonic('c');

		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=st.executeQuery("select * from sales.Customer");
			st.execute("use sales");
			System.out.println("connected");
		}
		catch(Exception e)
		{
			JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			//System.out.println("unable to connect");
		}
		setLocation(0,160);
		setSize(800,360);
		
		//pack();
		setVisible(true);
		custName.requestFocus();
		//custName.addKeyListener(this);
		//setResizable(false);
	}		

	

	public void actionPerformed(ActionEvent e)
	{
		//code for exit button
		if(e.getSource()==cancel)
		{
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
			CustForm2 p=new  CustForm2();
		}
		if(e.getSource()==save)
		{
			s1=custId.getText();
			s2=custName.getText();
			//s3=offAdd.getText();
			s3=null;
			s4=delAdd.getText();
			s5=contactNo.getText();
			int s6=0;
			String temp,no="";
			boolean  b=true;
			int spaCnt=0;
			//Validations
			if( s2.equals(no)  ||  s4.equals(no)  ||  s5.equals(no)  )
			{
				JOptionPane err=new JOptionPane();
				err.showMessageDialog(null,"To update customer record you must enter all values in empty field","Record Insert Error",JOptionPane.ERROR_MESSAGE);
				b=false;

			}
			
			for(int m=0;m<s2.length();m++)
			{
				s2=s2.toUpperCase();
				if(s2.charAt(m)==32)
				{
					spaCnt++;
				}
				if((s2.charAt(m)<65  || s2.charAt(m)>90) &&  s2.charAt(m)!=32  )
				{                         
					JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," Please enter a valid name","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					custName.setText("");
					custName.requestFocusInWindow();
					b=false;
					break;
				}
				else if(spaCnt>4)
				{
					JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," Please enter a valid name","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					b=false;
					break;
				}
			}
			
			
			for(int m=0;m<s5.length();m++)
			{
				if((s5.charAt(m)<48  || s5.charAt(m)>57)||s5.length()<=7||s5.length()>12)
				{
					if(s5.charAt(m)<48  || s5.charAt(m)>57)
					{
					System.out.println("3");
					JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," You must enter numeric value for Contact No.","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					contactNo.setText("");
					contactNo.requestFocusInWindow();
					b=false;
					break;
					}
					if(s5.length()<=7||s5.length()>12)
					{
					System.out.println("3");
					JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," You must enter contact no Less than 12 numbers and greater than 8 numbers .","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					contactNo.setText("");
					contactNo.requestFocusInWindow();
					b=false;
					break;
					}
				}

			}
			
			if(b==true)
			{
				try
				{
					
					//s3=s3.toUpperCase();
					s4=s4.toUpperCase();
					String strSql="insert into Customer	values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"',"+s6+" ) ";
					st.executeUpdate(strSql);
					rs=st.executeQuery("select  *  from  Customer ");
					System.out.println("updated");
					setVisible(false);	
					CustForm2 p=new  CustForm2();
				}
				catch(Exception se2)
				{
					JOptionPane err4=new JOptionPane();
					err4.showMessageDialog(null,se2.getMessage(),"Record Insert Error",JOptionPane.ERROR_MESSAGE);
					System.out.println("unable update");
				}
			}
		}


	}
}
