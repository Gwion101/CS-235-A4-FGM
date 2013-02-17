import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class DataSet {

	private Scanner m_fileScan;
	private String m_delimiter;
	private Scanner m_lineScanner;
	private int m_attributeCount;
	private int m_entryCount;
	private int m_lineDataCount;
	private String[] m_attributes;
	private double[][] m_data;
	private String m_dataEntry;
	private int m_emptyEntryCount;
	private double[] m_columnData;

	public double[] getColumnData(int m_columnNo){
		if(!(m_columnNo<=m_attributeCount)){
			System.out.println("data entry error");
		} else {
			m_columnData = new double[m_entryCount];
			for(int i = 0; i<m_entryCount; i++){
				m_columnData[i] = m_data[m_columnNo][i];
			}
			return m_columnData;
		}
		return null;
	}

	public String getAttributeName(int m_columnNo){
		if(!(m_columnNo<=m_attributeCount)){
			System.out.println("column entry error");
		} else {
			return m_attributes[m_columnNo];
		}
		return null;
	}

	public int getNoOfEntrys(){
		return m_entryCount;
	}

	public int getNoOfAttributes(){
		return m_attributeCount;
	}
	
	public Boolean buildDataSet (File m_dataFile) {
		if(!checkForValidFile(m_dataFile)){
			return false;
		}
		try {
			m_fileScan = new Scanner(m_dataFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_attributes = new String[m_attributeCount];
		m_lineScanner = new Scanner(m_fileScan.nextLine());
		m_lineScanner.useDelimiter(m_delimiter);
		for(int i=0; i<m_attributeCount; i++){
			m_attributes[i]=m_lineScanner.next();
		}
		m_data = new double[m_attributeCount][m_entryCount];
		for(int i=0; i<m_entryCount; i++){
			m_lineScanner = new Scanner(m_fileScan.nextLine());
			m_lineScanner.useDelimiter(m_delimiter);
			for(int n=0; n<m_attributeCount; n++){
				if(!(m_lineScanner.hasNext())){
					i--;
					break;
				}
				m_dataEntry=m_lineScanner.next();
				if(m_dataEntry.equals("")){
					n--;
				} else {
					m_data[n][i]=Double.parseDouble(m_dataEntry); 
				}	
			}
		}
		System.out.println("Data Set Built");
		return true;
	}
	
	private Boolean checkForValidFile(File m_dataFile){
		final JPanel m_frame = new JPanel();
		if(!checkForCommas(m_dataFile)){
			JOptionPane.showMessageDialog(m_frame,
        		    "(contains no commas)",
        		    "File error",
        		    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(!checkForAttributes(m_dataFile)){
			JOptionPane.showMessageDialog(m_frame,
        		    "(attribute error)",
        		    "File error",
        		    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(!checkForConsistentData(m_dataFile)){
			JOptionPane.showMessageDialog(m_frame,
        		    "(data not consistent)",
        		    "File error",
        		    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	private boolean checkForConsistentData(File m_dataFile) {
		try {
			m_fileScan = new Scanner(m_dataFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_entryCount=0;
		m_attributeCount=countAttributes(m_dataFile);
		m_delimiter=",";
		while(m_fileScan.hasNextLine()){
			m_lineScanner = new Scanner(m_fileScan.nextLine());
			m_lineScanner.useDelimiter(m_delimiter);
			m_lineDataCount=0;
			m_emptyEntryCount=0;
			while(m_lineScanner.hasNext()){
				m_dataEntry=m_lineScanner.next();
				if(m_dataEntry.equals("")){
					m_emptyEntryCount++;
					m_lineDataCount--;
				} else {
					try {  
						Double.parseDouble(m_dataEntry);  
					} catch(NumberFormatException nfe) {  
						return false; 
					}
				}
				m_lineDataCount++;
			}
			if(!(m_lineDataCount-m_emptyEntryCount==m_attributeCount)){
				if (!(m_lineDataCount==0)){
					return false;
				} else {
					m_entryCount--;
				}
			}
			m_entryCount++;
		}
		return true;
	}

	private int countAttributes(File m_dataFile) {
		int m_numberOfAttributes = 0;
		m_delimiter=",";
		try {
			m_fileScan = new Scanner(m_dataFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_lineScanner = new Scanner(m_fileScan.nextLine());
		m_lineScanner.useDelimiter(m_delimiter);
		while(m_lineScanner.hasNext()){
			System.out.println(m_lineScanner.next());
			m_numberOfAttributes++;
		}
		System.out.println(m_numberOfAttributes);
		return m_numberOfAttributes;
	}

	private boolean checkForAttributes(File m_dataFile) {
		try {
			m_fileScan = new Scanner(m_dataFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_delimiter=",";
		m_lineScanner= new Scanner(m_fileScan.nextLine());
		m_lineScanner.useDelimiter(m_delimiter);
		while(m_lineScanner.hasNext()){
			try {  
				Double.parseDouble(m_lineScanner.next());  
			} catch(NumberFormatException nfe) {  
				return true;  
			}
		}
		return false; 
	}
	
	private Boolean checkForCommas (File m_dataFile) {
		m_delimiter="";
		try {
			m_fileScan = new Scanner(m_dataFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_fileScan.useDelimiter(m_delimiter);
		while(m_fileScan.hasNext()){
			String m_charCheck= m_fileScan.next();
			if(m_charCheck.equals(",")){
				return true;
			}
		}
		return false;
	}
}