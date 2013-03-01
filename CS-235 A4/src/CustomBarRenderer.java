import org.jfree.chart.renderer.xy.*;
import java.awt.Paint;

public class CustomBarRenderer extends ClusteredXYBarRenderer {
	
	private ColourMap m_map;
	
	public CustomBarRenderer(ColourMap map) {
		setMap(map);
    }
	
	public ColourMap getMap() {
		return(m_map);
	}
	
	public void setMap(ColourMap map) {
		m_map = map;
	}
	
	public Paint getItemPaint(final int row, final int column) {
		int colours = m_map.getNumber();
		int colourIndex = row % colours;
		Colour itemColour = m_map.getColour(colourIndex);
		return(itemColour.getColour());
    }
}