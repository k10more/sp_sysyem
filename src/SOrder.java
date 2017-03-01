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

 class SOrder extends JFrame  implements ActionListener 
{
	ImageIcon i4= new ImageIcon("Image//new.png");      
   ImageIcon i5= new ImageIcon("Image//back.png");      
	ImageIcon i9=new ImageIcon("Image//can.png");
	
	
	JLabel l1=new JLabel("Order No :");   
	JComboBox OrdNo =new JComboBox();
	
	JLabel l2=new JLabel("Order Date :");
	JTextField ODate =new JTextField();

	JLabel l3=new JLabel("Customer :");
	JTextField OCust =new JTextField();

	JLabel l4=new JLabel("Quatation No:");
	JTextField QRef =new JTextField();

	JLabel l5=new JLabel("Total Order Amount :");
	JTextField TAmt =new JTextField("0.00");

	JLabel l6=new JLabel("Amount Paid :");
	JTextField DAmt =new JTextField("0.00");

	JLabel l7=new JLabel("Amount Pending :");
	JTextField PAmt =new JTextField("0.00");

	JTextField  lb = new JTextField();
	JTextField lb1=new JTextField();

	String[][] row= new String[40][6];
	String[] column={"Product Id "," Product  Name ","Price","Quantity","Amount","Qty Pending"};
	JTable QuoTbl= new JTable(row,column);
	JScrollPane table=new JScrollPane(QuoTbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	//JComboBox proid =new JComboBox();
	
	JButton cmdNew=new JButton("New",i4);
	JButton cmdCancel=new JButton("Cancel",i5);
	//JButton print=new JButton("Print");

	JLabel l8=new JLabel("Status :");
	JTextField  status = new JTextField();

	JLabel l10=new JLabel("Reason :",i9,10);
	JTextField reason =new JTextField();

	JLabel l9=new JLabel("Product  Information  ");

	JTextField pi =new JTextField();

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

	int ordCount=0,i=0;

	boolean can=false;


	public SOrder()
	{	
		super("Sales Order");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(OrdNo);
		c.add(l2);
		c.add(ODate);
		c.add(l3);
		c.add(OCust);
		c.add(l4);
		c.add(QRef);
		c.add(l5);
        c.add(TAmt);
		c.add(l6);
		c.add(DAmt);
		c.add(l7);
		c.add(PAmt);
		c.add(l8);
		c.add(status);
		c.add(table);
		//c.add(print);
		c.add(cmdNew);
		c.add(cmdCancel);
		c.add(l9);
		c.add(l10);
		c.add(reason);

		c.add(lb);
		lb.setBounds(565,0,5,395);
		lb.setBackground(new Color(100,200,250));
		lb.setEditable(false);
		
		c.add(lb1);
		lb1.setBounds(0,390,800,5);
		lb1.setBackground(new Color(100,200,250));
		lb1.setEditable(false);

		l1.setBounds(580,60,100,22);
		OrdNo.setBounds(680,60,100,22);
		OrdNo.addItem("");
		l1.setFont(new java.awt.Font("Courier New", 1, 13));
		OrdNo.setFont(new java.awt.Font("Courier New", 1, 13));
		

		l2.setBounds(580,115,100,22);
		ODate.setBounds(680,115,100,22);
		ODate.setEditable(false);
		l2.setFont(new java.awt.Font("Courier New", 1, 13));
		ODate.setFont(new java.awt.Font("Courier New", 1, 13));

		l3.setBounds(580,160,100,22);
		OCust.setBounds(580,190,200,22);
		OCust.setEditable(false);
		l3.setFont(new java.awt.Font("Courier New", 1, 13));
		OCust.setFont(new java.awt.Font("Courier New", 1, 13));

		l4.setBounds(580,235,130,22);
		QRef.setBounds(580,260,120,22);
		QRef.setEditable(false);
		l4.setFont(new java.awt.Font("Courier New", 1, 13));
		QRef.setFont(new java.awt.Font("Courier New", 1, 13));

		table.setBounds(20,60,535,320);
		QuoTbl.setRowHeight(25);

		QuoTbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumn column = null;
		column = QuoTbl.getColumnModel().getColumn(0);
		column.setPreferredWidth(80);
		column = QuoTbl.getColumnModel().getColumn(1);
		column.setPreferredWidth(150);
		column = QuoTbl.getColumnModel().getColumn(2);
		column.setPreferredWidth(60);
		column = QuoTbl.getColumnModel().getColumn(3);
		column.setPreferredWidth(70);
		column = QuoTbl.getColumnModel().getColumn(4);
		column.setPreferredWidth(80);
		column = QuoTbl.getColumnModel().getColumn(5);
		column.setPreferredWidth(90);

		QuoTbl.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(1).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(2).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(3).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(4).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(5).setCellEditor( new DefaultCellEditor(pi));
		pi.setEditable(false);
		//QuoTbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


		QuoTbl.setFont(new java.awt.Font("Courier New", 1, 13));
		//TAmt.setFont(new java.awt.Font("Courier New", 1, 13));
		
		l5.setBounds(50,400,180,22);
		TAmt.setBounds(230,400,120,22);
		TAmt.setEditable(false);
		l5.setFont(new java.awt.Font("Courier New", 1, 13));
		TAmt.setFont(new java.awt.Font("Courier New", 1, 13));

		l6.setBounds(50,440,180,22);
		DAmt.setBounds(230,440,120,22);
		DAmt.setEditable(false);
		l6.setFont(new java.awt.Font("Courier New", 1, 13));
		DAmt.setFont(new java.awt.Font("Courier New", 1, 13));

		l7.setBounds(50,480,180,22);
		PAmt.setBounds(230,480,120,22);
		PAmt.setEditable(false);
		l7.setFont(new java.awt.Font("Courier New", 1, 13));
		PAmt.setFont(new java.awt.Font("Courier New", 1, 13));

		

		l8.setBounds(580,330,140,22);
		status.setBounds(650,330,120,22);
		status.setEditable(false);
		l8.setFont(new java.awt.Font("Courier New", 1, 13));
		status.setFont(new java.awt.Font("Courier New", 1, 13));

		l9.setBounds(200,20,270,25);
		l9.setFont(new java.awt.Font("Papyrus", 1, 23));
		l9.setForeground(new Color(200,0,0));
		//TAmt.setFont(new java.awt.Font("Courier New", 1, 13));

		l10.setBounds(400,480,140,22);
		reason.setBounds(490,480,215,22);
		reason.setEditable(false);
		l10.setFont(new java.awt.Font("Courier New", 1, 13));
		reason.setFont(new java.awt.Font("Courier New", 1, 13));
		l10.setVisible(false);
		reason.setVisible(false);

        //cmdSave.setBounds(80,320,95,32);
		cmdNew.setBounds(490,400,130,40);
		cmdCancel.setBounds(630,400,130,40);
		cmdNew.setFont(new java.awt.Font("Courier New", 1, 13));
		cmdCancel.setFont(new java.awt.Font("Courier New", 1, 13));
		
		cmdNew.addActionListener(this);
		cmdNew.setMnemonic('n');
		
		cmdCancel.addActionListener(this);
		cmdCancel.setMnemonic('b');

		OrdNo.addActionListener(this);

		Border b=BorderFactory.createLineBorder(new Color(100,200,255));
		ODate.setBorder(b);
		OCust.setBorder(b);
		TAmt.setBorder(b);
		status.setBorder(b);
		QRef.setBorder(b);
		DAmt.setBorder(b);
		PAmt.setBorder(b);

		//print.addActionListener(this);
		//print.setMnemonic('p');

		//QuoTbl.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(proid));

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
			rs=st.executeQuery("select * from SOrder");
			rsmd=rs.getMetaData();

			while(rs.next())
			{
				OrdNo.addItem(rs.getString(1));
				ordCount++;
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
			NewOrder n = new NewOrder(ordCount+1);
		}


		if(e.getSource()==OrdNo)
		{
			try
			{

				double[] PRI = new double[30];
				
				double total=0.0,penAmt=0,disAmt=0;
				i=0;
				int no=OrdNo.getSelectedIndex();
				String num=(String)OrdNo.getSelectedItem();
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
				ODate.setText(rs.getString(2));
				//OSupl.setText(rs.getString(3));
				rs4=st4.executeQuery("select  QuoCust_Id from Quotation  where  Quo_No='"+rs.getString(3)+"'");
				rs4.next();
				String cus=rs4.getString(1);
				System.out.println("ok 3 ");
				rs4=st4.executeQuery("select  Cust_Name from Customer  where Cust_Id='"+cus+"'");
				rs4.next();
				OCust.setText(rs4.getString(1));
				System.out.println("ok  4");
				QRef.setText(rs.getString(3));
			
				if(rs.getInt(5)==0)
				{
					status.setText("Pending");
					l10.setVisible(false);
					reason.setVisible(false);
					
				}
				//System.out.println("ok  5");
				else if(rs.getInt(5)==1)
				{
					status.setText("Completed");
					l10.setVisible(false);
					reason.setVisible(false);
					
				}
				else
				{
					status.setText("Canceled");
					can=true;
					l10.setVisible(true);
					reason.setVisible(true);

				}
				//System.out.println("ok  6");
				System.out.println("ok  7");
				//status.setText("Completed");
				
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
				
				TAmt.setText(""+total);


				int[] qty1 = new int[i];//invoice qty
				int[] qty2 = new int[i];//order qty
				int[] diff = new int[i];//diff of order & invoice

				String ord=(String)OrdNo.getSelectedItem();
				try
				{
					rs3=st3.executeQuery("select * from SInvoice where SIOrder_No='"+ord+"' ");
					while(rs3.next())
					{
						for(int t=0;t<i;t++)
						{
							String tes=(String)QuoTbl.getValueAt(t,0);
							int te=rs3.getInt(tes);
							qty1[t]=qty1[t]+te;
							System.out.println("qty1  "+qty1[t]);
							
						}
					}
				}
				catch(SQLException e2)
				{
					System.out.println("UNABLE TO run");
					System.out.println(e2.getMessage());
				}

				try
				{
					rs3=st3.executeQuery("select * from SOrder where SOrder_No='"+ord+"' ");
					while(rs3.next())
					{
						for(int t=0;t<i;t++)
						{
							String tes=(String)QuoTbl.getValueAt(t,0);
							int te=rs3.getInt(tes);
							qty2[t]=te;
							System.out.println("qty2  "+qty2[t]);
							
						}
					}
				}
				catch(SQLException e3)
				{
					System.out.println("UNABLE TO run");
					System.out.println(e3.getMessage());
				}

				for(int t=0;t<i;t++)
				{
					diff[t]=qty2[t]-qty1[t];
					QuoTbl.setValueAt(""+diff[t],t,5);
					System.out.println("diff  "+diff[t]);	
				}

				for(int t=0;t<i;t++)
				{
					penAmt = penAmt + (PRI[t]*diff[t]);
				}
				PAmt.setText(""+penAmt);
				disAmt = total - penAmt;
				DAmt.setText(""+disAmt);


				for(int t=i;t<40-i;t++)
				{
					QuoTbl.setValueAt("",t,0);
					QuoTbl.setValueAt("",t,1);
					QuoTbl.setValueAt("",t,2);
					QuoTbl.setValueAt("",t,3);
					QuoTbl.setValueAt("",t,4);
					QuoTbl.setValueAt("",t,5);

				}

				

				if(can==true)
				{
					rs5=st5.executeQuery("select  Reason from SOCancel  where  SOCancel_No='"+num+"'");
					rs5.next();
					reason.setText(rs5.getString(1));

				}
						
				}
				else
				{
					ODate.setText("");
					QRef.setText("");
					status.setText("");
					TAmt.setText("");
					OCust.setText("");
					PAmt.setText("");
					DAmt.setText("");
					//DAmt.setText("");
					for(int t=0;t<40;t++)
					{
					QuoTbl.setValueAt("",t,0);
					QuoTbl.setValueAt("",t,1);
					QuoTbl.setValueAt("",t,2);
					QuoTbl.setValueAt("",t,3);
					QuoTbl.setValueAt("",t,4);
					QuoTbl.setValueAt("",t,5);

					}
					
				}

			}
			catch(SQLException se3)
			{
				//JOptionPane e1=new JOptionPane();
			//e1.showMessageDialog(null,se3.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}


	}

public static void main(String [] agrs)
	{
		
		SOrder s=new SOrder();
	}

}   //close of class User










class NewOrder extends JFrame  implements ActionListener 
{
	ImageIcon i4= new ImageIcon("Image//make.png");      
	ImageIcon i5= new ImageIcon("Image//back.png");
	ImageIcon i6= new ImageIcon("Image//calculator.png");     

	
	
	JLabel l1=new JLabel("Order No :");   
	JTextField OrdNo =new JTextField();
	
	JLabel l2=new JLabel("Order Date :");
	JTextField ODate =new JTextField();

	JLabel l3=new JLabel("Customer  :");
	JTextField OCust =new JTextField();

	JLabel l4=new JLabel("Quatation No:");
	JComboBox QRef =new JComboBox();

	JLabel l5=new JLabel("Total Order Amount :");
	JTextField TAmt =new JTextField("0.00");

	JTextField  lb = new JTextField();
	
	JTextField  lb1 = new JTextField();

	JTextField  pi= new JTextField();

	String[][] row= new String[40][5];
	String[] column={"Product Id "," Product Name ","Price","Quantity","Amount"};
	JTable QuoTbl= new JTable(row,column);
	JScrollPane table=new JScrollPane(QuoTbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JComboBox proid =new JComboBox();
	
	JButton cmdMake=new JButton("Make",i4);
	JButton cmdCancel=new JButton(i5);
	JButton cmdCal=new JButton("Calculate Amount",i6);
	
	JLabel l9=new JLabel("Product  Information  ");

	static Connection con;
	static Statement st;
	static ResultSet rs;
	static ResultSetMetaData rsmd;
	static Statement st1;
	static ResultSet rs1;
	static ResultSetMetaData rsmd1;
	static Statement st2;
	static ResultSet rs2;
	static ResultSetMetaData rsmd2;
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


	int proCnt,t=0;
	int[] rowCnt = new int[20];//array to storing selected row numbers
	int rowSelCnt=0;//index for rowCnt
	

	int rowNo,pno,supCnt=0;
	String pid,name;
	double pri;


	public NewOrder(int newOrd)
	{	
		super(" New Sales Order");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(OrdNo);
		c.add(l2);
		c.add(ODate);
		c.add(l3);
		c.add(OCust);
		c.add(l4);
		c.add(QRef);
		c.add(l5);
        c.add(TAmt);
		c.add(table);
		c.add(cmdMake);
		c.add(cmdCancel);
		c.add(cmdCal);
		c.add(l9);

		c.add(lb);
		lb.setBounds(565,0,5,410);
		lb.setBackground(new Color(100,200,250));
		lb.setEditable(false);
		
		
		c.add(lb1);
		lb1.setBounds(0,410,800,5);
		lb1.setBackground(new Color(100,200,250));
		lb1.setEditable(false);

		date1=df.format(date);
		d=date1.indexOf('/');
		mon=date1.substring(0,d);
		d1=date1.lastIndexOf('/');
		datex=date1.substring(d+1,d1);
		yearx=date1.substring(d1+1);
		year="20"+yearx;
		currentDate=year+"-"+mon+"-"+datex;
		System.out.println("current date  "+currentDate);

		l1.setBounds(580,60,100,22);
		OrdNo.setBounds(680,60,100,22);
		OrdNo.setEditable(false);
		OrdNo.setText("o"+newOrd);
		l1.setFont(new java.awt.Font("Courier New", 1, 13));
		OrdNo.setFont(new java.awt.Font("Courier New", 1, 13));

		l2.setBounds(580,125,100,22);
		ODate.setBounds(680,125,100,22);
		ODate.setEditable(false);
		ODate.setText(currentDate);
		l2.setFont(new java.awt.Font("Courier New", 1, 13));
		ODate.setFont(new java.awt.Font("Courier New", 1, 13));

		l3.setBounds(580,190,100,22);
		OCust.setBounds(580,220,200,22);;
		OCust.setEditable(false);
		l3.setFont(new java.awt.Font("Courier New", 1, 13));
		OCust.setFont(new java.awt.Font("Courier New", 1, 13));


		l4.setBounds(580,270,130,22);
		QRef.setBounds(580,295,120,22);
		//QRef.setEditable(false);
		l4.setFont(new java.awt.Font("Courier New", 1, 13));
		QRef.setFont(new java.awt.Font("Courier New", 1, 13));

		table.setBounds(20,60,530,333);
		QuoTbl.setRowHeight(25);

		QuoTbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumn column = null;
		column = QuoTbl.getColumnModel().getColumn(0);
		column.setPreferredWidth(90);
		column = QuoTbl.getColumnModel().getColumn(1);
		column.setPreferredWidth(170);
		column = QuoTbl.getColumnModel().getColumn(2);
		column.setPreferredWidth(90);
		column = QuoTbl.getColumnModel().getColumn(3);
		column.setPreferredWidth(90);
		column = QuoTbl.getColumnModel().getColumn(4);
		column.setPreferredWidth(90);
		QuoTbl.setFont(new java.awt.Font("Courier New", 1, 13));
		//OCust.setFont(new java.awt.Font("Courier New", 1, 13));

		l5.setBounds(400,480,180,22);
		TAmt.setBounds(590,480,120,22);
		TAmt.setEditable(false);
		l5.setFont(new java.awt.Font("Courier New", 1, 13));
		TAmt.setFont(new java.awt.Font("Courier New", 1, 13));

		l9.setBounds(180,20,270,26);
		l9.setFont(new java.awt.Font("Papyrus", 1, 23));
		l9.setForeground(new Color(200,0,0));
		//OCust.setFont(new java.awt.Font("Courier New", 1, 13));

		cmdMake.setBounds(80,420,115,40);
		cmdCancel.setBounds(210,420,115,40);
		cmdCal.setBounds(570,420,150,48);
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

		QRef.addActionListener(this);

		QRef .addItem("");

		Border b=BorderFactory.createLineBorder(new Color(100,200,255));
		ODate.setBorder(b);
		OCust.setBorder(b);
		TAmt.setBorder(b);
		//status.setBorder(b);
		OrdNo.setBorder(b);
		//DAmt.setBorder(b);
		//PAmt.setBorder(b);

		QuoTbl.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(1).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(2).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(4).setCellEditor( new DefaultCellEditor(pi));
		pi.setEditable(false);

        setSize(800,560);
		setResizable(false);
		setVisible(true);
 
		
		
		
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");

			st4=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st4.execute("use sales");

			st1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st1.execute("use sales");
			st3=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st3.execute("use sales");
			st2=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st2.execute("use sales");
			rs2=st2.executeQuery("select * from Quotation where QuoStatus=0");
			rsmd2=rs2.getMetaData();
			while(rs2.next())
			{
				QRef .addItem(rs2.getString(1));
			}
		
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st.execute("use sales");
			rs=st.executeQuery("select * from SOrder");
			rs.next();
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
			SOrder s=new SOrder();
		}



		if(e.getSource()==QRef )
		{
			try
			{
				
				double total=0.0;
				int i=0;
				int no=QRef .getSelectedIndex();
				String num=(String)QRef .getSelectedItem();
				if(no!=0)
				{
				//rs2.absolute(no);
				rs2.beforeFirst();
				while(rs2.next())
				{
					if(rs2.getString(1).equals(num))
					{
						break;
					}
				}
				for(int x=6;x<=rsmd2.getColumnCount();x++)
				{
					int qty=rs2.getInt(x);
					if(rs2.wasNull())
					{
						System.out.println("Null value");
					}
					else
					{
						QuoTbl.setValueAt(rsmd2.getColumnLabel(x),i,0);
	
						rs1=st1.executeQuery("select  Pro_Name,Sales_Price  from  Product  where  Pro_Id='"+QuoTbl.getValueAt(i,0)+"'");
						rs1.next();
						double pri=rs1.getDouble(2);
						QuoTbl.setValueAt(rs1.getString(1),i,1);
						QuoTbl.setValueAt(rs1.getString(2),i,2);
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
				String supl=rs2.getString(3);
				rs4=st4.executeQuery("select Cust_Name from Customer where Cust_Id='"+supl+"'    ");
				rs4.next();
				supl=rs4.getString(1);
				OCust.setText(supl);

				}
				else
				{
					OCust.setText("");
					TAmt.setText("");
					for(int t=0;t<40;t++)
					{
					QuoTbl.setValueAt("",t,0);
					QuoTbl.setValueAt("",t,1);
					QuoTbl.setValueAt("",t,2);
					QuoTbl.setValueAt("",t,3);
					QuoTbl.setValueAt("",t,4);

					}
				}

			}
			catch(SQLException se2)
			{
				JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,se2.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}




		if(e.getSource()==cmdCal)
		{
			double poTAmt=0,popri,poqty,poamt,stax,dcha;
			String qty,pri,no="";
			boolean b=true,c=true,d=true;
			Object nos=null;

			rowSelCnt=0;

			String QTY;

			for(int i=0;i<40;i++)
			{
				if(QuoTbl.getValueAt(i,0)==nos || QuoTbl.getValueAt(i,0).equals(no))
				{
					if(i==0)
					{
						JOptionPane err7=new JOptionPane();
						err7.showMessageDialog(null," Please select the Quotation to which create a Order.  ","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
						cmdMake.setEnabled(false);
						c=false;
					}
					System.out.println("break  "+rowSelCnt);
					break;
				}
				QTY=(String)QuoTbl.getValueAt(i,3);
				if((QuoTbl.getValueAt(i,3)==nos ||QuoTbl.getValueAt(i,3).equals(no)) && c==true)
				{
					JOptionPane err1=new JOptionPane();
					err1.showMessageDialog(null," Please enter Quantity for Product.  ","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
					c=false;
					cmdMake.setEnabled(false);
					break;
				}
				for(int m=0;m<QTY.length();m++)
				{
					if(QTY.charAt(m)<48  || QTY.charAt(m)>57 )
					{
						JOptionPane err3=new JOptionPane();
						err3.showMessageDialog(null," You must enter numeric value for Quantity.","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
						c=false;
						cmdMake.setEnabled(false);
						break;
					}
				}
				if(c==true)
				{
					try
					{
						rs3=st3.executeQuery(" select Qty from Inventory where InvPro_Id='"+QuoTbl.getValueAt(i,0)+"'   ");
						rs3.next();
						int tepmQtyAvb=rs3.getInt(1);
						String tempAdd=(String)QuoTbl.getValueAt(i,3);
						int tempQuo=Integer.parseInt(tempAdd);
						if(tepmQtyAvb<tempQuo)
						{
							JOptionPane e3=new JOptionPane();
							e3.showMessageDialog(null,"For Product  '"+QuoTbl.getValueAt(i,1)+"'  Available quantity is '"+tepmQtyAvb+"'  \n  Please enter quantity less than '"+tepmQtyAvb+"' . ","Record Insert Error",JOptionPane.ERROR_MESSAGE);
							c=false;
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
				rowSelCnt++;
				System.out.println("row cnt  "+rowSelCnt);
			}
			
			
				if(c==true )
				{
					String spri,sqty;
					double dpri,damt,dtamt=0,dstax,ddcha;
					int dqty;
					//dstax=Double.parseDouble(STax.getText());
					//ddcha=Double.parseDouble(DCha.getText());
					for(int t=0;t<rowSelCnt;t++)
					{
						spri=(String)QuoTbl.getValueAt(t,2);
						dpri=Double.parseDouble(spri);
						sqty=(String)QuoTbl.getValueAt(t,3);
						dqty=Integer.parseInt(sqty);
						damt = dpri * dqty;
						QuoTbl.setValueAt(""+damt,t,4);
						dtamt = dtamt + damt;
					}
					dtamt = dtamt ;
					TAmt.setText(""+dtamt);

					cmdMake.setEnabled(true);
					System.out.println("ok");
				}
			//}//end if
			System.out.println("ok");

		}// end event


		if(e.getSource()==cmdMake)
		{
			//code for saving records
			String[] PId = new String[rowSelCnt];
			String[] PQ = new String[rowSelCnt];
			int[] PQty = new int[rowSelCnt];

			String no="";
			String pino,pidate,pior,pista;
			double piamt,stax,dcha;
			int pistatus;
			boolean b=true;

			pino=OrdNo.getText();
			pidate=ODate.getText();
			pior=(String)QRef.getSelectedItem();
			
			piamt=Double.parseDouble(TAmt.getText());
				for(int i=0;i<rowSelCnt;i++)
			{
				PId[i]=(String)QuoTbl.getValueAt(i,0);
				PQ[i]=(String)QuoTbl.getValueAt(i,3);
				PQty[i]=Integer.parseInt(PQ[i]);
			}
			
			if(b==true)
			{
			
				try
				{
					rs.moveToInsertRow(); // moves cursor to the insert row
					rs.updateString(1,pino); 
					rs.updateString(2,pidate); 
					rs.updateString(3,pior); 
					rs.updateDouble(4,piamt);
					rs.updateInt(5,0);

					for(int i=0;i<rowSelCnt;i++)
					{
						rs.updateInt(PId[i],PQty[i]);
					}
					
					rs.insertRow();
					rs.moveToCurrentRow();
					
					
					st3.executeUpdate("update Quotation set QuoStatus=1 where Quo_No='"+pior+"'      ");
					setVisible(false);
					SOrder s=new SOrder();
					System.out.println("save");

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