import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 * @author Lewis Radcliffe
 * @file PieChart.java
 * @date 1.03.13
 * @version 1.2
 * @see http://www.jfree.org/jfreechart/api/javadoc/index.html
 * 
 *      \brief PieChart class creates the Pie Chart.
 */

public class PieChart {

	/**
	 * Method that creates the Pie Chart
	 * 
	 * @param m_dataSet
	 * @param m_A1
	 * @param m_A2
	 * @param m_header
	 * @return the chart for display
	 */
	public JFreeChart MakeChart(DataSet m_dataSet, int m_A1, int m_A2,
			String m_header) {

		DefaultPieDataset chartDataSet = new DefaultPieDataset();

		for (int i = 0; i < m_dataSet.GetNoOfEntrys(); i++) {
			Double nextValue1;

			try {
				nextValue1 = (double) ((Integer) m_dataSet.GetColumnData(m_A1)[i])
						.intValue();
			} catch (ClassCastException cce) {
				nextValue1 = (double) ((Float) m_dataSet.GetColumnData(m_A1)[i])
						.floatValue();
			}

			Double nextValue2;

			try {
				nextValue2 = (double) ((Integer) m_dataSet.GetColumnData(m_A2)[i])
						.intValue();
			} catch (ClassCastException cce) {
				nextValue2 = (double) ((Float) m_dataSet.GetColumnData(m_A2)[i])
						.floatValue();
			}
			chartDataSet.setValue(nextValue1, nextValue2);
		}

		JFreeChart chart = ChartFactory.createPieChart(m_header, chartDataSet,
				true, // include legend
				true, false);
		return chart;
	}
} /* end PieChart class */