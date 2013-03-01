import java.awt.Component;
import java.awt.Frame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.SeriesException;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class BarChartBuilder {
	private Double m_nextValue1;
	private Double m_nextValue2;
	private JFreeChart m_chart;
	
	public JFreeChart makeChart(DataSet m_dataSet,int m_A1,int m_A2,String m_header,String m_xTitle,String m_yTitle) {	
		XYSeries m_dataSeries = new XYSeries("Histogram");
		for (int i=0; i<m_dataSet.GetNoOfEntrys();i++){	
			try{
				m_nextValue1=(double) ((Integer) m_dataSet.GetColumnData(m_A1)[i]).intValue();
			} catch (NumberFormatException nfe){
				m_nextValue1=(double) ((Float) m_dataSet.GetColumnData(m_A1)[i]).floatValue();
			}
			try{
				m_nextValue2 =(double) ((Integer) m_dataSet.GetColumnData(m_A2)[i]).intValue();
			} catch (NumberFormatException nfe){
				m_nextValue2=(double) ((Float) m_dataSet.GetColumnData(m_A2)[i]).floatValue();
			}	
			//if (m_dataSeries.
			
			m_dataSeries.update( m_nextValue1, m_nextValue2);
			
		}
		XYSeriesCollection m_chartDataSet = new XYSeriesCollection();
		m_chartDataSet.addSeries(m_dataSeries);
		m_chart = ChartFactory.createXYBarChart( 
				m_header,
				m_xTitle,
				false,
				m_yTitle,
				m_chartDataSet,
				PlotOrientation.VERTICAL, false, false, false);
		return m_chart;
	}
}
