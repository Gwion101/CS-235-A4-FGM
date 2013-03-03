import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.PieDataset;

/**
 * @file Chart.java
 * @author Lewis Radcliffe & Gwion Rhys Davies
 * @date 1.03.13
 * @see BigJava 4th edition, p737 and
 *      http://docs.oracle.com/javase/tutorial/uiswing
 * 
 *      \brief Chart class that displays the visualisation options
 */

public class Chart extends JPanel {

	/**
	 * @return the chart type
	 */
	public int GetChartType() {
		return (m_chartType);
	}

	/**
	 * @param type
	 * @return TRUE or FALSE depending on chart type
	 */
	public boolean SetChartType(int type) {

		if ((type >= 0) && (type < 2)) {
			m_chartType = type;
			return (true);
		} else {
			return (false);
		}
	}

	/**
	 * @return the actual chart
	 */
	public JFreeChart GetChart() {
		return (m_chart);
	}

	/**
	 * Sets the chart to m_chart
	 * 
	 * @param chart
	 */
	public void SetChart(JFreeChart chart) {
		m_chart = chart;
	}

	/**
	 * Sets the window to visible
	 */
	public void SetWindowVisible() {
		m_window.setVisible(true);
	}

	/**
	 * Method that disposes the window
	 */
	public void DisposeWindow() {
		m_window.dispose();
	}

	/**
	 * Creates the UI which holds all the visualisation options
	 * 
	 * @param m_data
	 */
	public Chart(DataSet m_data) {

		super(new GridLayout(1, 0));
		m_dataSet = m_data;
		m_chartType = -1;
		m_chart = null;
		m_window = new JFrame("Chart Setup");
		m_window.setSize(WIDTH, HEIGHT);
		m_window.setLayout(new FlowLayout());

		JPanel barPanel = new JPanel(new GridLayout(B_ROW, B_COL, H_GAP, V_GAP));
		m_window.add(barPanel);
		JPanel piePanel = new JPanel(new GridLayout(B_ROW, B_COL, H_GAP, V_GAP));
		m_window.add(piePanel);

		Border barPanelBorder = BorderFactory
				.createTitledBorder("Bar Chart Settings");
		barPanel.setBorder(barPanelBorder);

		Border piePanelBorder = BorderFactory
				.createTitledBorder("Pie Chart Settings");
		piePanel.setBorder(piePanelBorder);

		String[] m_modelString = new String[m_data.GetNoOfAttributes()];

		for (int i = 0; i < m_data.GetNoOfAttributes(); i++) {
			m_modelString[i] = m_data.GetAttributeName(i);
		}

		JLabel label = new JLabel("Select X-Axis:");
		barPanel.add(label);
		m_attributeDropdownBar1 = new JComboBox(m_modelString);
		m_attributeDropdownBar1.setMaximumRowCount(m_data.GetNoOfAttributes());
		barPanel.add(m_attributeDropdownBar1);
		label = new JLabel("Select Y-Axis:");
		barPanel.add(label);
		m_attributeDropdownBar2 = new JComboBox(m_modelString);
		m_attributeDropdownBar2.setMaximumRowCount(m_data.GetNoOfAttributes());
		barPanel.add(m_attributeDropdownBar2);
		m_barHeader = new JTextField("Title");
		barPanel.add(m_barHeader);

		label = new JLabel("Select First Attribute:");
		piePanel.add(label);
		m_attributeDropdownPie1 = new JComboBox(m_modelString);
		m_attributeDropdownPie1.setMaximumRowCount(m_data.GetNoOfAttributes());
		piePanel.add(m_attributeDropdownPie1);
		label = new JLabel("Select Second Attribute:");
		piePanel.add(label);
		m_attributeDropdownPie2 = new JComboBox(m_modelString);
		m_attributeDropdownPie2.setMaximumRowCount(m_data.GetNoOfAttributes());
		piePanel.add(m_attributeDropdownPie2);
		m_pieHeader = new JTextField("Title");
		piePanel.add(m_pieHeader);

		m_xAxisInput = new JTextField("X-Axis Name");
		barPanel.add(m_xAxisInput);
		m_yAxisInput = new JTextField("Y-Axis Name");
		barPanel.add(m_yAxisInput);

		m_generateBarButton = new JButton("Generate Bar Chart");
		m_generatePieButton = new JButton("Generate Pie Chart");
		barPanel.add(m_generateBarButton);
		piePanel.add(m_generatePieButton);

		GUIEventHandler m_eventHandeler = new GUIEventHandler();
		m_generateBarButton.addActionListener(m_eventHandeler);
		m_generatePieButton.addActionListener(m_eventHandeler);
	}

