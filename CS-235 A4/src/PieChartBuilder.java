/**
 * \This class creates the Pie Chart.
 * 
 * @author 	-Lewis Radcliffe
 * \file 	-PieChartBuilder.java
 * \data	-28 Feb '13
 * \version     -1.0
 */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PieChartBuilder {
  
    public JFreeChart MakeChart( DataSet m_dataSet,int m_A1,int m_A2,String m_header ) {	
        
        DefaultPieDataset  chartDataSet = new DefaultPieDataset();

        for (int i=0; i<m_dataSet.GetNoOfEntrys();i++) {	
            
            Integer nextValue1 =( Integer ) m_dataSet.GetColumnData( m_A1 )[i];
            Integer nextValue2 =( Integer ) m_dataSet.GetColumnData( m_A2 )[i];
            chartDataSet.setValue( nextValue1, nextValue2 );
        }

        JFreeChart chart = ChartFactory.createPieChart3D( 
                        m_header,
                        chartDataSet,
                        true, //include legend
                        true,
                        false );
        return chart;
    }
}
