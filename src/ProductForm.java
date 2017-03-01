// Program for customer table form

//package customer;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
import java.lang.*;
import javax.swing.plaf.basic.*;
import javax.swing.border.*;
import javax.swing.table.*;

 class ProductForm extends JFrame implements ActionListener,MouseListener
{

    ImageIcon i1= new ImageIcon("Image//new.png");
	ImageIcon i2= new ImageIcon("Image//back.png");
	ImageIcon i3= new ImageIcon("Image//Recycle.png");
	ImageIcon i4= new ImageIcon("Image//filesave.png");
	int flag=1;
	JLabel up=new JLabel(" Updating ");
	ImageIcon i5= new ImageIcon("Image//up1.gif");
	JLabel upimg=new JLabel(i5);

	
	JLabel l1=new JLabel("Product Information ");
	JButton  add=new JButton("Add",i1);
	JButton  update=new JButton("Update",i4);
	JButton  delete=new JButton("Delete",i3);
	JButton  quit=new JButton(i2);

	JTextField  lb = new JTextField();

	String[] column={"Product Id","Product Name","Description","Purchase Price","Sales Price",""};
	String[][] row= new String[40][6];
	JTable tbl = new JTable( row,column);
	JScrollPane table=new JScrollPane(tbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	JTextField proid=new JTextField();
	JTextField netcontents=new JTextField();

	static Connection con;
	static Statement st;
	static ResultSet rs;
	static Statement st1;
	static ResultSet rs1;
	
	
	String s1,s2,s3,s4,s5,s6,t="";
	int i=0;


	int proCount;
	
	ProductForm()
	{	
		super("Product Information Table");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(add);
		c.add(update);
		c.add(delete);
		c.add(quit);

		//c.add(up);
		c.add(upimg);

		c.add(table);
		table.setBounds(20,60,760,380);
		tbl.setAutoResizeMode(5);
		tbl.setRowHeight(25);

		tbl.setFont(new java.awt.Font("Courier New", 1, 12));

		tbl.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(proid));
		tbl.getColumnModel().getColumn(5).setCellEditor( new DefaultCellEditor(netcontents));
		tbl.addMouseListener(this);
		proid.setEditable(false);
		netcontents.setEditable(false);

		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumn column = null;
		column = tbl.getColumnModel().getColumn(0);
		column.setPreferredWidth(90);
		column = tbl.getColumnModel().getColumn(1);
		column.setPreferredWidth(200);
		column = tbl.getColumnModel().getColumn(2);
		column.setPreferredWidth(240);
		column = tbl.getColumnModel().getColumn(3);
		column.setPreferredWidth(110);
		column = tbl.getColumnModel().getColumn(4);
		column.setPreferredWidth(120);
		column = tbl.getColumnModel().getColumn(5);
		column.setPreferredWidth(0);

		c.add(lb);
		lb.setBounds(0,450,800,5);
		lb.setBackground(new Color(100,200,250));
		lb.setEditable(false);
		
		l1.setBounds(270,20,290,25);
		l1.setFont(new java.awt.Font("papyrus", 1, 23));
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

		up.setBounds(60,390,120,25);
		up.setFont(new java.awt.Font("Courier New", 1, 15));
		up.setVisible(false);

		upimg.setBounds(40,400,150,120);
		upimg.setVisible(false);
		setResizable(false);

		setSize(800,560);
		setVisible(true);
		
		//code for db connection
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");
			st1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st1.execute("use sales");
			rs1=st1.executeQuery("select Pro_Id, Pro_Status from Product");
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st.execute("use sales");
			rs=st.executeQuery("select * from Product");
			System.out.println("connected");
		}
		catch(Exception e)
		{
			JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			//System.out.println("unable to connect");
		}
		
		
		try
		{
			while(rs.next())
				{
					
					tbl.setValueAt(rs.getString(1),i,0);
					tbl.setValueAt(rs.getString(2),i,1);
					tbl.setValueAt(rs.getString(3),i,2);
					tbl.setValueAt(rs.getString(4),i,3);
					tbl.setValueAt(rs.getString(5),i,4);
					tbl.setValueAt(rs.getString(6),i,5);
					i++;

					proCount++;
				}
				
		}
		catch(Exception e)
		{
			JOptionPane e2=new JOptionPane();
			e2.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			//System.out.println("unable to add  "+e1.getMessage());
		}
		


	}		


	public void mouseClicked(MouseEvent me)
	{
		if(me.getSource()==tbl)
		{
			int rowNo;
			rowNo=tbl.getSelectedRow();

			String str=(String )tbl.getValueAt(rowNo,0);
			try
			{
				if(rowNo<proCount)
				{
				rs1=st1.executeQuery("select Pro_Id, Pro_Status from Product");
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
			catch(Exception e2)
			{
				JOptionPane e3=new JOptionPane();
				e3.showMessageDialog(null,e2.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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
			}
		}
		if(e.getSource()==add)
		{
			setVisible(false);
			try
			{
				st.close();
        		con.close();
			}
			catch(SQLException se1)
			{
				JOptionPane err3=new JOptionPane();
				err3.showMessageDialog(null,se1.getMessage(),"Record Insert Error",JOptionPane.ERROR_MESSAGE);
			}
			AddProduct ap = new AddProduct("p"+(proCount+1));
		}

		if(e.getSource()==update)
		{
			boolean b=true;
			up.setVisible(true);
			//upimg.setVisible(true);
			for(int x=0;x<proCount;x++)
			{
			s1=(String)tbl.getValueAt(x,0);
			s2=(String)tbl.getValueAt(x,1);
			s3=(String)tbl.getValueAt(x,2);
			s4=(String)tbl.getValueAt(x,3);
			s5=(String)tbl.getValueAt(x,4);
			String no="";
			int spaCnt=0;

			if( s2.equals(no)  ||  s3.equals(no)  || s4.equals(no)  ||  s5.equals(no)  )
			{
				JOptionPane err=new JOptionPane();
				err.showMessageDialog(null,"To update customer record you must enter all values in empty field","Record Insert Error",JOptionPane.ERROR_MESSAGE);
				b=false;
				flag=1;

			}

			for(int m=0;m<s2.length();m++)
			{
				s2=s2.toUpperCase();
				if(s2.charAt(m)==32)
				{
					spaCnt++;
				}
				if((s2.charAt(m)<65  || s2.charAt(m)>90 ) && s2.charAt(m)!=32 && s2.charAt(m)!=46 && (s2.charAt(m)<48  || s2.charAt(m)>57 ))
				{                         
					JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," Please enter a valid name for Product Id '"+s1+"'","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					b=false;
					flag=1;
					break;
				}
				else if(spaCnt>4)
				{
					JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," Please enter a valid name for Product Id '"+s1+"' ","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					b=false;
					flag=1;
					break;
				}
			}
			
			for(int m=0;m<s4.length();m++)
			{
				if((s4.charAt(m)<48  || s4.charAt(m)>57)  &&  s4.charAt(m)!=46 )
				{
					JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," You must enter numeric value for Purchase Price","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					b=false;
					flag=1;
					break;
				}
			}
			for(int m=0;m<s5.length();m++)
			{
				if((s5.charAt(m)<48  || s5.charAt(m)>57)  && s5.charAt(m)!=46 )
				{
					JOptionPane err3=new JOptionPane();
					err3.showMessageDialog(null," You must enter numeric value for Sales Price","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					b=false;
					flag=1;
					break;
				}
			}
			
			if(b==true)
			{
				s3=s3.toUpperCase();
				try
				{
					String strSql="UPDATE  Product  SET  Pro_Name='"+s2+"', Discr='"+s3+"',Cost_Price="+s4+",Sales_Price="+s5+"   WHERE  Pro_Id='"+s1+"' and pro_Status=0 ";
					st.executeUpdate(strSql);
					rs=st.executeQuery("select  *  from  sales.Product ");
					flag=0;
					System.out.println("updated");
				}
				catch(Exception se2)
				{
					JOptionPane err3=new JOptionPane();
					err3.showMessageDialog(null,se2.getMessage(),"Record Insert Error",JOptionPane.ERROR_MESSAGE);
					//System.out.println("unable update   "+se1.getMessage());
				}
			}
			}//end for

			if(flag==0)
			{
			JOptionPane opt123=new JOptionPane();
			opt123.showMessageDialog(null," Records Updated Sucessfully","Update",JOptionPane.INFORMATION_MESSAGE);
			
			}
			else
			{
			/*JOptionPane opt123=new JOptionPane();
			opt123.showMessageDialog(null," plz enter correct data","Update",JOptionPane.INFORMATION_MESSAGE);
			*/
			}
			up.setVisible(false);
			upimg.setVisible(false);
	
		}


		
		if(e.getSource()==delete)
		{
			int x=1,rowNo;
			rowNo=tbl.getSelectedRow();
			s1=(String)tbl.getValueAt(rowNo,0);
			System.out.println(s1);
			if(rowNo==-1 ||  rowNo>proCount-1)
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
				String strSql1="UPDATE  sales.Product 	SET  Pro_Status="+x+"  WHERE  Pro_Id='"+s1+"'";
				st.executeUpdate(strSql1);
				delete.setEnabled(false);
				update.setEnabled(false);
				rs=st.executeQuery("select * from Product");
				System.out.println("deleted");
				}
			}
			catch(Exception se5)
			{
				JOptionPane e6=new JOptionPane();
				e6.showMessageDialog(null,se5.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			}//end of else
		}
	}
	public static void main(String [] agrs)
	{
		
		ProductForm pf=new ProductForm();
		
	}
	
}


//product form for adding new product


class AddProduct extends JFrame implements ActionListener
{
   ImageIcon i4= new ImageIcon("Image//filesave.png");      
   ImageIcon i5= new ImageIcon("Image//back.png");      


	JLabel l7=new JLabel("Add New Product ");
	
	JLabel l1=new JLabel("Product ID   :");
	JTextField productId =new JTextField();

	JLabel l2=new JLabel("Product Name :");
	JTextField productName=new JTextField();

	JLabel l3=new JLabel("Description :");
	JTextField desc=new JTextField();

	JLabel l4=new JLabel("Purchase Price :");
	JTextField costPrice=new JTextField();

	JLabel l5=new JLabel("Sales Price :");
	JTextField salesPrice=new JTextField();

	JButton  save=new JButton("Save",i4);
	JButton  cancel=new JButton(i5);

	JTextField  lb = new JTextField();

	static Connection con;
	static Statement st;
	static ResultSet rs;
	
	String s1,s2,s3,s4,s5,s6,procnt;

	int proCount;
	
	AddProduct(String count)
	{	
		super("Product Information Table");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(productId);
		c.add(l2);
		c.add(productName);
		c.add(l3);
		c.add(desc);
		c.add(l4);
		c.add(costPrice);
		c.add(l5);
		c.add(salesPrice);
		c.add(l7);
		c.add(save);
		c.add(cancel);

		l7.setBounds(270,20,270,27);
		l7.setFont(new java.awt.Font("papyrus", 1, 23));
		l7.setForeground(new Color(200,0,0));
		c.add(l7);
	
		c.add(lb);
		lb.setBounds(0,280,800,5);
		lb.setBackground(new Color(100,200,250));
		lb.setEditable(false);
		
		l1.setBounds(10,50,140,25);
		productId.setBounds(160,50,100,25);
		productId.setText(count);
		productId.setEditable(false);
		l1.setFont(new java.awt.Font("Courier New", 1, 13));
		productId.setFont(new java.awt.Font("Courier New", 1, 13));

		l2.setBounds(10,110,140,25);
		productName.setBounds(160,110,180,25);
		l2.setFont(new java.awt.Font("Courier New", 1, 13));
		productName.setFont(new java.awt.Font("Courier New", 1, 13));

		l3.setBounds(10,160,140,25);
		desc.setBounds(160,160,400,30);
		l3.setFont(new java.awt.Font("Courier New", 1, 13));
		desc.setFont(new java.awt.Font("Courier New", 1, 13));

		l4.setBounds(10,210,140,25);
		costPrice.setBounds(160,210,120,25);
		l4.setFont(new java.awt.Font("Courier New", 1, 13));
		costPrice.setFont(new java.awt.Font("Courier New", 1, 13));

		l5.setBounds(10,250,140,25);
		salesPrice.setBounds(160,250,120,25);
		l5.setFont(new java.awt.Font("Courier New", 1, 13));
		salesPrice.setFont(new java.awt.Font("Courier New", 1, 13));

		Border b=BorderFactory.createLineBorder(new Color(100,200,255));
		productId.setBorder(b);
		productName.setBorder(b);
		desc.setBorder(b);
		costPrice.setBorder(b);
		salesPrice.setBorder(b);
		//netcontents.setBorder(b);

				
		save.setBounds(200,290,115,40);
		cancel.setBounds(520,290,115,40);
		
		save.addActionListener(this);
		save.setMnemonic('s');

		cancel.addActionListener(this);
		cancel.setMnemonic('c');

		save.setFont(new java.awt.Font("Courier New", 1, 13));
		cancel.setFont(new java.awt.Font("Courier New", 1, 13));

		setLocation(0,160);
		setSize(800,370);
		setVisible(true);
		setResizable(false);
		productName.requestFocus();

		//code for db connection
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st.execute("use sales");
			rs=st.executeQuery("select * from Product");
			System.out.println("connected");
		}
		catch(Exception e)
		{
			JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}

		procnt=count;


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
			}
			ProductForm pf=new ProductForm();	
		}
		if(e.getSource()==save)
		{
			s1=productId.getText();
			s2=productName.getText();
			s3=desc.getText();
			s4=costPrice.getText();
			s5=salesPrice.getText();
			//s6=(String)netcontents.getSelectedItem();
			s6=null;
			int y=0;
			String no="";
			int spaCnt=0;
			boolean  b=true;

			if( s2.equals(no)  ||  s3.equals(no)  || s4.equals(no)  ||  s5.equals(no)  )
			{
				JOptionPane err=new JOptionPane();
				err.showMessageDialog(null,"To add new product  you must enter all values in empty fields","Record Insert Error",JOptionPane.ERROR_MESSAGE);
				b=false;

			}

			for(int m=0;m<s2.length();m++)
			{
				s2=s2.toUpperCase();
				if(s2.charAt(m)==32)
				{
					spaCnt++;
				}
				if((s2.charAt(m)<65  || s2.charAt(m)>90 ) && s2.charAt(m)!=32 && s2.charAt(m)!= 46 && (s2.charAt(m)<48  || s2.charAt(m)>57 ))
				{                         
					JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," Please enter a valid name","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					b=false;
					productName.setText("");
					productName.requestFocusInWindow();
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
			
			for(int m=0;m<s4.length();m++)
			{
				if((s4.charAt(m)<48  || s4.charAt(m)>57)  &&  s4.charAt(m)!=46 )
				{
					JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," You must enter numeric value for Purchase Price","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					b=false;
					costPrice.setText("");
					costPrice.requestFocusInWindow();
					break;
				}
			}
			for(int m=0;m<s5.length();m++)
			{
				if((s5.charAt(m)<48  || s5.charAt(m)>57)  && s5.charAt(m)!=46 )
				{
					JOptionPane err3=new JOptionPane();
					err3.showMessageDialog(null," You must enter numeric value for Sales Price","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					b=false;
					salesPrice.setText("");
					salesPrice.requestFocusInWindow();
					break;
				}
			}
			
			if(b==true)
			{
				s3=s3.toUpperCase();
				try
				{
					String strSql="insert into  Product	values('"+s1+"','"+s2+"','"+s3+"',"+s4+","+s5+",'"+s6+"',"+y+" ) ";
					String strSql1="insert into  Inventory	values('"+s1+"',0,0,0,0) ";
					st.executeUpdate(strSql);
					st.executeUpdate(strSql1);
					st.execute("alter table POrder add "+procnt+" int");
					System.out.println("Altered");
					st.execute("alter table PInvoice add "+procnt+" int");
					st.execute("alter table PMRet add "+procnt+" int");
					st.execute("alter table Quotation add "+procnt+" int");
					st.execute("alter table SOrder add "+procnt+" int");
					st.execute("alter table SInvoice add "+procnt+" int");
					st.execute("alter table SMRet add "+procnt+" int");
					System.out.println("vbvbvb");
					//rs=st.executeQuery("select  *  from  Product ");
					System.out.println("updated");
					setVisible(false);
					ProductForm pf=new ProductForm();
				}
				catch(Exception se2)
				{
					JOptionPane err4=new JOptionPane();
					err4.showMessageDialog(null,se2.getMessage(),"Record Insert Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}
}
