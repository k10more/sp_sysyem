import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import javax.swing.border.*;
import java.text.*;
import javax.swing.table.*;

 class PInvoice extends JFrame  implements ActionListener 
{

    ImageIcon i4= new ImageIcon("Image//back.png");      
    ImageIcon i5= new ImageIcon("Image//new.png");      

	JLabel l1=new JLabel("Invoice No :");   
	JComboBox InvNo =new JComboBox();
	
	JLabel l2=new JLabel("Invoice Date :");
	JTextField InvDate =new JTextField();

	JLabel l3=new JLabel("Order No :");
	JTextField ONo =new JTextField();

	JLabel l4=new JLabel("Supplier Name :");
	JTextField ISupl =new JTextField();

	JLabel l6=new JLabel("Sales Tax :");
	JTextField STax =new JTextField("0.00");

	JLabel l7=new JLabel("Delivery Challan :");
	JTextField DCha =new JTextField("0.00");

	JLabel l5=new JLabel("Total Invoice Amount :");
	JTextField TAmt =new JTextField("0.00");

	JTextField  lb = new JTextField();
	JTextField lb1=new JTextField();


	String[][] row= new String[40][5];
	String[] column={"Product  Id ","Product Name","Quantity","Price","Amount"};
	JTable QuoTbl= new JTable(row,column);
	JScrollPane table=new JScrollPane(QuoTbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JButton cmdNew=new JButton("New",i5);
	JButton cmdCancel=new JButton("Cancel",i4);
	
	JLabel l8=new JLabel("Status :");
	JTextField  status = new JTextField();

	JLabel l9=new JLabel("Product  Information  ");

	JTextField pid =new JTextField();
	JTextField pnam =new JTextField();
	JTextField pqty =new JTextField();
	JTextField ppri =new JTextField();
	JTextField pamt =new JTextField();

	static Connection con;
	static Statement st;
	static ResultSet rs;
	static ResultSetMetaData rsmd;
	static Statement st1;
	static ResultSet rs1;
	static ResultSetMetaData rsmd1;
	static Statement st2;
	static ResultSet rs2;

	int pinv=0,i;

	String newInv;



	public PInvoice()
	{	
		super("Purchase Invoice");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(InvNo);
		c.add(l2);
		c.add(InvDate);
		c.add(l3);
		c.add(ONo);
		c.add(l4);
		c.add(ISupl);
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
		InvNo.addItem("");
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
		ISupl.setBounds(580,260,180,22);
		ISupl.setEditable(false);
		l4.setFont(new java.awt.Font("Courier New", 1, 13));
		ISupl.setFont(new java.awt.Font("Courier New", 1, 13));

		table.setBounds(20,60,535,320);
		QuoTbl.setRowHeight(25);
		QuoTbl.setFont(new java.awt.Font("Courier New", 1, 13));

		QuoTbl.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(pid));
		QuoTbl.getColumnModel().getColumn(1).setCellEditor( new DefaultCellEditor(pnam));
		QuoTbl.getColumnModel().getColumn(2).setCellEditor( new DefaultCellEditor(pqty));
		QuoTbl.getColumnModel().getColumn(3).setCellEditor( new DefaultCellEditor(ppri));
		QuoTbl.getColumnModel().getColumn(4).setCellEditor( new DefaultCellEditor(pamt));

		pid.setEditable(false);
		pnam.setEditable(false);
		pqty.setEditable(false);
		ppri.setEditable(false);
		pamt.setEditable(false);

		QuoTbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumn column = null;
		column = QuoTbl.getColumnModel().getColumn(0);
		column.setPreferredWidth(100);
		column = QuoTbl.getColumnModel().getColumn(1);
		column.setPreferredWidth(160);
		column = QuoTbl.getColumnModel().getColumn(2);
		column.setPreferredWidth(80);
		column = QuoTbl.getColumnModel().getColumn(3);
		column.setPreferredWidth(80);
		column = QuoTbl.getColumnModel().getColumn(4);
		column.setPreferredWidth(90);

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
		status.setBounds(645,330,145,22);
		status.setEditable(false);
		l8.setFont(new java.awt.Font("Courier New", 1, 13));
		status.setFont(new java.awt.Font("Courier New", 1, 13));

		l9.setBounds(180,18,300,25);
		l9.setFont(new java.awt.Font("papyrus", 1, 23));
		l9.setForeground(new Color(200,0,0));

		cmdNew.setBounds(500,400,130,48);
		cmdCancel.setBounds(650,400,130,48);
		cmdNew.setFont(new java.awt.Font("Courier New", 1, 12));
		cmdCancel.setFont(new java.awt.Font("Courier New", 1, 12));
		
		cmdNew.addActionListener(this);
		cmdNew.setMnemonic('n');
		
		cmdCancel.addActionListener(this);
		cmdCancel.setMnemonic('c');

		InvNo.addActionListener(this);

		Border b=BorderFactory.createLineBorder(new Color(100,200,255));
		InvDate.setBorder(b);
		ONo.setBorder(b);
		ISupl.setBorder(b);
		TAmt.setBorder(b);
		STax.setBorder(b);
		DCha.setBorder(b);
		status.setBorder(b);
		
        setSize(800,560);
		setResizable(false);
		setVisible(true);
		
		try
		{

			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st.execute("use sales");
			rs=st.executeQuery("select * from PInvoice");
			rsmd=rs.getMetaData();
			st1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st1.execute("use sales");
			st2=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st2.execute("use sales");
			rs2=st2.executeQuery("select Supl_Id,Supl_Name from Supplier");


			System.out.println("connected");

			while(rs.next())
			{
				InvNo.addItem(rs.getString(1));
				pinv++;
			}
			System.out.println(pinv);
		}
		catch(Exception e)
		{
			JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}

		newInv="i"+(pinv+1);

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
			NewPInvoice n = new NewPInvoice(newInv);
		}

