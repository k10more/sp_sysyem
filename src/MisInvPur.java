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

 class MisInvPur extends JFrame  implements ActionListener 
{
	JLabel l1=new JLabel("Date From :");   
	JComboBox dt =new JComboBox();
	
	JLabel l2=new JLabel("To Date :");
	JComboBox to =new JComboBox();

	JLabel l3=new JLabel("Supplier Name :");
	JComboBox supName =new JComboBox();

	JLabel l4=new JLabel("Status :");
	JComboBox status =new JComboBox();

	JLabel ll1=new JLabel("Invoice No :");
	JComboBox ONo =new JComboBox();

	JLabel ll4=new JLabel("Supplier Name :");
	JTextField supl =new JTextField();

	JLabel ll2=new JLabel("Invoice Date :");
	JTextField odate =new JTextField();

	JLabel ll3=new JLabel("Status :");
	JTextField sta =new JTextField();

	JLabel ll6=new JLabel("Order No :");
	JTextField invO =new JTextField();

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
	String[][] row1= new String[40][5];
	String[] column1={"Id"," Product Name ","Price","Qty Invoiced","Qty Return"};
	JTable QuoTb2= new JTable(row1,column1);
	JScrollPane table2=new JScrollPane(QuoTb2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	
	ImageIcon i4= new ImageIcon("Image//back.png");      
	ImageIcon i5= new ImageIcon("Image//search.png");
	ImageIcon i6= new ImageIcon("Image//PIE.png");
	
	JButton cmdCancel=new JButton("",i4);
	JButton search=new JButton("",i5);
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
	static Statement stt3;
	static ResultSet rst3;
	static ResultSetMetaData rsmdt3;


	int recCnt=0,i=0;

	String d1,d2,orno,od,su,stas;


	double pi=0,pm=0,po=0,pp=0,STAX=0,DCHA=0;

	boolean ret=false;


	public MisInvPur()
	{	
		super("Purchase Order Information");
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
		/*
		c.add(l5);
        c.add(PAmt);

		c.add(l6);
		c.add(DAmt);
		*/

		c.add(ll6);
		c.add(invO);

		c.add(l7);
		c.add(totAmt);

		c.add(l8);
		c.add(retAmt);

		//c.add(table);
		c.add(table2);
		
		c.add(cmdCancel);
		c.add(search);
		c.add(rpt);

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

		ll6.setBounds(275,160,120,22);
		invO.setBounds(380,160,120,22);
		invO.setEditable(false);
		ll6.setFont(new java.awt.Font("Courier New", 1, 12));
		invO.setFont(new java.awt.Font("Courier New", 1, 12));

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
		status.setBounds(100,250,130,22);
		//status.setEditable(false);
		status.addItem("");
		//status.addItem("Pending");
		status.addItem("Complete");
		status.addItem("Material Return");
		l4.setFont(new java.awt.Font("Courier New", 1, 12));
		status.setFont(new java.awt.Font("Courier New", 1, 12));


		l5.setBounds(275,410,120,22);
		PAmt.setBounds(380,410,120,22);
		PAmt.setEditable(false);
		l5.setFont(new java.awt.Font("Courier New", 1, 12));
		PAmt.setFont(new java.awt.Font("Courier New", 1, 12));

		l6.setBounds(550,410,120,22);
		DAmt.setBounds(660,410,120,22);
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

        table2.setBounds(275,220,500,210);
		QuoTb2.setRowHeight(25);
		QuoTb2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		QuoTb2.setFont(new java.awt.Font("Courier New", 1, 12));
		//l1.setFont(new java.awt.Font("Courier New", 1, 12));

		TableColumn col = null;
		col = QuoTb2.getColumnModel().getColumn(0);
		col.setPreferredWidth(60);
		col = QuoTb2.getColumnModel().getColumn(1);
		col.setPreferredWidth(180);
		col = QuoTb2.getColumnModel().getColumn(2);
		col.setPreferredWidth(80);
		col = QuoTb2.getColumnModel().getColumn(3);
		col.setPreferredWidth(85);
		col = QuoTb2.getColumnModel().getColumn(4);
		col.setPreferredWidth(85);
		
		QuoTb2.getColumnModel().getColumn(0).setCellEditor( new DefaultCellEditor(p1));
		QuoTb2.getColumnModel().getColumn(1).setCellEditor( new DefaultCellEditor(p1));
		QuoTb2.getColumnModel().getColumn(2).setCellEditor( new DefaultCellEditor(p1));
		QuoTb2.getColumnModel().getColumn(3).setCellEditor( new DefaultCellEditor(p1));
		QuoTb2.getColumnModel().getColumn(4).setCellEditor( new DefaultCellEditor(p1));
		
		p.setEditable(false);
		p1.setEditable(false);
		
		cmdCancel.setBounds(70,450,85,28);
		cmdCancel.addActionListener(this);
		cmdCancel.setMnemonic('b');

		search.setBounds(70,330,85,28);
		search.addActionListener(this);
		search.setMnemonic('s');

		rpt.setBounds(70,390,115,32);
		rpt.addActionListener(this);
		rpt.setMnemonic('r');
		rpt.setEnabled(false);
		cmdCancel.setFont(new java.awt.Font("Courier New", 1, 12));
		search.setFont(new java.awt.Font("Courier New", 1, 12));
		rpt.setFont(new java.awt.Font("Courier New", 1, 12));
		//l1.setFont(new java.awt.Font("Courier New", 1, 12));

		ONo.addActionListener(this);

        setSize(800,540);
		setResizable(false);
		setVisible(true);
		
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sales","root","ltit1");
			st6=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st6.execute("use sales");
			
			st5=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st5.execute("use sales");
			st4=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st4.execute("use sales");
			st3=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st3.execute("use sales");
			stt3=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stt3.execute("use sales");
			st2=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st2.execute("use sales");
			st1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st1.execute("use sales");
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			st.execute("use sales");

			rs=st.executeQuery("select  DISTINCT PInvoice_Date from PInvoice order by PInvoice_Date ");
			while(rs.next())
			{
				dt.addItem(rs.getString(1));
				to.addItem(rs.getString(1));
			}

			rs1=st1.executeQuery("select Supl_Name from Supplier");
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



		if(e.getSource()==search)
		{
			int num1=0,num2=0,num3=0,num4=0;
			String dtFrom,dtTo,supNm,OrdSta,no="";
			boolean b=true;
			num1=dt.getSelectedIndex();//date
			dtFrom=(String)dt.getSelectedItem();
			d1=dtFrom;
			dtTo=(String)to.getSelectedItem();//date to
			num2=to.getSelectedIndex();//date to
			d2=dtTo;
			recCnt=0;
			supNm=(String)supName.getSelectedItem();
			num3=supName.getSelectedIndex();//supplier name
			OrdSta=(String)status.getSelectedItem();
			num4=status.getSelectedIndex();//supplier status
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
							rs2=st2.executeQuery("select * from PInvoice where  PInvoice_Date  between  '"+dtFrom+"'   and  '"+dtTo+"'  ");
							while(rs2.next())
							{
								ONo.addItem(rs2.getString(1));
								recCnt++;
							}
						}
						catch(SQLException se4)
						{
							System.out.println("UNABLE TO close  1");
							System.out.println(se4.getMessage());
						}
						b=false;
					}
					if(num3!=0 && num4!=0)
					//if(!(supNm.equals(no)  &&  OrdSta.equals(no)))
					{
						if(OrdSta.equals("Complete")) 
						{
							statemp=0;
						}
						else
							statemp=2;
						try
						{


							rs5=st5.executeQuery("select Supl_Id from Supplier where Supl_Name='"+supNm+"'      ");
							rs5.next();
							String suptemp=rs5.getString(1);
							System.out.println(suptemp);

							rs2=st2.executeQuery("select POrder_No from POrder  where POSupl_Id='"+suptemp+"' ");
							rs2.next();
							suptemp=rs2.getString(1);

							rs2=st2.executeQuery("select * from PInvoice where PIOrder_No='"+suptemp+"'  and  PIStatus="+statemp+"  and  PInvoice_Date  between  '"+dtFrom+"'   and  '"+dtTo+"'  ");
							while(rs2.next())
							{
								ONo.addItem(rs2.getString(1));
								recCnt++;
							}
							b=false;

						}
						catch(SQLException se3)
						{
							System.out.println("UNABLE TO close  2");
							System.out.println(se3.getMessage());
						}
						
					}
					if(num3!=0 && num4==0)
					//if( !(supNm.equals(no))  &&  OrdSta.equals(no) && b==true )
					{
						
						//if(!(supNm.equals(no)))
						//{
							try
							{
								rs5=st5.executeQuery("select Supl_Id from Supplier where Supl_Name='"+supNm+"'      ");
								rs5.next();
								String suptemp=rs5.getString(1);
								System.out.println(suptemp);

								rs2=st2.executeQuery("select POrder_No from POrder  where POSupl_Id='"+suptemp+"' ");
								rs2.next();
								suptemp=rs2.getString(1);

								rs2=st2.executeQuery("select * from PInvoice where PIOrder_No='"+suptemp+"'  and    PInvoice_Date  between  '"+dtFrom+"'   and  '"+dtTo+"'  ");
								while(rs2.next())
								{
									ONo.addItem(rs2.getString(1));
									recCnt++;
								}
								System.out.println("ok1");
							}
							catch(SQLException se5)
							{
								System.out.println("UNABLE TO close  3");
								System.out.println(se5.getMessage());
							}
					}
					if(num3==0 && num4!=0)
					//if(!(OrdSta.equals(no))  && supNm.equals(no)  && b==true)
						{
							try
							{

								if(OrdSta.equals("Complete")) 
								{
									statemp=0;
								}
								else
									statemp=2;

								rs2=st2.executeQuery("select * from PInvoice where PIStatus="+statemp+"  and    PInvoice_Date  between  '"+dtFrom+"'   and  '"+dtTo+"'  ");
								//rsmd2=rs2.getMetaData();
								System.out.println("ok1");
								while(rs2.next())
								{
									ONo.addItem(rs2.getString(1));
									recCnt++;
								}
							}
							catch(SQLException se6)
							{
								System.out.println("UNABLE TO close  4");
								System.out.println(se6.getMessage());
							}

					}

						
					
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
					err.showMessageDialog(null,"                    "+recCnt+"   Invoices  Found .\n To view Invoice information select Invoice No from Invoice No combobox. ","Order Count",JOptionPane.INFORMATION_MESSAGE);
					
					



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
			//String[] qpen = new String[i];
			//String[] qret = new String[i];

			for(int t=0;t<i;t++)
			{
				pnm[t]=(String)QuoTb2.getValueAt(t,1);
				ppri[t]=(String)QuoTb2.getValueAt(t,2);
				qord[t]=(String)QuoTb2.getValueAt(t,3);
				qinv[t]=(String)QuoTb2.getValueAt(t,4);
				//qpen[t]=(String)QuoTb2.getValueAt(t,5);
				//qret[t]=(String)QuoTb2.getValueAt(t,6);
			}
			System.out.println("ok sdsdsd  ");
			ori[0]=d1;
			ori[1]=d2;
			ori[2]=(String)ONo.getSelectedItem();
			ori[3]=odate.getText();
			ori[4]=supl.getText();
			ori[5]=sta.getText();
			ori[6]=totAmt.getText();
			ori[7]=retAmt.getText();
			ori[8]=invO.getText();
			System.out.println("ori[8]   "+ori[8]);
			//ori[9]=retAmt.getText();

			ReportPInv b = new ReportPInv(ori,pnm,ppri,qord,qinv);
		}
		
		

