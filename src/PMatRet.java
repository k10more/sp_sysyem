import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import javax.swing.border.*;
import javax.swing.table.*;

 class PMatRet extends JFrame  implements ActionListener 
{

	ImageIcon i4= new ImageIcon("Image//new.png");      
	ImageIcon i5= new ImageIcon("Image//back.png");      



	JLabel l1=new JLabel("Mat.Ret.No :");   
	JComboBox SMNo =new JComboBox();
	
	JLabel l2=new JLabel("Invoice Date:");
	JTextField SMDate =new JTextField();

	JLabel l3=new JLabel("Invoice No :");
	JTextField SMINo =new JTextField();

	JLabel l4=new JLabel("Supplier Name :");
	JTextField ISupl =new JTextField();

	JLabel l5=new JLabel("Total Returned Amount :");
	JTextField TAmt =new JTextField("0.00");

	JLabel l6=new JLabel("Order No :");
	JTextField ono =new JTextField();

	JTextField  lb = new JTextField();
	JTextField lb1=new JTextField();


	String[][] row= new String[40][5];
	String[] column={"Product Id "," Product  Name","Price","Qty Returned","Amount"};
	JTable QuoTbl= new JTable(row,column);
	JScrollPane table=new JScrollPane(QuoTbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JButton cmdNew=new JButton("New",i4);
	JButton cmdCancel=new JButton("Cancel",i5);

	JTextField pi =new JTextField();
	
	JLabel l9=new JLabel("Product  Information  ");

	static Connection con;
	static Statement st;
	static ResultSet rs;
	static ResultSetMetaData rsmd;
	static Statement st1;
	static ResultSet rs1;

	int pmrCount=0,i=0;


	public PMatRet()
	{	
		super("Purchase Material Return");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(SMNo);
		c.add(l2);
		c.add(SMDate);
		c.add(l3);
		c.add(SMINo);
		c.add(l4);
		c.add(ISupl);
		c.add(l5);
        c.add(TAmt);
		c.add(l6);
        c.add(ono);
		c.add(table);
		c.add(cmdNew);
		c.add(cmdCancel);
		c.add(l9);

		c.add(lb);
		lb.setBounds(535,0,5,446);
		lb.setBackground(new Color(100,200,250));
		lb.setEditable(false);

		c.add(lb1);
		lb1.setBounds(0,440,800,5);
		lb1.setBackground(new Color(100,200,250));
		lb1.setEditable(false);
		
		
		l1.setBounds(550,60,100,22);
		SMNo.setBounds(680,60,100,22);
		l1.setFont(new java.awt.Font("Courier New", 1, 13));
		SMNo.setFont(new java.awt.Font("Courier New", 1, 13));

		l2.setBounds(550,180,120,22);
		SMDate.setBounds(680,180,100,22);
		SMDate.setEditable(false);
		l2.setFont(new java.awt.Font("Courier New", 1, 13));
		SMDate.setFont(new java.awt.Font("Courier New", 1, 13));

		l3.setBounds(550,120,100,22);
		SMINo.setBounds(680,120,100,22);
		SMINo.setEditable(false);
		l3.setFont(new java.awt.Font("Courier New", 1, 13));
		SMINo.setFont(new java.awt.Font("Courier New", 1, 13));

		l4.setBounds(550,280,150,22);
		ISupl.setBounds(550,330,180,22);
		ISupl.setEditable(false);
		l4.setFont(new java.awt.Font("Courier New", 1, 13));
		ISupl.setFont(new java.awt.Font("Courier New", 1, 13));

		QuoTbl.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(1).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(2).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(3).setCellEditor( new DefaultCellEditor(pi));
		QuoTbl.getColumnModel().getColumn(4).setCellEditor( new DefaultCellEditor(pi));
		pi.setEditable(false);
		QuoTbl.setFont(new java.awt.Font("Courier New", 1, 13));
		QuoTbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumn column = null;
		column = QuoTbl.getColumnModel().getColumn(0);
		column.setPreferredWidth(80);
		column = QuoTbl.getColumnModel().getColumn(1);
		column.setPreferredWidth(160);
		column = QuoTbl.getColumnModel().getColumn(2);
		column.setPreferredWidth(80);
		column = QuoTbl.getColumnModel().getColumn(3);
		column.setPreferredWidth(90);
		column = QuoTbl.getColumnModel().getColumn(4);
		column.setPreferredWidth(90);
		

		table.setBounds(20,60,500,370);
		QuoTbl.setRowHeight(25);

		l5.setBounds(450,480,170,22);
		TAmt.setBounds(630,480,130,22);
		TAmt.setEditable(false);
		l5.setFont(new java.awt.Font("Courier New", 1, 13));
		TAmt.setFont(new java.awt.Font("Courier New", 1, 13));

		l6.setBounds(550,240,100,22);
		ono.setBounds(680,240,100,22);
		ono.setEditable(false);
		l6.setFont(new java.awt.Font("Courier New", 1, 13));
		ono.setFont(new java.awt.Font("Courier New", 1, 13));

		l9.setBounds(180,20,270,25);
		l9.setFont(new java.awt.Font("papyrus", 1, 23));
		l9.setForeground(new Color(200,0,0));

		cmdNew.setBounds(120,470,130,48);
		cmdCancel.setBounds(280,470,130,48);
		cmdNew.setFont(new java.awt.Font("Courier New", 1, 12));
		cmdCancel.setFont(new java.awt.Font("Courier New", 1, 12));
		
		cmdNew.addActionListener(this);
		cmdNew.setMnemonic('n');
		
		cmdCancel.addActionListener(this);
		cmdCancel.setMnemonic('b');

		Border b=BorderFactory.createLineBorder(new Color(100,200,255));
		SMINo.setBorder(b);
		ISupl.setBorder(b);
		TAmt.setBorder(b);
		SMDate.setBorder(b);
		ono.setBorder(b);

		SMNo.addItem("");
		SMNo.addActionListener(this);

        setSize(800,570);
		setResizable(false);
		setVisible(true);
		
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");
			st1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st1.execute("use sales");
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st.execute("use sales");
			rs=st.executeQuery("select * from PMRet");
			rsmd=rs.getMetaData();

			while(rs.next())
			{
				SMNo.addItem(rs.getString(1));
				pmrCount++;
			}
			System.out.println(pmrCount);



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

		if(e.getSource()==SMNo)
		{
			try
			{
				
				double total=0.0,stax=0,dcha=0;
				String sup;
				
				i=0;
				int no=SMNo.getSelectedIndex();
				String num=(String)SMNo.getSelectedItem();
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

				SMINo.setText(rs.getString(2));
				rs1=st1.executeQuery("select PIOrder_No,PInvoice_Date from PInvoice where PInvoice_No='"+SMINo.getText()+"'  ");
				rs1.next();
				String ordNum=rs1.getString(1);
				ono.setText(rs1.getString(1));
				SMDate.setText(rs1.getString(2));
				rs1=st1.executeQuery("select POSupl_Id from POrder where POrder_No='"+ordNum+"'  ");
				rs1.next();
				String ordSup=rs1.getString(1);
				rs1=st1.executeQuery("select Supl_Name from Supplier where Supl_Id='"+ordSup+"'  ");
				rs1.next();
				ISupl.setText(rs1.getString(1));
				System.out.println(rs1.getString(1));



				
			
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
	
						rs1=st1.executeQuery("select  Pro_Name,Sales_Price  from  Product  where  Pro_Id='"+QuoTbl.getValueAt(i,0)+"'");
						rs1.next();
						double pri=rs1.getDouble(2);
						QuoTbl.setValueAt(rs1.getString(1),i,1);
						QuoTbl.setValueAt(rs1.getString(2),i,2);
						double amt=pri*qty;
						QuoTbl.setValueAt(""+amt,i,4);
						total = total + amt ;
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
				
				
				
				}
				else
				{
					SMINo.setText("");
					ISupl.setText("");
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
			NewPMatRet n = new NewPMatRet(pmrCount+1);
		}

		

	}

public static void main(String [] agrs)
	{
		
		PMatRet s=new PMatRet();
	}

}   //close of class User










class NewPMatRet extends JFrame  implements ActionListener 
{
	ImageIcon i4= new ImageIcon("Image//make.png");      
	ImageIcon i5= new ImageIcon("Image//back.png");
	ImageIcon i6= new ImageIcon("Image//calculator.png");
     

	
	
	JLabel l1=new JLabel("Mat.Ret.No :");   
	JTextField SMNo =new JTextField();
	
	JLabel l2=new JLabel("Invoice Date :");
	JTextField SMDate =new JTextField();

	JLabel l3=new JLabel("Supplier Name :");
	JTextField ISupl =new JTextField();

	JLabel l4=new JLabel("Invoice No :");
	JComboBox SMINo =new JComboBox();

	JLabel l5=new JLabel("Total Returned Amount :");
	JTextField TAmt =new JTextField("0.00");

	JLabel l6=new JLabel("Order No :");
	JTextField ono =new JTextField();

	JTextField  lb = new JTextField();
	JTextField  lb1 = new JTextField();

	String[][] row= new String[40][5];
	String[] column={"Product Id "," Product Name","Price","Qty Returned","Amount"};
	JTable QuoTbl= new JTable(row,column);
	JScrollPane table=new JScrollPane(QuoTbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	JTextField proid =new JTextField();
	
	JButton cmdMake=new JButton("Make",i4);
	JButton cmdCancel=new JButton("Cancel",i5);
	
	JLabel l9=new JLabel("Product  Information  ");

	JButton cmdCal=new JButton("Calculate Amount",i6);

	static Connection con;
	static Statement st;
	static ResultSet rs;
	static ResultSetMetaData rsmd;
	static Statement st1;
	static ResultSet rs1;
	static Statement st2;
	static ResultSet rs2;
	static ResultSetMetaData rsmd2;
	static Statement st3;
	static ResultSet rs3;
	static Statement st4;
	static ResultSet rs4;

	String newPMR;

	int rowSelCnt=0;


	public NewPMatRet(int cnt)
	{	
		super("New Purchase Material Return");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(SMNo);
		c.add(l2);
		c.add(SMDate);
		c.add(l3);
		c.add(ISupl);
		c.add(l4);
		c.add(SMINo);
		c.add(l5);
        c.add(TAmt);
		c.add(l6);
        c.add(ono);
		c.add(table);
		c.add(cmdMake);
		c.add(cmdCancel);
		c.add(cmdCal);
		c.add(l9);

		c.add(lb);
		lb.setBounds(500,0,5,446);
		lb.setBackground(new Color(100,200,250));
		lb.setEditable(false);
		
		c.add(lb1);
		lb1.setBounds(0,440,800,5);
		lb1.setBackground(new Color(100,200,250));
		lb1.setEditable(false);

		l1.setBounds(520,60,100,22);
		SMNo.setBounds(680,60,100,22);
		SMNo.setEditable(false);
		SMNo.setText("m"+cnt);
		l1.setFont(new java.awt.Font("Courier New", 1, 13));
		SMNo.setFont(new java.awt.Font("Courier New", 1, 13));

		l2.setBounds(520,180,120,22);
		SMDate.setBounds(680,180,100,22);
		SMDate.setEditable(false);
		l2.setFont(new java.awt.Font("Courier New", 1, 13));
		SMDate.setFont(new java.awt.Font("Courier New", 1, 13));

		l6.setBounds(520,240,100,22);
		ono.setBounds(680,240,100,22);
		ono.setEditable(false);
		l6.setFont(new java.awt.Font("Courier New", 1, 13));
		ono.setFont(new java.awt.Font("Courier New", 1, 13));

		l3.setBounds(520,280,150,22);
		ISupl.setBounds(550,315,180,22);
		ISupl.setEditable(false);
		l3.setFont(new java.awt.Font("Courier New", 1, 13));
		ISupl.setFont(new java.awt.Font("Courier New", 1, 13));

		l4.setBounds(520,120,100,22);
		SMINo.setBounds(680,120,100,22);
		l4.setFont(new java.awt.Font("Courier New", 1, 13));
		SMINo.setFont(new java.awt.Font("Courier New", 1, 13));
		SMINo.addItem("");
		SMINo.addActionListener(this);
		
		table.setBounds(10,60,475,370);
		QuoTbl.setRowHeight(25);
		QuoTbl.setFont(new java.awt.Font("Courier New", 1, 13));

		QuoTbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumn column = null;
		column = QuoTbl.getColumnModel().getColumn(0);
		column.setPreferredWidth(90);
		column = QuoTbl.getColumnModel().getColumn(1);
		column.setPreferredWidth(160);
		column = QuoTbl.getColumnModel().getColumn(2);
		column.setPreferredWidth(70);
		column = QuoTbl.getColumnModel().getColumn(3);
		column.setPreferredWidth(80);
		column = QuoTbl.getColumnModel().getColumn(4);
		column.setPreferredWidth(60);


		l5.setBounds(400,520,180,22);
		TAmt.setBounds(580,520,110,22);
		TAmt.setEditable(false);
		l5.setFont(new java.awt.Font("Courier New", 1, 13));
		TAmt.setFont(new java.awt.Font("Courier New", 1, 13));

		l9.setBounds(180,20,280,25);
		l9.setFont(new java.awt.Font("papyrus", 1, 23));
		l9.setForeground(new Color(200,0,0));

		cmdMake.setBounds(80,450,130,48);
		cmdCancel.setBounds(250,450,130,48);
		cmdCal.setBounds(550,450,200,48);
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

		Border b=BorderFactory.createLineBorder(new Color(100,200,255));
		SMNo.setBorder(b);
		SMDate.setBorder(b);
		ISupl.setBorder(b);
		TAmt.setBorder(b);
		ono.setBorder(b);


		QuoTbl.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(proid));
		QuoTbl.getColumnModel().getColumn(1).setCellEditor( new DefaultCellEditor(proid));
		QuoTbl.getColumnModel().getColumn(2).setCellEditor( new DefaultCellEditor(proid));
		QuoTbl.getColumnModel().getColumn(4).setCellEditor( new DefaultCellEditor(proid));
		proid.setEditable(false);

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
			st3=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st3.execute("use sales");
			st1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st1.execute("use sales");
			st2=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st2.execute("use sales");
			rs2=st2.executeQuery("select * from PInvoice  where   PIStatus=1 ");
			rsmd2=rs2.getMetaData();

			while(rs2.next())
			{
				SMINo.addItem(rs2.getString(1));
			}


			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st.execute("use sales");
			rs=st.executeQuery("select * from PMRet");
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
			PMatRet s=new PMatRet();
		}


		if(e.getSource()==SMINo)
		{
			
			try
			{
				
				double total=0.0;
				int i=0;
				int no=SMINo.getSelectedIndex();
				String num=(String)SMINo.getSelectedItem();
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
				SMDate.setText(rs2.getString(2));
				ono.setText(rs2.getString(3));
				for(int x=8;x<=rsmd2.getColumnCount();x++)
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
				rs4=st4.executeQuery("select PIOrder_No from PInvoice where PInvoice_No='"+num+"'    ");
				rs4.next();
				String supl=rs4.getString(1);
				rs4=st4.executeQuery("select POSupl_Id from POrder where POrder_No='"+supl+"'    ");
				rs4.next();
				supl=rs4.getString(1);
				rs4=st4.executeQuery("select Supl_Name from Supplier where Supl_Id='"+supl+"'    ");
				rs4.next();
				supl=rs4.getString(1);
				ISupl.setText(supl);
				}
				else
				{
					ISupl.setText("");
					TAmt.setText("");
					SMDate.setText("");
					ono.setText("");
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
						err7.showMessageDialog(null," Please select the invoice for which the material return is to be created.  ","Invalid Data Error",JOptionPane.ERROR_MESSAGE);
						cmdMake.setEnabled(false);
						c=false;
					}
					System.out.println("break  "+rowSelCnt);
					break;
				}
				QTY=(String)QuoTbl.getValueAt(i,3);
				if(QuoTbl.getValueAt(i,3)==nos  && c==true)
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
			if(c==true)
			{
				int[] qty1 = new int[rowSelCnt];//invoice qty
				int[] qty2 = new int[rowSelCnt];//table qty
				int[] diff = new int[rowSelCnt];//diff of order & invoice

				String ord=(String)SMINo.getSelectedItem();
				try
				{
					rs3=st3.executeQuery("select * from PInvoice where PInvoice_No='"+ord+"' ");
					while(rs3.next())
					{
						for(int t=0;t<rowSelCnt;t++)
						{
							String tes=(String)QuoTbl.getValueAt(t,0);
							int te=rs3.getInt(tes);
							qty1[t]=te;
							System.out.println("qty1  "+qty1[t]);
							
						}
					}
				}
				catch(SQLException e2)
				{
					JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,e2.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
				for(int t=0;t<rowSelCnt;t++)
				{
					String tesQt=(String)QuoTbl.getValueAt(t,3);
					int te=Integer.parseInt(tesQt);;
					qty2[t]=te;
					System.out.println("qty2  "+qty2[t]);
				}
				for(int t=0;t<rowSelCnt;t++)
				{
					if(qty2[t]>qty1[t])
					{
						JOptionPane err7=new JOptionPane();
						err7.showMessageDialog(null," In Invoice '"+ord+"' for Product '"+(String)QuoTbl.getValueAt(t,0)+"' Invoice quantity is '"+qty1[t]+"' .\n     You enter quantity less or equal to "+qty1[t]+" .","Maximum quantity Error",JOptionPane.ERROR_MESSAGE);
						d=false;
						cmdMake.setEnabled(false);
						break;
					}
					
				}
				if( c==true  &&  d==true )
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
					//System.out.println("ok");
				}
			}//end if
			//System.out.println("ok");

		}




		if(e.getSource()==cmdMake)
		{
			//code for saving records
			String[] PId = new String[rowSelCnt];
			String[] PQ = new String[rowSelCnt];
			int[] PQty = new int[rowSelCnt];

			String no="";
			String pmrno,pidate,pi,pista;
			double piamt,stax,dcha;
			int pistatus;
			boolean b=true;

			pmrno=SMNo.getText();
			//pidate=InvDate.getText();
			pi=(String)SMINo.getSelectedItem();
			//pista=(String)status.getSelectedItem();
			piamt=Double.parseDouble(TAmt.getText());
			//stax=Double.parseDouble(STax.getText());
			//dcha=Double.parseDouble(DCha.getText());

			for(int i=0;i<rowSelCnt;i++)
			{
				PId[i]=(String)QuoTbl.getValueAt(i,0);
				PQ[i]=(String)QuoTbl.getValueAt(i,3);
				PQty[i]=Integer.parseInt(PQ[i]);
			}

			try
				{
					rs.moveToInsertRow(); // moves cursor to the insert row
					rs.updateString(1,pmrno); 
					rs.updateString(2,pi); 
					//rs.updateString(3,pior); 
					rs.updateDouble(3,piamt);
					//rs.updateDouble(5,stax);
					//rs.updateDouble(6,dcha);
					//rs.updateInt(7,pistatus);
					System.out.println("ok1");

					for(int i=0;i<rowSelCnt;i++)
					{
						rs.updateInt(PId[i],PQty[i]);
					}
					
					rs.insertRow();
					rs.moveToCurrentRow();
					System.out.println("ok2");

					for(int i=0;i<rowSelCnt;i++)
					{
						st3.executeUpdate("update Inventory set Qty = Qty - "+PQty[i]+" where InvPro_Id='"+PId[i]+"' ");
					}

					st3.executeUpdate("update PInvoice set PIStatus=2 where PInvoice_No='"+pi+"' ");
	
					System.out.println("save");
					setVisible(false);
					PMatRet s=new PMatRet();


				}
				catch(SQLException se4)
				{
					JOptionPane e1=new JOptionPane();
			e1.showMessageDialog(null,se4.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
				




		}
	}



		

}