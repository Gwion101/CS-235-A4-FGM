import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

/**
 * @file ColourManager.java
 * @author Matthew Adshead
 * @date 01.03.13
 * @version 2.5
 * @see http://www.jfree.org/jfreechart/api/javadoc/index.html and
 *      http://docs.oracle.com/javase/6/docs/api/
 * 
 *      \brief Object which manages colour schemes.
 * 
 *      This class stores and manages a number of colour schemes, as well as
 *      providing an interface to change which colour scheme is used when
 *      rendering charts. It also stores custom renderer classes for each kind
 *      of chart, which allow the charts to be drawn using the colour maps in
 *      the ColourMap array.
 */

public class ColourManager extends JFrame {

	/**
	 * Takes a chart and creates a new legend for it based on renderer colours.
	 * 
	 * @param chart
	 *            The JFreeChart object to construct legend for.
	 * @param renderer
	 *            The renderer for the chart.
	 * @return JFreeChart object LegendItemCollection, which is used to draw
	 *         colour legends on charts.
	 */
	public LegendItemCollection GetLegend(JFreeChart chart,
			CustomBarRenderer renderer) {
		XYPlot plot = chart.getXYPlot();
		XYDataset dataset = plot.getDataset();
		LegendItemCollection legend = new LegendItemCollection();

		for (int i = 0; i < chart.getXYPlot().getSeriesCount(); ++i) {
			LegendItem li = new LegendItem((String) dataset.getSeriesKey(i),
					renderer.GetMap().GetColour(i).GetColour());
			legend.add(li);
		}
		return (legend);
	}

	/**
	 * Access method for the active colour map.
	 * 
	 * @return Colour map at the active map index in the array.
	 */
	public ColourMap GetActiveMap() {
		return (m_colourMaps[m_activeMap]);
	}

	/**
	 * Mutator method for active map.
	 * 
	 * @param mapIndex
	 *            - Index for new active map.
	 * @return Boolean representing operation success.
	 */
	public boolean SetActiveMap(int mapIndex) {
		m_activeMap = mapIndex;
		SetRenderers();
		return (true);
	}

	/**
	 * Access method for maps in the array.
	 * 
	 * @param mapIndex
	 *            - Index of the array.
	 * @return ColourMap at index in the array.
	 */
	public ColourMap GetMap(int mapIndex) {
		return (m_colourMaps[mapIndex]);
	}

	/**
	 * Mutator method for maps in the array.
	 * 
	 * @param mapIndex
	 *            - Index of the array.
	 * @param map
	 *            - New colour map.
	 * @return Boolean representing operation success.
	 */
	public boolean SetMap(int mapIndex, ColourMap map) {
		m_colourMaps[mapIndex] = map;
		SetRenderers();
		return (true);
	}

	/**
	 * Initialises/re-initialises renderer objects using active map.
	 * 
	 * @return Boolean representing operation success.
	 */
	public boolean SetRenderers() {
		m_barRenderer = new CustomBarRenderer(GetActiveMap());

		m_pieRenderer = new CustomPieRenderer(GetActiveMap());
		return (true);
	}

	/**
	 * Access method for bar renderer.
	 * 
	 * @return Bar renderer object.
	 */
	public CustomBarRenderer GetBarRenderer() {
		return (m_barRenderer);
	}

	/**
	 * Access method for pie renderer.
	 * 
	 * @return Pie renderer object.
	 */
	public CustomPieRenderer GetPieRenderer() {
		return (m_pieRenderer);
	}

	/**
	 * Initialises the colour manager.
	 */
	public ColourManager() {
		InitMaps();
		InitGUI();
		setSize(new Dimension(350, 250));
		setTitle("Colour Options");
	}

	/**
	 * Initialises the GUI components.
	 */
	public void InitGUI() {

		cmActionListener listener = new cmActionListener();

		Container m_container = getContentPane();
		m_container.setLayout(new FlowLayout());

		JPanel optionsPanel = new JPanel(new GridLayout(4, 2));
		m_container.add(optionsPanel);

		JLabel dropboxText = new JLabel("Select colour scheme: ");
		optionsPanel.add(dropboxText);

		String[] colourMaps = new String[m_mapCount];
		for (int i = 0; i < m_mapCount; i++) {
			colourMaps[i] = GetMap(i).GetMapName();
		}

		m_mapList = new JComboBox(colourMaps);
		m_mapList.setPreferredSize(new Dimension(150, 10));
		m_mapList.addActionListener(listener);
		optionsPanel.add(m_mapList);

		JLabel blankLabel1 = new JLabel("");
		optionsPanel.add(blankLabel1);

		m_previewContainer = new JPanel();
		optionsPanel.add(m_previewContainer);

		m_previewPanel = DrawSwatches(m_mapList.getSelectedIndex());
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

		if (m_cmFirstTimeOpen) {
			setVisible(false);
			m_cmFirstTimeOpen = false;
		} else {
			setVisible(true);
		}
	}

