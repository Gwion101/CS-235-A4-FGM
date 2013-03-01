public class ColourMap {
	
	private final int m_mapSize = 5;
	
	private Colour[] m_colourMap = new Colour[m_mapSize];
	private String m_mapName;
	
	public ColourMap(Colour[] colours, String name) {
		initMap();
		if (colours.length <= m_mapSize) {
			for (int i=0; i<colours.length; i++) {
				setColour(i, colours[i]);
			}
		} else {
			for (int i=0; i<m_mapSize; i++) {
				setColour(i, colours[i]);
			}
			System.out.println("Error: The array you are using is too big. A" +
			" ColourMap can only hold " + m_mapSize + " colours. The first " +
			m_mapSize + " have been used.");
		}
		setMapName(name);
	}
	
	public void initMap() {
		for (int i=0; i<5; i++) {
			setColour(i, null);
		}
	}
	
	public void setColour(int i, Colour colour) {
		m_colourMap[i] = colour;
	}
	
	public Colour getColour(int i) {
		return(m_colourMap[i]);
	}
	
	public int getNumber() {
		int num = 0;
		for (int i=0; i<m_colourMap.length; i++) {
			if (!(m_colourMap[i].equals(null))) {
				num++;
			}
		}
		return(num);
	}
	
	public void setMapName(String name) {
		m_mapName = name;
	}
	
	public String getMapName() {
		return(m_mapName);
	}
}