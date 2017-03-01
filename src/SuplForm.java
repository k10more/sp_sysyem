//Program  Supplier Form
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.lang.String.*;
import javax.swing.border.*;
import javax.swing.table.*;




 public class SuplForm extends JFrame implements ActionListener,MouseListener
{
	ImageIcon i1= new ImageIcon("Image//new.png");
	ImageIcon i2= new ImageIcon("Image//back.png");
	ImageIcon i3= new ImageIcon("Image//Recycle.png");
	ImageIcon i4= new ImageIcon("Image//filesave.png");
	int flag=1;

	JLabel up=new JLabel(" Updating :");
	ImageIcon i5= new ImageIcon("Image//up1.gif");
	//JLabel upimg=new JLabel(i5);



	JLabel l1=new JLabel("Supplier Information ");
	JButton  add=new JButton("Add",i1);
	JButton  update=new JButton("Update",i4);
	JButton  delete=new JButton("Delete",i3);
	JButton  quit=new JButton(i2);

	JTextField  lb = new JTextField();

	String[] column={"Supplier Id","Supplier Name"," Office Address","Contact No"};
	String[][] row= new String[40][4];
	JTable tbl = new JTable( row,column);
	JScrollPane table=new JScrollPane(tbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	JTextField suplid=new JTextField();

	static Connection con;
	static Statement st;
	static ResultSet rs;
	static Statement st1;
	static ResultSet rs1;
	
	int suplCount=0;
	String s1,s2,s3,s4,t="";
	int s5;
	int i=0;
	
	public SuplForm()
	{	
		super("Supplier Information Form");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(up);
		//c.add(upimg);
		
		c.add(table);
		table.setBounds(5,60,780,380);
		tbl.setAutoResizeMode(5);
		tbl.setRowHeight(25);
		tbl.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(suplid));
		tbl.addMouseListener(this);
		tbl.setFont(new java.awt.Font("Courier New", 1, 12));

		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumn column = null;
		column = tbl.getColumnModel().getColumn(0);
		column.setPreferredWidth(80);
		column = tbl.getColumnModel().getColumn(1);
		column.setPreferredWidth(155);
		column = tbl.getColumnModel().getColumn(2);
		column.setPreferredWidth(435);
		column = tbl.getColumnModel().getColumn(3);
		column.setPreferredWidth(90);
		//column = tbl.getColumnModel().getColumn(4);
		//column.setPreferredWidth(70);
		//column = tbl.getColumnModel().getColumn(5);
		//column.setPreferredWidth(50);

		suplid.setEditable(false);

		c.add(add);
		c.add(update);
		c.add(delete);
		c.add(quit);

		c.add(lb);
		lb.setBounds(0,450,800,5);
		lb.setBackground(new Color(100,200,250));
		lb.setEditable(false);
		
		l1.setBounds(270,20,290,27);
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

		//upimg.setBounds(40,400,150,120);
		//upimg.setVisible(false);

		setSize(800,560);
		setVisible(true);
		setResizable(false);

		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");
			st1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st1.execute("use sales");
			rs1=st1.executeQuery("select Supl_Id, Supl_Status from Supplier");
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st.execute("use sales");
			rs=st.executeQuery("select * from Supplier");
			System.out.println("connected");
		}
		catch(Exception e)
		{
			JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		try
		{
			while(rs.next())
				{
					tbl.setValueAt(rs.getString(1),i,0);
					tbl.setValueAt(rs.getString(2),i,1);
					tbl.setValueAt(rs.getString(3),i,2);
					tbl.setValueAt(rs.getString(4),i,3);
					i++;
					suplCount++;
				}
		}
		catch(Exception e)
		{
			JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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
				if(rowNo<suplCount)
				{
				rs1=st1.executeQuery("select Supl_Id, Supl_Status from Supplier");
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
				//System.out.println("UNABLE TO close  ");
				//System.out.println(se1.getMessage());
			}
		}
		if(e.getSource()==add)
		{
			
			try
			{
				st.close();
        		con.close();
				System.out.println(" close");
			}
			catch(SQLException se1)
			{
				JOptionPane err3=new JOptionPane();
					err3.showMessageDialog(null,se1.getMessage(),"Record Insert Error",JOptionPane.ERROR_MESSAGE);
			}
			setVisible(false);
			SuplAddForm caf= new SuplAddForm("s"+(suplCount+1));
			
		}

		if(e.getSource()==update)
		{
			boolean b=true;
			//up.setVisible(true);
			//upimg.setVisible(true);
			for(int x=0;x<suplCount;x++)
			{
			s1=(String)tbl.getValueAt(x,0);
			s2=(String)tbl.getValueAt(x,1);
			s3=(String)tbl.getValueAt(x,2);
			s4=(String)tbl.getValueAt(x,3);
			
			String no="";
			int spaCnt=0;
			if( s2.equals(no)  ||  s3.equals(no)  || s4.equals(no)  )
			{
				JOptionPane err=new JOptionPane();
				err.showMessageDialog(null,"To update Supplier record you must enter all values in empty field","Record Insert Error",JOptionPane.ERROR_MESSAGE);
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
				if((s2.charAt(m)<65  || s2.charAt(m)>90) &&  s2.charAt(m)!=32  )
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
				if((s4.charAt(m)<48  || s4.charAt(m)>57) ||s4.length()<=7|| s4.length()>14)
				{
					if(s4.charAt(m)<48  || s4.charAt(m)>57)
					{
					JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," You must enter numeric value for Supplier Contact No.","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					flag=1;
					b=false;
					break;
					}
					if(s4.length()<=7|| s4.length()>12)
					{
					JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," You must enter contact no Less than 12 numbers and greater than 8 numbers .","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					flag=1;
					b=false;
					break;
					}
				}
			}
			
			if(b==true)
			{
				s3=s3.toUpperCase();
				try
				{
					String strSql="UPDATE  Supplier	SET   Supl_Name='"+s2+"', Off_Add='"+s3+"',Cont_No='"+s4+"'   WHERE  Supl_Id='"+s1+"' and Supl_Status=0 ";
					st.executeUpdate(strSql);
					flag=0;
					//rs=st.executeQuery("select  *  from  sales.Supplier ");
					System.out.println("updated");
				}
				catch(Exception se1)
				{
					JOptionPane err3=new JOptionPane();
					err3.showMessageDialog(null,se1.getMessage(),"Record Insert Error",JOptionPane.ERROR_MESSAGE);
					System.out.println("unable update");
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
			//upimg.setVisible(false);
	
		}
	
		if(e.getSource()==delete)
		{
			int x=1,rowNo;
			rowNo=tbl.getSelectedRow();
			s1=(String)tbl.getValueAt(rowNo,0);
			System.out.println(rowNo+ "   "+s1);

			if(rowNo==-1 ||  rowNo>suplCount-1)
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
				String strSql1="UPDATE  Supplier	SET  Supl_Status="+x+"  WHERE  Supl_Id='"+s1+"'";
				st.executeUpdate(strSql1);
				delete.setEnabled(false);
				update.setEnabled(false);
				rs=st.executeQuery("select * from sales.Supplier");
				System.out.println("deleted");
				//rs=st.executeQuery("select  *  from  sales.Supplier");	
				}
			}
			catch(Exception se1)
			{
				JOptionPane err3=new JOptionPane();
					err3.showMessageDialog(null,se1.getMessage(),"Record Insert Error",JOptionPane.ERROR_MESSAGE);
			}
			}//end else
		}
	}

