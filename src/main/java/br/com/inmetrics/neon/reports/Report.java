package br.com.inmetrics.neon.reports;

import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.text.Document;
import org.apache.poi.xwpf.usermodel.*;

public class Report {
	
	public void createWord(String nomeArq) throws FileNotFoundException{
	
		FileOutputStream out = new FileOutputStream(new File("C:\\Testes\\" + nomeArq + ".docx"));	
		
	}

}
