import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.io.*;
import java.util.Date;
import java.lang.*;
import java.text.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaMauveMetallicLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaGreenDreamLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSilverMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueSteelLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueIceLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlueMoonLookAndFeel;
import java.awt.Dimension.*;

//declare class  for display main form
 class MainForm extends JFrame implements ActionListener
{
	//creating menubar and it's items
	JLabel l1=new JLabel("WELCOME  ");
	JLabel l2=new JLabel("Date :");
	JLabel l3=new JLabel(" ");

ImageIcon img2= new ImageIcon("Image//bg.gif");
	JLabel mg3=new JLabel(img2);

	ImageIcon img1=new ImageIcon("Image//omkarbanner.jpg");
	JLabel mg1=new JLabel(img1);
	ImageIcon img= new ImageIcon("Image//cokebg18.jpg");
//	JLabel mg=new JLabel(img);
//
	//ImageIcon img123= new ImageIcon("Image//kellogs1.gif");
//	JLabel mg123=new JLabel(img123);

//Background
JPanel panelBgImg = new JPanel()
        {
            public void paintComponent(Graphics g) 
            {
                Image img = new ImageIcon("Image//cokebg18.jpg").getImage();
                Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
                setPreferredSize(size);
                setMinimumSize(size);
                setMaximumSize(size);
                setSize(size);
                setLayout(null);
                g.drawImage(img, 0, 0, null);
            } 
        };
        
        
	 Timer th = new Timer(1000, this);
    


	JMenu main = new JMenu("Main");

	

	JMenuItem createUser = new JMenuItem("Create New User");
	//JMenuItem createDept = new JMenuItem("Add a Department");
	JMenuItem changePass = new JMenuItem("Change Password");
	JMenuItem logOf = new JMenuItem("Log Off");
	JMenuItem exit = new JMenuItem("Exit");

	JMenu database=new JMenu("Database");

	JMenuItem customers = new JMenuItem("Customers");
	JMenuItem products= new JMenuItem("Products");
	JMenuItem supplier = new JMenuItem("Supplier");

	JMenu purchasing = new JMenu("Purchasing");

	JMenuItem porder = new JMenuItem("Order");
	JMenuItem pinvoice = new JMenuItem("Invoice");
	JMenuItem porderCan = new JMenuItem("Order Cancellation");

	JMenu sales = new JMenu("Sales");

	JMenuItem quotation = new JMenuItem("Quotation");
	JMenuItem sorder = new JMenuItem("Order ");
	JMenuItem sinvoice = new JMenuItem("Invoice");
	JMenuItem sorderCan = new JMenuItem("Order Cancellation");

	//JMenu inv = new JMenu("Inventory");

	JMenuItem inv1 = new JMenuItem("Inventory");
	JMenuItem pmat = new JMenuItem("Material return");
	JMenuItem smat = new JMenuItem("Material Return");

	JMenu report = new JMenu("Reports");
	

	JMenu misPur = new JMenu("Purchasing");
	JMenu misSales = new JMenu("Sales");
	//modified
	JMenu customers1=new JMenu("Customers");
	JMenuItem customers12=new JMenuItem("Previous");
	JMenuItem customers123=new JMenuItem("Existing");
	
	JMenu suppliers1=new JMenu("Suppliers");
	JMenuItem suppliers12=new JMenuItem("Previous");
	JMenuItem suppliers123=new JMenuItem("Existing");

	JMenu products1=new JMenu("Products");
	JMenuItem products12=new JMenuItem("Previous");
	JMenuItem products123=new JMenuItem("Existing");


	
	//
	JMenuItem misPurOrd = new JMenuItem("Order ");
	JMenuItem misPurInv = new JMenuItem("Invoice");

	
	JMenuItem misSalQuo = new JMenuItem("Quotation");
	JMenuItem misSalOrd = new JMenuItem("Order");
	JMenuItem misSalInv = new JMenuItem("Invoice");

	JMenuItem misVed = new JMenuItem("Monthly");
	
