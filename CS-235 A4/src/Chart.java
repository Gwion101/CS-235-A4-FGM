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

public class Chart extends JPanel {
	
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
    
    public JFreeChart getChart() {
        return(m_chart);
    }
    
    public void setChart(JFreeChart chart) {
        m_chart = chart;
    }
	
    public void SetWindowVisible() {
        m_window.setVisible(true);
    }
    
    public Chart (DataSet m_data) {
        
        super(new GridLayout(1,0));
        m_dataSet = m_data;
        m_chartType = -1;
        m_chart = null;
        m_window = new JFrame("Chart Setup");
        m_window.setSize(650, 230);
        m_window.setLayout(new FlowLayout());
        
        JPanel barPanel = new JPanel(new GridLayout(B_ROW,B_COL,H_GAP,V_GAP));
        m_window.add(barPanel);
        JPanel piePanel = new JPanel(new GridLayout(B_ROW,B_COL,H_GAP,V_GAP));
        m_window.add(piePanel);
        
        Border barPanelBorder = BorderFactory.createTitledBorder("Bar Chart Settings");
        barPanel.setBorder(barPanelBorder);

        Border piePanelBorder = BorderFactory.createTitledBorder("Pie Chart Settings");
        piePanel.setBorder(piePanelBorder);
        
        String[] m_modelString = new String[m_data.GetNoOfAttributes()];
        
        for(int i=0; i<m_data.GetNoOfAttributes(); i++){
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
        m_yAxisInput= new JTextField("Y-Axis Name");
        barPanel.add(m_yAxisInput);

        m_generateBarButton = new JButton("Generate Bar Chart");
        m_generatePieButton = new JButton("Generate Pie Chart");
        barPanel.add(m_generateBarButton);	
        piePanel.add(m_generatePieButton);

        GUIEventHandler m_eventHandeler = new GUIEventHandler();
        m_generateBarButton.addActionListener(m_eventHandeler);
        m_generatePieButton.addActionListener(m_eventHandeler);
    }
	
    private class GUIEventHandler implements ActionListener {
		
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource()== m_generateBarButton){                         
                int m_A1 = m_attributeDropdownBar1.getSelectedIndex();
                int m_A2 = m_attributeDropdownBar2.getSelectedIndex();
                String m_header = m_barHeader.getText();
                String m_xTitle = m_xAxisInput.getText();
                String m_yTitle = m_yAxisInput.getText();
                BarChartBuilder m_barChartBuilder=new BarChartBuilder();
                JFreeChart chart = m_barChartBuilder.makeChart(m_dataSet,m_A1,m_A2,m_header,m_xTitle,m_yTitle);
                XYPlot plot = chart.getXYPlot();
                CustomBarRenderer renderer = DataVisualizerGUI.m_cm.getBarRenderer();
                plot.setRenderer(renderer);
                plot.setFixedLegendItems(DataVisualizerGUI.m_cm.getLegend(chart, renderer));
                m_frame = new ChartPanel(chart);
                setChartType(0);
                setChart(chart);
                removeAll();
                add(m_frame);
                repaint();
                revalidate();
                DataVisualizer.dataVisualizer.activateColour();
                DataVisualizer.dataVisualizer.selectChartTab();
                m_window.setVisible(false);
                                
            } else if (event.getSource()== m_generatePieButton){           			
                int m_A1 = m_attributeDropdownPie1.getSelectedIndex();
                int m_A2 = m_attributeDropdownPie2.getSelectedIndex();
                String m_header = m_pieHeader.getText();
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
                DataVisualizer.dataVisualizer.selectChartTab();
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
}