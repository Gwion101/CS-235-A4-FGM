import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.PieDataset;

public class Chart extends JPanel {
    
	private int m_chartType;
	private JFreeChart m_chart;
	
	public int getChartType() {
		return(m_chartType);
	}
	
	public boolean setChartType(int type) {
		if ((type>=0) && (type<2)) {
			m_chartType = type;
			return(true);
		} else {
			return(false);
		}
	}
	
	public void setChart(JFreeChart chart) {
		m_chart = chart;
	}
	
	public JFreeChart getChart() {
		return(m_chart);
	}
	
    public void SetWindowVisible() {
        m_createWindow.setVisible(true);
    }
    
    public Chart (DataSet m_data) {
        super(new GridLayout(1,0));
        m_dataSet = m_data;
        m_chartType = -1;
        m_chart = null;
        m_createWindow = new JFrame("Chart Setup");
        m_createWindow.setSize(400, 400);
        m_createWindow.setLayout(new FlowLayout());
        m_label = new JLabel("Select First Attribute:");
        m_createWindow.add(m_label);
        m_modelString = new String[m_data.GetNoOfAttributes()];
        for(int i=0; i<m_data.GetNoOfAttributes(); i++){
                m_modelString[i] = m_data.GetAttributeName(i);
        }
        m_attributeDropdown1= new JComboBox(m_modelString);
        m_attributeDropdown1.setMaximumRowCount(m_data.GetNoOfAttributes());
        m_createWindow.add(m_attributeDropdown1);

        m_label = new JLabel("Select Second Attribute:");
        m_createWindow.add(m_label);
        m_attributeDropdown2= new JComboBox(m_modelString);
        m_attributeDropdown2.setMaximumRowCount(m_data.GetNoOfAttributes());
        m_createWindow.add(m_attributeDropdown2);

        m_headerInput= new JTextField("Title");
        m_createWindow.add(m_headerInput);
        m_xAxisInput= new JTextField("X-Axis");
        m_createWindow.add(m_xAxisInput);
        m_yAxisInput= new JTextField("Y-Axis");
        m_createWindow.add(m_yAxisInput);

        m_generateButton= new JButton("Generate");
        m_generateButton1= new JButton("PIE YUMMY YAY PIE");
        m_createWindow.add(m_generateButton);	
        m_createWindow.add(m_generateButton1);
        GUIEventHandler m_eventHandeler = new GUIEventHandler();

        m_generateButton.addActionListener(m_eventHandeler);
        m_generateButton1.addActionListener(m_eventHandeler);
    }
	
    private class GUIEventHandler implements ActionListener {
		
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource()==m_generateButton){                         
                int m_A1 = m_attributeDropdown1.getSelectedIndex();
                int m_A2 = m_attributeDropdown2.getSelectedIndex();
                String m_header = m_headerInput.getText();
                String m_xTitle = m_xAxisInput.getText();
                String m_yTitle = m_yAxisInput.getText();
                BarChartBuilder m_barChartBuilder=new BarChartBuilder();
                JFreeChart chart = m_barChartBuilder.makeChart(m_dataSet,m_A1,m_A2,m_header,m_xTitle,m_yTitle);
                XYPlot plot = chart.getXYPlot();
                plot.setRenderer(DataVisualizerGUI.m_cm.getBarRenderer());
                m_frame = new ChartPanel(chart);
                setChartType(0);
                setChart(chart);
                removeAll();
                add(m_frame);
                repaint();
                revalidate();
                DataVisualizer.dataVisualizer.activateColour();
                                
            } else if (event.getSource()==m_generateButton1){           			
                int m_A1 = m_attributeDropdown1.getSelectedIndex();
                int m_A2 = m_attributeDropdown2.getSelectedIndex();
                String m_header = m_headerInput.getText();
                PieChartBuilder m_pieChartBuilder=new PieChartBuilder();
                JFreeChart chart = m_pieChartBuilder.MakeChart(m_dataSet,m_A1,m_A2,m_header);
                PiePlot plot = (PiePlot) chart.getPlot();
                PieDataset dataset = plot.getDataset();
                CustomPieRenderer renderer = new CustomPieRenderer(DataVisualizerGUI.m_cm.getActiveMap());
                renderer.setColor(plot, dataset);
                setChartType(1);
                setChart(chart);
                m_frame = new ChartPanel(chart);
                removeAll();
                add(m_frame);
                repaint();
                revalidate();
                DataVisualizer.dataVisualizer.activateColour();
            }
        }
    }
    
    private JLabel m_label;
    private String[] m_modelString;
    private JComboBox m_attributeDropdown1;
    private JComboBox m_attributeDropdown2;
    private JTextField m_headerInput;
    private JButton m_generateButton;
    private JButton m_generateButton1;
    private DataSet m_dataSet;
    private JTextField m_yAxisInput;
    private JTextField m_xAxisInput;
    private ChartPanel m_frame;
    private JFrame m_createWindow;
}