	/**
	 * Inner class acting as an action listener for the GUI components.
	 * 
	 * @author Lewis Radcliffe & Gwion Rhys Davies
	 */
	private class GUIEventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == m_generateBarButton) {
				int A1 = m_attributeDropdownBar1.getSelectedIndex();
				int A2 = m_attributeDropdownBar2.getSelectedIndex();
				String header = m_barHeader.getText();
				String xTitle = m_xAxisInput.getText();
				String yTitle = m_yAxisInput.getText();
				BarChart barChart = new BarChart();
				JFreeChart chart = barChart.MakeChart(m_dataSet, A1, A2,
						header, xTitle, yTitle);
				XYPlot plot = chart.getXYPlot();
				CustomBarRenderer renderer = DataVisualizerGUI.m_cm
						.GetBarRenderer();
				plot.setRenderer(renderer);
				plot.setFixedLegendItems(DataVisualizerGUI.m_cm.GetLegend(
						chart, renderer));
				m_frame = new ChartPanel(chart);
				SetChartType(0);
				SetChart(chart);
				removeAll();
				add(m_frame);
				repaint();
				revalidate();
				GroupFGMApplication.dataVisualizer.ActivateColour();
				GroupFGMApplication.dataVisualizer.SelectChartTab();
				m_window.setVisible(false);

			} else if (event.getSource() == m_generatePieButton) {
				int A1 = m_attributeDropdownPie1.getSelectedIndex();
				int A2 = m_attributeDropdownPie2.getSelectedIndex();
				String header = m_pieHeader.getText();
				PieChart pieChart = new PieChart();
				JFreeChart chart = pieChart
						.MakeChart(m_dataSet, A1, A2, header);
				PiePlot plot = (PiePlot) chart.getPlot();
				PieDataset dataset = plot.getDataset();
				CustomPieRenderer renderer = new CustomPieRenderer(
						DataVisualizerGUI.m_cm.GetActiveMap());
				renderer.SetColor(plot, dataset);
				SetChartType(1);
				SetChart(chart);
				m_frame = new ChartPanel(chart);
				removeAll();
				add(m_frame);
				repaint();
				revalidate();
				GroupFGMApplication.dataVisualizer.ActivateColour();
				GroupFGMApplication.dataVisualizer.SelectChartTab();
				m_window.setVisible(false);
			}
		}
	}

	private JComboBox m_attributeDropdownBar1;
	private JComboBox m_attributeDropdownBar2;
	private JComboBox m_attributeDropdownPie1;
	private JComboBox m_attributeDropdownPie2;
	private JTextField m_barHeader;
	private JTextField m_pieHeader;
	private JButton m_generatePieButton;
	private JButton m_generateBarButton;
	private DataSet m_dataSet;
	private JTextField m_yAxisInput;
	private JTextField m_xAxisInput;
	private ChartPanel m_frame;
	private JFrame m_window;
	private int m_chartType;
	private JFreeChart m_chart;
	final private int B_ROW = 4;
	final private int B_COL = 2;
	final private int H_GAP = 5;
	final private int V_GAP = 5;
	final private int WIDTH = 650;
	final private int HEIGHT = 230;

} /** end Chart class */