	JMenu theme=new JMenu("Theme");
	JMenuItem nimrod = new JMenuItem("Nimrod");
	JMenuItem officexp = new JMenuItem("Office XP");
	JMenuItem OrangeMetallic = new JMenuItem("OrangeMetallic");
	JMenuItem liquid = new JMenuItem("Liquid");
	JMenuItem metal = new JMenuItem("Metal");
	JMenuItem MauveMetallic = new JMenuItem("MauveMetallic");
	JMenuItem Synthetica = new JMenuItem("Synthetica");
	JMenuItem ptd = new JMenuItem("Plastic3D");
	JMenuItem quaqua = new JMenuItem("Quaqua");
	JMenuItem BlackMoon = new JMenuItem("BlackMoon");
	JMenuItem GreenDream = new JMenuItem("GreenDream");
	JMenuItem BlackStar = new JMenuItem("BlackStar");
	JMenuItem SilverMoon = new JMenuItem("SilverMoon");
	JMenuItem BlueSteel = new JMenuItem("BlueSteel");
	JMenuItem BlueIce = new JMenuItem("BlueIce");
	JMenuItem BlueMoon = new JMenuItem("BlueMoon");


	JMenu help=new JMenu("Help");
	JMenuItem helptop=new JMenuItem("Help Topic");
	JMenuItem calc=new JMenuItem("Calculator");
	

	JMenuBar bar = new JMenuBar();

	java.util.Date date = new java.util.Date();
	DateFormat df = DateFormat.getDateInstance(3);
	String  date1,mon,datex,year,yearx,currentDate;
	int d,d1;

	int dt=0;