		if(e.getSource()==ONo)
		{
			
			
			try
			{

				double[] PRI = new double[30];
				
				double total=0.0,penAmt=0,disAmt=0;
				i=0;
				pm=0;
				STAX=0;
				DCHA=0;
				pi=0;
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
						//{
							//break;
						//}
					//}


				
	
					//rs2=st2.executeQuery("select  * from POrder  where  POrder_No='"+num+"'");
					//rs2.next();
				
					System.out.println("ok  2");
					odate.setText(rs2.getString(2));
					//OSupl.setText(rs.getString(3));
					rs4=st4.executeQuery("select  Supl_Name from Supplier  where  Supl_Id in ( select POSupl_Id from POrder where POrder_No= '"+rs2.getString(3)+"')");
					rs4.next();
					System.out.println("ok 3 ");
					supl.setText(rs4.getString(1));
					System.out.println("ok  4");
				//}


							
				if(rs2.getInt(7)==0)
				{
					sta.setText("Complete");
					ret=false;
					//l10.setVisible(false);
					//reason.setVisible(false);
				}
				else //if(rs.getInt(5)==2)
				{
					sta.setText("Material Return");
					ret=true;
					//can=true;
					//l10.setVisible(true);
					//reason.setVisible(true);

				}
				po = rs2.getDouble(4);
				totAmt.setText(""+po);
				invO.setText(rs2.getString(3));
				System.out.println("ok  7");
				
	
				
				for(int x=8;x<=rsmd2.getColumnCount();x++)
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
				//int[] qty2 = new int[i];//order qty
				//int[] diff = new int[i];//diff of order & invoice
				//int[] qty3 = new int[i];//mat ret qty

				String ord=(String)ONo.getSelectedItem();
				try
				{
					int xyz=rs2.getInt(7);
					if(xyz==2)
					{
						rst3=stt3.executeQuery("select * from PMRet where PMRInvoice_No='"+ord+"' ");
						System.out.println("1");
						rst3.next();
						System.out.println("2");
						//pm = pm + rst3.getDouble(3);
						System.out.println("3");
						for(int t=0;t<i;t++)
						{
							String tes=(String)QuoTb2.getValueAt(t,0);
							qty1[t]=rst3.getInt(tes);
							System.out.println("4");
							//System.out.println("te1"+te1);
							//qty1[t]=qty1[t]+te1;
							QuoTb2.setValueAt(""+qty1[t],t,4);
						}
						retAmt.setText(""+rst3.getDouble(3));
					}
					else
					{
						for(int t=0;t<i;t++)
						{
							QuoTb2.setValueAt("0",t,4);

						}
						retAmt.setText("0.0");
					}


					
				}
				catch(SQLException e2)
				{
					System.out.println("UNABLE TO run1");
					System.out.println(e2.getMessage());
				}
				

				for(int t=i;t<40-i;t++)
				{
					QuoTb2.setValueAt("",t,0);
					QuoTb2.setValueAt("",t,1);
					QuoTb2.setValueAt("",t,2);
					QuoTb2.setValueAt("",t,3);
					QuoTb2.setValueAt("",t,4);
					//QuoTb2.setValueAt("",t,5);
					//QuoTb2.setValueAt("",t,6);

				}
				rpt.setEnabled(true);
				System.out.println("ok x 4");
				
				
				
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
					invO.setText("");
					for(int t=0;t<40;t++)
					{
					QuoTb2.setValueAt("",t,0);
					QuoTb2.setValueAt("",t,1);
					QuoTb2.setValueAt("",t,2);
					QuoTb2.setValueAt("",t,3);
					QuoTb2.setValueAt("",t,4);
					//QuoTb2.setValueAt("",t,5);
					//QuoTb2.setValueAt("",t,6);

					}
					rpt.setEnabled(false);
					//l10.setVisible(false);
					//reason.setVisible(false);
				}
				
				

			}
			catch(SQLException se2)
			{
				System.out.println("UNABLE TO get");
				System.out.println(se2.getMessage());
			}
			
			
		}
		

		
		

		

		

	}

public static void main(String [] agrs)
	{
		
		MisInvPur s=new MisInvPur();
	}

}   //close of class User









