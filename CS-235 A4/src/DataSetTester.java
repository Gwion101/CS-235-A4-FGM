import java.io.File;


public class DataSetTester {
	public static void main(String args[]){
		DataSet dataset = new DataSet();
		File file = new File("coalDisasters.csv");
		dataset.BuildDataSet(file);
		
		System.out.println();
		System.out.println("Test on reading Attribute names form the data set by method getAttributeName(Int)");
		System.out.println("result = " + dataset.GetAttributeName(0));
		System.out.println("expected resut = Record no");
		System.out.println();
		System.out.println("Test on geting the correct number of entrys form the data set by method GetNoOfEntrys()");
		System.out.println("result = " + dataset.GetNoOfEntrys());
		System.out.println("expected result = 191");
		System.out.println();
		System.out.println("Test on getting the number of atributes form the data set by method GetNoOfAttributes()");
		System.out.println("result = " + dataset.GetNoOfAttributes());
		System.out.println("expected result = 6");
		System.out.println();
		System.out.println("Test for getting the attribute array form the data set by method GetAttributes()[int]");
		System.out.println("result = " + dataset.GetAttributes()[0] + ", " 
									   + dataset.GetAttributes()[1] + ", " 
									   + dataset.GetAttributes()[2] + ", " 
								       + dataset.GetAttributes()[3] + ", " 
								       + dataset.GetAttributes()[4] + ", " 
								       + dataset.GetAttributes()[5]);
		System.out.println("expected result = Record no, Month, Year, DayOfYear, Interval, Deaths");
	}
}
