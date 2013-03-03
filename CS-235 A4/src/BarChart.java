import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * @author Lewis Radcliffe & Laurence Tsang
 * @file BarChart.java
 * @date 28.02.13
 * @version 1.7
 * @see http://www.jfree.org/jfreechart/api/javadoc/index.html
 * 
 *      \brief BarChart class creates the Bar Chart.
 */

public class BarChart {

	/**
	 * Method that creates the bar chart
	 * 
	 * @param m_dataSet
	 * @param m_A1
	 * @param m_A2
	 * @param m_header
	 * @param m_xTitle
	 * @param m_yTitle
	 * @return the chart for display
	 */
	public JFreeChart MakeChart(DataSet m_dataSet, int m_A1, int m_A2,
			String m_header, String m_xTitle, String m_yTitle) {

		Double nextValue1;
		Double nextValue2;

		XYSeries dataSeries = new XYSeries(m_dataSet.GetAttributeName(m_A2));
		for (int i = 0; i < m_dataSet.GetNoOfEntrys(); i++) {
			try {
				nextValue1 = (double) ((Integer) m_dataSet.GetColumnData(m_A1)[i])
						.intValue();
			} catch (ClassCastException cce) {
				nextValue1 = (double) ((Float) m_dataSet.GetColumnData(m_A1)[i])
						.floatValue();
			}

			try {
				nextValue2 = (double) ((Integer) m_dataSet.GetColumnData(m_A2)[i])
						.intValue();
			} catch (ClassCastException cce) {
				nextValue2 = (double) ((Float) m_dataSet.GetColumnData(m_A2)[i])
						.floatValue();
			}

			dataSeries.add(nextValue1, nextValue2);
		}

		XYSeriesCollection chartDataSet = new XYSeriesCollection();
		chartDataSet.addSeries(dataSeries);
		JFreeChart chart = ChartFactory.createXYBarChart(m_header, m_xTitle,
				false, m_yTitle, chartDataSet, PlotOrientation.VERTICAL, true,
				false, false);
		return chart;
	}
} /** end BarChart class */
