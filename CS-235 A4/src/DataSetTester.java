import java.io.File;


public class DataSetTester {
	public static void main(String args[]){
		DataSet dataset = new DataSet();
		File file = new File("coalDisasters.csv");
		dataset.buildDataSet(file);
		
		System.out.println("result = " + dataset.GetAttributeName(0));
		System.out.println("expected resut = Record no");
		System.out.println("result = " + dataset.GetNoOfEntrys());
		System.out.println("expected result = 191");
		System.out.println("result = " + dataset.GetNoOfAttributes());
		System.out.println("expected result = 6");
		System.out.println("result = " + dataset.GetAttributes()[0] + ", " 
									   + dataset.GetAttributes()[1] + ", " 
									   + dataset.GetAttributes()[2] + ", " 
								       + dataset.GetAttributes()[3] + ", " 
								       + dataset.GetAttributes()[4] + ", " 
								       + dataset.GetAttributes()[5]);
		System.out.println("expected result = Record no, Month, Year, DayOfYear, Interval, Deaths");
	}
}