		if(e.getSource()==InvNo)
		{
			try
			{
				
				double total=0.0,stax=0,dcha=0;
				String sup;
				
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
				ONo.setText(rs.getString(3));
				//ISupl.setText(rs.getString(4));
				TAmt.setText(rs.getString(4));
				stax=rs.getDouble(5);
				STax.setText(rs.getString(5));
				dcha=rs.getDouble(6);
				DCha.setText(rs.getString(6));
				if(rs.getInt(7)==0)
				status.setText("Complete");
				else //if(rs.getInt(7)==1  || rs.getInt(7)==2)
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
				for(int t=i;t<40-i;t++)
				{
					QuoTbl.setValueAt("",t,0);
					QuoTbl.setValueAt("",t,1);
					QuoTbl.setValueAt("",t,2);
					QuoTbl.setValueAt("",t,3);
					QuoTbl.setValueAt("",t,4);

				}
				rs1=st1.executeQuery("select POSupl_Id from POrder where POrder_No='"+ONo.getText()+"' ");
				rs1.next();                            
				sup=rs1.getString(1);
				rs2.beforeFirst();
				while(rs2.next())
				{
					if(rs2.getString(1).equals(sup))
					{
						ISupl.setText(rs2.getString(2));
						break;
					}
				}
				//ISupl.setText(rs1.getString(1));
				}
				else
				{
					InvDate.setText("");
					ONo.setText("");
					TAmt.setText("");
					STax.setText("");
					DCha.setText("");
					status.setText("");
					ISupl.setText("");
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
			catch(SQLException se3)
			{
				JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,se3.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}

		

	}

public static void main(String [] agrs)
	{
		
		PInvoice s=new PInvoice();
	}

}   //close of class User










class NewPInvoice extends JFrame  implements ActionListener 
{
	ImageIcon i4= new ImageIcon("Image//filesave.png");      
	ImageIcon i5= new ImageIcon("Image//back.png");
	ImageIcon i6= new ImageIcon("Image//calculator.png");

	
	
	JLabel l1=new JLabel("Invoice No :");   
	JTextField InvNo =new JTextField();
	
