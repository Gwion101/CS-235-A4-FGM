import org.jfree.chart.renderer.xy.*;
import java.awt.Paint;

/**
 * \file 	 CustomBarRenderer.java
 * \author 	 Matthew Adshead
 * \date	 15 Feb 2013
 * \version  1.1
 * 
 * \brief Custom bar chart renderer class.
 * 
 * This class is a custom JFreeChart renderer class which is used to
 * render bar chart objects using colours from the stored
 * colour map object.
 */

public class CustomBarRenderer extends ClusteredXYBarRenderer {
	
	/**
	 * ColourMap object used to define colours in the renderer.
	 */
	private ColourMap m_map;
	
	/**
	 * Initialises the CustomBarRenderer.
	 * \param The colour map for rendering the chart.
	 */
	public CustomBarRenderer(ColourMap map) {
		setMap(map);
    }
	
	/**
	 * Access method for the renderer colour map.
	 * \return The renderer colour map.
	 */
	public ColourMap getMap() {
		return(m_map);
	}
	
	/**
	 * Mutator method for the renderer colour map.
	 * \return Boolean representing operation success.
	 * \param map - New colour map for renderer.
	 */
	public boolean setMap(ColourMap map) {
		m_map = map;
		return(true);
	}
	
	/**
	 * Overridden method from superclass, called by charts when
	 * determining the colour for bars to be rendered with.
	 * \return Paint - Color object for rendering bar.
	 * \param row - Index of series.
	 * \param column - Index of series item.
	 */
	public Paint getItemPaint(final int row, final int column) {
		int colours = m_map.getNumber();
		int colourIndex = row % colours;
		Colour itemColour = m_map.getColour(colourIndex);
		return(itemColour.getColour());
    }
}