import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import javax.swing.table.*;
import javax.swing.border.*;

 class MisOrdPenS extends JFrame  implements ActionListener 
{
	JLabel l1=new JLabel("Date From :");   
	JComboBox dt =new JComboBox();
	
	JLabel l2=new JLabel("To Date :");
	JComboBox to =new JComboBox();

	JLabel l3=new JLabel("Customer Name :");
	JComboBox supName =new JComboBox();

	JLabel l4=new JLabel("Status :");
	JComboBox status =new JComboBox();

	JLabel ll1=new JLabel("Order No :");
	JComboBox ONo =new JComboBox();

	JLabel ll4=new JLabel("Customer Name :");
	JTextField supl =new JTextField();

	JLabel ll2=new JLabel("Order Date :");
	JTextField odate =new JTextField();

	JLabel ll3=new JLabel("Status :");
	JTextField sta =new JTextField();

	JLabel l5=new JLabel("Pending Amount :");
	JTextField PAmt =new JTextField();

	JLabel l6=new JLabel("Dispatch Amount :");
	JTextField DAmt =new JTextField();

	JLabel l7=new JLabel("Total Amount :");
	JTextField totAmt =new JTextField();

	JLabel l8=new JLabel("Mat. Ret. Amount :"); 
	JTextField retAmt =new JTextField();

