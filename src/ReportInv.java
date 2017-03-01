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
import java.text.*;
import java.awt.print.*;

import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;



class  ReportInv extends JFrame //implements ActionListener ,Printable
{
	String msg="hello";

	java.util.Date date = new java.util.Date();

	int cnt=0;

	String[] col = { "Product Name", "Uses", "Cumulative usage", "Cumu. usage %",
					"Category"};
	int NumColumns = 6;

	String pn[]= new String[100]; 
	String pq[]= new String[100]; 
	String mi[]= new String[100]; 
	String ma[]= new String[100]; 
	String av[]= new String[100]; 
	String reo[]= new String[100]; 
	String ner[]= new String[10]; 

	String dt1,dt2,ordno;
	ReportInv(String orinfo[],String Pname[],String Q[],String MI[],String MA[],String AV[],String REO[],int c)
	{
		System.out.println("cusntrocudksl call");
		cnt=c;
		for(int j=0;j<orinfo.length;j++)
		{
			ner[j]=orinfo[j];
			System.out.println("in report value of orinfo is"+ner[j]);
		}
		
		for(int k=0;k<Pname.length;k++)
		{
			pn[k]=Pname[k];
			System.out.println("in report value of product name is"+pn[k]);
			//cnt++;
		}

		for(int k=0;k<Q.length;k++)
		{
			pq[k]=Q[k];
		}

		for(int k=0;k<MI.length;k++)
		{
			mi[k]=MI[k];
		}

		for(int k=0;k<MA.length;k++)
		{
			ma[k]=MA[k];
		}

		for(int k=0;k<AV.length;k++)
		{
			av[k]=AV[k];
		}

		for(int k=0;k<REO.length;k++)
		{
			reo[k]=REO[k];
		}

		

		Document document = new Document();
		
		try {
			// step 2:
			// we create a writer that listens to the document
			// and directs a PDF-stream to a file
			PdfWriter.getInstance(document,
					new FileOutputStream("Inventory.pdf"));

			// step 3: open the document
			document.open();
			// step 4: add a paragraph to the document
			BaseFont bfComic1 = BaseFont.createFont("VeraSe.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			BaseFont bfComic = BaseFont.createFont("COUR.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bfComic1,20,1);
			String text1 = "                           OMKAR DISTRIBUTORS";
			String t2 =     "                          Shop No. 1/B, Shreenath A, ";
			String t3 =     "                          Kalwa,Thane 400605.Phone no:022-256449260";
			String t1 = " ";
			String text2 = "  Inventory Details from date  "+ner[0]+" to date "+ner[1]+" ";
			String text7 = "________________________________________________________________________";
			String text8 ="Date : "+date+" ";;
			PdfPTable datatable = new PdfPTable(NumColumns);//IT CREATES AN  TABLE
			int headerwidths[] = { 19,10, 7, 9, 9,9 }; // percentage
			datatable.setWidths(headerwidths);
			datatable.setWidthPercentage(100); // percentage
			datatable.getDefaultCell().setPadding(3);
			datatable.getDefaultCell().setBorderWidth(2);
			datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			datatable.addCell("Product Name");
			datatable.addCell("Quantity");
			datatable.addCell("Minimum Level");
			datatable.addCell("Maximum Level");
			datatable.addCell("Average Level");
			datatable.addCell("Reorder Level");
			datatable.setHeaderRows(1);
			font.setColor(Color.red);
			document.add(new Paragraph(text1, font));
			font = new Font(bfComic, 5);
			document.add(new Paragraph(t1, font));
			font = new Font(bfComic, 10,1);
			document.add(new Paragraph(t2, font));
			document.add(new Paragraph(t3, font));
			font = new Font(bfComic, 12,1);
			document.add(new Paragraph(text7, font));
			//font = new Font(bfComic, 9);
			//document.add(new Paragraph(t1, font));
			font = new Font(bfComic, 5);
			document.add(new Paragraph(t1, font));
			font = new Font(bfComic, 13,1);
			document.add(new Paragraph(text2, font));
			font = new Font(bfComic, 9);
			document.add(new Paragraph(t1, font));
			document.add(new Paragraph(t1, font));
			document.add(new Paragraph(t1, font));
			font = new Font(bfComic, 11,1);

			datatable.setHeaderRows(1); // this is the end of the table header

			datatable.getDefaultCell().setBorderWidth(1);
			for (int i = 0; i < cnt; i++) {
				if (i % 2 == 1) {
					datatable.getDefaultCell().setGrayFill(0.9f);
				}
				
				
					datatable.addCell(pn[i]);
					datatable.addCell(pq[i]);
					datatable.addCell(mi[i]);
					datatable.addCell(ma[i]);
					datatable.addCell(av[i]);
					datatable.addCell(reo[i]);
			
				
				if (i % 2 == 1) {
					datatable.getDefaultCell().setGrayFill(0.0f);
				}
			}

			document.add(datatable);

			font = new Font(bfComic, 9);
			document.add(new Paragraph(t1, font));
			//document.add(new Paragraph(t1, font));
			font = new Font(bfComic, 12,1);
			document.add(new Paragraph(text7, font));
			font = new Font(bfComic, 9);
			//document.add(new Paragraph(t1, font));
			font = new Font(bfComic, 11,1);
			document.add(new Paragraph(text8, font));


			//document.add(new Paragraph("RAHUL DISRIBUTORS"));
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}

		// step 5: we close the document
		document.close();
		try
		{
			Runtime r;
			r=Runtime.getRuntime();
			Process p;
			String path[]={"Foxit Reader.exe","Inventory.pdf"};
			p=r.exec(path);
		}
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(null,ioe.getMessage(),"IOException",JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	public static void main(String[] args) 
	{
		String[] x= {"sjkdhk","sduu","ggggg"};
		String[] y= {"p1","p2","p3","p4"};
		String[] z= {"23","43","21","65"};
		String[] a= {"100","200","180","300"};
		String[] b= {"50","40","60","150"};
		String[] c= {"50","160","60","150"};
		String[] d= {"10","6","9","22"};
		ReportInv r=new ReportInv(x,y,z,a,b,c,d,4);
		System.out.println("Hello World!");
	}
}

