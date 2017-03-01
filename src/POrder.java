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


 class POrder extends JFrame  implements ActionListener 
{
     ImageIcon i1= new ImageIcon("Image//new.png"); 
     ImageIcon i2= new ImageIcon("Image//back.png");   
	 ImageIcon i9= new ImageIcon("Image//can.png");   
	 

	JLabel l1=new JLabel("Order No :");   
	JComboBox OrdNo =new JComboBox();
	
	JLabel l2=new JLabel("Order Date :");
	JTextField ODate =new JTextField();

	JLabel l3=new JLabel("Supplier :");
	JTextField OSupl =new JTextField();

	JLabel l5=new JLabel("Total Order Amount :");
	JTextField TAmt =new JTextField("0.00");

	JLabel l6=new JLabel(" Amount Paid:");
	JTextField DAmt =new JTextField("0.00");

	JLabel l7=new JLabel(" Amount Pending:");
	JTextField PAmt =new JTextField("0.00");

	JTextField  lb = new JTextField();
	JTextField lb1=new JTextField();
   

	String[][] row= new String[40][6];
	String[] column={"Product Id","Product Name","Quantity","Price","Amount","Qty Pending"};
	JTable QuoTbl= new JTable(row,column);
	JScrollPane table=new JScrollPane(QuoTbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JButton cmdNew=new JButton("New",i1);
	JButton cmdCancel=new JButton("Cancel",i2);

	JLabel l8=new JLabel("Status :");
	JTextField  status = new JTextField();

	JLabel l9=new JLabel("Product  Information  ");

	JLabel l10=new JLabel("Reason:",i9,10);
	JTextField reason =new JTextField();


	JTextField pid =new JTextField();
	JTextField pnam =new JTextField();
	JTextField pqty =new JTextField();
	JTextField ppri =new JTextField();
	JTextField pamt =new JTextField();
	JTextField ppen =new JTextField();

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

	int porderCount=0,i=0;
	String newOrd;
	boolean can=false;


	public POrder()
	{	
		super("Purchase Order");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(OrdNo);
		c.add(l2);
		c.add(ODate);
		c.add(l3);
		c.add(OSupl);
		c.add(l5);
        c.add(TAmt);
		c.add(l6);
		c.add(DAmt);
		c.add(l7);
		c.add(PAmt);
		c.add(l8);
		c.add(status);
		c.add(table);
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
		OSupl.setBounds(580,190,200,22);
		OSupl.setEditable(false);
		l3.setFont(new java.awt.Font("Courier New", 1, 13));
		OSupl.setFont(new java.awt.Font("Courier New", 1, 13));

		table.setBounds(20,60,535,320);
		QuoTbl.setRowHeight(25);
		QuoTbl.setFont(new java.awt.Font("Courier New", 1, 13));

		QuoTbl.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(pid));
		QuoTbl.getColumnModel().getColumn(1).setCellEditor( new DefaultCellEditor(pnam));
		QuoTbl.getColumnModel().getColumn(2).setCellEditor( new DefaultCellEditor(pqty));
		QuoTbl.getColumnModel().getColumn(3).setCellEditor( new DefaultCellEditor(ppri));
		QuoTbl.getColumnModel().getColumn(4).setCellEditor( new DefaultCellEditor(pamt));
		QuoTbl.getColumnModel().getColumn(5).setCellEditor( new DefaultCellEditor(ppen));

		QuoTbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumn column = null;
		column = QuoTbl.getColumnModel().getColumn(0);
		column.setPreferredWidth(80);
		column = QuoTbl.getColumnModel().getColumn(1);
		column.setPreferredWidth(150);
		column = QuoTbl.getColumnModel().getColumn(2);
		column.setPreferredWidth(70);
		column = QuoTbl.getColumnModel().getColumn(3);
		column.setPreferredWidth(60);
		column = QuoTbl.getColumnModel().getColumn(4);
		column.setPreferredWidth(80);
		column = QuoTbl.getColumnModel().getColumn(5);
		column.setPreferredWidth(90);

		pid.setEditable(false);
		pnam.setEditable(false);
		pqty.setEditable(false);
		ppri.setEditable(false);
		pamt.setEditable(false);
		ppen.setEditable(false);

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

		l5.setBounds(50,400,180,22);
		TAmt.setBounds(230,400,120,22);
		TAmt.setEditable(false);
		l5.setFont(new java.awt.Font("Courier New", 1, 13));
		TAmt.setFont(new java.awt.Font("Courier New", 1, 13));

		l8.setBounds(580,330,140,22);
		status.setBounds(650,330,120,22);
		status.setEditable(false);
		l8.setFont(new java.awt.Font("Courier New", 1, 13));
		status.setFont(new java.awt.Font("Courier New", 1, 13));

		l10.setBounds(450,480,140,22);
		reason.setBounds(520,480,200,22);
		reason.setEditable(false);
		l10.setFont(new java.awt.Font("Courier New", 1, 13));
		reason.setFont(new java.awt.Font("Courier New", 1, 13));
		l10.setVisible(false);
		reason.setVisible(false);

		l9.setBounds(180,20,270,26);
		l9.setFont(new java.awt.Font("papyrus", 1, 23));
		l9.setForeground(new Color(200,0,0));

		cmdNew.setBounds(450,400,130,48);
		cmdCancel.setBounds(600,400,130,48);
		cmdNew.setFont(new java.awt.Font("Courier New", 1, 12));
		cmdCancel.setFont(new java.awt.Font("Courier New", 1, 12));
		
		cmdNew.addActionListener(this);
		cmdNew.setMnemonic('n');
		
		cmdCancel.addActionListener(this);
		cmdCancel.setMnemonic('b');

		OrdNo.addActionListener(this);

		Border b=BorderFactory.createLineBorder(new Color(100,200,255));
		ODate.setBorder(b);
		OSupl.setBorder(b);
		DAmt.setBorder(b);
		PAmt.setBorder(b);
		TAmt.setBorder(b);
		status.setBorder(b);

        setSize(800,550);
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
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st.execute("use sales");
			rs=st.executeQuery("select * from POrder");
			rsmd=rs.getMetaData();
			st1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st1.execute("use sales");

			System.out.println("connected");


			while(rs.next())
			{
				OrdNo.addItem(rs.getString(1));
				porderCount++;
			}
			System.out.println(porderCount);
		}
		catch(Exception e)
		{
			JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}

