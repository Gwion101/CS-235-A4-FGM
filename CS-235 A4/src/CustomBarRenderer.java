import java.awt.Paint;
import org.jfree.chart.renderer.xy.*;

/**
 * @file CustomBarRenderer.java
 * @author Matthew Adshead
 * @date 27.02.13
 * @version 1.1
 * @see http://www.jfree.org/jfreechart/api/javadoc/index.html and
 *      http://docs.oracle.com/javase/6/docs/api/
 * 
 *      \brief Custom bar chart renderer class.
 * 
 *      This class is a custom JFreeChart renderer class which is used to render
 *      bar chart objects using colours from the stored colour map object.
 */

public class CustomBarRenderer extends ClusteredXYBarRenderer {

	/**
	 * Overridden method from superclass, called by charts when determining the
	 * colour for bars to be rendered with.
	 * 
	 * @param row
	 *            - Index of series.
	 * @param column
	 *            - Index of series item.
	 * @return Paint - Color object for rendering bar.
	 */
	@Override
	public Paint getItemPaint(final int row, final int column) {
		int colours = m_map.GetNumber();
		int colourIndex = row % colours;
		Colour itemColour = m_map.GetColour(colourIndex);
		return (itemColour.GetColour());
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
	 * Initialises the CustomBarRenderer.
	 * 
	 * @param The
	 *            colour map for rendering the chart.
	 */
	public CustomBarRenderer(ColourMap map) {
		SetMap(map);
	}

	/** ColourMap object used to define colours in the renderer. */
	private ColourMap m_map;

} /* end class CustomBarRenderer */
