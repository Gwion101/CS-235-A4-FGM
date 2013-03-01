import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class BarChartBuilder {

    public JFreeChart makeChart(DataSet m_dataSet,int m_A1,int m_A2,String m_header,String m_xTitle,String m_yTitle) {	
        
        Double nextValue1;
        Double nextValue2;
        
        XYSeries dataSeries = new XYSeries("");
        for (int i=0; i<m_dataSet.GetNoOfEntrys();i++) {	
            try {
                nextValue1=(double) ((Integer) m_dataSet.GetColumnData(m_A1)[i]).intValue();
            } catch (NumberFormatException nfe){
                nextValue1=(double) ((Float) m_dataSet.GetColumnData(m_A1)[i]).floatValue();
            }
            
            try {
                    nextValue2 =(double) ((Integer) m_dataSet.GetColumnData(m_A2)[i]).intValue();
            } catch (NumberFormatException nfe){
                    nextValue2=(double) ((Float) m_dataSet.GetColumnData(m_A2)[i]).floatValue();
            }	
						
            dataSeries.add( nextValue1, nextValue2);	
        }
        
        XYSeriesCollection chartDataSet = new XYSeriesCollection();
        chartDataSet.addSeries(dataSeries);
        JFreeChart chart = ChartFactory.createXYBarChart( 
                        m_header,
                        m_xTitle,
                        false,
                        m_yTitle,
                        chartDataSet,
                        PlotOrientation.VERTICAL, false, false, false);
        return chart;
    }
}
