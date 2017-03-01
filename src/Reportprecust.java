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



class  Reportprecust extends JFrame //implements ActionListener ,Printable
{
	String msg="hello";

	java.util.Date date = new java.util.Date();

	int cnt=0;

	String[] col = { "Customer Id", "Customer Name", "Delivery Address", "Contact No"};
	int NumColumns = 4;


	String ner[]= new String[10]; 
	String cid[]= new String[100]; 
	String cnm[]= new String[100]; 
	String cdl[]= new String[100]; 
	String cno[]= new String[100]; 
	
	String dt1,dt2,ordno;

	Reportprecust(String orinfo[],String CID[],String CN[],String DA[],String CNO[],int c)
	{
		System.out.println("call constr");
		cnt=c;
		for(int j=0;j<orinfo.length;j++)
		{
			ner[j]=orinfo[j];
			System.out.println(ner[j]);
		}
		
		for(int k=0;k<CID.length;k++)
		{
			cid[k]=CID[k];
			System.out.println("pname is"+CID[k]);
			System.out.println(cid[k]);
			//cnt++;
		}

		for(int k=0;k<CN.length;k++)
		{
			cnm[k]=CN[k];
		}

		for(int k=0;k<DA.length;k++)
		{
			cdl[k]=DA[k];
		}
		for(int k=0;k<CNO.length;k++)
		{
			cno[k]=CNO[k];
		}
		
		Document document = new Document();
		
		try {
			PdfWriter.getInstance(document,
					new FileOutputStream("Precust.pdf"));

			// step 3: we open the document
			document.open();
			// step 4: we add a paragraph to the document
			BaseFont bfComic1 = BaseFont.createFont("VeraSe.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			BaseFont bfComic = BaseFont.createFont("COUR.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bfComic1,20,1);
			String text1 = "                           OMKAR DISTRIBUTORS";
			String t2 =     "                          Shop No. 1/B, Shreenath A, ";
			String t3 =     "                          Kalwa,Thane 400605.Phone no:022-9920477522";
			String t1 = " ";
			String text2 = "  Previous Customers Details from date  "+ner[0]+" to date "+ner[1]+" ";
			String text7 = "________________________________________________________________________";
			String text8 ="Date : "+date+" ";;
			PdfPTable datatable = new PdfPTable(NumColumns);
			int headerwidths[] = { 8, 16, 15, 8}; // percentage
			datatable.setWidths(headerwidths);
			datatable.setWidthPercentage(100); // percentage
			datatable.getDefaultCell().setPadding(3);
			datatable.getDefaultCell().setBorderWidth(2);
			datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			datatable.addCell("Customer Id");
			datatable.addCell("Customer Name");
			datatable.addCell("Delivery Address");
			datatable.addCell("Contact No");
			//datatable.addCell("Average Level");
			//datatable.addCell("Reorder Level");
			datatable.setHeaderRows(1);
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
				
				
					datatable.addCell(cid[i]);
					datatable.addCell(cnm[i]);
					datatable.addCell(cdl[i]);
					datatable.addCell(cno[i]);
					
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
			String path[]={"Foxit Reader.exe","Precust.pdf"};
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
		String[] y= {"c1","c2","c3","c4"};
		String[] z= {"dushant","vijay","nilesh","karan"};
		String[] a= {"gorai","bhayander","Kandivali","kandivali"};
		String[] b= {"28011812","25252525","2846846","2854621"};
	
		Reportprecust r=new Reportprecust(x,y,z,a,b,4);
		
		System.out.println("Hello World!");
	}
}

