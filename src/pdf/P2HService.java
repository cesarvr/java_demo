package pdf;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.util.ImageIOUtil;
import org.apache.pdfbox.util.PDFTextStripper;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class P2HService {

	public String Strip(String path) throws FileNotFoundException, IOException {
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		File file = new File(path);

		PDFParser parser = new PDFParser(new FileInputStream(file));
		parser.parse();
		cosDoc = parser.getDocument();
		pdfStripper = new PDFTextStripper();
		pdDoc = new PDDocument(cosDoc);
		pdfStripper.setStartPage(1);
		pdfStripper.setEndPage(pdDoc.getNumberOfPages());
		String parsedText = pdfStripper.getText(pdDoc);

		pdDoc.close();
		return parsedText;
	}

	public byte[] preview(String pdfFilename) throws IOException {

		PDDocument document;

		document = PDDocument.loadNonSeq(new File(pdfFilename), null);

		PDPage pdPage = (PDPage) document.getDocumentCatalog().getAllPages().get(0);
		int page = 0;

		BufferedImage bim = pdPage.convertToImage(BufferedImage.TYPE_INT_RGB, 300);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIOUtil.writeImage(bim, "jpg", baos);

		document.close();

		return baos.toByteArray();
	}

	public byte[] html2pdf(String str) {
		ByteArrayOutputStream ostream = null;

		try {
			ostream = new ByteArrayOutputStream();

			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, ostream);
			document.open();
			InputStream is = new ByteArrayInputStream(str.getBytes());
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
			document.close();
			ostream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ostream.toByteArray();
	}

	public String transform(String name) {
		return " [ boom ] " + name + "[ boom ]";
	}

	public short test1() {
		return 0;
	}

	public String concat(String name, String secondName) {

		return transform(name + " " + secondName);
	}

	public String concatHeavy(String name, String secondName, int time) throws InterruptedException {

		Thread.sleep(time);
		return transform(name + " " + secondName);
	}

	public int add(int x, int y) {

		return x + y;
	}
	
	public float add(float x, float y) {

		return x + y;
	}
	
	public double add(double x, double y){
		return x + y;
	}

	public byte getByte() {
		byte i = 0x1;
		return i;
	}
	
	public int[] getInts() {
		int MAX = 10;
		int[] i = new  int[MAX];
		for(int x=0; x<MAX; x++)
			i[x]=1;
		return i;
	}
	
	public void fff(float x) {
		System.out.println("hello");
	}

	public int fff(int x){return 1;}
	
	
	public static void main(String[] args) {
		
		//Methods m = P2HService.class.getMethods();
		
	}
}
