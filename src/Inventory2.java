import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.lang.*;
import java.text.*;
import javax.swing.table.*;

 class Inventory2 extends JFrame implements ActionListener 
{
	ImageIcon i4= new ImageIcon("Image//back.png");      
	ImageIcon i5= new ImageIcon("Image//app.png");      

	JButton cmdCancel=new JButton("Cancel",i4);
	JButton print=new JButton("Report",i5);
	
	JLabel l1=new JLabel("Inventory Details  ");

	JLabel ld=new JLabel("Date : ");
	JLabel dtt=new JLabel("jhg");

	JTextField p =new JTextField();

	String[][] row= new String[20][8];
	String[] column={" Id ","Product Name","Price","Quantity","Minimum Level","Maximum Level","Average Level","Reorder Lelvel"};
	JTable QuoTbl= new JTable(row,column);
	JScrollPane table=new JScrollPane(QuoTbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
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
	static ResultSetMetaData rsmd3;
	static Statement st4;
	static ResultSet rs4;
	static ResultSetMetaData rsmd4;
	static Statement st5;
	static ResultSet rs5;

	java.util.Date date = new java.util.Date();
	DateFormat df = DateFormat.getDateInstance(3);
	String  date1,mon,datex,year,yearx,currentDate;
	int d,d1;

	String prname[] = new String[100];  //array for product name
	int re[] = new int[100];//array for product qty pending purchase
	int pqop[] = new int[100];
	int max[] = new int[100];
	int avg[] = new int[100];

	String pn[]= new String[100]; 
	String pq[]= new String[100]; 
	String mi[]= new String[100]; 
	String ma[]= new String[100]; 
	String av[]= new String[100]; 
	String reo[]= new String[100]; 
	String ner[]= new String[10]; 

	int i=0,j=0,k=0,l=0;

	int rq=0;

	String  date2,year2,mon3,day2;
	int m,mon2,y;
	
	public Inventory2()
	{	
		super("Inventory Details");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(table);
		c.add(cmdCancel);
		c.add(print);
		c.add(l1);
		//c.add(ld);
		//c.add(dtt);

		date1=df.format(date);
		d=date1.indexOf('/');
		mon=date1.substring(0,d);
		d1=date1.lastIndexOf('/');
		datex=date1.substring(d+1,d1);
		yearx=date1.substring(d1+1);
		year="20"+yearx;
		currentDate=year+"-"+mon+"-"+datex;
		System.out.println("datex  "+datex);
		System.out.println("mon  "+mon);
		System.out.println("year  "+year);
		System.out.println("current date  "+currentDate);

		day2=datex;
		//mon2=mon;
		y=Integer.parseInt(yearx);

		mon2=Integer.parseInt(mon);

		if(mon2==1)
		{
			y=y-1;
			mon2=12;
			date2 = "20"+y+"-"+mon2+"-"+day2;
		}
		else
		{
			mon2 = mon2-1;
		}
		date2 = "20"+yearx+"-"+mon2+"-"+day2;
		
		System.out.println("date2  "+date2);

		l1.setBounds(300,5,350,50);
		l1.setFont(new java.awt.Font("papyrus", 1, 25));
		l1.setForeground(new Color(200,0,0));

		ld.setBounds(570,35,100,25);
		ld.setFont(new java.awt.Font("Courier ", 1, 12));
		dtt.setBounds(610,35,300,25);
		dtt.setFont(new java.awt.Font("Courier ", 1, 12));
		dtt.setText(""+date);
		
		table.setBounds(60,70,680,330);
		QuoTbl.setRowHeight(25);
		QuoTbl.setFont(new java.awt.Font("Courier New", 1, 13));
		QuoTbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumn column = null;
		column = QuoTbl.getColumnModel().getColumn(0);
		column.setPreferredWidth(50);
		column = QuoTbl.getColumnModel().getColumn(1);
		column.setPreferredWidth(150);
		column = QuoTbl.getColumnModel().getColumn(2);
		column.setPreferredWidth(80);
		column = QuoTbl.getColumnModel().getColumn(3);
		column.setPreferredWidth(80);
		column = QuoTbl.getColumnModel().getColumn(4);
		column.setPreferredWidth(80);
		column = QuoTbl.getColumnModel().getColumn(5);
		column.setPreferredWidth(80);
		column = QuoTbl.getColumnModel().getColumn(6);
		column.setPreferredWidth(80);
		column = QuoTbl.getColumnModel().getColumn(7);
		column.setPreferredWidth(80);
		

		cmdCancel.setBounds(100,440,125,48);
		cmdCancel.addActionListener(this);
		cmdCancel.setMnemonic('b');
		cmdCancel.setFont(new java.awt.Font("Courier New", 1, 13));

		print.setBounds(350,440,125,48);
		print.addActionListener(this);
		print.setMnemonic('r');
		print.setFont(new java.awt.Font("Courier New", 1, 13));

        setSize(800,540);
		setResizable(false);
		setVisible(true);

		QuoTbl.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(p));
		QuoTbl.getColumnModel().getColumn(1).setCellEditor( new DefaultCellEditor(p));
		QuoTbl.getColumnModel().getColumn(2).setCellEditor( new DefaultCellEditor(p));
		QuoTbl.getColumnModel().getColumn(3).setCellEditor( new DefaultCellEditor(p));
		QuoTbl.getColumnModel().getColumn(4).setCellEditor( new DefaultCellEditor(p));
		QuoTbl.getColumnModel().getColumn(5).setCellEditor( new DefaultCellEditor(p));
		QuoTbl.getColumnModel().getColumn(6).setCellEditor( new DefaultCellEditor(p));
		QuoTbl.getColumnModel().getColumn(7).setCellEditor( new DefaultCellEditor(p));
		
		
        p.setEditable(false);

		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");
			st5=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st5.execute("use sales");
			st4=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st4.execute("use sales");
			st3=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st3.execute("use sales");
			st2=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st2.execute("use sales");
			st1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st1.execute("use sales");
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st.execute("use sales");
			rs=st.executeQuery("select * from Inventory");
			//rsmd=rs.getMetaData();
			while(rs.next())
			{
				QuoTbl.setValueAt(rs.getString(2),i,3);
				i++;
			}
			rs5=st5.executeQuery("select Pro_Id,Pro_Name,Sales_Price from Product ");
			while(rs5.next())
			{
				QuoTbl.setValueAt(rs5.getString(1),l,0);
				prname[l]=rs5.getString(1);
				QuoTbl.setValueAt(rs5.getString(2),l,1);
				QuoTbl.setValueAt(rs5.getString(3),l,2);
				l++;
			}				
			//code for qty ordered purchase
			rs1=st1.executeQuery("select * from SOrder where SOrder_Date between '"+date2+"' and '"+currentDate+"'  and  SOStatus=0     ");
			rsmd1=rs1.getMetaData();
			System.out.println("SUCCESS");
			while(rs1.next())
			{
				System.out.println("SUCCESS1");
				for(int x=6;x<=rsmd1.getColumnCount();x++)
				{
					for(int a=0;a<i;a++)
					{
						System.out.println("SUCCESS3");
						if(prname[a].equals(rsmd1.getColumnLabel(x)))
						{
							pqop[a] = pqop[a] + rs1.getInt(x);
							System.out.println(pqop[a]);
							break;
						}
					}
				}
			}
			System.out.println("kk4");
			for(int a=0;a<i;a++)
			{
				QuoTbl.setValueAt(""+pqop[a],a,4);
			}

			for(int a=0;a<i;a++)
			{
				re[a] =  2*pqop[a];
			}
			for(int a=0;a<i;a++)
			{
				QuoTbl.setValueAt(""+re[a],a,7);
			}

			for(int a=0;a<i;a++)
			{
				rq = (int)Math.sqrt((int)(2*pqop[a]*12*36)/(0.18));
				max[a] = pqop[a] + rq;
				QuoTbl.setValueAt(""+max[a],a,5);

			}

			for(int a=0;a<i;a++)
			{
				rq = (int)Math.sqrt((int)(2*pqop[a]*12*36)/(0.18));
				avg[a] = pqop[a] + (rq/2);
				QuoTbl.setValueAt(""+avg[a],a,6);

			}

		}
		catch(SQLException se2)
		{
			JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,se2.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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

		if(e.getSource()==print)
		{
			ner[0]=date2;
			ner[1]=currentDate;

			for(int a=0;a<l;a++)
			{
				pn[a]=(String)QuoTbl.getValueAt(a,1);

				System.out.println("pn of a "+pn[a]);

				pq[a]=(String)QuoTbl.getValueAt(a,3);
				System.out.println("pq quantity "+pq[a]);

				mi[a]=(String)QuoTbl.getValueAt(a,4);
				System.out.println("mi mi"+mi[a]);	

				ma[a]=(String)QuoTbl.getValueAt(a,5);
				System.out.println("ma of ma "+ma[a]);

				av[a]=(String)QuoTbl.getValueAt(a,6);
				System.out.println("av of av"+av[a]);

				reo[a]=(String)QuoTbl.getValueAt(a,7);
				System.out.println("reo of reo"+pq[a]);

			}
			ReportInv r=new ReportInv(ner,pn,pq,mi,ma,av,reo,i);
			System.out.println(ner);
			System.out.println(pn);
			System.out.println(pq);
			System.out.println(mi);
			System.out.println(ma);
			System.out.println(av);
			System.out.println(reo);
			System.out.println(i);
			
		}

	}
	

public static void main(String [] agrs)
	{
		Inventory2 s=new Inventory2();
	}
}
