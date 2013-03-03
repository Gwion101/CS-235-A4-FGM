import java.awt.Color;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.PieDataset;

/**
 * @file CustomPieRenderer.java
 * @author Matthew Adshead
 * @date 24.02.13
 * @version 1.1
 * @see http://www.jfree.org/jfreechart/api/javadoc/index.html and
 *      http://docs.oracle.com/javase/6/docs/api/
 * 
 *      \brief Custom pie chart renderer class.
 * 
 *      This class is a custom renderer class which is used to render pie chart
 *      objects using colours from the stored colour map object.
 */

public class CustomPieRenderer {

	/**
	 * Method for determining the colour for pie segments to be rendered with
	 * and applying those colours to the chart object.
	 * 
	 * @param plot
	 *            - PiePlot object from the pie chart.
	 * @param dataset
	 *            - PieDataset object from the pie chart.
	 */
	public void SetColor(PiePlot plot, PieDataset dataset) {

		int colours = m_map.GetNumber();
		for (int i = 0; i < dataset.getItemCount(); i++) {
			int colourIndex = i % colours;
			Color colour = m_map.GetColour(colourIndex).GetColour();
			plot.setSectionPaint(dataset.getKey(i), colour);
		}
	}

	/**
	 * Access method for the renderer colour map.
	 * 
	 * @return The renderer colour map.
	 */
	public ColourMap GetMap() {
		return (m_map);
	}

	/**
	 * Mutator method for the renderer colour map.
	 * 
	 * @param map
	 *            - New colour map for renderer.
	 * @return Boolean representing operation success.
	 */
	public boolean SetMap(ColourMap map) {
		m_map = map;
		return (true);
	}

	/**
	 * Initialises the CustomPieRenderer.
	 * 
	 * @param The
	 *            colour map for rendering the chart.
	 */
	public CustomPieRenderer(ColourMap map) {
		SetMap(map);
	}

	/** ColourMap object used to define colours in the renderer. */
	private ColourMap m_map;

} /** end class CustomPieRenderer */
