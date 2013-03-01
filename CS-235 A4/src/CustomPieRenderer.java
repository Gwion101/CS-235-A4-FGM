import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.PieDataset;

public class CustomPieRenderer {
	
	private ColourMap m_map;
	
	public CustomPieRenderer(ColourMap map) {
		setMap(map);
    }
	
	public ColourMap getMap() {
		return(m_map);
	}
	
	public void setMap(ColourMap map) {
		m_map = map;
	} 
    
    public void setColor(PiePlot plot, PieDataset dataset) {
    	int colours = m_map.getNumber();
    	for (int i = 0; i < dataset.getItemCount(); i++) { 
    		int colourIndex = i % colours; 
            plot.setSectionPaint(dataset.getKey(i), m_map.getColour(colourIndex).getColour()); 
        } 
    }
}
