import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class chartSetup extends JFrame {
	
	private JComboBox<?> m_attributeDropdown1;
	private String[] m_modelString;
	private JLabel m_label;
	private JComboBox<?> m_attributeDropdown2;
	private JTextField m_headerInput;
	private JButton m_generateButton;
	private DataSet m_dataSet;
	private JFreeChart m_chart;
	private ChartPanel m_frame;
	private Container m_container;
	
	public chartSetup(DataSet m_data) throws IOException {
		m_dataSet = m_data;
		m_container = getContentPane();
		m_container.setLayout(new FlowLayout());
		m_label = new JLabel("Select First Attribute:");
		m_container.add(m_label);
		m_modelString = new String[m_data.GetNoOfAttributes()];
		for(int i=0; i<m_data.GetNoOfAttributes(); i++){
			m_modelString[i] = m_data.GetAttributeName(i);
		}
		m_attributeDropdown1= new JComboBox<Object>(m_modelString);
		m_attributeDropdown1.setMaximumRowCount(m_data.GetNoOfAttributes());
		m_container.add(m_attributeDropdown1);
		
		m_label = new JLabel("Select Second Attribute:");
		m_container.add(m_label);
		m_attributeDropdown2= new JComboBox<Object>(m_modelString);
		m_attributeDropdown2.setMaximumRowCount(m_data.GetNoOfAttributes());
		m_container.add(m_attributeDropdown2);
		
		m_headerInput= new JTextField("Title");
		m_container.add(m_headerInput);
		
		m_generateButton= new JButton("Genereate");
		m_container.add(m_generateButton);		
		GUIEventHandler m_eventHandeler = new GUIEventHandler();
		
		m_generateButton.addActionListener(m_eventHandeler);
		
		setVisible(true);
	}
	private class GUIEventHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			if (event.getSource()==m_generateButton){
				makeChart();
			}
		}
	}
	
	private void makeChart() {	
		XYSeries m_dataSeries = new XYSeries("Histogram");
		for (int i=0; i<m_dataSet.GetNoOfEntrys();i++){
			
			m_dataSeries.add((double) m_dataSet.GetColumnData(m_attributeDropdown1.getSelectedIndex())[i],(double) m_dataSet.GetColumnData(m_attributeDropdown2.getSelectedIndex())[i]);
		}
		XYSeriesCollection m_chartDataSet = new XYSeriesCollection();
		m_chartDataSet.addSeries(m_dataSeries);
		m_chart = ChartFactory.createXYBarChart( 
				m_headerInput.getText(),
				"",//x axis
				false,
				"",//y axis
				m_chartDataSet,
				PlotOrientation.VERTICAL, false, false, false);
		m_frame = new ChartPanel(m_chart);
		m_container.add(m_frame);
		m_container.revalidate();
		m_container.repaint();
	}
}