		newOrd="o"+(porderCount+1);
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
			NewPOrder n = new NewPOrder(newOrd);
		}

		if(e.getSource()==OrdNo)
		{
			try
			{

				double[] PRI = new double[40];
				
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
				rs4=st4.executeQuery("select  Supl_Name from Supplier  where  Supl_Id='"+rs.getString(3)+"'");
				rs4.next();
				//System.out.println("ok 3 ");
				OSupl.setText(rs4.getString(1));
				System.out.println("ok  4");
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
				//System.out.println("ok  6");
				else //if(rs.getInt(5)==2)
				{
					status.setText("Canceled");
					can=true;
					l10.setVisible(true);
					reason.setVisible(true);

				}
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
						QuoTbl.setValueAt(rs.getString(x),i,2);
	
						rs1=st1.executeQuery("select  Pro_Name,Sales_Price  from  Product  where  Pro_Id='"+rsmd.getColumnLabel(x)+"'");
						rs1.next();
						double pri=rs1.getDouble(2);
						PRI[i]=pri;
						QuoTbl.setValueAt(rs1.getString(1),i,1);
						QuoTbl.setValueAt(rs1.getString(2),i,3);
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
					rs3=st3.executeQuery("select * from PInvoice where PIOrder_No='"+ord+"' ");
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
					rs3=st3.executeQuery("select * from POrder where POrder_No='"+ord+"' ");
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
					rs5=st5.executeQuery("select  Reason from POCancel  where  POCancel_No='"+num+"'");
					rs5.next();
					reason.setText(rs5.getString(1));

				}
				
				}
				else
				{
					ODate.setText("");
					OSupl.setText("");
					status.setText("");
					TAmt.setText("");
					PAmt.setText("");
					DAmt.setText("");
					for(int t=0;t<40;t++)
					{
					QuoTbl.setValueAt("",t,0);
					QuoTbl.setValueAt("",t,1);
					QuoTbl.setValueAt("",t,2);
					QuoTbl.setValueAt("",t,3);
					QuoTbl.setValueAt("",t,4);
					QuoTbl.setValueAt("",t,5);

					}
					l10.setVisible(false);
					reason.setVisible(false);
				}

			}
			catch(SQLException se2)
			{
				//JOptionPane e1=new JOptionPane();
			//e1.showMessageDialog(null,se2.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}

		

	}

public static void main(String [] agrs)
	{
		
		POrder s=new POrder();
	}

}   //close of class User










class NewPOrder extends JFrame  implements ActionListener ,ItemListener
{
    ImageIcon i4= new ImageIcon("Image/make.png");      
   ImageIcon i5= new ImageIcon("Image//back.png");
   ImageIcon i6= new ImageIcon("Image//calculator.png");

