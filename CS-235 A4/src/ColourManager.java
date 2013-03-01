import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;

public class ColourManager extends JFrame {
	
	private final int m_mapCount = 2;
	
	private ColourMap[] m_colourMaps = new ColourMap[m_mapCount];
	private int m_activeMap;
	private CustomBarRenderer m_barRenderer;
	private CustomPieRenderer m_pieRenderer;
	private JComboBox m_mapList;
	private JPanel m_previewPanel, m_previewContainer;
	private Container m_container;
	private JButton m_saveButton, m_closeButton;
			
	public ColourManager() {
		initMaps();
		initGUI();
		setSize(new Dimension(350, 250));
		setTitle("Colour Options");
	}
	
	private class cmActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
            if (e.getSource()==m_mapList) {
            	m_previewContainer.removeAll();
            	m_previewPanel = drawSwatches(m_mapList.getSelectedIndex());
            	m_previewContainer.add(m_previewPanel);
            	m_previewPanel.repaint();
            	m_previewPanel.revalidate();
            }
            if (e.getSource()==m_closeButton) {
            	closeFrame();
            }
            if (e.getSource()==m_saveButton) {
            	saveAndClose();
            }
		}
	}
	
	public void initGUI() {
		cmActionListener listener = new cmActionListener();
		
		//m_container.
		m_container = getContentPane();
		m_container.setLayout(new FlowLayout());
		
		JPanel optionsPanel = new JPanel(new GridLayout(4,2));
		m_container.add(optionsPanel);
		
		JLabel dropboxText = new JLabel("Select colour scheme: ");
		optionsPanel.add(dropboxText);
		
		String[] colourMaps = new String[m_mapCount];
		for (int i=0; i<m_mapCount; i++) {
			colourMaps[i] = getMap(i).getMapName();
		}
		
		m_mapList = new JComboBox(colourMaps);
		m_mapList.setPreferredSize(new Dimension(150, 10));
		m_mapList.addActionListener(listener);
		optionsPanel.add(m_mapList);
		
		JLabel blankLabel1 = new JLabel("");
		optionsPanel.add(blankLabel1);
		
		m_previewContainer = new JPanel();
		optionsPanel.add(m_previewContainer);
		
		m_previewPanel = drawSwatches(m_mapList.getSelectedIndex());
		m_previewContainer.add(m_previewPanel);
		
		JPanel spacerPanel1 = new JPanel();
		spacerPanel1.setSize(new Dimension(10, 400));
		optionsPanel.add(spacerPanel1);
		
		JPanel spacerPanel2 = new JPanel();
		spacerPanel2.setSize(new Dimension(10, 400));
		optionsPanel.add(spacerPanel2);
		
		JLabel blankLabel2 = new JLabel("");
		optionsPanel.add(blankLabel2);
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		optionsPanel.add(buttonPanel);
		
		m_saveButton = new JButton("OK");
		m_saveButton.addActionListener(listener);
		buttonPanel.add(m_saveButton);
		
		m_closeButton = new JButton("Cancel");
		m_closeButton.addActionListener(listener);
		buttonPanel.add(m_closeButton);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public JPanel drawSwatches(int mapIndex) {
		JPanel swatchPanel = new JPanel();
		Border swatchPanelBorder = BorderFactory.createTitledBorder("Preview");
		swatchPanel.setBorder(swatchPanelBorder);
		
		Dimension swatchSize = new Dimension(20, 20);
		int borderWidth = 1;
		Color borderColour = Color.black;
		
		Border swatchOutline = BorderFactory.createMatteBorder(borderWidth, borderWidth, borderWidth, borderWidth, borderColour);
		
		for (int colourIndex=0; colourIndex<5; colourIndex++) {
			switch (colourIndex) {
			    case 0:	JPanel colour1 = new JPanel();
						colour1.setSize(swatchSize);
						colour1.setBorder(swatchOutline);
						colour1.setBackground(getMap(mapIndex).getColour(colourIndex).getColour());
						swatchPanel.add(colour1);
			    		break;
			    case 1:	JPanel colour2 = new JPanel();
						colour2.setSize(swatchSize);
						colour2.setBorder(swatchOutline);
						colour2.setBackground(getMap(mapIndex).getColour(colourIndex).getColour());
						swatchPanel.add(colour2);
						break;
			    case 2:	JPanel colour3 = new JPanel();
						colour3.setSize(swatchSize);
						colour3.setBorder(swatchOutline);
						colour3.setBackground(getMap(mapIndex).getColour(colourIndex).getColour());
						swatchPanel.add(colour3);
						break;
			    case 3:	JPanel colour4 = new JPanel();
						colour4.setSize(swatchSize);
						colour4.setBorder(swatchOutline);
						colour4.setBackground(getMap(mapIndex).getColour(colourIndex).getColour());
						swatchPanel.add(colour4);
						break;
			    case 4: JPanel colour5 = new JPanel();
						colour5.setSize(swatchSize);
						colour5.setBorder(swatchOutline);
						colour5.setBackground(getMap(mapIndex).getColour(colourIndex).getColour());
						swatchPanel.add(colour5);
						break;
			}
		}
		return(swatchPanel);
	}
	
	public void initMaps() {
		Colour[] colours = new Colour[5];
		for (int i=0; i<5; i++) {
			switch (i) {
			    case 0:	Colour black = new Colour("#000000");
			    		colours[i] = black;
			    		break;
			    case 1: Colour darkgrey = new Colour("#222222");
			    		colours[i] = darkgrey;
			            break;
			    case 2: Colour grey = new Colour("#555555");
			    		colours[i] = grey;
			    		break;
			    case 3: Colour lightgrey = new Colour("#BBBBBB");
			    		colours[i] = lightgrey;
			    		break;
			    case 4: Colour white = new Colour("#EEEEEE");
			    		colours[i] = white;        
			    		break;
			}	
		}
		ColourMap greyscale = new ColourMap(colours, "Greyscale");
		setMap(0, greyscale);
		for (int i=0; i<5; i++) {
			switch (i) {
			    case 0:	Colour colour1 = new Colour("#556270");
			    		colours[i] = colour1;
			    		break;
			    case 1: Colour colour2 = new Colour("#4ECDC4");
			    		colours[i] = colour2;
			            break;
			    case 2: Colour colour3 = new Colour("#C7F464");
			    		colours[i] = colour3;
			    		break;
			    case 3: Colour colour4 = new Colour("#FF6B6B");
			    		colours[i] = colour4;
			    		break;
			    case 4: Colour colour5 = new Colour("#C44D58");
			    		colours[i] = colour5;        
			    		break;
			}
		}
		ColourMap nicecolours = new ColourMap(colours, "Nice Colours");
		setMap(1, nicecolours);
		setActiveMap(1);
		setRenderers();
	}
	
	public LegendItemCollection getLegend(JFreeChart chart, CustomBarRenderer renderer) {
		XYPlot plot = chart.getXYPlot();
		XYDataset dataset = plot.getDataset();
		LegendItemCollection legend = new LegendItemCollection();    
		for (int i = 0; i < chart.getXYPlot().getSeriesCount(); ++i) {
		    LegendItem li = new LegendItem((String) dataset.getSeriesKey(i), renderer.getMap().getColour(i).getColour());
		    legend.add(li);
		}
		return(legend);
	}
	
	public void saveAndClose() {
		setActiveMap(m_mapList.getSelectedIndex());
		DataVisualizer.dataVisualizer.redrawChartColour();
		setVisible(false);
	}
	
	public void closeFrame() {
		setVisible(false);
	}
	
	public ColourMap getActiveMap() {
		return(m_colourMaps[m_activeMap]);
	}
	
	public void setActiveMap(int mapIndex) {
		m_activeMap = mapIndex;
		setRenderers();
	}

	public ColourMap getMap(int mapIndex) {
		return(m_colourMaps[mapIndex]);
	}
	
	public void setMap(int mapIndex, ColourMap map) {
		m_colourMaps[mapIndex] = map;
		setRenderers();
	}
	
	public void setRenderers() {
		m_barRenderer = new CustomBarRenderer(getActiveMap());
		m_pieRenderer = new CustomPieRenderer(getActiveMap());
	}
	
	public CustomBarRenderer getBarRenderer() {
		return(m_barRenderer);
	}
	
	public CustomPieRenderer getPieRenderer() {
		return(m_pieRenderer);
	}
}