	JTextField  lb = new JTextField();
    JTextField  lb1 = new JTextField();
	/*
	String[][] row= new String[20][4];
	String[] column={"Order NO","Order Date","Status","Cutomer Name"};
	JTable QuoTbl= new JTable(row,column);
	JScrollPane table=new JScrollPane(QuoTbl,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	*/
	String[][] row1= new String[40][7];
	String[] column1={"Id"," Product Name ","Price","Qty Ordered","Qty Pending","Qty Dispatch","Qty Return"};
	JTable QuoTb2= new JTable(row1,column1);
	JScrollPane table2=new JScrollPane(QuoTb2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	
	ImageIcon i4= new ImageIcon("Image//back.png");      
	ImageIcon i5= new ImageIcon("Image//search.png");
	ImageIcon i6= new ImageIcon("Image//PIE.png");
	
	JButton cmdCancel=new JButton(i4);
	JButton search=new JButton(i5);
	JButton rpt=new JButton("Report",i6);
	
	JTextField p =new JTextField();
	JTextField p1 =new JTextField();


	JLabel l9=new JLabel("Product  Information  :");

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
	static ResultSetMetaData rsmd5;
	static Statement st6;
	static ResultSet rs6;
	static ResultSetMetaData rsmd6;


	int recCnt=0,i=0;

	String d1,d2,orno,od,su,stas;


	double pi=0,pm=0,po=0,pp=0,STAX=0,DCHA=0;

	double staCom=0,staCan=0,staPen=0;

	boolean allSta=false;


	public MisOrdPenS()
	{	
		super("Sales Order Information");
		Container c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(dt);

		c.add(l2);
		c.add(to);

		c.add(l3);
		c.add(supName);

		c.add(l4);
        c.add(status);

		c.add(ll1);
        c.add(ONo);

		c.add(ll2);
        c.add(odate);

		c.add(ll3);
        c.add(sta);

		c.add(ll4);
        c.add(supl);

		c.add(l5);
        c.add(PAmt);

		c.add(l6);
		c.add(DAmt);

		c.add(l7);
		c.add(totAmt);

		c.add(l8);
		c.add(retAmt);

		//c.add(table);
		c.add(table2);
		
		c.add(cmdCancel);
		c.add(search);
		c.add(rpt);
		//c.add(allOrd);

		c.add(lb);
		lb.setBounds(250,0,5,540);
		lb.setBackground(new Color(100,200,250));
		lb.setEditable(false);
		/*
		c.add(lb1);
		lb1.setBounds(0,238,800,5);
		lb1.setBackground(new Color(100,200,250));
		lb1.setEditable(false);
		*/

		l1.setBounds(20,40,120,22);
		dt.setBounds(110,40,120,22);
		dt.addItem("");
		l1.setFont(new java.awt.Font("Courier New", 1, 12));
		dt.setFont(new java.awt.Font("Courier New", 1, 12));

		ll1.setBounds(275,40,120,22);
		ONo.setBounds(380,40,120,22);
		ONo.addItem("");
		ll1.setFont(new java.awt.Font("Courier New", 1, 12));
		ONo.setFont(new java.awt.Font("Courier New", 1, 12));

		ll2.setBounds(550,40,120,22);
		odate.setBounds(650,40,120,22);
		odate.setEditable(false);
		ll2.setFont(new java.awt.Font("Courier New", 1, 12));
		odate.setFont(new java.awt.Font("Courier New", 1, 12));

		ll4.setBounds(550,90,180,22);
		supl.setBounds(580,120,190,22);
		supl.setEditable(false);
		ll4.setFont(new java.awt.Font("Courier New", 1, 12));
		supl.setFont(new java.awt.Font("Courier New", 1, 12));

		ll3.setBounds(275,100,180,22);
		sta.setBounds(380,100,120,22);
		sta.setEditable(false);
		ll3.setFont(new java.awt.Font("Courier New", 1, 12));
		sta.setFont(new java.awt.Font("Courier New", 1, 12));

		l2.setBounds(20,100,120,22);
		to.setBounds(110,100,120,22);
		to.addItem("");
		l2.setFont(new java.awt.Font("Courier New", 1, 12));
		to.setFont(new java.awt.Font("Courier New", 1, 12));

		l3.setBounds(20,160,180,22);
		supName.setBounds(40,190,190,22);
		supName.addItem("");
		l3.setFont(new java.awt.Font("Courier New", 1, 12));
		supName.setFont(new java.awt.Font("Courier New", 1, 12));

		l4.setBounds(20,250,120,22);
		status.setBounds(110,250,110,22);
		//status.setEditable(false);
		status.addItem("");
		status.addItem("Pending");
		status.addItem("Completed");
		status.addItem("Canceled");
		l4.setFont(new java.awt.Font("Courier New", 1, 12));
		status.setFont(new java.awt.Font("Courier New", 1, 12));


		l5.setBounds(275,410,130,22);
		PAmt.setBounds(390,410,120,22);
		PAmt.setEditable(false);
		l5.setFont(new java.awt.Font("Courier New", 1, 12));
		PAmt.setFont(new java.awt.Font("Courier New", 1, 12));

		l6.setBounds(550,410,130,22);
		DAmt.setBounds(670,410,120,22);
		DAmt.setEditable(false);
		l6.setFont(new java.awt.Font("Courier New", 1, 12));
		DAmt.setFont(new java.awt.Font("Courier New", 1, 12));

		l7.setBounds(550,460,120,22);
		totAmt.setBounds(660,460,120,22);
		totAmt.setEditable(false);
		l7.setFont(new java.awt.Font("Courier New", 1, 12));
		totAmt.setFont(new java.awt.Font("Courier New", 1, 12));

		l8.setBounds(275,460,140,22);
		retAmt.setBounds(400,460,120,22);
		retAmt.setEditable(false);
		l8.setFont(new java.awt.Font("Courier New", 1, 12));
		retAmt.setFont(new java.awt.Font("Courier New", 1, 12));

        table2.setBounds(275,170,500,210);
		QuoTb2.setRowHeight(25);
		QuoTb2.setFont(new java.awt.Font("Courier New", 1, 12));
		QuoTb2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableColumn col = null;
		col = QuoTb2.getColumnModel().getColumn(0);
		col.setPreferredWidth(60);
		col = QuoTb2.getColumnModel().getColumn(1);
		col.setPreferredWidth(150);
		col = QuoTb2.getColumnModel().getColumn(2);
		col.setPreferredWidth(80);
		col = QuoTb2.getColumnModel().getColumn(3);
		col.setPreferredWidth(80);
		col = QuoTb2.getColumnModel().getColumn(4);
		col.setPreferredWidth(80);
		col = QuoTb2.getColumnModel().getColumn(4);
		col.setPreferredWidth(80);
		col = QuoTb2.getColumnModel().getColumn(5);
		col.setPreferredWidth(80);
		col = QuoTb2.getColumnModel().getColumn(6);
		col.setPreferredWidth(80);


		/*

		table.setBounds(275,30,500,180);
		QuoTbl.setRowHeight(25);

		QuoTbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		//TableColumn col = null;
		col = QuoTbl.getColumnModel().getColumn(0);
		col.setPreferredWidth(90);
		col = QuoTbl.getColumnModel().getColumn(1);
		col.setPreferredWidth(120);
		col = QuoTbl.getColumnModel().getColumn(2);
		col.setPreferredWidth(110);
		col = QuoTbl.getColumnModel().getColumn(3);
		col.setPreferredWidth(170);


		QuoTbl.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(p));
		QuoTbl.getColumnModel().getColumn(1).setCellEditor( new DefaultCellEditor(p));
		QuoTbl.getColumnModel().getColumn(2).setCellEditor( new DefaultCellEditor(p));

		*/

		QuoTb2.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(p1));
		QuoTb2.getColumnModel().getColumn(1).setCellEditor( new DefaultCellEditor(p1));
		QuoTb2.getColumnModel().getColumn(2).setCellEditor( new DefaultCellEditor(p1));
		QuoTb2.getColumnModel().getColumn(3).setCellEditor( new DefaultCellEditor(p1));
		QuoTb2.getColumnModel().getColumn(4).setCellEditor( new DefaultCellEditor(p1));
		QuoTb2.getColumnModel().getColumn(5).setCellEditor( new DefaultCellEditor(p1));
		QuoTb2.getColumnModel().getColumn(6).setCellEditor( new DefaultCellEditor(p1));



		p.setEditable(false);
		p.setEditable(false);
		p.setEditable(false);

		p1.setEditable(false);
		p1.setEditable(false);
		p1.setEditable(false);
		p1.setEditable(false);
		p1.setEditable(false);

        		

		//l9.setBounds(280,20,180,22);

		
		cmdCancel.setBounds(70,430,85,28);
		cmdCancel.addActionListener(this);
		cmdCancel.setMnemonic('b');

		search.setBounds(70,310,85,28);
		search.addActionListener(this);
		search.setMnemonic('s');

		rpt.setBounds(70,370,125,32);
		rpt.addActionListener(this);
		rpt.setMnemonic('r');
		rpt.setEnabled(false);

		cmdCancel.setFont(new java.awt.Font("Courier New", 1, 12));
		search.setFont(new java.awt.Font("Courier New", 1, 12));
		rpt.setFont(new java.awt.Font("Courier New", 1, 12));

		//allOrd.setBounds(70,420,125,32);
		//allOrd.addActionListener(this);
		//allOrd.setMnemonic('r');
		//allOrd.setEnabled(false);

		ONo.addActionListener(this);

        setSize(800,560);
		setResizable(false);
		setVisible(true);
		
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");
			st6=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st6.execute("use sales");
			
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

			rs=st.executeQuery("select  DISTINCT SOrder_Date from SOrder order by SOrder_Date ");
			while(rs.next())
			{
				dt.addItem(rs.getString(1));
				to.addItem(rs.getString(1));
			}

			rs1=st1.executeQuery("select Cust_Name from Customer");
			while(rs1.next())
			{
				supName.addItem(rs1.getString(1));
				//to.addItem(rs.getString(2));
			}


			System.out.println("connected");
		}
		catch(Exception e)
		{
			System.out.println("unable to connect  "+e.getMessage());
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
				System.out.println("UNABLE TO close");
				System.out.println(se1.getMessage());
			}
		}

		/*

		if(e.getSource()==allOrd)
		{
			String[] a =new String[2];
			a[0]=(String)dt.getSelectedItem();
			a[1]=(String)to.getSelectedItem();
			SOrderBetween x = new SOrderBetween(a,staPen,staCom,staCan);
		}
		*/



		if(e.getSource()==search)
		{
			recCnt=0;
			int num1=0,num2=0,num3=0,num4=0;
			String dtFrom,dtTo,supNm,OrdSta,no="";
			boolean b=true;
			dtFrom=(String)dt.getSelectedItem();
			num1=dt.getSelectedIndex();
			d1=dtFrom;
			dtTo=(String)to.getSelectedItem();
			num2=to.getSelectedIndex();
			d2=dtTo;
			supNm=(String)supName.getSelectedItem();
			num3=supName.getSelectedIndex();
			OrdSta=(String)status.getSelectedItem();
			num4=status.getSelectedIndex();
			int statemp=0;
			System.out.println(dtFrom);
			System.out.println(dtTo);
			System.out.println(supNm);
			System.out.println(OrdSta);
			if(	ONo.getItemCount()>1)
			{
				ONo.removeAllItems();
				ONo.addItem("");
			}
			System.out.println("ok   1");
			


			if(dtFrom.equals(no))
			{
				JOptionPane err1=new JOptionPane();
				err1.showMessageDialog(null," Please select a date from which to show order.","REQUIRED DATA",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				if(dtTo.equals(no))
				{
					JOptionPane err2=new JOptionPane();
					err2.showMessageDialog(null," Please select a date upto which to show order.","REQUIRED DATA",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(num3==0 && num4==0)
					//if(supNm.equals(no)  &&  OrdSta.equals(no))
					{
						try
						{
							rs2=st2.executeQuery("select * from SOrder where  SOrder_Date  between  '"+dtFrom+"'   and  '"+dtTo+"'  ");
							//rsmd2=rs2.getMetaData();
							//System.out.println("ok1");
							while(rs2.next())
							{
								ONo.addItem(rs2.getString(1));
								recCnt++;
								if(rs2.getInt(5)==0)
									staPen = staPen + rs2.getDouble(4); 
								else if(rs2.getInt(5)==1)
									staCom = staCom + rs2.getDouble(4); 
								else
									staCan = staCan + rs2.getDouble(4); 
								
							}
							
							System.out.println("recCnt    "+recCnt);
							allSta=true;
							
						}
						catch(SQLException se4)
						{
							System.out.println("UNABLE TO run  1");
							System.out.println(se4.getMessage());
						}
						b=false;
					}
					if(num3!=0 && num4!=0)
					//if(!(supNm.equals(no)  &&  OrdSta.equals(no)))
					{
						if(OrdSta.equals("Pending")) 
						{
							statemp=0;
						}
						if(OrdSta.equals("Completed")) 
						{
							statemp=1;
						}
						if(OrdSta.equals("Canceled")) 
						{
							statemp=2;
						}
						try
						{


							rs5=st5.executeQuery("select Cust_Id from Customer where Cust_Name='"+supNm+"'      ");
							rs5.next();
							String suptemp=rs5.getString(1);
							System.out.println(suptemp);
							

							rs2=st2.executeQuery("select * from SOrder where SOStatus="+statemp+"  and  SOrder_Date  between  '"+dtFrom+"'   and  '"+dtTo+"'  and SQuo_No in  (select Quo_No from Quotation where QuoCust_Id = '"+suptemp+"' )");
							while(rs2.next())
							{
								ONo.addItem(rs2.getString(1));
								recCnt++;
							}
							b=false;
							

						}
						catch(SQLException se3)
						{
							System.out.println("UNABLE TO run  2");
							System.out.println(se3.getMessage());
						}
						
					}
					if(num3==0 && num4!=0)
					///if( (supNm.equals(no)  ||  OrdSta.equals(no))  && b==true )
					{
						

						//if(!(OrdSta.equals(no)))
						//{
							try
							{

								if(OrdSta.equals("Pending")) 
								{
									statemp=0;
								}
								if(OrdSta.equals("Completed")) 
								{
									statemp=1;
								}
								if(OrdSta.equals("Canceled")) 
								{
									statemp=2;
								}

								rs2=st2.executeQuery("select * from SOrder where SOStatus="+statemp+"  and   SOrder_Date  between  '"+dtFrom+"'   and  '"+dtTo+"'  ");
								//rsmd2=rs2.getMetaData();
								//System.out.println("ok1");
								while(rs2.next())
								{
									ONo.addItem(rs2.getString(1));
									recCnt++;
								}
								System.out.println("recCnt    "+recCnt);
							}
							catch(SQLException se6)
							{
								System.out.println("UNABLE TO run  4");
								System.out.println(se6.getMessage());
							}

						}
						
						//if(!(supNm.equals(no)))
						if(num3!=0 && num4==0)
						//else
						{
							try
							{
								rs2=st2.executeQuery("select Cust_Id from Customer where Cust_Name='"+supNm+"'      ");
								rs2.next();
								String suptemp=rs2.getString(1);
								System.out.println(suptemp);

								rs2=st2.executeQuery("select * from SOrder where  SOrder_Date  between  '"+dtFrom+"'   and  '"+dtTo+"' and SQuo_No in  (select Quo_No from Quotation where QuoCust_Id = '"+suptemp+"'    )           ");
								//rsmd2=rs2.getMetaData();
								//System.out.println("ok1");
								while(rs2.next())
								{
									ONo.addItem(rs2.getString(1));
									recCnt++;
								}
								System.out.println("recCnt    "+recCnt);
							}
							catch(SQLException se5)
							{
								System.out.println("UNABLE TO run  3");
								System.out.println(se5.getMessage());
							}
						}
						

						
					//}
					try
					{
						rsmd2=rs2.getMetaData();
					}
					catch(SQLException se12)
					{
						System.out.println("UNABLE TO close  4");
						System.out.println(se12.getMessage());
					}
					JOptionPane err=new JOptionPane();
					err.showMessageDialog(null,"                    "+recCnt+"   Orders  Found .\n To view order information select Order No from Order No combobox. ","Order Count",JOptionPane.INFORMATION_MESSAGE);
					
					



				}//end else dtTo

			}//end else dtFrom
			

			
		}

		if(e.getSource()==rpt)
		{
			String[] ori = new String[10];
			String[] pnm = new String[i];
			String[] ppri = new String[i];
			String[] qord = new String[i];
			String[] qinv = new String[i];
			String[] qpen = new String[i];
			String[] qret = new String[i];

			for(int t=0;t<i;t++)
			{
				pnm[t]=(String)QuoTb2.getValueAt(t,1);
				ppri[t]=(String)QuoTb2.getValueAt(t,2);
				qord[t]=(String)QuoTb2.getValueAt(t,3);
				qinv[t]=(String)QuoTb2.getValueAt(t,4);
				qpen[t]=(String)QuoTb2.getValueAt(t,5);
				qret[t]=(String)QuoTb2.getValueAt(t,6);
			}
			System.out.println("ok sdsdsd  ");
			ori[0]=d1;
			ori[1]=d2;
			ori[2]=(String)ONo.getSelectedItem();
			ori[3]=odate.getText();
			ori[4]=supl.getText();
			ori[5]=sta.getText();
			ori[6]=totAmt.getText();
			ori[7]=DAmt.getText();
			ori[8]=PAmt.getText();
			ori[9]=retAmt.getText();

			if(allSta==true)
			{

				Object[] pv = {"Sales Order Report","Graph Representation of order status"};
				Object v = JOptionPane.showInputDialog(null,"Please select the type of Report.","Report Type",JOptionPane.INFORMATION_MESSAGE,null,pv,pv[0]);
				String temp = (String)v;


				if(temp.equals("Sales Order Report"))
				{
					ReportSOrd b = new ReportSOrd(ori,pnm,ppri,qord,qinv,qpen,qret);
				}
				else
				{
					String[] a =new String[2];
					a[0]=(String)dt.getSelectedItem();
					a[1]=(String)to.getSelectedItem();
					//SOrderBetween x = new SOrderBetween(a,staPen,staCom,staCan);
				}
			}
			else
			{
				ReportSOrd b = new ReportSOrd(ori,pnm,ppri,qord,qinv,qpen,qret);
			}
		}
		
		

		if(e.getSource()==ONo)
		{
			
			
			try
			{

				double[] PRI = new double[30];
				
				double total=0.0,penAmt=0,disAmt=0;
				i=0;
				pi=0;
				pm=0;
				STAX=0;
				DCHA=0;
				int no=ONo.getSelectedIndex();
				String num=(String)ONo.getSelectedItem();
				//on=num;
				//System.out.println("on  "+on);
				if(no!=0 )
				{
					
				
					rs2.absolute(no);
					//rs2.beforeFirst();
					System.out.println("ok  000");
					System.out.println("ok1  ");
					//while(rs2.next())
					//{
						//if(rs2.getString(1).equals(num))
						///{
							//break;
						//}
					//}


				
	
					//rs2=st2.executeQuery("select  * from POrder  where  POrder_No='"+num+"'");
					//rs2.next();
				
					System.out.println("ok  2");
					odate.setText(rs2.getString(2));
					//OSupl.setText(rs.getString(3));
					rs4=st4.executeQuery("select  QuoCust_Id from Quotation  where  Quo_No='"+rs2.getString(3)+"'");
					rs4.next();
					String xy=rs4.getString(1);
					rs4=st4.executeQuery("select  Cust_Name from Customer  where  Cust_Id='"+xy+"'");
					rs4.next();
					System.out.println("ok 3 ");
					supl.setText(rs4.getString(1));
					System.out.println("ok  4");
				//}


							
				if(rs2.getInt(5)==0)
				{
					sta.setText("Pending");
					//l10.setVisible(false);
					//reason.setVisible(false);
				}
				//System.out.println("ok  5");
				else if(rs2.getInt(5)==1)
				{
					sta.setText("Completed");
					//l10.setVisible(false);
					//reason.setVisible(false);
				}
				//System.out.println("ok  6");
				else //if(rs.getInt(5)==2)
				{
					sta.setText("Canceled");
					//can=true;
					//l10.setVisible(true);
					//reason.setVisible(true);

				}
				po = rs2.getDouble(4);
				//totAmt.setText(""+po);
				System.out.println("ok  7");
				
	
				
				for(int x=6;x<=rsmd2.getColumnCount();x++)
				{
					int qty=rs2.getInt(x);
					if(rs2.wasNull())
					{
						System.out.println("Null value");
					}
					else
					{
						QuoTb2.setValueAt(rsmd2.getColumnLabel(x),i,0);
						QuoTb2.setValueAt(rs2.getString(x),i,3);
	
						rs1=st1.executeQuery("select  Pro_Name,Sales_Price  from  Product  where  Pro_Id='"+rsmd2.getColumnLabel(x)+"'");
						rs1.next();
						double pri=rs1.getDouble(2);
						PRI[i]=pri;
						QuoTb2.setValueAt(rs1.getString(1),i,1);
						QuoTb2.setValueAt(rs1.getString(2),i,2);
						double amt=pri*qty;
						//QuoTbl.setValueAt(""+amt,i,4);
						total = total + amt;
						i++;

					}
				}
				//totAmt.setText(""+total);
				
				
				int tempi=0,tempm=0;
				int te1=0,te2=0;
				int[] qty1 = new int[i];//invoice qty
				int[] qty2 = new int[i];//order qty
				int[] diff = new int[i];//diff of order & invoice
				int[] qty3 = new int[i];//mat ret qty

				String ord=(String)ONo.getSelectedItem();
				try
				{
					rs3=st3.executeQuery("select * from SInvoice where SIOrder_No='"+ord+"' ");
					//System.out.println("temp=  "+temp);
					while(rs3.next())
					{
						tempi++;
						pi = pi + rs3.getDouble(4);
						STAX = STAX + rs3.getDouble(5);
						DCHA = DCHA + rs3.getDouble(6);
					}
					System.out.println("tempi  "+tempi);
					//rs3.getString(1);
					if(tempi==0)
					{
						/*
						for(int t=0;t<i;t++)
						{
							//String tes=(String)QuoTb2.getValueAt(t,0);
							//int te=rs3.getInt(tes);
							qty1[t]=0;
							QuoTb2.setValueAt(""+qty1[t],t,5);
							String tes=(String)QuoTb2.getValueAt(t,0);
							te2=rs3.getInt(tes);
							qty1[t]=qty1[t]+te2;
							System.out.println("qty1  "+qty1[t]);
							
						}
						*/
					}
					else
					{
						rs3.beforeFirst();
					while(rs3.next())
					{
						
							//QuoTb2.setValueAt(""+qty3[t],t,6);
							
							//QuoTb2.setValueAt(""+qty1[t],t,5);
							for(int t=0;t<i;t++)
							{
								String tes=(String)QuoTb2.getValueAt(t,0);
							te2=rs3.getInt(tes);
							qty1[t]=qty1[t]+te2;

							}
							if(rs3.getInt(7)==2)
							{
								rs6=st6.executeQuery("select * from SMRet where SMRInvoice_No='"+rs3.getString(1)+"'         ");
								rs6.next();
								pm = pm + rs6.getDouble(3);

								for(int t=0;t<i;t++)
								{
								String tes1=(String)QuoTb2.getValueAt(t,0);
								te1=rs6.getInt(tes1);
								System.out.println("te1"+te1);
								qty3[t]=qty3[t]+te1;
								}
							}
							else
							{
								for(int t=0;t<i;t++)
								{
								te1=0;
								qty3[t]=qty3[t]+te1;
								}
								
							}
							

							

							/*
							rs6=st6.executeQuery("select * from PMRet where PMRInvoice_No='"+rs3.getString(1)+"'         ");
							while(rs6.next())
							{
								tempm++;
							}
							if(tempm==0)
							{
								te1=0;
							}
							else
							{
							rs6.beforeFirst();
							String tes1=(String)QuoTb2.getValueAt(t,0);
							te1=rs6.getInt(tes1);
							System.out.println(te1);
							qty3[t]=qty3[t]+te1;
							QuoTb2.setValueAt(""+qty3[t],t,6);
							System.out.println("qty3  "+qty3[t]);
							}
							*/
							
							

							
							//}//end for 
							
							
							
						}//end while
						for(int t=0;t<i;t++)
						{
							QuoTb2.setValueAt(""+qty1[t],t,5);
							QuoTb2.setValueAt(""+qty3[t],t,6);
						}
					}//end else
					
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
							String tes=(String)QuoTb2.getValueAt(t,0);
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
					QuoTb2.setValueAt(""+diff[t],t,4);
					System.out.println("diff  "+diff[t]);	
				}
				System.out.println("okx 1  ");

				for(int t=0;t<i;t++)
				{
					penAmt = penAmt + (PRI[t]*diff[t]);
				}
				System.out.println("okx  2");
				//PAmt.setText(""+penAmt);
				//disAmt = total - penAmt;
				
				pi = pi - (STAX  + DCHA);
				DAmt.setText(""+pi);
				if(po>pi)
				{
					pp = po - pi;
				}
				else
				{
					pp = pi - po;
				}
				PAmt.setText(""+pp);
				retAmt.setText(""+pm);
				System.out.println("okx  3");
				if(sta.getText().equals("Completed"))
				{
					PAmt.setText("0.0");
				}
				po = po + STAX  + DCHA;
				totAmt.setText(""+po);

				for(int t=i;t<40-i;t++)
				{
					QuoTb2.setValueAt("",t,0);
					QuoTb2.setValueAt("",t,1);
					QuoTb2.setValueAt("",t,2);
					QuoTb2.setValueAt("",t,3);
					QuoTb2.setValueAt("",t,4);
					QuoTb2.setValueAt("",t,5);
					QuoTb2.setValueAt("",t,6);

				}
				rpt.setEnabled(true);
				System.out.println("ok x 4");
				/*
				if(can==true)
				{
					rs5=st5.executeQuery("select  Reason from POCancel  where  POCancel_No='"+num+"'");
					rs5.next();
					reason.setText(rs5.getString(1));

				}
				*/
				
				
				}
				else
				{
					System.out.println("ok  5");
					odate.setText("");
					supl.setText("");
					sta.setText("");
					totAmt.setText("");
					PAmt.setText("");
					DAmt.setText("");
					retAmt.setText("");
					for(int t=0;t<40;t++)
					{
					QuoTb2.setValueAt("",t,0);
					QuoTb2.setValueAt("",t,1);
					QuoTb2.setValueAt("",t,2);
					QuoTb2.setValueAt("",t,3);
					QuoTb2.setValueAt("",t,4);
					QuoTb2.setValueAt("",t,5);
					QuoTb2.setValueAt("",t,6);

					}
					rpt.setEnabled(false);
					//l10.setVisible(false);
					//reason.setVisible(false);
				}
				
				

			}
			catch(SQLException se2)
			{
				System.out.println("UNABLE TO get ghb");
				System.out.println(se2.getMessage());
			}
			
			
		}
		

		
		

		

		

	}

public static void main(String [] agrs)
	{
		
		MisOrdPenS s=new MisOrdPenS();
	}

}   //close of class User