	public static void main(String [] agrs)
	{
		
		 SuplForm p=new  SuplForm();
	}
} 

class SuplAddForm extends JFrame  implements ActionListener
{
   ImageIcon i4= new ImageIcon("Image//filesave.png");      
   ImageIcon i5= new ImageIcon("Image//back.png");      

	
	
	JLabel l1=new JLabel("Supplier ID :");
	JTextField suplId =new JTextField();

	JLabel l2=new JLabel("Supplier Name :");
	JTextField suplName=new JTextField();

	JLabel l3=new JLabel("Office Address :");
	JTextField offAdd=new JTextField();

	JLabel l6=new JLabel("Contact Number :");
	JTextField contactNo=new JTextField();

	JButton  save=new JButton("Save",i4);
	JButton  cancel=new JButton(i5);

	JTextField  lb = new JTextField();
	
	JLabel l7=new JLabel("Add New Supplier");

	static Connection con;
	static Statement st;
	static ResultSet rs;

	int custCount=0;
	String s1,s2,s3,s4;
	int s5;
	
	 public SuplAddForm(String count)
	{	
		super("Add Supplier Form");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(suplId);
		c.add(l2);
		c.add(suplName);
		c.add(l3);
		c.add(offAdd);
		c.add(l6);
		c.add(contactNo);
		c.add(save);
		c.add(cancel);
		c.add(l7);

		c.add(lb);
		lb.setBounds(0,250,800,5);
		lb.setBackground(new Color(100,200,250));
		lb.setEditable(false);
		
		l7.setBounds(270,20,270,28);
		l7.setFont(new java.awt.Font("papyrus", 1, 25));
		l7.setForeground(new Color(200,0,0));


		l1.setBounds(10,50,140,25);
		suplId.setBounds(160,50,100,25);
		suplId.setText(count);
		suplId.setEditable(false);
		l1.setFont(new java.awt.Font("Courier New", 1, 13));
		suplId.setFont(new java.awt.Font("Courier New", 1, 13));

		l2.setBounds(10,90,140,25);
		suplName.setBounds(160,90,250,25);
		l2.setFont(new java.awt.Font("Courier New", 1, 13));
		suplName.setFont(new java.awt.Font("Courier New", 1, 13));

		l3.setBounds(10,150,140,25);
		offAdd.setBounds(160,150,630,30);
		l3.setFont(new java.awt.Font("Courier New", 1, 13));
		offAdd.setFont(new java.awt.Font("Courier New", 1, 13));

		l6.setBounds(10,220,140,25);
		contactNo.setBounds(160,220,150,25);
		l6.setFont(new java.awt.Font("Courier New", 1, 13));
		contactNo.setFont(new java.awt.Font("Courier New", 1, 13));

		Border b=BorderFactory.createLineBorder(new Color(100,200,255));
		suplId.setBorder(b);
		suplName.setBorder(b);
		offAdd.setBorder(b);
		contactNo.setBorder(b);

		
        save.setBounds(200,270,115,40);
		cancel.setBounds(520,270,115,40);

		save.addActionListener(this);
		cancel.addActionListener(this);

		save.setFont(new java.awt.Font("Courier New", 1, 13));
		cancel.setFont(new java.awt.Font("Courier New", 1, 13));

		save.setMnemonic('S');
		getRootPane().setDefaultButton(save);
		cancel.setMnemonic('c');

		setLocation(0,160);
		setSize(800,360);
		setVisible(true);
		setResizable(false);
		suplName.requestFocus();

		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=st.executeQuery("select * from sales.supplier");
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
			 SuplForm p=new  SuplForm();
		}
		if(e.getSource()==save)
		{
			s1=suplId.getText();
			s2=suplName.getText();
			s3=offAdd.getText();
			s4=contactNo.getText();
			//s5=contactNo.getText();
			int s5=0;
			String no="";
			int spaCnt=0;
			boolean  b=true;

			if( s2.equals(no)  ||  s3.equals(no)  || s4.equals(no)  )
			{
				JOptionPane err=new JOptionPane();
				err.showMessageDialog(null,"To update Supplier record you must enter all values in empty field","Record Insert Error",JOptionPane.ERROR_MESSAGE);
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
					err2.showMessageDialog(null," Please enter a valid name .","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					b=false;
					suplName.setText("");
					suplName.requestFocusInWindow();
					break;
				}
				else if(spaCnt>4)
				{
					JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," Please enter a valid name . ","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					b=false;
					//suplName.requestFocusInWindow();
					break;
				}
			}
			
			for(int m=0;m<s4.length();m++)
			{
				if((s4.charAt(m)<48  || s4.charAt(m)>57) ||s4.length()<=7||s4.length()>14)
				{
				/*	JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," You must enter numeric value for Supplier Contact No.","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					contactNo.setText("");
					contactNo.requestFocusInWindow();
					b=false;
					break;
				}*/
				if(s4.charAt(m)<48  || s4.charAt(m)>57)
					{
					JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," You must enter numeric value for Supplier Contact No.","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					//flag=1;
					b=false;
					contactNo.setText("");
					contactNo.requestFocusInWindow();
					break;
					}
					if(s4.length()<=7|| s4.length()>12)
					{
					JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," You must enter contact no Less than 12 numbers and greater than 8 numbers .","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					contactNo.setText("");
					contactNo.requestFocusInWindow();
					//flag=1;
					b=false;
					break;
					}
				}
			}
			
			if(b==true)
			{
				s3=s3.toUpperCase();
				try
				{
					String strSql="insert into  sales.Supplier	values('"+s1+"','"+s2+"','"+s3+"','"+s4+"',"+s5+") ";
					st.executeUpdate(strSql);
					rs=st.executeQuery("select  *  from  sales.Supplier ");
					System.out.println("updated insert");
					setVisible(false);	
					SuplForm p=new  SuplForm();
				}
				catch(Exception se1)
				{
					JOptionPane err4=new JOptionPane();
					err4.showMessageDialog(null,se1.getMessage(),"Record Insert Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
	}
}
		