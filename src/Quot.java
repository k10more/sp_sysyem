import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.text.*;
import javax.swing.border.*;
import javax.swing.table.*;

 class Quot extends JFrame  implements ActionListener 
{
	ImageIcon i4= new ImageIcon("Image//new.png");      
	ImageIcon i5= new ImageIcon("Image//back.png");      



	JLabel l1=new JLabel("Quotation No:");   
	JComboBox QuoNo =new JComboBox();
	
	JLabel l2=new JLabel("Quotn. Date:");
	JTextField QDate =new JTextField();

	JLabel l3=new JLabel("Customer Name :");
	JTextField QCust =new JTextField();

	//JLabel l6=new JLabel("Sales Tax :");
	//JTextField STax =new JTextField("0.00");

	JLabel l5=new JLabel("Total Order Amount :");
	JTextField TAmt =new JTextField("0.00");

	JTextField  lb = new JTextField();
	JTextField lb1=new JTextField();

	String[][] row= new String[40][5];
	String[] column={" Id ","Product Name","Price","Quantity","Amount"};
	JTable QuoTbl= new JTable(row,column);
	JScrollPane table=new JScrollPane(QuoTbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JButton cmdNew=new JButton("New",i4);
	JButton cmdCancel=new JButton("Cancel",i5);
	
	JLabel l8=new JLabel("Status :");
	JTextField  status = new JTextField();

	JLabel l9=new JLabel("Product  Information  ");

	JTextField pid =new JTextField();

	static Connection con;
	static Statement st;
	static ResultSet rs;
	static ResultSetMetaData rsmd;
	static Statement st1;
	static ResultSet rs1;
	static ResultSetMetaData rsmd1;
	static Statement st3;
	static ResultSet rs3;
	static Statement st4;
	static ResultSet rs4;
	static Statement st5;
	static ResultSet rs5;

	int quoCount=0,i=0;


	public Quot()
	{	
		super("Quotation");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(QuoNo);
		c.add(l2);
		c.add(QDate);
		c.add(l3);
		c.add(QCust);
		c.add(l5);
        c.add(TAmt);
		//c.add(l6);
       // c.add(STax);
		c.add(l8);
		c.add(status);
		c.add(table);
		c.add(cmdNew);
		c.add(cmdCancel);
		c.add(l9);

		c.add(lb);
		lb.setBounds(552,0,5,395);
		lb.setBackground(new Color(100,200,250));
		lb.setEditable(false);
		
		c.add(lb1);
		lb1.setBounds(0,390,800,5);
		lb1.setBackground(new Color(100,200,250));
		lb1.setEditable(false);

		l1.setBounds(580,60,120,22);
		QuoNo.setBounds(680,60,100,22);
		QuoNo.addItem("");
		l1.setFont(new java.awt.Font("Courier New", 1, 13));
		QuoNo.setFont(new java.awt.Font("Courier New", 1, 13));

		l2.setBounds(580,115,120,22);
		QDate.setBounds(680,115,100,22);
		QDate.setEditable(false);
		l2.setFont(new java.awt.Font("Courier New", 1, 13));
		QDate.setFont(new java.awt.Font("Courier New", 1, 13));

		l3.setBounds(580,160,120,22);
		QCust.setBounds(580,190,200,22);
		QCust.setEditable(false);
		l3.setFont(new java.awt.Font("Courier New", 1, 13));
		QCust.setFont(new java.awt.Font("Courier New", 1, 13));

		table.setBounds(20,60,520,320);
		QuoTbl.setRowHeight(25);
		QuoTbl.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(pid));
		QuoTbl.getColumnModel().getColumn(1).setCellEditor( new DefaultCellEditor(pid));
		QuoTbl.getColumnModel().getColumn(2).setCellEditor( new DefaultCellEditor(pid));
		QuoTbl.getColumnModel().getColumn(3).setCellEditor( new DefaultCellEditor(pid));
		QuoTbl.getColumnModel().getColumn(4).setCellEditor( new DefaultCellEditor(pid));
		QuoTbl.setFont(new java.awt.Font("Courier New", 1, 13));
		//QCust.setFont(new java.awt.Font("Courier New", 1, 13));

		pid.setEditable(false);

		QuoTbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumn column = null;
		column = QuoTbl.getColumnModel().getColumn(0);
		column.setPreferredWidth(60);
		column = QuoTbl.getColumnModel().getColumn(1);
		column.setPreferredWidth(165);
		column = QuoTbl.getColumnModel().getColumn(2);
		column.setPreferredWidth(75);
		column = QuoTbl.getColumnModel().getColumn(3);
		column.setPreferredWidth(85);
		column = QuoTbl.getColumnModel().getColumn(4);
		column.setPreferredWidth(120);
		//column = QuoTbl.getColumnModel().getColumn(5);
		//column.setPreferredWidth(70);

		l5.setBounds(50,400,180,22);
		TAmt.setBounds(230,400,120,22);
		TAmt.setEditable(false);
		l5.setFont(new java.awt.Font("Courier New", 1, 13));
		TAmt.setFont(new java.awt.Font("Courier New", 1, 13));

		//l6.setBounds(300,380,160,22);
		//STax.setBounds(480,380,120,22);
		//STax.setEditable(false);
		//l6.setFont(new java.awt.Font("Courier New", 1, 13));
		//STax.setFont(new java.awt.Font("Courier New", 1, 13));

		l8.setBounds(580,330,140,22);
		status.setBounds(650,330,120,22);
		status.setEditable(false);
		l8.setFont(new java.awt.Font("Courier New", 1, 13));
		status.setFont(new java.awt.Font("Courier New", 1, 13));

		l9.setBounds(280,20,270,25);
		l9.setFont(new java.awt.Font("papyrus", 1, 23));
		l9.setForeground(new Color(200,0,0));
		//QCust.setFont(new java.awt.Font("Courier New", 1, 13));

		cmdNew.setBounds(490,400,140,40);
		cmdCancel.setBounds(640,400,140,40);
		cmdNew.setFont(new java.awt.Font("Courier New", 1, 13));
		cmdCancel.setFont(new java.awt.Font("Courier New", 1, 13));
		
		cmdNew.addActionListener(this);
		cmdNew.setMnemonic('n');
		
		cmdCancel.addActionListener(this);
		cmdCancel.setMnemonic('c');

		QuoNo.addActionListener(this);

		Border b=BorderFactory.createLineBorder(new Color(100,200,255));
		QDate.setBorder(b);
		QCust.setBorder(b);
		TAmt.setBorder(b);
		status.setBorder(b);
		//TAmt.setBorder(b);
		//status.setBorder(b);

        setSize(800,540);
		setResizable(false);
		setVisible(true);
		
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");

			st5=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st5.execute("use sales");
			st3=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st3.execute("use sales");
			st4=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st4.execute("use sales");
			st1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st1.execute("use sales");
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st.execute("use sales");
			rs=st.executeQuery("select * from Quotation");
			rsmd=rs.getMetaData();

			while(rs.next())
			{
				QuoNo.addItem(rs.getString(1));
				quoCount++;
			}
			System.out.println(quoCount);




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

		if(e.getSource()==cmdNew)
		{	
			setVisible(false);
			try
			{
				st.close();
        		con.close();
			}
			catch(SQLException se2)
			{
				JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,se2.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			NewQuot n = new NewQuot(quoCount+1);
		}


		if(e.getSource()==QuoNo)
		{
			try
			{

				double[] PRI = new double[30];
				
				double total=0.0,penAmt=0,disAmt=0;
				i=0;
				int no=QuoNo.getSelectedIndex();
				String num=(String)QuoNo.getSelectedItem();
				if(no!=0 )
				{
					System.out.println("ok  ");
				rs.beforeFirst();
				System.out.println("ok1  ");
				while(rs.next())
				{
					if(rs.getString(1).equals(num))
					{
						break;
					}
				}
				System.out.println("ok  2");
				QDate.setText(rs.getString(2));
				//OSupl.setText(rs.getString(3));
				rs4=st4.executeQuery("select  Cust_Name from Customer  where  Cust_Id='"+rs.getString(3)+"'");
				rs4.next();
				System.out.println("ok 3 ");
				QCust.setText(rs4.getString(1));
				System.out.println("ok  4");
				
				if(rs.getInt(5)==0)
				{
					status.setText("Not Accepted");
					
				}
				//System.out.println("ok  5");
				if(rs.getInt(5)==1)
				{
					status.setText("Accepted");
					
				}
				//System.out.println("ok  6");
				System.out.println("ok  7");
				
				for(int x=6;x<=rsmd.getColumnCount();x++)
				{
					int qty=rs.getInt(x);
					if(rs.wasNull())
					{
						System.out.println("Null value");
					}
					else
					{
						QuoTbl.setValueAt(rsmd.getColumnLabel(x),i,0);
						QuoTbl.setValueAt(rs.getString(x),i,3);
	
						rs1=st1.executeQuery("select  Pro_Name,Sales_Price  from  Product  where  Pro_Id='"+rsmd.getColumnLabel(x)+"'");
						rs1.next();
						double pri=rs1.getDouble(2);
						PRI[i]=pri;
						QuoTbl.setValueAt(rs1.getString(1),i,1);
						QuoTbl.setValueAt(rs1.getString(2),i,2);
						double amt=pri*qty;
						QuoTbl.setValueAt(""+amt,i,4);
						total = total + amt;
						i++;

					}
				}
				for(int t=i;t<40-i;t++)
				{
					QuoTbl.setValueAt("",t,0);
					QuoTbl.setValueAt("",t,1);
					QuoTbl.setValueAt("",t,2);
					QuoTbl.setValueAt("",t,3);
					QuoTbl.setValueAt("",t,4);
					

				}
				TAmt.setText(""+total);
				}
				else
				{
					QDate.setText("");
					
					status.setText("");
					TAmt.setText("");
					QCust.setText("");
					//DAmt.setText("");
					for(int t=0;t<40;t++)
					{
					QuoTbl.setValueAt("",t,0);
					QuoTbl.setValueAt("",t,1);
					QuoTbl.setValueAt("",t,2);
					QuoTbl.setValueAt("",t,3);
					QuoTbl.setValueAt("",t,4);
					//QuoTbl.setValueAt("",t,5);

					}
					
				}

			}
			catch(SQLException se2)
			{
				JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,se2.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}

		

	}

public static void main(String [] agrs)
	{
		
		Quot s=new Quot();
	}

}   //close of class User










class NewQuot extends JFrame  implements ActionListener ,ItemListener
{
	ImageIcon i4= new ImageIcon("Image//make.png");      
	ImageIcon i5= new ImageIcon("Image//back.png");
	ImageIcon i6= new ImageIcon("Image//calculator.png");      

	
	
	JLabel l1=new JLabel("Quotation No :");   
	JTextField QuoNo =new JTextField();
	
	JLabel l2=new JLabel("Quotn. Date :");
	JTextField QDate =new JTextField();

	JLabel l4=new JLabel("Customer Name :");
	JComboBox QCust =new JComboBox();

	JLabel l5=new JLabel("Total Order Amount :");
	JTextField TAmt =new JTextField("0.00");

	//JLabel l6=new JLabel("Sales Tax :");
	//JTextField STax =new JTextField("0.00");

	JTextField  lb = new JTextField();
	JTextField  lb1 = new JTextField();

	String[][] row= new String[40][5];
	String[] column={ "Product Name"," Id ","Price","Quantity","Amount"};
	JTable QuoTbl= new JTable(row,column);
	JScrollPane table=new JScrollPane(QuoTbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JComboBox proid =new JComboBox();
	JTextField  pi = new JTextField();
	
	JButton cmdMake=new JButton("Make",i4);
	JButton cmdCancel=new JButton("Cancel",i5);
	JButton cmdCal=new JButton("Calculate Amount",i6);
	
	JLabel l9=new JLabel("Product  Information  ");

	static Connection con;
	static Statement st;
	static ResultSet rs;
	static ResultSetMetaData rsmd;
	static Statement st1;
	static ResultSet rs1;
	static ResultSetMetaData rsmd1;
	static Statement st3;
	static ResultSet rs3;
	static Statement st4;
	static ResultSet rs4;
	static Statement st5;
	static ResultSet rs5;


	java.util.Date date = new java.util.Date();
	DateFormat df = DateFormat.getDateInstance(3);
	String  date1,mon,datex,year,yearx,currentDate;
	int d,d1;

	int rowSelCnt=0,proCnt=0;


	public NewQuot(int newCnt)
	{	
		super("New Quotation");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(QuoNo);
		c.add(l2);
		c.add(QDate);
		c.add(l4);
		c.add(QCust);
		c.add(l5);
        c.add(TAmt);
		//c.add(l6);
       // c.add(STax);
		c.add(table);
		c.add(cmdMake);
		c.add(cmdCancel);
		c.add(cmdCal);
		c.add(l9);

		c.add(lb);
		lb.setBounds(565,0,5,395);
		lb.setBackground(new Color(100,200,250));
		lb.setEditable(false);
		
		
		c.add(lb1);
		lb1.setBounds(0,390,800,5);
		lb1.setBackground(new Color(100,200,250));
		lb1.setEditable(false);

		l1.setBounds(580,60,120,22);
		QuoNo.setBounds(680,60,80,22);
		QuoNo.setEditable(false);
		QuoNo.setText("q"+newCnt);
		l1.setFont(new java.awt.Font("Courier New", 1, 13));
		QuoNo.setFont(new java.awt.Font("Courier New", 1, 13));

		l2.setBounds(580,115,120,22);
		QDate.setBounds(680,115,90,22);
		QDate.setEditable(false);
		l2.setFont(new java.awt.Font("Courier New", 1, 13));
		QDate.setFont(new java.awt.Font("Courier New", 1, 13));

		l4.setBounds(580,160,130,22);
		QCust.setBounds(580,190,200,22);
		l4.setFont(new java.awt.Font("Courier New", 1, 13));
		QCust.setFont(new java.awt.Font("Courier New", 1, 13));
		
		table.setBounds(20,60,530,320);
		QuoTbl.setRowHeight(25);
		QuoTbl.getColumnModel().getColumn(1).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(2).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(4).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.setFont(new java.awt.Font("Courier New", 1, 13));
		pi.setEditable(false);
		proid.addItemListener(this);

		QuoTbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumn column = null;
		column = QuoTbl.getColumnModel().getColumn(0);
		column.setPreferredWidth(150);
		column = QuoTbl.getColumnModel().getColumn(1);
		column.setPreferredWidth(65);
		column = QuoTbl.getColumnModel().getColumn(2);
		column.setPreferredWidth(75);
		column = QuoTbl.getColumnModel().getColumn(3);
		column.setPreferredWidth(85);
		column = QuoTbl.getColumnModel().getColumn(4);
		column.setPreferredWidth(120);

		date1=df.format(date);
		d=date1.indexOf('/');
		mon=date1.substring(0,d);
		d1=date1.lastIndexOf('/');
		datex=date1.substring(d+1,d1);
		yearx=date1.substring(d1+1);
		year="20"+yearx;
		currentDate=year+"-"+mon+"-"+datex;
		System.out.println("current date  "+currentDate);
		QDate.setText(currentDate);

		l5.setBounds(350,475,160,22);
		TAmt.setBounds(530,475,120,22);
		TAmt.setEditable(false);
		l5.setFont(new java.awt.Font("Courier New", 1, 13));
		TAmt.setFont(new java.awt.Font("Courier New", 1, 13));

		//l6.setBounds(300,380,160,22);
		//STax.setBounds(480,380,120,22);
		//STax.setEditable(false);

		l9.setBounds(280,20,280,26);
		l9.setFont(new java.awt.Font("papyrus", 1, 24));
		l9.setForeground(new Color(200,0,0));
		//status.setFont(new java.awt.Font("Courier New", 1, 13));

		cmdMake.setBounds(80,420,115,48);
		cmdCancel.setBounds(210,420,115,48);
		cmdCal.setBounds(530,420,200,48);
		cmdMake.setFont(new java.awt.Font("Courier New", 1, 12));
		cmdCancel.setFont(new java.awt.Font("Courier New", 1, 12));
		cmdCal.setFont(new java.awt.Font("Courier New", 1, 12));
		
		cmdMake.addActionListener(this);
		cmdMake.setMnemonic('m');
		cmdMake.setEnabled(false);
		
		cmdCancel.addActionListener(this);
		cmdCancel.setMnemonic('c');

		cmdCal.addActionListener(this);
		cmdCal.setMnemonic('l');

		proid.addItem("");

		QCust.addItem("");

		Border b=BorderFactory.createLineBorder(new Color(100,200,255));
		QDate.setBorder(b);
		QuoNo.setBorder(b);
		TAmt.setBorder(b);
		//status.setBorder(b);

		QuoTbl.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(proid));

        setSize(800,560);
		setResizable(false);
		setVisible(true);

		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");

			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st.execute("use sales");
			rs=st.executeQuery("select  *  from  Quotation");
			rs.next();

			
			st3=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st3.execute("use sales");
			st4=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st4.execute("use sales");
			st1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st1.execute("use sales");
			st5=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st5.execute("use sales");
			rs5=st5.executeQuery("select Cust_Id,Cust_Name from  Customer where Cust_Status=0");
			while(rs5.next())
			{
				QCust.addItem(rs5.getString(2));
				//supCnt++;
			}

			rs4=st4.executeQuery("select Pro_Id,Pro_Name,Sales_Price from  Product where Pro_Status=0");
			while(rs4.next())
			{
				proid.addItem(rs4.getString(2));
				proCnt++;
			}



			System.out.println("connected");
		}
		catch(Exception e)
		{
			JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}

	}



	public void itemStateChanged(ItemEvent ie)
	{
		//if(ie.getStateChange()==ie.SELECTED)
		if(ie.getSource()==proid)
		{
			if(ie.getStateChange()==ie.SELECTED)
			{
				try
				{
					int t1=proid.getSelectedIndex();
					String num=(String)proid.getSelectedItem();
					int rowNo=QuoTbl.getSelectedRow();
					if(t1!=0)
					{
						//rs4.absolute(t1);	
						rs4.beforeFirst();
						while(rs4.next())
						{
							if(rs4.getString(2).equals(num))
							{
								break;
							}
						}
						QuoTbl.setValueAt(rs4.getString(1),rowNo,1);
						QuoTbl.setValueAt(rs4.getString(3),rowNo,2);
					}
					else
					{
						QuoTbl.setValueAt("",rowNo,1);
						QuoTbl.setValueAt("",rowNo,2);
					}

				}
				catch(Exception e3)
				{
					System.out.println("unable to mouse click 1  "+e3.getMessage());
				}

			}
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
			Quot s=new Quot();
		}


		if(e.getSource()==cmdCal)
		{
			
			
			double poTAmt=0,popri,poqty,poamt;
			String qty,pri,no="";
			boolean b=true,c=true,d=true;
			Object nos=null;

			rowSelCnt=0;

			String AMT;

			for(int i=0;i<proCnt;i++)
			{
				if(QuoTbl.getValueAt(i,0)==nos)
				{
					if(i==0)
					{
						JOptionPane err7=new JOptionPane();
						err7.showMessageDialog(null," Please enter record in Product information table.  ","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
						cmdMake.setEnabled(false);
						c=false;
					}
					break;
				}
				AMT=(String)QuoTbl.getValueAt(i,3);
				if(QuoTbl.getValueAt(i,3)==nos  && c==true)
				{
					JOptionPane err1=new JOptionPane();
					err1.showMessageDialog(null," Please enter Quantity for Product.  ","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					c=false;
					cmdMake.setEnabled(false);
					break;
				}
				for(int m=0;m<AMT.length();m++)
				{
					if(AMT.charAt(m)<48  || AMT.charAt(m)>57 )
					{
						JOptionPane err3=new JOptionPane();
						err3.showMessageDialog(null," You must enter numeric value for Quantity.","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
						c=false;
						cmdMake.setEnabled(false);
						break;
					}
				}
				rowSelCnt++;
				System.out.println("row cnt  "+rowSelCnt);
			}

			

			for(int i=0;i<proCnt;i++)
			{
				if(QuoTbl.getValueAt(i,0)==nos)
				{
					break;
				}
				for(int j=i+1;j<proCnt;j++)
				{
					
					if(QuoTbl.getValueAt(i,0).equals( QuoTbl.getValueAt(j,0) ) )
					{
						JOptionPane err=new JOptionPane();
						err.showMessageDialog(null,"For order you must place one product at a time .\n        You place same product in order.  ","Record Insert Error",JOptionPane.ERROR_MESSAGE);
						b=false;
						cmdMake.setEnabled(false);
						break;
					}
					
				}
				if( b==false)
				break;	

				if(b==true)
				{
					try
					{
						rs3=st3.executeQuery(" select Qty from Inventory where InvPro_Id='"+QuoTbl.getValueAt(i,1)+"'   ");
						rs3.next();
						int tepmQtyAvb=rs3.getInt(1);
						String tempAdd=(String)QuoTbl.getValueAt(i,3);
						int tempQuo=Integer.parseInt(tempAdd);
						if(tepmQtyAvb<=0)
						{
							JOptionPane e33=new JOptionPane();
							e33.showMessageDialog(null," For Product  '"+QuoTbl.getValueAt(i,0)+"'  Available quantity is '"+tepmQtyAvb+"'  \n  Its time for new purchase order  .","Record Insert Error",JOptionPane.ERROR_MESSAGE);
							b=false;
							cmdMake.setEnabled(false);
							break;

						}
						if(tepmQtyAvb<tempQuo)
						{
							JOptionPane e3=new JOptionPane();
							e3.showMessageDialog(null," For Product  '"+QuoTbl.getValueAt(i,0)+"'  Available quantity is '"+tepmQtyAvb+"'  \n  Please enter quantity less than '"+tepmQtyAvb+"' . ","Record Insert Error",JOptionPane.ERROR_MESSAGE);
							b=false;
							cmdMake.setEnabled(false);
							break;
						}
					}
			
					catch(Exception se11)
					{
						JOptionPane e1=new JOptionPane();
						e1.showMessageDialog(null,se11.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}
				}


			}

			if(b==true && c==true)
			{
			try
			{
				for(int t=0;t<rowSelCnt;t++)
				{
					pri=(String)QuoTbl.getValueAt(t,2);
					qty=(String)QuoTbl.getValueAt(t,3);
					poamt=Double.parseDouble(pri)*Integer.parseInt(qty);
					QuoTbl.setValueAt(""+poamt,t,4);
					poTAmt = poTAmt + poamt;
				}
				TAmt.setText(""+poTAmt);
				cmdMake.setEnabled(true);
			}
			catch(Exception se10)
			{
				JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,se10.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			}
		}


		if(e.getSource()==cmdMake)
		{
			//code for saving records

			String[] PId = new String[rowSelCnt];
			String[] PQ = new String[rowSelCnt];
			int[] PQty = new int[rowSelCnt];

			String no="";
			String pono,podate,posupl=null,proId,sup;
			double poamt,proQty;
			int postatus=0;
			boolean b=true;

			pono=QuoNo.getText();
			podate=QDate.getText();
			sup=(String)QCust.getSelectedItem();
			
			poamt=Double.parseDouble(TAmt.getText());

			for(int i=0;i<rowSelCnt;i++)
			{
				PId[i]=(String)QuoTbl.getValueAt(i,1);
				PQ[i]=(String)QuoTbl.getValueAt(i,3);
				PQty[i]=Integer.parseInt(PQ[i]);
			}
			if(sup.equals(no))
			{
				JOptionPane err4=new JOptionPane();
				err4.showMessageDialog(null," Please select a Customer.","Record Insert Error",JOptionPane.ERROR_MESSAGE);
				b=false;

			}
			
			if(b==true)
			{
				
				try
				{
					rs5.beforeFirst();
					System.out.println("1");
					while(rs5.next())
					{
						//OSupl.addItem(rs1.getString(2));
						System.out.println("2");
						if(rs5.getString(2).equals(sup))
						{
							posupl=rs5.getString(1);
							System.out.println(posupl);
							System.out.println("3");
							break;

						}
						
					}
						
					
					System.out.println("4");
					rs.moveToInsertRow(); // moves cursor to the insert row
					rs.updateString(1,pono); 
					rs.updateString(2,podate); 
					rs.updateString(3,posupl); 
					System.out.println("5");
					rs.updateDouble(4,poamt);
					rs.updateInt(5,0);

					for(int i=0;i<rowSelCnt;i++)
					{
						rs.updateInt(PId[i],PQty[i]);
					}
					
					rs.insertRow();
					rs.moveToCurrentRow();
					System.out.println("save");
					setVisible(false);
					Quot s=new Quot();

				}
				catch(SQLException se4)
				{
					JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,se4.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	}