	/**
	 * Creates/redraws the preview panel.
	 * 
	 * @param mapIndex
	 *            - The index of the map to use when drawing the panel.
	 * @return JPanel containing preview of map colours.
	 */
	public JPanel DrawSwatches(int mapIndex) {

		JPanel swatchPanel = new JPanel();
		Border swatchPanelBorder = BorderFactory.createTitledBorder("Preview");
		swatchPanel.setBorder(swatchPanelBorder);

		Dimension swatchSize = new Dimension(20, 20);
		int borderWidth = 1;
		Color borderColour = Color.black;

		Border swatchOutline = BorderFactory.createMatteBorder(borderWidth,
				borderWidth, borderWidth, borderWidth, borderColour);

		ColourMap map = GetMap(mapIndex);
		Color colour;

		for (int colourIndex = 0; colourIndex < 5; colourIndex++) {
			switch (colourIndex) {

			case 0:
				JPanel colour1 = new JPanel();
				colour1.setSize(swatchSize);
				colour1.setBorder(swatchOutline);
				colour = map.GetColour(colourIndex).GetColour();
				colour1.setBackground(colour);
				swatchPanel.add(colour1);
				break;

			case 1:
				JPanel colour2 = new JPanel();
				colour2.setSize(swatchSize);
				colour2.setBorder(swatchOutline);
				colour = map.GetColour(colourIndex).GetColour();
				colour2.setBackground(colour);
				swatchPanel.add(colour2);
				break;

			case 2:
				JPanel colour3 = new JPanel();
				colour3.setSize(swatchSize);
				colour3.setBorder(swatchOutline);
				colour = map.GetColour(colourIndex).GetColour();
				colour3.setBackground(colour);
				swatchPanel.add(colour3);
				break;

			case 3:
				JPanel colour4 = new JPanel();
				colour4.setSize(swatchSize);
				colour4.setBorder(swatchOutline);
				colour = map.GetColour(colourIndex).GetColour();
				colour4.setBackground(colour);
				swatchPanel.add(colour4);
				break;

			case 4:
				JPanel colour5 = new JPanel();
				colour5.setSize(swatchSize);
				colour5.setBorder(swatchOutline);
				colour = map.GetColour(colourIndex).GetColour();
				colour5.setBackground(colour);
				swatchPanel.add(colour5);
				break;
			}
		}
		return (swatchPanel);
	}

	/**
	 * Initialises the colour maps.
	 */
	public void InitMaps() {
		Colour[] colours = new Colour[5];

		for (int i = 0; i < 5; i++) {
			switch (i) {
			case 0:
				Colour black = new Colour("#000000");
				colours[i] = black;
				break;
			case 1:
				Colour darkgrey = new Colour("#222222");
				colours[i] = darkgrey;
				break;
			case 2:
				Colour grey = new Colour("#555555");
				colours[i] = grey;
				break;
			case 3:
				Colour lightgrey = new Colour("#BBBBBB");
				colours[i] = lightgrey;
				break;
			case 4:
				Colour white = new Colour("#EEEEEE");
				colours[i] = white;
				break;
			}
		}

		ColourMap greyscale = new ColourMap(colours, "Greyscale");
		SetMap(0, greyscale);

		for (int i = 0; i < 5; i++) {
			switch (i) {
			case 0:
				Colour colour1 = new Colour("#556270");
				colours[i] = colour1;
				break;
			case 1:
				Colour colour2 = new Colour("#4ECDC4");
				colours[i] = colour2;
				break;
			case 2:
				Colour colour3 = new Colour("#C7F464");
				colours[i] = colour3;
				break;
			case 3:
				Colour colour4 = new Colour("#FF6B6B");
				colours[i] = colour4;
				break;
			case 4:
				Colour colour5 = new Colour("#C44D58");
				colours[i] = colour5;
				break;
			}
		}

		ColourMap nicecolours = new ColourMap(colours, "Nice Colours");
		SetMap(1, nicecolours);
		SetActiveMap(0);
		SetRenderers();
	}

	/**
	 * Saves the choice of active map and closes the UI window.
	 */
	public void SaveAndClose() {
		SetActiveMap(m_mapList.getSelectedIndex());
		GroupFGMApplication.dataVisualizer.RedrawChartColour();
		setVisible(false);
	}

	/**
	 * Closes UI window.
	 */
	public void CloseFrame() {
		setVisible(false);
	}

	/**
	 * Inner class acting as an action listener for the GUI components.
	 * 
	 * @author Matthew Adshead
	 */
	private class cmActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			/* When combo box changed, redraw preview panel. */
			if (e.getSource() == m_mapList) {

				m_previewContainer.removeAll();
				m_previewPanel = DrawSwatches(m_mapList.getSelectedIndex());
				m_previewContainer.add(m_previewPanel);
				m_previewPanel.repaint();
				m_previewPanel.revalidate();
			}

			if (e.getSource() == m_closeButton) {
				CloseFrame();
			}

			if (e.getSource() == m_saveButton) {
				GroupFGMApplication.dataVisualizer.SelectChartTab(); // line
																		// added
				SaveAndClose();
			}
		}
	}

	/** Constant representing the total number of colour maps. */
	private final int m_mapCount = 2;

	/** Array of colour map objects. */
	private ColourMap[] m_colourMaps = new ColourMap[m_mapCount];

	/** Index of current active map. */
	private int m_activeMap;

	/** Custom renderer class for bar charts. */
	private CustomBarRenderer m_barRenderer;

	/** Custom renderer class for pie charts. */
	private CustomPieRenderer m_pieRenderer;

	/**
	 * UI Elements which needed to be accessed by the ActionListener class. A
	 * combo box containing the names of the colour maps.
	 */
	private JComboBox m_mapList;

	/**
	 * UI Elements which needed to be accessed by the ActionListener class.
	 * Panel containing samples of the colours in the colour map.
	 */
	private JPanel m_previewPanel;

	/**
	 * UI Elements which needed to be accessed by the ActionListener class.
	 * Panel which holds the preview panel, for the purpose of redrawing it.
	 */
	private JPanel m_previewContainer;

	/**
	 * UI Elements which needed to be accessed by the ActionListener class. Save
	 * button, when clicked saves changes and closes GUI.
	 */
	private JButton m_saveButton;

	/**
	 * UI Elements which needed to be accessed by the ActionListener class.
	 * Close button, when clicked closes GUI.
	 */
	private JButton m_closeButton;

	/** flag checking if the manager is opening first time */
	private Boolean m_cmFirstTimeOpen = true;

} /** end class ColourManager */