    public MainForm(int log,String LogType)
   {
        super("Sales Order Processing");

        Container c=getContentPane();
        c.setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

// retrieve the user screen size
Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

System.out.println("Width "+screenSize.getWidth());
System.out.println("Height "+screenSize.getHeight());
//c.setBackground(Color.gray);
		c.add(l1);
		c.add(l2);
		c.add(l3);
		//c.add(mg);
	//	c.add(mg123);
		c.add(mg1);
		
c.add(mg3);
c.add(panelBgImg);
        panelBgImg.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        
		l1.setBounds((int)screenSize.getWidth()-1000,(int)screenSize.getHeight()-150,400,25);
		l2.setBounds((int)screenSize.getWidth()-520,(int)screenSize.getHeight()-150,70,25);
		l3.setBounds((int)screenSize.getWidth()-470,(int)screenSize.getHeight()-150,250,25);
		mg3.setBounds((int)screenSize.getWidth()-980,(int)screenSize.getHeight()-700,640,480);
		//mg123.setBounds(1200,700,170,100);
		mg1.setBounds((int)screenSize.getWidth()-1000,(int)screenSize.getHeight()-890,686,174);
		l1.setFont(new java.awt.Font("showcard gothic", Font.BOLD, 15));
		l2.setFont(new java.awt.Font("showcard gothic", Font.BOLD, 15));
		l3.setFont(new java.awt.Font("showcard gothic", Font.BOLD,15));
		
		l3.setText(""+date);
		
        
        setJMenuBar(bar);
     
        database.add(customers);
       database.add(products);
       database.add(supplier);

		main.add(createUser);
		main.add(changePass);
		main.addSeparator();
		main.add(logOf);
		main.add(exit);

		main.setFont(new java.awt.Font("showcard gothic", 1, 13));
		customers.setFont(new java.awt.Font("showcard gothic", 1, 13));
		products.setFont(new java.awt.Font("showcard gothic", 1, 13));
		supplier.setFont(new java.awt.Font("showcard gothic", 1, 13));
		createUser.setFont(new java.awt.Font("showcard gothic", 1, 13));
		changePass.setFont(new java.awt.Font("showcard gothic", 1, 13));
		logOf.setFont(new java.awt.Font("showcard gothic", 1, 13));
		exit.setFont(new java.awt.Font("showcard gothic", 1, 13));

		database.setFont(new java.awt.Font("showcard gothic", 1, 13));
		
		purchasing.add(porder);
		purchasing.add(pinvoice);
		purchasing.add(porderCan);
		purchasing.add(pmat);

		purchasing.setFont(new java.awt.Font("showcard gothic", 1, 13));
		porder.setFont(new java.awt.Font("showcard gothic", 1, 13));
		pinvoice.setFont(new java.awt.Font("showcard gothic", 1, 13));
		porderCan.setFont(new java.awt.Font("showcard gothic", 1, 13));

		sales.add(quotation);
		sales.add(sorder);
		sales.add(sinvoice);
		sales.add(sorderCan);
		sales.add(smat);

		sales.setFont(new java.awt.Font("showcard gothic", 1, 13));
		quotation.setFont(new java.awt.Font("showcard gothic", 1, 13));
		sorder.setFont(new java.awt.Font("showcard gothic", 1, 13));
		sinvoice.setFont(new java.awt.Font("showcard gothic", 1, 13));
		sorderCan.setFont(new java.awt.Font("showcard gothic", 1, 13));

		inv1.setFont(new java.awt.Font("showcard gothic", 1, 13));
		pmat.setFont(new java.awt.Font("showcard gothic", 1, 13));
		smat.setFont(new java.awt.Font("showcard gothic", 1, 13));
		
		misPur.add(misPurOrd);
		misPur.add(misPurInv);
		
		misSales.add(misSalOrd);
		misSales.add(misSalInv);

		

		report.add(inv1);
		report.add(misPur);
		report.add(misSales);
		//modified
		report.add(customers1);
		customers1.add(customers12);
		customers1.add(customers123);

		report.add(suppliers1);
		suppliers1.add(suppliers12);
		suppliers1.add(suppliers123);

		report.add(products1);
		products1.add(products12);
		products1.add(products123);
		
		customers1.setFont(new java.awt.Font("showcard gothic", 1, 13));
		customers12.setFont(new java.awt.Font("showcard gothic", 1, 13));
		customers123.setFont(new java.awt.Font("showcard gothic", 1, 13));

		products1.setFont(new java.awt.Font("showcard gothic", 1, 13));
		products12.setFont(new java.awt.Font("showcard gothic", 1, 13));
		products123.setFont(new java.awt.Font("showcard gothic", 1, 13));


		suppliers1.setFont(new java.awt.Font("showcard gothic", 1, 13));
		suppliers12.setFont(new java.awt.Font("showcard gothic", 1, 13));
		suppliers123.setFont(new java.awt.Font("showcard gothic", 1, 13));
	
		report.setFont(new java.awt.Font("showcard gothic", 1, 13));
		misPur.setFont(new java.awt.Font("showcard gothic", 1, 13));
		misPurOrd.setFont(new java.awt.Font("showcard gothic", 1, 13));
		misPurInv.setFont(new java.awt.Font("showcard gothic", 1, 13));
		misSales.setFont(new java.awt.Font("showcard gothic", 1, 13));
		misSalQuo.setFont(new java.awt.Font("showcard gothic", 1, 13));
		misSalOrd.setFont(new java.awt.Font("showcard gothic", 1, 13));
		misSalInv.setFont(new java.awt.Font("showcard gothic", 1, 13));
	
		//theme.add(nimrod);
		//theme.add(liquid);
		//theme.add(officexp);
		theme.add(BlueIce);
		theme.add(BlackMoon);
        theme.add(SilverMoon);
		theme.add(BlueMoon);
		theme.add(OrangeMetallic);
		theme.add(metal);
		theme.add(MauveMetallic);
		theme.add(BlueSteel);
		theme.add(BlackStar);
		theme.add(ptd);
		theme.add(quaqua);
		theme.add(GreenDream);
		theme.add(Synthetica);
		
		help.add(helptop);
		help.add(calc);
        
		theme.setFont(new java.awt.Font("showcard gothic", 1, 13));
		nimrod.setFont(new java.awt.Font("showcard gothic", 1, 13));
		liquid.setFont(new java.awt.Font("showcard gothic", 1, 13));
		officexp.setFont(new java.awt.Font("showcard gothic", 1, 13));
		OrangeMetallic.setFont(new java.awt.Font("showcard gothic", 1, 13));
		metal.setFont(new java.awt.Font("showcard gothic", 1, 13));
		MauveMetallic.setFont(new java.awt.Font("showcard gothic", 1, 13));
		//hippo.setFont(new java.awt.Font("showcard gothic", 1, 13));
		Synthetica.setFont(new java.awt.Font("showcard gothic", 1, 13));
		quaqua.setFont(new java.awt.Font("showcard gothic", 1, 13));
		BlackMoon.setFont(new java.awt.Font("showcard gothic", 1, 13));
		ptd.setFont(new java.awt.Font("showcard gothic", 1, 13));
		GreenDream.setFont(new java.awt.Font("showcard gothic", 1, 13));
		BlackStar.setFont(new java.awt.Font("showcard gothic", 1, 13));
		BlueSteel.setFont(new java.awt.Font("showcard gothic", 1, 13));
		BlueIce.setFont(new java.awt.Font("showcard gothic", 1, 13));
		SilverMoon.setFont(new java.awt.Font("showcard gothic", 1, 13));
		BlueMoon.setFont(new java.awt.Font("showcard gothic", 1, 13));
		
		help.setFont(new java.awt.Font("showcard gothic", 1, 13));
		helptop.setFont(new java.awt.Font("showcard gothic", 1, 13));
		bar.add(main);
		bar.add(database);
		bar.add(purchasing);
		bar.add(sales);
		//bar.add(inv);
		bar.add(report);
		bar.add(theme);
		bar.add(help);
		

		customers.setEnabled(false);
		products.setEnabled(false);
		supplier.setEnabled(false);
		createUser.setEnabled(false);
	
		porder.setEnabled(false);
		pinvoice.setEnabled(false);
		porderCan.setEnabled(false);

		quotation.setEnabled(false);
		sorder.setEnabled(false);
		sinvoice.setEnabled(false);
		sorderCan.setEnabled(false);

		smat.setEnabled(false);
		pmat.setEnabled(false);

		misPur.setEnabled(false);
		misSales.setEnabled(false);
		inv1.setEnabled(false);
		customers1.setEnabled(false);
		suppliers1.setEnabled(false);
		products1.setEnabled(false);

		if(log==1)
		{
			customers.setEnabled(true);
			products.setEnabled(true);
			supplier.setEnabled(true);
			createUser.setEnabled(true);
		

			porder.setEnabled(true);
			pinvoice.setEnabled(true);
			porderCan.setEnabled(true);

			quotation.setEnabled(true);
			sorder.setEnabled(true);
			sinvoice.setEnabled(true);
			sorderCan.setEnabled(true);

			smat.setEnabled(true);
			pmat.setEnabled(true);

			misPur.setEnabled(true);
			misSales.setEnabled(true);
			inv1.setEnabled(true);

			customers1.setEnabled(true);

			suppliers1.setEnabled(true);

			products1.setEnabled(true);

			l1.setText("WELCOME "+LogType+" Type : Manager");

		}

		if(log==2)
		{
			porder.setEnabled(true);
			pinvoice.setEnabled(true);
			porderCan.setEnabled(true);
			pmat.setEnabled(true);
			misPur.setEnabled(true);

			l1.setText("WELCOME "+LogType+" Type : Purchase Department");
			
		}

		if(log==3)
		{
			quotation.setEnabled(true);
			sorder.setEnabled(true);
			sinvoice.setEnabled(true);
			sorderCan.setEnabled(true);
			smat.setEnabled(true);
			misSales.setEnabled(true);

			l1.setText("WELCOME "+LogType+" Type : Sales Department");
			

		}


			
		createUser.addActionListener(this);
		changePass.addActionListener(this);
		customers.addActionListener(this);
        products.addActionListener(this);
        supplier.addActionListener(this);
		logOf.addActionListener(this);
		exit.addActionListener(this);

		porder.addActionListener(this);
		pinvoice.addActionListener(this);
		porderCan.addActionListener(this);

		quotation.addActionListener(this);
		sorder.addActionListener(this);
		sinvoice.addActionListener(this);
		sorderCan.addActionListener(this);

		inv1.addActionListener(this);
		pmat.addActionListener(this);
		smat.addActionListener(this);

		misPurOrd.addActionListener(this);
		misPurInv.addActionListener(this);
		misSalQuo.addActionListener(this);
		misSalOrd.addActionListener(this);
		misSalInv.addActionListener(this);
		
		//
		customers12.addActionListener(this);
		customers123.addActionListener(this);
		//
		nimrod.addActionListener(this);
		liquid.addActionListener(this);
		officexp.addActionListener(this);
		OrangeMetallic.addActionListener(this);
		metal.addActionListener(this);
		MauveMetallic.addActionListener(this);
		//hippo.addActionListener(this);
		Synthetica.addActionListener(this);
		quaqua.addActionListener(this);
        BlackMoon.addActionListener(this);
		ptd.addActionListener(this);
		GreenDream.addActionListener(this);
		BlackStar.addActionListener(this);
		BlueSteel.addActionListener(this);
		BlueIce.addActionListener(this);
		SilverMoon.addActionListener(this);
		BlueMoon.addActionListener(this);
		

		suppliers12.addActionListener(this);
		suppliers123.addActionListener(this);

		products12.addActionListener(this);
		products123.addActionListener(this);

		helptop.addActionListener(this);
		calc.addActionListener(this);

		main.setMnemonic('m');
		database.setMnemonic('d');
		customers.setMnemonic('c');
		products.setMnemonic('p');
		supplier.setMnemonic('s');
		createUser.setMnemonic('u');
		changePass.setMnemonic('p');
		logOf.setMnemonic('l');
		exit.setMnemonic('x');

		purchasing.setMnemonic('p');
		porder.setMnemonic('o');
		pinvoice.setMnemonic('i');
		porderCan.setMnemonic('c');
		pmat.setMnemonic('m');

		sales.setMnemonic('s');
		quotation.setMnemonic('q');
		sorder.setMnemonic('o');
		sinvoice.setMnemonic('i');
		sorderCan.setMnemonic('c');
		smat.setMnemonic('m');

		report.setMnemonic('r');
		inv1.setMnemonic('i');
		misPur.setMnemonic('p');
		misSales.setMnemonic('s');


		misPurOrd.setMnemonic('o');
		misPurInv.setMnemonic('i');
		misSalOrd.setMnemonic('o');
		misSalInv.setMnemonic('i');
		
		theme.setMnemonic('t');
		nimrod.setMnemonic('n');
		//officexp.setMnemonic('o');
         OrangeMetallic.setMnemonic('o');
		liquid.setMnemonic('l');
		metal.setMnemonic('m');
		//hippo.setMnemonic('h');
		MauveMetallic.setMnemonic('v');
		Synthetica.setMnemonic('t');
		quaqua.setMnemonic('q');
	     BlackMoon.setMnemonic('b');
		 ptd.setMnemonic('p');
		  GreenDream.setMnemonic('d');
        BlackStar.setMnemonic('s');
		BlueSteel.setMnemonic('e');
		BlueIce.setMnemonic('i');
	    BlueMoon.setMnemonic('u');
        SilverMoon.setMnemonic('r');
		
	
		customers1.setMnemonic('c');
		customers12.setMnemonic('p');
		customers123.setMnemonic('e');
		
		suppliers1.setMnemonic('s');
		suppliers12.setMnemonic('p');
		suppliers123.setMnemonic('e');

		products1.setMnemonic('o');
		products12.setMnemonic('p');
		products123.setMnemonic('e');


		date1=df.format(date);
		d=date1.indexOf('/');
		mon=date1.substring(0,d);
		d1=date1.lastIndexOf('/');
		datex=date1.substring(d+1,d1);
		yearx=date1.substring(d1+1);
		year="20"+yearx;
		currentDate=year+"-"+mon+"-"+datex;
		System.out.println("current date  "+currentDate);

		dt=Integer.parseInt(datex);

		if(dt > 24)
	   {
			misVed.setEnabled(true);
	   }


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);
        setSize(800,575);
        setVisible(true);
		setResizable(true);

