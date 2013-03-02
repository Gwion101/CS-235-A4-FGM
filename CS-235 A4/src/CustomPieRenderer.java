import java.awt.Color;

import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.PieDataset;

/**
 * \file 	 CustomBarRenderer.java
 * \author 	 Matthew Adshead
 * \date	 15 Feb 2013
 * \version  1.1
 * 
 * \brief Custom pie chart renderer class.
 * 
 * This class is a custom renderer class which is used to
 * render pie chart objects using colours from the stored
 * colour map object.
 */

public class CustomPieRenderer {
	
	/**
	 * ColourMap object used to define colours in the renderer.
	 */
	private ColourMap m_map;
	
	/**
	 * Initialises the CustomPieRenderer.
	 * \param The colour map for rendering the chart.
	 */
	public CustomPieRenderer(ColourMap map) {
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
	 * Method for determining the colour for pie segments
	 * to be rendered with and applying those colours
	 * to the chart object.
	 * \param plot - PiePlot object from the pie chart.
	 * \param dataset - PieDataset object from the pie chart.
	 */
    public void setColor(PiePlot plot, PieDataset dataset) {
    	int colours = m_map.getNumber();
    	for (int i = 0; i < dataset.getItemCount(); i++) { 
    		int colourIndex = i % colours; 
    		Color colour = m_map.getColour(colourIndex).getColour();
            plot.setSectionPaint(dataset.getKey(i), colour);
        } 
    }
}
