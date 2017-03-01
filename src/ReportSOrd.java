
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



class  ReportSOrd //extends JFrame //implements ActionListener ,Printable
{
	java.util.Date date = new java.util.Date();

	int cnt=0;

	String[] col = { "Product Name", "Price", "Qty Ord", "Qty Dis",
					"Qty Pen", "Qty Ret"};
	int NumColumns = 6;

	String[] ner= new String[50];
	String[] ProName = new String[50];
	String[] ProQ = new String[50];
	String[] ProQord = new String[50];
	String[] ProQdes= new String[50];
	String[] ProQpen = new String[50];
	String[] ProQret = new String[50];

	String dt1,dt2,ordno;

	ReportSOrd(String orinfo[],String Pname[],String PP[],String PQ[],String PQD[],String PQP[],String PQR[])
	{
		for(int j=0;j<orinfo.length;j++)
		{
			ner[j]=orinfo[j];
		}
		
		for(int k=0;k<Pname.length;k++)
		{
			ProName[k]=Pname[k];
			cnt++;
		}

		for(int k=0;k<PP.length;k++)
		{
			ProQ[k]=PP[k];
		}

		for(int k=0;k<PQ.length;k++)
		{
			ProQord[k]=PQ[k];
		}

		for(int k=0;k<PQD.length;k++)
		{
			ProQdes[k]=PQD[k];
		}

		for(int k=0;k<PQP.length;k++)
		{
			ProQpen[k]=PQP[k];
		}

		for(int k=0;k<PQR.length;k++)
		{
			ProQret[k]=PQR[k];
		}

		Document document = new Document();
		
		try {
			// step 2:
			// we create a writer that listens to the document
			// and directs a PDF-stream to a file
			PdfWriter.getInstance(document,
					new FileOutputStream("SOrder.pdf"));

			// step 3: we open the document
			document.open();
			// step 4: we add a paragraph to the document
			BaseFont bfComic1 = BaseFont.createFont("VeraSe.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			BaseFont bfComic = BaseFont.createFont("COUR.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bfComic1,20,1);
			String text1 = "                           OMKAR DISTRIBUTORS";
			String t2 =     "                          Shop No. 1/B, Shreenath A, ";
			String t3 =     "                          Kalwa,Thane 400605.Phone no:022-256449260";
			String t1 = " ";
			String text2 = "  Sales Order Details from date  "+ner[0]+" to date "+ner[1]+" ";
			String text3 = "    Order No.: "+ner[2]+"                   Order Date : "+ner[3]+" ";
			String text4 = "    Customer Name : "+ner[4]+"              Status : "+ner[5]+" ";
			String text5 = "    Dispatched Amount : "+ner[7]+"          Returned Amount : "+ner[9]+" ";
			String text6 = "    Pending Amount : "+ner[8]+"             Total Amount : "+ner[6]+" ";
			String text7 = "_______________________________________________________________________";
			String text8 ="Date : "+date+" ";;
			PdfPTable datatable = new PdfPTable(NumColumns);
			int headerwidths[] = { 15, 5, 7, 7, 7, 6 }; // percentage
			datatable.setWidths(headerwidths);
			datatable.setWidthPercentage(100); // percentage
			datatable.getDefaultCell().setPadding(3);
			datatable.getDefaultCell().setBorderWidth(2);
			datatable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			datatable.addCell("Product Name");
			datatable.addCell("Price");
			datatable.addCell("Qty Ord");
			datatable.addCell("Qty Pen");
			datatable.addCell("Qty Dis");
			datatable.addCell("Qty Ret");
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
			font = new Font(bfComic, 12,1);
			document.add(new Paragraph(text3, font));
			font = new Font(bfComic, 9);
			document.add(new Paragraph(t1, font));
			font = new Font(bfComic, 12,1);
			document.add(new Paragraph(text4, font));
			font = new Font(bfComic, 9);
			document.add(new Paragraph(t1, font));
			document.add(new Paragraph(t1, font));
			font = new Font(bfComic, 12,1);
			document.add(new Paragraph(text5, font));
			font = new Font(bfComic, 9);
			document.add(new Paragraph(t1, font));
			font = new Font(bfComic, 12,1);
			document.add(new Paragraph(text6, font));
			document.add(new Paragraph(t1, font));
			document.add(new Paragraph(t1, font));

			datatable.setHeaderRows(1); // this is the end of the table header

			datatable.getDefaultCell().setBorderWidth(1);
			for (int i = 0; i < cnt; i++) {
				if (i % 2 == 1) {
					datatable.getDefaultCell().setGrayFill(0.9f);
				}
				
				
					datatable.addCell(ProName[i]);
					datatable.addCell(ProQ[i]);
					datatable.addCell(ProQord[i]);
					datatable.addCell(ProQdes[i]);
					datatable.addCell(ProQpen[i]);
					datatable.addCell(ProQret[i]);
			
				
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


			//document.add(new Paragraph("RAHUL DISTRIBUTORS"));
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
			String path[]={"Foxit Reader.exe","SOrder.pdf"};
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
		ReportSOrd r=new ReportSOrd(x,y,z,a,b,c,d);
		System.out.println("Hello World!");
	}
}