		th.start();
    }

	public void actionPerformed(ActionEvent e)
	{

		if(e.getSource()==customers)
		{
			// code for calling customer form
			CustForm2 cf = new CustForm2();
		}
			
		if(e.getSource()== products)
		{
			// code for calling product form
			ProductForm pf= new ProductForm();
		}

		if(e.getSource()== supplier)
		{
			// code for calling sales order form
			SuplForm p=new  SuplForm();
		}

		if(e.getSource()== createUser)
		{
			//CreateUser cu = new CreateUser();
			CreateUser cu = new CreateUser();
		}

		if(e.getSource()== changePass)
		{
			
			ChangePass  cp = new ChangePass ();
		}

		if(e.getSource()== logOf)
		{
			// code for calling Login form
			
			JOptionPane cof=new JOptionPane();
			
			if(cof.showConfirmDialog(null,"Do you want to Continue ?","Log Off Confirmation ",JOptionPane.YES_NO_OPTION)==cof.YES_OPTION)
			{
				
				setVisible(false);
				User1 u=new User1();
			}
			
		}

		if(e.getSource()== quotation)
		{
			// code for calling quotation form
			Quot q= new Quot();
		}
		if(e.getSource()== sorder)
		{
			// code for calling sales order form
			SOrder s=new SOrder();
		}

		if(e.getSource()== sinvoice)
		{
			// code for calling sales order form
			 SInvoice s=new SInvoice();
		}

		if(e.getSource()== sorderCan)
		{
			// code for calling sales order form
			SOrdCan s=new SOrdCan();
		}

		if(e.getSource()== porder)
		{
			// code for calling sales order form
			POrder s=new POrder();
		}

		if(e.getSource()== pinvoice)
		{
			// code for calling sales order form
			PInvoice s=new PInvoice();
		}

		if(e.getSource()== porderCan)
		{
			// code for calling sales order form
			POrdCan s=new POrdCan();
		}

		if(e.getSource()== inv1)
		{
			// code for calling sales order form
			Inventory2 s=new Inventory2();
			
		}

		if(e.getSource()== pmat)
		{
			// code for calling sales order form
			PMatRet s=new PMatRet();
		}

		if(e.getSource()== smat)
		{
			// code for calling sales order form
			SMatRet s=new SMatRet();
		}

		if(e.getSource()== misPurOrd)
		{
			MisOrdPen s=new MisOrdPen();
		}

		if(e.getSource()== misPurInv)
		{
			MisInvPur s=new MisInvPur();
		}

		if(e.getSource()== misSalQuo)
		{
			
		}

		if(e.getSource()== misSalOrd)
		{
			MisOrdPenS s=new MisOrdPenS();
		}

		if(e.getSource()== misSalInv)
		{
			MisInvSale s=new MisInvSale();
		}

		/*if(e.getSource()== misVed)
		{
			XandY x = new XandY();
		}*/

		if(e.getSource()==customers12)
		{
			precust pc=new precust();
		}

		if(e.getSource()==customers123)
		{
			ecust ec=new ecust();
		}

		if(e.getSource()==suppliers12)
		{
			presup ps=new presup();
		}

		if(e.getSource()==suppliers123)
		{
			esup es=new esup();
		}

		if(e.getSource()==products12)
		{
			preprod pp=new preprod();
		}

		if(e.getSource()==products123)
		{
			eprod ep=new eprod();
		}

		if(e.getSource()== th)
		{
			l3.setText((new Date()).toString());
		}


		if(e.getSource()== nimrod)
		
		{			
			try
			{
				
				UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");
				SwingUtilities.updateComponentTreeUI(this);
				setVisible(false);
				setVisible(true);
			}
			catch(Exception ee)
			{
				System.out.println("unable");
			}
		}


	if(e.getSource()== liquid)
{
		
			try
		{
				
		UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");;
		SwingUtilities.updateComponentTreeUI(this);
		setVisible(false);
				setVisible(true);
		}
		catch(Exception ee1)
		{
			System.out.println("unable");
		}
}

			if(e.getSource()== officexp)
{
		
			try
		{
		UIManager.setLookAndFeel("org.fife.plaf.OfficeXP.OfficeXPLookAndFeel");
		SwingUtilities.updateComponentTreeUI(this);
		setVisible(false);
		setVisible(true);
		}
		catch(Exception ee2)
		{
			System.out.println("unable");
		}
}



			if(e.getSource()== OrangeMetallic)
		
{			try
	{
		UIManager.setLookAndFeel(new SyntheticaOrangeMetallicLookAndFeel());
		SwingUtilities.updateComponentTreeUI(this);
		
		setVisible(false);
		setVisible(true);
		}
		catch(Exception ee4)
		{
			System.out.println("unable");
		}
}

if(e.getSource()== metal)
		
		{			
			try
			{
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				SwingUtilities.updateComponentTreeUI(this);
				setVisible(false);
				setVisible(true);
			}
			catch(Exception ee3)
			{
				System.out.println("unable");
			}
		}
		if(e.getSource()== MauveMetallic)
		
		{			
			try
			{
				UIManager.setLookAndFeel(new SyntheticaMauveMetallicLookAndFeel());
				SwingUtilities.updateComponentTreeUI(this);
				setVisible(false);
				setVisible(true);
			}
			catch(Exception ee5)
			{
				System.out.println("unable");
			}
		}

		if(e.getSource()== Synthetica)
		
		{			
			try
			{
				UIManager.setLookAndFeel(new SyntheticaStandardLookAndFeel());
				SwingUtilities.updateComponentTreeUI(this);
				setVisible(false);
				setVisible(true);
			}
			catch(Exception ee6)
			{
				System.out.println("unable");
			}
		}

if(e.getSource()== GreenDream)
{
		
			try
		{
		UIManager.setLookAndFeel(new SyntheticaGreenDreamLookAndFeel());
		SwingUtilities.updateComponentTreeUI(this);
		setVisible(false);
		setVisible(true);
		}
		catch(Exception ee7)
		{
			System.out.println("unable");
		}
}

if(e.getSource()== quaqua)
		
		{			
			try
			{
				UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
				SwingUtilities.updateComponentTreeUI(this);
				setVisible(false);
				setVisible(true);
			}
			catch(Exception ee8)
			{
				System.out.println("unable");
			}
		}
		

if(e.getSource()== BlackMoon)
		
		{			
			try
			{
				UIManager.setLookAndFeel(new SyntheticaBlackMoonLookAndFeel());
				SwingUtilities.updateComponentTreeUI(this);
				setVisible(false);
				setVisible(true);
			}
			catch(Exception ee9)
			{
				System.out.println("unable");
			}
		}
		if(e.getSource()== BlackStar)
		
		{			
			try
			{
				UIManager.setLookAndFeel(new SyntheticaBlackStarLookAndFeel());
				SwingUtilities.updateComponentTreeUI(this);
				setVisible(false);
				setVisible(true);
			}
			catch(Exception ee10)
			{
				System.out.println("unable");
			}
		}

if(e.getSource()==ptd)
		
		{			
			try
			{   
				UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
				SwingUtilities.updateComponentTreeUI(this);
				setVisible(false);
				setVisible(true);
			}
			catch(Exception ee11)
			{
				System.out.println("unable");
			}
		}
		
		if(e.getSource()== BlueMoon)
		
		{			
			try
			{
				UIManager.setLookAndFeel(new SyntheticaBlueMoonLookAndFeel());
				SwingUtilities.updateComponentTreeUI(this);
				setVisible(false);
				setVisible(true);
			}
			catch(Exception ee12)
			{
				System.out.println("unable");
			}
		}
		if(e.getSource()== BlueIce)
		
		{			
			try
			{
				UIManager.setLookAndFeel(new SyntheticaBlueIceLookAndFeel());
				SwingUtilities.updateComponentTreeUI(this);
				setVisible(false);
				setVisible(true);
			}
			catch(Exception ee13)
			{
				System.out.println("unable");
			}
		}
		if(e.getSource()== SilverMoon)
		
		{			
			try
			{
				UIManager.setLookAndFeel(new SyntheticaSilverMoonLookAndFeel());
				SwingUtilities.updateComponentTreeUI(this);
				setVisible(false);
				setVisible(true);
			}
			catch(Exception ee14)
			{
				System.out.println("unable");
			}
		}
		if(e.getSource()== BlueSteel)
		
		{			
			try
			{
				UIManager.setLookAndFeel(new SyntheticaBlueSteelLookAndFeel());
				SwingUtilities.updateComponentTreeUI(this);
				setVisible(false);
				setVisible(true);
			}
			catch(Exception ee15)
			{
				System.out.println("unable");
			}
		}
		
		if(e.getSource()==exit)
		{
                 // code for exiting the system
			
			JOptionPane exit=new JOptionPane();
			if(exit.showConfirmDialog(null,"Do you really want to exit the system?","Exit Confirmation ",JOptionPane.YES_NO_OPTION)==exit.YES_OPTION)
			System.exit(0);
		}
		if (e.getSource()==helptop)
		{
			
			try {
				
					Desktop.getDesktop().open( new File("docs//Final_report.pdf"));
				} 
				catch (IOException f) {
				 f.printStackTrace();
				 }
			
		}
		if (e.getSource()==calc)
		{		
			System.out.println("Saurabh");
      try 
      {   
		Process p = Runtime.getRuntime().exec("\"C:/WINDOWS/system32/calc.exe\""); 

      }catch(Exception exc){/*handle exception*/}
		
		}
	}
	public static void main(String [] args)
	{
		try
		{
			 //UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");
			//UIManager.setLookAndFeel("de.muntjak.tinylookandfeel.TinyLookAndFeel");
			//UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
			
		}
		catch(Exception e)
		{
			System.out.println("unable");
		}
		MainForm m = new MainForm(1,"Intruder");

		
	}
	
}   //close of class MainForm

