import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * \file 	 DataSet.java
 * \author 	 Gwion Rhys Davies
 * \date	 28 Feb 2013
 * \version  1.4.9
 * 
 * \brief DataSet class to check whether a valid file has been imported.
 * 
 * The class will validate the received file and then store 
 * the attribute data in one single dimensional array with the data
 * values in a 2 dimensional array.
 */

public class DataSet {

	/**
	 * A scanner that scans the entire imported file 
	 */
	private Scanner m_fileScan;
	
	/**
	 * Delimiter separates strings 
	 */
	private String m_delimiter;
	
	/**
	 * A scanner that scans the lines in the .csv file 
	 */
	private Scanner m_lineScanner;
	
	/**
	 * Number of attributes in the .csv file 
	 */
	private int m_attributeCount;
	
	/**
	 * Number of entries of data in .csv file 
	 */
	private int m_entryCount;
	
	/**
	 *  Counter that counts the number of pieces of data in a single line.  
	 */
	private int m_lineDataCount;
	
	/**
	 * One dimensional string array containing all the data from the .csv file
	 */
	private String[] m_attributes;
	
	/**
	 * Two dimensional object array containing all the data from the .csv file 
	 */
	private Object[][] m_data;
	
	/**
	 * Saves all the data of the string temporarily and compares with null
	 */
	private String m_dataEntry;
	
	/** 
	 * Counts the number of empty entries from a single line 
	 */
	private int m_emptyEntryCount;
	
	/** One dimensional object array containing the column data 
	 * from the .csv file 
	 */
	private Object[] m_columnData;
	
	/** 
	 * Counts the number of attributes from a single line 
	 */
	private int m_numberOfAttributes;
	
	
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
	 * @param m_columnNo the desired column
	 */
	public Object[] GetColumnData(int m_columnNo){
		if(!(m_columnNo<m_attributeCount)){
			System.out.println("data entry error");
		} else {
			m_columnData = new Object[m_entryCount];
			for(int i = 0; i<m_entryCount; i++){
				m_columnData[i] = m_data[i][m_columnNo];
			}
			return m_columnData;
		}
		return null;
	}
	/**
	 * @return a String of the 'nth' attribute of the attribute array 'nth'
	 * term being the given argument integer which must be a value between
	 * 0 and the number of attributes in the data set.
	 * @param m_columnNo the desired column
	 */
	public String GetAttributeName(int m_columnNo){
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
	public int GetNoOfEntrys(){
		return m_entryCount;
	}
	/**
	 * @return the number of attributes in the data set.
	 */
	public int GetNoOfAttributes(){
		return m_attributeCount;
	}
	/**
	 * @return TRUE on success if the .csv file is valid and the data set
	 * has been successfully built.
	 * @param m_dataFile the imported .csv file.
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
		m_attributes[0]="Record no";
		for(int i=1; i<m_attributeCount; i++){
			m_attributes[i]=m_lineScanner.next();
		}
		m_data = new Object[m_entryCount][m_attributeCount];
		for(int i=0; i<m_entryCount; i++){
			m_lineScanner = new Scanner(m_fileScan.nextLine());
			m_lineScanner.useDelimiter(m_delimiter);
			m_data[i][0]=i+1;
			for(int n=1; n<m_attributeCount; n++){
				if(!(m_lineScanner.hasNext())){
					i--;
					break;
				}
				m_dataEntry=m_lineScanner.next();
				if(m_dataEntry.equals("")){
					n--;
				} else {
					try{
						m_data[i][n]= Integer.parseInt(m_dataEntry);
					} catch (NumberFormatException nfe){
						m_data[i][n]= Float.parseFloat(m_dataEntry); 
					}
				}	
			}
		}
		System.out.println("Data Set Built");
		return true;
	}
	/**
	 * @return TRUE on success if all validation tests return true.
	 * @param m_dataFile the imported .csv file.
	 */
	private Boolean checkForValidFile(File m_dataFile){
		final JPanel m_frame = new JPanel();
		if(!checkForCommas(m_dataFile)){
			JOptionPane.showMessageDialog(m_frame,
        		    "File not compatible (contains no commas)",
        		    "File error",
        		    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(!checkForAttributes(m_dataFile)){
			JOptionPane.showMessageDialog(m_frame,
        		    "File not compatible (attribute error)",
        		    "File error",
        		    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(!checkForConsistentData(m_dataFile)){
			JOptionPane.showMessageDialog(m_frame,
        		    "File not compatible (data not consistent)",
        		    "File error",
        		    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	/**
	 * @return TRUE on success.
	 * @param m_dataFile the imported .csv file.
	 */
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
			if(!(m_lineDataCount+1-m_emptyEntryCount==m_attributeCount)){
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
	 * @param m_dataFile the imported .csv file.
	 */
	private int countAttributes(File m_dataFile) {
		m_numberOfAttributes = 1;
		//the record no is automatically the first entry 
		//so this var starts at 1;
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
	 * @param m_dataFile the imported .csv file.
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
	 * @param m_dataFile the imported .csv file.
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