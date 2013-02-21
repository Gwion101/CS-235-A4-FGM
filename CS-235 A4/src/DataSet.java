/**
 * \This class checks the received file to see if the file valid and then
 * stores the attribute data in one single dimensional array with the data
 * values in a 2 dimensional array.
 * 
 * @author 	-Gwion Rhys Davies
 * @file 	-DataSet.java
 * @data	-15 Feb '13
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DataSet {

	/** */
	private Scanner m_fileScan;
	/** */
	private String m_delimiter;
	/** */
	private Scanner m_lineScanner;
	/** */
	private int m_attributeCount;
	/** */
	private int m_entryCount;
	/** */
	private int m_lineDataCount;
	/** */
	private String[] m_attributes;
	/** */
	private Object[][] m_data;
	/** */
	private String m_dataEntry;
	/** */
	private int m_emptyEntryCount;
	/** */
	private Object[] m_columnData;
	
	/**
	    * @return the 2 dimensional array of data values.
	    */
	public Object [][] GetData() {
		return m_data;
	}
	/**
	    * @return the array of attribute values.
	    */
	public String[] GetAttributes() {
		return m_attributes;
	}
	/**
	    * @return an array containing a column data values, the column of 
	    * values returned is chosen by the given argument integer which must
	    * be a value between 0 and the number of attributes in the data set.
	    */
	public Object[] getColumnData(int m_columnNo){
		if(!(m_columnNo<m_attributeCount)){
			System.out.println("data entry error");
		} else {
			m_columnData = new Object[m_entryCount];
			for(int i = 0; i<m_entryCount; i++){
				m_columnData[i] = m_data[m_columnNo][i];
			}
			return m_columnData;
		}
		return null;
	}
	/**
	    * @return a String of the 'nth' attribute of the attribute array 'nth'
	    * term being the given argument integer which must be a value between
	    * 0 and the number of attributes in the data set.
	    */
	public String getAttributeName(int m_columnNo){
		if(!(m_columnNo<m_attributeCount)){
			System.out.println("column entry error");
		} else {
			return m_attributes[m_columnNo];
		}
		return null;
	}
	/**
	    * @return the number of entries in the data set.
	    */
	public int getNoOfEntrys(){
		return m_entryCount;
	}
	/**
	    * @return the number of attributes in the data set.
	    */
	public int getNoOfAttributes(){
		return m_attributeCount;
	}
	/**
	    * @return TRUE on success if the csv file is valid and the data set
	    * has been successfully built.
	    */
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
		m_data = new Object[m_attributeCount][m_entryCount];
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
	/**
	    * @return TRUE on success if all validation tests return true.
	    */
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
				if (!(m_lineDataCount-m_emptyEntryCount==0)){
					return false;
				} else {
					m_entryCount--;
				}
			}
			m_entryCount++;
		}
		return true;
	}
	/**
	    * @return TRUE on success.
	    */
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
	/**
	    * @return TRUE on success.
	    */
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
	/**
	    * @return TRUE on success.
	    */
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
} /** end class DataSet */