	JLabel l2=new JLabel("Invoice Date :");
	JTextField InvDate =new JTextField();

	JLabel l4=new JLabel("Order No :");
	JComboBox InvOrd =new JComboBox();

	JLabel l3=new JLabel("Supplier Name :");
	JTextField ISupl =new JTextField();

	JLabel l5=new JLabel("Total Invoice Amount :");
	JTextField TAmt =new JTextField("0.00");

	JLabel l6=new JLabel("Sales Tax :");
	JTextField STax =new JTextField();

	JLabel l7=new JLabel("Delivery Challan :");
	JTextField DCha =new JTextField();

	JTextField  lb = new JTextField();
	JTextField  lb1 = new JTextField();

	String[][] row= new String[40][5];
	String[] column={"Product Id ","Product Name","Price","Quantity","Amount"};
	JTable QuoTbl= new JTable(row,column);
	JScrollPane table=new JScrollPane(QuoTbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JTextField proid =new JTextField();
	
	JButton cmdMake=new JButton("Make",i4);
	JButton cmdCancel=new JButton(i5);

	JLabel l8=new JLabel("Status :");
	JComboBox  status = new JComboBox();

	JButton cmdCal=new JButton("Calculate Amount",i6);
	
	JLabel l9=new JLabel("Product  Information  ");

	JTextField  pnm = new JTextField();
	JTextField  ppr = new JTextField();
	JTextField  pam = new JTextField();

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

	java.util.Date date = new java.util.Date();
	DateFormat df = DateFormat.getDateInstance(3);
	String  date1,mon,datex,year,yearx,currentDate;
	int d,d1;

	boolean orc=false;//flag for cheking orde is completed or not


	public NewPInvoice(String newInv)
	{	
		super("New Purchase Invoice");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(InvNo);
		c.add(l2);
		c.add(InvDate);
		c.add(l3);
		c.add(ISupl);
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
		InvNo.setText(newInv);
		InvNo.setEditable(false);
		l1.setFont(new java.awt.Font("Courier New", 1, 13));
		InvNo.setFont(new java.awt.Font("Courier New", 1, 13));

		date1=df.format(date);
		d=date1.indexOf('/');
		mon=date1.substring(0,d);
		d1=date1.lastIndexOf('/');
		datex=date1.substring(d+1,d1);
		yearx=date1.substring(d1+1);
		year="20"+yearx;
		currentDate=year+"-"+mon+"-"+datex;
		System.out.println("current date  "+currentDate);


		l2.setBounds(580,115,120,22);
		InvDate.setBounds(680,144,100,22);
		InvDate.setText(currentDate);
		InvDate.setEditable(false);
		l2.setFont(new java.awt.Font("Courier New", 1, 13));
		InvDate.setFont(new java.awt.Font("Courier New", 1, 13));

		l4.setBounds(580,160,100,22);
		InvOrd.setBounds(580,190,200,22);
		l4.setFont(new java.awt.Font("Courier New", 1, 13));
		InvOrd.setFont(new java.awt.Font("Courier New", 1, 13));

		l3.setBounds(580,220,150,22);
		ISupl.setBounds(580,260,180,22);
		ISupl.setEditable(false);
		l3.setFont(new java.awt.Font("Courier New", 1, 13));
		ISupl.setFont(new java.awt.Font("Courier New", 1, 13));

		l8.setBounds(580,330,140,22);
		status.setBounds(645,330,143,22);
		l8.setFont(new java.awt.Font("Courier New", 1, 13));
		status.setFont(new java.awt.Font("Courier New", 1, 13));
		
		table.setBounds(20,60,535,320);
		QuoTbl.setRowHeight(25);
		QuoTbl.setFont(new java.awt.Font("Courier New", 1, 13));
		QuoTbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumn column = null;
		column = QuoTbl.getColumnModel().getColumn(0);
		column.setPreferredWidth(80);
		column = QuoTbl.getColumnModel().getColumn(1);
		column.setPreferredWidth(170);
		column = QuoTbl.getColumnModel().getColumn(2);
		column.setPreferredWidth(75);
		column = QuoTbl.getColumnModel().getColumn(3);
		column.setPreferredWidth(85);
		column = QuoTbl.getColumnModel().getColumn(4);
		column.setPreferredWidth(120);
		
		l5.setBounds(470,480,180,22);   
		TAmt.setBounds(650,480,120,22);
		TAmt.setEditable(false);
		l5.setFont(new java.awt.Font("Courier New", 1, 13));
		TAmt.setFont(new java.awt.Font("Courier New", 1, 13));

		l6.setBounds(470,440,180,22);
		STax.setBounds(650,440,120,22);
		l6.setFont(new java.awt.Font("Courier New", 1, 13));
		STax.setFont(new java.awt.Font("Courier New", 1, 13));
		//STax.setEditable(false);

		l7.setBounds(470,400,180,22);
		DCha.setBounds(650,400,120,22);
		l7.setFont(new java.awt.Font("Courier New", 1, 13));
		DCha.setFont(new java.awt.Font("Courier New", 1, 13));
		//DCha.setEditable(false);

		l9.setBounds(180,20,290,25);
		l9.setFont(new java.awt.Font("papyrus", 1, 23));
		l9.setForeground(new Color(200,0,0));

		cmdMake.setBounds(80,460,140,48);
		cmdCancel.setBounds(250,460,140,48);
		cmdCal.setBounds(80,400,250,48);
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

		InvOrd.addActionListener(this);

		InvOrd.addItem("");

		status.addItem("");
		status.addItem("Complete");
		status.addItem("Material Returned");

		QuoTbl.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(proid));
		QuoTbl.getColumnModel().getColumn(1).setCellEditor( new DefaultCellEditor(pnm));
		QuoTbl.getColumnModel().getColumn(2).setCellEditor( new DefaultCellEditor(ppr));
		QuoTbl.getColumnModel().getColumn(4).setCellEditor( new DefaultCellEditor(pam));
		proid.setEditable(false);
		pnm.setEditable(false);
		ppr.setEditable(false);
		pam.setEditable(false);

		Border b=BorderFactory.createLineBorder(new Color(100,200,255));
		InvNo.setBorder(b);
		InvDate.setBorder(b);
		TAmt.setBorder(b);
		STax.setBorder(b);
		DCha.setBorder(b);
		ISupl.setBorder(b);
		
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
			rs2=st2.executeQuery("select * from POrder where POStatus=0");
			rsmd2=rs2.getMetaData();
			while(rs2.next())
			{
				InvOrd.addItem(rs2.getString(1));
			}
		
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st.execute("use sales");
			rs=st.executeQuery("select * from PInvoice");
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
			PInvoice s=new PInvoice();
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
				String supl=rs2.getString(3);
				rs4=st4.executeQuery("select Supl_Name from Supplier where Supl_Id='"+supl+"'    ");
				rs4.next();
				supl=rs4.getString(1);
				ISupl.setText(supl);

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
					break;
				}
				for(int m=0;m<QTY.length();m++)
				{
					if(QTY.charAt(m)<48  || QTY.charAt(m)>57 )
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
			if(STax.getText().equals(no)  ||  DCha.getText().equals(no))
			{
				JOptionPane err4=new JOptionPane();
				err4.showMessageDialog(null," You must enter Sales Tax value Or Delivery Challan value.","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
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
						err6.showMessageDialog(null," You must enter numeric value for Delivery Challan.","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
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
					rs3=st3.executeQuery("select * from PInvoice where PIOrder_No='"+ord+"' ");
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
					rs3=st3.executeQuery("select * from POrder where POrder_No='"+ord+"' ");
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
						st3.executeUpdate("update Inventory set Qty = Qty + "+PQty[i]+" where InvPro_Id='"+PId[i]+"' ");
					}
	
					if(orc==true)
					{
						st3.executeUpdate("update POrder set POStatus=1 where POrder_No='"+pior+"'  ");
					}
					System.out.println("save");
					setVisible(false);
					PInvoice s=new PInvoice();


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