	JLabel l1=new JLabel("Order No :");   
	JTextField OrdNo =new JTextField();
	
	JLabel l2=new JLabel("Order Date :");
	JTextField ODate =new JTextField();

	JLabel l3=new JLabel("Supplier  :");
	JComboBox OSupl =new JComboBox();

	JLabel l5=new JLabel("Total Order Amount :");
	JTextField TAmt =new JTextField("0.00");

	JTextField  lb = new JTextField();
	JTextField lb1=new JTextField();

	String[][] row= new String[40][5];
	String[] column={"Product Name","Product Id ","Price","Quantity","Amount"};
	JTable QuoTbl= new JTable(row,column);
	JScrollPane table=new JScrollPane(QuoTbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JComboBox proid =new JComboBox();
	
	JButton cmdMake=new JButton("Make",i4);
	JButton cmdCancel=new JButton(i5);

	JButton cmdCal=new JButton("Calculate Amount",i6);
	
	JLabel l9=new JLabel("Product  Information  ");

	JTextField pname =new JTextField();
	JTextField ppri =new JTextField();
	JTextField pamt =new JTextField();

	static Connection con;
	static Statement st;
	static ResultSet rs;
	static ResultSetMetaData rsmd;
	static Statement st1;
	static ResultSet rs1;
	static Statement st2;
	static ResultSet rs2;

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


	public NewPOrder(String newOrd)
	{	
		super(" New Purchase Order");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(OrdNo);
		c.add(l2);
		c.add(ODate);
		c.add(l3);
		c.add(OSupl);
		c.add(l5);
        c.add(TAmt);
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
		OrdNo.setText(newOrd);
		l1.setFont(new java.awt.Font("Courier New", 1, 13));
		OrdNo.setFont(new java.awt.Font("Courier New", 1, 13));

		l2.setBounds(580,115,100,22);
		ODate.setBounds(680,115,100,22);
		ODate.setEditable(false);
		ODate.setText(currentDate);
		l2.setFont(new java.awt.Font("Courier New", 1, 13));
		ODate.setFont(new java.awt.Font("Courier New", 1, 13));


		l3.setBounds(580,160,100,22);
		OSupl.setBounds(580,190,200,22);
		OSupl.addItem("");
		l3.setFont(new java.awt.Font("Courier New", 1, 13));
		OSupl.setFont(new java.awt.Font("Courier New", 1, 13));

		table.setBounds(20,60,530,320);
		QuoTbl.setRowHeight(25);
		QuoTbl.setFont(new java.awt.Font("Courier New", 1, 13));

		QuoTbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumn column = null;
		column = QuoTbl.getColumnModel().getColumn(0);
		column.setPreferredWidth(150);
		column = QuoTbl.getColumnModel().getColumn(1);
		column.setPreferredWidth(90);
		column = QuoTbl.getColumnModel().getColumn(2);
		column.setPreferredWidth(90);
		column = QuoTbl.getColumnModel().getColumn(3);
		column.setPreferredWidth(100);
		column = QuoTbl.getColumnModel().getColumn(4);
		column.setPreferredWidth(100);
		//column = QuoTbl.getColumnModel().getColumn(5);
		//column.setPreferredWidth(70);

		l5.setBounds(350,475,160,22);
		TAmt.setBounds(530,475,120,22);
		TAmt.setEditable(false);
		l5.setFont(new java.awt.Font("Courier New", 1, 13));
		TAmt.setFont(new java.awt.Font("Courier New", 1, 13));

		l9.setBounds(180,20,270,26);
		l9.setFont(new java.awt.Font("papyrus", 1, 23));
		l9.setForeground(new Color(200,0,0));

		cmdMake.setBounds(80,420,115,40);
		cmdCancel.setBounds(210,420,115,40);
		cmdCal.setBounds(530,420,240,48);
		cmdMake.setFont(new java.awt.Font("Courier New", 1, 13));
		cmdCancel.setFont(new java.awt.Font("Courier New", 1, 13));
		cmdCal.setFont(new java.awt.Font("Courier New", 1, 13));
		
		cmdMake.addActionListener(this);
		cmdMake.setMnemonic('m');
		cmdMake.setEnabled(false);
		
		cmdCancel.addActionListener(this);
		cmdCancel.setMnemonic('c');

		cmdCal.addActionListener(this);
		cmdCal.setMnemonic('l');
		getRootPane().setDefaultButton(cmdCal);

		proid.addItemListener(this);
		proid.addItem("");

		QuoTbl.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(proid));
		QuoTbl.getColumnModel().getColumn(1).setCellEditor( new DefaultCellEditor(pname));
		QuoTbl.getColumnModel().getColumn(2).setCellEditor( new DefaultCellEditor(ppri));
		QuoTbl.getColumnModel().getColumn(4).setCellEditor( new DefaultCellEditor(pamt));

		pamt.setEditable(false);

		Border b=BorderFactory.createLineBorder(new Color(100,200,255));
		ODate.setBorder(b);
		OrdNo.setBorder(b);
		TAmt.setBorder(b);
		
        setSize(800,540);
		setResizable(false);
		setVisible(true);

		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");
			
			st1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st1.execute("use sales");
			st2=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st2.execute("use sales");
			rs1=st1.executeQuery("select Supl_Name,Supl_Id from Supplier where  Supl_Status=0 ");
			
			while(rs1.next())
			{
				OSupl.addItem(rs1.getString(1));
				supCnt++;
			}
		
			rs2=st2.executeQuery("select Pro_Id,Pro_Name,Sales_Price from Product where Pro_Status=0");
			
			while(rs2.next())
			{
				proid.addItem(rs2.getString(2));
				proCnt++;
			}
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st.execute("use sales");
			rs=st.executeQuery("select * from POrder");
			rs.next();
			
			System.out.println("connected");
		}
		catch(Exception e)
		{
			JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,"unable to connect"+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}

