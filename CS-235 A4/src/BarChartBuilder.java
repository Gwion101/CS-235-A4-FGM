import java.awt.Component;
import java.awt.Frame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class BarChartBuilder {
	private Integer m_nextValue1;
	private Integer m_nextValue2;
	private JFreeChart m_chart;
	
	public JFreeChart makeChart(DataSet m_dataSet,int m_A1,int m_A2,String m_header,String m_xTitle,String m_yTitle) {	
		XYSeries m_dataSeries = new XYSeries("Histogram");
		for (int i=0; i<m_dataSet.GetNoOfEntrys();i++){	
			m_nextValue1=(Integer) m_dataSet.GetColumnData(m_A1)[i];
			m_nextValue2 =(Integer) m_dataSet.GetColumnData(m_A2)[i];
			m_dataSeries.add((double) m_nextValue1.intValue(),(double) m_nextValue2.intValue());
		}
		XYSeriesCollection m_chartDataSet = new XYSeriesCollection();
		m_chartDataSet.addSeries(m_dataSeries);
		m_chart = ChartFactory.createXYBarChart( 
				m_header,
				m_xTitle,//x axis
				false,
				m_yTitle,//y axis
				m_chartDataSet,
				PlotOrientation.VERTICAL, false, false, false);
		return m_chart;
	}
}
