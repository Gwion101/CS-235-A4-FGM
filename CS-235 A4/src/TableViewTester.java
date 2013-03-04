import java.awt.Component;
import java.io.File;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class TableViewTester extends JFrame {

	private static  DataSet m_testData;
	
	public static void CreateFrame(Component component, String frameTitle) {
		JFrame testWindow = new JFrame();
		testWindow.setTitle(frameTitle); 
		testWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testWindow.setSize(600, 600);
		testWindow.add(component);
		testWindow.setVisible(true);
	}
	
	private static Boolean TableViewTester(DataSet m_testData){

		TableView table = new TableView(m_testData);
		CreateFrame(table, "TableViewTester");
		
		return true;
	}
	
	private static Boolean ChartTester(DataSet m_testData) {
		Chart chart = new Chart(m_testData);
		CreateFrame(chart, "ChartTester");

		return true;
	}

	private static Boolean BarChartTester(DataSet m_testData) {
        int A1 = 0; //Record number
        int A2 = 4; //Interval
        String header = "Bar Chart Test";
        String xTitle = "x-axis tex display Test";
        String yTitle = "y-axis tex display Test";
        BarChart barChart = new BarChart();
        JFreeChart chart = barChart.MakeChart(m_testData,A1,A2,header,xTitle,yTitle);
        ChartPanel panel = new ChartPanel(chart);        
		CreateFrame(panel, "BarChartTester");

		return true;
	}	
	
	private static Boolean PieChartTester(DataSet m_testData) {
        int A1 = 1; //Record number
        int A2 = 2; //Month
        String header = "Pie Chart Test";
        PieChart pieChart = new PieChart();
        JFreeChart chart = pieChart.MakeChart(m_testData,A1,A2,header);
        ChartPanel panel = new ChartPanel(chart);
		CreateFrame(panel, "PieChartTester");
		
		return true;
	}	
	
	public static void main(String[] args) {
		File file = new File("coalDisasters.csv");
		m_testData = new DataSet();
		m_testData.BuildDataSet(file);
		System.out.println();
		System.out.println("Test displaying table: " + TableViewTester(m_testData));
		System.out.println("Test displaying chart: " + ChartTester(m_testData));
		System.out.println("Test displaying bar chart: " + BarChartTester(m_testData));
		System.out.println("Test displaying pie chart: " + PieChartTester(m_testData));
	}
}