		ppri.setEditable(false);
		pname.setEditable(false);

	}
	
	public void itemStateChanged(ItemEvent ie)
	{
		//if(ie.getStateChange()==ie.SELECTED)
		if(ie.getSource()==proid)
		{
			if(ie.getStateChange()==ie.SELECTED)
			{
				int t1=0;
				try
				{
					t1=proid.getSelectedIndex();
					rowNo=QuoTbl.getSelectedRow();
					if(t1!=0)
					{
						rs2.absolute(t1);						
						QuoTbl.setValueAt(rs2.getString(1),rowNo,1);
						QuoTbl.setValueAt(rs2.getString(3),rowNo,2);
					}
					else
					{
						QuoTbl.setValueAt("",rowNo,1);
						QuoTbl.setValueAt("",rowNo,2);
					}

				}
				catch(Exception e3)
				{
					//JOptionPane e1=new JOptionPane();
					//e1.showMessageDialog(null,"unable to connect 1"+e3.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}

			}
		}
	}


	public void actionPerformed(ActionEvent e)
	{


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
					
					break;
				}
				for(int m=0;m<AMT.length();m++)
				{
					if(AMT.charAt(m)<48  || AMT.charAt(m)>57 )
					{
						JOptionPane err3=new JOptionPane();
						err3.showMessageDialog(null," You must enter numeric value for Quantity.","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
						c=false;
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
						err.showMessageDialog(null,"For order you must place one product at a time .\n        You have placed same product in order.  ","Record Insert Error",JOptionPane.ERROR_MESSAGE);
						b=false;
						cmdMake.setEnabled(false);
						break;
					}
					
				}
				if( b==false)
				break;	
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
			e1.showMessageDialog(null, se1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			POrder s=new POrder();
		}

		if(e.getSource()==cmdMake)
		{
			String[] PId = new String[rowSelCnt];
			String[] PQ = new String[rowSelCnt];
			int[] PQty = new int[rowSelCnt];

			String no="";
			String pono,podate,posupl=null,proId,sup;
			double poamt,proQty;
			int postatus=0;
			boolean b=true;

			pono=OrdNo.getText();
			podate=ODate.getText();
			sup=(String)OSupl.getSelectedItem();
			
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
				err4.showMessageDialog(null," Please select a Supplier.","Record Insert Error",JOptionPane.ERROR_MESSAGE);
				b=false;
			}
			
			if(b==true)
			{				
				try
				{
					rs1.beforeFirst();
					System.out.println("1");
					System.out.println("Supplier Id :"+sup);
					while(rs1.next())
					{
						System.out.println("Supplier Name :"+rs1.getString(2));
						//OSupl.addItem(rs1.getString(2));
						System.out.println("2");
						if(rs1.getString(1).equals(sup))
						{
							posupl=rs1.getString(2);
							System.out.println("Supplier Name :"+posupl);
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
					POrder s=new POrder();

				}
				catch(SQLException se4)
				{
					JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,se4.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}								
			}			
		}//end event
	}//end actionPerformed		
}