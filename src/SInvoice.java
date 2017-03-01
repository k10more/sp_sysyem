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

 class SInvoice extends JFrame  implements ActionListener 
{
	
	ImageIcon i4= new ImageIcon("Image//new.png");      
     ImageIcon i5= new ImageIcon("Image//back.png");      

	
	JLabel l1=new JLabel("Invoice No :");   
	JComboBox InvNo =new JComboBox();
	
	JLabel l2=new JLabel("Invoice Date:");
	JTextField InvDate =new JTextField();

	JLabel l3=new JLabel("Order No :");
	JTextField ONo =new JTextField();

	JLabel l4=new JLabel("Customer Name :");
	JTextField ICust =new JTextField();

	JLabel l6=new JLabel("Sales Tax :");
	JTextField STax =new JTextField("0.00");

	JLabel l7=new JLabel("Delivery Challan :");
	JTextField DCha =new JTextField("0.00");

	JLabel l5=new JLabel("Total Invoice Amount :");
	JTextField TAmt =new JTextField("0.00");

	JTextField  lb = new JTextField();
	JTextField lb1=new JTextField();

	JTextField  pi = new JTextField();

	String[][] row= new String[40][5];
	String[] column={"Product Id ","Product  Name ","Quantity","Price","Amount"};
	JTable QuoTbl= new JTable(row,column);
	JScrollPane table=new JScrollPane(QuoTbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JButton cmdNew=new JButton("New",i4);
	JButton cmdCancel=new JButton("Cancel",i5);
	
	JLabel l8=new JLabel("Status :");
	JTextField  status = new JTextField();

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
	static Statement st3;
	static ResultSet rs3;
	static Statement st4;
	static ResultSet rs4;

	int sinv=0,i=0;


	public SInvoice()
	{	
		super("Sales Invoice");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(InvNo);
		c.add(l2);
		c.add(InvDate);
		c.add(l3);
		c.add(ONo);
		c.add(l4);
		c.add(ICust);
		c.add(l5);
        c.add(TAmt);
		c.add(l6);
        c.add(STax);
		c.add(l7);
        c.add(DCha);
		c.add(l8);
		c.add(status);
		c.add(table);
		c.add(cmdNew);
		c.add(cmdCancel);
		c.add(l9);

		c.add(lb);
		lb.setBounds(565,0,5,395);
		lb.setBackground(new Color(100,200,250));
		lb.setEditable(false);
		
		
		c.add(lb1);
		lb1.setBounds(0,390,800,5);
		lb1.setBackground(new Color(100,200,250));
		lb1.setEditable(false);

		l1.setBounds(580,60,100,22);
		InvNo.setBounds(680,60,100,22);
		l1.setFont(new java.awt.Font("Courier New", 1, 13));
		InvNo.setFont(new java.awt.Font("Courier New", 1, 13));

		l2.setBounds(580,115,120,22);
		InvDate.setBounds(680,144,100,22);
		InvDate.setEditable(false);
		l2.setFont(new java.awt.Font("Courier New", 1, 13));
		InvDate.setFont(new java.awt.Font("Courier New", 1, 13));

		l3.setBounds(580,160,100,22);
		ONo.setBounds(580,190,200,22);
		ONo.setEditable(false);
		l3.setFont(new java.awt.Font("Courier New", 1, 13));
		ONo.setFont(new java.awt.Font("Courier New", 1, 13));

		l4.setBounds(580,220,150,22);
		ICust.setBounds(580,260,180,22);
		ICust.setEditable(false);
		l4.setFont(new java.awt.Font("Courier New", 1, 13));
		ICust.setFont(new java.awt.Font("Courier New", 1, 13));

		table.setBounds(20,60,535,320);
		QuoTbl.setRowHeight(25);
		QuoTbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumn column = null;
		column = QuoTbl.getColumnModel().getColumn(0);
		column.setPreferredWidth(90);
		column = QuoTbl.getColumnModel().getColumn(1);
		column.setPreferredWidth(165);
		column = QuoTbl.getColumnModel().getColumn(2);
		column.setPreferredWidth(85);
		column = QuoTbl.getColumnModel().getColumn(3);
		column.setPreferredWidth(85);
		column = QuoTbl.getColumnModel().getColumn(4);
		column.setPreferredWidth(110);
		QuoTbl.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(1).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(2).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(3).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(4).setCellEditor( new DefaultCellEditor(pi));
		pi.setEditable(false);
		QuoTbl.setFont(new java.awt.Font("Courier New", 1, 13));
		//OrdNo.setFont(new java.awt.Font("Courier New", 1, 13));

		l5.setBounds(50,480,180,22);
		TAmt.setBounds(230,480,120,22);
		TAmt.setEditable(false);
		l5.setFont(new java.awt.Font("Courier New", 1, 13));
		TAmt.setFont(new java.awt.Font("Courier New", 1, 13));

		l6.setBounds(50,440,180,22);
		STax.setBounds(230,440,120,22);
		STax.setEditable(false);
		l6.setFont(new java.awt.Font("Courier New", 1, 13));
		STax.setFont(new java.awt.Font("Courier New", 1, 13));

		l7.setBounds(50,400,180,22);
		DCha.setBounds(230,400,120,22);
		DCha.setEditable(false);
		l7.setFont(new java.awt.Font("Courier New", 1, 13));
		DCha.setFont(new java.awt.Font("Courier New", 1, 13));

		l8.setBounds(580,330,140,22);
		status.setBounds(650,330,120,22);
		status.setEditable(false);
		l8.setFont(new java.awt.Font("Courier New", 1, 13));
		status.setFont(new java.awt.Font("Courier New", 1, 13));

		l9.setBounds(180,20,270,26);
		l9.setFont(new java.awt.Font("papyrus", 1, 23));
		l9.setForeground(new Color(200,0,0));
		//OrdNo.setFont(new java.awt.Font("Courier New", 1, 13));

		cmdNew.setBounds(450,400,115,40);
		cmdCancel.setBounds(650,400,115,40);
		cmdNew.setFont(new java.awt.Font("Courier New", 1, 12));
		cmdCancel.setFont(new java.awt.Font("Courier New", 1, 12));
		
		cmdNew.addActionListener(this);
		cmdNew.setMnemonic('n');
		
		cmdCancel.addActionListener(this);
		cmdCancel.setMnemonic('b');

		InvNo.addItem("");
		InvNo.addActionListener(this);

		Border b=BorderFactory.createLineBorder(new Color(100,200,255));
		InvDate.setBorder(b);
		ONo.setBorder(b);
		TAmt.setBorder(b);
		status.setBorder(b);
		STax.setBorder(b);
		DCha.setBorder(b);
		ICust.setBorder(b);

        setSize(800,560);
		setResizable(false);
		setVisible(true);
		
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");
			st3=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st3.execute("use sales");
			st4=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st4.execute("use sales");
			st1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st1.execute("use sales");
			st2=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st2.execute("use sales");
			//rs2=st2.executeQuery("select Cust_Name from Customer ");
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st.execute("use sales");
			rs=st.executeQuery("select * from SInvoice");
			rsmd=rs.getMetaData();
			while(rs.next())
			{
				InvNo.addItem(rs.getString(1));
				sinv++;
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
			NewSInvoice n = new NewSInvoice(sinv+1);
		}




		if(e.getSource()==InvNo)
		{
			String supl;
			try
			{
				
				double total=0.0,stax=0,dcha=0;
				
				
				i=0;
				int no=InvNo.getSelectedIndex();
				String num=(String)InvNo.getSelectedItem();
				if(no!=0)
				{
				//rs.absolute(no);
				rs.beforeFirst();
				while(rs.next())
				{
					if(rs.getString(1).equals(num))
					{
						break;
					}
				}
				InvDate.setText(rs.getString(2));
				String cust=rs.getString(3);
				ONo.setText(rs.getString(3));
				
				//ISupl.setText(rs.getString(4));
				TAmt.setText(rs.getString(4));
				stax=rs.getDouble(5);
				STax.setText(rs.getString(5));
				dcha=rs.getDouble(6);
				DCha.setText(rs.getString(6));
				if(rs.getInt(7)==0)
				status.setText("Complete");
				else //if(rs.getInt(7)==1)
				status.setText("Material Returned");
				for(int x=8;x<=rsmd.getColumnCount();x++)
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
	
						rs1=st1.executeQuery("select  Pro_Name,Sales_Price  from  Product  where  Pro_Id='"+QuoTbl.getValueAt(i,0)+"'");
						rs1.next();
						double pri=rs1.getDouble(2);
						QuoTbl.setValueAt(rs1.getString(1),i,1);
						QuoTbl.setValueAt(rs1.getString(2),i,3);
						double amt=pri*qty;
						QuoTbl.setValueAt(""+amt,i,4);
						total = total + amt + stax + dcha;
						i++;

					}
				}
				TAmt.setText(""+total);

				rs4=st4.executeQuery("select SIOrder_No from SInvoice where SInvoice_No='"+num+"'    ");
				rs4.next();
				supl=rs4.getString(1);

				rs4=st4.executeQuery("select SQuo_No from SOrder where SOrder_No='"+supl+"'    ");
				rs4.next();
				supl=rs4.getString(1);
				rs4=st4.executeQuery("select QuoCust_Id from Quotation where Quo_No='"+supl+"'    ");
				rs4.next();
				supl=rs4.getString(1);
				rs4=st4.executeQuery("select Cust_Name from Customer where Cust_Id='"+supl+"'    ");
				rs4.next();
				supl=rs4.getString(1);
				ICust.setText(supl);


				for(int t=i;t<40-i;t++)
				{
					QuoTbl.setValueAt("",t,0);
					QuoTbl.setValueAt("",t,1);
					QuoTbl.setValueAt("",t,2);
					QuoTbl.setValueAt("",t,3);
					QuoTbl.setValueAt("",t,4);

				}

				
			}
				else
				{
					InvDate.setText("");
					ONo.setText("");
					TAmt.setText("");
					STax.setText("");
					DCha.setText("");
					status.setText("");
					ICust.setText("");
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





		

	}

public static void main(String [] agrs)
	{
		
		SInvoice s=new SInvoice();
	}

}   //close of class User










class NewSInvoice extends JFrame  implements ActionListener 
{
	ImageIcon i4= new ImageIcon("Image//filesave.png");      
	ImageIcon i5= new ImageIcon("Image//back.png");
	ImageIcon i6= new ImageIcon("Image//calculator.png");     

	
	
	JLabel l1=new JLabel("Invoice No :");   
	JTextField InvNo =new JTextField();
	
	JLabel l2=new JLabel("Invoice Date :");
	JTextField InvDate =new JTextField();

	JLabel l3=new JLabel("Customer Name :");
	JTextField ICust =new JTextField();

	JLabel l4=new JLabel("Order No :");
	JComboBox InvOrd =new JComboBox();

	JLabel l5=new JLabel("Total Invoice Amount :");
	JTextField TAmt =new JTextField("0.00");

	JLabel l6=new JLabel("Sales Tax :");
	JTextField STax =new JTextField();

	JLabel l7=new JLabel("Delivery Challan :");
	JTextField DCha =new JTextField();

	JTextField  lb = new JTextField();
	JTextField  lb1 = new JTextField();

	String[][] row= new String[40][5];
	String[] column={" Id ","Product  Name ","Price","Quantity","Amount"};
	JTable QuoTbl= new JTable(row,column);
	JScrollPane table=new JScrollPane(QuoTbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JComboBox proid =new JComboBox();
	
	JButton cmdMake=new JButton("Make",i4);
	JButton cmdCancel=new JButton("Cancel",i5);
	JButton cmdCal=new JButton("Calculate Amount",i6);

	JLabel l8=new JLabel("Status :");
	JComboBox  status = new JComboBox();
	
	JLabel l9=new JLabel("Product  Information  ");

	JTextField  pi = new JTextField();

	static Connection con;
	static Statement st;
	static ResultSet rs;
	static ResultSetMetaData rsmd2;
	static Statement st1;
	static ResultSet rs1;
	static Statement st2;
	static ResultSet rs2;
	static Statement st3;
	static ResultSet rs3;
	static Statement st4;
	static ResultSet rs4;

	int proCnt=0,inOr=0,rowSelCnt=0;

	boolean orc=false;//flag for cheking orde is completed or not


	java.util.Date date = new java.util.Date();
	DateFormat df = DateFormat.getDateInstance(3);
	String  date1,mon,datex,year,yearx,currentDate;
	int d,d1;


	public NewSInvoice(int newInv)
	{	
		super("New Sales Invoice");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(InvNo);
		c.add(l2);
		c.add(InvDate);
		c.add(l3);
		c.add(ICust);
		c.add(l4);
		c.add(InvOrd);
		c.add(l5);
        c.add(TAmt);
		c.add(l6);
        c.add(STax);
		c.add(l7);
        c.add(DCha);
		c.add(l8);
        c.add(status);
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

		l1.setBounds(580,60,100,22);
		InvNo.setBounds(680,60,100,22);
		InvNo.setEditable(false);
		InvNo.setText("i"+newInv);
		l1.setFont(new java.awt.Font("Courier New", 1, 13));
		InvNo.setFont(new java.awt.Font("Courier New", 1, 13));

		l2.setBounds(580,115,120,22);
		InvDate.setBounds(680,144,100,22);
		InvDate.setEditable(false);
		l2.setFont(new java.awt.Font("Courier New", 1, 13));
		InvDate.setFont(new java.awt.Font("Courier New", 1, 13));

		date1=df.format(date);
		d=date1.indexOf('/');
		mon=date1.substring(0,d);
		d1=date1.lastIndexOf('/');
		datex=date1.substring(d+1,d1);
		yearx=date1.substring(d1+1);
		year="20"+yearx;
		currentDate=year+"-"+mon+"-"+datex;
		System.out.println("current date  "+currentDate);
		InvDate.setText(currentDate);


		l3.setBounds(580,220,150,22);
		ICust.setBounds(580,260,180,22);
		ICust.setEditable(false);
		l3.setFont(new java.awt.Font("Courier New", 1, 13));
		ICust.setFont(new java.awt.Font("Courier New", 1, 13));

		l4.setBounds(580,160,100,22);
		InvOrd.setBounds(580,190,200,22);
		l4.setFont(new java.awt.Font("Courier New", 1, 13));
		InvOrd.setFont(new java.awt.Font("Courier New", 1, 13));
		
		table.setBounds(20,60,535,320);
		QuoTbl.setRowHeight(25);
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
		column.setPreferredWidth(110);
		QuoTbl.setFont(new java.awt.Font("Courier New", 1, 13));
		//status.setFont(new java.awt.Font("Courier New", 1, 13));

		l5.setBounds(470,480,180,22);
		TAmt.setBounds(650,480,120,22);
		TAmt.setEditable(false);
		l5.setFont(new java.awt.Font("Courier New", 1, 13));
		TAmt.setFont(new java.awt.Font("Courier New", 1, 13));

		l6.setBounds(470,440,180,22);
		STax.setBounds(650,440,120,22);
		//STax.setEditable(false);
		l6.setFont(new java.awt.Font("Courier New", 1, 13));
		STax.setFont(new java.awt.Font("Courier New", 1, 13));

		l7.setBounds(470,400,180,22);
		DCha.setBounds(650,400,120,22);
		l7.setFont(new java.awt.Font("Courier New", 1, 13));
		DCha.setFont(new java.awt.Font("Courier New", 1, 13));

		l8.setBounds(580,330,140,22);
		status.setBounds(650,330,120,22);
		l8.setFont(new java.awt.Font("Courier New", 1, 13));
		status.setFont(new java.awt.Font("Courier New", 1, 13));

		l9.setBounds(180,20,270,26);
		l9.setFont(new java.awt.Font("Papyrus", 1, 23));
		l9.setForeground(new Color(200,0,0));
		//status.setFont(new java.awt.Font("Courier New", 1, 13));

		cmdMake.setBounds(80,460,140,48);
		cmdCancel.setBounds(250,460,140,48);
		cmdCal.setBounds(80,400,250,48);
		
		cmdMake.addActionListener(this);
		cmdMake.setMnemonic('m');
		cmdMake.setEnabled(false);
		cmdMake.setFont(new java.awt.Font("Courier New", 1, 12));
		cmdCancel.setFont(new java.awt.Font("Courier New", 1, 12));
		cmdCal.setFont(new java.awt.Font("Courier New", 1, 12));
		
		cmdCancel.addActionListener(this);
		cmdCancel.setMnemonic('c');

		cmdCal.addActionListener(this);
		cmdCal.setMnemonic('l');

		InvOrd.addActionListener(this);
		InvOrd.addItem("");



		QuoTbl.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(1).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(2).setCellEditor( new DefaultCellEditor(pi));
		//QuoTbl.getColumnModel().getColumn(3).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(4).setCellEditor( new DefaultCellEditor(pi));
		pi.setEditable(false);

		Border b=BorderFactory.createLineBorder(new Color(100,200,255));
		InvDate.setBorder(b);
		InvNo.setBorder(b);
		TAmt.setBorder(b);
		//status.setBorder(b);
		STax.setBorder(b);
		DCha.setBorder(b);
		ICust.setBorder(b);

		status.addItem("");
		status.addItem("Complete");
		status.addItem("Mat. Return");

        setSize(800,560);
		setResizable(false);
		setVisible(true);

		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			rs2=st2.executeQuery("select * from SOrder where SOStatus=0");
			rsmd2=rs2.getMetaData();
			while(rs2.next())
			{
				InvOrd.addItem(rs2.getString(1));
			}
		
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st.execute("use sales");
			rs=st.executeQuery("select * from SInvoice");
			rs.next();
			
			System.out.println("connected");
			//System.out.println("connected");
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
			SInvoice s=new SInvoice();
		}


		if(e.getSource()==InvOrd)
		{
			try
			{
				
				double total=0.0;
				int i=0;
				int no=InvOrd.getSelectedIndex();
				String num=(String)InvOrd.getSelectedItem();
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
				
				String supl;//=rs2.getString(3);
				rs4=st4.executeQuery("select SQuo_No from SOrder where SOrder_No='"+num+"'    ");
				rs4.next();
				supl=rs4.getString(1);
				rs4=st4.executeQuery("select QuoCust_Id from Quotation where Quo_No='"+supl+"'    ");
				rs4.next();
				supl=rs4.getString(1);
				rs4=st4.executeQuery("select Cust_Name from Customer where Cust_Id='"+supl+"'    ");
				rs4.next();
				supl=rs4.getString(1);
				ICust.setText(supl);
				

				}
				else
				{
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
						err7.showMessageDialog(null," Please select the order to which create a invoice.  ","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
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
				rowSelCnt++;
				System.out.println("row cnt  "+rowSelCnt);
			}
			if(STax.getText().equals(no)  ||  DCha.getText().equals(no))
			{
				JOptionPane err4=new JOptionPane();
				err4.showMessageDialog(null," You must enter Sales Tax value Or Delivary Challan value.","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
				 b=false;
			}
			if(b==true)
			{
				String temp1=STax.getText();
				for(int m=0;m<temp1.length();m++)
				{
					if((temp1.charAt(m)<48  || temp1.charAt(m)>57) && temp1.charAt(m)!=46 )
					{
						JOptionPane err5=new JOptionPane();
						err5.showMessageDialog(null," You must enter numeric value for Sales Tax.","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
						STax.setText("");
						STax.requestFocusInWindow();
						b=false;
						break;
					}
				}
				String temp2=DCha.getText();
				for(int m=0;m<temp2.length();m++)
				{
					if((temp2.charAt(m)<48  || temp2.charAt(m)>57)  &&  temp2.charAt(m)!=46 )
					{
						JOptionPane err6=new JOptionPane();
						err6.showMessageDialog(null," You must enter numeric value for Delivary Challan.","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
						DCha.setText("");
						DCha.requestFocusInWindow();
						b=false;
						break;
					}
				}

			}

			

			if(b==true && c==true)
			{
				int[] qty1 = new int[rowSelCnt];//invoice qty
				int[] qty2 = new int[rowSelCnt];//order qty
				int[] qty3 = new int[rowSelCnt];//table qty
				int[] diff = new int[rowSelCnt];//diff of order & invoice
				
				String ord=(String)InvOrd.getSelectedItem();
				try
				{
					rs3=st3.executeQuery("select * from SInvoice where SIOrder_No='"+ord+"' ");
					while(rs3.next())
					{
						for(int t=0;t<rowSelCnt;t++)
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
						for(int t=0;t<rowSelCnt;t++)
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
					JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,e3.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}

				for(int t=0;t<rowSelCnt;t++)
				{
					diff[t]=qty2[t]-qty1[t];
					System.out.println("diff  "+diff[t]);	
				}

				for(int t=0;t<rowSelCnt;t++)
				{
					String tesQt=(String)QuoTbl.getValueAt(t,3);
					int te=Integer.parseInt(tesQt);;
					qty3[t]=te;
					System.out.println("qty3  "+qty3[t]);
				}

				for(int t=0;t<rowSelCnt;t++)
				{
					if(qty3[t]>diff[t])
					{
						JOptionPane err7=new JOptionPane();
						err7.showMessageDialog(null," In Order '"+ord+"' for Product '"+(String)QuoTbl.getValueAt(t,0)+"' ordered quantity is '"+qty2[t]+"' and dispatched quantity is '"+qty1[t]+"'.\n                                 You enter quantity less or equal to "+diff[t]+" .","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
						d=false;
						cmdMake.setEnabled(false);
						break;
					}
					
				}
				if(d==true)
				{
					for(int t=0;t<rowSelCnt;t++)
					{
						if(qty3[t]==diff[t])
						{
							orc=true;
							System.out.println(" Order completed");
						}
						else
						{
							orc=false;
							break;
						}
					}
				}
				if(b==true  &&  c==true  &&  d==true )
				{
					String spri,sqty;
					double dpri,damt,dtamt=0,dstax,ddcha;
					int dqty;
					dstax=Double.parseDouble(STax.getText());
					ddcha=Double.parseDouble(DCha.getText());
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
					dtamt = dtamt + dstax + ddcha;
					TAmt.setText(""+dtamt);

					cmdMake.setEnabled(true);
					System.out.println("ok");
				}
			}//end if
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

			pino=InvNo.getText();
			pidate=InvDate.getText();
			pior=(String)InvOrd.getSelectedItem();
			pista=(String)status.getSelectedItem();
			piamt=Double.parseDouble(TAmt.getText());
			stax=Double.parseDouble(STax.getText());
			dcha=Double.parseDouble(DCha.getText());

			for(int i=0;i<rowSelCnt;i++)
			{
				PId[i]=(String)QuoTbl.getValueAt(i,0);
				PQ[i]=(String)QuoTbl.getValueAt(i,3);
				PQty[i]=Integer.parseInt(PQ[i]);
			}

			if(pista.equals(no))
			{
				JOptionPane err9=new JOptionPane();
				err9.showMessageDialog(null," Please select status of the invoice.","Record Insert Error",JOptionPane.ERROR_MESSAGE);
				b=false;
			}
			if(b==true)
			{
				int xy=status.getSelectedIndex();
				System.out.println("Get selected index"+xy);
				if(xy==1)
					pistatus=0;
				else
					pistatus=1;
				try
				{
					rs.moveToInsertRow(); // moves cursor to the insert row
					rs.updateString(1,pino); 
					rs.updateString(2,pidate); 
					rs.updateString(3,pior); 
					rs.updateDouble(4,piamt);
					rs.updateDouble(5,stax);
					rs.updateDouble(6,dcha);
					rs.updateInt(7,pistatus);

					for(int i=0;i<rowSelCnt;i++)
					{
						rs.updateInt(PId[i],PQty[i]);
					}
					
					rs.insertRow();
					rs.moveToCurrentRow();

					for(int i=0;i<rowSelCnt;i++)
					{
						st3.executeUpdate("update Inventory set Qty = Qty - "+PQty[i]+" where InvPro_Id='"+PId[i]+"' ");
					}
	
					if(orc==true)
					{
						st3.executeUpdate("update SOrder set SOStatus=1 where SOrder_No='"+pior+"'  ");
					}
					System.out.println("save");
					setVisible(false);
					SInvoice s=new SInvoice();
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